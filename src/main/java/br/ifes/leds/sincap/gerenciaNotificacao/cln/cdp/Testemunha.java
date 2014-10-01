package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import br.ifes.leds.sincap.controleInterno.cln.cdp.Pessoa;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * Testemunha.java
 *
 * @author 20091BSI0273 Classe que representa uma Testemunha da autorizacao de
 *         doacao
 */
@Getter
@Setter
@Entity
public class Testemunha extends Pessoa {

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private DocumentoComFoto documentoSocial;
}