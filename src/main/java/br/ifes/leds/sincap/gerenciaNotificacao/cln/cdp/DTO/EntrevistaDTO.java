/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto;

import java.util.Calendar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Breno
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntrevistaDTO {

    private Long id;
    // Dados para o cabeçalho do formulário
    private boolean doacaoAutorizada;
    private Calendar dataEntrevista; // Data e horario do entrevista
    private Calendar dataCadastro; // Data e horario da notificação
                                   // Doacao(entrevista)
    private ResponsavelDTO responsavel;
    private TestemunhaDTO testemunha1;
    private TestemunhaDTO testemunha2;
    private Long funcionario;
}
