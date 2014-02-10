package br.ifes.leds.sincap.controleInterno.cln.cgt;

import br.ifes.leds.sincap.controleInterno.cgd.MotivoRecusaRepository;
import br.ifes.leds.sincap.controleInterno.cgd.TipoMotivoRecusaRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.MotivoRecusa;
import br.ifes.leds.sincap.controleInterno.cln.cdp.TipoMotivoRecusa;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 20102BSI0553
 */
@Service
public class AplMotivoRecusa {
    @Autowired
    TipoMotivoRecusaRepository tipoMotivoRecusaRepository;
    @Autowired
    MotivoRecusaRepository motivoRecusaRepository;
    
    // MOTIVO RECUSA
    public List<TipoMotivoRecusa> obterTodosContraindicacaoMedica() {
        return null;
    }
    
    // TIPO MOTIVO RECUSA
    public List<TipoMotivoRecusa> obterTodosRecusaFamiliar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
