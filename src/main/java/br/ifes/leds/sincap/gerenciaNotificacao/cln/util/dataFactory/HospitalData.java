/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.gerenciaNotificacao.cln.util.dataFactory;

import br.ifes.leds.reuse.endereco.cdp.Endereco;
import br.ifes.leds.reuse.endereco.cgd.BairroRepository;
import br.ifes.leds.reuse.endereco.cgd.CidadeRepository;
import br.ifes.leds.reuse.endereco.cgd.EnderecoRepository;
import br.ifes.leds.reuse.endereco.cgd.EstadoRepository;
import br.ifes.leds.sincap.controleInterno.cgd.TelefoneRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Hospital;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import br.ifes.leds.sincap.controleInterno.cln.cgt.AplHospital;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.ifes.leds.reuse.utility.Factory.criaObjeto;

/**Classe para a criação de objetos Hospital randomicos.
 *
 * @author aleao
 * @version 1.0
 */
@Service
public class HospitalData {
    
    @Autowired
    EnderecoRepository enderecoRepository;
    @Autowired
    TelefoneRepository telefoneRepository;
    @Autowired
    BairroRepository bairroRepository;
    @Autowired
    CidadeRepository cidadeRepository;
    @Autowired
    EstadoRepository estadoRepository;
    @Autowired
    AplHospital aplHospital;

    /**Método responsável por criar Objetos Hospital randomico, sendo nescessário apenas passar
     * uma instancia DataFactory e a quantidade a ser criada.
     * @param df - instancia DataFacotry.
     * @param qtdHos - quantidade de objetos a serem criados. 
     */
    @SuppressWarnings("unused")
    public void criaHospitalRandom(DataFactory df,Integer qtdHos){
        
        for (int i = 0; i < qtdHos; i++){
            salvarHospital(criaHospital(df));
        }
    }

     /** Método responsável por salvar um objeto Hospital no banco de dados.
     * @param hosp - Objeto Hospital. 
     */
    public void salvarHospital(Hospital hosp) {
        enderecoRepository.save(hosp.getEndereco());
        telefoneRepository.save(hosp.getTelefone());
        aplHospital.cadastrar(hosp);
    }

     /**Método responsável por criar Objetos Hospital randomico.
     * @param df - instancia DataFactory.
     * @return hospital - objeto Hospital Randomico.
     */
    public Hospital criaHospital(DataFactory df) {
        /*Criação dos objetos*/
        Hospital hospital = criaObjeto(Hospital.class);
        Telefone telefone = criaObjeto(Telefone.class);
        Endereco endereco = criaObjeto(Endereco.class);
        
        /*Preenche a aba dados Gerais*/
        hospital.setNome("Hospital " + df.getName());
        hospital.setFantasia(hospital.getNome());
        hospital.setCnes(df.getNumberText(9));
        hospital.setSigla(df.getRandomChars(3));
        hospital.setEmail(df.getEmailAddress());
        
        /*Preenche a aba Endereço*/
        
        endereco.setLogradouro(df.getStreetName());
        endereco.setCep(df.getNumberText(8));
        endereco.setEstado(estadoRepository.findOne((long) 1));
        endereco.setCidade(cidadeRepository.findOne((long) 1));
        endereco.setBairro(bairroRepository.findOne((long) 1));
        endereco.setNumero(df.getNumberText(5));
        endereco.setComplemento(df.getStreetSuffix());
        hospital.setEndereco(endereco);
        
        /*Telefone*/
        telefone.setNumero("(" + df.getNumberText(2) + ")"
                + df.getNumberText(4) + "-" + df.getNumberText(4));
        hospital.setTelefone(telefone);
        
        return hospital;
    }
}
