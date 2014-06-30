/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt;

import br.ifes.leds.reuse.ledsExceptions.CRUDExceptions.ViolacaoDeRIException;
import br.ifes.leds.reuse.utility.Utility;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.EntrevistaRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ResponsavelRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.TestemunhaRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.DTO.EntrevistaDTO;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Entrevista;
import java.util.List;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe que gera o DTO e administra os dados da Entrevista do Processo de
 * Doacao
 *
 * @author 20112bsi0083
 */
@Service
public class AplEntrevista {

    Utility utility = Utility.INSTANCE;

    @Autowired
    private Mapper mapper;
    @Autowired
    EntrevistaRepository entrevistaRepository;
    @Autowired
    ResponsavelRepository responsavelRepository;
    @Autowired
    TestemunhaRepository testemunhaRepository;
    
    public List<EntrevistaDTO> getAllEntrevistas()
    {
        return utility.mapList(entrevistaRepository.findAll(), EntrevistaDTO.class);
    }
    
    public EntrevistaDTO getEntrevista(Long id) {
        Entrevista entrevista = entrevistaRepository.findOne(id);
        return mapper.map(entrevista, EntrevistaDTO.class);
    }
    
    public void setEntrevista(EntrevistaDTO entrevistaDTO) throws ViolacaoDeRIException
    {
        Entrevista entrevista = mapper.map(entrevistaDTO, Entrevista.class);
        setEntrevista(entrevista);
    }
    
    public void setEntrevista(Entrevista entrevista) throws ViolacaoDeRIException
    {
        if (entrevista.isDoacaoAutorizada()) {
            if (entrevistaValida(entrevista)) {
                responsavelRepository.save(entrevista.getResponsavel());
                testemunhaRepository.save(entrevista.getTestemunha1());
                testemunhaRepository.save(entrevista.getTestemunha2());
                //TODO: Validar todos os dados cadastrais
            }
            else {
                throw new ViolacaoDeRIException("Dados cadastrais de Responsável ou Testemunha(s) estão nulos!");
            }
        }
        entrevistaRepository.save(entrevista);
    }
    
    private boolean entrevistaValida(Entrevista entrevista) {
        boolean b;
        b = (entrevista.getResponsavel() != null);
        b = b && (entrevista.getTestemunha1()!= null);
        b = b && (entrevista.getTestemunha2()!= null);
        return b;
    }
}
