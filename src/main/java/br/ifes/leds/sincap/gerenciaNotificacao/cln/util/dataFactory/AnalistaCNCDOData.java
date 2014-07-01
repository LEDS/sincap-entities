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
import br.ifes.leds.sincap.controleInterno.cln.cdp.AnalistaCNCDO;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.AnalistaCNCDORepository;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author aleao
 */
@Service
public class AnalistaCNCDOData {
    @Autowired
    private TelefoneRepository telefoneRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private BairroRepository bairroRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private AnalistaCNCDORepository analistaCNCDORepository;
    @Autowired
    private  Factory fabrica;
    
    private Telefone telefone;
    private Endereco endereco;
    private AnalistaCNCDO analista;
    
    
    
    public void criaAnalistaRandom(DataFactory df, Integer qtdAna){
        for (int i = 0; i < qtdAna; i++){
            //Objetos;
            analista = fabrica.criaObjeto(AnalistaCNCDO.class);
            endereco = fabrica.criaObjeto(Endereco.class);
            telefone = fabrica.criaObjeto(Telefone.class);
            
            //Preenchendo o objeto AnalistaCNCDO.
            analista.setAtivo(true);
            analista.setNome(df.getName());
            analista.setCpf(df.getNumberText(11));
            analista.setDocumentoSocial(df.getNumberText(9));
            analista.setEmail(df.getEmailAddress());
            analista.setSenha("123456");
            
            //Endereco
            endereco.setLogradouro(df.getStreetName());
            endereco.setCidade(cidadeRepository.findOne(new Long(1)));
            endereco.setBairro(bairroRepository.findOne(new Long(1)));
            endereco.setEstado(estadoRepository.findOne(new Long(1)));
            endereco.setNumero(df.getNumberText(5));
            endereco.setComplemento(df.getStreetSuffix());
            endereco.setCep(df.getNumberText(8));
            analista.setEndereco(endereco); 
            enderecoRepository.save(endereco);
            //Telefone
            telefone.setNumero(df.getNumberText(8)); 
            analista.setTelefone(telefone); 
            telefoneRepository.save(telefone);
            //Salva o objeto Instituição.
            analistaCNCDORepository.save(analista);
        }
    }
}
