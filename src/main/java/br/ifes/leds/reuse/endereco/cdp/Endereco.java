/*
 * Endereco.java
 * 
 * LEDS - Laboratório de Engenharia e Desenvolvimento de Software
 * IFES - Instituto Federal do Espírito Santo - Campus Serra.
 */

package br.ifes.leds.reuse.endereco.cdp;

import javax.persistence.Entity;
import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import javax.persistence.OneToOne;

@Entity
public class Endereco  extends ObjetoPersistente {
    
    private String logradouro;
    
    private String numero;
    
    private String complemento;
    
    @OneToOne
    private Bairro bairro;
    
    @OneToOne
    private Cidade cidade;
    
    @OneToOne
    private Estado estado;
    
    private String cep;
    
    public String getLogradouro() {
        return logradouro;
    }
    
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }
    
    public String getNumero() {
        return numero;
    }
    
    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    public String getComplemento() {
        return complemento;
    }
    
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    
    public Bairro getBairro() {
        return bairro;
    }
    
    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }
    
    public Cidade getCidade() {
        return cidade;
    }
    
    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
    
    public Estado getEstado() {
        return estado;
    }
    
    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    public String getCEP() {
        return cep;
    }
    
    public void setCEP(String CEP) {
        this.cep = CEP;
    }
    
}
