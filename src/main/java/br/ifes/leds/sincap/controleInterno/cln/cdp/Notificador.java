package br.ifes.leds.sincap.controleInterno.cln.cdp;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

/**
 * Notificador.java
 *
 * @author 20091BSI0273 Classe que representa o notificador
 */
@Entity
public class Notificador extends Funcionario {

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<InstituicaoNotificadora> instituicoesNotificadoras = new HashSet<InstituicaoNotificadora>();

    public Set<InstituicaoNotificadora> getInstituicoesNotificadoras() {
        return instituicoesNotificadoras;
    }

    public void setInstituicoesNotificadoras(Set<InstituicaoNotificadora> instituicoesNotificadoras) {
        this.instituicoesNotificadoras = instituicoesNotificadoras;
    }

}
