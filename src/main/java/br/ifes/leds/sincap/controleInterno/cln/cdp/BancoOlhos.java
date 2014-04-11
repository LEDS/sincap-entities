package br.ifes.leds.sincap.controleInterno.cln.cdp;

import javax.persistence.Entity;

/**
 * BancoOlhos.java
 *
 * @author 200121BSI0252 Classe que representa um banco de olhos
 */
@Entity
public class BancoOlhos extends Instituicao{
    private String cnes; //codigo que identifica o estabelecimento de saude
    private String telefone;
    private String fax;
    private Long cidade;
    private Long estado;
    private Long bairro;
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;

    public String getCnes() {
        return cnes;
    }

    public void setCnes(String cnes) {
        this.cnes = cnes;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Long getCidade() {
        return cidade;
    }

    public void setCidade(Long cidade) {
        this.cidade = cidade;
    }

    public Long getEstado() {
        return estado;
    }

    public void setEstado(Long estado) {
        this.estado = estado;
    }

    public Long getBairro() {
        return bairro;
    }

    public void setBairro(Long bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

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
    

    
    
}
