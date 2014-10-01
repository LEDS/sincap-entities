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
import br.ifes.leds.sincap.controleInterno.cln.cdp.AnalistaCNCDO;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.AnalistaCNCDORepository;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.ifes.leds.reuse.utility.Factory.criaObjeto;

/**Classe para a criação de objetos AnalistaCNCDO randomicos.
 * @author aleao
 * @version 1.0
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


    /**Método responsável por criar Objetos AnalistaCNDO randomico, sendo nescessário apenas passar
     * uma instancia DataFactory e a quantidade a ser criada.
     * @param df - instancia DataFacotry.
     * @param qtdAna - quantidade de objetos a serem criados. 
     */
    @SuppressWarnings("unused")
    public void criaAnalistaRandom(DataFactory df, Integer qtdAna){
        for (int i = 0; i < qtdAna; i++){
            salvarAnalistaCNDO(criaAnalistaCNDO(df));
            
        }
    }
    
    /**Método responsável por criar Objetos AnalistaCNCDO randomico.
     * @param df - instancia DataFactory.
     * @return analista - objeto AnalistaCNCDO Randomico.
     */
    public AnalistaCNCDO criaAnalistaCNDO(DataFactory df) {
        //Objetos;
        AnalistaCNCDO analista = criaObjeto(AnalistaCNCDO.class);
        Endereco endereco = criaObjeto(Endereco.class);
        Telefone telefone = criaObjeto(Telefone.class);
        
        //Preenchendo o objeto AnalistaCNCDO.
        analista.setAtivo(true);
        analista.setNome(df.getName());
        analista.setCpf(df.getNumberText(11));
        analista.setDocumentoSocial(df.getNumberText(9));
        analista.setEmail(df.getEmailAddress());
        analista.setSenha("123456");
        
        //Endereco
        endereco.setLogradouro(df.getStreetName());
        endereco.setCidade(cidadeRepository.findOne(1L));
        endereco.setBairro(bairroRepository.findOne(1L));
        endereco.setEstado(estadoRepository.findOne(1L));
        endereco.setNumero(df.getNumberText(5));
        endereco.setComplemento(df.getStreetSuffix());
        endereco.setCep(df.getNumberText(8));
        analista.setEndereco(endereco);
        
        //Telefone
        telefone.setNumero("(" + df.getNumberText(2) + ")"
                + df.getNumberText(4) + "-" + df.getNumberText(4));
        analista.setTelefone(telefone);

        
        return analista;
    }

     /** Método responsável por salvar um objeto AnalistaCNCDO no banco de dados.
     * @param ana - Objeto AnalistaCNCDO. 
     */
    public void salvarAnalistaCNDO(AnalistaCNCDO ana) {
        analistaCNCDORepository.save(ana);
    }
}
