package br.ifes.leds.sincap.gerenciaNotificacao.cln.util.dataFactory;

import br.ifes.leds.reuse.endereco.cdp.Endereco;
import br.ifes.leds.reuse.endereco.cgd.BairroRepository;
import br.ifes.leds.reuse.endereco.cgd.CidadeRepository;
import br.ifes.leds.reuse.endereco.cgd.EnderecoRepository;
import br.ifes.leds.reuse.endereco.cgd.EstadoRepository;
import br.ifes.leds.sincap.controleInterno.cgd.TelefoneRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Sexo;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ResponsavelRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.EstadoCivil;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Parentesco;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Responsavel;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoDocumentoComFoto;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.DocumentoComFoto;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

import static br.ifes.leds.reuse.utility.Factory.criaObjeto;

/**Classe para a criação de objetos Responsavel randomicos.
 *
 * @author aleao
 * @version 1.0
 */
@Service
public class ResponsavelData {

    @Autowired
    private ResponsavelRepository responsavelRepository;
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

    private Responsavel responsavel;
    private Calendar dataNascimento;
    private Calendar dataInternacao;
    private List<String> listaProfissao;
    private List<Parentesco> listaParentesco;
    private List<EstadoCivil> listaEstadoCivil;
    private List<Sexo> listaSexo;
    private Endereco endereco;
    private Telefone telefone;
    private Listas list = Listas.INSTANCE;

    
    /**Método responsável por criar Objetos Responsavel randomico, sendo nescessário apenas passar
     * uma instancia DataFactory e a quantidade a ser criada.
     * @param df - instancia DataFacotry.
     * @param qtdRes - quantidade de objetos a serem criados. 
     */
    @SuppressWarnings("unused")
    public void criaResponsavelRandom(DataFactory df, Integer qtdRes) {
        for (int i = 0; i < qtdRes; i++) {

            criarResponsavel(df);
            enderecoRepository.save(endereco);

            telefoneRepository.save(telefone);

            responsavelRepository.save(responsavel);
        }
    }
    
    /**Método responsável por criar Objetos Responsavel randomico.
     * @param df - instancia DataFactory.
     * @return responsavel - objeto Responsavel Randomico.
     */
    public Responsavel criarResponsavel(DataFactory df) {
        responsavel = criaObjeto(Responsavel.class);
        dataNascimento = Calendar.getInstance();
        dataInternacao = Calendar.getInstance();
        listaProfissao = list.getListProf();
        listaSexo = list.getListSex();
        listaEstadoCivil = list.getListEst();
        listaParentesco = list.getListPar();
        endereco = criaObjeto(Endereco.class);
        telefone = criaObjeto(Telefone.class);
        Telefone telefone2 = criaObjeto(Telefone.class);

        gerarDadosResponsavel(df);

        gerarDadosEndereco(df);
        // Telefone
        telefone.setNumero("(" + df.getNumberText(2) + ")"
                + df.getNumberText(4) + "-" + df.getNumberText(4));
        telefone2.setNumero("(" + df.getNumberText(2) + ")"
                + df.getNumberText(4) + "-" + df.getNumberText(4));
        responsavel.setTelefone(telefone);
        responsavel.setTelefone2(telefone2);

        return responsavel;
    }

    /**Método responsavel por gerar um endereço randomico.
     * @param df - instancia DataFactory.
    */
    private void gerarDadosEndereco(DataFactory df) {
        // Endereco
        endereco.setLogradouro(df.getStreetName());
        endereco.setEstado(estadoRepository.findOne((long) 1));
        endereco.setCidade(cidadeRepository.findOne((long) 1));
        endereco.setBairro(bairroRepository.findOne((long) 1));
        endereco.setNumero(df.getNumberText(5));
        endereco.setComplemento(df.getStreetSuffix());
        endereco.setCep(df.getNumberText(8));
        responsavel.setEndereco(endereco);
    }

    /**Método responsavel por gerar dados de um Responsavel de forma randomica.
     * @param df - instancia DataFactory.
    */
    private void gerarDadosResponsavel(DataFactory df) {
        // Dados do Responsavel
        DocumentoComFoto documentoComFoto = criaObjeto(DocumentoComFoto.class);
        documentoComFoto.setDocumento(df.getNumberText(9));
        documentoComFoto.setTipoDocumentoComFoto(TipoDocumentoComFoto.RG);

        responsavel.setNome(df.getName());
        responsavel.setNacionalidade("Brasileiro");
        responsavel.setEstadoCivil(df.getItem(listaEstadoCivil));
        responsavel.setSexo(df.getItem(listaSexo));
        dataNascimento.setTime(df.getBirthDate());
        dataInternacao.setTime(df.getDateBetween(df.getDate(2000, 1, 1),
                df.getDate(2014, 1, 1)));
        responsavel.setDocumentoSocial(documentoComFoto);
        responsavel.setProfissao(df.getItem(listaProfissao));
        responsavel.setParentesco(df.getItem(listaParentesco));
    }

}
