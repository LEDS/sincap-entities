package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.interfaces;

import java.util.Calendar;

/**
 * Interface que contém métodos exclusivos de óbito. Tanto DTO quanto do {@code Entity}.
 */
public interface ObitoInterface extends DataCadastro, HaCausaNaoDoacao {

    public PacienteInterface getPaciente();

    public Calendar getDataObito();

    public Integer getIdadePaciente();
}
