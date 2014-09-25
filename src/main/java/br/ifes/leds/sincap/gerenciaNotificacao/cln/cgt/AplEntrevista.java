/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt;

import br.ifes.leds.reuse.ledsExceptions.CRUDExceptions.ViolacaoDeRIException;
import br.ifes.leds.reuse.utility.Utility;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.EntrevistaRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Entrevista;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto.EntrevistaDTO;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe que gera o DTO e administra os dados da Entrevista do Processo de
 * Doacao
 *
 * @author 20112bsi0083
 */
@Service
public class AplEntrevista {

    @Autowired
    private Utility utility;
    @Qualifier("mapper")
    @Autowired
    private Mapper mapper;
    @Autowired
    private EntrevistaRepository entrevistaRepository;

    public List<EntrevistaDTO> getAllEntrevistas() {
        return utility.mapList(entrevistaRepository.findAll(), EntrevistaDTO.class);
    }

    @SuppressWarnings("unused")
    public EntrevistaDTO getEntrevista(Long id) {
        Entrevista entrevista = entrevistaRepository.findOne(id);
        return mapper.map(entrevista, EntrevistaDTO.class);
    }

    public void salvarEntrevista(EntrevistaDTO entrevistaDTO) throws ViolacaoDeRIException {
        salvarEntrevista(mapper.map(entrevistaDTO, Entrevista.class));
    }

    public void salvarEntrevista(Entrevista entrevista)  {
        entrevistaRepository.save(entrevista);
    }

}
