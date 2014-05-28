package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Marcos Dias
 */
@Getter
@Setter
@Entity
public class CausaNaoDoacao extends ObjetoPersistente{
    
    @Column
    private String nome;
}
