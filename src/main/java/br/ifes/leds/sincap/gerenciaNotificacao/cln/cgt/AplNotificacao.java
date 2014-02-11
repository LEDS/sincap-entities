package br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt;

import br.ifes.leds.sincap.gerenciaNotificacao.cgd.CausaObitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifes.leds.sincap.gerenciaNotificacao.cgd.NotificacaoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ObitoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.PacienteRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ResponsavelRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Notificacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Obito;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Paciente;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Responsavel;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * AplNotificacao.java
 *
 * @author 20091BSI0273 Classe de servico para regras de negocio da notificacao
 */
@Service

public class AplNotificacao {

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    @Autowired
    private ObitoRepository obitoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Autowired
    private CausaObitoRepository causaObitoRepository;

    public List<Notificacao> obter() {
        return notificacaoRepository.findAll();
    }
    
    public Obito getObito(Long id) {
        return obitoRepository.findOne(id);
    }

    public void salvar(Notificacao notificacao) {

        Obito obito = notificacao.getObito();
        salvarObito(obito);

        //Gerando o codigo
        notificacao.setCodigo(genereateCode());
        notificacao.setDataArquivamento(Calendar.getInstance());
        notificacaoRepository.save(notificacao);
    }

    private void salvarObito(Obito obito) {
        //Salvando o paciente
        Paciente paciente = obito.getPaciente();
        salvarPaciente(paciente);

        causaObitoRepository.save(obito.getPrimeiraCausaMortis());
        causaObitoRepository.save(obito.getSegundaCausaMortis());
        causaObitoRepository.save(obito.getTerceiraCausaMortis());
        causaObitoRepository.save(obito.getQuartaCausaMortis());

        //Salvando o Obito
        obitoRepository.save(obito);

    }

    private void salvarPaciente(Paciente paciente) {
        //Salvando responsavel
        Responsavel responsavel = paciente.getResponsavel();
        responsavelRepository.save(responsavel);
        pacienteRepository.save(paciente);
        
        
    }
    
    public List<Notificacao> retornarTodas(){
        
        Sort sort = new Sort(Sort.Direction.ASC, "dataNotificacao");        
        
        return notificacaoRepository.findAll(sort);
    }
    
     public List<Notificacao> retornarNotificacaoNaoArquivada(){
        
        Sort sort = new Sort(Sort.Direction.ASC, "dataNotificacao");
         
        return notificacaoRepository.findByDataNotificacaoIsNull(sort);
    }
     
     public List<Notificacao> retornarNotificacaoNaoArquivada(int valorInicial, int qtd){
        
        Sort sort = new Sort(Sort.Direction.ASC, "dataNotificacao");
        
        Pageable pageable = new PageRequest(valorInicial, qtd);
        
        return notificacaoRepository.findByDataNotificacaoIsNull(sort, pageable);
    }
    
    private String genereateCode()
    {
        return  UUID.randomUUID().toString();
    }

    private String genereateCode() {
        return UUID.randomUUID().toString();
    }

}
