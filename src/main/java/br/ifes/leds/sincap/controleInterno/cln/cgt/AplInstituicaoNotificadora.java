package br.ifes.leds.sincap.controleInterno.cln.cgt;

import br.ifes.leds.sincap.controleInterno.cgd.InstituicaoNotificadoraRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.InstituicaoNotificadora;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Phillipe Lopes
 */
@Service
public class AplInstituicaoNotificadora {

    @Autowired
    private InstituicaoNotificadoraRepository instituicaoNotificadoraRepository;
    
    /**
     * Método responsável por buscar todas as intituições notificadoras.
     * @return Lista de todas instituições Notificadoras.
     */
    public List<InstituicaoNotificadora> obterTodasInstituicoesNotificadoras() {
        return instituicaoNotificadoraRepository.findAll();
    }
    
    /**
     * Método responsável por buscar uma intituição notificadora pelo seu id.
     * @return Objeto instituição Notificadora.
     */
    public InstituicaoNotificadora obter(Long idInstituicao) {
        return instituicaoNotificadoraRepository.findOne(idInstituicao);
    }
}
