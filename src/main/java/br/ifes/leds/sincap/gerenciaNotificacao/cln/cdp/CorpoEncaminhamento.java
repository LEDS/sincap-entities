package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

/**
 * Encaminhamento.java
 * @author 20091BSI0273
 * Enum que representao otipo de encaminhamento de um obito. Está mapeado no BD como ENUMERATED, logo nao pode alterar a ordem.
 * Acrescimos devem ser feitos sempre no final da lista
 */
public enum CorpoEncaminhamento {
	SVO, IML, NAO_ENCAMINHADO
}
