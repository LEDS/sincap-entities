package br.ifes.leds.reuse.endereco.cgt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifes.leds.reuse.endereco.cdp.Bairro;
import br.ifes.leds.reuse.endereco.cdp.Estado;
import br.ifes.leds.reuse.endereco.cdp.Cidade;
import br.ifes.leds.reuse.endereco.cdp.Pais;
import br.ifes.leds.reuse.endereco.cgd.BairroRepository;
import br.ifes.leds.reuse.endereco.cgd.EnderecoRepository;
import br.ifes.leds.reuse.endereco.cgd.EstadoRepository;
import br.ifes.leds.reuse.endereco.cgd.CidadeRepository;
import br.ifes.leds.reuse.endereco.cgd.PaisRepository;
import java.util.ArrayList;

/**
 * AplEndereco.java
 * @author 20091BSI0273
 * Classe de servico para enderecos
 */
@Service
public class AplEndereco {
    
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private BairroRepository bairroRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private PaisRepository paisRepository;
    
    /*BAIRRO*/
    /**
     * salvarBairro
     * @param bairro, Bairro a ser persistido.
     */
    public void salvarBairro(Bairro bairro){
        this.bairroRepository.save(bairro);
    }

    /**
     * excluirBairro
     * @param bairro, Bairro a ser excluido.
     */
    public void excluirBairro(Bairro bairro){
        this.bairroRepository.delete(bairro);
    }

    /**
     * obterBairroPorID
     * @param id, Long representando o ID do bairro.
     * @return Bairro
     */
    public Bairro obterBairroPorID(Long id){
        return bairroRepository.findOne(id);
    }
    
    public Bairro obterBairroPorNome(String nomeBairro){
        return bairroRepository.findByNomeIgnoreCase(nomeBairro);
    }

    /**
     * obterBairros
     * @return Lista de todos os bairros cadastrados.
     */
    public List<Bairro> obterBairros(){
        return bairroRepository.findAll();
    }

    /**
     * obterBairrosPorCidade
     * @param cidadeId; Long que presenta o ID do cidade
     * @return Lista de bairros relacionados ao cidade dado.
     */
    public List<Bairro> obterBairrosPorCidade (Long cidadeId){	
        Cidade cidade = this.cidadeRepository.findOne(cidadeId);
        List<Bairro> bairros = new ArrayList<Bairro>();
        bairros.addAll(cidade.getBairros());
        return bairros;
    }

    /* CIDADE */
    /**
     * salvarCidade
     * @param cidade, Cidade a ser persistido.
     */
    public void salvarCidade(Cidade cidade){
        this.cidadeRepository.save(cidade);
    }

    /**
     * excluirCidade
     * @param cidade, cidade a ser excluido.
     */
    public void excluirMunicipio(Cidade cidade){
        this.cidadeRepository.delete(cidade);
    }

    /**
     * obterCidadePorID
     * @param id, Long que representa id do municpio
     * @return, o Cidade
     */
    public Cidade obterCidadePorID(Long id){
        return cidadeRepository.findOne(id);
    }

    /**
     * obterCidade
     * @return
     */
    public List<Cidade> obterCidade(){
        return cidadeRepository.findAll();
    }
    
    public Cidade obterCidadePorNome(String nome){
        return cidadeRepository.findByNomeIgnoreCase(nome);
    }

    /**
     * obterCidadePorEstado
     * @param estadoId, Long que representa o estado ao qual o municipio pertence.
     * @return Lista de cidades do estado dado.
     */
    public List<Cidade> obterCidadesPorEstado(Long estadoId){
        Estado estado = estadoRepository.findOne(estadoId);
        List<Cidade> cidades = new ArrayList<Cidade>();
        cidades.addAll(estado.getCidades());
        return cidades;
    }

//    /**
//     * obterCidadePOrSiglaEstado
//     * @param siglaEstado, String que representa a sigla do estado
//     * @return Lista de Ms relacionados ao estado representado pela sigla
//     */
//    public List<Cidade> obterCidadesPorSiglaEstado (String siglaEstado){
//        Estado estado = this.estadoRepository.findBySiglaIgnoreCase(siglaEstado);
//        
//        return estado.getCidades();            
//    }
    
    /* ESTADO */
    /**
     * salvarEstado
     * @param estado, Estado a ser persistido.
     */
    public void salvarEstado(Estado estado){
        this.estadoRepository.save(estado);
    }

    /**
     * excluirEstado
     * @param estado, Estado a ser excluido.
     */
    public void excluirEstado(Estado estado){
        this.estadoRepository.delete(estado);
    }
    /*
    * obterEstadoPorNome
    * @paran nomeEstado, nome do estado a ser buscado
    * @return cidade identificada pelo nome
    */
    public Estado ObterEstadoPorNome(String nomeEstado){
        return estadoRepository.findByNomeIgnoreCase(nomeEstado);
    }

    /**
     * obterEstado
     * @param id, Long que representa o id do Estado.
     * @return Estado identificado pelo id.
     */
    public Estado obterEstadosPorID(Long id){
        return estadoRepository.findOne(id);
    }

    /**
     * obterestadoPorPais
     * @param paisId, Long que representa o ID do pais ao qual o estado pertence.
     * @return Lista de Estados relacionados ao pais dado.
     */
    public List<Estado> obterEstadosPorPais(Long paisId){
        Pais pais = this.paisRepository.findOne(paisId);
        List<Estado> estados = new ArrayList<Estado>();
        estados.addAll(pais.getEstados());
        return estados;
    }

    /**
     * obterEstadoPornomePais
     * @param paisNome, String que representa o nome do pais.
     * @return Lista de Estado, relacionados ao pais do nome dado.
     */
    public List<Estado> obterEstadosPorNomePais(String paisNome){
//        Pais pais = this.paisRepository.findDistinctByNomeIgnoreCase(paisNome);
//        List<Estado> estados = new ArrayList<>();
//        estados.addAll(pais.getEstados());
        return this.estadoRepository.findByNomePais(paisNome);
    }

    /* PAIS */
    /**
     * salvarPais
     * @param pais, Pais a ser persistido.
     */
    public void salvarPais(Pais pais){
        this.paisRepository.save(pais);
    }

    /**
     * excluirPais
     * @param pais, Pais a ser excluido.
     */
    public void excluirPais(Pais pais){
        this.paisRepository.delete(pais);
    }

    /**
     *obterPaisPorId
     * @param id, Long que representa o id do pais.
     * @return Pais relacionado ao id dado.
     */
    public Pais obterPaisPorID(Long id){
        return this.paisRepository.findOne(id);
    }

    /**
     * obterPais
     * @return Lista de todos os paises cadastrados.
     */
    public List<Pais> obterPais(){
        return this.paisRepository.findAll();
    }

    /**
     * obterPaisPorNome
     * @param paisNome, String que representa o nome do pais
     * @return Pais relacionado ao nome dado.
     */
    public Pais obterPaisPorNome(String paisNome){
            return this.paisRepository.findDistinctByNomeIgnoreCase(paisNome);
    }
}
