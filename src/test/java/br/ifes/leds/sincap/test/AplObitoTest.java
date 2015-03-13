package br.ifes.leds.sincap.test;

import br.ifes.leds.reuse.utility.Utility;
import br.ifes.leds.reuse.utility.function.Function;
import br.ifes.leds.sincap.controleInterno.cgd.HospitalRepository;
import br.ifes.leds.sincap.controleInterno.cgd.SetorRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Hospital;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Setor;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CausaMortis;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto.ObitoDTO;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto.PacienteDTO;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt.AplObito;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.util.dataFactory.PacienteData;
import org.dozer.Mapper;
import org.fluttercode.datafactory.impl.DataFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.GregorianCalendar;

import static br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CorpoEncaminhamento.IML;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

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
    private PacienteData pacienteData;
    @Autowired
    private DataFactory df;

    private ObitoDTO obitoDTO;
    private PacienteDTO pacienteDTO;

    @Before
    public void before() throws Exception {
        this.pacienteDTO = mapper.map(pacienteData.criarPaciente(df), PacienteDTO.class);

        preencherDadosObito();
    }

    private void preencherDadosObito() {
        Hospital hospital = hospitalRepository.findAll().get(0);
        Setor setor = setorRepository.findAll().get(0);

        this.obitoDTO = ObitoDTO.builder()
                .paciente(pacienteDTO)
                .aptoDoacao(true)
                .corpoEncaminhamento(IML)
                .dataCadastro(new GregorianCalendar(2014, 5, 28, 18, 30))
                .dataObito(new GregorianCalendar(2014, 5, 27, 22, 55))
                .hospital(hospital.getId())
                .setor(setor.getId())
                .primeiraCausaMortis(new CausaMortis("Primeira Causa Mortis"))
                .segundaCausaMortis(new CausaMortis("Segunda Causa Mortis"))
                .terceiraCausaMortis(new CausaMortis("Terceira Causa Mortis"))
                .quartaCausaMortis(new CausaMortis("Quarta Causa Mortis"))
                .build();
    }

    @Test
    public void obterPacienteTest() {
        aplObito.salvarPaciente(pacienteDTO);

        PacienteDTO pacienteTmp = utility.getObjectByMethod(
                aplObito.obterTodosPacientes(), this.pacienteDTO.getNome(), getNomePaciente());
        Long idTest = pacienteTmp.getId();

        assertNotNull(aplObito.obterPaciente(idTest));
        assertEquals(idTest, aplObito.obterPaciente(idTest).getId());
    }

    @Test
    public void salvarPacienteTest() {
        aplObito.salvarPaciente(pacienteDTO);

        PacienteDTO pacienteTest = utility.getObjectByMethod(
                aplObito.obterTodosPacientes(), this.pacienteDTO.getNome(), getNomePaciente());

        assertNotNull(pacienteTest.getId());
        assertNotNull(pacienteTest.getEndereco());
        assertNotNull(pacienteTest.getTelefone());

        assertEquals(this.pacienteDTO.getNome(), pacienteTest.getNome());
        assertEquals(this.pacienteDTO.getNacionalidade(), pacienteTest.getNacionalidade());
        assertEquals(this.pacienteDTO.getNomeMae(), pacienteTest.getNomeMae());
        assertEquals(this.pacienteDTO.getNumeroProntuario(), pacienteTest.getNumeroProntuario());
        assertEquals(this.pacienteDTO.getNumeroSUS(), pacienteTest.getNumeroSUS());
        assertEquals(this.pacienteDTO.getProfissao(), pacienteTest.getProfissao());
        assertEquals(this.pacienteDTO.getEndereco().getBairro(), pacienteTest.getEndereco().getBairro());
        assertEquals(this.pacienteDTO.getDocumentoSocial(), pacienteTest.getDocumentoSocial());
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
    public void salvarObitoTest() {
        aplObito.salvarObito(obitoDTO);
        
        /*
         * Quando o banco já está preenchido, os testes rodam em cima desse banco,
         * logo, foi necessario usar essa recurso abaixo para que nao haja erros
         * independente do estado do banco.
         */
        int quantidadeObitos = aplObito.obterTodosObitos().size();
        if(quantidadeObitos > 0) quantidadeObitos--;

        ObitoDTO obitoTest = aplObito.obterTodosObitos().get(quantidadeObitos);

        assertNotNull(obitoTest);
        assertNotNull(obitoTest.getSetor());
        assertNotNull(obitoTest.getPaciente().getId());
//        assertEquals(obitoTest.getPaciente().getEndereco().getCep(), obitoTest.getPaciente().getEndereco().getCep());
    }

}
