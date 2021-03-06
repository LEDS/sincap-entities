package br.ifes.leds.sincap.gerenciaNotificacao.cgd;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.*;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.relatorios.NaoDoacaoCIHDOTT;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.relatorios.QualificacaoRecusaFamiliar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    public List<ProcessoNotificacao> findByObitoPacienteNomeContainingAndEntrevistaIsNotNullAndEntrevistaDoacaoAutorizadaFalse(String searchString);

    public List<ProcessoNotificacao> findByDataArquivamentoIsNullOrderByDataAberturaDesc();

    public List<ProcessoNotificacao> findByDataAberturaBetween(Calendar dataAberturaInicio, Calendar dataAberturaFim);

    public List<ProcessoNotificacao> findByUltimoEstadoEstadoNotificacaoOrderByUltimoEstadoDataAtualizacaosAsc(EstadoNotificacaoEnum estado);

    public List<ProcessoNotificacao> findByDataArquivamentoIsNotNullOrderByDataAberturaDesc();

    public List<ProcessoNotificacao> findByUltimoEstadoEstadoNotificacaoAndNotificadorInstituicoesNotificadorasIdOrderByUltimoEstadoDataAtualizacaosAsc(EstadoNotificacaoEnum estado, Long id);

    public List<ProcessoNotificacao> findByUltimoEstadoEstadoNotificacaoAndObitoHospitalBancoOlhosIdOrderByUltimoEstadoDataAtualizacaosAsc(EstadoNotificacaoEnum estado, Long id);

    public List<ProcessoNotificacao> findByCodigoIgnoreCaseContainingOrNotificadorNomeIgnoreCaseContainingOrObitoPacienteNomeIgnoreCaseContainingOrObitoPacienteNomeMaeIgnoreCaseContaining(String codigo, String notificadornome, String obitoPacienteNome, String obitoPacienteNomeMae);

    public Integer countByDataAberturaBetweenAndObitoHospitalId(Calendar dataAberturaInicio, Calendar dataAberturaFim,Long id);

    public Integer countByDataAberturaBetweenAndObitoHospitalIdAndObitoTipoObito(Calendar dataAberturaInicio, Calendar dataAberturaFim,Long id,TipoObito tipo);

    public Integer countByDataAberturaBetweenAndObitoHospitalIdAndEntrevistaIsNotNullAndEntrevistaEntrevistaRealizadaTrue(Calendar dataAberturaInicio, Calendar dataAberturaFim,Long id);

    public Integer countByDataAberturaBetweenAndObitoHospitalIdAndEntrevistaIsNotNullAndEntrevistaEntrevistaRealizadaTrueAndObitoTipoObito(Calendar dataAberturaInicio, Calendar dataAberturaFim,Long id,TipoObito tipo);

    public Integer countByDataAberturaBetweenAndObitoHospitalIdAndEntrevistaIsNotNullAndEntrevistaEntrevistaRealizadaTrueAndEntrevistaDoacaoAutorizadaFalse(Calendar dataAberturaInicio, Calendar dataAberturaFim,Long id);

    public Integer countByDataAberturaBetweenAndObitoHospitalIdAndEntrevistaIsNotNullAndEntrevistaEntrevistaRealizadaTrueAndEntrevistaDoacaoAutorizadaFalseAndObitoTipoObito(Calendar dataAberturaInicio, Calendar dataAberturaFim,Long id,TipoObito tipo);

    public Integer countByDataAberturaBetweenAndObitoHospitalIdAndEntrevistaIsNotNullAndEntrevistaEntrevistaRealizadaTrueAndEntrevistaDoacaoAutorizadaTrueAndCaptacaoCaptacaoRealizadaTrue(Calendar dataAberturaInicio, Calendar dataAberturaFim,Long id);

    public Integer countByDataAberturaBetweenAndObitoHospitalIdAndEntrevistaIsNullAndEntrevistaEntrevistaRealizadaFalseAndEntrevistaDoacaoAutorizadaFalse(Calendar dataAberturaInicio, Calendar dataAberturaFim,Long id);

    public Integer countByDataAberturaBetweenAndObitoHospitalIdAndEntrevistaIsNullAndEntrevistaEntrevistaRealizadaFalseAndEntrevistaDoacaoAutorizadaFalseAndObitoTipoObito(Calendar dataAberturaInicio, Calendar dataAberturaFim,Long id,TipoObito tipo);

    public Integer countByDataAberturaBetweenAndObitoHospitalIdAndEntrevistaIsNotNullAndEntrevistaEntrevistaRealizadaTrueAndEntrevistaDoacaoAutorizadaTrueAndObitoTipoObito(Calendar dataAberturaInicio, Calendar dataAberturaFim,Long id,TipoObito tipo);

    @Query(value = "select distinct new br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.relatorios.QualificacaoRecusaFamiliar(c.id, c.nome, count(c.id) as total) " +
        "from br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.ProcessoNotificacao p join p.obito o " +
        "join p.entrevista e on (e.entrevistaRealizada is True and e.doacaoAutorizada is False) " +
        "join p.causaNaoDoacao c " +
        "where p.dataAbertura between :dataInicial and :dataFinal and o.hospital.id in (:id)" +
        "group by c.id, c.nome order by total desc")
    public List<QualificacaoRecusaFamiliar> getRelatorioQualificacaoRecusaFamiliar(@Param("dataInicial") Calendar dataInicial, @Param("dataFinal") Calendar dataFinal,@Param("id")List<Long> id);


    public Integer countByDataAberturaBetweenAndObitoHospitalIdAndCausaNaoDoacaoTipoNaoDoacao(Calendar dataInicio, Calendar dataFinal,Long id,TipoNaoDoacao motivo);

    @Query(value = "select distinct new br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.relatorios.NaoDoacaoCIHDOTT(c.nome, count(p.id)) " +
            "from br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.ProcessoNotificacao p join p.obito o " +
            "join p.causaNaoDoacao c on (c.tipoNaoDoacao = :tipo)" +
            "where p.dataAbertura between :dataInicial and :dataFinal and o.hospital.id in (:id)" +
            "GROUP BY c.nome order by c.nome asc" )
    public List<NaoDoacaoCIHDOTT> getNaoDoacaCIHDOTT(@Param ("dataInicial")Calendar dataInicio,@Param("dataFinal") Calendar dataFinal,@Param("id")Long id,@Param("tipo")TipoNaoDoacao tipo);

    public Integer countByCausaNaoDoacaoId(Long id);

    public ProcessoNotificacao findByObito_id(Long id);

    public List<ProcessoNotificacao> findByObitoPacienteNomeContainingAndEntrevistaIsNotNullAndEntrevistaDoacaoAutorizadaFalseAndCausaNaoDoacaoTipoNaoDoacao(String string, TipoNaoDoacao recusaFamiliar);

    public Integer countByNotificador_id(Long id);

}

