package br.ifes.leds.sincap.controleInterno.cln.cgt;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifes.leds.sincap.controleInterno.cgd.HospitalRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Hospital;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Setor;
import org.springframework.data.domain.Pageable;


@Service
public class AplHospital {

	@Autowired
	private HospitalRepository repository;
	
	public void cadastrar(Hospital hospital){
		repository.save(hospital);
	}
	
	public void update(Hospital hospital){
		repository.save(hospital);
	}
	
	public void delete(Hospital hospital){
		repository.delete(hospital);
	}
	
	/*public List<Hospital> obterTodos(Sort ordem){
		return repository.findAll(ordem);
	}
        */
	
	//--foi um teste -- mas ele busca os dados usando como chave o nome
	public List<Hospital> obter(String nome)
	{
		return repository.findByNome(nome);
	}
        
	public Hospital obter(Long id) {
		return repository.findOne(id);
	}

	public List<Hospital> obter(Pageable pageable) {
		return repository.findAll(pageable).getContent();
	}
	
	public List<Hospital> obter() {
		return repository.findAll();
	}

	public Long quantidade() {
		return repository.count();
	}

	public void addSetor(Setor setor, Long idHospital) {
		Hospital hospital = this.repository.findOne(idHospital);
		hospital.addSetor(setor);
		this.repository.save(hospital);
	}

	public void removerSetor(Setor setor, Long idHospital) {
		Hospital hospital = this.repository.findOne(idHospital);
		hospital.removeSetor(setor);
	}
}
