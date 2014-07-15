package br.ifes.leds.reuse.utility;

import br.ifes.leds.reuse.utility.function.Function;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author 20112bsi0083
 */
public enum Utility {

    INSTANCE;

    private static final String FORMATO_DATA_HORA = "dd/MM/yyyy HH:mm";
    private static final String FORMATO_DATA = "dd/MM/yyyy";
    private static final String FORMATO_HORA = "HH:mm";

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
    @Deprecated
    public void calendarToString(Calendar calendar, String data, String hora) {
        SimpleDateFormat formatoHora = new SimpleDateFormat(FORMATO_HORA);
        SimpleDateFormat formatoData = new SimpleDateFormat(FORMATO_DATA);
        data = formatoData.format(calendar.getTime());
        hora = formatoHora.format(calendar.getTime());
    }

    public String calendarToString(Calendar calendar) {

        SimpleDateFormat formatoHora = new SimpleDateFormat(FORMATO_DATA_HORA);

        return formatoHora.format(calendar.getTime());
    }

    public String calendarHoraToString(Calendar hora) {
        DateFormat formatHora = new SimpleDateFormat(FORMATO_HORA);
        return formatHora.format(hora.getTime());
    }

    public String calendarDataToString(Calendar hora) {
        DateFormat formatData = new SimpleDateFormat(FORMATO_DATA);
        return formatData.format(hora.getTime());
    }

    public Calendar stringToCalendar(String data, String hora)
            throws ParseException {
        Calendar calendar = Calendar.getInstance();
        DateFormat formatoDataHora = new SimpleDateFormat(FORMATO_DATA_HORA);
        String dataHora = data + " " + hora;
        Date convertedDataHora = formatoDataHora.parse(dataHora);
        calendar.setTime(convertedDataHora);
        return calendar;
    }

    public Calendar stringToCalendar(String data) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        DateFormat formatoDataHora = new SimpleDateFormat(FORMATO_DATA);
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
     * @param lista  A lista de objetos do tipo T.
     * @param objeto O objeto usado para pesquisar na lista.
     * @param funcao A função que retorna o objeto do que será usado como parâmetro de comparação.
     * @param <T>    O tipo de objeto da lista.
     * @return O primeiro objeto da lista que corresponder ao parâmetro de busca utilizado.
     */
    public <T, K> T getObjectByMethod(List<T> lista, K objeto, Function<T, K> funcao) {
        for (T obj : lista) {
            if (funcao.apply(obj).equals(objeto)) {
                return obj;
            }
        }
        return null;
    }
}
