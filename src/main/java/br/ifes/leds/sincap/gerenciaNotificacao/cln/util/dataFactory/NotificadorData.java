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
import br.ifes.leds.sincap.controleInterno.cgd.InstituicaoNotificadoraRepository;
import br.ifes.leds.sincap.controleInterno.cgd.TelefoneRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Funcionario;
import br.ifes.leds.sincap.controleInterno.cln.cdp.InstituicaoNotificadora;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Notificador;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import br.ifes.leds.sincap.controleInterno.cln.cgt.AplNotificador;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoDocumentoComFoto;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.DocumentoComFoto;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static br.ifes.leds.reuse.utility.Factory.criaObjeto;

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
    private BairroRepository bairroRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private AplNotificador aplNotificador;

    private Endereco endereco;
    private Notificador notificador;
    private Telefone telefone;

    /**Método responsável por criar Objetos Notificador randomico, sendo nescessário apenas passar
     * uma instancia DataFactory e a quantidade a ser criada.
     * @param df - instancia DataFacotry.
     * @param qtdNot - quantidade de objetos a serem criados. 
     */
    @SuppressWarnings("unused")
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
        notificador = criaObjeto(Notificador.class);
        endereco =  criaObjeto(Endereco.class);
        telefone = criaObjeto(Telefone.class);
        DocumentoComFoto documentoComFoto = criaObjeto(DocumentoComFoto.class);

        // Dados do Notificador
        documentoComFoto.setDocumento(df.getNumberText(9));
        documentoComFoto.setTipoDocumentoComFoto(TipoDocumentoComFoto.RG);

        notificador.setSenha("123456");
        notificador.setAtivo(true);
        notificador.setCpf(df.getNumberText(11));
        notificador.setDocumentoSocial(documentoComFoto);
        notificador.setNome(df.getName());
        notificador.setEmail(notificador.getNome().replaceAll(" ","").toLowerCase()+"@gmail.com");
        notificador.setTelefone(criarTelefone(df));
        notificador.setEndereco(criarEndereco(df));
        
        return notificador;
    }

    /** Método responsável por salvar um objeto Notificador no banco de dados.
     * @param not - Objeto Notificador. 
     * @param df - instancia DataFactory.
     */
    public void salvarNotificador(DataFactory df, Notificador not) {
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

    /** Método responsável por criar um objeto Endereco randomico.
     * @param df - instancia DataFactory.
     * @return endereco - Objeto Endereco.
     */
    public Endereco criarEndereco(DataFactory df) {
        endereco.setLogradouro(df.getStreetName());
        endereco.setEstado(estadoRepository.findOne((long) 1));
        endereco.setCidade(cidadeRepository.findOne((long) 1));
        endereco.setBairro(bairroRepository.findOne((long) 1));
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
        InstituicaoNotificadora notificadorInstituicao;
        List<InstituicaoNotificadora> listInstituicao;
        Set<InstituicaoNotificadora> setInstituicao;
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
        Set<Funcionario> setFuncionario;
        for(InstituicaoNotificadora i:setInstituicaoFuncionario){
            setFuncionario = i.getFuncionarios();
            setFuncionario.add(not);
            i.setFuncionarios(setFuncionario);
            instituicaoNotificadoraRepository.save(i);
        }
    }
    
}
