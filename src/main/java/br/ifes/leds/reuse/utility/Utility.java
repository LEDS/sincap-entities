package br.ifes.leds.reuse.utility;

import br.ifes.leds.reuse.utility.function.Function;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.joda.time.Years.yearsBetween;

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
     * Pesquisa um objeto em uma lista baseado em um método.
     *
     * @param lista  A lista de objetos do tipo T.
     * @param objeto O objeto usado para pesquisar na lista.
     * @param funcao A função que retorna o objeto do que será usado como parâmetro de comparação.
     * @param <T>    O tipo de objeto da lista.
     * @return O primeiro objeto da lista que corresponder ao parâmetro de busca utilizado.
     */
    public <T, K> T getObjectByMethod(Collection<T> lista, K objeto, Function<T, K> funcao) {
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

    public static Calendar hoje() {
        return Calendar.getInstance();
    }
}
