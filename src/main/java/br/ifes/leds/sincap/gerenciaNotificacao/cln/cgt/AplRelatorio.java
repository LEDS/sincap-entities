package br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt;

import br.ifes.leds.sincap.controleInterno.cgd.InstituicaoNotificadoraRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.InstituicaoNotificadora;
import br.ifes.leds.sincap.controleInterno.cln.cgt.AplHospital;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ObitoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ProcessoNotificacaoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoNaoDoacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoObito;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.relatorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import static br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoNaoDoacao.*;

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


    private Integer quantidadeNotificacoesPCR(Long id,Calendar datIni, Calendar datFim,TipoObito tipo){
        return processoNotificacaoRepository.countByDataAberturaBetweenAndObitoHospitalIdAndObitoTipoObito(datIni, datFim, id, tipo);
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
     * Esta função é responsável por buscar a quantidade de entrevistas realizadas com tipo de obito PCR
     * de uma instituição notificadora através do seu id.
     * @param id - da instituição notificadora.
     * @param datIni - Data Incial de abertura.
     * @param datFim - Data final de abertura.
     * @return quantidadeNotificacoes.
     */
    private Integer quantidadeEntrevistaRealizadaPCR(Long id,Calendar datIni, Calendar datFim,TipoObito tipo){
        return processoNotificacaoRepository.countByDataAberturaBetweenAndObitoHospitalIdAndEntrevistaIsNotNullAndEntrevistaEntrevistaRealizadaTrueAndObitoTipoObito(datIni, datFim, id, tipo);
    }

    /**
     * Esta função é responsável por buscar a quantidade de entrevistas  não realizadas
     * de uma instituição notificadora através do seu id.
     * @param id - da instituição notificadora.
     * @param datIni - Data Incial de abertura.
     * @param datFim - Data final de abertura.
     * @return quantidadeNotificacoes.
     */
    private Integer quantidadeEntrevistaNaoRealizada(Long id,Calendar datIni, Calendar datFim){
        return processoNotificacaoRepository.countByDataAberturaBetweenAndObitoHospitalIdAndEntrevistaIsNullAndEntrevistaEntrevistaRealizadaFalseAndEntrevistaDoacaoAutorizadaFalse(datIni, datFim, id);
    }

    /**
     * Esta função é responsável por buscar a quantidade de entrevistas  não realizadas
     * de uma instituição notificadora através do seu id.
     * @param id - da instituição notificadora.
     * @param datIni - Data Incial de abertura.
     * @param datFim - Data final de abertura.
     * @return quantidadeNotificacoes.
     */
    private Integer quantidadeEntrevistaNaoRealizadaPCR(Long id,Calendar datIni, Calendar datFim,TipoObito tipo){
        return processoNotificacaoRepository.countByDataAberturaBetweenAndObitoHospitalIdAndEntrevistaIsNullAndEntrevistaEntrevistaRealizadaFalseAndEntrevistaDoacaoAutorizadaFalseAndObitoTipoObito(datIni, datFim, id, tipo);
    }

    /**
     * Esta função é responsável por buscar a quantidade de entrevistas  autorizadas por consentimento familiar e tipo de obito PCR
     * @param id - da instituição notificadora.
     * @param datIni - Data Incial de abertura.
     * @param datFim - Data final de abertura.
     * @return quantidadeNotificacoes.
     */
    private Integer quantidadeConsentimentoFamiliarPCR(Long id,Calendar datIni, Calendar datFim,TipoObito tipo){
        return processoNotificacaoRepository.countByDataAberturaBetweenAndObitoHospitalIdAndEntrevistaIsNotNullAndEntrevistaEntrevistaRealizadaTrueAndEntrevistaDoacaoAutorizadaTrueAndObitoTipoObito(datIni, datFim, id, tipo);
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
     * Esta função é responsável por buscar a quantidade de recusas
     * de uma instituição notificadora através do seu id.
     * @param id - da instituição notificadora.
     * @param datIni - Data Incial de abertura.
     * @param datFim - Data final de abertura.
     * @return quantidadeNotificacoes.
     */
    private Integer quantidadeRecusaPCR(Long id,Calendar datIni, Calendar datFim,TipoObito tipo){
        return processoNotificacaoRepository.countByDataAberturaBetweenAndObitoHospitalIdAndEntrevistaIsNotNullAndEntrevistaEntrevistaRealizadaTrueAndEntrevistaDoacaoAutorizadaFalseAndObitoTipoObito(datIni, datFim, id, tipo);
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

        return bTotalCaptacao.divide(bTotalDoacao, 2, RoundingMode.UP);
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

    public List<QualificacaoRecusaFamiliar> relatorioQualificacaoRecusa(Calendar datIni, Calendar datFim, Long id) {
        final List<Long> ids = new ArrayList<>();
        ids.add(id);
        return processoNotificacaoRepository.getRelatorioQualificacaoRecusaFamiliar(datIni, datFim, ids);
    }

    public <E extends InstituicaoNotificadora> List<QualificacaoRecusaFamiliar> relatorioQualificacaoRecusa(Calendar datIni, Calendar datFim, Collection<E> hospitais) {
        final List<Long> ids = new ArrayList<>();

        for (E hospital : hospitais) {
            ids.add(hospital.getId());
        }

        return processoNotificacaoRepository.getRelatorioQualificacaoRecusaFamiliar(datIni, datFim, ids);
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
        Long totalObitoPCR = quantidadeObitoPCR(0,200,listaHospital,datIni,datFim);
        Long totalObitoME = quantidadeObitoME(0, 200, listaHospital, datIni, datFim);

        menorDois.setFaixa("Menor de 2 anos");
        menorDois.setTotalPCR(quantidadeObitoPCR(0, 2, listaHospital, datIni, datFim));
        menorDois.setTotalME(quantidadeObitoME(0, 2, listaHospital, datIni, datFim));
        menorDois.setPercentualPCR(calculaPercentual(totalObitoPCR, menorDois.getTotalPCR()));
        menorDois.setPercentualME(calculaPercentual(totalObitoME, menorDois.getTotalME()));
        listaFaixa.add(menorDois);


        doisDezoito.setFaixa("Entre 2 e 18 anos");
        doisDezoito.setTotalPCR(quantidadeObitoPCR(2, 17, listaHospital, datIni, datFim));
        doisDezoito.setTotalME(quantidadeObitoME(2, 17, listaHospital, datIni, datFim));
        doisDezoito.setPercentualPCR(calculaPercentual(totalObitoPCR, doisDezoito.getTotalPCR()));
        doisDezoito.setPercentualME(calculaPercentual(totalObitoME, doisDezoito.getTotalME()));
        listaFaixa.add(doisDezoito);

        dezoitoQuarenta.setFaixa("Entre 18 e 40 anos");
        dezoitoQuarenta.setTotalPCR(quantidadeObitoPCR(18, 39, listaHospital, datIni, datFim));
        dezoitoQuarenta.setTotalME(quantidadeObitoME(18, 39, listaHospital, datIni, datFim));
        dezoitoQuarenta.setPercentualPCR(calculaPercentual(totalObitoPCR, dezoitoQuarenta.getTotalPCR()));
        dezoitoQuarenta.setPercentualME(calculaPercentual(totalObitoME, dezoitoQuarenta.getTotalME()));
        listaFaixa.add(dezoitoQuarenta);

        quarentaSessenta.setFaixa("Entre 40 e 60 anos");
        quarentaSessenta.setTotalPCR(quantidadeObitoPCR(40, 59, listaHospital, datIni, datFim));
        quarentaSessenta.setTotalME(quantidadeObitoME(40, 59, listaHospital, datIni, datFim));
        quarentaSessenta.setPercentualPCR(calculaPercentual(totalObitoPCR, quarentaSessenta.getTotalPCR()));
        quarentaSessenta.setPercentualME(calculaPercentual(totalObitoME, quarentaSessenta.getTotalME()));
        listaFaixa.add(quarentaSessenta);

        SessentaSetenta.setFaixa("Entre 60 e 70 anos");
        SessentaSetenta.setTotalPCR(quantidadeObitoPCR(60, 69, listaHospital, datIni, datFim));
        SessentaSetenta.setTotalME(quantidadeObitoME(60, 69, listaHospital, datIni, datFim));
        SessentaSetenta.setPercentualPCR(calculaPercentual(totalObitoPCR, SessentaSetenta.getTotalPCR()));
        SessentaSetenta.setPercentualME(calculaPercentual(totalObitoME, SessentaSetenta.getTotalME()));
        listaFaixa.add(SessentaSetenta);

        maiorSetenta.setFaixa("Maior de 70 anos");
        maiorSetenta.setTotalPCR(quantidadeObitoPCR(70, 200, listaHospital, datIni, datFim));
        maiorSetenta.setTotalME(quantidadeObitoME(70, 200, listaHospital, datIni, datFim));
        maiorSetenta.setPercentualPCR(calculaPercentual(totalObitoPCR, maiorSetenta.getTotalPCR()));
        maiorSetenta.setPercentualME(calculaPercentual(totalObitoME, maiorSetenta.getTotalME()));
        listaFaixa.add(maiorSetenta);

        return listaFaixa;
    }

    /**
     * Esta função é responsável por realizar o cálculo do percentual genérico.
     * @param qtdTotal - denominador.
     * @param  qtdParcial - numerador.
     * @return percentual de entrevistas realizadas.
     */
    private BigDecimal calculaPercentual(Integer qtdTotal, Integer qtdParcial){

        BigDecimal bQtdParcial = new BigDecimal(qtdParcial*100);
        BigDecimal bQtdTotal = new BigDecimal(qtdTotal);

        if (qtdTotal == 0){
            return bQtdTotal;
        } else {
            return bQtdParcial.divide(bQtdTotal,2, RoundingMode.UP);
        }

    }

    /**
     * Esta função é responsável por realizar o cálculo do percentual genérico.
     * @param qtdTotal - denominador.
     * @param  qtdParcial - numerador.
     * @return percentual de entrevistas realizadas.
     */
    private BigDecimal calculaPercentual(Long qtdTotal, Long qtdParcial){

        BigDecimal bQtdParcial = new BigDecimal(qtdParcial*100);
        BigDecimal bQtdTotal = new BigDecimal(qtdTotal);

        if (qtdTotal == 0){
            return bQtdTotal;
        } else {
            return bQtdParcial.divide(bQtdTotal,2, RoundingMode.UP);
        }

    }

    public List<ObitoCardio> retornaObitoCardio(Long idHospital,Calendar dataInicio,Calendar dataFinal){
        ObitoCardio totalObito = new ObitoCardio();
        ObitoCardio entrevistaRealizada = new ObitoCardio();
        ObitoCardio entrevistaNaoRealizada = new ObitoCardio();
        ObitoCardio consentimentoFamiliar = new ObitoCardio();
        ObitoCardio recusas = new ObitoCardio();
        List<ObitoCardio> liObito = new ArrayList<>();

        totalObito.setNome("Total de óbitos hospitalares");
        totalObito.setTotal(quantidadeNotificacoesPCR(idHospital, dataInicio, dataFinal, TipoObito.PCR));
        totalObito.setPercentual(calculaPercentual(100,100));
        liObito.add(totalObito);

        entrevistaRealizada.setNome("Entrevistas realizadas");
        entrevistaRealizada.setTotal(quantidadeEntrevistaRealizadaPCR(idHospital, dataInicio, dataFinal, TipoObito.PCR));
        entrevistaRealizada.setPercentual(calculaPercentual(totalObito.getTotal(),entrevistaRealizada.getTotal()));
        liObito.add(entrevistaRealizada);

        entrevistaNaoRealizada.setNome("Entrevistas não realizadas");
        entrevistaNaoRealizada.setTotal(quantidadeEntrevistaNaoRealizadaPCR(idHospital, dataInicio, dataFinal, TipoObito.PCR));
        entrevistaNaoRealizada.setPercentual(calculaPercentual(totalObito.getTotal(),entrevistaNaoRealizada.getTotal()));
        liObito.add(entrevistaNaoRealizada);

        consentimentoFamiliar.setNome("Consentimento Familiar");
        consentimentoFamiliar.setTotal(quantidadeConsentimentoFamiliarPCR(idHospital, dataInicio, dataFinal, TipoObito.PCR));
        consentimentoFamiliar.setPercentual(calculaPercentual(totalObito.getTotal(),consentimentoFamiliar.getTotal()));
        liObito.add(consentimentoFamiliar);

        recusas.setNome("Recusas");
        recusas.setTotal(quantidadeRecusaPCR(idHospital, dataInicio, dataFinal, TipoObito.PCR));
        recusas.setPercentual(calculaPercentual(totalObito.getTotal(),recusas.getTotal()));
        liObito.add(recusas);

        return liObito;
    }

    /**
     * Esta função é responsável por preencher a lista de Obitos por ME por turno.
     * @param idHospital - Id do Hospital.
     * @param  dataInicio - Data inicial.
     * @param  dataFinal - Data Final.
     * @return lista de ObitosMETurno.
     */

    public List<ObitosMeTurno> retornaObitosMeTurno(Long idHospital,Calendar dataInicio,Calendar dataFinal){
        List<ObitosMeTurno> listObitoMe = new ArrayList<>();
        ObitosMeTurno obitosManha = new ObitosMeTurno();
        ObitosMeTurno obitosTarde = new ObitosMeTurno();
        ObitosMeTurno obitosNoite = new ObitosMeTurno();

        obitosManha.setTurno("Manha");
        obitosManha.setQuantidade(obitoRepository.getObitosMEPorTurno(dataInicio, dataFinal, idHospital, TipoObito.ME,7,12));
        listObitoMe.add(obitosManha);

        obitosTarde.setTurno("Tarde");
        obitosTarde.setQuantidade(obitoRepository.getObitosMEPorTurno(dataInicio, dataFinal, idHospital, TipoObito.ME,13,18));
        listObitoMe.add(obitosTarde);

        obitosNoite.setTurno("Noite");
        obitosNoite.setQuantidade(obitoRepository.getObitosMEPorTurno(dataInicio, dataFinal, idHospital, TipoObito.ME,19,6));
        listObitoMe.add(obitosNoite);

        return listObitoMe;
    }
    public List<NaoDoacaoCIHDOTT> naoDoacaoMensal(Long idHospital,Calendar dataInicio,Calendar dataFinal,TipoNaoDoacao tipo)
    {
        List<NaoDoacaoCIHDOTT> naoDoacaoFamiliar = processoNotificacaoRepository.getNaoDoacaCIHDOTT(dataInicio,dataFinal,idHospital,tipo);

        return naoDoacaoFamiliar;
    }

  }
