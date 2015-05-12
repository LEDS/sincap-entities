package br.ifes.leds.sincap.test;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CausaMortis;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.ProcessoNotificacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto.*;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

import static br.ifes.leds.sincap.controleInterno.cln.cdp.Sexo.MASCULINO;
import static br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.EstadoCivil.SOLTEIRO;
import static br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoDocumentoComFoto.RG;
import static java.util.Calendar.HOUR_OF_DAY;

public abstract class TestUtil {
    public static final boolean sim = true;
    public static final boolean nao = false;

    @Autowired
    protected Mapper mapper;

    public ProcessoNotificacao processoComObitoValido() {
        final ProcessoNotificacaoDTO notificacaoDTO = processoComObitoValidoDTO();

        return mapper.map(notificacaoDTO, ProcessoNotificacao.class);
    }

    public static ProcessoNotificacaoDTO processoComObitoValidoDTO() {
        return ProcessoNotificacaoDTO.builder()
                .codigo("SOMECODE")
                .dataAbertura(hoje())
                .historico(new ArrayList<AtualizacaoEstadoDTO>())
                .notificador(1L)
                .obito(ObitoDTO.builder()
                        .dataObito(ontem())
                        .dataCadastro(hoje())
                        .aptoDoacao(sim)
                        .primeiraCausaMortis(new CausaMortis("Causa mortis 1"))
                        .segundaCausaMortis(new CausaMortis("Causa mortis 2"))
                        .paciente(PacienteDTO.builder()
                                .dataInternacao(haDezMeses())
                                .dataNascimento(haVinteAnos())
                                .profissao("Alguma Profissão")
                                .nomeMae("Nome da Mãe")
                                .numeroProntuario("HOSP123456789")
                                .numeroSUS("123456789")
                                .nacionalidade("Brasileira")
                                .documentoSocial(DocumentoComFotoDTO.builder()
                                        .documento("2131432")
                                        .tipoDocumentoComFoto(RG)
                                        .build())
                                .sexo(MASCULINO)
                                .EstadoCivil(SOLTEIRO)
                                .build())
                        .setor(1L)
                        .hospital(1L)
                        .build()).build();
    }

    public static Calendar hoje() {
        return Calendar.getInstance(Locale.getDefault());
    }

    public static Calendar ontem() {
        final Calendar ontem = hoje();

        ontem.set(Calendar.DAY_OF_MONTH, ontem.get(Calendar.DAY_OF_MONTH) - 1);

        return ontem;
    }

    public static Calendar haDuasHoras() {
        final Calendar haDuasHoras = hoje();

        haDuasHoras.set(HOUR_OF_DAY, haDuasHoras.get(HOUR_OF_DAY) - 3);

        return haDuasHoras;
    }

    public static Calendar haDezMeses() {
        final Calendar haDezMeses = hoje();

        haDezMeses.set(Calendar.MONTH, haDezMeses.get(Calendar.MONTH) - 10);

        return haDezMeses;
    }

    public static Calendar haVinteAnos() {
        final Calendar vinteAnos = hoje();

        vinteAnos.set(Calendar.YEAR, vinteAnos.get(Calendar.YEAR) - 20);

        return vinteAnos;
    }

    public static Calendar dezesseisAnos() {
        final Calendar dezesseis = hoje();

        dezesseis.set(Calendar.YEAR, dezesseis.get(Calendar.YEAR) - 16);

        return dezesseis;
    }

    public static long id() {
        return new Random().nextLong();
    }
}
