/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt;

import br.ifes.leds.reuse.ledsExceptions.CRUDExceptions.ViolacaoDeRIException;
import br.ifes.leds.reuse.utility.Utility;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.EntrevistaRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.PacienteRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ProcessoNotificacaoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Entrevista;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.ProcessoNotificacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto.EntrevistaDTO;
import br.ifes.leds.sincap.validacao.groups.entrevista.EntrevistaNaoRealizada;
import br.ifes.leds.sincap.validacao.groups.entrevista.EntrevistaPacienteMenorIdade;
import br.ifes.leds.sincap.validacao.groups.entrevista.EntrevistaRealizadaDoacaoAutorizada;
import br.ifes.leds.sincap.validacao.groups.entrevista.EntrevistaRealizadaDoacaoNaoAutorizada;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

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
    @Autowired
    private PacienteRepository pacienteRepository;
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();


    @Transactional
    public void salvarEntrevista(ProcessoNotificacao processo, Long idFuncionario) {
        Set<ConstraintViolation<ProcessoNotificacao>> violations = validarEntrevista(processo);
        if (!violations.isEmpty()) {
            throw new ViolacaoDeRIException(violations);
        }

    }

    public List<EntrevistaDTO> getAllEntrevistas() {
        return utility.mapList(entrevistaRepository.findAll(), EntrevistaDTO.class);
    }

    public void salvarEntrevista(EntrevistaDTO entrevistaDTO) {
        salvarEntrevista(mapper.map(entrevistaDTO, Entrevista.class));
    }

    public void salvarEntrevista(Entrevista entrevista)  {
        entrevistaRepository.save(entrevista);
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
