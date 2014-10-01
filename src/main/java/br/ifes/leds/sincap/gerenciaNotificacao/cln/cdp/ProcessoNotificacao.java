package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Notificador;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.EstadoNotificacaoEnum.NOTIFICACAOEXCLUIDA;

/**
 * Notificacao.java
 *
 * @author 20091BSI0273 Classe que representa a notificacao de obito de um
 * paciente.
 *
 * Alterado por: Lucas Coutinho de Souza Oliveira em 09/12/2013. Alterado por
 * 20112bsi0083 em 29/11/2013
 */
@Setter
@Getter
@Entity
@EqualsAndHashCode(callSuper = true)
public class ProcessoNotificacao extends ObjetoPersistente {

    @Column(unique = true, nullable = false)
    @NotNull
    private String codigo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    @NotNull
    private Calendar dataAbertura;

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Calendar dataArquivamento;
    
    @Column
    private boolean arquivado;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @NotNull
    private List<AtualizacaoEstado> historico;

    @OneToOne
    private AtualizacaoEstado ultimoEstado;
    
    @ManyToOne
    @JoinColumn
    @NotNull
    private Notificador notificador;
    
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn
    @NotNull
    private Obito obito;
    
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(nullable = true)
    private Entrevista entrevista;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(nullable = true)
    private Captacao captacao;
    
    @OneToOne
    @JoinColumn
    private CausaNaoDoacao causaNaoDoacao;

    public boolean isExcluido() {
        return ultimoEstado != null && ultimoEstado.getEstadoNotificacao() == NOTIFICACAOEXCLUIDA;
    }

    public boolean aptoDoacao() {
        return this.obito.isAptoDoacao();
    }

    /**
     * Muda o estado atual do processo de notificação. O que esse método
     * faz é setar o último estado do processo de notificação com o parâmetro
     * e adiciona o mesmo estado ao histórico do processo.
     *
     * @param estado O estado a ser definido no processo de notificação.
     */
    public void mudarEstadoAtual(AtualizacaoEstado estado) {
        if (this.historico == null) {
            this.historico = new ArrayList<>();
        }

        this.ultimoEstado = estado;
        this.historico.add(estado);
    }

    /**
     * Sobrescreve o get original para retornar uma lista imutável.
     * O estado do processo de notificação deve ser alterado somente por essa classe.
     *
     * @return Uma lista imutável com o histórico de atualização estado.
     */
    @SuppressWarnings("unused")
    public List<AtualizacaoEstado> getHistorico() {
        return Collections.unmodifiableList(this.historico);
    }

    /**
     * Pega o histórico do processo de notificação. Cuidado! Esse método retorna
     * uma lista que pode ser modificada, use com MUITO cuidado. Se quiser adicionar
     * um novo estado ao processo favor utilizar o método {@code mudarEstadoAtual()}.
     *
     * @return Uma lista com o histórico das atualizações do estado do processo.
     */
    @SuppressWarnings("unused")
    public List<AtualizacaoEstado> getHistoricoModificavel() {
        return this.historico;
    }

    /**
     * O mesmo que {@code setHistorico()}. Criado apenas para compatibilidade com o
     * framework Dozer.
     *
     * @param historico O histórico de atualizações de estado do processo de
     *                  notificação.
     */
    @SuppressWarnings("unused")
    public void setHistoricoModificavel(List<AtualizacaoEstado> historico) {
        this.setHistorico(historico);
    }
}
