/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt;

import br.ifes.leds.sincap.gerenciaNotificacao.cgd.CausaNaoDoacaoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ComentarioRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CausaNaoDoacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Comentario;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoNaoDoacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**Classe responsável pelos testes dos métodos de um Comentario.
 *
 * @author aleao
 */
@Service
public class AplComentario {
    @Autowired
    private ComentarioRepository comentarioRepository;
    
    /***
     * Metodo responsavel por cadastrar um objeto Comentario.
     * @param comentario
     */
    public void cadastrar(Comentario comentario){
        comentarioRepository.save(comentario);
    }
            
    /***
     * Metodo responsavel por atualizar um objeto Comentario.
     * @param comentario
     */
    public void atualizar(Comentario comentario){
        comentarioRepository.save(comentario);
    }
    

    /***
     * Metodo responsavel por obter uma lista de todas os comentarios.
     * @return lista de Comentario.
     */
    public List<Comentario> obter(){
        return comentarioRepository.findAll();
    }
    
    /***
     * Metodo responsavel por obter um comentario atraves do seu id.
     * @param id - Id do comentario.
     * @return objeto Comentario.
     */
    public Comentario obter(Long id){
        return comentarioRepository.findOne(id);
    }


    /***
     * Metodo que retorna a quantidade de comentarios.
     * @return quantidade.
     */
    public Long quantidade(){
        return comentarioRepository.count();
    }
    
    /***
     * Metodo responsavel por exlcuir um comentario.
     * @param comentario
     */
    public void excluir(Comentario comentario){
        comentarioRepository.delete(comentario);
    }
    
    
}
