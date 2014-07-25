/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt;

import br.ifes.leds.reuse.endereco.cgd.EnderecoRepository;
import br.ifes.leds.reuse.ledsExceptions.CRUDExceptions.ViolacaoDeRIException;
import br.ifes.leds.reuse.utility.Utility;
import br.ifes.leds.sincap.controleInterno.cgd.TelefoneRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.EntrevistaRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ResponsavelRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.TestemunhaRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto.EntrevistaDTO;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Entrevista;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private Mapper mapper;
    @Autowired
    private EntrevistaRepository entrevistaRepository;
    @Autowired
    private ResponsavelRepository responsavelRepository;
    @Autowired
    private TestemunhaRepository testemunhaRepository;
    @Autowired
    private TelefoneRepository telefoneRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

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
                if (entrevista.getResponsavel().getTelefone2() != null)
                    telefoneRepository.save(entrevista.getResponsavel().getTelefone2());
                responsavelRepository.save(entrevista.getResponsavel());

                setUpEntrevista(entrevista);

                testemunhaRepository.save(entrevista.getTestemunha1());
                testemunhaRepository.save(entrevista.getTestemunha2());
                // TODO: Validar todos os dados cadastrais
            } else {
                throw new ViolacaoDeRIException("Dados cadastrais de Responsável ou Testemunha(s) estão nulos!");
            }
        } else {
            entrevista.setResponsavel(null);
            entrevista.setTestemunha1(null);
            entrevista.setTestemunha2(null);
        }
        entrevistaRepository.save(entrevista);
    }

    private void setUpEntrevista(Entrevista entrevista) {
        entrevista.getTestemunha1().setEndereco(null);
        entrevista.getTestemunha1().setTelefone(null);
        entrevista.getTestemunha2().setEndereco(null);
        entrevista.getTestemunha2().setTelefone(null);
    }

    private boolean entrevistaValida(Entrevista entrevista) {
        boolean b;
        b = (entrevista.getResponsavel() != null);
        b = b && (entrevista.getTestemunha1() != null);
        b = b && (entrevista.getTestemunha2() != null);
        return b;
    }
}
