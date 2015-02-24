package br.ifes.leds.sincap.test.entrevista;

import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.*;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto.*;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static br.ifes.leds.sincap.controleInterno.cln.cdp.Sexo.MASCULINO;
import static br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Escolaridade.ENSINO_MEDIO_COMPLETO;
import static br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.EstadoCivil.SOLTEIRO;
import static br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Parentesco.AUTORIZACAO_JUDICIAL;
import static br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoDocumentoComFoto.RG;

@Service
public class EntrevistaTestUtil {

    public static final boolean sim = true;
    public static final boolean nao = false;

    @Autowired
    private Mapper mapper;

    Responsavel responsavel() {
        final ResponsavelDTO responsavelDTO = ResponsavelDTO.builder()
                .nacionalidade("Brasileira")
                .dataNascimento(haVinteAnos())
                .profissao("Alguma")
                .documentoSocial(DocumentoComFotoDTO.builder()
                        .documento("fdsafads")
                        .tipoDocumentoComFoto(RG)
                        .build())
                .religiao("Católica")
                .grauEscolaridade(ENSINO_MEDIO_COMPLETO)
                .estadoCivil(SOLTEIRO)
                .sexo(MASCULINO)
                .parentesco(AUTORIZACAO_JUDICIAL)
                .telefone(Telefone.builder()
                        .numero("1231321321")
                        .build())
                .build();

        responsavelDTO.setNome("Fulano de Tal");
        return mapper.map(responsavelDTO, Responsavel.class);
    }

    Testemunha testemunha() {
        final TestemunhaDTO testemunhaDTO = TestemunhaDTO.builder()
                .nome("Testemunha")
                .documentoSocial(DocumentoComFotoDTO.builder()
                        .documento("432534534ES")
                        .tipoDocumentoComFoto(RG)
                        .build())
                .build();

        return mapper.map(testemunhaDTO, Testemunha.class);
    }

    Entrevista entrevistaRealizada() {
        final EntrevistaDTO entrevistaDTO = EntrevistaDTO.builder()
                .dataCadastro(hoje())
                .entrevistaRealizada(sim)
                .build();

        return mapper.map(entrevistaDTO, Entrevista.class);
    }

    Entrevista entrevistaDoacaoAutorizada() {
        final EntrevistaDTO entrevistaDTO = entrevistaDoacaoAutorizadaDTO();

        return mapper.map(entrevistaDTO, Entrevista.class);
    }

    static EntrevistaDTO entrevistaDoacaoAutorizadaDTO() {
        return EntrevistaDTO.builder()
                .dataCadastro(hoje())
                .entrevistaRealizada(sim)
                .doacaoAutorizada(sim)
                .build();
    }

    Entrevista entrevistaNaoRealizada() {
        EntrevistaDTO entrevistaDTO = EntrevistaDTO.builder()
                .dataCadastro(hoje())
                .entrevistaRealizada(nao)
                .build();

        return mapper.map(entrevistaDTO, Entrevista.class);
    }

    ProcessoNotificacao processoComObitoValido() {
        final ProcessoNotificacaoDTO notificacaoDTO = processoComObitoValidoDTO();

        return mapper.map(notificacaoDTO, ProcessoNotificacao.class);
    }

    static ProcessoNotificacaoDTO processoComObitoValidoDTO() {
        return ProcessoNotificacaoDTO.builder()
                .codigo("SOMECODE")
                .dataAbertura(hoje())
                .historico(new ArrayList<AtualizacaoEstadoDTO>())
                .notificador(1L)
                .obito(ObitoDTO.builder()
                        .dataObito(ontem())
                        .dataCadastro(hoje())
                        .aptoDoacao(sim)
                        .primeiraCausaMortis(new CausaMortis())
                        .segundaCausaMortis(new CausaMortis())
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

    static Calendar hoje() {
        return Calendar.getInstance(Locale.getDefault());
    }

    static Calendar ontem() {
        final Calendar ontem = hoje();

        ontem.set(Calendar.DAY_OF_MONTH, ontem.get(Calendar.DAY_OF_MONTH) - 1);

        return ontem;
    }

    static Calendar haDezMeses() {
        final Calendar haDezMeses = hoje();

        haDezMeses.set(Calendar.MONTH, haDezMeses.get(Calendar.MONTH) - 10);

        return haDezMeses;
    }

    static Calendar haVinteAnos() {
        final Calendar vinteAnos = hoje();

        vinteAnos.set(Calendar.YEAR, vinteAnos.get(Calendar.YEAR) - 20);

        return vinteAnos;
    }

    static Calendar dezesseisAnos() {
        final Calendar dezesseis = hoje();

        dezesseis.set(Calendar.YEAR, dezesseis.get(Calendar.YEAR) - 16);

        return dezesseis;
    }

    static long id() {
        return new Random().nextLong();
    }
}







