package br.ifes.leds.reuse.utility;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

/**
 *
 * @author 20112bsi0083
 */
public enum Utility {

    INSTANCE;

    /**
     *
     * @author 20112bsi0083
     * @param calendar
     *            representa o a data salva na classe que precisa ser
     *            transformada em string para ser usada pelo DTO e
     *            posteriormente a interface
     * @param data
     *            se refere ao valor de DTO.data
     * @param hora
     *            se refere ao valor de DTO.hora
     */
    public void calendarToString(Calendar calendar, String data, String hora) {
        SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm");
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        data = formatoData.format(calendar.getTime());
        hora = formatoHora.format(calendar.getTime());
    }

    public void stringToCalendar(String data, String hora, Calendar calendar) throws ParseException {
        DateFormat formatoDataHora = new SimpleDateFormat("yyyy/MM/dd hh:mm");
        String dataHora = data + " " + hora;
        Date convertedDataHora = formatoDataHora.parse(dataHora);
        calendar.setTime(convertedDataHora);
    }

    public Long getLongBoolean(boolean attribute) {
        if (attribute) {
            return (long) 1;
        } else {
            return (long) 0;
        }
    }

    /**
     * Pesquisa um objeto em uma lista baseado em um método.
     * 
     * Exemplo de uso: <code>
     * 
     * getObjectByMethod(umaListQualquer, ClasseDaLista.class.getDeclaredMethod("getNome"), "Nome do Objeto");
     * 
     *  </code>
     * 
     * @param lista
     *            A lista de objetos a ser pesquisada. O objeto que a lista
     *            contém deve implementar o método {@code equals()}.
     * @param metodo
     *            Uma classe {@code Method} com o método a ser utilizado para
     *            comparação. O método deve obrigatoriamente não receber nenhum
     *            parâmetro e deve retornar algum objeto.
     * @param objeto
     *            O objeto usado para pesquisar na lista.
     * 
     * @return O objeto que está na lista e bate com o objeto passado por
     *         parâmetro.
     */
    public <T> T getObjectByMethod(List<T> lista, Method metodo, Object objeto) {
        try {
            for (T obj : lista) {
                if (metodo.invoke(obj).equals(objeto)) {
                    return obj;
                }
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            Logger.getLogger("getObjectByMethod").error("Couldn't get object by method.");
        }
        return null;
    }
}
