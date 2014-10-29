package br.ifes.leds.sincap.controleInterno.cln.cgt;

import br.ifes.leds.sincap.controleInterno.cgd.InstituicaoNotificadoraRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.InstituicaoNotificadora;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * Método responsável por buscar uma lista de intituições notificadoras através de uma lista de Long.
     * @return Lista de instituição Notificadora.
     */
    public List<InstituicaoNotificadora> obter(List<Long> idInstituicao) {
        return instituicaoNotificadoraRepository.findByIdIn(idInstituicao);
    }

    /**
     * Método responsável por cadastrar uma instituicao no banco.
     * @return Objeto instituição Notificadora.
     */
    public InstituicaoNotificadora salvar(InstituicaoNotificadora instituicaoNotificadora) {
        return instituicaoNotificadoraRepository.save(instituicaoNotificadora);
    }
}
