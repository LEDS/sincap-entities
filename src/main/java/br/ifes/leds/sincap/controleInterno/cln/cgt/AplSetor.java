/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.controleInterno.cln.cgt;

import br.ifes.leds.reuse.ledsExceptions.CRUDExceptions.SetorEmUsoException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifes.leds.reuse.ledsExceptions.CRUDExceptions.SetorExistenteException;
import br.ifes.leds.reuse.utility.Utility;
import br.ifes.leds.sincap.controleInterno.cgd.HospitalRepository;
import br.ifes.leds.sincap.controleInterno.cgd.SetorRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Hospital;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Setor;
import br.ifes.leds.sincap.controleInterno.cln.cdp.dto.SetorDTO;
import java.util.ArrayList;
import org.dozer.Mapper;

/**
 *
 * @author 20131BSI0173
 */
@Service
public class AplSetor {
    @Autowired
    private Mapper mapper;
    
    @Autowired
    private SetorRepository setorRepository;
    
    @Autowired
    private HospitalRepository hospitalRepository;
    
    @Autowired
    private Utility utility;
    
    /** Método para buscar uma lista de setores por hospital.
     * @param hospital - objeto Hospital.
     * @return lista de setores.
     */
    public List<Setor> buscar(Hospital hospital)
    {
        return this.setorRepository.findByHospital(hospital);
    }
    
    /** Método para buscar uma lista de setores pela id de um hospital.
     * @param idHospital - id do Hospital.
     * @return lista de setores.
     */
    public List<Setor> buscar(Long idHospital)
    {
        return this.setorRepository.findByHospitalIdOrderByNomeAsc(idHospital);
    }
    
    /** Método para adicionar um setor a um hospital.
     * @param hospital - objeto Hospital.
     * @param idSetor  - id do Setor.      
     */
    public void addHospital(Hospital hospital, Long idSetor)
    {
        Setor setor = this.setorRepository.findOne(idSetor);
        setor.addHospital(hospital);
        this.setorRepository.save(setor);
    }
    
    /** Método para remover um setor de um hospital.
     * @param hospital - objeto Hospital.
     * @param idSetor  - id do Setor.      
     */
    
    public void removeHospital(Hospital hospital, Long idSetor)
    {
        Setor setor = this.setorRepository.findOne(idSetor);
        setor.removeHospital(hospital);
    }
    
    /** Método para buscar um setor de um hospital pelo seu id.
      * @param idSetor  - id do Setor.   
      * @return objeto setor.
     */
    public Setor buscarSetor(Long idSetor){
    	return this.setorRepository.findOne(idSetor);
    }
    
    public Setor buscarSetor(String nome){
        return this.setorRepository.findByNome(nome);
    }
    
    /** Método para adicionar um setor à partir de um SetorDTO.
      * @param setorDTO  - objeto SetorDTO.   
     */
    public void adicionar(SetorDTO setorDTO) throws SetorExistenteException{
        adicionar(mapper.map(setorDTO, Setor.class));        
    }
    
    /** Método para adicionar um setor.
      * @param setor  - objeto Setor.   
     */
    public void adicionar(Setor setor) throws SetorExistenteException{
    	
    	String nomeSetor = setor.getNome().toUpperCase().trim();
		
		if (this.existe(nomeSetor)) 
                    throw new SetorExistenteException();
                else{				
                    setor.setNome(nomeSetor);
                    setorRepository.save(setor);
                }
    }
    
    /**
     * 
     * @return Lista de SetorDTO com o valor "boolean usado" já preenchido.
     */
    public List<SetorDTO> obterDTO(){
        List<Setor> setores = this.obter();
        List<SetorDTO> setoresDTO = new ArrayList<>();
        
        //Verifica se o setor possui hospitais cadastrados. Caso possua, seta usado como true, senão false;
        for(Setor setor : setores){
            if(hospitalRepository.findBySetores(setor)
                    .isEmpty()){
                setoresDTO.add(new SetorDTO(setor.getId(),setor.getNome(),false));
            }
            else
                setoresDTO.add(new SetorDTO(setor.getId(),setor.getNome(),true));
        }
        
        return setoresDTO;
    }
    
    /** Método para buscar uma lista com todos os setores existentes.   
      * @return list de setores.
     */
    public List<Setor> obter(){
    	return this.setorRepository.findAll();
    }
    
    /** Método para verificar o cadastro prévio de um setor.
     * @param nome 
      * @return retorna o valor correspondente a existência ou a não existência de um registro prévio.
     */
    private boolean existe(String nome){
        return this.setorRepository.findByNome(nome)!= null;
    }
    
    /** Método para exlcuir um setor.   
      * @param  id - id do setor.
     */
    public void excluir(Long id) throws SetorEmUsoException{
        
        Setor setor = setorRepository.findOne(id);
        
        if(hospitalRepository.findBySetores(setor).isEmpty())
            this.setorRepository.delete(id);
        else
            throw new SetorEmUsoException();
    }
}
