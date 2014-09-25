package br.ifes.leds.sincap.test;

import br.ifes.leds.reuse.endereco.cdp.Estado;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ifes.leds.reuse.endereco.cgt.AplEndereco;

import java.util.List;

import junit.framework.Assert;

public class AplEnderecoTest extends AbstractionTest {

    @Autowired
    private AplEndereco aplEndereco;


    @Test
    public void obterEstadoNomePais() {
        Assert.assertTrue(aplEndereco.obterEstadosPorNomePais("Brasil").size() == 27);
    }

    @Test
    public void obterCidadesPorEstado() throws Exception {
        List<Estado> estados = aplEndereco.obterEstadosPorNomePais("Brasil");
        Estado estado = estados.get(0);
        Assert.assertTrue(aplEndereco.obterCidadesPorEstado(estado.getId()).size() > 0);
    }

}
