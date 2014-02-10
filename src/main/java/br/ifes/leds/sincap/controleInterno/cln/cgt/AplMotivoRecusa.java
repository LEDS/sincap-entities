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
    public List<MotivoRecusa> obterTodosContraindicacaoMedica() {
        // 2 -- id do Contra Indicação Médica
        return motivoRecusaRepository.findByTipoMotivoRecusa(2);
    }
    
    public MotivoRecusa salvar(MotivoRecusa motivoR){
        return motivoRecusaRepository.save(motivoR);
    }
    
    // TIPO MOTIVO RECUSA
    public List<MotivoRecusa> obterTodosRecusaFamiliar() {
        // 1 -- id do Contra Indicação Médica
        TipoMotivoRecusa tipo = this.obterTipoMotivoRecusa(1);
        return motivoRecusaRepository.findByTipoMotivoRecusa(1);//(tipo);
    }
    
    public TipoMotivoRecusa obterTipoMotivoRecusa(long id){
        return tipoMotivoRecusaRepository.findOne(id);
    }
}
