package br.ifes.leds.reuse.utility.function;

/**
 * Created by phillipe on 11/07/14.
 */

/**
 * Interface para funções dinâmicas.
 *
 * @param <T> O tipo do parâmetro.
 * @param <R> O tipo do retorno da função.
 */
public interface Function<T, R> {

    public R apply(T parameter);
}
