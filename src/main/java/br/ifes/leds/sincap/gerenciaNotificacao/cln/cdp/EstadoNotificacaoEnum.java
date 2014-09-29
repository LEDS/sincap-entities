package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

/**
 *
 * @author 20102bsi0553
 */
public enum EstadoNotificacaoEnum {
    AGUARDANDOCORRECAOOBITO("Aguardando Correção do Óbito"),
    AGUARDANDOCORRECAOENTREVISTA("Aguardando Correção do Entrevista"),
    AGUARDANDOCORRECAOCAPTACACAO("Aguardando Correção do Captação"),
    AGUARDANDOANALISEOBITO("Aguardando Análise de Óbito"),
    EMANALISEOBITO("Em Análise de Óbito"),
    AGUARDANDOENTREVISTA("Aguardando Entrevista"),
    AGUARDANDOANALISEENTREVISTA("Aguardando Análise Entrevista"),
    EMANALISEENTREVISTA("Em Analise da Entrevista"),
    AGUARDANDOCAPTACAO("Aguardando Captacao"),
    AGUARDANDOANALISECAPTACAO("Aguardando Análise da Captacao"),
    EMANALISECAPTACAO("Em Análise de Captação"),
    AGUARDANDOARQUIVAMENTO("Aguardando Arquivamento"),
    NOTIFICACAOARQUIVADA("Notificacao Arquivada"),
    NOTIFICACAOEXCLUIDA("Notificacao Excluida");
    
    private final String nome;

    private EstadoNotificacaoEnum(String nome) {
        this.nome = nome;
    }
    
    @Override
    public String toString() {
        return nome;
    }
    
    
    
}
