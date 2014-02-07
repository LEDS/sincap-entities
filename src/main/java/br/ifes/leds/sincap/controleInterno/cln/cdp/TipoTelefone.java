package br.ifes.leds.sincap.controleInterno.cln.cdp;

/**
 * TipoTelefone.java
 * @author 20091BSI0273
 * Enum que representa o tipo de telefone. Está mapeado no BD como ENUMERATED, logo nao pode alterar a ordem.
 * Acrescimos devem ser feitos sempre no final da lista
 */
public enum TipoTelefone {
	RESIDENCIAL, COMERCIAL, FAX, CELULAR
}
