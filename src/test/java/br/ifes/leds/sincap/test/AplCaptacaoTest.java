package br.ifes.leds.sincap.test;

import br.ifes.leds.reuse.ledsExceptions.CRUDExceptions.ViolacaoDeRIException;
import br.ifes.leds.reuse.utility.Factory;
import br.ifes.leds.reuse.utility.Utility;
import br.ifes.leds.sincap.controleInterno.cgd.CaptadorRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Captador;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.DTO.CaptacaoDTO;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt.AplCaptacao;
import junit.framework.Assert;
import org.dozer.Mapper;
import org.fluttercode.datafactory.impl.DataFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;

public class AplCaptacaoTest extends AbstractionTest {

    @Autowired
    private AplCaptacao aplCaptacao;
    @Autowired
    private CaptadorRepository captadorRepository;
    @Autowired
    private Mapper mapper;
    @Autowired
    private Utility utility;
    @Autowired
    private Factory fabrica;
    @Autowired
    private DataFactory df;

    private CaptacaoDTO captacaoDTO;

    @Before
    public void before() {
        this.captacaoDTO = fabrica.criaObjeto(CaptacaoDTO.class);

        preencherDadosCaptacao();
    }

    private void preencherDadosCaptacao() {
        Captador captador = captadorRepository.findAll().get(0);

        captacaoDTO.setCaptador(captador.getId());
        captacaoDTO.setCaptacaoRealizada(true);
        captacaoDTO.setComentario("Comentario");
        captacaoDTO.setDataCadastro(Calendar.getInstance());
        captacaoDTO.setDataCaptacao(Calendar.getInstance());
    }

    @Test
    public void salvarObitoTest() throws ViolacaoDeRIException {
        aplCaptacao.salvarCaptacao(captacaoDTO);

        CaptacaoDTO captacaoDTOSalvo = aplCaptacao.obterTodos().get(0);

        Assert.assertNotNull(captacaoDTOSalvo);
        Assert.assertNotNull(captacaoDTOSalvo.getId());
    }

}
