package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.DTO;

import java.util.ArrayList;
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

    private Long id;
    private List<AtualizacaoEstadoDTO> historico = new ArrayList<>();
    private ObitoDTO obito = new ObitoDTO();
    private EntrevistaDTO entrevista;
    private String codigo;
    private Calendar dataAbertura;
    private Calendar dataArquivamento;
    private boolean arquivado;
    private Long notificador;
    // private CaptacaoDTO Captacao;
    private Long causaNaoDoacao;
}
