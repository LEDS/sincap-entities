package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp;

/**
 *
 * @author 20102bsi0553
 */
public enum EstadoNotificacaoEnum {
    AGUARDANDOANALISE("Aguardando Analisa"),
    EMANALISE("Em Analise"),
    NOTIFICACAOANALISADA("Notificacao Analisada"),
    AGUARDANDOENTREVISTA("Aguardando Entrevista"),
    ENTREVISTAREALIZADA("Entrevista Realizada"),
    ANALISEENTREVISTA("Analise Entrevista"),
    AGUARDANDOCAPTACAO("Aguardando Captacao"),
    EMCAPTACAO("Em Captacao"),
    AGUARDANDOARQUIVAMENTO("Aguardando Arquivamento"),
    NOTIFICACAOARQUIVADA("Notificacao Arquivada");
    
    private String nome;

    private EstadoNotificacaoEnum(String nome) {
        this.nome = nome;
    }
    
    @Override
    public String toString() {
        return nome;
    }
    
    
    
}
