package br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifes.leds.sincap.gerenciaNotificacao.cgd.PacienteRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Paciente;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.DTO.PacienteDTO;

@Service
public class AplObito {

    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private Mapper mapper;

    public void salvarPaciente(PacienteDTO pacienteDTO) {
        Paciente paciente = mapper.map(pacienteDTO, Paciente.class);

        pacienteRepository.save(paciente);
    }
}
