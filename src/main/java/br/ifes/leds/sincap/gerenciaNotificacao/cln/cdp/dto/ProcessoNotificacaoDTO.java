package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto;

import lombok.*;
import lombok.experimental.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author 20102bsi0553
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class ProcessoNotificacaoDTO {

    private Long id;

    @Valid
    private ObitoDTO obito;

    @Valid
    private EntrevistaDTO entrevista;

    @Valid
    private CaptacaoDTO captacao;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private Calendar dataAbertura;

    private List<AtualizacaoEstadoDTO> historico = new ArrayList<>();
    private String codigo;
    private Calendar dataArquivamento;
    private boolean arquivado;
    private Long notificador;
    private Long causaNaoDoacao;
    private AtualizacaoEstadoDTO ultimoEstado;
}
