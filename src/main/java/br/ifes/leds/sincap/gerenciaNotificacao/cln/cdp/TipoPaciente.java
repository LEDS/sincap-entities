package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

/**
 * TipoPaciente.java
 * @author 20091BSI0273
 * Enum que presenta o tipo de paciente.  Está mapeado no BD como ENUMERATED, logo nao pode alterar a ordem.
 * Acrescimos devem ser feitos sempre no final da lista
 */
public enum TipoPaciente {
	CRIANCA, NAO_IDENTIFICADO, RECEM_NASCIDO, ADULTO
}
