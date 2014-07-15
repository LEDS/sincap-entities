/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.gerenciaNotificacao.cln.util.dataFactory;

import br.ifes.leds.reuse.utility.Factory;
import br.ifes.leds.sincap.controleInterno.cgd.SetorRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Hospital;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Setor;
import br.ifes.leds.sincap.controleInterno.cln.cgt.AplHospital;
import br.ifes.leds.sincap.controleInterno.cln.cgt.AplSetor;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**Classe para a criação de objetos Setor randomicos.
 *
 * @author aleao
 * @version 1.0
 */
@Service
public class SetorData {
    @Autowired
    private AplSetor aplSetor;
    @Autowired
    private AplHospital aplHospital;
    @Autowired
    private SetorRepository setorRepository;
    @Autowired
    private Factory fabrica;
    
    private Setor setor;
    private List<String> listaSetor;
    private List<Hospital> listaHospital;
    private List<Setor> listaObjetoSetor;
    
    
    /**Método responsável por criar setores randomicos.
     *
     */
    public void criaSetorRandom() {
        listaSetor = criaListaSetores(); 
        for (String listaSetor1 : listaSetor) {
            setor = fabrica.criaObjeto(Setor.class);
            setor.setNome(listaSetor1);
            setorRepository.save(setor);
        }
    }
    /**Método responsável por associar os setores criados ao hospitais existentes.
     * 
    */
    public void associaSetorHospital(){
        listaHospital = aplHospital.obter();
        listaObjetoSetor = aplSetor.obter();
        for (Hospital h: listaHospital){
            for (Setor s : listaObjetoSetor) {
                aplHospital.addSetor(s,h.getId());
            }
        }
    }
    /**Método responsavel por criar uma lista de Setores.
     *@return ls - Lista de setores.
     */
    public static List<String> criaListaSetores(){
        List<String> ls = new ArrayList<>();
        
        ls.add("CLINICA MEDICA");
        ls.add("CIRURGIA GERAL");
        ls.add("CTI");
        ls.add("PEDIATRIA");
        ls.add("NEFROLOGIA");
        ls.add("GINECOLOGIA");
        ls.add("OBSTETRICIA");
                
        return ls;
    }
    
}
