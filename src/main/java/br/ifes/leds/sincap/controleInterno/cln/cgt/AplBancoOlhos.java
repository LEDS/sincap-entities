/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.controleInterno.cln.cgt;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.ifes.leds.reuse.endereco.cgd.EnderecoRepository;
import br.ifes.leds.sincap.controleInterno.cgd.BancoOlhosRepository;
import br.ifes.leds.sincap.controleInterno.cgd.TelefoneRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.BancoOlhos;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ProcessoNotificacaoRepository;

/**
 *
 * @author 20112BSI0083
 */
@Service
public class AplBancoOlhos {
    @Autowired 
    BancoOlhosRepository bancoOlhosRepository;
    @Autowired
    TelefoneRepository telefoneRepository;
    @Autowired
    EnderecoRepository enderecoRepository;
    @Autowired
    ProcessoNotificacaoRepository notificacaoRepository;
        
    /**
     * Método responsável por cadastrar um novo banco de olhos.
     * @param bancoOlhos - Objeto do banco de olhos.
     */
    public void cadastrar(BancoOlhos bancoOlhos) {        
        enderecoRepository.save(bancoOlhos.getEndereco());
        bancoOlhosRepository.save(bancoOlhos);
    }

    /**
     * Método responsável por atualizar um banco de olhos.
     * @param bancoOlhos - Objeto do banco de olhos.
     */
    public void update(BancoOlhos bancoOlhos) {
        bancoOlhosRepository.save(bancoOlhos);
    }

    /**
     * Método responsável por remover um banco de olhos pelo seu id.
     * @param id - Id do banco de olhos.
     */
    public void delete(Long id)  {
        bancoOlhosRepository.delete(id);
    }
    
    /**
     * Método responsável por remover um banco de olhos.
     * @param bancoOlhos - Objeto do banco de olhos.
     */
    public void delete(BancoOlhos bancoOlhos)  {
        bancoOlhosRepository.delete(bancoOlhos);
    }
    
    /**
     * Método responsável por obter uma lista com banco de olhos.
     * @param nome - Nome do banco de olhos.
     * @return retorna uma lista com o(s) banco(s) de olhos.
     */
    public BancoOlhos obter(String nome) {
        return bancoOlhosRepository.findByNome(nome);
    }
    
    /**
     * Método responsável por obter um banco de olhos pelo seu id.
     * @param id - Id do banco de olhos.
     * @return retorna o objeto banco de olhos.
     */
    public BancoOlhos obter(Long id) {
        return bancoOlhosRepository.findOne(id);
    }

    /**
     * Método responsável por obter uma lista com banco de olhos.
     * @param pageable - Nome do banco de olhos.
     * @return retorna uma lista com o(s) banco(s) de olhos.
     */
    public List<BancoOlhos> obter(Pageable pageable) {
        return bancoOlhosRepository.findAll(pageable).getContent();
    }
    
    /**
     * Método responsável por obter uma lista com todos os banco de olhos existentes.
     * @return retorna uma lista com o(s) banco(s) de olhos.
     */
    public List<BancoOlhos> obter() {
        return bancoOlhosRepository.findAll();
    }

    /**
     * Método responsável por obter a quantidade de banco de olhos existentes.
     * @return retorna a quantidade de banco(s) de olhos existentes.
     */    
    public Long quantidade() {
        return bancoOlhosRepository.count();
    }
}
