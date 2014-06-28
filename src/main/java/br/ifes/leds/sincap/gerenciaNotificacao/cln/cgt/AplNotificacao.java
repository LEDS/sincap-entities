package br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifes.leds.reuse.endereco.cdp.Endereco;
import br.ifes.leds.reuse.endereco.cgd.EnderecoRepository;
import br.ifes.leds.sincap.controleInterno.cgd.TelefoneRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.AtualizacaoEstadoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.CaptacaoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.CausaMortisRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.EntrevistaRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ObitoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.PacienteRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ProcessoNotificacaoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ResponsavelRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.TestemunhaRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.AtualizacaoEstado;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Captacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Entrevista;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.EstadoNotificacaoEnum;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Obito;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Paciente;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.ProcessoNotificacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Responsavel;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Testemunha;

/**
 * AplNotificacao.java
 *
 * @author 20091BSI0273 Classe de servico para regras de negocio da notificacao
 */
@Service

public class AplNotificacao {

    @Autowired
    private ProcessoNotificacaoRepository notificacaoRepository;

    @Autowired
    private ObitoRepository obitoRepository;
    
    @Autowired
    private EntrevistaRepository entrevistaRepository;
    
    @Autowired
    private CaptacaoRepository captacaoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ResponsavelRepository responsavelRepository;

    @Autowired
    private CausaMortisRepository causaMortisRepository;

    @Autowired
    private TestemunhaRepository testemunhaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private TelefoneRepository telefoneRepository;
    
    @Autowired
    private AtualizacaoEstadoRepository atualizacaoEstadoRepository;
    
    /**
     * 
     * Metodo que salva uma notificação
     * @param notificacao ProcessoNotificacao - Notificacao que sera salva
     * @return long - Retorna o id do obito salvo
    */
    public long salvar(ProcessoNotificacao notificacao) {
        Obito obito = notificacao.getObito();
        this.salvarEtapaObito(obito);
        
        AtualizacaoEstado novoEstado = new AtualizacaoEstado();
        novoEstado.setEstadoNotificacao(EstadoNotificacaoEnum.AGUARDANDOANALISE);
        
        notificacao.getHistorico().add(novoEstado);
                
        this.salvarHistorico(notificacao.getHistorico());
        
        if(notificacao.aptoDoacao()){
            Entrevista entrevista = notificacao.getEntrevista();
            
            if(entrevista != null){
                this.salvarEtapaEntrevista(entrevista);
                
                if(notificacao.doacaoAutorizado()){
                    Captacao captacao = notificacao.getCaptacao();
                    
                    if(captacao != null){
                        this.salvarEtapaCaptacao(captacao);
                    }
                }
            }
        }

        //Gerando o codigo
        notificacao.setCodigo(genereateCode());
        notificacao.setDataAbertura(Calendar.getInstance());
        
        notificacaoRepository.save(notificacao);        
        return notificacao.getId();
    }
    
    
    /**
    * Metodo que salva uma notificação
    * @param historico List - Lista de Atualizacoes de estados
    */
    public void salvarHistorico(List<AtualizacaoEstado> historico){
        if(!historico.isEmpty()){
            for(AtualizacaoEstado estado: historico){
                atualizacaoEstadoRepository.save(estado);
            }
        }
    }

    /**
    * Metodo que salva o obito de uma notificação
    * @param obito Obito - Obito que sera salvo
    * @return long - Id do obito salvo
    */
    public long salvarEtapaObito(Obito obito) {
        obito.setDataEvento(Calendar.getInstance());
        
        Paciente paciente = obito.getPaciente();
        salvarPaciente(paciente);

        causaMortisRepository.save(obito.getPrimeiraCausaMortis());
        causaMortisRepository.save(obito.getSegundaCausaMortis());
        causaMortisRepository.save(obito.getTerceiraCausaMortis());
        causaMortisRepository.save(obito.getQuartaCausaMortis());

        obitoRepository.save(obito);
        
        return obito.getId();
    }
    
    /**
    * Metodo que salva o paciente de um obito
    * @param paciente Paciente - Paciente que sera salvo
    * @return long - Id do paciente salvo
    */
    private long salvarPaciente(Paciente paciente) {        
        Endereco ender = paciente.getEndereco();
        Telefone tel = paciente.getTelefone();
        
        telefoneRepository.save(tel);
        enderecoRepository.save(ender);
        pacienteRepository.save(paciente);
        
        return paciente.getId();
    }
    
    /**
    * Metodo que salva a entrevista de uma notificacao
    * @param entrevista Entrevista - Entrevista que sera salva
    * @return long - Id do paciente salvo
    */
    private long salvarEtapaEntrevista(Entrevista entrevista) {
        entrevista.setDataEvento(Calendar.getInstance());
        
        Testemunha testemunha1 = entrevista.getTestemunha1();
        Testemunha testemunha2 = entrevista.getTestemunha2();
        
        Responsavel responsavel = entrevista.getResponsavel();
        
        testemunhaRepository.save(testemunha1);
        testemunhaRepository.save(testemunha2);
        
        this.salvarResponsavel(responsavel);
        
        entrevistaRepository.save(entrevista);
        
        return entrevista.getId();
    }
    
    /**
    * Metodo que salva o responsavel de uma entrevista
    * @param responsavel Responsavel - Responsavel que sera salvo
    * @return long - Id do responsavel salvo
    */
    public long salvarResponsavel(Responsavel responsavel){
        Endereco endereco = responsavel.getEndereco();
        Telefone tel1 = responsavel.getTelefone();
        Telefone tel2 = responsavel.getTelefone2();
        
        enderecoRepository.save(endereco);
        telefoneRepository.save(tel1);
        telefoneRepository.save(tel2);
        
        responsavelRepository.save(responsavel);
        
        return responsavel.getId();
    }
    
    /**
    * Metodo que salva a captacao de uma notificacao
    * @param captacao Captacao - Captacao que sera salvo
    * @return long - Id da captacao salvo
    */
    private long salvarEtapaCaptacao(Captacao captacao) {
        captacao.setDataEvento(Calendar.getInstance());
        
        captacaoRepository.save(captacao);
        
        return captacao.getId();
    }
    
    /**
    * Metodo que arquiva uma notificacao
    * @param notificacao Notificacao - Notificacao que sofera o arquivamoento
    */
    public void arquivar (ProcessoNotificacao notificacao) {
        notificacao.setArquivado(true);
        notificacaoRepository.save(notificacao);
    }

    /**
    * Metodo para obeter todas as notificacoes
     * @return List - Lista com todos as notificacoes
    */
    public List<ProcessoNotificacao> obter() {
        return notificacaoRepository.findAll();
    }

    /**
    * Metodo que salva a captacao de uma notificacao
    * @param id long - Id da notificacao que sera buscada
    * @return ProcessoNotificacao - Notificacao buscada
    */
    public ProcessoNotificacao getNotificacao(Long id) {
        return notificacaoRepository.findOne(id);
    }

    /**
    * Metodo que busca todas as notificacoes nao arquivadas
    * @return List - Lista de notificacoes
    */
    public List<ProcessoNotificacao> retornarNotificacaoNaoArquivada() {
        return notificacaoRepository.findByDataArquivamentoIsNullOrderByDataAberturaDesc();
    }
    
    /**
    * Metodo que busca todas as notificacoes arquivadas
    * @return List - Lista de notificacoes
    */
    public List<ProcessoNotificacao> retornarNotificacaoArquivada() {
        return notificacaoRepository.findByDataArquivamentoIsNotNullOrderByDataAberturaDesc();
    }

    public List<ProcessoNotificacao> retornarNotificacaoNaoArquivada(int valorInicial, int quantidade, String campoOrdenacao) {

                return notificacaoRepository.findByDataArquivamentoIsNull(null);
    }

    public List<ProcessoNotificacao> retornarTodasNotificacoes() {
        return notificacaoRepository.findByDataAberturaIsNotNullOrderByDataAberturaDesc();
    }    
    
    /**
    * Metodo que busca todas as notificacoes por data inicio e final
    * @param DataAberturaIni Calendar - Data inicial da busca
    * @param DataAberturaFim Calendar - Data final da busca
    * @return List - Lista de notificacoes
    */
    public List<ProcessoNotificacao> retornarNotificacaoPorData(Calendar DataAberturaIni, Calendar DataAberturaFim)
    {
        return notificacaoRepository.findByDataAberturaBetween(DataAberturaIni,
                DataAberturaFim);
    }

    private String genereateCode() {
        return UUID.randomUUID().toString();
    }
}
