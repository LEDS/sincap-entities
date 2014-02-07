package br.ifes.leds.sincap.controleInterno.cln.cgt;

import br.ifes.leds.sincap.controleInterno.cgd.TipoMotivoRecusaRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.TipoMotivoRecusa;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 20102BSI0553
 */
@Service
public class AplTipoMotivoRecusa {
    @Autowired
    TipoMotivoRecusaRepository tipoMotivoRecusaRepository;
    
    public TipoMotivoRecusa obter(String nome)
    {
        return tipoMotivoRecusaRepository.findByNome(nome);
    }
    
    public List<TipoMotivoRecusa> obter() throws Exception{
            return this.tipoMotivoRecusaRepository.findAll();
    }
    
    public TipoMotivoRecusa obter(Long id) throws Exception{
            return this.tipoMotivoRecusaRepository.findOne(id);
    }
}
