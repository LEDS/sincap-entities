package br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt;

import br.ifes.leds.reuse.ledsExceptions.CRUDExceptions.ViolacaoDeRIException;
import br.ifes.leds.reuse.utility.Utility;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Funcionario;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Hospital;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Notificador;
import br.ifes.leds.sincap.controleInterno.cln.cgt.AplHospital;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ObitoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.PacienteRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ProcessoNotificacaoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.*;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto.ComentarioDTO;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto.ObitoDTO;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto.PacienteDTO;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto.ProcessoNotificacaoDTO;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.interfaces.DataCadastro;
import br.ifes.leds.sincap.validacao.groups.obito.*;
import org.dozer.Mapper;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CorpoEncaminhamento.NAO_ENCAMINHADO;
import static br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoDocumentoComFoto.PNI;

@Service
public class AplObito {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private ObitoRepository obitoRepository;
    @Qualifier("mapper")
    @Autowired
    private Mapper mapper;
    @Autowired
    private Utility utility;
    @Autowired
    private ProcessoNotificacaoRepository notificacaoRepository;
    @Autowired
    private AplHospital aplHospital;

    public static void validarObito(ProcessoNotificacao processo) {
        Set<ConstraintViolation<ProcessoNotificacao>> violations;

        if (processo.getHistorico().isEmpty() && processo.getId() != null) {
            violations = validator.validate(processo, NovaNotificacao.class);
        }
        //O processo já foi salvo ao menos uma vez
        if (processo.getId() == null) {
            //O processo necessita ter um id caso já exista.
            violations = validator.validate(processo, NotificacaoSalva.class);
        }
        if (processo.getObito().getPaciente().getDocumentoSocial().getTipoDocumentoComFoto() == PNI) {
            violations = validator.validate(processo, ObitoNaoPNI.class);
        }
        if (processo.getObito().getCorpoEncaminhamento() == NAO_ENCAMINHADO) {
            violations = validator.validate(processo, ObitoNaoEncaminhado.class);
        } else {
            violations = validator.validate(processo, ObitoEncaminhado.class);
        }
        if (processo.getObito().isAptoDoacao()) {
            violations = validator.validate(processo, ObitoApto.class);
        } else {
            violations = validator.validate(processo, ObitoInapto.class);
        }

        if (violations != null && !violations.isEmpty()) {
            throw new ViolacaoDeRIException(violations);
        }
    }

    /**
     * Obtém todos os pacientes.
     *
     * @return Uma lista de {@code PacienteDTO}.
     */
    public List<PacienteDTO> obterTodosPacientes() {
        return utility.mapList(pacienteRepository.findAll(), PacienteDTO.class);
    }

    /**
     * Obtém os dados de um paciente, dado seu id.
     *
     * @param id O {@code id} do paciente no banco de dados.
     * @return Um DTO de paciente.
     */
    public PacienteDTO obterPaciente(Long id) {
        Paciente pacienteTmp = pacienteRepository.findOne(id);

        return mapper.map(pacienteTmp, PacienteDTO.class);
    }

    /**
     * Salva os dados de um paciente, tanto para alteração quanto para um novo
     * paciente.
     *
     * @param pacienteDTO O objeto que representa o paciente.
     */
    public void salvarPaciente(PacienteDTO pacienteDTO) {
        pacienteRepository.save(mapper.map(pacienteDTO, Paciente.class));
    }

    /**
     * Obtém todos os obitos.
     *
     * @return Uma lista de {@code ObitoDTO}.
     */
    public List<ObitoDTO> obterTodosObitos() {
        return utility.mapList(obitoRepository.findAll(), ObitoDTO.class);
    }

    /**
     * Salva os dados de um óbito, tanto para alteração quanto para um novo
     *
     * @param obitoDTO O objeto que representa o óbito.
     */
    public void salvarObito(ObitoDTO obitoDTO) {
        obitoRepository.save(mapper.map(obitoDTO, Obito.class));
    }

    public void salvarObito(Obito obito) {
        obitoRepository.save(obito);
    }

    public ProcessoNotificacao salvarNovaNotificacao(ProcessoNotificacaoDTO processoNotificacaoDTO, Long idFuncionario) {

        ProcessoNotificacao notificacao = instanciarNovoProcessoNotificacao(processoNotificacaoDTO);

        final Long idHospital = notificacao.getObito().getHospital().getId();
        final Hospital hospitalBd = aplHospital.obter(idHospital);

        notificacao.setNotificador(criarNotificador(idFuncionario));

        setDatasNovaNotificacao(notificacao);

        this.addEstadoInicial(notificacao, idFuncionario);

        notificacao.setCodigo(hospitalBd.getSigla() + notificacao.getObito().getPaciente().getNumeroProntuario());

        validarObito(notificacao);

        try {
            return notificacaoRepository.save(notificacao);
        } catch (Exception e) {
            throw new ViolacaoDeRIException(e);
        }
    }

    public ProcessoNotificacao salvarNovaNotificacao(ProcessoNotificacaoDTO processoNotificacaoDTO, Long idFuncionario, ComentarioDTO comentarioDTO) {

        ProcessoNotificacao notificacao = instanciarNovoProcessoNotificacao(processoNotificacaoDTO);
        Comentario comentario = mapearComentarioDTO(comentarioDTO);

        final Long idHospital = notificacao.getObito().getHospital().getId();
        final Hospital hospitalBd = aplHospital.obter(idHospital);

        notificacao.setNotificador(criarNotificador(idFuncionario));

        setDatasNovaNotificacao(notificacao);

        this.addEstadoInicial(notificacao, idFuncionario);

        notificacao.setCodigo(hospitalBd.getSigla() + notificacao.getObito().getPaciente().getNumeroProntuario());

        validarObito(notificacao);

        try {

            notificacao.addComentario(comentario);

            return notificacaoRepository.save(notificacao);
        } catch (Exception e) {
            throw new ViolacaoDeRIException(e);
        }
    }

    public ProcessoNotificacao salvarObito(ProcessoNotificacaoDTO processoNotificacaoDTO) {

        ProcessoNotificacao notificacao = mapearProcessoNotificacaoDTO(processoNotificacaoDTO);
        validarObito(notificacao);

        try {
            return notificacaoRepository.save(notificacao);
        } catch (Exception e) {
            throw new ViolacaoDeRIException(e);
        }
    }

    private ProcessoNotificacao mapearProcessoNotificacaoDTO(ProcessoNotificacaoDTO processoNotificacaoDTO) {

        return mapper.map(processoNotificacaoDTO, ProcessoNotificacao.class);
    }

    private ProcessoNotificacao instanciarNovoProcessoNotificacao(ProcessoNotificacaoDTO processoNotificacao) {
        ProcessoNotificacao notificacaoASerRetornada;
        ProcessoNotificacao notificacaoDTO = mapper.map(processoNotificacao, ProcessoNotificacao.class);

        if (notificacaoDTO.getId() == null) {
            notificacaoASerRetornada = new ProcessoNotificacao();
        } else {
            notificacaoASerRetornada = notificacaoRepository.findOne(notificacaoDTO.getId());
        }

        notificacaoASerRetornada.setObito(notificacaoDTO.getObito());
        notificacaoASerRetornada.setComentarios(notificacaoDTO.getComentarios());

        verificaCausaNaoDoacao(notificacaoASerRetornada, notificacaoDTO);

        if (notificacaoASerRetornada.getObito().getPaciente().getDocumentoSocial().getTipoDocumentoComFoto() != PNI) {
            if (notificacaoASerRetornada.getObito().getIdadePaciente() < 2 || notificacaoASerRetornada.getObito().getIdadePaciente() > 75) {
                notificacaoASerRetornada.getObito().setAptoDoacao(false);
                notificacaoASerRetornada.setCausaNaoDoacao(new CausaNaoDoacao());
                notificacaoASerRetornada.getCausaNaoDoacao().setId(17L);
            }
        }

        return notificacaoASerRetornada;
    }

    /**
     * Verifica se há alguma causa de não doação na notificação recebida dos controllers.
     * Se houver, então é definida na notificacao a ser salva.
     *
     * @param notificacaoASerSalva O objeto a ser persistido no banco de dados.
     * @param notificacaoView      O objeto recebido dos controllers.
     */
    private static void verificaCausaNaoDoacao(ProcessoNotificacao notificacaoASerSalva, ProcessoNotificacao notificacaoView) {
        boolean haCausaNaoDoacaoEmObito = notificacaoView.getObito() != null && notificacaoView.getObito().haCausaNaoDoacao();
        boolean haCausaNaoDoacaoEmEntrevista = notificacaoView.getEntrevista() != null && notificacaoView.getEntrevista().haCausaNaoDoacao();

        if (haCausaNaoDoacaoEmObito || haCausaNaoDoacaoEmEntrevista) {
            notificacaoASerSalva.setCausaNaoDoacao(notificacaoView.getCausaNaoDoacao());
        } else {
            notificacaoASerSalva.setCausaNaoDoacao(null);
        }
    }

    private Comentario mapearComentarioDTO(ComentarioDTO comentarioDTO){
        return mapper.map(comentarioDTO, Comentario.class);
    }

    private static Notificador criarNotificador(Long idFuncionario) {
        Notificador notificador = new Notificador();
        notificador.setId(idFuncionario);
        return notificador;
    }

    private void setDatasNovaNotificacao(ProcessoNotificacao notificacao) {
        if (notificacao.getDataAbertura() == null) {
            notificacao.setDataAbertura(Calendar.getInstance());
        }

        verificaDataCadastro(notificacao.getObito());
    }
    private static void verificaDataCadastro(DataCadastro notificacao) {
        if (notificacao.getDataCadastro() == null) {
            notificacao.setDataCadastro(new DateTime().toCalendar(Locale.getDefault()));
        }
    }

    /**
     * Adiciona o historio de estados na notificacao, linkando o primeiro estado
     *
     * @param notificacao Notificacao a ser adicionado o estado inicial.
     */
    private void addEstadoInicial(ProcessoNotificacao notificacao, Long idFuncionario) {
        AtualizacaoEstado atualizacaoEstado = new AtualizacaoEstado();

        atualizacaoEstado.setFuncionario(this.getFuncionario(idFuncionario));
        atualizacaoEstado.setDataAtualizacaos(Calendar.getInstance());
        atualizacaoEstado.setEstadoNotificacao(EstadoNotificacaoEnum.AGUARDANDOANALISEOBITO);

        notificacao.mudarEstadoAtual(atualizacaoEstado);
    }

    /**
     * Retorna um funcionario dado um id recebido.
     * OBS.: O funcionario nao tem todos os campos preenchidos, apenas o ID
     *
     * @return - Retorna um funcionario
     */
    private Funcionario getFuncionario(Long idFuncionario) {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(idFuncionario);
        return funcionario;
    }

    public void salvarComentario(Long idProcesso, Comentario comentario){
        ProcessoNotificacao processo = getProcessoNotificacao(idProcesso);
        processo.addComentario(comentario);
        notificacaoRepository.saveAndFlush(processo);
    }

    public ProcessoNotificacao getProcessoNotificacao(Long id) {
        return notificacaoRepository.findOne(id);
    }

}
