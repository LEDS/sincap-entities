/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.controleInterno.cln.cgt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifes.leds.reuse.ledsExceptions.CRUDExceptions.SetorExistenteException;
import br.ifes.leds.sincap.controleInterno.cgd.SetorRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Hospital;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Setor;

/**
 *
 * @author 20131BSI0173
 */
@Service
public class AplSetor {
    @Autowired
    private SetorRepository repository;
    
    
    public List<Setor> buscar(Hospital hospital)
    {
        return this.repository.findByHospital(hospital);
    }
    
    public List<Setor> buscar(Long idHospital)
    {
        return this.repository.findByHospitalId(idHospital);
    }
    
    public void addHospital(Hospital hospital, Long idSetor)
    {
        Setor setor = this.repository.findOne(idSetor);
        setor.addHospital(hospital);
        this.repository.save(setor);
    }
    
    public void removeHospital(Hospital hospital, Long idSetor)
    {
        Setor setor = this.repository.findOne(idSetor);
        setor.removeHospital(hospital);
    }
    
    public Setor buscarSetor(Long idSetor){
    	return this.repository.findOne(idSetor);
    }
    
    public void adicionar(Setor setor) throws SetorExistenteException{
    	
    	String nomeSetor = setor.getNome().toUpperCase().trim();
		
		Setor setorBanco = repository.findByNome(nomeSetor);
		
		if (setorBanco != null) throw new SetorExistenteException();
				
		setor.setNome(nomeSetor);
		
		repository.save(setor);
    }
    
    public List<Setor> obter(){
    	return this.repository.findAll();
    }
    
    public void excluir(Long id){
    	this.repository.delete(id);
    }
}
