package br.ifes.leds.sincap.gerenciaNotificacao.cgd;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.EstadoNotificacaoEnum;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.ProcessoNotificacao;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * NotificacaoRepository.java
 *
 * @author 20091BSI0273
 *         Interface que representa o repositorio de Notificacao
 */
@Repository
@Transactional
public interface ProcessoNotificacaoRepository extends JpaRepository<ProcessoNotificacao, Long> {
    String findByPacienteNumeroSUS = "SELECT * " +
                                     "FROM processonotificacao pn " +
                                     "INNER JOIN obito o " +
                                     "	ON o.id = pn.obito_id " +
                                     "INNER JOIN paciente p " +
                                     "	ON p.id = o.paciente_id " +
                                     "WHERE p.numerosus = :numeroSus";
    
    public List<ProcessoNotificacao> findByDataArquivamentoIsNullOrderByDataAberturaDesc();

    public List<ProcessoNotificacao> findByDataAberturaBetween(Calendar dataAberturaInicio, Calendar dataAberturaFim);

    public List<ProcessoNotificacao> findByUltimoEstadoEstadoNotificacaoOrderByUltimoEstadoDataAtualizacaosAsc(EstadoNotificacaoEnum estado);

    public List<ProcessoNotificacao> findByUltimoEstadoEstadoNotificacaoOrderByUltimoEstadoDataAtualizacaosAsc(EstadoNotificacaoEnum estado, Pageable pageable);

    public List<ProcessoNotificacao> findByDataArquivamentoIsNotNullOrderByDataAberturaDesc();
    
    @Query(value = findByPacienteNumeroSUS, nativeQuery = true)
    public ProcessoNotificacao findByPacienteNumeroSus(@Param("numeroSus") String numeroSus);
}
