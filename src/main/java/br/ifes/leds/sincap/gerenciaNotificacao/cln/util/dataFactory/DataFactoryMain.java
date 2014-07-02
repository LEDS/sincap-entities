/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.leds.sincap.gerenciaNotificacao.cln.util.dataFactory;

import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

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

        // NotificadorData nd = (NotificadorData)
        // factory.getBean("notificadorData");
        // AnalistaCNCDOData ad = (AnalistaCNCDOData)
        // factory.getBean("analistaCNCDOData");
        // CaptadorData cd = (CaptadorData) factory.getBean("captadorData");
        // HospitalData hd = (HospitalData) factory.getBean("hospitalData");
        // SetorData sd = (SetorData) factory.getBean("setorData");
        // BancoOlhosData bd = (BancoOlhosData)
        // factory.getBean("bancoOlhosData");
        // PacienteData pd = (PacienteData) factory.getBean("pacienteData");
        // ResponsavelData rd = (ResponsavelData)
        // factory.getBean("responsavelData");
        TestemunhaData td = (TestemunhaData) factory.getBean("testemunhaData");
        EntrevistaData ed = (EntrevistaData) factory.getBean("entrevistaData");
        CausaMortisData cmd = (CausaMortisData) factory.getBean("causaMortisData");
        ObitoData obd = (ObitoData) factory.getBean("obitoData");
        CausaNaoDoacaoData cnd = (CausaNaoDoacaoData) factory.getBean("causaNaoDoacaoData");
        TipoNaoDoacaoData tpn = (TipoNaoDoacaoData) factory.getBean("tipoNaoDoacaoData");
        
        DataFactory df = new DataFactory();
        df.randomize((int) System.currentTimeMillis());

        
//        nd.criaNotificadorRandom(df,30);
//        hd.criaHospitalRandom(df, 30);
//        sd.criaSetorRandom();
//        sd.associaSetorHospital();
//        bd.criaBancoOlhosRandom(df, 30);
//        ad.criaAnalistaRandom(df, 30);
//        cd.criaCaptadorRandom(df, 30);
//        pd.criaPacienteRandom(df, 30);
//        rd.criaResponsavelRandom(df, 30);
//        td.criaTestemunhaRandom(df, 30);
//        ed.criaEntrevistaRandom(df, 30);
//        cmd.criaCausaMortisRandom(df, 30);
//        obd.criaObitoRandom(df, 30);
        tpn.criaTipoNaoDoacao();
        cnd.criaCausaNaoDoacaoRandom(df, 10);
        
    }

}
