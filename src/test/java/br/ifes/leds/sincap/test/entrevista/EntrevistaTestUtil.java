package br.ifes.leds.sincap.test.entrevista;

import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Entrevista;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Responsavel;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Testemunha;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto.DocumentoComFotoDTO;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto.EntrevistaDTO;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto.ResponsavelDTO;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto.TestemunhaDTO;
import br.ifes.leds.sincap.test.TestUtil;
import org.springframework.stereotype.Service;

import static br.ifes.leds.sincap.controleInterno.cln.cdp.Sexo.MASCULINO;
import static br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Escolaridade.ENSINO_MEDIO_COMPLETO;
import static br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.EstadoCivil.SOLTEIRO;
import static br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Parentesco.AUTORIZACAO_JUDICIAL;
import static br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoDocumentoComFoto.RG;

@Service
public class EntrevistaTestUtil extends TestUtil {

    Responsavel responsavel() {
        final ResponsavelDTO responsavelDTO = responsavelDTO();

        responsavelDTO.setNome("Fulano de Tal");
        return mapper.map(responsavelDTO, Responsavel.class);
    }

    static ResponsavelDTO responsavelDTO() {
        return ResponsavelDTO.builder()
                .nacionalidade("Brasileira")
                .nome("Nome do Responsavel")
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
                .telefone2(Telefone.builder()
                                .numero("1231321312")
                                .build()
                )
                .build();
    }

    Testemunha testemunha() {
        final TestemunhaDTO testemunhaDTO = testemunhaDTO();

        return mapper.map(testemunhaDTO, Testemunha.class);
    }

    static TestemunhaDTO testemunhaDTO() {
        return TestemunhaDTO.builder()
                .nome("Testemunha")
                .documentoSocial(DocumentoComFotoDTO.builder()
                        .documento("432534534ES")
                        .tipoDocumentoComFoto(RG)
                        .build())
                .build();
    }

    Entrevista entrevistaRealizada() {
        final EntrevistaDTO entrevistaDTO = entrevistaRealizadaDTO();

        return mapper.map(entrevistaDTO, Entrevista.class);
    }

    static EntrevistaDTO entrevistaRealizadaDTO() {
        return EntrevistaDTO.builder()
                .dataCadastro(hoje())
                .dataEntrevista(hoje())
                .entrevistaRealizada(sim)
                .build();
    }

    Entrevista entrevistaDoacaoAutorizada() {
        final EntrevistaDTO entrevistaDTO = entrevistaDoacaoAutorizadaDTO();

        return mapper.map(entrevistaDTO, Entrevista.class);
    }

    static EntrevistaDTO entrevistaDoacaoAutorizadaDTO() {
        return EntrevistaDTO.builder()
                .dataCadastro(hoje())
                .dataEntrevista(hoje())
                .entrevistaRealizada(sim)
                .doacaoAutorizada(sim)
                .build();
    }

    Entrevista entrevistaNaoRealizada() {
        EntrevistaDTO entrevistaDTO = entrevistaNaoRealizadaDTO();

        return mapper.map(entrevistaDTO, Entrevista.class);
    }

    static EntrevistaDTO entrevistaNaoRealizadaDTO() {
        return EntrevistaDTO.builder()
                .dataCadastro(hoje())
                .entrevistaRealizada(nao)
                .build();
    }
}







