package br.ifes.leds.sincap.test;

import br.ifes.leds.sincap.controleInterno.cgd.TipoMotivoRecusaRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.MotivoRecusa;
import br.ifes.leds.sincap.controleInterno.cln.cdp.TipoMotivoRecusa;
import br.ifes.leds.sincap.controleInterno.cln.cgt.AplMotivoRecusa;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author 20102BSI0553
 */
public class AplMotivoRecusaTest extends AbstractionTest  {
    @Autowired
    AplMotivoRecusa apl;
    
    @Autowired
    TipoMotivoRecusaRepository tipoMotivoRecusaRepository;
    
    @Test
    public void cadastraMotivo(){
        
        MotivoRecusa motivoR = new MotivoRecusa();
        TipoMotivoRecusa tipo = apl.obterTipoMotivoRecusa(1);
        
        motivoR.setNome("motivo teste");
        motivoR.setTipoMotivoRecusa(tipo);
        
        apl.salvar(motivoR);
        Assert.assertNotSame(0, motivoR.getId());
    }
    
    @Test
    public void buscarTodos(){        
        Assert.assertNotNull(apl.obter());
    }
}
