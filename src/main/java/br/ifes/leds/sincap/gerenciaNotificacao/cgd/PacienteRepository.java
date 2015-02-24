package br.ifes.leds.sincap.gerenciaNotificacao.cgd;

import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * PacienteRepository. java
 * @author 20091BSI0273
 * Interface que representa o repositorio de Paciente
 */
@Repository
@Transactional
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    @SuppressWarnings("JpaQlInspection")
    @Modifying(clearAutomatically = true)
    @Query(value = "update Paciente p set p.nome = :nome where p.id = :id")
    public void updateNome(@Param("id") Long id, @Param("nome") String nome);
}
