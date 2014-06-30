package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

/**
 *
 * @author 20102bsi0553
 */
public enum EstadoNotificacaoEnum {
    AGUARDANDOANALISEOBITO("Aguardando Análise de Óbito"),
    EMANALISEOBITO("Em Análise de Óbito"),
    AGUARDANDOENTREVISTA("Aguardando Entrevista"),
    AGUARDANDOANALISEENTREVISTA("Aguardando Análise Entrevista"),
    EMANALISEENTREVISTA("Em Analise da Entrevista"),
    AGUARDANDOCAPTACAO("Aguardando Captacao"),
    AGUARDANDOANALISECAPTACAO("Aguardando Análise da Captacao"),
    EMANALISECAPTACAO("Em Análise de Captação"),
    AGUARDANDOARQUIVAMENTO("Aguardando Arquivamento"),
    NOTIFICACAOARQUIVADA("Notificacao Arquivada");
    
    private final String nome;

    private EstadoNotificacaoEnum(String nome) {
        this.nome = nome;
    }
    
    @Override
    public String toString() {
        return nome;
    }
    
    
    
}
