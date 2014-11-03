/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.leds.sincap.gerenciaNotificacao.cln.util.dataFactory;

import br.ifes.leds.sincap.controleInterno.cln.cdp.Hospital;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author aleao
 */
@Component
public class DataFactoryMain {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext(
                "spring-context.xml");

        BeanFactory factory = context;

         NotificadorData nd = (NotificadorData)
         factory.getBean("notificadorData");
         AnalistaCNCDOData ad = (AnalistaCNCDOData)
         factory.getBean("analistaCNCDOData");
         CaptadorData cd = (CaptadorData) factory.getBean("captadorData");
         HospitalData hd = (HospitalData) factory.getBean("hospitalData");
         SetorData sd = (SetorData) factory.getBean("setorData");
         BancoOlhosData bd = (BancoOlhosData)
         factory.getBean("bancoOlhosData");
         PacienteData pd = (PacienteData) factory.getBean("pacienteData");
         ResponsavelData rd = (ResponsavelData)
         factory.getBean("responsavelData");
        TestemunhaData td = (TestemunhaData) factory.getBean("testemunhaData");
        EntrevistaData ed = (EntrevistaData) factory.getBean("entrevistaData");
        CausaMortisData cmd = (CausaMortisData) factory.getBean("causaMortisData");
        ObitoData obd = (ObitoData) factory.getBean("obitoData");
        CausaNaoDoacaoData cnd = (CausaNaoDoacaoData) factory.getBean("causaNaoDoacaoData");
        ProcessoNotificacaoData pnd = (ProcessoNotificacaoData) factory.getBean("processoNotificacaoData");
        HospitalData hospitalData = (HospitalData) factory.getBean("hospitalData");

        DataFactory df = new DataFactory();
        df.randomize((int) System.currentTimeMillis());

        Calendar datIni = Calendar.getInstance();
        Calendar datFim = Calendar.getInstance();
        Hospital hospital = hospitalData.criaHospital(df);
        hospitalData.salvarHospital(hospital);


//        nd.criaNotificadorRandom(df,5);
//        hd.criaHospitalRandom(df, 5);
//        sd.criaSetorRandom();
//        sd.associaSetorHospital();
//        bd.criaBancoOlhosRandom(df, 5);
//        ad.criaAnalistaRandom(df, 5);
//        cd.criaCaptadorRandom(df, 5);
        pd.criaPacienteRandom(df, 5);
        rd.criaResponsavelRandom(df, 5);
        td.criaTestemunhaRandom(df, 5);
//        cmd.criaCausaMortisRandom(df, 5);
//        pnd.criarAnaliseObitoRandom(df,5);
//        pnd.criaEntrevistaRadom(df, 5);
//        pnd.criaCaptacaoRadom(df, 5);
        pnd.criaEntrevistaAutorizadaRadom(df,hospital,5,datIni,datFim);
        pnd.criaEntrevistaRecusadaRadom(df,hospital,5,datIni,datFim);
        pnd.criaCaptacaoRealizadaRadom(df,hospital,5,datIni,datFim);
//        pnd.criaEntrevistaRecusadaRadom(df,5,datIni,datFim);
//        cnd.criaCausaNaoDoacaoRandom(df, 5);
    }

}
