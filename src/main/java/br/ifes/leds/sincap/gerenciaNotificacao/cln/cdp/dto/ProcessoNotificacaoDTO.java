package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Builder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.EstadoNotificacaoEnum.NOTIFICACAOEXCLUIDA;

/**
 *
 * @author 20102bsi0553
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProcessoNotificacaoDTO {

    private Long id;
    private List<AtualizacaoEstadoDTO> historico = new ArrayList<>();
    private ObitoDTO obito = new ObitoDTO();
    private EntrevistaDTO entrevista;
    private String codigo;
    private Calendar dataAbertura;
    private Calendar dataArquivamento;
    private boolean arquivado;
    private Long notificador;
    private CaptacaoDTO captacao;
    private Long causaNaoDoacao;
    private AtualizacaoEstadoDTO ultimoEstado;

    public boolean isExcluido() {
        if (ultimoEstado != null) {
            return ultimoEstado.getEstadoNotificacao() == NOTIFICACAOEXCLUIDA;
        }
        return false;
    }
}
