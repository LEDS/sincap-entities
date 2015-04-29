package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Notificador;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.interfaces.ProcessoNotificacaoInterface;
import br.ifes.leds.sincap.validacao.annotations.DataEntrevistaObitoConsistentes;
import br.ifes.leds.sincap.validacao.groups.entrevista.EntrevistaNaoRealizada;
import br.ifes.leds.sincap.validacao.groups.entrevista.EntrevistaRealizadaDoacaoAutorizada;
import br.ifes.leds.sincap.validacao.groups.entrevista.EntrevistaRealizadaDoacaoNaoAutorizada;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.*;

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
@DataEntrevistaObitoConsistentes
public class ProcessoNotificacao extends ObjetoPersistente implements ProcessoNotificacaoInterface {

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
    private Set<AtualizacaoEstado> historico;

    @OneToOne
    private AtualizacaoEstado ultimoEstado;
    
    @ManyToOne
    @JoinColumn
    @NotNull
    private Notificador notificador;
    
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn
    @NotNull
    @Valid
    private Obito obito;
    
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(nullable = true)
    @Valid
    private Entrevista entrevista;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(nullable = true)
    @Valid
    private Captacao captacao;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comentario> comentarios;

    @OneToOne
    @JoinColumn
    @Valid
    @Null(message = "{EntrevistaValida.causaNaoDoacaoExiste}", groups = {EntrevistaRealizadaDoacaoAutorizada.class})
    @NotNull.List({
        @NotNull(message = "{EntrevistaValida.problemasEstruturais}", groups = {EntrevistaNaoRealizada.class}),
        @NotNull(message = "{EntrevistaValida.recusaFamiliar}", groups = {EntrevistaRealizadaDoacaoNaoAutorizada.class})
    })
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
            this.historico = new HashSet<>();
        }

        this.ultimoEstado = estado;
        this.historico.add(estado);
    }

    /**
     * Cria uma lista de comentários caso ela não exista e adiciona o comentário
     * passado por parâmetro a lista.
     *
     * @param comentario O comentário que será incluído na lista de comentários
     */
    public void addComentario(Comentario comentario){
        if(this.comentarios == null){
            this.comentarios = new HashSet<>();
        }
        this.comentarios.add(comentario);
    }


    /**
     * Sobrescreve o get original para retornar uma lista imutável.
     * O estado do processo de notificação deve ser alterado somente por essa classe.
     *
     * @return Uma lista imutável com o histórico de atualização estado.
     */
    @SuppressWarnings("unused")
    public List<AtualizacaoEstado> getHistorico() {
        return Collections.unmodifiableList(new ArrayList<>(this.historico));
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

        if (this.historico !=null){
            return new ArrayList<>(this.historico);
        }

        return new ArrayList<>();

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
        this.historico = new HashSet<>(historico);
    }

    @Override
    @NotNull(message = "{EntrevistaValida.processoSemId}", groups = {EntrevistaNaoRealizada.class, EntrevistaRealizadaDoacaoAutorizada.class, EntrevistaRealizadaDoacaoNaoAutorizada.class})
    public Long getId() {
        return super.getId();
    }

    public void setHistorico(List<AtualizacaoEstado> historico) {
        this.historico = new HashSet<>(historico);
    }
}
