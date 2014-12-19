package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

import lombok.Getter;

/**
 *
 * @author 20102bsi0553
 */
@Getter
public enum EstadoNotificacaoEnum {
    AGUARDANDOCORRECAOOBITO("Aguardando Correção do Óbito"),
    AGUARDANDOCORRECAOENTREVISTA("Aguardando Correção do Entrevista"),
    AGUARDANDOCORRECAOCAPTACACAO("Aguardando Correção do Captação"),
    AGUARDANDOANALISEOBITO("Aguardando Análise de Óbito"),
    EMANALISEOBITO("Em Análise de Óbito"),
    AGUARDANDOENTREVISTA("Aguardando Entrevista"),
    AGUARDANDOANALISEENTREVISTA("Aguardando Análise Entrevista"),
    EMANALISEENTREVISTA("Em Análise da Entrevista"),
    AGUARDANDOCAPTACAO("Aguardando Captação"),
    AGUARDANDOANALISECAPTACAO("Aguardando Análise da Captação"),
    EMANALISECAPTACAO("Em Análise de Captação"),
    AGUARDANDOARQUIVAMENTO("Aguardando Arquivamento"),
    NOTIFICACAOARQUIVADA("Notificação Arquivada"),
    NOTIFICACAOEXCLUIDA("Notificação Excluída");
    
    private final String nome;

    private EstadoNotificacaoEnum(String nome) {
        this.nome = nome;
    }
    
    @Override
    public String toString() {
        return nome;
    }
    
    
    
}
