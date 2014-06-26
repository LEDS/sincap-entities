package br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifes.leds.sincap.gerenciaNotificacao.cgd.PacienteRepository;

@Service
public class AplObito {

    @Autowired
    private PacienteRepository pacienteRepository;
    
    
}
