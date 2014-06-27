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

        ApplicationContext context
                = new ClassPathXmlApplicationContext("spring-context.xml");

        BeanFactory factory = context;

        NotificadorData nd = (NotificadorData) factory.getBean("notificadorData");
        AnalistaCNCDOData ad = (AnalistaCNCDOData) factory.getBean("analistaCNCDOData");
        CaptadorData cd = (CaptadorData) factory.getBean("captadorData");
        HospitalData hd = (HospitalData) factory.getBean("hospitalData");
        SetorData sd = (SetorData) factory.getBean("setorData");
        BancoOlhosData bd = (BancoOlhosData) factory.getBean("bancoOlhosData");
        
        DataFactory df = new DataFactory();
        df.randomize((int) System.currentTimeMillis());

        
        //nd.criaNotificadorRandom(df,1);
        //hd.criaHospitalRandom(df, 5);
        //sd.criaSetorRandom();
        //sd.associaSetorHospital();
//        bd.criaBancoOlhosRandom(df, 5);
//        ad.criaAnalistaRandom(df, 5);
//        cd.criaCaptadorRandom(df, 10);
    }

}
