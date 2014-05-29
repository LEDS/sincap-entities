package br.ifes.leds.sincap.controleInterno.cln.cdp;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

/**
 * Notificador.java
 *
 * @author 20091BSI0273 Classe que representa o notificador
 */
@Getter
@Setter
@Entity
@PrimaryKeyJoinColumn(name="id")
public class Notificador extends Funcionario {

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<InstituicaoNotificadora> instituicoesNotificadoras = new HashSet<>();
}
