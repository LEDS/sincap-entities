package br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt;

import br.ifes.leds.reuse.ledsExceptions.CRUDExceptions.ViolacaoDeRIException;
import br.ifes.leds.reuse.utility.Utility;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ObitoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.PacienteRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ProcessoNotificacaoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Obito;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Paciente;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.ProcessoNotificacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto.ObitoDTO;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto.PacienteDTO;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto.ProcessoNotificacaoDTO;
import br.ifes.leds.sincap.validacao.groups.obito.*;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

import static br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CorpoEncaminhamento.NAO_ENCAMINHADO;
import static br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoDocumentoComFoto.PNI;

@Service
public class AplObito {

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

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

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

    public ProcessoNotificacao salvarObito(ProcessoNotificacaoDTO processoNotificacaoDTO){

        ProcessoNotificacao notificacao = mapearProcessoNotificacaoDTO(processoNotificacaoDTO);

        try {
            return notificacaoRepository.save(notificacao);
        } catch (Exception e) {
            validarProcesso(notificacao);
            throw new ViolacaoDeRIException(e);
        }
    }

    private ProcessoNotificacao mapearProcessoNotificacaoDTO(ProcessoNotificacaoDTO processoNotificacaoDTO) {

        return mapper.map(processoNotificacaoDTO, ProcessoNotificacao.class);
    }

    /**
     * Valida o processo de notificação, verificando as restrições anotadas nas classes
     * que compõem um processo.
     *
     * @param notificacao O objeto a ser validado.
     * @throws ViolacaoDeRIException É lançada caso haja alguma violação de RI.
     */
    private static void validarProcesso(ProcessoNotificacao notificacao) throws ViolacaoDeRIException {
        Set<ConstraintViolation<ProcessoNotificacao>> constraintViolations;
        constraintViolations = validator.validate(notificacao);

        if (constraintViolations != null && constraintViolations.size() > 0) {
            throw new ViolacaoDeRIException(constraintViolations);
        }
    }
    public static void validarObito(ProcessoNotificacao processo) {
        Set<ConstraintViolation<ProcessoNotificacao>> violations;

        if (processo.getHistorico().isEmpty()) {
            //validações do novo processo.
            if (processo.getId() != null) {
                //O processo necessita de ID nulo na primeira tentativa de salvar
                violations = validator.validate(processo, NovaNotificacao.class);
            }
        } else {
            //O processo já foi salvo ao menos uma vez
            if (processo.getId() == null) {
                //O processo necessita ter um id caso já exista.
                violations = validator.validate(processo, NotificacaoSalva.class);
            } else {
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
            }
        }
    }

}
