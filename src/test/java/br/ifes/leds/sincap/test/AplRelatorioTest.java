package br.ifes.leds.sincap.test;

import br.ifes.leds.sincap.controleInterno.cgd.CaptadorRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Captador;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto.CaptacaoDTO;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt.AplCaptacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt.AplProcessoNotificacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt.AplRelatorio;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;

import static br.ifes.leds.reuse.utility.Factory.criaObjeto;

public class AplRelatorioTest extends AbstractionTest {

    private AplRelatorio aplRelatorio;
    @Autowired
    AplProcessoNotificacao aplProcessoNotificacao;


    @Before
    public void before() {

    }
    @Test
    public void relatorioTotalDoacaoInstituicao() {


    }

}
