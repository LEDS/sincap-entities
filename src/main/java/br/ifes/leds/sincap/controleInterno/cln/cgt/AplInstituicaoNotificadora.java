package br.ifes.leds.sincap.controleInterno.cln.cgt;

import br.ifes.leds.reuse.endereco.cgd.EnderecoRepository;
import br.ifes.leds.sincap.controleInterno.cgd.InstituicaoNotificadoraRepository;
import br.ifes.leds.sincap.controleInterno.cgd.TelefoneRepository;
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
    @Autowired
    private TelefoneRepository telefoneRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
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
     * Método responsável por cadastrar uma instituicao no banco.
     * @return Objeto instituição Notificadora.
     */
    public InstituicaoNotificadora salvar(InstituicaoNotificadora instituicaoNotificadora) {
        enderecoRepository.save(instituicaoNotificadora.getEndereco());
        telefoneRepository.save(instituicaoNotificadora.getTelefone());
        return instituicaoNotificadoraRepository.save(instituicaoNotificadora);
    }
}
