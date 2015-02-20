package br.ifes.leds.sincap.test;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.*;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto.*;
import org.dozer.Mapper;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Set;

import static br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoNaoDoacao.PROBLEMAS_ESTRUTURAIS;
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Locale.getDefault;
import static org.junit.Assert.*;

public class EntrevistaValidatorTest extends AbstractionTest {

    public static final boolean sim = true;
    public static final boolean nao = false;
    private static Validator validator;
    
    @Autowired
    private Mapper mapper;
    private ProcessoNotificacao processoNotificacao;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    @Before
    public void before() {
        this.processoNotificacao = processoComObitoValido();
    }
    
    @Test
    public void processoSemId() {
        final Set<ConstraintViolation<ProcessoNotificacao>> violations = validator.validate(processoNotificacao);
        boolean processoComId = sim;

        for (ConstraintViolation<ProcessoNotificacao> violation : violations) {
            if (violation.getMessage().equals("Processo sem ID"))
                processoComId = nao;
        }

        assertFalse("Processo sem ID está sendo válido", processoComId);
    }

    @Test
    public void processoComId() {
        processoNotificacao.setId(1L);
        final Set<ConstraintViolation<ProcessoNotificacao>> violations = validator.validate(processoNotificacao);
        boolean processoComId = sim;

        for (ConstraintViolation<ProcessoNotificacao> violation : violations) {
            if (violation.getMessage().equals("Processo sem ID"))
                processoComId = nao;
        }

        assertTrue("Processo com ID está sendo inválido", processoComId);
    }
    
    @Test
    public void processoComEntrevistaNull() {
        processoNotificacao.setId(1L);
        Set<ConstraintViolation<ProcessoNotificacao>> violations = validator.validate(processoNotificacao);
        
        assertEquals(0, violations.size());
    }

    @Test
    public void entrevistaNaoRealizadaValida() {
        processoNotificacao.setId(1L);
        processoNotificacao.setEntrevista(entrevistaNaoRealizada());
        processoNotificacao.setCausaNaoDoacao(new CausaNaoDoacao());
        processoNotificacao.getCausaNaoDoacao().setTipoNaoDoacao(PROBLEMAS_ESTRUTURAIS);
        Set<ConstraintViolation<ProcessoNotificacao>> violations = validator.validate(processoNotificacao);

        assertEquals("Entrevista nao validando quando deveria.", 0, violations.size());
    }

    private Entrevista entrevistaNaoRealizada() {
        EntrevistaDTO entrevistaDTO = EntrevistaDTO.builder()
            .dataCadastro(hoje())
            .entrevistaRealizada(nao)
        .build();

        return mapper.map(entrevistaDTO, Entrevista.class);
    }
    
    private ProcessoNotificacao processoComObitoValido() {
        final ProcessoNotificacaoDTO notificacaoDTO = ProcessoNotificacaoDTO.builder()
                .codigo("SOMECODE")
                .dataAbertura(hoje())
                .historico(new ArrayList<AtualizacaoEstadoDTO>())
                .notificador(1L)
                .obito(ObitoDTO.builder()
                        .dataObito(ontem())
                        .dataCadastro(hoje())
                        .aptoDoacao(sim)
                        .primeiraCausaMortis(new CausaMortis())
                        .segundaCausaMortis(new CausaMortis())
                        .paciente(new PacienteDTO())
                        .setor(1L)
                        .hospital(1L)
                        .build()).build();
        
        return mapper.map(notificacaoDTO, ProcessoNotificacao.class);
    }

    private static Calendar hoje() {
        return Calendar.getInstance(getDefault());
    }

    private static Calendar ontem() {
        final Calendar ontem = hoje();

        ontem.set(DAY_OF_MONTH, ontem.get(DAY_OF_MONTH) - 1);
        
        return ontem;
    }
}
