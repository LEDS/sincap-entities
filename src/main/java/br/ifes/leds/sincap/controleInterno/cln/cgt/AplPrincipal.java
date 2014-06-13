package br.ifes.leds.sincap.controleInterno.cln.cgt;

import br.ifes.leds.sincap.controleInterno.cgd.CaptadorRepository;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    private CaptadorRepository captadorRepository;
    @Autowired
    private AplNotificador aplNotificador;
    

    /**
     * Metodo para validar login do usuario.
     * @param cpf
     * @param password
     * @return user - Objeto Usuário
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

    /**
    * Método para a obtenção de um conjunto de instituições notificadoras atraves do CPF do Notificador.
     * @param cpf - CPF do Notificador
     * @return Conjunto de Instituições notificadoras
    * @throws Exception
    */
    
    public Set<InstituicaoNotificadora> obterInstituicoesNotificadorasPorCpf(String cpf) throws Exception {
        
        Notificador notificador = aplNotificador.obterNotificador(cpf);
        if (notificador != null) {
            return notificador.getInstituicoesNotificadoras();
        } else {
            throw new Exception("Nome de usuario nao existe");
        }

    }
    
    /**
    * Método para a obtenção de um Banco de Olhos atraves do CPF do Capador.
     * @param cpf - CPF do Captador.
     * @return Banco de olhos do Captador.
    * @throws Exception
    */
    
    public BancoOlhos obterBancoOlhosPorCpf(String cpf) throws Exception {
        
        Captador captador = this.captadorRepository.findByCpf(cpf);
        if (captador != null) {
            return captador.getBancoOlhos();
        } else {
            throw new Exception("Nome de usuario nao existe");
        }

    }
}
