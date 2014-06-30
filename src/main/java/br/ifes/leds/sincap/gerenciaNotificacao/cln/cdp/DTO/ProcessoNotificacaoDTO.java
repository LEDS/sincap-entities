package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.DTO;

import java.util.Calendar;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author 20102bsi0553
 */
@Getter
@Setter
public class ProcessoNotificacaoDTO {
    private String codigo;
    private Calendar dataAbertura;
    private Calendar dataArquivamento;
    private boolean arquivado;
    private List<AtualizacaoEstadoDTO> historicoDTO;
    private Long notificador;
    private ObitoDTO obitoDTO;
    private EntrevistaDTO entrevistaDTO;
    //private CaptacaoDTO Captacao;
    private Long causaNaoDoacao;
}
