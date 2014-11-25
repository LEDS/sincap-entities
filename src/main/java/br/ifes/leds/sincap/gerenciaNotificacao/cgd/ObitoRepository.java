package br.ifes.leds.sincap.gerenciaNotificacao.cgd;


import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoObito;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.relatorios.FaixaEtaria;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Obito;

import java.util.Calendar;
import java.util.List;

/**
 * ObitoRepository.java
 * @author 20091BSI0273
 * Interface que representa o repositorio de Obito.
 */
@Repository
@Transactional
public interface ObitoRepository extends JpaRepository <Obito, Long> {
	
//	/**
//	 * Metodo que retorna um alista de obitos ocorridos no intervalo de tempo dado.
//	 * @param dataInicial, Calendar que representa a data inicial.
//	 * @param dataFinal, Calendar que representa a data final.
//	 * @return Lista de Obito, ocorridos no intervalo de tempo dado.
//	 */
//	public List<Obito> findByDataObitoBetween (Calendar dataInicial, Calendar dataFinal);
	
//	/**
//	 * Metodo que retornar uma lista de obitos no intervalo de tempo determinado, por tipo de Obito
//	 * @param dataInicial, Calendar que representa a data inicial.
//	 * @param dataFinal, Calendar que representa a data final.
//	 * @param tipoObito, TipoObito que representa o tipo de obito PCR ou ME.
//	 * @return Lista de Obito, do tipo dado ocorridos no intervalo de tempo dado.
//	 */
//	public List<Obito> findByDataObitoBetweenAndTipoObito(Calendar dataInicial, Calendar dataFinal, TipoObito tipoObito);
@Query(nativeQuery = true,value = "select count(DATE_PART('YEAR',age(o.dataobito,p.datanascimento))) " +
        "from Obito o " +
        "join Paciente p " +
        "ON (o.paciente_id = p.id)" +
        "where DATE_PART('YEAR',age(o.dataobito,p.datanascimento)) between :idadeIni and :idadeFim " +
        "and o.hospital_id = (:idHospital)and o.tipoobito = (:idTipo)" +
        "and o.dataobito between :datIni and :datFim")
public Long getFaixaEtaria(@Param("idadeIni") Integer idadeIni, @Param("idadeFim") Integer idadeFim,@Param("idHospital")Long idHospital,@Param("idTipo")Integer idTipo,@Param("datIni") Calendar datIni,@Param("datFim") Calendar datFim);
}
