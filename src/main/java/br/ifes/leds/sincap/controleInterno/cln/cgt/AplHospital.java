package br.ifes.leds.sincap.controleInterno.cln.cgt;

import br.ifes.leds.reuse.endereco.cgd.EnderecoRepository;
import br.ifes.leds.reuse.ledsExceptions.CRUDExceptions.HospitalEmUsoException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifes.leds.sincap.controleInterno.cgd.HospitalRepository;
import br.ifes.leds.sincap.controleInterno.cgd.TelefoneRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Hospital;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Setor;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ProcessoNotificacaoRepository;
import org.springframework.data.domain.Pageable;

@Service
public class AplHospital {

    @Autowired
    private HospitalRepository hospitalRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private TelefoneRepository telefoneRepository;
    @Autowired
    private ProcessoNotificacaoRepository notificacaoRepository;

    public void cadastrar(Hospital hospital) {

        telefoneRepository.save(hospital.getTelefone());
        enderecoRepository.save(hospital.getEndereco());
        hospitalRepository.save(hospital);
    }

    public void update(Hospital hospital) {
        hospitalRepository.save(hospital);
    }

//    public void delete(Long id) throws HospitalEmUsoException {
//
//        if (notificacaoRepository.findByInstituicaoId(id).isEmpty()) {
//            hospitalRepository.delete(id);
//        } else {
//            throw new HospitalEmUsoException();
//        }
//    }

    /*public List<Hospital> obterTodos(Sort ordem){
     return repository.findAll(ordem);
     }
     */
    //--foi um teste -- mas ele busca os dados usando como chave o nome
    public List<Hospital> obter(String nome) {
        return hospitalRepository.findByNome(nome);
    }

    public Hospital obter(Long id) {
        return hospitalRepository.findOne(id);
    }

    public List<Hospital> obter(Pageable pageable) {
        return hospitalRepository.findAll(pageable).getContent();
    }

    public List<Hospital> obter() {
        return hospitalRepository.findAll();
    }

    public Long quantidade() {
        return hospitalRepository.count();
    }

    public void addSetor(Setor setor, Long idHospital) {
        Hospital hospital = this.hospitalRepository.findOne(idHospital);
        hospital.addSetor(setor);
        this.hospitalRepository.save(hospital);
    }

    public void removerSetor(Setor setor, Long idHospital) {
        Hospital hospital = this.hospitalRepository.findOne(idHospital);
        hospital.removeSetor(setor);
    }
}
