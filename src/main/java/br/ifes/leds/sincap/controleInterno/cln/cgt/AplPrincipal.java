package br.ifes.leds.sincap.controleInterno.cln.cgt;

import br.ifes.leds.sincap.controleInterno.cgd.AnalistaCNCDORepository;
import br.ifes.leds.sincap.controleInterno.cgd.CaptadorRepository;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.ifes.leds.sincap.controleInterno.cgd.NotificadorRepository;
import br.ifes.leds.sincap.controleInterno.cgd.FuncionarioRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Captador;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Funcionario;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Hospital;
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
//    @Autowired
//    private CaptadorRepository captadorRepository;
//    @Autowired
//    private AnalistaCNCDORepository analistaRepository;

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

            if (user.isActive()) {
                return user;
            } else {
                throw new Exception("Usuario Inativo, contate o administrador do sistema.");
            }

        } else {
            throw new Exception("Login ou Senha nao conferem.");
        }

    }

    public Set<Hospital> obterHopitaisPorUsername(String username) throws Exception {
        
        Notificador notificador = this.notificadorRepository.findByCpf(username);
        if (notificador != null) {
            return notificador.getHospitais();
        } else {
            throw new Exception("Nome de usuario nao existe");
        }

    }

    public Notificador obterNotificadorPorUsuarioUsername(String username) {

        return notificadorRepository.findByCpf(username);
    }

}
