package br.ifes.leds.reuse.utility;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;

/**
 *
 * @author 20112bsi0083
 */
public enum Utility {

    INSTANCE;

    private final Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();

    public static Utility getInstance() {
        return INSTANCE;
    }

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
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        data = formatoData.format(calendar.getTime());
        hora = formatoHora.format(calendar.getTime());
    }

    public Calendar stringToCalendar(String data, String hora) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        DateFormat formatoDataHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String dataHora = data + " " + hora;
        Date convertedDataHora = formatoDataHora.parse(dataHora);
        calendar.setTime(convertedDataHora);
        return calendar;
    }

    public Calendar stringToCalendar(String data) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        DateFormat formatoDataHora = new SimpleDateFormat("dd/MM/yyyy");
        String dataHora = data;
        Date convertedDataHora = formatoDataHora.parse(dataHora);
        calendar.setTime(convertedDataHora);
        return calendar;
    }

    public Long booleanToLong(boolean attribute) {
        if (attribute) {
            return (long) 1;
        } else {
            return (long) 0;
        }
    }

    /**
     * Mapeia uma lista de um tipo para outro.
     * 
     * @param listaOrigem
     *            A lista a ser mapeada.
     * @param classeDestino
     *            O tipo destino a ser mapeado.
     * @return Uma lista do tipo definido no {@code classeDestino}.
     */
    public <K, T> List<T> mapList(List<K> listaOrigem, Class<T> classeDestino) {
        List<T> listaDestino = new ArrayList<>();

        for (K origem : listaOrigem) {
            listaDestino.add(mapper.map(origem, classeDestino));
        }

        return listaDestino;
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
