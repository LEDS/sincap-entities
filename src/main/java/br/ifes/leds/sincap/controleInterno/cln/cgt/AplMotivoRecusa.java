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
        TipoMotivoRecusa tipo = this.obterTipoMotivoRecusa(2);
        return motivoRecusaRepository.findByTipoMotivoRecusa(tipo);
    }

    public MotivoRecusa salvar(MotivoRecusa motivoR) {
        return motivoRecusaRepository.save(motivoR);
    }

    // TIPO MOTIVO RECUSA
    public List<MotivoRecusa> obterTodosRecusaFamiliar() {
        // 1 -- id do Contra Indicação Médica
        TipoMotivoRecusa tipo = this.obterTipoMotivoRecusa("Recusa Familiar");
        return motivoRecusaRepository.findByTipoMotivoRecusa(tipo);
    }

    public TipoMotivoRecusa salvar(TipoMotivoRecusa tipoMotivoR) {
        return tipoMotivoRecusaRepository.save(tipoMotivoR);
    }

    // All    
    public TipoMotivoRecusa obterTipoMotivoRecusa(long id) {
        return tipoMotivoRecusaRepository.findOne(id);
    }

    public TipoMotivoRecusa obterTipoMotivoRecusa(String tipoMotivoRecusa) {
        return tipoMotivoRecusaRepository.findByNome(tipoMotivoRecusa);
    }

    public MotivoRecusa obter(long id) {
        return motivoRecusaRepository.findOne(id);
    }
}
