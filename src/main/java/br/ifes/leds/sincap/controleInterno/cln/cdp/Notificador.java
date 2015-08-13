package br.ifes.leds.sincap.controleInterno.cln.cdp;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Notificador.java
 *
 * @author 20091BSI0273 Classe que representa o notificador
 */
@Getter
@Setter
@Entity
@PrimaryKeyJoinColumn(name="id")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, exclude = {"id","instituicoesNotificadoras"})
public class Notificador extends Funcionario {

    @ManyToMany(fetch = FetchType.EAGER)
    @NotNull
    private Set<InstituicaoNotificadora> instituicoesNotificadoras = new HashSet<>();
}
