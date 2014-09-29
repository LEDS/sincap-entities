/*
 * Endereco.java
 * 
 * LEDS - Laboratório de Engenharia e Desenvolvimento de Software
 * IFES - Instituto Federal do Espírito Santo - Campus Serra.
 */

package br.ifes.leds.reuse.endereco.cdp;

import javax.persistence.Entity;
import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Builder;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Endereco  extends ObjetoPersistente {
    @Column
    @JoinColumn(nullable = false)
    private String logradouro;

    @Column
    @JoinColumn(nullable = false)
    private String numero;

    @Column
    @JoinColumn(nullable = false)
    private String complemento;

    @OneToOne
    private Bairro bairro;

    @OneToOne
    private Cidade cidade;

    @OneToOne
    private Estado estado;

    @Column
    private String cep;
}
