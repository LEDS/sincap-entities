package br.ifes.leds.sincap.gerenciaNotificacao.cgd;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Paciente;

/**
 * PacienteRepository. java
 * @author 20091BSI0273
 * Interface que representa o repositorio de Paciente
 */
@Repository
@Transactional
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
	
	/**
	 * Metodo para encontrar um paciente pelo rg.
	 * @param rg, String que representa o rg do Paciente
	 * @return
	 */
	public Paciente findByRg(String rg);
	
	/**
	 * Metodo para retornar uma lista de pacientes a partir de parte do nome do paciente e da mae do paciente.
	 * Ex: Joao da Silva e Maria da Silva, Joao% e Maria%, %Silva e Maria%.
	 * @param nome, String que representa o nome do paciente
	 * @param nomeMae, String que representa o nome da mae do paciente
	 * @return, Lista de paciente relacionado ao nome e nome da mae dados.
	 */
	public List <Paciente> findByNomeLikeIgnoreCaseAndNomeMaeLikeIgnoreCase (String nome, String nomeMae);
	
	/**
	 * Metodo que retorna uma lista de pacientes relacionados ao numero de prontuario do paciente no hsopital onde ocorreu obito do mesmo.
	 * @param numeroProntuario, String
	 * @return
	 */
	public List <Paciente> findByNumeroProntuarioLike (String numeroProntuario);
}
