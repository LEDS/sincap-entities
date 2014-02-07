/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.controleInterno.cln.cgt;

import java.util.List;

import br.ifes.leds.sincap.controleInterno.cgd.TipoMotivoInviabilidadeRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.TipoMotivoInviabilidade;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Notificacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 20112BSI0083
 */
@Service
public class AplTipoMotivoInviabilidade {
    @Autowired
    TipoMotivoInviabilidadeRepository TipoMotivoInviabilidadeRepository;
    
    public TipoMotivoInviabilidade obter(String nome)
    {
        return TipoMotivoInviabilidadeRepository.findByNome(nome);
    }
    
    public List<TipoMotivoInviabilidade> obter() throws Exception{
		return this.TipoMotivoInviabilidadeRepository.findAll();
	}
    
    public TipoMotivoInviabilidade obter(Long id) throws Exception{
		return this.TipoMotivoInviabilidadeRepository.findOne(id);
	}
    
}
