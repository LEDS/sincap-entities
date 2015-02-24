/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt;

import br.ifes.leds.reuse.ledsExceptions.CRUDExceptions.ViolacaoDeRIException;
import br.ifes.leds.reuse.utility.Utility;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Funcionario;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.EntrevistaRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ProcessoNotificacaoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.AtualizacaoEstado;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Entrevista;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.EstadoNotificacaoEnum;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.ProcessoNotificacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto.EntrevistaDTO;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto.ProcessoNotificacaoDTO;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.interfaces.DataCadastro;
import br.ifes.leds.sincap.validacao.groups.entrevista.EntrevistaNaoRealizada;
import br.ifes.leds.sincap.validacao.groups.entrevista.EntrevistaPacienteMenorIdade;
import br.ifes.leds.sincap.validacao.groups.entrevista.EntrevistaRealizadaDoacaoAutorizada;
import br.ifes.leds.sincap.validacao.groups.entrevista.EntrevistaRealizadaDoacaoNaoAutorizada;
import org.dozer.Mapper;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.EstadoNotificacaoEnum.AGUARDANDOANALISEENTREVISTA;

/**
 * Classe que gera o DTO e administra os dados da Entrevista do Processo de
 * Doacao
 *
 * @author 20112bsi0083
 */
@Repository
public class AplEntrevista {

    @Autowired
    private Utility utility;
    @Qualifier("mapper")
    @Autowired
    private Mapper mapper;
    @Autowired
    private EntrevistaRepository entrevistaRepository;
    @Autowired
    private ProcessoNotificacaoRepository notificacaoRepository;
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();


    public List<EntrevistaDTO> getAllEntrevistas() {
        return utility.mapList(entrevistaRepository.findAll(), EntrevistaDTO.class);
    }

    public void salvarEntrevista(EntrevistaDTO entrevistaDTO) {
        salvarEntrevista(mapper.map(entrevistaDTO, Entrevista.class));
    }

    public void salvarEntrevista(Entrevista entrevista) {
        entrevistaRepository.save(entrevista);
    }

    public ProcessoNotificacao salvarEntrevista(ProcessoNotificacaoDTO processoNotificacaoDTO, Long idFuncionario) {
        ProcessoNotificacao notificacaoView = mapper.map(processoNotificacaoDTO, ProcessoNotificacao.class);
        final Set<ConstraintViolation<ProcessoNotificacao>> violations = validarEntrevista(notificacaoView);

        if (!violations.isEmpty()) {
            throw new ViolacaoDeRIException(violations);
        }


        ProcessoNotificacao notificacaoBd = notificacaoRepository.findOne(processoNotificacaoDTO.getId());

        notificacaoBd.setEntrevista(notificacaoView.getEntrevista());
        notificacaoBd.setObito(notificacaoView.getObito());

        verificaDataCadastro(notificacaoBd.getEntrevista());
        verificaCausaNaoDoacao(notificacaoBd, notificacaoView);
        verificaOQueDeveSerNull(notificacaoBd.getEntrevista());

        addNovoEstado(AGUARDANDOANALISEENTREVISTA, notificacaoBd, idFuncionario);

        try {
            return notificacaoRepository.save(notificacaoBd);
        } catch (Exception e) {
            validarEntrevista(notificacaoBd);
            throw new ViolacaoDeRIException(e);
        }
    }

    /**
     * Verifica se a data de cadastro existe. Se não existir, então a data de cadastro é definida.
     *
     * @param notificacao A notificação a ser verificada. ({@code Obito}, {@code Entrevista} ou {@code Captacao})
     */
    private static void verificaDataCadastro(DataCadastro notificacao) {
        if (notificacao.getDataCadastro() == null) {
            notificacao.setDataCadastro(new DateTime().toCalendar(Locale.getDefault()));
        }
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

    private static void verificaOQueDeveSerNull(Entrevista entrevista) {
        if (entrevista != null) {
            if (entrevista.haCausaNaoDoacao()) {
                entrevista.setResponsavel(null);
                entrevista.setResponsavel2(null);
                entrevista.setTestemunha1(null);
                entrevista.setTestemunha2(null);
            }
            if (!entrevista.isEntrevistaRealizada()) {
                entrevista.setDataEntrevista(null);
                entrevista.setDoacaoAutorizada(false);
            }
        }
    }

    public void addNovoEstado(EstadoNotificacaoEnum enumEstado, ProcessoNotificacao processo, Long idFuncionario) {

        AtualizacaoEstado novoEstado = new AtualizacaoEstado();

        novoEstado.setDataAtualizacaos(Calendar.getInstance());
        novoEstado.setEstadoNotificacao(enumEstado);
        novoEstado.setFuncionario(this.getFuncionario(idFuncionario));

        processo.mudarEstadoAtual(novoEstado);
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

    private static Set<ConstraintViolation<ProcessoNotificacao>> validarEntrevista(ProcessoNotificacao processo) {
        Set<ConstraintViolation<ProcessoNotificacao>> violations;
        if (!processo.getEntrevista().isEntrevistaRealizada()) {
            violations = validator.validate(processo, EntrevistaNaoRealizada.class);
        } else if (!processo.getEntrevista().isDoacaoAutorizada()) {
            violations = validator.validate(processo, EntrevistaRealizadaDoacaoNaoAutorizada.class);
        } else {
            violations = validator.validate(processo, EntrevistaRealizadaDoacaoAutorizada.class);
            if (processo.getObito().getIdadePaciente() < 18) {
                violations.addAll(validator.validate(processo, EntrevistaPacienteMenorIdade.class));
            }
        }
        return violations;
    }
}
