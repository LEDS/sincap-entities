package br.ifes.leds.reuse.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


/**
 * Created by aleao on 11/08/15.
 */
@org.aspectj.lang.annotation.Aspect
public class Aspect {


    private final Logger logger = Logger.getLogger(this.getClass());

    @After("execution(* br.ifes.leds.sincap.*.cln.cgt.*.salvar(..))")
    public void logAfter(JoinPoint joinPoint) {

        BasicConfigurator.configure();

        logger.setLevel(Level.INFO);

        StringBuffer logMessage = new StringBuffer();

        logMessage.append("Método: ");
        logMessage.append(joinPoint.getSignature());
        logMessage.append(" Executado com sucesso!");

        logger.info(logMessage.toString());

    }
}
