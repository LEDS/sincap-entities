package br.ifes.leds.sincap.controleInterno.cln.cgt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifes.leds.reuse.utility.Utility;
import br.ifes.leds.sincap.controleInterno.cgd.SetorRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Setor;
import br.ifes.leds.sincap.controleInterno.cln.cdp.dto.SetorDTO;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.CausaMortisRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.CausaNaoDoacaoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CausaMortis;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CausaNaoDoacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoNaoDoacao;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.DTO.CausaNaoDoacaoDTO;

@Service
public class AplCadastroInterno {

    @Autowired
    private SetorRepository setorRepository;
    @Autowired
    private CausaMortisRepository causaObitoRepository;
    @Autowired
    private CausaNaoDoacaoRepository causaNaoDoacaoRepository;
    @Autowired
    private Utility utility;

    public Setor obterSetorPorId(Long id) {
        return this.setorRepository.findOne(id);
    }

    public List<SetorDTO> obterSetorPorHospital(Long hospitalId) {
        List<Setor> listaSetores = this.setorRepository
                .findByHospitalIdOrderByNomeAsc(hospitalId);
        return utility.mapList(listaSetores, SetorDTO.class);
    }

    public List<CausaNaoDoacaoDTO> obterCausaNaoDoacaoContraIndMedica() {
        return obterCausaNaoDoacao(TipoNaoDoacao.CONTRAINDICACAO_MEDICA);
    }

    public List<CausaNaoDoacaoDTO> obterCausaNaoDoacao(TipoNaoDoacao tipo) {
        List<CausaNaoDoacao> listaCausas = this.causaNaoDoacaoRepository
                .findByTipoNaoDoacao(tipo);
        return utility.mapList(listaCausas, CausaNaoDoacaoDTO.class);
    }

    /* Causas de Obito */
    public List<CausaMortis> obterTodosCausaObito() {
        return this.causaObitoRepository.findAll();
    }

    public CausaMortis obterCausaObitoPorId(Long id) {
        return this.causaObitoRepository.findOne(id);
    }
}
