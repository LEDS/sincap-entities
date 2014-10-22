package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.interfaces;

public interface HaCausaNaoDoacao {

    /**
     * Verifica se há alguma causa de não doação.
     * @return {@code true} se houver alguma causa de não doação e <br/>
     * {@code false} se não houver nenhuma causa
     */
    public boolean haCausaNaoDoacao();
}
