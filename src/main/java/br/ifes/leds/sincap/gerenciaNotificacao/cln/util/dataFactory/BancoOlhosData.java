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
import br.ifes.leds.sincap.controleInterno.cgd.BancoOlhosRepository;
import br.ifes.leds.sincap.controleInterno.cgd.TelefoneRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.BancoOlhos;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.ifes.leds.reuse.utility.Factory.criaObjeto;

/**Classe para a criação de objetos BancoOlhos randomicos.
 *
 * @author aleao
 * @version 1.0
 */
@Service
public class BancoOlhosData {
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private BairroRepository bairroRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private BancoOlhosRepository bancoOlhosRepository;
    @Autowired
    private TelefoneRepository telefoneRepository;

    /**Método responsável por criar Objetos BancoOlhos randomico, sendo nescessário apenas passar
     * uma instancia DataFactory e a quantidade a ser criada.
     * @param df - instancia DataFacotry.
     * @param qtdBan - quantidade de objetos a serem criados. 
     */
    @SuppressWarnings("unused")
    public void criaBancoOlhosRandom(DataFactory df, Integer qtdBan){
        for (int i = 0; i < qtdBan; i++){
            salvarBancoOlhos(criaBancoOlhos(df));
        }
    }

    /** Método responsável por salvar um objeto BancoOlhos no banco de dados.
     * @param bo - Objeto BancoOlhos. 
     */
    public void salvarBancoOlhos(BancoOlhos bo) {
        bancoOlhosRepository.save(bo);
    }

    /**Método responsável por criar Objetos BancoOlhos randomico.
     * @param df - instancia DataFactory.
     * @return bancoOlhos - objeto BancoOlhos Randomico.
     */
    public BancoOlhos criaBancoOlhos(DataFactory df) {
        //Objetos;
        BancoOlhos bancoOlhos = criaObjeto(BancoOlhos.class);
        Endereco endereco = criaObjeto(Endereco.class);
        Telefone telefone = criaObjeto(Telefone.class);
        
        //Preenchendo o objeto InstituicaoNotificadora.
        bancoOlhos.setNome("Banco de Olhos " + df.getName());
        bancoOlhos.setFantasia(bancoOlhos.getNome());
        bancoOlhos.setCnes(df.getNumberText(9));
        bancoOlhos.setEmail(df.getEmailAddress());
        //Endereco
        endereco.setLogradouro(df.getStreetName());
        endereco.setCidade(cidadeRepository.findOne(1L));
        endereco.setBairro(bairroRepository.findOne(1L));
        endereco.setEstado(estadoRepository.findOne(1L));
        endereco.setNumero(df.getNumberText(5));
        endereco.setComplemento(df.getStreetSuffix());
        endereco.setCep(df.getNumberText(8));
        bancoOlhos.setEndereco(endereco);
        
        //Telefone
        telefone.setNumero("(" + df.getNumberText(2) + ")"
                + df.getNumberText(4) + "-" + df.getNumberText(4));
        bancoOlhos.setTelefone(telefone);
        
        return bancoOlhos;
    }
}
