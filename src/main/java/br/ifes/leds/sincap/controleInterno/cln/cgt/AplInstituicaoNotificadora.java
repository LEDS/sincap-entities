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

    public List<InstituicaoNotificadora> obterTodasInstituicoesNotificadoras() {
        return instituicaoNotificadoraRepository.findAll();
    }

    public InstituicaoNotificadora obter(Long idInstituicao) {
        return instituicaoNotificadoraRepository.findOne(idInstituicao);
    }
}
