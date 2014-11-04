package br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt;

import br.ifes.leds.sincap.controleInterno.cgd.HospitalRepository;
import br.ifes.leds.sincap.controleInterno.cgd.InstituicaoNotificadoraRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Instituicao;
import br.ifes.leds.sincap.controleInterno.cln.cdp.InstituicaoNotificadora;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ProcessoNotificacaoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.relatorios.QualificacaoRecusaFamiliar;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.relatorios.TotalDoacaoInstituicao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.List;

/**
 * Created by aleao on 21/10/14.
 */
@Service
public class AplRelatorio {

    @Autowired
    ProcessoNotificacaoRepository processoNotificacaoRepository;
    @Autowired
    InstituicaoNotificadoraRepository instituicaoNotificadoraRepository;



    /**
     * Esta função é responsável por buscar a quantidade de notificações
     * de uma instituição notificadora através do seu id.
     * @param id - da instituição notificadora.
     * @param datIni - Data Incial de abertura.
     * @param datFim - Data final de abertura.
     * @return quantidadeNotificacoes.
     */
    private Integer quantidadeNotificacoes(Long id,Calendar datIni, Calendar datFim){
        return processoNotificacaoRepository.countByDataAberturaBetweenAndObitoHospitalId(datIni, datFim, id);
    }

    /**
     * Esta função é responsável por buscar a quantidade de entrevistas
     * de uma instituição notificadora através do seu id.
     * @param id - da instituição notificadora.
     * @param datIni - Data Incial de abertura.
     * @param datFim - Data final de abertura.
     * @return quantidadeNotificacoes.
     */
    private Integer quantidadeEntrevista(Long id,Calendar datIni, Calendar datFim){
        return processoNotificacaoRepository.countByDataAberturaBetweenAndObitoHospitalIdAndEntrevistaIsNotNullAndEntrevistaEntrevistaRealizadaTrue(datIni, datFim, id);
    }

    /**
     * Esta função é responsável por buscar a quantidade de recusas
     * de uma instituição notificadora através do seu id.
     * @param id - da instituição notificadora.
     * @param datIni - Data Incial de abertura.
     * @param datFim - Data final de abertura.
     * @return quantidadeNotificacoes.
     */
    private Integer quantidadeRecusa(Long id,Calendar datIni, Calendar datFim){
        return processoNotificacaoRepository.countByDataAberturaBetweenAndObitoHospitalIdAndEntrevistaIsNotNullAndEntrevistaEntrevistaRealizadaTrueAndEntrevistaDoacaoAutorizadaFalse(datIni, datFim, id);
    }

    /**
     * Esta função é responsável por buscar a quantidade de doações(captações realizadas)
     * de uma instituição notificadora através do seu id.
     * @param id - da instituição notificadora.
     * @param datIni - Data Incial de abertura.
     * @param datFim - Data final de abertura.
     * @return quantidadeNotificacoes.
     */
    private Integer quantidadeDoacao(Long id,Calendar datIni, Calendar datFim){
        return processoNotificacaoRepository.countByDataAberturaBetweenAndObitoHospitalIdAndEntrevistaIsNotNullAndEntrevistaEntrevistaRealizadaTrueAndEntrevistaDoacaoAutorizadaTrueAndCaptacaoCaptacaoRealizadaTrue(datIni, datFim, id);
    }

    /**
     * Esta função é responsável por realizar o cálculo do percentual de efetivação.
     * Esta recebe como parametro o id da instituição,
     * o nº total de captações realizadas multiplica por 100
     * e divide pelo total de notificações da instuição.
     * @return quantidadeNotificacoes.
     */
    private BigDecimal percentualEfetivacao(Integer totalCaptacao, Integer totalDoacao){

        BigDecimal bTotalCaptacao = new BigDecimal(totalCaptacao*100);
        BigDecimal bTotalDoacao = new BigDecimal(totalDoacao);

        return bTotalCaptacao.divide(bTotalDoacao,2, RoundingMode.UP);
    }

    /**
     * Esta função é responsável por preencher um objeto do relatório de TotalDoacaoInstituição.
     * @param id - da instituição notificadora.
     * @param datIni - Data Incial de abertura.
     * @param datFim - Data Final de abertura.
     * @return td - Obejto TotalDoacaoInstituicao.
     */

    public TotalDoacaoInstituicao relatorioTotalDoacaoInstituicao(Long id,Calendar datIni,Calendar datFim){

        InstituicaoNotificadora in = instituicaoNotificadoraRepository.findOne(id);
        TotalDoacaoInstituicao td = new TotalDoacaoInstituicao();

        td.setNomeInstituicao(in.getNome());
        td.setNumeroNotificacao(quantidadeNotificacoes(id,datIni,datFim));
        td.setNumeroDoacao(quantidadeDoacao(id,datIni,datFim));
        td.setNumeroEntrevista(quantidadeEntrevista(id,datIni,datFim));
        td.setNumeroRecusa(quantidadeRecusa(id,datIni,datFim));

        if(td.getNumeroDoacao()==0 || td.getNumeroNotificacao()==0 ){
            td.setPercentualEfetivacao(new BigDecimal(0.00));
        }
        else{
            td.setPercentualEfetivacao(percentualEfetivacao(td.getNumeroDoacao(),td.getNumeroNotificacao()));
        }
        return td;
    }
    /**
     * Esta função é responsável por preencher o relatório de QualificacaoRecusaFamiliar.
     * @param datIni - Data Incial de abertura.
     * @param datFim - Data Final de abertura.
     * @return lista de Obejtos QualificacaoRecusaFamiliar.
     */
    public List<QualificacaoRecusaFamiliar> relatorioQualificacaoRecusa(Calendar datIni,Calendar datFim,List<Long> id){
        return processoNotificacaoRepository.getRelatorioQualificacaoRecusaFamiliar(datIni,datFim,id);
    }
}
