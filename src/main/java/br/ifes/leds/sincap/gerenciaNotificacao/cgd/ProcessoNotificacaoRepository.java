package br.ifes.leds.sincap.gerenciaNotificacao.cgd;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.EstadoNotificacaoEnum;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.ProcessoNotificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

/**
 * NotificacaoRepository.java
 *
 * @author 20091BSI0273
 *         Interface que representa o repositorio de Notificacao
 */
@Repository
@Transactional
public interface ProcessoNotificacaoRepository extends JpaRepository<ProcessoNotificacao, Long> {

    public List<ProcessoNotificacao> findByObitoPacienteNomeContainingAndEntrevistaIsNotNullAndEntrevistaDoacaoAutorizadaTrue(String searchString);

    public List<ProcessoNotificacao> findByDataArquivamentoIsNullOrderByDataAberturaDesc();

    public List<ProcessoNotificacao> findByDataAberturaBetween(Calendar dataAberturaInicio, Calendar dataAberturaFim);

    public List<ProcessoNotificacao> findByUltimoEstadoEstadoNotificacaoOrderByUltimoEstadoDataAtualizacaosAsc(EstadoNotificacaoEnum estado);

    public List<ProcessoNotificacao> findByDataArquivamentoIsNotNullOrderByDataAberturaDesc();

    public List<ProcessoNotificacao> findByUltimoEstadoEstadoNotificacaoAndNotificadorInstituicoesNotificadorasIdOrderByUltimoEstadoDataAtualizacaosAsc(EstadoNotificacaoEnum estado, Long id);

    public List<ProcessoNotificacao> findByUltimoEstadoEstadoNotificacaoAndObitoHospitalBancoOlhosIdOrderByUltimoEstadoDataAtualizacaosAsc(EstadoNotificacaoEnum estado, Long id);

    public List<ProcessoNotificacao> findByCodigoIgnoreCaseContainingOrNotificadorNomeIgnoreCaseContainingOrObitoPacienteNomeIgnoreCaseContainingOrObitoPacienteNomeMaeIgnoreCaseContaining(String codigo, String notificadornome, String obitoPacienteNome, String obitoPacienteNomeMae);

    public Long countByEntrevistaEntrevistaRealizadaTrueAndEntrevistaDoacaoAutorizadaTrueAndCaptacaoCaptacaoRealizadaTrueAndObitoAptoDoacaoTrueAndDataAberturaBetweenAndInstituicaoNotificadoraId(Calendar dataInicio, Calendar dataFim,Long id);
}
