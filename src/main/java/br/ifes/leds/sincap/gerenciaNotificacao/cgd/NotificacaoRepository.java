package br.ifes.leds.sincap.gerenciaNotificacao.cgd;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Notificacao;

/**
 * NotificacaoRepository.java
 * @author 20091BSI0273
 * Interface que representa o repositorio de Notificacao
 */
@Repository
@Transactional
public interface NotificacaoRepository extends JpaRepository <Notificacao, Long> {
    
        List<Notificacao> findByDataArquivamentoIsNullOrderByDataArquivamentoDesc();
	
//	/**
//	 * Metodo para retornar uma lista de notificacoes relacionados as hospital dado, no intervalo de tempo de ocorrencia da notificao determinado.
//	 * @param hospitalNome, String que representa o nome do Hospital, ou parte do nome, case insensitive. Ex: Hospital Evangelico de Vila Velha, ou %Evangelico%.
//	 * @param dataInicial, Calendar que representa a data inicial
//	 * @param dataFinal, Calendar que representa a data final
//	 * @return Lista de Notificacao ocorridas no Setor dado, do Hospital dado, no intervalo de tempo dado.
//	 */
//	public List<Notificacao> findByObitoSetorHospitalNomeLikeIgnoreCaseAndDataNotificacaoBetween(String hospitalNome, Calendar dataInicial, Calendar dataFinal);
//	
//	/**
//	 * Metodo para retornar uma lista de notificacoes a partir de parte do nome do paciente e parte do nome da mae.
//	 * @param nome, String que representa o nome do paciente, ou parte do nome, case insensitive. Ex: joao da silva, ou joao%, ou %silva.
//	 * @param nomeMae, String que representa o nome da mae do paciente, ou parte do nome, case insentive. Ex: maria da silva, ou maria%, ou %silva.
//	 * @return Lista de Notificacao de pacientes relacionados ao nome e nome da mae dados.
//	 */
//	public List<Notificacao> findByObitoPacienteNomeLikeIgnoreCaseAndObitoPacienteNomeMaeLikeIgnoreCase (String nome, String nomeMae);
//
//	/**
//	 * Metodos para retornar uma lista de notificacoes por parte do nome do paciente, e uma data de nascimento.
//	 * @param nome, String que representa nome do paciente ou parte do nome, case insesitive. Ex: Ex: joao da silva, ou joao%, %silva
//	 * @param dataNascimento, Calendar que representa data de nascimento do paciente
//	 * @return Lista de Notificacao relacionada ao nome e data de nascimentos dados.
//	 */
//	public List<Notificacao> findByObitoPacienteNomeLikeIgnoreCaseAndObitoPacienteDataNascimento (String nome, Calendar dataNascimento);
//	
//	/**
//	 * Metodo para retornar uma notificacao a partir de uma notificao a partir de um intervalo de tempo da ocorrencia do obito.
//	 * @param dataInicial, Calendar que representa a data inicial.
//	 * @param dataFinal, Calendar que representa a data final.
//	 * @return Lista de Notificacao de obitos ocorridos no intervalo de tempo dado
//	 */
//	public List<Notificacao> findByObitoDataObitoBetween (Calendar dataInicial, Calendar dataFinal);
//	
//	@QueryHints(value = { @QueryHint(name = "name", value = "value")}, forCounting = false)
//	public Page<Notificacao> findByNotificador(Notificador notificador, Pageable pageable);
//	
//		
//	/* Metodo para encontrar notificacoes com determinado MotivoInviabilidade*/
//	public List<Notificacao> findByMotivoInviabilidadeId(Long Id);
}
