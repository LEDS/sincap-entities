package br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifes.leds.reuse.endereco.cgd.EnderecoRepository;
import br.ifes.leds.sincap.controleInterno.cgd.TelefoneRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.PacienteRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Paciente;
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
    private Mapper mapper;

    /**
     * Obtém todos os pacientes.
     * 
     * @return Uma lista de {@code PacienteDTO}.
     */
    public List<PacienteDTO> obterTodosPacientes() {
        List<PacienteDTO> pacientes = new ArrayList<PacienteDTO>();

        for (Paciente paciente : pacienteRepository.findAll()) {
            pacientes.add(mapper.map(paciente, PacienteDTO.class));
        }

        return pacientes;
    }

    public void salvarPaciente(PacienteDTO pacienteDTO) {
        Paciente paciente = mapper.map(pacienteDTO, Paciente.class);

        // TODO Validar os dados recebidos.

        telefoneRepository.save(paciente.getTelefone());
        enderecoRepository.save(paciente.getEndereco());
        pacienteRepository.save(paciente);
    }
}
