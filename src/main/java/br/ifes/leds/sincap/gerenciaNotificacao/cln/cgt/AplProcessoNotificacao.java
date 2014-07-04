package br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifes.leds.reuse.ledsExceptions.CRUDExceptions.ViolacaoDeRIException;
import br.ifes.leds.reuse.utility.Utility;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.AtualizacaoEstadoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ProcessoNotificacaoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.AtualizacaoEstado;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.EstadoNotificacaoEnum;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.ProcessoNotificacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.DTO.ProcessoNotificacaoDTO;

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
     * @return long - Retorna o id do ProcessoNotificacao salvo
     * @throws ViolacaoDeRIException
     */
    public long salvarNovaNotificacao(
            ProcessoNotificacaoDTO processoNotificacaoDTO)
            throws ViolacaoDeRIException {

        ProcessoNotificacao notificacao = mapper.map(processoNotificacaoDTO,
                ProcessoNotificacao.class);

        aplObito.salvarObito(notificacao.getObito());
        notificacao.setCausaNaoDoacao(null);
        notificacao.setEntrevista(null);
        
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
     * @return
     * @throws ViolacaoDeRIException
     */
    public long salvarEntrevista(ProcessoNotificacaoDTO processoNotificacaoDTO)
            throws ViolacaoDeRIException {
        ProcessoNotificacao notificacao = mapper.map(processoNotificacaoDTO,
                ProcessoNotificacao.class);

        aplEntrevista.setEntrevista(notificacao.getEntrevista());
        this.salvarHistorico(notificacao.getHistorico());
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
     * Metodo que atualiza o Processo de Notificacao, essa etapa irá acontecer:
     * - Quando o usuario for "Analisar Obito" - Quando estiver
     * "Aguardando Entrevista" - Quando o usuario for "Analisar Entrevista" -
     * Quando estiver "Aguardando Captacao" - Quando o usuario for
     * "Analisar Captacao" - Quando estiver "Aguardando Arquivamento"
     * 
     * @param processoNotificacaoDTO
     *            - Processo de notificacao DTO
     * @return - Returna o processo de notificacao salvo
     */
    public long atualizarEstadoProcessoNotificacao(
            ProcessoNotificacaoDTO processoNotificacaoDTO) {
        ProcessoNotificacao notificacao = mapper.map(processoNotificacaoDTO,
                ProcessoNotificacao.class);
        this.salvarHistorico(notificacao.getHistorico());

        notificacaoRepository.save(notificacao);

        return notificacao.getId();
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

    private String genereateCode() {
        return UUID.randomUUID().toString();
    }
}
