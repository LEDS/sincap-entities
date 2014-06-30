package br.ifes.leds.sincap.gerenciaNotificacao.cln.util;

import br.ifes.leds.reuse.persistence.ObjetoPersistente;

public class FabricaGerenciaNotificacao {

    private final String path = "br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp";

    private static FabricaGerenciaNotificacao fabrica;

    public synchronized static FabricaGerenciaNotificacao getInstance() {

        if (fabrica == null) {
            fabrica = new FabricaGerenciaNotificacao();
        }

        return fabrica;
    }

    private FabricaGerenciaNotificacao() {
    }

    public synchronized ObjetoPersistente getObjeto(String nomeObjeto) throws ClassNotFoundException {
        try {
            Class<?> c = null;

            c = Class.forName(path + nomeObjeto);

            ObjetoPersistente objetoPersistente = (ObjetoPersistente) c.newInstance();

            return objetoPersistente;
        } catch (Exception e) {
            throw new ClassNotFoundException();
        }
    }
}
