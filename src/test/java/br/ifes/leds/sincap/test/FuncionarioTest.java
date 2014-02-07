package br.ifes.leds.sincap.test;

import br.ifes.leds.sincap.controleInterno.cgd.FuncionarioRepository;
import br.ifes.leds.sincap.controleInterno.cgd.NotificadorRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ifes.leds.sincap.controleInterno.cln.cdp.Funcionario;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Notificador;
import org.junit.Assert;

public class FuncionarioTest extends AbstractionTest {

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private NotificadorRepository notificadorRepository;
    private Funcionario funcionario;
    private Notificador notificador;

    @Test
    public void findNofierByCPF() {
        funcionario = this.funcionarioRepository.findByCpf("111.111.111-11");
        Assert.assertNotNull(funcionario);

    }

    @Test
    public void findNotificadorByCPF() {
        notificador = this.notificadorRepository.findByCpf("111.111.111-11");
        Assert.assertNotNull(notificador);

    }

}
