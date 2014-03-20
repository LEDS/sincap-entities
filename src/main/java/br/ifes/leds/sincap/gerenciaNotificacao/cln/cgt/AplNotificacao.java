package br.ifes.leds.sincap.gerenciaNotificacao.cln.cgt;

import br.ifes.leds.reuse.endereco.cdp.Endereco;
import br.ifes.leds.reuse.endereco.cgd.EnderecoRepository;
import br.ifes.leds.sincap.controleInterno.cgd.MotivoRecusaRepository;
import br.ifes.leds.sincap.controleInterno.cgd.TelefoneRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.CaptacaoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.CausaObitoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.DoacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifes.leds.sincap.gerenciaNotificacao.cgd.NotificacaoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ObitoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.PacienteRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ResponsavelRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.TestemunhaRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Captacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Doacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Notificacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Obito;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Paciente;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Responsavel;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
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

    @Autowired
    private DoacaoRepository doacaoRepository;

    @Autowired
    private TestemunhaRepository testemunhaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private TelefoneRepository telefoneRepository;

    @Autowired
    private CaptacaoRepository captacaoRepository;

    public void salvar(Notificacao notificacao) {
        Obito obito = notificacao.getObito();
        salvarObito(obito);

        //Gerando o codigo
        notificacao.setCodigo(genereateCode());
        notificacao.setDataAbertura(Calendar.getInstance());
        notificacaoRepository.save(notificacao);
    }

    public void arquivar(Notificacao notificacao) {
        notificacaoRepository.save(notificacao);
    }

    public List<Notificacao> obter() {
        return notificacaoRepository.findAll();
    }

    public Notificacao getNotificacao(Long id) {
        return notificacaoRepository.findOne(id);
    }

    public Obito getObito(Long id) {
        return obitoRepository.findOne(id);
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
        Doacao doacao = paciente.getDoacao();
        this.salvarDoacao(doacao);

        //Salvando responsavel
        Responsavel responsavel = paciente.getResponsavel();
        Endereco ender = paciente.getEndereco();
        List<Telefone> telefones = responsavel.getTelefones();
        telefoneRepository.save(telefones);
//        for(Telefone telefone : telefones){
//            telefoneRepository.save(telefone);
//        }
        responsavelRepository.save(responsavel);
        enderecoRepository.save(ender);
        pacienteRepository.save(paciente);

    }

    public List<Notificacao> retornarNotificacaoNaoArquivada() {
        return notificacaoRepository.findByDataArquivamentoIsNullOrderByDataAberturaDesc();
    }

    public List<Notificacao> retornarNotificacaoArquivada() {
        return notificacaoRepository.findByDataArquivamentoIsNotNullOrderByDataAberturaDesc();
    }

    public List<Notificacao> retornarNotificacaoNaoArquivada(int valorInicial, int quantidade, String campoOrdenacao) {

        Sort sort = new Sort(Sort.Direction.ASC, campoOrdenacao);

        Pageable pageable = new PageRequest(valorInicial, quantidade, sort);

        return notificacaoRepository.findByDataArquivamentoIsNull(null);
    }

    public List<Notificacao> retornarTodasNotificacoes() {
        return notificacaoRepository.findByDataAberturaIsNotNullOrderByDataAberturaDesc();
    }

    private Doacao salvarDoacao(Doacao doacao) {

        Captacao captacao = doacao.getCaptacao();

        Set<Responsavel> resp = doacao.getResponsaveis();
        for (Responsavel responsavel : resp) {
            Endereco ender = responsavel.getEndereco();
            List<Telefone> telefones = responsavel.getTelefones();
            for (Telefone tel : telefones) {
                telefoneRepository.save(tel);
            }
            enderecoRepository.save(ender);
        }

        captacaoRepository.save(captacao);
        responsavelRepository.save(doacao.getResponsaveis());
        testemunhaRepository.save(doacao.getTestemunhas());

        return doacaoRepository.save(doacao);
    }

    private String genereateCode() {
        return UUID.randomUUID().toString();
    }

    public List<Notificacao> retornarNotificacao(Long idHospital) {
        return notificacaoRepository.findByInstituicaoId(idHospital);
    }

}
