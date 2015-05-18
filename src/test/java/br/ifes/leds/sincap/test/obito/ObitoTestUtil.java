package br.ifes.leds.sincap.test.obito;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CausaMortis;
import br.ifes.leds.sincap.test.TestUtil;
import org.springframework.stereotype.Service;

@Service
public class ObitoTestUtil extends TestUtil {

    static CausaMortis causaMortis(){
        final CausaMortis  causa = new CausaMortis();
        causa.setNome("Causa 1");

        return causa;
    }
}
