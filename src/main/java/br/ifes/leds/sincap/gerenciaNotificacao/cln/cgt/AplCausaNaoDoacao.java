/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt;

import br.ifes.leds.sincap.gerenciaNotificacao.cgd.CausaNaoDoacaoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CausaNaoDoacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoNaoDoacao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**Classe responsável pelos testes dos métodos de uma CausaNaoDoacao.
 *
 * @author aleao
 */
@Service
public class AplCausaNaoDoacao {
    @Autowired
    private CausaNaoDoacaoRepository causaNaoDoacaoRepository;
    
    /***
     * Metodo responsavel por cadastrar um objeto CausaNaoDoacao.
     * @param causaNaoDoacao 
     */
    public void cadastrar(CausaNaoDoacao causaNaoDoacao){
        causaNaoDoacaoRepository.save(causaNaoDoacao);
    }
            
    /***
     * Metodo responsavel por atualizar um objeto CausaNaoDoacao.
     * @param causaNaoDoacao 
     */
    public void atualizar(CausaNaoDoacao causaNaoDoacao){
        causaNaoDoacaoRepository.save(causaNaoDoacao);
    }
    
    /***
     * Metodo responsavel por obter uma lista de CausaNaoDoacao de acordo com o tipo.
     * @param tipo - objeto TipoNaoDoacao.
     * @return lista de CausaNaoDoacao.
     */
    public List<CausaNaoDoacao> obter(TipoNaoDoacao tipo){
        return causaNaoDoacaoRepository.findByTipoNaoDoacao(tipo);
    }
    
    /***
     * Metodo responsavel por obter uma lista de todas as causas.
     * @return lista de CausaNaoDoacao.
     */
    public List<CausaNaoDoacao> obter(){
        return causaNaoDoacaoRepository.findAll();
    }
    
    /***
     * Metodo responsavel por obter uma causa atraves do seu id.
     * @param id - Id da CausaNaoDoacao.
     * @return objeto CausaNaoDoacao.
     */
    public CausaNaoDoacao obter(Long id){
        return causaNaoDoacaoRepository.findOne(id);
    }
    
    /***
     * Metodo que retorna a quantidade de causas cadastradas.
     * @return quantidade.
     */
    public Long quantidade(){
        return causaNaoDoacaoRepository.count();
    }
    
    /***
     * Metodo responsavel por exlcuir uma causa.
     * @param causaNaoDoacao 
     */
    public void excluir(CausaNaoDoacao causaNaoDoacao){
        causaNaoDoacaoRepository.delete(causaNaoDoacao);
    }
    
    
}
