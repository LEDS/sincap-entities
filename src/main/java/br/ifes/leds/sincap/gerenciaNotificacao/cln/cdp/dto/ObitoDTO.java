package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto;

import java.util.Calendar;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CausaMortis;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CorpoEncaminhamento;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoObito;
import lombok.experimental.Builder;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ObitoDTO {

    private Long id;
    private PacienteDTO paciente = new PacienteDTO();
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private Calendar dataObito;
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private Calendar dataCadastro;
    private boolean aptoDoacao;
    private CorpoEncaminhamento corpoEncaminhamento;
    private CausaMortis primeiraCausaMortis;
    private CausaMortis segundaCausaMortis;
    private CausaMortis terceiraCausaMortis;
    private CausaMortis quartaCausaMortis;
    private Long setor;
    private Long hospital;
    private TipoObito tipoObito;
}
