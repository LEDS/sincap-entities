package br.ifes.leds.sincap.test;

import br.ifes.leds.sincap.controleInterno.cln.cdp.Hospital;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.ProcessoNotificacao;
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
//        ApplicationContext context = new ClassPathXmlApplicationContext(
//                "spring-context.xml");
//
//        BeanFactory factory = context;
//
//        ProcessoNotificacaoData processoNotificacaoData = (ProcessoNotificacaoData) factory.getBean("processoNotificacaoData");
//        HospitalData hospitalData = (HospitalData) factory.getBean("hospitalData");
//
//        this.processoNotificacaoData = criaObjeto(ProcessoNotificacaoData.class);
        this.df = criaObjeto(DataFactory.class);
        this.datIni = Calendar.getInstance();
        this.datFim = Calendar.getInstance();
        this.listProcessoNotificacao = new ArrayList<>();
        this.hospital = hospitalData.criaHospital(df);
        hospitalData.salvarHospital(this.hospital);

        processoNotificacaoData.criaEntrevistaAutorizadaRadom(df,hospital,5,datIni,datFim);
        processoNotificacaoData.criaEntrevistaRecusadaRadom(df,hospital,5,datIni,datFim);
    }
    @Test
    public void relatorioTotalDoacaoInstituicao() {
        TotalDoacaoInstituicao tdi ;

        tdi= aplRelatorio.relatorioTotalDoacaoInstituicao(hospital.getId(),datIni,datFim);
        System.out.println(tdi.getNomeInstituicao());
        System.out.println(tdi.getNumeroNotificacao());
        System.out.println(tdi.getNumeroEntrevista());
        System.out.println(tdi.getNumeroRecusa());
        System.out.println(tdi.getNumeroDoacao());
        System.out.println(tdi.getPercentualEfetivacao());

        Assert.assertEquals(tdi.getNumeroEntrevista(),new Integer(10));
    }

}
