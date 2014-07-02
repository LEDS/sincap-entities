package br.ifes.leds.sincap.test;

import java.util.Calendar;
import java.util.List;

import org.dozer.Mapper;
import org.fluttercode.datafactory.impl.DataFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ifes.leds.reuse.ledsExceptions.CRUDExceptions.ViolacaoDeRIException;
import br.ifes.leds.sincap.controleInterno.cgd.NotificadorRepository;
import br.ifes.leds.sincap.controleInterno.cgd.SetorRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Notificador;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Setor;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CausaMortis;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CorpoEncaminhamento;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.EstadoNotificacaoEnum;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoObito;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.DTO.AtualizacaoEstadoDTO;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.DTO.ObitoDTO;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.DTO.PacienteDTO;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.DTO.ProcessoNotificacaoDTO;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt.AplProcessoNotificacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.util.dataFactory.PacienteData;

/**
 *
 * @author 20102BSI0553
 */
public class AplProcessoNotificacaoTest extends AbstractionTest {

    @Autowired
    private AplProcessoNotificacao aplProcessoNotificacao;
    @Autowired
    private NotificadorRepository notificadorRepository;
    @Autowired
    private SetorRepository setorRepository;
    @Autowired
    private Mapper mapper;
    @Autowired
    private PacienteData pacienteData;
    @Autowired
    private DataFactory df;

    private ProcessoNotificacaoDTO notificacao;

    @Before
    public void before() {
        notificacao = new ProcessoNotificacaoDTO();

        this.notificacao.setDataAbertura(Calendar.getInstance());
        this.notificacao.setArquivado(false);

        this.getEstadoNotificacao(this.notificacao);
        this.getObito(this.notificacao);

        Notificador notificador = notificadorRepository.findAll().get(0);
        this.notificacao.setNotificador(notificador.getId());
    }

    private void getEstadoNotificacao(ProcessoNotificacaoDTO notificacao) {
        Notificador notificador = notificadorRepository.findAll().get(0);

        AtualizacaoEstadoDTO novoEstado = new AtualizacaoEstadoDTO();
        novoEstado.setFuncionario(notificador.getId());
        // FIXME: Verificar o valor correto do Enum
        novoEstado
                .setEstadoNotificacao(EstadoNotificacaoEnum.AGUARDANDOANALISEOBITO);

        notificacao.getHistorico().add(novoEstado);
    }

    private void getObito(ProcessoNotificacaoDTO notificacao) {
        ObitoDTO obito = notificacao.getObito();
        Setor setor = setorRepository.findAll().get(0);

        obito.setTipoObito(TipoObito.PCR);
        obito.setDataEvento(Calendar.getInstance());
        obito.setDataObito(Calendar.getInstance());
        obito.setCorpoEncaminhamento(CorpoEncaminhamento.NAO_ENCAMINHADO);
        obito.setAptoDoacao(true);
        obito.setSetor(setor.getId());

        this.getCausasMortis(obito);
        obito.setPaciente(mapper.map(pacienteData.criarPaciente(df),
                PacienteDTO.class));
    }

    private void getCausasMortis(ObitoDTO obito) {
        CausaMortis causa1 = new CausaMortis();
        CausaMortis causa2 = new CausaMortis();
        CausaMortis causa3 = new CausaMortis();
        CausaMortis causa4 = new CausaMortis();
        causa1.setNome("CausaObito1");
        causa1.setNome("CausaObito2");
        causa1.setNome("CausaObito3");
        causa1.setNome("CausaObito4");

        obito.setPrimeiraCausaMortis(causa1);
        obito.setSegundaCausaMortis(causa2);
        obito.setTerceiraCausaMortis(causa3);
        obito.setQuartaCausaMortis(causa4);
    }

    @Test
    public void recuperarNotificacoesNaoArquivadas()
            throws ViolacaoDeRIException {

        aplProcessoNotificacao.salvarNovaNotificacao(notificacao);

        List<ProcessoNotificacaoDTO> notificacoes = aplProcessoNotificacao
                .retornarNotificacaoNaoArquivada();

        Assert.assertTrue(notificacoes.size() > 0);
    }

}