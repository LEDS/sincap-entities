/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.gerenciaNotificacao.cln.util.dataFactory;

import br.ifes.leds.sincap.controleInterno.cgd.SetorRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Hospital;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Setor;
import br.ifes.leds.sincap.controleInterno.cln.cgt.AplHospital;
import br.ifes.leds.sincap.controleInterno.cln.cgt.AplSetor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static br.ifes.leds.reuse.utility.Factory.criaObjeto;

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


    /**Método responsável por criar setores randomicos.
     *
     */
    @SuppressWarnings("unused")
    public void criaSetorRandom() {
        List<String> listaSetor = criaListaSetores();
        for (String listaSetor1 : listaSetor) {
            Setor setor = criaObjeto(Setor.class);
            setor.setNome(listaSetor1);
            setorRepository.save(setor);
        }
    }

    /**
     * Método responsável por associar os setores criados ao hospitais existentes.
     */
    @SuppressWarnings("unused")
    public void associaSetorHospital(){
        List<Hospital> listaHospital = aplHospital.obter();
        List<Setor> listaObjetoSetor = aplSetor.obter();
        for (Hospital h: listaHospital){
            for (Setor s : listaObjetoSetor) {
                aplHospital.addSetor(s,h.getId());
            }
        }
    }
    /**
     * Método responsavel por criar uma lista de Setores.
     *
     * @return ls - Lista de setores.
     */
    private static List<String> criaListaSetores(){
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
