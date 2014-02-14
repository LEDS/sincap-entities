package br.ifes.leds.sincap.controleInterno.cln.cgt;

import br.ifes.leds.sincap.controleInterno.cgd.MotivoRecusaRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifes.leds.sincap.controleInterno.cgd.SetorRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.MotivoRecusa;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Setor;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.CausaObitoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CausaMortis;
import org.springframework.data.domain.Sort;

@Service
public class AplCadastroInterno {


	@Autowired
	private SetorRepository setorRepository;
	@Autowired
	private CausaObitoRepository causaObitoRepository;
        @Autowired
        private MotivoRecusaRepository motivoRecusaRepository;
        
        //TODO Add sorte para ordenar os elementos da lista
	
	public Setor obterSetorPorId(Long id){
		return this.setorRepository.findOne(id);
	}
	
	public List<Setor> obterSetorPorHospital(Long hospitalId){
		return this.setorRepository.findByHospitalId(hospitalId); 
	}
	/* Causas de Obito */
	public List<CausaMortis> obterTodosCausaObito(){
		return this.causaObitoRepository.findAll();
	}
	
	public CausaMortis obterCausaObitoPorId(Long id){
		return this.causaObitoRepository.findOne(id);
	}
   
        public List<MotivoRecusa> obterTodosRecusaFamiliar(){
            
            Sort sort = new Sort(Sort.Direction.ASC, "nome");
            
            return this.motivoRecusaRepository.findAll(sort);
        }
	
//	
}
