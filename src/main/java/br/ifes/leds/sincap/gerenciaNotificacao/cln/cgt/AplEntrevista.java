/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt;

import br.ifes.leds.reuse.utility.Utility;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.DTO.EntrevistaDTO;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.ProcessoNotificacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Responsavel;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Testemunha;
import java.text.ParseException;
import java.util.Calendar;

/**
 * Classe que gera o DTO e administra os dados da Entrevista do Processo de Doacao
 * @author 20112bsi0083
 */
public class AplEntrevista {
    
    //Utilizado para pegar os dados salvos referentes ao processo de doação
    AplNotificacao aplNotificacao = new AplNotificacao();

    //
    Utility utility = Utility.getInstance();
    
    public EntrevistaDTO getEntrevista(Long processoNotificacaoId)
    {
        EntrevistaDTO dto = new EntrevistaDTO();
        ProcessoNotificacao processoNotificacao = aplNotificacao.getNotificacao(processoNotificacaoId);
        
        //Dados de cabecalho:
        dto.setProcessoNotificacaoId(processoNotificacaoId);
        dto.setProcessoNotificacaoCodigo(processoNotificacao.getCodigo());
        dto.setPacienteNome(processoNotificacao.getObito().getPaciente().getNome());
        
        //Dados que necessitam de entrevista realizada
        if(processoNotificacao.getEntrevista() != null)
            entrevistaExistente(dto, processoNotificacao);
        else
            dto.setEntrevistaRealizada(utility.getLongBoolean(false));
        
        return dto;
    }
    
    private void entrevistaExistente(EntrevistaDTO dto, ProcessoNotificacao processoNotificacao)
    {
        //Dados booleanos
        Long doacaoAutorizada = utility.getLongBoolean(processoNotificacao.getEntrevista().isDoacaoAutorizada());
        dto.setDoacaoAutorizada(doacaoAutorizada);
        Long entrevistaRealizada = utility.getLongBoolean(true);
        dto.setEntrevistaRealizada(entrevistaRealizada);
        
        //Dados temporais
        fillDataHora(processoNotificacao, dto);
        
        //Dados do responsável legal:
        Responsavel rl = processoNotificacao.getEntrevista().getResponsavel();
        
        dto.setEndereco(rl.getEndereco());
        
        dto.setResponsavelId(rl.getId());
        dto.setResponsavelLegalDocumentoSocial(rl.getDocumentoSocial());
        dto.setResponsavelLegalEstadoCivil(rl.getEstadoCivil().toString());
        dto.setResponsavelLegalNacionalidade(rl.getNacionalidade());
        dto.setResponsavelLegalParentesco(rl.getParentesco().toString());
        dto.setResponsavelLegalProfissao(rl.getProfissao());
        dto.setResponsavelLegalTelefone1(rl.getTelefone().getNumero());
        dto.setResponsavelLegalTelefone2(rl.getTelefone2().getNumero());
        
        //Dados da primeira testemunha
        Testemunha testemunha1 = processoNotificacao.getEntrevista().getTestemunha1();
        dto.setTestemunha1CPF(testemunha1.getCpf());
        dto.setTestemunha1Nome(testemunha1.getNome());
        
        //Dados da segunda testemunha
        Testemunha testemunha2 = processoNotificacao.getEntrevista().getTestemunha2();
        dto.setTestemunha1CPF(testemunha2.getCpf());
        dto.setTestemunha1Nome(testemunha2.getNome());
    }
    
    private void fillDataHora(ProcessoNotificacao processoNotificacao, EntrevistaDTO dto)
    {
        String data = "", hora = "";
        utility.calendarToString(processoNotificacao.getEntrevista().getDataEntrevista(), data, hora);
        dto.setDataEntrevista(data);
        dto.setHoraEntrevista(hora);
    }
    
    
    //Definir entrevista a partir de um DTO
    private void setDataHora(ProcessoNotificacao processoNotificacao, EntrevistaDTO dto) throws ParseException
    {
        Calendar dataEntrevista = Calendar.getInstance();
        utility.stringToCalendar(dto.getDataEntrevista(), dto.getHoraEntrevista(), dataEntrevista);
        processoNotificacao.getEntrevista().setDataEntrevista(dataEntrevista);
    }
}
