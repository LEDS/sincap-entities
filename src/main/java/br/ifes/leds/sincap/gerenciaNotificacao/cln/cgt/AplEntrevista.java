/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt;

import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifes.leds.reuse.endereco.cgd.EnderecoRepository;
import br.ifes.leds.reuse.ledsExceptions.CRUDExceptions.ViolacaoDeRIException;
import br.ifes.leds.reuse.utility.Utility;
import br.ifes.leds.sincap.controleInterno.cgd.TelefoneRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.EntrevistaRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ResponsavelRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.TestemunhaRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Entrevista;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.DTO.EntrevistaDTO;

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
    @Autowired
    private Mapper mapper;
    @Autowired
    EntrevistaRepository entrevistaRepository;
    @Autowired
    ResponsavelRepository responsavelRepository;
    @Autowired
    TestemunhaRepository testemunhaRepository;
    @Autowired
    TelefoneRepository telefoneRepository;
    @Autowired
    EnderecoRepository enderecoRepository;

    public List<EntrevistaDTO> getAllEntrevistas() {
        return utility.mapList(entrevistaRepository.findAll(), EntrevistaDTO.class);
    }

    public EntrevistaDTO getEntrevista(Long id) {
        Entrevista entrevista = entrevistaRepository.findOne(id);
        return mapper.map(entrevista, EntrevistaDTO.class);
    }

    public void salvarEntrevista(EntrevistaDTO entrevistaDTO) throws ViolacaoDeRIException {
        Entrevista entrevista = mapper.map(entrevistaDTO, Entrevista.class);
        salvarEntrevista(entrevista);
    }

    public void salvarEntrevista(Entrevista entrevista) throws ViolacaoDeRIException {
        if (entrevista.isDoacaoAutorizada()) {
            if (entrevistaValida(entrevista)) {
                
                enderecoRepository.save(entrevista.getResponsavel().getEndereco());
                telefoneRepository.save(entrevista.getResponsavel().getTelefone());
                if(entrevista.getResponsavel().getTelefone2() != null)
                    telefoneRepository.save(entrevista.getResponsavel().getTelefone2());
                responsavelRepository.save(entrevista.getResponsavel());
                
                telefoneRepository.save(entrevista.getTestemunha1().getTelefone());
                testemunhaRepository.save(entrevista.getTestemunha1());
                
                telefoneRepository.save(entrevista.getTestemunha2().getTelefone());
                testemunhaRepository.save(entrevista.getTestemunha2());
                // TODO: Validar todos os dados cadastrais
            } else {
                throw new ViolacaoDeRIException("Dados cadastrais de Responsável ou Testemunha(s) estão nulos!");
            }
        }
        entrevistaRepository.save(entrevista);
    }

    private boolean entrevistaValida(Entrevista entrevista) {
        boolean b;
        b = (entrevista.getResponsavel() != null);
        b = b && (entrevista.getTestemunha1() != null);
        b = b && (entrevista.getTestemunha2() != null);
        return b;
    }

    public void salvarEntrevistaEntrevista(EntrevistaDTO entrevistaDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
