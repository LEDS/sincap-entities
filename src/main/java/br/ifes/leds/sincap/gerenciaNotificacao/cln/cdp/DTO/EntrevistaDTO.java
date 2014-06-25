/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.DTO;

/**
 *
 * @author Breno
 */
public class EntrevistaDTO {
    //Dados para o cabeçalho do formulário
    private String processoNotificacaoCodigo;
    private String pacienteNome;
    
    // 0 - False, 1 - True
    private Long entrevistaRealizada;
    
    // 0 - False, 1 - True
    private Long doacaoAutorizada;
    
    //Representa a recusa da doação e seu motivo, caso haja entrevista
    private String causaNaoDoacao;
    private String tipoNaoDoacao;
    
    //Representa a data e hora da intrevista, caso seja feita
    private String dataEntrevista;
    private String horaEntrevista;
    
    //Representa o endereco do paciente
    private String enderecoCEP;
    private String enderecoLogradouro;
    private String enderecoNumero;
    private String enderecoBairro;
    private String enderecoCidade;
    private String enderecoUF;
    
    //Representa os outros dados pessoais do paciente
    private String pacienteDocumentoSocial;
    private String pacienteParentesco;
    private String pacienteEstadoCivil;
    private String pacienteTelefone1;
    private String pacienteTelefone2;
    private String pacienteProfissao;
    private String pacienteNacionalidade;
    
    
    //Representa os dados da primeira testemunha
    private String testemunha1Nome;
    private String testemunha1CPF;
    
    //Representa os dados da primeira testemunha
    private String testemunha2Nome;
    private String testemunha2CPF;
}
