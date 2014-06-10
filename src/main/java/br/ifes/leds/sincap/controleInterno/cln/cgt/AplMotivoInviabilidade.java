package br.ifes.leds.sincap.controleInterno.cln.cgt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifes.leds.reuse.ledsExceptions.CRUDExceptions.MotivoInviabilidadeExistenteException;
import br.ifes.leds.sincap.controleInterno.cgd.MotivoInviabilidadeRepository;
import br.ifes.leds.sincap.controleInterno.cgd.TipoMotivoInviabilidadeRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.MotivoInviabilidade;
import br.ifes.leds.sincap.controleInterno.cln.cdp.TipoMotivoInviabilidade;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ProcessoNotificacaoRepository;

@Service
public class AplMotivoInviabilidade {

    @Autowired
    TipoMotivoInviabilidadeRepository tipoMotivoInviabilidadeRepository;

    @Autowired
    MotivoInviabilidadeRepository motivoInviabilidadeRepository;
    
    //@Autowired
    //CaptacaoRepository captacaoRepository;

    @Autowired
    ProcessoNotificacaoRepository notificacaoRepository;

    public List<TipoMotivoInviabilidade> getTipoMotivoInviabilidade() {
        return tipoMotivoInviabilidadeRepository.findAll();
    }

    public void adicionar(MotivoInviabilidade novoMotivoInviabilidade) throws MotivoInviabilidadeExistenteException {
        String nome = novoMotivoInviabilidade.getNome().toUpperCase().trim();

        MotivoInviabilidade motivoInviabilidade = motivoInviabilidadeRepository.findByNome(nome);

        if (motivoInviabilidade != null) {
            throw new MotivoInviabilidadeExistenteException();
        }

        novoMotivoInviabilidade.setNome(nome);

        motivoInviabilidadeRepository.save(novoMotivoInviabilidade);
    }

    public void editar(MotivoInviabilidade motivoInviabilidade) {
        this.motivoInviabilidadeRepository.save(motivoInviabilidade);
    }

//    public void excluir(MotivoInviabilidade motivoInviabilidade) throws MotivoInviabilidadeEmUsoException {
//        if (this.captacaoRepository.findByMotivosInviabilidade(motivoInviabilidade).isEmpty()) {
//            this.motivoInviabilidadeRepository.delete(motivoInviabilidade.getId());
//        } else {
//            throw new MotivoInviabilidadeEmUsoException();
//        }
//    }

    public List<MotivoInviabilidade> buscarPorTipoMotivoInviabilidade(TipoMotivoInviabilidade tipoMotivoInviabilidade) {
        return motivoInviabilidadeRepository.findByTipoMotivoInviabilidade(tipoMotivoInviabilidade);
    }

    public List<MotivoInviabilidade> buscarPorTipoMotivoInviabilidadeId(Long id) {
        return motivoInviabilidadeRepository.findByTipoMotivoInviabilidadeId(id);
    }

    public List<MotivoInviabilidade> obter() {
        return motivoInviabilidadeRepository.findAll();
    }

    public MotivoInviabilidade buscar(String nome) {
        return motivoInviabilidadeRepository.findByNome(nome);
    }

    public MotivoInviabilidade buscar(Long id) {
        return motivoInviabilidadeRepository.findById(id);
    }

}
