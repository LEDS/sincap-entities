/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt;

import br.ifes.leds.reuse.ledsExceptions.CRUDExceptions.ViolacaoDeRIException;
import br.ifes.leds.reuse.utility.Utility;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Funcionario;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.CausaNaoDoacaoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.EntrevistaRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.PacienteRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ProcessoNotificacaoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.AtualizacaoEstado;
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
    private CausaNaoDoacaoRepository naoDoacaoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private EntrevistaRepository entrevistaRepository;
    @Autowired
    private ProcessoNotificacaoRepository notificacaoRepository;
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();


    public List<EntrevistaDTO> getAllEntrevistas() {
        return utility.mapList(entrevistaRepository.findAll(), EntrevistaDTO.class);
    }

    public ProcessoNotificacao salvarEntrevista(ProcessoNotificacaoDTO processoNotificacaoDTO, Long idFuncionario) {
        ProcessoNotificacao notificacaoView = mapper.map(processoNotificacaoDTO, ProcessoNotificacao.class);
        if (notificacaoView.getCausaNaoDoacao() != null && notificacaoView.getCausaNaoDoacao().getId() != null) {
            notificacaoView.setCausaNaoDoacao(naoDoacaoRepository.findOne(notificacaoView.getCausaNaoDoacao().getId()));
        }
        validarEntrevista(notificacaoView);

        if (!notificacaoView.getEntrevista().isEntrevistaRealizada()) {
            pacienteRepository.updateNome(notificacaoView.getObito().getPaciente().getId(), notificacaoView.getObito().getPaciente().getNome());
        } else {
            pacienteRepository.saveAndFlush(notificacaoView.getObito().getPaciente());
        }

        final ProcessoNotificacao notificacaoBd = notificacaoRepository.findOne(processoNotificacaoDTO.getId());
        addNovoEstado(AGUARDANDOANALISEENTREVISTA, notificacaoBd, idFuncionario);
        notificacaoBd.setCausaNaoDoacao(notificacaoView.getCausaNaoDoacao());
        notificacaoBd.setEntrevista(notificacaoView.getEntrevista());

        verificaDataCadastro(notificacaoBd.getEntrevista());

        return notificacaoRepository.saveAndFlush(notificacaoBd);
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

    private static void validarEntrevista(ProcessoNotificacao processo) {
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
        if (!violations.isEmpty()) {
            throw new ViolacaoDeRIException(violations);
        }
    }
}
