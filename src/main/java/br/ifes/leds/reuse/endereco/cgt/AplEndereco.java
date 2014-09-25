package br.ifes.leds.reuse.endereco.cgt;

import br.ifes.leds.reuse.endereco.cdp.Bairro;
import br.ifes.leds.reuse.endereco.cdp.Cidade;
import br.ifes.leds.reuse.endereco.cdp.Estado;
import br.ifes.leds.reuse.endereco.cgd.BairroRepository;
import br.ifes.leds.reuse.endereco.cgd.CidadeRepository;
import br.ifes.leds.reuse.endereco.cgd.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * AplEndereco.java
 *
 * @author 20091BSI0273
 *         Classe de servico para enderecos
 */
@Service
public class AplEndereco {

    @Autowired
    private BairroRepository bairroRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private EstadoRepository estadoRepository;


    /**
     * obterBairroPorID
     *
     * @param id, Long representando o ID do bairro.
     * @return Bairro
     */
    public Bairro obterBairroPorID(Long id) {
        return bairroRepository.findOne(id);
    }

    /**
     * obterBairrosPorCidade
     *
     * @param cidadeId; Long que presenta o ID do cidade
     * @return Lista de bairros relacionados ao cidade dado.
     */
    public List<Bairro> obterBairrosPorCidade(Long cidadeId) {
        return this.bairroRepository.findByCidadeIdOrderByNomeAsc(cidadeId);
    }

    /**
     * obterCidadePorID
     *
     * @param id, Long que representa id do municpio
     * @return o Cidade
     */
    public Cidade obterCidadePorID(Long id) {
        return cidadeRepository.findOne(id);
    }

    /**
     * obterCidadePorEstado
     *
     * @param estadoId, Long que representa o estado ao qual o municipio pertence.
     * @return Lista de cidades do estado dado.
     */
    public List<Cidade> obterCidadesPorEstado(Long estadoId) {
        return this.cidadeRepository.findByEstadoIdOrderByNomeAsc(estadoId);
    }


    /**
     * obterEstado
     *
     * @param id, Long que representa o id do Estado.
     * @return Estado identificado pelo id.
     */
    public Estado obterEstadosPorID(Long id) {
        return estadoRepository.findOne(id);
    }

    /**
     * obterEstadoPornomePais
     *
     * @param paisNome, String que representa o nome do pais.
     * @return Lista de Estado, relacionados ao pais do nome dado.
     */
    public List<Estado> obterEstadosPorNomePais(String paisNome) {
        return this.estadoRepository.findByPaisNomeOrderByNomeAsc(paisNome);
    }
}
