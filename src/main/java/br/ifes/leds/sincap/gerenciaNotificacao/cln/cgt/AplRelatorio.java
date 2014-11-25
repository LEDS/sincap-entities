package br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt;

import br.ifes.leds.sincap.controleInterno.cgd.HospitalRepository;
import br.ifes.leds.sincap.controleInterno.cgd.InstituicaoNotificadoraRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Hospital;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Instituicao;
import br.ifes.leds.sincap.controleInterno.cln.cdp.InstituicaoNotificadora;
import br.ifes.leds.sincap.controleInterno.cln.cgt.AplHospital;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ObitoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ProcessoNotificacaoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoNaoDoacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoObito;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.relatorios.FaixaEtaria;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.relatorios.QualificacaoRecusaFamiliar;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.relatorios.TotalDoacaoInstituicao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.relatorios.TotalNaoDoacaoInstituicao;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoNaoDoacao.CONTRAINDICACAO_MEDICA;
import static br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoNaoDoacao.PROBLEMAS_LOGISTICOS;
import static br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoNaoDoacao.RECUSA_FAMILIAR;

/**
 * Created by aleao on 21/10/14.
 */
@Service
public class AplRelatorio {

    @Autowired
    ProcessoNotificacaoRepository processoNotificacaoRepository;
    @Autowired
    InstituicaoNotificadoraRepository instituicaoNotificadoraRepository;
    @Autowired
    ObitoRepository obitoRepository;
    @Autowired
    AplHospital aplHospital;



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

    private Integer quantNaoDoacao(Calendar DataInicio, Calendar DataFinal, Long idHops, TipoNaoDoacao causa)
    {
        return processoNotificacaoRepository.countByDataAberturaBetweenAndObitoHospitalIdAndCausaNaoDoacaoTipoNaoDoacao(DataInicio, DataFinal, idHops, causa);
    }

    /*
     * Esta função é responsável por preencher um objeto do relatório de TotalDoacaoInstituição.
    */

    public TotalDoacaoInstituicao relatorioTotalDoacaoInstituicao(Long id,Calendar datIni,Calendar datFim){

        InstituicaoNotificadora in = instituicaoNotificadoraRepository.findOne(id);
        TotalDoacaoInstituicao td = new TotalDoacaoInstituicao();

        td.setNomeInstituicao(in.getNome());
        td.setNumeroNotificacao(quantidadeNotificacoes(id, datIni, datFim));
        td.setNumeroDoacao(quantidadeDoacao(id, datIni, datFim));
        td.setNumeroEntrevista(quantidadeEntrevista(id, datIni, datFim));
        td.setNumeroRecusa(quantidadeRecusa(id,datIni,datFim));

        if(td.getNumeroDoacao()==0 || td.getNumeroNotificacao()==0 ){
            td.setPercentualEfetivacao(new BigDecimal(0.00));
        }
        else{
            td.setPercentualEfetivacao(percentualEfetivacao(td.getNumeroDoacao(),td.getNumeroNotificacao()));
        }
        return td;
    }

    /*
     * Esta função é responsável por preencher um objeto do relatório de TotalNaoDoacaoInstituição.
    */
    public TotalNaoDoacaoInstituicao relatorioTotalNaoDoacaoInstituicao(Long id,Calendar dataInicio, Calendar dataFinal){

        InstituicaoNotificadora in = instituicaoNotificadoraRepository.findOne(id);
        TotalNaoDoacaoInstituicao td = new TotalNaoDoacaoInstituicao();

        td.setNome(in.getNome());

        td.setRecusaFamiliar(quantNaoDoacao(dataInicio, dataFinal, id, RECUSA_FAMILIAR));

        td.setContraInd(quantNaoDoacao(dataInicio, dataFinal, id, CONTRAINDICACAO_MEDICA));

        td.setProblema(quantNaoDoacao(dataInicio, dataFinal, id, PROBLEMAS_LOGISTICOS));

        td.setTotal(td.getContraInd() + td.getProblema() + td.getRecusaFamiliar());

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

    /**
     * Esta função é responsável por buscar a quantidade de pacientes que morreram
     * por PCR.
     * @param idadeIni - Idade inicial.
     * @param idadeIni- Idade Final.
     * @return Quantidade de pacientes na Faixa Etária e tipo de obitos escolhidos.
     */
    private Long quantidadeObitoPCR(Integer idadeIni,Integer idadeFim,Long idHospital,Calendar datIni,Calendar datFim){
        return obitoRepository.getFaixaEtaria(idadeIni,idadeFim,idHospital,0,datIni,datFim);
    }

    /**
     * Esta função é responsável por buscar a quantidade de pacientes que morreram
     * por ME.
     * @param idadeIni - Idade inicial.
     * @param idadeIni- Idade Final.
     * @return Quantidade de pacientes na Faixa Etária e tipo de obitos escolhidos.
     */
    private Long quantidadeObitoME(Integer idadeIni,Integer idadeFim,Long idHospital,Calendar datIni,Calendar datFim){
        return obitoRepository.getFaixaEtaria(idadeIni,idadeFim,idHospital,1,datIni,datFim);
    }

    /**
     * Esta função é responsável por realizar o cálculo do percentual de pacientes com
     * óbito por PCR.
     * Esta recebe como parametro o nº total de pacientes na faixa escolhida multiplica por 100
     * e divide pelo total pacientes com mortes PCR.
     * @return percentual de pacintes na faixa escolhida.
     */
    private BigDecimal percentualPCR(Long qtdPCR, Long totalPCR){

        BigDecimal bQtdPCR = new BigDecimal(qtdPCR*100);
        BigDecimal bTotalPCR = new BigDecimal(totalPCR);
        if (totalPCR ==0){
            return bTotalPCR;
        } else {
            return bQtdPCR.divide(bTotalPCR,2, RoundingMode.UP);
        }

    }

    /**
     * Esta função é responsável por realizar o cálculo do percentual de pacientes com
     * óbito por ME.
     * Esta recebe como parametro o nº total de pacientes na faixa escolhida multiplica por 100
     * e divide pelo total pacientes com mortes PCR.
     * @return percentual de pacintes na faixa escolhida.
     */
    private BigDecimal percentualME(Long qtdME, Long totalME){

        BigDecimal bQtdME = new BigDecimal(qtdME*100);
        BigDecimal bTotalME = new BigDecimal(totalME);

        if (totalME == 0){
            return bTotalME;
        } else {
            return bQtdME.divide(bTotalME,2, RoundingMode.UP);
        }

    }

    /**
     * Esta função é responsável por retornar uma lista com a quantidade de paciente por Faixa Etaria
     * e pelo tipo de óbito (PCR ou ME). As Faixa que serão listadas no relatório são:0 a 2 anos,
     * 2 a 18 anos,18 a 40 anos,40 a 60 anos,60 a 70 anos e maior de 70 anos.
     * @return Quantidade de pacientes na Faixa Etária e tipo de obitos escolhidos.
     */
    public List<FaixaEtaria> retornaFaixaEtaria(Long listaHospital,Calendar datIni, Calendar datFim){
        FaixaEtaria menorDois = new FaixaEtaria();
        FaixaEtaria doisDezoito = new FaixaEtaria();
        FaixaEtaria dezoitoQuarenta = new FaixaEtaria();
        FaixaEtaria quarentaSessenta = new FaixaEtaria();
        FaixaEtaria SessentaSetenta = new FaixaEtaria();
        FaixaEtaria maiorSetenta = new FaixaEtaria();
        List<FaixaEtaria> listaFaixa =  new ArrayList<>();
        Long totalObito = quantidadeObitoPCR(0,200,listaHospital,datIni,datFim);

        menorDois.setFaixa("Menor de 2 anos");
        menorDois.setTotalPCR(quantidadeObitoPCR(0, 2, listaHospital, datIni, datFim));
        menorDois.setTotalME(quantidadeObitoME(0, 2, listaHospital, datIni, datFim));
        menorDois.setPercentualPCR(percentualPCR(menorDois.getTotalPCR(), totalObito));
        menorDois.setPercentualME(percentualPCR(menorDois.getTotalME(), totalObito));
        listaFaixa.add(menorDois);


        doisDezoito.setFaixa("Entre 2 e 18 anos");
        doisDezoito.setTotalPCR(quantidadeObitoPCR(2, 18, listaHospital, datIni, datFim));
        doisDezoito.setTotalME(quantidadeObitoME(2, 18, listaHospital, datIni, datFim));
        doisDezoito.setPercentualPCR(percentualPCR(doisDezoito.getTotalPCR(), totalObito));
        doisDezoito.setPercentualME(percentualPCR(doisDezoito.getTotalME(), totalObito));
        listaFaixa.add(doisDezoito);

        dezoitoQuarenta.setFaixa("Entre 18 e 40 anos");
        dezoitoQuarenta.setTotalPCR(quantidadeObitoPCR(18, 40, listaHospital, datIni, datFim));
        dezoitoQuarenta.setTotalME(quantidadeObitoME(18, 40, listaHospital, datIni, datFim));
        dezoitoQuarenta.setPercentualPCR(percentualPCR(dezoitoQuarenta.getTotalPCR(), totalObito));
        dezoitoQuarenta.setPercentualME(percentualPCR(dezoitoQuarenta.getTotalME(), totalObito));
        listaFaixa.add(dezoitoQuarenta);

        quarentaSessenta.setFaixa("Entre 40 e 60 anos");
        quarentaSessenta.setTotalPCR(quantidadeObitoPCR(40, 60, listaHospital, datIni, datFim));
        quarentaSessenta.setTotalME(quantidadeObitoME(40, 60, listaHospital, datIni, datFim));
        quarentaSessenta.setPercentualPCR(percentualPCR(quarentaSessenta.getTotalPCR(), totalObito));
        quarentaSessenta.setPercentualME(percentualPCR(quarentaSessenta.getTotalME(), totalObito));
        listaFaixa.add(quarentaSessenta);

        SessentaSetenta.setFaixa("Entre 60 e 70 anos");
        SessentaSetenta.setTotalPCR(quantidadeObitoPCR(60, 70, listaHospital, datIni, datFim));
        SessentaSetenta.setTotalME(quantidadeObitoME(60, 70, listaHospital, datIni, datFim));
        SessentaSetenta.setPercentualPCR(percentualPCR(SessentaSetenta.getTotalPCR(), totalObito));
        SessentaSetenta.setPercentualME(percentualPCR(SessentaSetenta.getTotalME(), totalObito));
        listaFaixa.add(SessentaSetenta);

        maiorSetenta.setFaixa("Maior de 70 anos");
        maiorSetenta.setTotalPCR(quantidadeObitoPCR(70, 200, listaHospital, datIni, datFim));
        maiorSetenta.setTotalME(quantidadeObitoME(70, 200, listaHospital, datIni, datFim));
        maiorSetenta.setPercentualPCR(percentualPCR(maiorSetenta.getTotalPCR(), totalObito));
        maiorSetenta.setPercentualME(percentualPCR(maiorSetenta.getTotalME(), totalObito));
        listaFaixa.add(maiorSetenta);

        return listaFaixa;
    }
}
