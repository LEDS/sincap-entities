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
import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.TestemunhaRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Testemunha;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**Classe para a criação de objetos Testemunha randomicos.
 *
 * @author aleao
 * @version 1.0
 */
@Service
public class TestemunhaData {
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
    private Factory fabrica;
    @Autowired
    private TestemunhaRepository testemunhaRepository;
    private Testemunha testemunha;
    private Endereco endereco;
    private Telefone telefone;
    
    /**Método responsável por criar Objetos Testemunha randomico, sendo nescessário apenas passar
     * uma instancia DataFactory e a quantidade a ser criada.
     * @param df - instancia DataFacotry.
     * @param qtdTes - quantidade de objetos a serem criados. 
     */
    public void criaTestemunhaRandom(DataFactory df,Integer qtdTes){
        for (int i = 0; i < qtdTes; i++){
            salvarTestemunha(criarTestemunha(df));
        }
    }

    /** Método responsável por salvar um objeto Testemunha no banco de dados.
     * @param tes - Objeto Testemunha. 
     */
    public void salvarTestemunha(Testemunha tes) {
        enderecoRepository.save(endereco);
        telefoneRepository.save(telefone);
        testemunhaRepository.save(tes);
    }

    /**Método responsável por criar Objetos Testemunha randomico.
     * @param df - instancia DataFactory.
     * @return testemunha - objeto Testemunha Randomico.
     */
    public Testemunha criarTestemunha(DataFactory df) {
        testemunha = fabrica.criaObjeto(Testemunha.class);
        endereco = fabrica.criaObjeto(Endereco.class);
        telefone = fabrica.criaObjeto(Telefone.class);
        
        testemunha.setNome(df.getName());
        testemunha.setDocumentoSocial(df.getNumberText(11));
        
        //Endereco
        endereco.setLogradouro(df.getStreetName());
        endereco.setEstado(estadoRepository.findOne(new Long(1)));
        endereco.setCidade(cidadeRepository.findOne(new Long(1)));
        endereco.setBairro(bairroRepository.findOne(new Long(1)));
        endereco.setNumero(df.getNumberText(5));
        endereco.setComplemento(df.getStreetSuffix());
        endereco.setCep(df.getNumberText(8));
        testemunha.setEndereco(endereco);
        
        // Telefone
        telefone.setNumero("(" + df.getNumberText(2) + ")"
                + df.getNumberText(4) + "-" + df.getNumberText(4));
        testemunha.setTelefone(telefone);
        
        return testemunha;
    }
}
