package br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt;

import br.ifes.leds.sincap.controleInterno.cgd.HospitalRepository;
import br.ifes.leds.sincap.controleInterno.cgd.InstituicaoNotificadoraRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Instituicao;
import br.ifes.leds.sincap.controleInterno.cln.cdp.InstituicaoNotificadora;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ProcessoNotificacaoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.relatorios.TotalDoacaoInstituicao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.relatorios.TotalNaoDoacaoInstituicao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * Created by aleao on 21/10/14.
 */
@Service
public class AplRelatorio {

    @Autowired
    ProcessoNotificacaoRepository processoNotificacaoRepository;
    @Autowired
    InstituicaoNotificadoraRepository instituicaoNotificadoraRepository;



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
    private Double percentualEfetivacao(Integer totalCaptacao, Integer totalDoacao){

        return new Double((totalCaptacao*100)/totalDoacao);
    }

    /*
     * Esta função é responsável por preencher um objeto do relatório de TotalDoacaoInstituição.
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
            td.setPercentualEfetivacao(0.0);
        }
        else{
            td.setPercentualEfetivacao(percentualEfetivacao(td.getNumeroDoacao(),td.getNumeroNotificacao()));
        }
        return td;
    }

    public TotalNaoDoacaoInstituicao relatorioTotalNaoDoacaoInstituicao(Long id,Calendar dataInicio,Calendar dataFinal){

        InstituicaoNotificadora in = instituicaoNotificadoraRepository.findOne(id);
        TotalNaoDoacaoInstituicao td = new TotalNaoDoacaoInstituicao();

        td.setNome(in.getNome());

        td.setRecusaFamiliar(quantidadeNotificacoes(id,dataInicio,dataFinal));

        td.setContraInd(quantidadeDoacao(id,dataInicio,dataFinal));

        td.setProblema(quantidadeEntrevista(id,dataInicio,dataFinal));

        td.setTotal(td.getContraInd() + td.getProblema() + td.getRecusaFamiliar());


        return td;
    }
}
