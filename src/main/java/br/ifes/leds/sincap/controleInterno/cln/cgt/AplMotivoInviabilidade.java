package br.ifes.leds.sincap.controleInterno.cln.cgt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifes.leds.reuse.ledsExceptions.CRUDExceptions.MotivoInviabilidadeEmUsoException;
import br.ifes.leds.reuse.ledsExceptions.CRUDExceptions.MotivoInviabilidadeExistenteException;
import br.ifes.leds.sincap.controleInterno.cgd.MotivoInviabilidadeRepository;
import br.ifes.leds.sincap.controleInterno.cgd.TipoMotivoInviabilidadeRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.MotivoInviabilidade;
import br.ifes.leds.sincap.controleInterno.cln.cdp.TipoMotivoInviabilidade;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.NotificacaoRepository;

@Service
public class AplMotivoInviabilidade {

    @Autowired
    TipoMotivoInviabilidadeRepository TipoMotivoInviabilidadeRepository;

    @Autowired
    MotivoInviabilidadeRepository repos;

    @Autowired
    NotificacaoRepository notificacaoRepository;

    public List<TipoMotivoInviabilidade> getTipoMotivoInviabilidade()
    {
            return TipoMotivoInviabilidadeRepository.findAll();
    }

    public void adicionar(MotivoInviabilidade novoMotivoInviabilidade) throws MotivoInviabilidadeExistenteException
    {
            String nome = novoMotivoInviabilidade.getNome().toUpperCase().trim();

            MotivoInviabilidade motivoInviabilidade = repos.findByNome(nome);

            if (motivoInviabilidade != null) throw new MotivoInviabilidadeExistenteException();

            novoMotivoInviabilidade.setNome(nome);

            repos.save(novoMotivoInviabilidade);
    }

    public void editar(MotivoInviabilidade motivoInviabilidade)
    {
            this.repos.save(motivoInviabilidade);
    }

//	public void excluir(MotivoInviabilidade motivoInviabilidade) throws MotivoInviabilidadeEmUsoException
//	{
//		if(this.notificacaoRepository.findByMotivoInviabilidade(motivoInviabilidade).isEmpty())
//			this.repos.delete(motivoInviabilidade.getId());
//		else
//			throw new MotivoInviabilidadeEmUsoException();
//	}
	
    public List<MotivoInviabilidade> buscarPorTipoMotivoInviabilidade(TipoMotivoInviabilidade tipoMotivoInviabilidade)
    {
            return repos.findByTipoMotivoInviabilidade(tipoMotivoInviabilidade);
    }

    public List<MotivoInviabilidade> buscarPorTipoMotivoInviabilidadeId(Long id)
    {
            return repos.findByTipoMotivoInviabilidadeId(id);
    }

    public List<MotivoInviabilidade> obter()
    {
            return repos.findAll();
    }

    public MotivoInviabilidade buscar(String nome)
    {
            return repos.findByNome(nome);
    }

    public MotivoInviabilidade buscar(Long id)
    {
            return repos.findById(id);
    }
	
}
