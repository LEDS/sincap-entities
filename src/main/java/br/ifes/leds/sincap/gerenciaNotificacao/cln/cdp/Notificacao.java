package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import br.ifes.leds.sincap.controleInterno.cln.cdp.InstituicaoNotificadora;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Notificador;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Setor;

/**
 * Notificacao.java
 *
 * @author 20091BSI0273 Classe que representa a notificacao de obito de um
 * paciente.
 *
 * Alterado por: Lucas Coutinho de Souza Oliveira em 09/12/2013. Alterado por
 * 20112bsi0083 em 29/11/2013
 */
@Entity
public class Notificacao extends ObjetoPersistente {

    // TODO: Cria regra de negocio para geracao do codigo
    @Column(unique = true, nullable = true)
    private String codigo;// representa o codigo da notificacao

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataAbertura;// representa a data de notificacao

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataArquivamento;

    @OneToOne
    @JoinColumn(nullable = true)
    @Cascade({CascadeType.SAVE_UPDATE})
    private Obito obito;// representa o obito que esta sendo notificado

    @ManyToOne
    @JoinColumn(nullable = false)
    private Notificador notificador;// representa o notificador que esta
    // notificando
    @OneToOne
    private Setor setor;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private InstituicaoNotificadora instituicao;

    public Calendar getDataArquivamento() {
        return dataArquivamento;
    }

    public void setDataArquivamento(Calendar dataArquivamento) {
        this.dataArquivamento = dataArquivamento;
    }

    public Calendar getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Calendar dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Obito getObito() {
        return obito;
    }

    public void setObito(Obito obito) {
        this.obito = obito;
    }

    public Notificador getNotificador() {
        return notificador;
    }

    public void setNotificador(Notificador notificador) {
        this.notificador = notificador;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public InstituicaoNotificadora getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(InstituicaoNotificadora instituicao) {
        this.instituicao = instituicao;
    }
}
