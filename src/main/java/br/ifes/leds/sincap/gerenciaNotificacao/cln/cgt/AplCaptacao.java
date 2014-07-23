package br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt;

import br.ifes.leds.reuse.utility.Utility;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.CaptacaoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Captacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto.CaptacaoDTO;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe que gera o DTO e administra os dados da Captacao do Processo de
 * Doacao
 *
 * @author 20112bsi0553
 */
@Service
public class AplCaptacao {
    @Autowired
    private Utility utility;
    @Autowired
    private Mapper mapper;
    @Autowired
    private CaptacaoRepository captacaoRepository;

    /**
     * Metodo que retorna uma lista com todas as captacaoDTOs
     *
     * @return
     */
    public List<CaptacaoDTO> obterTodos() {
        return utility.mapList(captacaoRepository.findAll(), CaptacaoDTO.class);
    }

    /**
     * Dado um id, retorna um CaptacaoDTO
     *
     * @param id - Id para buscar CaptacaoDTO
     * @return
     */
    public CaptacaoDTO obter(Long id) {
        Captacao captacao = captacaoRepository.findOne(id);
        return mapper.map(captacao, CaptacaoDTO.class);
    }

    /**
     * Salvar Captacao, dado um CaptacaoDTO
     *
     * @param captacaoDTO
     * @return
     */
    public Long salvarCaptacao(CaptacaoDTO captacaoDTO) {
        Captacao captacao = mapper.map(captacaoDTO, Captacao.class);
        return this.salvarCaptacao(captacao);
    }

    /**
     * Salva uma Captacao
     *
     * @param captacao
     * @return
     */
    public Long salvarCaptacao(Captacao captacao) {
        captacaoRepository.save(captacao);
        return captacao.getId();
    }
}
