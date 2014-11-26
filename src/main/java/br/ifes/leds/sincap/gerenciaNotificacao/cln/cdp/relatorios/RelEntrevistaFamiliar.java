package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.relatorios;


import lombok.*;
import lombok.experimental.Builder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class RelEntrevistaFamiliar
{
    Integer desconhecimento;
    Integer potencial;
    Integer familiares;
    Integer familiaresCorpo;
    Integer naoEntendimento;
    Integer familiaresDescontentes;
    Integer receio;
    Integer religiao;
    Integer outros;

}
