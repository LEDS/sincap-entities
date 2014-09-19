package br.ifes.leds.sincap.test;

import br.ifes.leds.reuse.ledsExceptions.CRUDExceptions.ViolacaoDeRIException;
import br.ifes.leds.reuse.utility.Factory;
import br.ifes.leds.reuse.utility.Utility;
import br.ifes.leds.reuse.utility.function.Function;
import br.ifes.leds.sincap.controleInterno.cgd.HospitalRepository;
import br.ifes.leds.sincap.controleInterno.cgd.SetorRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Hospital;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Setor;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CausaMortis;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CorpoEncaminhamento;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto.ObitoDTO;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto.PacienteDTO;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt.AplObito;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.util.dataFactory.PacienteData;
import junit.framework.Assert;
import org.dozer.Mapper;
import org.fluttercode.datafactory.impl.DataFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.GregorianCalendar;

public class AplObitoTest extends AbstractionTest {

    @Autowired
    private AplObito aplObito;
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
    @Autowired
    private PacienteData pacienteData;
    @Autowired
    private DataFactory df;

    private ObitoDTO obitoDTO;
    private PacienteDTO pacienteDTO;

    @Before
    public void before() throws Exception {
        this.obitoDTO = fabrica.criaObjeto(ObitoDTO.class);
        this.pacienteDTO = mapper.map(pacienteData.criarPaciente(df),
                PacienteDTO.class);

        preencherDadosObito();
    }

    private void preencherDadosObito() {
        Hospital hospital = hospitalRepository.findAll().get(0);
        Setor setor = setorRepository.findAll().get(0);

        this.obitoDTO.setPaciente(pacienteDTO);
        this.obitoDTO.setAptoDoacao(true);
        this.obitoDTO.setCorpoEncaminhamento(CorpoEncaminhamento.IML);
        this.obitoDTO.setDataCadastro(new GregorianCalendar(2014, 5, 28, 18, 30));
        this.obitoDTO.setDataObito(new GregorianCalendar(2014, 5, 27, 22, 55));
        this.obitoDTO.setHospital(hospital.getId());
        this.obitoDTO.setSetor(setor.getId());
        this.obitoDTO.setPrimeiraCausaMortis(new CausaMortis(
                "Primeira Causa Mortis"));
        this.obitoDTO.setSegundaCausaMortis(new CausaMortis(
                "Segunda Causa Mortis"));
        this.obitoDTO.setTerceiraCausaMortis(new CausaMortis(
                "Terceira Causa Mortis"));
        this.obitoDTO.setQuartaCausaMortis(new CausaMortis(
                "Quarta Causa Mortis"));
    }

    @Test
    public void obterPacienteTest() throws ViolacaoDeRIException {
        aplObito.salvarPaciente(pacienteDTO);

        PacienteDTO pacienteTmp = utility.getObjectByMethod(
                aplObito.obterTodosPacientes(), this.pacienteDTO.getNome(), getNomePaciente());
        Long idTest = pacienteTmp.getId();

        Assert.assertNotNull(aplObito.obterPaciente(idTest));
        Assert.assertEquals(idTest, aplObito.obterPaciente(idTest).getId());
    }

    @Test
    public void salvarPacienteTest() throws ViolacaoDeRIException {
        aplObito.salvarPaciente(pacienteDTO);

        PacienteDTO pacienteTest = utility.getObjectByMethod(
                aplObito.obterTodosPacientes(), this.pacienteDTO.getNome(), getNomePaciente());

        Assert.assertNotNull(pacienteTest.getId());
        Assert.assertNotNull(pacienteTest.getEndereco());
        Assert.assertNotNull(pacienteTest.getTelefone());

        Assert.assertEquals(this.pacienteDTO.getNome(), pacienteTest.getNome());
        Assert.assertEquals(this.pacienteDTO.getDocumentoSocial(),
                pacienteTest.getDocumentoSocial());
        Assert.assertEquals(this.pacienteDTO.getNacionalidade(),
                pacienteTest.getNacionalidade());
        Assert.assertEquals(this.pacienteDTO.getNomeMae(),
                pacienteTest.getNomeMae());
        Assert.assertEquals(this.pacienteDTO.getNumeroProntuario(),
                pacienteTest.getNumeroProntuario());
        Assert.assertEquals(this.pacienteDTO.getNumeroSUS(),
                pacienteTest.getNumeroSUS());
        Assert.assertEquals(this.pacienteDTO.getProfissao(),
                pacienteTest.getProfissao());
        Assert.assertEquals(this.pacienteDTO.getEndereco().getBairro(),
                pacienteTest.getEndereco().getBairro());
    }

    private Function<PacienteDTO, String> getNomePaciente() {
        return new Function<PacienteDTO, String>() {
            @Override
            public String apply(PacienteDTO parameter) {
                return parameter.getNome();
            }
        };
    }

    @Test
    public void salvarObitoTest() throws ViolacaoDeRIException {
        aplObito.salvarObito(obitoDTO);
        
        /*
         * Quando o banco já está preenchido, os testes rodam em cima desse banco,
         * logo, foi necessario usar essa recurso abaixo para que nao haja erros
         * independente do estado do banco.
         */
        int quantidadeObitos = aplObito.obterTodosObitos().size();
        if(quantidadeObitos > 0) quantidadeObitos--;

        ObitoDTO obitoTest = aplObito.obterTodosObitos().get(quantidadeObitos);

        Assert.assertNotNull(obitoTest);
        Assert.assertNotNull(obitoTest.getSetor());
        Assert.assertNotNull(obitoTest.getPaciente().getId());
        Assert.assertEquals(obitoTest.getPaciente().getEndereco().getCep(), 
                obitoTest.getPaciente().getEndereco().getCep());
    }

}
