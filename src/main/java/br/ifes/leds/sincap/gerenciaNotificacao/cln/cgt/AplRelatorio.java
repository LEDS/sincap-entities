package br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt;

import br.ifes.leds.sincap.controleInterno.cgd.HospitalRepository;
import br.ifes.leds.sincap.controleInterno.cgd.InstituicaoNotificadoraRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Instituicao;
import br.ifes.leds.sincap.controleInterno.cln.cdp.InstituicaoNotificadora;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ProcessoNotificacaoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CausaNaoDoacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoNaoDoacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.relatorios.QualificacaoRecusaFamiliar;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.relatorios.RelEntrevistaFamiliar;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.relatorios.TotalDoacaoInstituicao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.relatorios.TotalNaoDoacaoInstituicao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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


//Relatorio CIHDOTT

    private Integer quantRecFamiliar(Calendar DataInicio, Calendar DataFinal, Long idHops, CausaNaoDoacao causa)
    {
        return processoNotificacaoRepository.countByDataAberturaBetweenAndObitoHospitalIdAndCausaNaoDoacao(DataInicio, DataFinal, idHops, causa);
    }
   /* public RelEntrevistaFamiliar relatorioTotalEntrevistaFamiliar(Long id,Calendar dataInicio, Calendar dataFinal){

        InstituicaoNotificadora in = instituicaoNotificadoraRepository.findOne(id);
        TotalNaoDoacaoInstituicao td = new TotalNaoDoacaoInstituicao();

        RelEntrevistaFamiliar rel = new RelEntrevistaFamiliar();
        rel.setDesconhecimento(quantRecFamiliar(dataInicio, dataFinal, id,"Desconhecimento do desejo do potencial doador"));
        rel.setPotencial(quantRecFamiliar(dataInicio, dataFinal, id, "Doador contrário à doação em vida"));
        rel.setFamiliares(quantRecFamiliar(dataInicio, dataFinal, id, "Familiares indecisos"));
        rel.setFamiliaresCorpo(quantRecFamiliar(dataInicio, dataFinal, id, "'Familiares desejam o corpo íntegro'"));
        rel.setNaoEntendimento(quantRecFamiliar(dataInicio, dataFinal, id, "'Desconhecimento do desejo do potencial doador'"));
        rel.setFamiliaresDescontentes(quantRecFamiliar(dataInicio, dataFinal, id," 'Familiares descontentes com o atendimento'"));
        rel.setReceio(quantRecFamiliar(dataInicio, dataFinal, id, "'Receio de demora na liberação do corpo'"));
        rel.setReligiao(quantRecFamiliar(dataInicio, dataFinal, id," 'Convicções religiosas'"));
        rel.setOutros(quantRecFamiliar(dataInicio, dataFinal, id," 'Outros'"));


        td.setNome(in.getNome());

        td.setRecusaFamiliar(quantNaoDoacao(dataInicio, dataFinal, id, RECUSA_FAMILIAR));

        td.setContraInd(quantNaoDoacao(dataInicio, dataFinal, id, CONTRAINDICACAO_MEDICA));

        td.setProblema(quantNaoDoacao(dataInicio, dataFinal, id, PROBLEMAS_LOGISTICOS));

        td.setTotal(td.getContraInd() + td.getProblema() + td.getRecusaFamiliar());

        return td;
    }*/


}
