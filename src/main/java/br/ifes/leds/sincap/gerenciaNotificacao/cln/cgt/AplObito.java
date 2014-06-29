package br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt;

import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifes.leds.reuse.endereco.cgd.EnderecoRepository;
import br.ifes.leds.reuse.utility.Utility;
import br.ifes.leds.sincap.controleInterno.cgd.TelefoneRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.CausaMortisRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ObitoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.PacienteRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Obito;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Paciente;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.DTO.ObitoDTO;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.DTO.PacienteDTO;

@Service
public class AplObito {

    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private TelefoneRepository telefoneRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private ObitoRepository obitoRepository;
    @Autowired
    private Mapper mapper;
    @Autowired
    private CausaMortisRepository causaMortisRepository;
    private Utility utility = Utility.INSTANCE;

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
        Paciente paciente = mapper.map(pacienteDTO, Paciente.class);

        // TODO Validar os dados recebidos.

        salvarPaciente(paciente);
    }

    private void salvarPaciente(Paciente paciente) {
        telefoneRepository.save(paciente.getTelefone());
        enderecoRepository.save(paciente.getEndereco());
        pacienteRepository.save(paciente);
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
     * Obtém os dados de um óbito, dado seu id.
     * 
     * @param id
     *            O {@code id} do óbito no banco de dados.
     * @return Um DTO de óbito.
     */
    public ObitoDTO obterObito(Long id) {
        Obito obito = obitoRepository.findOne(id);

        return mapper.map(obito, ObitoDTO.class);
    }

    /**
     * Salva os dados de um óbito, tanto para alteração quanto para um novo
     * 
     * @param obitoDTO
     *            O objeto que representa o óbito.
     */
    public void salvarObito(ObitoDTO obitoDTO) {
        Obito obito = mapper.map(obitoDTO, Obito.class);

        salvarPaciente(obito.getPaciente());

        salvarObito(obito);
    }

    private void salvarObito(Obito obito) {
        causaMortisRepository.save(obito.getPrimeiraCausaMortis());
        causaMortisRepository.save(obito.getSegundaCausaMortis());
        causaMortisRepository.save(obito.getTerceiraCausaMortis());
        causaMortisRepository.save(obito.getQuartaCausaMortis());

        obitoRepository.save(obito);
    }
}
