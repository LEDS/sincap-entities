 package br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt;

 import br.ifes.leds.reuse.ledsExceptions.CRUDExceptions.ViolacaoDeRIException;
 import br.ifes.leds.reuse.utility.Utility;
 import br.ifes.leds.sincap.controleInterno.cln.cdp.Funcionario;
 import br.ifes.leds.sincap.gerenciaNotificacao.cgd.AtualizacaoEstadoRepository;
 import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ProcessoNotificacaoRepository;
 import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.AtualizacaoEstado;
 import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.DTO.ProcessoNotificacaoDTO;
 import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.EstadoNotificacaoEnum;
 import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.ProcessoNotificacao;
 import org.dozer.Mapper;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;

 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.List;
 import java.util.UUID;

/**
 * AplProcessoNotificacao.java
 *
 * @author 20091BSI0273 Classe de servico para regras de negocio da notificacao
 */
@Service
public class AplProcessoNotificacao {

    @Autowired
    private ProcessoNotificacaoRepository notificacaoRepository;
    @Autowired
    private AplObito aplObito;
    @Autowired
    private AplEntrevista aplEntrevista;
    @Autowired
    private AtualizacaoEstadoRepository atualizacaoEstadoRepository;
    @Autowired
    private Mapper mapper;
    @Autowired
    private Utility utility;

    /**
     * Metodo que salva uma nova notificação contendo notificacao de obito
     * 
     * @param processoNotificacaoDTO
     *            - ProcessoNotificacao - Notificacao que sera salva
     * @param idFuncionario - Id do funcionario que criou a notificacao
     * @return long - Retorna o id do ProcessoNotificacao salvo
     * @throws ViolacaoDeRIException
     */
    public long salvarNovaNotificacao(
            ProcessoNotificacaoDTO processoNotificacaoDTO,
            Long idFuncionario)
            throws ViolacaoDeRIException {

        ProcessoNotificacao notificacao = mapper.map(processoNotificacaoDTO,
                ProcessoNotificacao.class);

        aplObito.salvarObito(notificacao.getObito());
        notificacao.setCausaNaoDoacao(null);
        notificacao.setEntrevista(null);
        
        this.addEstadoInicial(notificacao, idFuncionario);
        this.salvarHistorico(notificacao.getHistorico());

        notificacao.setDataAbertura(Calendar.getInstance());

        // TODO - Gerando o codigo - Esse metodo deve ser alterado
        notificacao.setCodigo(genereateCode());

        notificacaoRepository.save(notificacao);
        return notificacao.getId();
    }

    /**
     * Metodo que salva uma nova entrevista vinculada a um Processo de
     * Notificacao
     * 
     * @param processoNotificacaoDTO
     * @param idFuncionario
     * @return
     * @throws ViolacaoDeRIException
     */
    public long salvarEntrevista(ProcessoNotificacaoDTO processoNotificacaoDTO,
            Long idFuncionario)
            throws ViolacaoDeRIException {
        ProcessoNotificacao notificacao = mapper.map(processoNotificacaoDTO,
                ProcessoNotificacao.class);
        
        this.addNovoEstado(EstadoNotificacaoEnum.AGUARDANDOANALISEENTREVISTA, 
                notificacao.getHistorico(),
                idFuncionario);

        if (notificacao.getEntrevista().getDataEntrevista() == null) {
            notificacao.setEntrevista(null);
        } else {
            aplEntrevista.salvarEntrevista(notificacao.getEntrevista());
        }
        if (notificacao.doacaoAutorizado()) {
            notificacao.setCausaNaoDoacao(null);
        }

        notificacaoRepository.save(notificacao);

        return notificacao.getId();
    }

    /**
     * Metodo que salva um nova captacao vinculada a um Processo de Notificacao
     * 
     * @param processoNotificacaoDTO
     * @return
     */
    public long salvarCaptacao(ProcessoNotificacaoDTO processoNotificacaoDTO) {
        ProcessoNotificacao notificacao = mapper.map(processoNotificacaoDTO,
                ProcessoNotificacao.class);

        // aplCaptacao.salvarCaptacao(notificacao.getCaptacao());
        this.salvarHistorico(notificacao.getHistorico());
        notificacaoRepository.save(notificacao);

        return notificacao.getId();
    }
    
    /**
     * Quando um processo de notificacao na etapa obito
     * entra para ser analisado, 
     * o mesmo troca o seu estado para EMANALISEOBITO
     * 
     * @param processoNotificacaoDTO
     * @param idFuncionario
     * @return 
     */
    public long entrarAnaliseObito(ProcessoNotificacaoDTO processoNotificacaoDTO, 
            Long idFuncionario){        
        
        return this.addNovoEstadoNoProcessoNotificacao(
                processoNotificacaoDTO, 
                EstadoNotificacaoEnum.EMANALISEOBITO, 
                idFuncionario);
    }
    
    /**
     * Quando um processo de notificacao esta em analisa
     * uma das opcoes eh recusar essa analise,
     * dado a erros que haja na notificacao, por exemplo;
     * Voltar para o estado AGUARDANDOANALISEOBITO.
     * 
     * @param processoNotificacaoDTO
     * @param idFuncionario
     * @return 
     */
    public Long recusarAnaliseObito(ProcessoNotificacaoDTO processoNotificacaoDTO, 
            Long idFuncionario){
        
        return this.addNovoEstadoNoProcessoNotificacao(
                processoNotificacaoDTO, 
                EstadoNotificacaoEnum.AGUARDANDOANALISEOBITO, 
                idFuncionario);
    }
    
    /**
     * Quando um processo de notificacao etapa obito esta em analisa
     * uma das opcoes eh aceitar essa analise,
     * logo, o estado muda para AGUARDANDOENTREVISTA.
     * 
     * @param processoNotificacaoDTO
     * @param idFuncionario
     * @return 
     */
    public Long validarAnaliseObito(ProcessoNotificacaoDTO processoNotificacaoDTO, 
            Long idFuncionario){
        
        return this.addNovoEstadoNoProcessoNotificacao(
                processoNotificacaoDTO, 
                EstadoNotificacaoEnum.AGUARDANDOENTREVISTA, 
                idFuncionario);
    }
    
    /**
     * Quando um processo de notificacao na etapa entrevista
     * entra para ser analisado, 
     * o mesmo troca o seu estado para EMANALISEENTREVISTA
     * 
     * @param processoNotificacaoDTO
     * @param idFuncionario
     * @return 
     */
    public Long entrarAnaliseEntrevista(ProcessoNotificacaoDTO processoNotificacaoDTO, 
            Long idFuncionario){
        
        return this.addNovoEstadoNoProcessoNotificacao(
                processoNotificacaoDTO, 
                EstadoNotificacaoEnum.EMANALISEENTREVISTA,
                idFuncionario);
    }
    
    /**
     * Quando um processo de notificacao etapa entrevista esta em analisa
     * uma das opcoes eh recusar essa analise,
     * dado a erros que haja na notificacao, por exemplo;
     * Voltar para o estado AGUARDANDOENTREVISTA.
     * 
     * @param processoNotificacaoDTO
     * @param idFuncionario
     * @return 
     */
    public Long recusarAnaliseEntrevista(ProcessoNotificacaoDTO processoNotificacaoDTO, 
            Long idFuncionario){
        
        return this.addNovoEstadoNoProcessoNotificacao(
                processoNotificacaoDTO, 
                EstadoNotificacaoEnum.AGUARDANDOENTREVISTA,
                idFuncionario);
    }
    
    /**
     * Quando um processo de notificacao etapa entrevsista esta em analisa
     * uma das opcoes eh aceitar essa analise,
     * logo, o estado muda para AGUARDANDOCAPTACAO.
     * 
     * @param processoNotificacaoDTO
     * @param idFuncionario
     * @return 
     */
    public Long validarAnaliseEntrevista(ProcessoNotificacaoDTO processoNotificacaoDTO, 
            Long idFuncionario){
        
        return this.addNovoEstadoNoProcessoNotificacao(
                processoNotificacaoDTO, 
                EstadoNotificacaoEnum.AGUARDANDOCAPTACAO,
                idFuncionario);
    }
    
    /**
     * Dado haja causa de nao doacao ou o processo de notificacao chegou ao fim,
     * o estado do mesmo deve mudar para AGUARDANDOARQUIVAMENTO.
     * 
     * @param processoNotificacaoDTO
     * @param idFuncionario
     * @return 
     */
    public Long finalizarProcesso(ProcessoNotificacaoDTO processoNotificacaoDTO, 
            Long idFuncionario){
        
        return this.addNovoEstadoNoProcessoNotificacao(
                processoNotificacaoDTO, 
                EstadoNotificacaoEnum.AGUARDANDOARQUIVAMENTO,
                idFuncionario);
    }
    
    private Long addNovoEstadoNoProcessoNotificacao(
            ProcessoNotificacaoDTO processoNotificacaoDTO, 
            EstadoNotificacaoEnum enumEstado,
            Long idFuncionario){
        
        ProcessoNotificacao notificacao = mapper.map(processoNotificacaoDTO, ProcessoNotificacao.class);

        notificacao.setCausaNaoDoacao(null);

        this.addNovoEstado(enumEstado, 
                notificacao.getHistorico(), 
                idFuncionario);
        
        notificacaoRepository.save(notificacao);
        return notificacao.getId();
    }
    
    public void addNovoEstado(EstadoNotificacaoEnum enumEstado, 
            List<AtualizacaoEstado> historico,
            Long idFuncionario){
        
        AtualizacaoEstado novoEstado = new AtualizacaoEstado();
        
        novoEstado.setDataAtualizacaos(Calendar.getInstance());
        novoEstado.setEstadoNotificacao(enumEstado);
        novoEstado.setFuncionario(this.getFuncionario(idFuncionario));
        
        historico.add(novoEstado);
        this.salvarHistorico(historico);
    }
    
    /**
     * Adiciona o historio de estados na notificacao, linkando o primeiro estado
     * 
     * @param historico - Historico de atualizacoes da notificacao
     */
    private void addEstadoInicial(ProcessoNotificacao notificacao, Long idFuncionario) {
        List<AtualizacaoEstado> historico = new ArrayList<>();
        AtualizacaoEstado atualizacaoEstado = new AtualizacaoEstado();
        
        atualizacaoEstado.setFuncionario(this.getFuncionario(idFuncionario));
        atualizacaoEstado.setDataAtualizacaos(Calendar.getInstance());
        atualizacaoEstado.setEstadoNotificacao(EstadoNotificacaoEnum.AGUARDANDOANALISEOBITO);
        
        historico.add(atualizacaoEstado);
        notificacao.setHistorico(historico);
    }
    
    /**
     * Retorna um funcionario dado um id recebido.
     * OBS.: O funcionario nao tem todos os campos preenchidos, apenas o ID
     * 
     * @param idFuncionario
     * @return  - Retorna um funcionario
     */
    private Funcionario getFuncionario(Long idFuncionario){
        Funcionario funcionario = new Funcionario();
        funcionario.setId(idFuncionario);
        return funcionario;
    }

    /**
     * Metodo que salva os estados da notificacao
     * 
     * @param historico
     *            List - Lista de Atualizacoes de estados
     */
    private void salvarHistorico(List<AtualizacaoEstado> historico) {
        if (!historico.isEmpty()) {
            for (AtualizacaoEstado estado : historico) {
                atualizacaoEstadoRepository.save(estado);
            }
        }
    }

    /**
     * Obtém todos os Processos de Notificacao.
     * 
     * @return Uma lista de {@code ProcessoNotificacaoDTO}.
     */
    public List<ProcessoNotificacaoDTO> obterTodasNotificacoes() {
        return utility.mapList(notificacaoRepository.findAll(),
                ProcessoNotificacaoDTO.class);
    }

    /**
     * Metodo que arquiva uma notificacao
     * 
     * @param notificacao
     *            Notificacao - Notificacao que sofera o arquivamoento
     */
    public void arquivar(ProcessoNotificacao notificacao) {
        notificacao.setArquivado(true);
        notificacaoRepository.save(notificacao);
    }

    /**
     * Obter Processo de Notificacao
     * 
     * @param id
     *            - Id do Processo de Notificacao
     * @return
     */
    public ProcessoNotificacaoDTO obter(Long id) {
        ProcessoNotificacao processoNotificacao = notificacaoRepository
                .findOne(id);

        return mapper.map(processoNotificacao, ProcessoNotificacaoDTO.class);
    }

    /**
     * Metodo que busca todas as notificacoes nao arquivadas
     * 
     * @return List - Lista de notificacoes
     */
    public List<ProcessoNotificacaoDTO> retornarNotificacaoNaoArquivada() {
        List<ProcessoNotificacao> processoNotificacaos = notificacaoRepository
                .findByDataArquivamentoIsNullOrderByDataAberturaDesc();

        return utility.mapList(processoNotificacaos,
                ProcessoNotificacaoDTO.class);
    }

    /**
     * Metodo que busca todas as notificacoes arquivadas
     * 
     * @return List - Lista de notificacoes
     */
    public List<ProcessoNotificacaoDTO> retornarNotificacaoArquivada() {
        List<ProcessoNotificacao> processoNotificacaos = notificacaoRepository
                .findByDataArquivamentoIsNotNullOrderByDataAberturaDesc();

        return utility.mapList(processoNotificacaos,
                ProcessoNotificacaoDTO.class);
    }

    /**
     * Metodo que busca todas as notificacoes por data inicio e final
     * 
     * @param DataAberturaIni
     *            Calendar - Data inicial da busca
     * @param DataAberturaFim
     *            Calendar - Data final da busca
     * @return List - Lista de notificacoes
     */
    public List<ProcessoNotificacaoDTO> retornarNotificacaoPorData(
            Calendar DataAberturaIni, Calendar DataAberturaFim) {
        List<ProcessoNotificacao> processoNotificacaos = notificacaoRepository
                .findByDataAberturaBetween(DataAberturaIni, DataAberturaFim);

        return utility.mapList(processoNotificacaos,
                ProcessoNotificacaoDTO.class);
    }

    /**
     * Metodo que busca todas as notificacoes, pegando apenas as que estão no
     * estado atual indicado.
     *
     * @param estado
     *            Estado que será usado para filtrar as notificações.
     * @return Notificações filtras pelo estado atual.
     */
    public List<ProcessoNotificacaoDTO> retornarNotificacaoPorEstadoAtual(
            EstadoNotificacaoEnum estado) {
        List<ProcessoNotificacao> processosNotificacao = notificacaoRepository
                .findByLastEstadoNotificao(estado);

        return utility.mapList(processosNotificacao,
                ProcessoNotificacaoDTO.class);
    }

    /**
     * Metodo que busca todas as notificacoes, pegando apenas as que estão no
     * estado atual indicado.
     *
     * @param estado
     *            Estado que será usado para filtrar as notificações.
     * @return Notificações filtras pelo estado atual.
     */
    public List<ProcessoNotificacao> retornarProcessoNotificacaoPorEstadoAtual(
            EstadoNotificacaoEnum estado) {
        List<ProcessoNotificacao> processosNotificacao = notificacaoRepository
                .findByLastEstadoNotificao(estado);

        return processosNotificacao;
    }

    private String genereateCode() {
        return UUID.randomUUID().toString();
    }
}
