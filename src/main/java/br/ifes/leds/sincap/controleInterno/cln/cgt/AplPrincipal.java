package br.ifes.leds.sincap.controleInterno.cln.cgt;

import br.ifes.leds.sincap.controleInterno.cgd.CaptadorRepository;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.ifes.leds.sincap.controleInterno.cgd.NotificadorRepository;
import br.ifes.leds.sincap.controleInterno.cgd.FuncionarioRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.BancoOlhos;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Captador;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Funcionario;
import br.ifes.leds.sincap.controleInterno.cln.cdp.InstituicaoNotificadora;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Notificador;

/**
 * AplPrincipal.java
 *
 * @author 20091BSI0273 Classe que presenta os serviços realizados pelos UC de
 * login
 */
@Service
public class AplPrincipal {

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private NotificadorRepository notificadorRepository;
    @Autowired
    private CaptadorRepository captadorRepository;
    
    

    /**
     * Metodo para validar login do usuario
     *
     * @param cpf
     * @param password
     * @return
     * @throws Exception
     */
    public Funcionario validarLogin(String cpf, String password) throws Exception {

        Funcionario user = funcionarioRepository.findByCpf(cpf);
        Pageable pageable;

        if (user != null && user.getCpf().equals(cpf) && user.getSenha().equals(password)) {

            if (user.isAtivo()) {
                return user;
            } else {
                throw new Exception("Usuario Inativo, contate o administrador do sistema.");
            }

        } else {
            throw new Exception("Login ou Senha nao conferem.");
        }

    }

    public Set<InstituicaoNotificadora> obterInstituicoesNotificadorasPorCpf(String username) throws Exception {
        
        Notificador notificador = this.notificadorRepository.findByCpf(username);
        if (notificador != null) {
            return notificador.getInstituicoesNotificadoras();
        } else {
            throw new Exception("Nome de usuario nao existe");
        }

    }
    
    public BancoOlhos obterBancoOlhosPorCpf(String username) throws Exception {
        
        Captador captador = this.captadorRepository.findByCpf(username);
        if (captador != null) {
            return captador.getBancoOlhos();
        } else {
            throw new Exception("Nome de usuario nao existe");
        }

    }

    public Notificador obterNotificadorPorUsuarioUsername(String username) {

        return notificadorRepository.findByCpf(username);
    }
    
    public Funcionario obterFuncionarioPorUsuarioUsername(String username) {

        return funcionarioRepository.findByCpf(username);
    }

}
