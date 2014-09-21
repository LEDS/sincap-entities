package br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt;

import br.ifes.leds.reuse.ledsExceptions.CRUDExceptions.ViolacaoDeRIException;
import br.ifes.leds.reuse.utility.Utility;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Funcionario;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.AtualizacaoEstadoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ProcessoNotificacaoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.AtualizacaoEstado;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CausaNaoDoacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.EstadoNotificacaoEnum;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.ProcessoNotificacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto.CaptacaoDTO;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.dto.ProcessoNotificacaoDTO;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private AplCaptacao aplCaptacao;
    @Autowired
    private AplCausaNaoDoacao aplCausaNaoDoacao;
    @Autowired
    private AtualizacaoEstadoRepository atualizacaoEstadoRepository;
    @Autowired
    private Mapper mapper;
    @Autowired
    private Utility utility;

    /**
     * Metodo que salva uma nova notificação contendo notificacao de obito
     *
     * @param processoNotificacaoDTO - ProcessoNotificacao - Notificacao que
     *                               sera salva
     * @param idFuncionario          - Id do funcionario que criou a notificacao
     * @return long - Retorna o id do ProcessoNotificacao salvo
     */
    public long salvarNovaNotificacao(
            ProcessoNotificacaoDTO processoNotificacaoDTO,
            Long idFuncionario) {

        ProcessoNotificacao notificacao = mapper.map(processoNotificacaoDTO,
                ProcessoNotificacao.class);
        preparaProcessoParaSalvar(notificacao);
        notificacao.getObito().setDataCadastro(Calendar.getInstance());

        aplObito.salvarObito(notificacao.getObito());

        this.addEstadoInicial(notificacao, idFuncionario);
        this.salvarHistorico(notificacao.getHistorico());

        notificacao.setDataAbertura(Calendar.getInstance());

        // TODO - Gerando o codigo - Esse metodo deve ser alterado
        notificacao.setCodigo(genereateCode());

        notificacaoRepository.save(notificacao);
        return notificacao.getId();
    }

    private void preparaProcessoParaSalvar(ProcessoNotificacao notificacao) {
        if (notificacao.getId() != null) {
            ProcessoNotificacao notificacaoBd = notificacaoRepository.findOne(notificacao.getId());
            utility.mergeIds(notificacao, notificacaoBd);
            notificacao.setHistorico(notificacaoBd.getHistoricoModificavel());
            notificacao.setDataAbertura(notificacaoBd.getDataAbertura());
            notificacao.setCodigo(notificacaoBd.getCodigo());
            notificacao.setArquivado(notificacaoBd.isArquivado());
        }
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
            {
        ProcessoNotificacao notificacao = mapper.map(processoNotificacaoDTO,
                ProcessoNotificacao.class);

        preparaProcessoParaSalvar(notificacao);

        this.addNovoEstado(EstadoNotificacaoEnum.AGUARDANDOANALISEENTREVISTA,
                notificacao,
                idFuncionario);
        this.salvarHistorico(notificacao.getHistorico());

        aplEntrevista.salvarEntrevista(notificacao.getEntrevista());

        notificacaoRepository.save(notificacao);

        return notificacao.getId();
    }

    /**
     * Metodo que salva um nova captacao vinculada a um Processo de Notificacao
     *
     * @param captacaoDTO - CaptacaoDTO que sera salvo
     * @param idProcesso  - Id do processo de notificacao
     * @param idCaptador  - Id do captador vinculado a notificacao
     * @return
     */
    public long salvarCaptacao(Long idProcesso, CaptacaoDTO captacaoDTO, Long idCaptador) {

        ProcessoNotificacaoDTO processo = this.obter(idProcesso);
        processo.setCaptacao(captacaoDTO);

        ProcessoNotificacao notificacao = mapearProcessoNotificacaoDTO(processo);

        this.addNovoEstado(EstadoNotificacaoEnum.AGUARDANDOANALISECAPTACAO,
                notificacao,
                idCaptador);

        if (notificacao.getCaptacao().getId() == null)
            notificacao.getCaptacao().setDataCadastro(Calendar.getInstance());

        aplCaptacao.salvarCaptacao(notificacao.getCaptacao());
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
                                   Long idFuncionario) {

        return this.addNovoEstadoNoProcessoNotificacao(
                processoNotificacaoDTO,
                EstadoNotificacaoEnum.EMANALISEOBITO,
                idFuncionario);
    }

    /**
     * Quando um processo de notificacao esta em analisa uma das opcoes eh
     * recusar essa analise, dado a erros que haja na notificacao, por exemplo;
     * Voltar para o estado AGUARDANDOANALISEOBITO.
     *
     * @param processoNotificacaoDTO
     * @param idFuncionario
     * @return
     */
    public Long recusarAnaliseObito(ProcessoNotificacaoDTO processoNotificacaoDTO,
                                    Long idFuncionario) {

        return this.addNovoEstadoNoProcessoNotificacao(
                processoNotificacaoDTO,
                EstadoNotificacaoEnum.AGUARDANDOCORRECAOOBITO,
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
                                    Long idFuncionario) {
        Long situacao;

        if (processoNotificacaoDTO.getCausaNaoDoacao() == null) {
            situacao = this.addNovoEstadoNoProcessoNotificacao(
                    processoNotificacaoDTO,
                    EstadoNotificacaoEnum.AGUARDANDOENTREVISTA,
                    idFuncionario);
        } else {
            situacao = this.addNovoEstadoNoProcessoNotificacao(
                    processoNotificacaoDTO,
                    EstadoNotificacaoEnum.AGUARDANDOARQUIVAMENTO,
                    idFuncionario);
        }

        return situacao;
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
                                        Long idFuncionario) {

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
     * @param idProcessoNotificacao
     * @param idFuncionario
     * @return
     */
    public Long recusarAnaliseEntrevista(Long idProcessoNotificacao,
                                         Long idFuncionario) {

        ProcessoNotificacaoDTO processoNotificacao = obter(idProcessoNotificacao);

        return this.addNovoEstadoNoProcessoNotificacao(
                processoNotificacao,
                EstadoNotificacaoEnum.AGUARDANDOCORRECAOENTREVISTA,
                idFuncionario);
    }

    /**
     * Quando um processo de notificacao etapa entrevsista esta em analisa
     * uma das opcoes eh aceitar essa analise,
     * logo, o estado muda para AGUARDANDOCAPTACAO.
     *
     * @param idProcessoNotificacao
     * @param idFuncionario
     * @return
     */
    public Long validarAnaliseEntrevista(Long idProcessoNotificacao,
                                         Long idFuncionario) {

        ProcessoNotificacaoDTO processoNotificacao = obter(idProcessoNotificacao);

        if (processoNotificacao.getCausaNaoDoacao() == null && processoNotificacao.getEntrevista().isDoacaoAutorizada()) {
            return this.addNovoEstadoNoProcessoNotificacao(
                    processoNotificacao,
                    EstadoNotificacaoEnum.AGUARDANDOCAPTACAO,
                    idFuncionario);
        }

        return this.addNovoEstadoNoProcessoNotificacao(
                processoNotificacao,
                EstadoNotificacaoEnum.AGUARDANDOARQUIVAMENTO,
                idFuncionario);
    }


    /**
     * Quando um processo de notificacao etapa óbito esta em analise
     * uma das opcoes é arquivar o processo,
     * logo, o estado muda para NOTIFICACAOARQUIVADA.
     *
     * @param processoNotificacaoDTO
     * @param idFuncionario
     * @return
     */
    public Long arquivarProcesso(ProcessoNotificacaoDTO processoNotificacaoDTO,
                                 Long idFuncionario) {
        ProcessoNotificacao notificacao = mapearProcessoNotificacaoDTO(processoNotificacaoDTO);
        if (notificacao.getCausaNaoDoacao().getNome() == null
                || notificacao.getCausaNaoDoacao().getTipoNaoDoacao() == null) {
            notificacao.setCausaNaoDoacao(null);
        }
        arquivar(notificacao);

        return this.addNovoEstadoNoProcessoNotificacao(
                processoNotificacaoDTO,
                EstadoNotificacaoEnum.NOTIFICACAOARQUIVADA,
                idFuncionario);
    }

    /**
     * Dado haja causa de nao doacao ou o processo de notificacao chegou ao fim,
     * o estado do mesmo deve mudar para AGUARDANDOARQUIVAMENTO.
     *
     * @param idProcessoNotificacao
     * @param idFuncionario
     * @return
     */
    public Long finalizarProcesso(Long idProcessoNotificacao,
                                  Long idFuncionario) {

        ProcessoNotificacaoDTO processoNotificacao = obter(idProcessoNotificacao);

        return this.addNovoEstadoNoProcessoNotificacao(
                processoNotificacao,
                EstadoNotificacaoEnum.AGUARDANDOARQUIVAMENTO,
                idFuncionario);
    }

    private Long addNovoEstadoNoProcessoNotificacao(
            ProcessoNotificacaoDTO processoNotificacaoDTO,
            EstadoNotificacaoEnum enumEstado,
            Long idFuncionario) {

        ProcessoNotificacao notificacao = mapearProcessoNotificacaoDTO(processoNotificacaoDTO);

        this.addNovoEstado(enumEstado,
                notificacao,
                idFuncionario);

        salvarHistorico(notificacao.getHistorico());
        notificacaoRepository.save(notificacao);

        return notificacao.getId();
    }

    private ProcessoNotificacao mapearProcessoNotificacaoDTO(ProcessoNotificacaoDTO processoNotificacaoDTO) {
        ProcessoNotificacao notificacao = mapper.map(processoNotificacaoDTO, ProcessoNotificacao.class);

        return notificacao;
    }

    public void addNovoEstado(EstadoNotificacaoEnum enumEstado,
                              ProcessoNotificacao processo,
                              Long idFuncionario) {

        AtualizacaoEstado novoEstado = new AtualizacaoEstado();

        novoEstado.setDataAtualizacaos(Calendar.getInstance());
        novoEstado.setEstadoNotificacao(enumEstado);
        novoEstado.setFuncionario(this.getFuncionario(idFuncionario));

        processo.mudarEstadoAtual(novoEstado);
    }

    /**
     * Adiciona o historio de estados na notificacao, linkando o primeiro estado
     *
     * @param notificacao Notificacao a ser adicionado o estado inicial.
     */
    private void addEstadoInicial(ProcessoNotificacao notificacao, Long idFuncionario) {
        AtualizacaoEstado atualizacaoEstado = new AtualizacaoEstado();

        atualizacaoEstado.setFuncionario(this.getFuncionario(idFuncionario));
        atualizacaoEstado.setDataAtualizacaos(Calendar.getInstance());
        atualizacaoEstado.setEstadoNotificacao(EstadoNotificacaoEnum.AGUARDANDOANALISEOBITO);

        notificacao.mudarEstadoAtual(atualizacaoEstado);
    }

    /**
     * Retorna um funcionario dado um id recebido.
     * OBS.: O funcionario nao tem todos os campos preenchidos, apenas o ID
     *
     * @param idFuncionario
     * @return - Retorna um funcionario
     */
    private Funcionario getFuncionario(Long idFuncionario) {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(idFuncionario);
        return funcionario;
    }

    /**
     * Metodo que salva os estados da notificacao
     *
     * @param historico List - Lista de Atualizacoes de estados
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
     * @param notificacao Notificacao - Notificacao que sofera o arquivamoento
     */
    public void arquivar(ProcessoNotificacao notificacao) {
        notificacao.setArquivado(true);
        notificacaoRepository.save(notificacao);
    }

    /**
     * Obter Processo de Notificacao
     *
     * @param id - Id do Processo de Notificacao
     * @return
     */
    public ProcessoNotificacaoDTO obter(Long id) {
        ProcessoNotificacao processoNotificacao = getProcessoNotificacao(id);

        return mapper.map(processoNotificacao, ProcessoNotificacaoDTO.class);
    }

    public ProcessoNotificacao getProcessoNotificacao(Long id) {
        return notificacaoRepository.findOne(id);
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
     * @param DataAberturaIni Calendar - Data inicial da busca
     * @param DataAberturaFim Calendar - Data final da busca
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
     * @param estado Estado que será usado para filtrar as notificações.
     * @return Notificações filtras pelo estado atual.
     */
    public List<ProcessoNotificacaoDTO> retornarNotificacaoPorEstadoAtual(
            EstadoNotificacaoEnum estado) {
        List<ProcessoNotificacao> processosNotificacao = notificacaoRepository
                .findByUltimoEstadoEstadoNotificacaoOrderByUltimoEstadoDataAtualizacaosAsc(estado);

        return utility.mapList(processosNotificacao,
                ProcessoNotificacaoDTO.class);
    }

    /**
     * Metodo que busca todas as notificacoes, pegando apenas as que estão no
     * estado atual indicado.
     *
     * @param estado Estado que será usado para filtrar as notificações.
     * @return Notificações filtras pelo estado atual.
     */
    public List<ProcessoNotificacao> retornarProcessoNotificacaoPorEstadoAtual(
            EstadoNotificacaoEnum estado) {
        List<ProcessoNotificacao> processosNotificacao = notificacaoRepository
                .findByUltimoEstadoEstadoNotificacaoOrderByUltimoEstadoDataAtualizacaosAsc(estado);

        return processosNotificacao;
    }

    private String genereateCode() {
        return UUID.randomUUID().toString();
    }

    public void recusarAnaliseCaptacao(Long idProcesso, Long idFuncionario) {
        ProcessoNotificacaoDTO processoNotificacao = obter(idProcesso);

        this.addNovoEstadoNoProcessoNotificacao(
                processoNotificacao,
                EstadoNotificacaoEnum.AGUARDANDOCORRECAOCAPTACACAO,
                idFuncionario);
    }

    public void confirmarAnaliseCaptacao(Long idProcesso, Long idFuncionario) {
        ProcessoNotificacaoDTO processoNotificacao = obter(idProcesso);

        this.addNovoEstadoNoProcessoNotificacao(
                processoNotificacao,
                EstadoNotificacaoEnum.AGUARDANDOARQUIVAMENTO,
                idFuncionario);
    }

    public CausaNaoDoacao getCausadeDoacaoProcesso(ProcessoNotificacaoDTO processo) {
        return aplCausaNaoDoacao.obter(processo.getCausaNaoDoacao());
    }
    
    public List<ProcessoNotificacao> obterPorPacienteNome(String searchString){
        return notificacaoRepository.findByObitoPacienteNomeLikeIgnoreCase("%" + searchString + "%");
    }

    public void arquivarProcessoNotificacao(Long id, Long idFuncionario){
        ProcessoNotificacaoDTO processoNotificacao = obter(id);

        this.addNovoEstadoNoProcessoNotificacao(
                processoNotificacao,
                EstadoNotificacaoEnum.NOTIFICACAOARQUIVADA,
                idFuncionario);
    }
}
