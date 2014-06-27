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
import br.ifes.leds.reuse.utility.Factory;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Hospital;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import br.ifes.leds.sincap.controleInterno.cln.cgt.AplHospital;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author aleao
 */
@Service
public class HospitalData {
    
    @Autowired
    EnderecoRepository enderecoRepository;
    @Autowired
    BairroRepository bairroRepository;
    @Autowired
    CidadeRepository cidadeRepository;
    @Autowired
    EstadoRepository estadoRepository;
    @Autowired
    AplHospital aplHospital;
    
    private Hospital hospital;
    private Telefone telefone;
    private Endereco endereco;
    private final Factory fabrica = Factory.INSTANCE;
    
    public void criaHospitalRandom(DataFactory df,Integer qtdHos){
        
        for (int i = 0; i < qtdHos; i++){
            /*Criação dos objetos*/
            hospital =  fabrica.criaObjeto(Hospital.class);
            telefone = fabrica.criaObjeto(Telefone.class);
            endereco = fabrica.criaObjeto(Endereco.class);

            /*Preenche a aba dados Gerais*/
            df.randomize((int) System.currentTimeMillis());
            hospital.setNome("Hospital "+ df.getRandomText(3,15));
            hospital.setFantasia(hospital.getNome());
            hospital.setCnes(df.getNumberText(9));
            hospital.setSigla(df.getRandomChars(3));
            telefone.setNumero(df.getNumberText(8));        
            hospital.setTelefone(telefone);
            hospital.setEmail(df.getEmailAddress());

            /*Preenche a aba Endereço*/        

            endereco.setLogradouro(df.getStreetName());
            endereco.setCep(df.getNumberText(8));
            endereco.setEstado(estadoRepository.findOne(new Long(1)));
            endereco.setCidade(cidadeRepository.findOne(new Long(1)));
            endereco.setBairro(bairroRepository.findOne(new Long(1)));
            endereco.setNumero(df.getNumberText(5));
            endereco.setComplemento(df.getStreetSuffix());
            hospital.setEndereco(endereco);
            
            aplHospital.cadastrar(hospital);
        }
    }
}
