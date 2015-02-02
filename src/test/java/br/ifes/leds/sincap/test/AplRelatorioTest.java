package br.ifes.leds.sincap.test;

import br.ifes.leds.sincap.controleInterno.cln.cdp.Hospital;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.ProcessoNotificacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.relatorios.QualificacaoRecusaFamiliar;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.relatorios.TotalDoacaoInstituicao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt.AplProcessoNotificacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt.AplRelatorio;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.util.dataFactory.HospitalData;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.util.dataFactory.ProcessoNotificacaoData;
import junit.framework.Assert;
import org.fluttercode.datafactory.impl.DataFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static br.ifes.leds.reuse.utility.Factory.criaObjeto;

public class AplRelatorioTest extends AbstractionTest {

    @Autowired
    private AplRelatorio aplRelatorio;
    @Autowired
    AplProcessoNotificacao aplProcessoNotificacao;
    @Autowired
    HospitalData hospitalData;
    @Autowired
    ProcessoNotificacaoData processoNotificacaoData;

    private TotalDoacaoInstituicao totalDoacaoInstituicao;
    private List<ProcessoNotificacao> listProcessoNotificacao;


    private DataFactory df;

    private Calendar datIni;
    private Calendar datFim;
    private Hospital hospital;


    @Before
    public void before() {
        this.df = criaObjeto(DataFactory.class);
        this.datIni = Calendar.getInstance();
        this.datFim = Calendar.getInstance();
        this.hospital = hospitalData.criaHospital(df);
        hospitalData.salvarHospital(this.hospital);

        processoNotificacaoData.criaEntrevistaAutorizadaRadom(df,hospital,5,datIni,datFim);
        processoNotificacaoData.criaEntrevistaRecusadaRadom(df, hospital, 5, datIni, datFim);
        processoNotificacaoData.criaCaptacaoRealizadaRadom(df, hospital, 5, datIni, datFim);
    }

    @Test
    @Transactional
    public void relatorioTotalDoacaoInstituicao() {
        TotalDoacaoInstituicao tdi ;

        tdi= aplRelatorio.relatorioTotalDoacaoInstituicao(hospital.getId(),datIni,datFim);

        Assert.assertEquals(tdi.getNumeroNotificacao(), new Integer(15));
        Assert.assertEquals(tdi.getNumeroEntrevista(), new Integer(15));
        Assert.assertEquals(tdi.getNumeroRecusa(), new Integer(5));
        Assert.assertEquals(tdi.getNumeroDoacao(), new Integer(5));
        Assert.assertNotNull(tdi.getPercentualEfetivacao());
    }

//    @Test
//    @Transactional
//    public void relatorioQualificacaoRecusaFamiliar(){
//        List<Long> listHospital = new ArrayList<>();
//        listHospital.add(hospital.getId());
//        List<QualificacaoRecusaFamiliar> listQrf = aplRelatorio.relatorioQualificacaoRecusa(datIni,datFim,listHospital);
//
//        Assert.assertEquals(5, listQrf.size());
//    }

}
