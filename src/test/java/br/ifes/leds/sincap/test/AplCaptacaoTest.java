package br.ifes.leds.sincap.test;

import br.ifes.leds.reuse.ledsExceptions.CRUDExceptions.ViolacaoDeRIException;
import br.ifes.leds.sincap.controleInterno.cgd.CaptadorRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Captador;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto.CaptacaoDTO;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt.AplCaptacao;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;

import static br.ifes.leds.reuse.utility.Factory.criaObjeto;

public class AplCaptacaoTest extends AbstractionTest {

    @Autowired
    private AplCaptacao aplCaptacao;
    @Autowired
    private CaptadorRepository captadorRepository;

    private CaptacaoDTO captacaoDTO;

    @Before
    public void before() {
        this.captacaoDTO = criaObjeto(CaptacaoDTO.class);

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
    public void salvarCaptacaoTest() throws ViolacaoDeRIException {
        aplCaptacao.salvarCaptacao(captacaoDTO);

        CaptacaoDTO captacaoDTOSalvo = aplCaptacao.obterTodos().get(0);

        Assert.assertNotNull(captacaoDTOSalvo);
        Assert.assertNotNull(captacaoDTOSalvo.getId());
    }

}
