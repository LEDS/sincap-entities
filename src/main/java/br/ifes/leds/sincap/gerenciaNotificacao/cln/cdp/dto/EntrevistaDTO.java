/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto;

import java.util.Calendar;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.interfaces.EntrevistaInterface;
import lombok.*;
import lombok.experimental.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

/**
 *
 * @author Breno
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class EntrevistaDTO implements EntrevistaInterface {

    private Long id;
    // Dados para o cabeçalho do formulário
    @NotNull
    private boolean doacaoAutorizada;
    @NotNull
    private boolean entrevistaRealizada;
    @Past
    private Calendar dataEntrevista; // Data e horario do entrevista
    @Past
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private Calendar dataCadastro; // Data e horario da notificação
    @Valid
    private ResponsavelDTO responsavel;
    @Valid
    private ResponsavelDTO responsavel2;
    @Valid
    private TestemunhaDTO testemunha1;
    @Valid
    private TestemunhaDTO testemunha2;
    private Long funcionario;

    @Override
    public boolean haCausaNaoDoacao() {
        return !this.isDoacaoAutorizada() || !this.isEntrevistaRealizada();
    }
}
