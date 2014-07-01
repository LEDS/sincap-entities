package br.ifes.leds.sincap.test;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.GregorianCalendar;

import junit.framework.Assert;

import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ifes.leds.reuse.endereco.cdp.Bairro;
import br.ifes.leds.reuse.endereco.cdp.Cidade;
import br.ifes.leds.reuse.endereco.cdp.Estado;
import br.ifes.leds.reuse.endereco.cdp.Pais;
import br.ifes.leds.reuse.endereco.cdp.dto.EnderecoDTO;
import br.ifes.leds.reuse.endereco.cgt.AplEndereco;
import br.ifes.leds.reuse.ledsExceptions.CRUDExceptions.ViolacaoDeRIException;
import br.ifes.leds.reuse.utility.Factory;
import br.ifes.leds.reuse.utility.Utility;
import br.ifes.leds.sincap.controleInterno.cgd.HospitalRepository;
import br.ifes.leds.sincap.controleInterno.cgd.SetorRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Hospital;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Setor;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Sexo;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CausaMortis;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CorpoEncaminhamento;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.EstadoCivil;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.DTO.ObitoDTO;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.DTO.PacienteDTO;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt.AplObito;

public class AplObitoTest extends AbstractionTest {

    @Autowired
    private AplObito aplObito;
    @Autowired
    private AplEndereco aplEndereco;
    @Autowired
    private HospitalRepository hospitalRepository;
    @Autowired
    private SetorRepository setorRepository;
    @Autowired
    private Mapper mapper;
    @Autowired
    private Utility utility;
    @Autowired
    private Factory fabrica;

    private final Method getNomePacienteDTO;
    private final Method getSiglaEstado;
    private final Method getNomeCidade;
    private final Method getNomeBairro;

    private ObitoDTO obitoDTO;
    private PacienteDTO pacienteDTO;
    private String nome;
    private Telefone telefone;
    private EnderecoDTO endereco;
    private Calendar dataInternacao;
    private Calendar dataNascimento;
    private String profissao;
    private String nomeMae;
    private String numeroProntuario;
    private String numeroSUS;
    private String nacionalidade;
    private String documentoSocial;
    private Sexo sexo;
    private EstadoCivil estadoCivil;

    public AplObitoTest() throws NoSuchMethodException, SecurityException {
        getNomePacienteDTO = PacienteDTO.class.getDeclaredMethod("getNome");
        getSiglaEstado = Estado.class.getDeclaredMethod("getSigla");
        getNomeCidade = Cidade.class.getDeclaredMethod("getNome");
        getNomeBairro = Bairro.class.getDeclaredMethod("getNome");
    }

    @Before
    public void before() throws Exception {
        this.obitoDTO = fabrica.criaObjeto(ObitoDTO.class);
        this.pacienteDTO = fabrica.criaObjeto(PacienteDTO.class);
        this.endereco = fabrica.criaObjeto(EnderecoDTO.class);
        this.telefone = fabrica.criaObjeto(Telefone.class);
        this.dataInternacao = new GregorianCalendar();
        this.dataNascimento = new GregorianCalendar();

        gerarDadosPaciente();
        preencherEndereco(endereco);
        preencherDadosPaciente();
        preencherDadosObito();
    }

    @Test
    public void obterPacienteTest() throws ViolacaoDeRIException {
        aplObito.salvarPaciente(pacienteDTO);

        PacienteDTO pacienteTmp = utility.getObjectByMethod(aplObito.obterTodosPacientes(), getNomePacienteDTO, this.nome);
        Long idTest = pacienteTmp.getId();

        Assert.assertNotNull(aplObito.obterPaciente(idTest));
        Assert.assertEquals(idTest, aplObito.obterPaciente(idTest).getId());
    }

    @Test
    public void savarPacienteTest() throws ViolacaoDeRIException {
        aplObito.salvarPaciente(pacienteDTO);

        PacienteDTO pacienteTest = utility.getObjectByMethod(aplObito.obterTodosPacientes(), getNomePacienteDTO, this.nome);

        Assert.assertNotNull(pacienteTest.getId());
        Assert.assertNotNull(pacienteTest.getEndereco());
        Assert.assertNotNull(pacienteTest.getTelefone());

        Assert.assertEquals(this.nome, pacienteTest.getNome());
        Assert.assertEquals(this.documentoSocial, pacienteTest.getDocumentoSocial());
        Assert.assertEquals(this.nacionalidade, pacienteTest.getNacionalidade());
        Assert.assertEquals(this.nomeMae, pacienteTest.getNomeMae());
        Assert.assertEquals(this.numeroProntuario, pacienteTest.getNumeroProntuario());
        Assert.assertEquals(this.numeroSUS, pacienteTest.getNumeroSUS());
        Assert.assertEquals(this.profissao, pacienteTest.getProfissao());
        Assert.assertEquals(this.endereco.getBairro(), pacienteTest.getEndereco().getBairro());
    }

    @Test
    public void salvarObitoTest() throws ViolacaoDeRIException {
        aplObito.salvarObito(obitoDTO);

        ObitoDTO obitoTest = aplObito.obterTodosObitos().get(0);

        Assert.assertNotNull(obitoTest);
        Assert.assertNotNull(obitoTest.getSetor());
        Assert.assertNotNull(obitoTest.getPaciente().getId());
        Assert.assertEquals(endereco.getCep(), obitoTest.getPaciente().getEndereco().getCep());
        Assert.assertEquals("Primeira Causa Mortis", obitoTest.getPrimeiraCausaMortis().getNome());
        Assert.assertEquals("Segunda Causa Mortis", obitoTest.getSegundaCausaMortis().getNome());
        Assert.assertEquals("Terceira Causa Mortis", obitoTest.getTerceiraCausaMortis().getNome());
        Assert.assertEquals("Quarta Causa Mortis", obitoTest.getQuartaCausaMortis().getNome());
    }

    private void gerarDadosPaciente() {
        this.nome = "José do Teste";
        this.telefone.setNumero("(27)3322-5640");

        // 3 Março de 1974
        this.dataNascimento.set(Calendar.YEAR, 1974);
        this.dataNascimento.set(Calendar.MONTH, 2);
        this.dataNascimento.set(Calendar.DAY_OF_MONTH, 3);

        // 14 de Janeiro de 2014
        this.dataInternacao.set(Calendar.YEAR, 2014);
        this.dataInternacao.set(Calendar.MONTH, 0);
        this.dataInternacao.set(Calendar.DAY_OF_MONTH, 14);

        this.profissao = "Pintor";
        this.nomeMae = "Josefina da Silva";
        this.numeroProntuario = "1288641";
        this.numeroSUS = "7898557";
        this.nacionalidade = "Brasileira";
        this.documentoSocial = "354863";
        this.documentoSocial = "354863";
        this.sexo = Sexo.MASCULINO;
        this.estadoCivil = EstadoCivil.SOLTEIRO;
    }

    private void preencherDadosPaciente() {
        pacienteDTO.setNome(nome);
        pacienteDTO.setTelefone(telefone);
        pacienteDTO.setEndereco(endereco);
        pacienteDTO.setDataInternacao(dataInternacao);
        pacienteDTO.setDataNascimento(dataNascimento);
        pacienteDTO.setProfissao(profissao);
        pacienteDTO.setNomeMae(nomeMae);
        pacienteDTO.setNumeroProntuario(numeroProntuario);
        pacienteDTO.setNumeroSUS(numeroSUS);
        pacienteDTO.setNacionalidade(nacionalidade);
        pacienteDTO.setDocumentoSocial(documentoSocial);
        pacienteDTO.setSexo(sexo);
        pacienteDTO.setEstadoCivil(estadoCivil);
    }

    private void preencherDadosObito() {
        Hospital hospital = hospitalRepository.findAll().get(0);
        Setor setor = setorRepository.findAll().get(0);

        this.obitoDTO.setPaciente(pacienteDTO);
        this.obitoDTO.setAptoDoacao(true);
        this.obitoDTO.setCorpoEncaminhamento(CorpoEncaminhamento.IML);
        this.obitoDTO.setDataEvento(new GregorianCalendar(2014, 5, 27, 18, 30));
        this.obitoDTO.setDataObito(new GregorianCalendar(2014, 5, 27, 22, 55));
        this.obitoDTO.setHospital(hospital.getId());
        this.obitoDTO.setSetor(setor.getId());
        this.obitoDTO.setPrimeiraCausaMortis(new CausaMortis("Primeira Causa Mortis"));
        this.obitoDTO.setSegundaCausaMortis(new CausaMortis("Segunda Causa Mortis"));
        this.obitoDTO.setTerceiraCausaMortis(new CausaMortis("Terceira Causa Mortis"));
        this.obitoDTO.setQuartaCausaMortis(new CausaMortis("Quarta Causa Mortis"));
    }

    private void preencherEndereco(EnderecoDTO endereco) {

        endereco.setCep("29182527");
        endereco.setComplemento("");
        endereco.setLogradouro("Rua Luiz Inacio Lula da Silva");
        endereco.setNumero("9");
        this.preencherPaisEstadoCidadeBairro(endereco);
    }

    private void preencherPaisEstadoCidadeBairro(EnderecoDTO endereco) {

        Pais brasil = aplEndereco.obterPaisPorNome("Brasil");
        Estado es = utility.getObjectByMethod(aplEndereco.obterEstadosPorPais(brasil.getId()), getSiglaEstado, "ES");
        Cidade serra = utility.getObjectByMethod(aplEndereco.obterCidadesPorEstado(es.getId()), getNomeCidade, "Serra");
        Bairro laranjeiras = utility.getObjectByMethod(aplEndereco.obterBairrosPorCidade(serra.getId()), getNomeBairro, "Laranjeiras");

        endereco.setEstado(es.getId());
        endereco.setCidade(serra.getId());
        endereco.setBairro(laranjeiras.getId());
    }

}
