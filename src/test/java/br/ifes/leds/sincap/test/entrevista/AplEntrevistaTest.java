package br.ifes.leds.sincap.test.entrevista;

import br.ifes.leds.reuse.ledsExceptions.CRUDExceptions.ViolacaoDeRIException;
import br.ifes.leds.reuse.utility.Utility;
import br.ifes.leds.reuse.utility.function.Function;
import br.ifes.leds.sincap.controleInterno.cgd.FuncionarioRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Funcionario;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.ProcessoNotificacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto.*;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt.AplEntrevista;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.util.dataFactory.ResponsavelData;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.util.dataFactory.TestemunhaData;
import br.ifes.leds.sincap.test.AbstractionTest;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.dozer.Mapper;
import org.fluttercode.datafactory.impl.DataFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.GregorianCalendar;

import static br.ifes.leds.reuse.utility.Factory.criaObjeto;
import static br.ifes.leds.sincap.test.entrevista.EntrevistaTestUtil.dezesseisAnos;
import static br.ifes.leds.sincap.test.entrevista.EntrevistaTestUtil.entrevistaDoacaoAutorizadaDTO;
import static br.ifes.leds.sincap.test.entrevista.EntrevistaTestUtil.processoComObitoValidoDTO;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
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
    private Utility utility;
    @Autowired
    private ResponsavelData responsavelData;
    @Autowired
    private TestemunhaData testemunhaData;
    @Autowired
    private DataFactory dataFactory;
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private EntrevistaTestUtil util;
    // TODO Fazer testes de obter entrevista.

    private EntrevistaDTO entrevistaDTO;
    private ResponsavelDTO responsavelDTO;
    private TestemunhaDTO testemunhaDTO1;
    private TestemunhaDTO testemunhaDTO2;

    @Before
    public void before() {
        this.entrevistaDTO = criaObjeto(EntrevistaDTO.class);
        this.responsavelDTO = mapper.map(responsavelData.criarResponsavel(dataFactory),
                ResponsavelDTO.class);
        this.testemunhaDTO1 = mapper.map(testemunhaData.criarTestemunha(dataFactory),
                TestemunhaDTO.class);
        this.testemunhaDTO2 = mapper.map(testemunhaData.criarTestemunha(dataFactory),
                TestemunhaDTO.class);

        preencherDadosEntrevista();
    }

    private void preencherDadosEntrevista() {
        Funcionario funcionario = funcionarioRepository.findAll().get(0);

        this.entrevistaDTO.setDataEntrevista(new GregorianCalendar(2014, 5, 27, 18, 30));
        this.entrevistaDTO.setDataCadastro(new GregorianCalendar(2014, 5, 27, 22, 55));
        this.entrevistaDTO.setDoacaoAutorizada(true);
        this.entrevistaDTO.setFuncionario(funcionario.getId());
        this.entrevistaDTO.setResponsavel(responsavelDTO);
        this.entrevistaDTO.setTestemunha1(testemunhaDTO1);
        this.entrevistaDTO.setTestemunha2(testemunhaDTO2);
    }

    @Test(expected = ViolacaoDeRIException.class)
    public void validacaoTest() {
        final ProcessoNotificacaoDTO notificacaoDTO = processoComObitoValidoDTO();
        notificacaoDTO.setEntrevista(entrevistaDoacaoAutorizadaDTO());
        notificacaoDTO.getObito().getPaciente().setDataNascimento(dezesseisAnos());

        aplEntrevista.salvarEntrevista(notificacaoDTO, 1L);
    }

    @Test
    public void salvarEntrevistaTest() {
        aplEntrevista.salvarEntrevista(this.entrevistaDTO);

        EntrevistaDTO entrevistaTest = utility.getObjectByMethod(aplEntrevista.getAllEntrevistas(), responsavelDTO.getDocumentoSocial(), getDocumentoSocialResp());

        Assert.assertNotNull(entrevistaTest);
        Assert.assertNotNull(entrevistaTest.getResponsavel().getId());
        Assert.assertNotNull(entrevistaTest.getTestemunha1().getId());
        Assert.assertNotNull(entrevistaTest.getTestemunha2().getId());

        Assert.assertEquals(this.entrevistaDTO.getResponsavel().getEndereco().getCep(),
                entrevistaTest.getResponsavel().getEndereco().getCep());
        Assert.assertEquals(this.entrevistaDTO.getTestemunha1().getNome(),
                entrevistaTest.getTestemunha1().getNome());
        Assert.assertEquals(this.entrevistaDTO.getTestemunha2().getNome(),
                entrevistaTest.getTestemunha2().getNome());
    }

    private Function<EntrevistaDTO, DocumentoComFotoDTO> getDocumentoSocialResp() {
        return new Function<EntrevistaDTO, DocumentoComFotoDTO>() {
            @Override
            public DocumentoComFotoDTO apply(EntrevistaDTO parameter) {
                if (parameter.getResponsavel() != null && parameter.getResponsavel().getDocumentoSocial() != null)
                    return parameter.getResponsavel().getDocumentoSocial();
                return null;
            }
        };
    }
}
