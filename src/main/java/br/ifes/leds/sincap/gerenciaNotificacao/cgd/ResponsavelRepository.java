package br.ifes.leds.sincap.gerenciaNotificacao.cgd;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.DocumentoComFoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Responsavel;

/**
 * ResponsavelRepository.java
 * @author 20091BSI0273
 * Interface que representa o repositorio de Responsavel
 */
@Repository
@Transactional
public interface ResponsavelRepository extends JpaRepository<Responsavel, Long> {
	
	/**
	 * Metodo que retorna o responsavel a partir do RG
	 * @param documentoSocial, String que representa o documento com foto do responsavel
	 * @return
	 */
	public Responsavel findByDocumentoSocial(DocumentoComFoto documentoSocial);

}
