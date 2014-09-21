package br.ifes.leds.sincap.controleInterno.cln.cgt;

import br.ifes.leds.sincap.controleInterno.cgd.CaptadorRepository;
import br.ifes.leds.sincap.controleInterno.cgd.FuncionarioRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.*;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.AnalistaCNCDORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * AplPrincipal.java
 *
 * @author 20091BSI0273 Classe que presenta os serviços realizados pelos UC de
 *         login
 */
@Service
public class AplPrincipal {

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private CaptadorRepository captadorRepository;
    @Autowired
    private AnalistaCNCDORepository analistaCNCDORepository;
    @Autowired
    private AplNotificador aplNotificador;

    /**
     * Metodo para validar login do usuario.
     *
     * @return user - Objeto Usuário
     */
    public Funcionario validarLogin(String cpf) {
        return funcionarioRepository.findByCpf(cpf);
    }

    /**
     * Método para a obtenção de um conjunto de instituições notificadoras
     * atraves do CPF do Notificador.
     *
     * @param cpf - CPF do Notificador
     * @return Conjunto de Instituições notificadoras
     */
    public Set<InstituicaoNotificadora> obterInstituicoesNotificadorasPorCpf(String cpf) {

        Notificador notificador = aplNotificador.obterNotificador(cpf);
        if (notificador != null) {
            return notificador.getInstituicoesNotificadoras();
        }

        return null;
    }

    /**
     * Método para a obtenção de um Banco de Olhos atraves do CPF do Capador.
     *
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

    @SuppressWarnings("unused")
    public Funcionario obterFuncionarioPorFuncao(String cpf) {
        /*
         TODO - Refatorar o codigo para criar as Apls 
         para cada tipo de funcionario. Ou,
         criar uma AplFuncionario com todos os repositorios        
         */
        Funcionario funcionario;
        funcionario = analistaCNCDORepository.findByCpf(cpf);

        if (funcionario == null) {
            funcionario = captadorRepository.findByCpf(cpf);

            if (funcionario == null) {
                funcionario = aplNotificador.obterNotificador(cpf);
            }
        }

        return funcionario;
    }
}
