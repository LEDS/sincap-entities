/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.controleInterno.cln.cgt;
import br.ifes.leds.reuse.endereco.cgd.EnderecoRepository;
import br.ifes.leds.reuse.ledsExceptions.CRUDExceptions.BancoOlhosEmUsoException;
import br.ifes.leds.sincap.controleInterno.cgd.TelefoneRepository;
import br.ifes.leds.sincap.controleInterno.cgd.BancoOlhosRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.BancoOlhos;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.NotificacaoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author 20112BSI0083
 */
@Service
public class AplBancoOlhos {
    @Autowired 
    BancoOlhosRepository bancoOlhosRepository;
    @Autowired
    TelefoneRepository telefoneRepository;
    @Autowired
    EnderecoRepository enderecoRepository;
    @Autowired
    NotificacaoRepository notificacaoRepository;
        
    
    public void cadastrar(BancoOlhos bancoOlhos) {

        for (Telefone telefone : bancoOlhos.getTelefones()) {
            telefoneRepository.save(telefone);
        }

        enderecoRepository.save(bancoOlhos.getEndereco());
        bancoOlhosRepository.save(bancoOlhos);
    }

    public void update(BancoOlhos bancoOlhos) {
        bancoOlhosRepository.save(bancoOlhos);
    }

    public void delete(Long id) throws BancoOlhosEmUsoException {

        if (notificacaoRepository.findByInstituicaoId(id).isEmpty()) {
            bancoOlhosRepository.delete(id);
        } else {
            throw new BancoOlhosEmUsoException();
        }
    }

    public List<BancoOlhos> obter(String nome) {
        return bancoOlhosRepository.findByNome(nome);
    }

    public BancoOlhos obter(Long id) {
        return bancoOlhosRepository.findOne(id);
    }

    public List<BancoOlhos> obter(Pageable pageable) {
        return bancoOlhosRepository.findAll(pageable).getContent();
    }

    public List<BancoOlhos> obter() {
        return bancoOlhosRepository.findAll();
    }

    public Long quantidade() {
        return bancoOlhosRepository.count();
    }
}
