package br.ifes.leds.sincap.test.entrevista;

import br.ifes.leds.reuse.ledsExceptions.CRUDExceptions.ViolacaoDeRIException;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ProcessoNotificacaoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.ProcessoNotificacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto.PacienteDTO;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto.ProcessoNotificacaoDTO;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt.AplEntrevista;
import br.ifes.leds.sincap.test.AbstractionTest;
import org.dozer.Mapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.EstadoNotificacaoEnum.AGUARDANDOANALISEENTREVISTA;
import static br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoNaoDoacao.PROBLEMAS_ESTRUTURAIS;
import static br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoNaoDoacao.RECUSA_FAMILIAR;
import static br.ifes.leds.sincap.test.entrevista.EntrevistaTestUtil.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * @author Breno Grillo
 */
public class AplEntrevistaTest extends AbstractionTest {

    @Autowired
    private AplEntrevista aplEntrevista;
    @Autowired
    private Mapper mapper;
    @Autowired
    private ProcessoNotificacaoRepository notificacaoRepository;
    @Autowired
    private EntrevistaTestUtil util;

    @Test(expected = ViolacaoDeRIException.class)
    public void validacaoTest() {
        final ProcessoNotificacaoDTO notificacaoDTO = processoComObitoValidoDTO();
        notificacaoDTO.setEntrevista(entrevistaDoacaoAutorizadaDTO());
        notificacaoDTO.getObito().getPaciente().setDataNascimento(dezesseisAnos());

        aplEntrevista.salvarEntrevista(notificacaoDTO, 1L);
    }

    @Test
    public void salvarEntrevistaNaoRealizada() {
        final ProcessoNotificacao notificacao = notificacaoRepository.saveAndFlush(util.processoComObitoValido());
        final ProcessoNotificacaoDTO notificacaoDTO = mapper.map(notificacao, ProcessoNotificacaoDTO.class);
        final Long idPaciente = notificacaoDTO.getObito().getPaciente().getId();
        notificacaoDTO.setEntrevista(entrevistaNaoRealizadaDTO());
        notificacaoDTO.setCausaNaoDoacao(problemaEstrutural());
        notificacaoDTO.getObito().setPaciente(PacienteDTO.builder().id(idPaciente).nome("Fulano de Tal").build());

        final ProcessoNotificacao notificacaoSalva = aplEntrevista.salvarEntrevista(notificacaoDTO, 1L);

        assertThat(notificacaoSalva.getCausaNaoDoacao(), allOf(
                        hasProperty("tipoNaoDoacao", is(PROBLEMAS_ESTRUTURAIS)),
                        hasProperty("nome", is("Família não localizada")))
        );
        assertThat(notificacaoSalva.getObito().getPaciente(), allOf(
                hasProperty("nome", is("Fulano de Tal")),
                hasProperty("numeroProntuario", notNullValue())
        ));
        assertThat(notificacaoSalva.getUltimoEstado(), hasProperty("estadoNotificacao", is(AGUARDANDOANALISEENTREVISTA)));
        assertThat(notificacaoSalva.getEntrevista(), allOf(
                hasProperty("entrevistaRealizada", is(nao)),
                hasProperty("doacaoAutorizada", is(nao)),
                hasProperty("dataCadastro", notNullValue()),
                hasProperty("dataEntrevista", nullValue())
        ));
    }

    @Test
    public void salvarEntrevistaRealizadaDoacaoNaoAutorizada() {
        final ProcessoNotificacao notificacao = notificacaoRepository.saveAndFlush(util.processoComObitoValido());
        final ProcessoNotificacaoDTO notificacaoDTO = mapper.map(notificacao, ProcessoNotificacaoDTO.class);
        notificacaoDTO.getObito().getPaciente().setNome("Fulano de Tal");
        notificacaoDTO.getObito().getPaciente().setNumeroSUS("algumnumerosusalterado15645");
        notificacaoDTO.setCausaNaoDoacao(recusaFamiliar());
        notificacaoDTO.setEntrevista(entrevistaRealizdaDTO());
        notificacaoDTO.getEntrevista().setDoacaoAutorizada(nao);

        final ProcessoNotificacao notificacaoSalva = aplEntrevista.salvarEntrevista(notificacaoDTO, 1L);

        assertThat(
                notificacaoSalva.getObito().getPaciente(),
                allOf(
                        hasProperty("nome", is("Fulano de Tal")),
                        hasProperty("numeroSUS", is("algumnumerosusalterado15645"))
                ));

        assertThat(
                notificacaoSalva.getCausaNaoDoacao(),
                allOf(
                        hasProperty("nome", is("Familiares indecisos")),
                        hasProperty("tipoNaoDoacao", is(RECUSA_FAMILIAR))
                )
        );
        assertThat(notificacaoSalva.getUltimoEstado(), hasProperty("estadoNotificacao", is(AGUARDANDOANALISEENTREVISTA)));
        assertThat(notificacaoSalva.getEntrevista(), allOf(
                hasProperty("entrevistaRealizada", is(sim)),
                hasProperty("doacaoAutorizada", is(nao)),
                hasProperty("dataCadastro", notNullValue()),
                hasProperty("dataEntrevista", nullValue())
        ));
    }

    private static long problemaEstrutural() {
        return 20L;
    }

    private static long recusaFamiliar() {
        return 3L;
    }
}
