package br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt;

import br.ifes.leds.reuse.utility.Utility;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ObitoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.PacienteRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Obito;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Paciente;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto.ObitoDTO;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto.PacienteDTO;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AplObito {

    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private ObitoRepository obitoRepository;
    @Qualifier("mapper")
    @Autowired
    private Mapper mapper;
    @Autowired
    private Utility utility;

    /**
     * Obtém todos os pacientes.
     * 
     * @return Uma lista de {@code PacienteDTO}.
     */
    public List<PacienteDTO> obterTodosPacientes() {
        return utility.mapList(pacienteRepository.findAll(), PacienteDTO.class);
    }

    /**
     * Obtém os dados de um paciente, dado seu id.
     * 
     * @param id
     *            O {@code id} do paciente no banco de dados.
     * @return Um DTO de paciente.
     */
    public PacienteDTO obterPaciente(Long id) {
        Paciente pacienteTmp = pacienteRepository.findOne(id);

        return mapper.map(pacienteTmp, PacienteDTO.class);
    }

    /**
     * Salva os dados de um paciente, tanto para alteração quanto para um novo
     * paciente.
     * 
     * @param pacienteDTO
     *            O objeto que representa o paciente.
     */
    public void salvarPaciente(PacienteDTO pacienteDTO) {
        pacienteRepository.save(mapper.map(pacienteDTO, Paciente.class));
    }

    /**
     * Obtém todos os obitos.
     * 
     * @return Uma lista de {@code ObitoDTO}.
     */
    public List<ObitoDTO> obterTodosObitos() {
        return utility.mapList(obitoRepository.findAll(), ObitoDTO.class);
    }

    /**
     * Salva os dados de um óbito, tanto para alteração quanto para um novo
     * 
     * @param obitoDTO
     *            O objeto que representa o óbito.
     */
    public void salvarObito(ObitoDTO obitoDTO) {
        obitoRepository.save(mapper.map(obitoDTO, Obito.class));
    }

    public void salvarObito(Obito obito) {
        obitoRepository.save(obito);
    }
}
