/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.reuse.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author 20112bsi0083
 */
public class Utility {
    
    private static Utility INSTANCE = new Utility();
    
    private Utility()
    {
    }
    
    public static Utility getInstance()
    {
        return INSTANCE;
    }
    
    /**
     *
     * @author 20112bsi0083
     * @param calendar representa o a data salva na classe que precisa ser transformada em string
     * para ser usada pelo DTO e posteriormente a interface
     * @param data se refere ao valor de DTO.data 
     * @param hora se refere ao valor de DTO.hora 
     */
    public void calendarToString(Calendar calendar, String data, String hora)
    {
        SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm");
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        data = formatoData.format(calendar.getTime());
        hora = formatoHora.format(calendar.getTime());
    }
    
    public void stringToCalendar(String data, String hora, Calendar calendar) throws ParseException
    {
       DateFormat formatoDataHora = new SimpleDateFormat("yyyy/MM/dd hh:mm");
       String dataHora = data+" "+hora;
       Date convertedDataHora = formatoDataHora.parse(dataHora);
       calendar.setTime(convertedDataHora);
    }
    
    public Long getLongBoolean(boolean attribute)
    {
        if(attribute)
        {
            return (long) 1;
        }
        else
        {
            return (long) 0;
        }
    }
}
