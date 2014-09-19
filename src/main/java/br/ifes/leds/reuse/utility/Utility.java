package br.ifes.leds.reuse.utility;

import br.ifes.leds.reuse.utility.function.Function;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author 20112bsi0083
 */
@Component
public class Utility {

    private static final String FORMATO_DATA_HORA = "dd/MM/yyyy HH:mm";
    private static final String FORMATO_DATA = "dd/MM/yyyy";
    private static final String FORMATO_HORA = "HH:mm";

    private final Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();

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

    public int calculaIdade(Calendar dataNasc, Calendar dataFim){
        int idade = dataFim.get(Calendar.YEAR) - dataNasc.get(Calendar.YEAR);

        dataNasc.add(Calendar.YEAR, idade);

        if (dataFim.before(dataNasc)) {
            idade--;
        }
        return idade;
    }

    public Long booleanToLong(boolean attribute) {
        if (attribute) {
            return (long) 1;
        } else {
            return (long) 0;
        }
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
     * Dado um objeto {@code source}, insere todos os atributos com nome "id" dessa classe (aninhados inclusive)
     * dentro do objeto {@code destination}. É útil para edição de objetos no banco de dados.
     *
     * @param destination
     *          Objeto no qual os ids serão inseridos.
     * @param source
     *          Objeto do qual os ids vem.
     * @param <T>
     *           Tipo dos objetos que serão manipulados.
     */
    public <T> void mergeIds(T destination, T source) {
        Method[] methods = source.getClass().getMethods();

        for (Method getMethod : methods) {
            if (enterMergeLoop(destination, getMethod)) {
                String fromName = getMethod.getName();
                String toName = fromName.replace("get", "set");

                try {
                    Method setMethod = source.getClass().getMethod(toName, getMethod.getReturnType());
                    Object sourceValue = getMethod.invoke(source);

                    if (sourceValue != null) {
                        Object destinationValue = getMethod.invoke(destination);

                        if (!stopRecursion(sourceValue)) {
                            mergeIds(destinationValue, sourceValue);
                        } else if (fromName.equals("getId") && destinationValue == null) {
                            setMethod.invoke(destination, sourceValue);
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private <T> boolean enterMergeLoop(T destination, Method getMethod) {
        return getMethod.getName().startsWith("get") && !getMethod.getName().equals("getClass")
                && destination != null;
    }

    private boolean stopRecursion(Object value) {
        return value.getClass().isPrimitive() || value.getClass().isEnum() || value instanceof String
                || Calendar.class.isAssignableFrom(value.getClass())
                || value instanceof Long || Collection.class.isAssignableFrom(value.getClass());
    }
}
