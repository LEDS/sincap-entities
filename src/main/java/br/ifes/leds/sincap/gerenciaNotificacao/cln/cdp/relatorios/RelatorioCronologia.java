package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.relatorios;

import lombok.*;
import lombok.experimental.Builder;

import java.util.Calendar;
import java.util.Date;


/**
 * @author AAguiar on 29/10/14.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class RelatorioCronologia {
    private Calendar data;
    private Integer hora; //rever isso
    private String nome;
    private int idade;
    private String causaObito;
    private String setorObito;
    private String doaTecido;
    private String causaNaoDoacao;
    private String observacao;
}
