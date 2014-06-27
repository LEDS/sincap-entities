/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.DTO;

import br.ifes.leds.reuse.endereco.cdp.Endereco;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Breno
 */

@Getter
@Setter
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
    
    //Representa o endereco do responsável legal
    private Endereco endereco;
    
    //Representa os outros dados pessoais do responsável legal
    private Long responsavelId;
    private String responsavelLegalDocumentoSocial;
    private String responsavelLegalParentesco;
    private String responsavelLegalEstadoCivil; 
    private String responsavelLegalTelefone1;
    private String responsavelLegalTelefone2;
    private String responsavelLegalProfissao;
    private String responsavelLegalNacionalidade;
    
    
    //Representa os dados da primeira testemunha
    private String testemunha1Nome;
    private String testemunha1CPF;
    
    //Representa os dados da primeira testemunha
    private String testemunha2Nome;
    private String testemunha2CPF;

}
