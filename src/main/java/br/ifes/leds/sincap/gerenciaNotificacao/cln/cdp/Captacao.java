package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import javax.persistence.Column;
import javax.persistence.Entity;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import br.ifes.leds.sincap.controleInterno.cln.cdp.MotivoInviabilidade;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.ManyToMany;

/**
 * Captacao.java
 *
 * @author 20091BSI0273 Classe que representa a captacao de um determinado orgao
 */
@Entity
public class Captacao extends ObjetoPersistente {

    @Column
    private boolean corneasCaptadas;//valor que diz se a captacao foi realizada ou nao
    @Column
    private boolean equipeCaptacaoDisponivel;//valor que diz se a equipe de captacao estava disponivel
    @Column
    private String comentario;
    @ManyToMany
    private Set<MotivoInviabilidade> motivosInviabilidade = new HashSet<>();

    public boolean isCorneasCaptadas() {
        return corneasCaptadas;
    }

    public void setCorneasCaptadas(boolean corneasCaptadas) {
        this.corneasCaptadas = corneasCaptadas;
    }

    public boolean isEquipeCaptacaoDisponivel() {
        return equipeCaptacaoDisponivel;
    }

    public void setEquipeCaptacaoDisponivel(boolean equipeCaptacaoDisponivel) {
        this.equipeCaptacaoDisponivel = equipeCaptacaoDisponivel;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Set<MotivoInviabilidade> getMotivosInviabilidade() {
        return motivosInviabilidade;
    }

    public void setMotivosInviabilidade(Set<MotivoInviabilidade> motivosInviabilidade) {
        this.motivosInviabilidade = motivosInviabilidade;
    }
    
    public void addMotivoInviabilidade(MotivoInviabilidade motivoInviabilidade){
        this.motivosInviabilidade.add(motivoInviabilidade);
    }

 }
