package br.ifes.leds.sincap.controleInterno.cln.cdp;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Setor.java
 *
 * @author 20091BSI0273 Classe que representa um setor hospitalar em que ocorreu
 *         um obito
 */
@Setter
@Getter
@Entity
@EqualsAndHashCode(callSuper = true, exclude = {"hospital"})
public class Setor extends ObjetoPersistente{

    @ManyToMany(mappedBy = "setores", fetch = FetchType.EAGER)
    Set<Hospital> hospital = new HashSet<>();

    private String nome;

    public void addHospital(Hospital hospital) {
        this.hospital.add(hospital);
    }

    public void removeHospital(Hospital hospital) {
        this.hospital.remove(hospital);
    }
}
