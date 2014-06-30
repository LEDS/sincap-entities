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
import br.ifes.leds.sincap.controleInterno.cgd.TelefoneRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Notificador;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import br.ifes.leds.sincap.controleInterno.cln.cgt.AplNotificador;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author aleao
 */
@Service
public class NotificadorData {
    @Autowired
    TelefoneRepository telefoneRepository;
    @Autowired
    EnderecoRepository enderecoRepository;
    @Autowired
    BairroRepository bairroRepository;
    @Autowired
    CidadeRepository cidadeRepository;
    @Autowired
    EstadoRepository estadoRepository;
    @Autowired
    AplNotificador aplNotificador;
    
    private Endereco endereco;
    private Notificador notificador;
    private Telefone telefone;
    private final Factory fabrica = Factory.INSTANCE;
        
    public void criaNotificadorRandom(DataFactory df,Integer qtdNot){
        for (int i = 0; i < qtdNot; i++){
            
            notificador = fabrica.criaObjeto(Notificador.class);
            endereco =  fabrica.criaObjeto(Endereco.class);
            telefone = fabrica.criaObjeto(Telefone.class);
            
            
            //Dados do Notificador                     
            notificador.setSenha("123456");
            notificador.setAtivo(true);
            notificador.setCpf(df.getNumberText(11));
            notificador.setDocumentoSocial(df.getNumberText(9));
            notificador.setEmail(df.getEmailAddress());
            notificador.setNome(df.getName());
            
            //Endereco
            endereco.setLogradouro(df.getStreetName());
            endereco.setEstado(estadoRepository.findOne(new Long(1)));
            endereco.setCidade(cidadeRepository.findOne(new Long(1)));
            endereco.setBairro(bairroRepository.findOne(new Long(1)));
            endereco.setNumero(df.getNumberText(5));
            endereco.setComplemento(df.getStreetSuffix());
            endereco.setCep(df.getNumberText(8));
            notificador.setEndereco(endereco); 
            enderecoRepository.save(endereco);
            
            //Telefone
            telefone.setNumero(df.getNumberText(8)); 
            notificador.setTelefone(telefone); 
            telefoneRepository.save(telefone);
            
            aplNotificador.salvarNotificador(notificador);
        }
    }
    
}
