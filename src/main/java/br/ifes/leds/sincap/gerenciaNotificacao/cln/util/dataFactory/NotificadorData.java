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
import br.ifes.leds.sincap.controleInterno.cgd.InstituicaoNotificadoraRepository;
import br.ifes.leds.sincap.controleInterno.cgd.TelefoneRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Funcionario;
import br.ifes.leds.sincap.controleInterno.cln.cdp.InstituicaoNotificadora;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Notificador;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import br.ifes.leds.sincap.controleInterno.cln.cgt.AplNotificador;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**Classe para a criação de objetos Notificador randomicos.
 *
 * @author aleao
 * @version 1.0
 */
@Service
public class NotificadorData {
    @Autowired
    private InstituicaoNotificadoraRepository instituicaoNotificadoraRepository;
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
    private AplNotificador aplNotificador;
    @Autowired
    private Factory fabrica;
    
    private Endereco endereco;
    private Notificador notificador;
    private Telefone telefone;
    private InstituicaoNotificadora instituicaoNotificadora;
    private List<InstituicaoNotificadora> listInstituicao;
    private Set<InstituicaoNotificadora> setInstituicao;
    private InstituicaoNotificadora notificadorInstituicao;
    
    /**Método responsável por criar Objetos Notificador randomico, sendo nescessário apenas passar
     * uma instancia DataFactory e a quantidade a ser criada.
     * @param df - instancia DataFacotry.
     * @param qtdNot - quantidade de objetos a serem criados. 
     */
    public void criaNotificadorRandom(DataFactory df,Integer qtdNot){
        for (int i = 0; i < qtdNot;i++){
            Notificador not = criaNotificador(df);
            not = associaInstituicaoNotificadora(df,not);
            salvarNotificador(df,not);
            associaInstiuicaoFuncionario(not);
        } 
    }

    /**Método responsável por criar Objetos Notificador randomico.
     * @param df - instancia DataFactory.
     * @return notificador - objeto Notificador Randomico.
     */
    public Notificador criaNotificador(DataFactory df) {
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
        
        return notificador;
    }

    /** Método responsável por salvar um objeto Notificador no banco de dados.
     * @param not - Objeto Notificador. 
     * @param df - instancia DataFactory.
     */
    public void salvarNotificador(DataFactory df, Notificador not) {
        salvarTelefone(criarTelefone(df));
        salvarEndereco(criaEndereco(df));
        aplNotificador.salvarNotificador(not);
    }
    
    /** Método responsável por criar um objeto Telefone randomico.
     * @param df - instancia DataFactory.
     * @return telefone - Objeto Telefone.
     */
    public Telefone criarTelefone(DataFactory df) {
        telefone.setNumero("(" + df.getNumberText(2) + ")"
                + df.getNumberText(4) + "-" + df.getNumberText(4));
        
        return telefone;
    }

    /** Método responsável por salvar um objeto Telefone no banco de dados.
     * @param tel - Objeto Telefone. 
     */
    public void salvarTelefone(Telefone tel) {
        notificador.setTelefone(tel);
        telefoneRepository.save(tel);
    }

    /** Método responsável por salvar um objeto Endereco no banco de dados.
     * @param end - Objeto Endereco. 
     */
    public void salvarEndereco(Endereco end) {
        notificador.setEndereco(end);
        enderecoRepository.save(end);
    }

    /** Método responsável por criar um objeto Endereco randomico.
     * @param df - instancia DataFactory.
     * @return endereco - Objeto Endereco.
     */
    public Endereco criaEndereco(DataFactory df) {
        endereco.setLogradouro(df.getStreetName());
        endereco.setEstado(estadoRepository.findOne(new Long(1)));
        endereco.setCidade(cidadeRepository.findOne(new Long(1)));
        endereco.setBairro(bairroRepository.findOne(new Long(1)));
        endereco.setNumero(df.getNumberText(5));
        endereco.setComplemento(df.getStreetSuffix());
        endereco.setCep(df.getNumberText(8));

        return endereco;
    }
    
    /**Método responsável por preencher o relacionamento da tabela notificador_instituicaoNotificadora.
     * @param df - objeto DataFactory.
     * @param not - Objeto Notificador.
     * @return not - Objeto Notificador.
     */
    public Notificador associaInstituicaoNotificadora(DataFactory df,Notificador not) {
        notificadorInstituicao = fabrica.criaObjeto(InstituicaoNotificadora.class);
        instituicaoNotificadora = fabrica.criaObjeto(InstituicaoNotificadora.class);
        listInstituicao = new ArrayList<>();
        setInstituicao = new HashSet<>();
        listInstituicao = instituicaoNotificadoraRepository.findAll();
        notificadorInstituicao = df.getItem(listInstituicao);
        setInstituicao = not.getInstituicoesNotificadoras();
        setInstituicao.add(notificadorInstituicao);
        not.setInstituicoesNotificadoras(setInstituicao);
        
        return not;
    }
    
    /**Método responsável por preencher o relacionamento da tabela instituicaoNotificadora_funcionario.
     * @param not - Objeto Notificador.
     */
    public void associaInstiuicaoFuncionario(Notificador not){
        Set<InstituicaoNotificadora> setInstituicaoFuncionario = not.getInstituicoesNotificadoras();
        Set<Funcionario> setFuncionario = new HashSet<>();
        for(InstituicaoNotificadora i:setInstituicaoFuncionario){
            setFuncionario = i.getFuncionarios();
            setFuncionario.add(not);
            i.setFuncionarios(setFuncionario);
            instituicaoNotificadoraRepository.save(i);
        }
    }
    
}
