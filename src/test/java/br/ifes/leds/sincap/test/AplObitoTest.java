package br.ifes.leds.sincap.test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ifes.leds.reuse.endereco.cdp.Bairro;
import br.ifes.leds.reuse.endereco.cdp.Cidade;
import br.ifes.leds.reuse.endereco.cdp.Endereco;
import br.ifes.leds.reuse.endereco.cdp.Estado;
import br.ifes.leds.reuse.endereco.cdp.Pais;
import br.ifes.leds.reuse.endereco.cgt.AplEndereco;
import br.ifes.leds.reuse.utility.Factory;
import br.ifes.leds.reuse.utility.Utility;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Sexo;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.EstadoCivil;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.DTO.PacienteDTO;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt.AplObito;

public class AplObitoTest extends AbstractionTest {

    @Autowired
    private AplObito aplObito;
    @Autowired
    private AplEndereco aplEndereco;
    private Utility utility = Utility.INSTANCE;

    private final Factory fabrica = Factory.INSTANCE;
    private PacienteDTO pacienteDTO;
    private String nome;
    private Telefone telefone;
    private Endereco endereco;
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

    @Before
    public void before() throws Exception {
        this.pacienteDTO = fabrica.criaObjeto(PacienteDTO.class);
        this.endereco = fabrica.criaObjeto(Endereco.class);
        this.telefone = fabrica.criaObjeto(Telefone.class);
        this.dataInternacao = new GregorianCalendar();
        this.dataNascimento = new GregorianCalendar();

        gerarDadosPaciente();
        preencherEndereco(endereco);
        preencherDadosPaciente();
    }

    @Test
    public void savarPacienteTest() throws Exception {
        aplObito.salvarPaciente(pacienteDTO);

        PacienteDTO pacienteTest = utility.getObjectByMethod(aplObito.obterTodosPacientes(), PacienteDTO.class.getDeclaredMethod("getNome"), this.nome);

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
    }

    private void gerarDadosPaciente() {
        this.nome = "José do Teste";
        this.telefone.setNumero("2733225640");

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

    private void preencherEndereco(Endereco endereco) throws Exception {

        endereco.setCep("29182527");
        endereco.setComplemento("");
        endereco.setLogradouro("Rua Luiz Inacio Lula da Silva");
        endereco.setNumero("9");
        this.preencherPaisEstadoCidadeBairro(endereco);
    }

    private void preencherPaisEstadoCidadeBairro(Endereco endereco) throws Exception {

        Pais brasil = aplEndereco.obterPaisPorNome("Brasil");

        Estado es = utility.getObjectByMethod(aplEndereco.obterEstadosPorPais(brasil.getId()), Estado.class.getDeclaredMethod("getSigla"), "ES");

        Cidade serra = utility.getObjectByMethod(aplEndereco.obterCidadesPorEstado(es.getId()), Cidade.class.getDeclaredMethod("getNome"), "Serra");

        Bairro laranjeiras = utility.getObjectByMethod(aplEndereco.obterBairrosPorCidade(serra.getId()), Bairro.class.getDeclaredMethod("getNome"), "Laranjeiras");

        endereco.setEstado(es);
        endereco.setCidade(serra);
        endereco.setBairro(laranjeiras);
    }

}
