/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.gerenciaNotificacao.cln.util.dataFactory;

import br.ifes.leds.reuse.utility.Factory;
import br.ifes.leds.sincap.controleInterno.cgd.SetorRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Setor;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.CausaMortisRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ObitoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.PacienteRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CausaMortis;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Obito;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Paciente;
import java.util.Calendar;
import java.util.List;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author aleao
 */
@Service
public class ObitoData {
    @Autowired
    private ObitoRepository obitoRepository;
    @Autowired
    private CausaMortisRepository causaMortisRepository;
    @Autowired
    private SetorRepository setorRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private Factory fabrica;
    
    private Obito obito;
    private Calendar dataObito;
    private Calendar dataEvento;
    private Listas list = Listas.INSTANCE;
    
    private List<CausaMortis> listCausa;
    private List<Setor> listSetor;
    private List<Paciente> listPaciente;
    
    public void criaObitoRandom(DataFactory df, Integer qtdObt){
        for (int i = 0; i < qtdObt; i++){
           salvaObito(criaObito(df));
        }
    }

    public void salvaObito(Obito ob){
        obitoRepository.save(ob);
    }
    public Obito criaObito(DataFactory df) {
        obito = fabrica.criaObjeto(Obito.class);
        dataObito = Calendar.getInstance();
        dataEvento = Calendar.getInstance();
        
        obito.setAptoDoacao(df.chance(50));
        obito.setCorpoEncaminhamento(df.getItem(list.getListCorp()));
        dataObito.setTime(df.getDateBetween(df.getDate(2000, 01, 01), df.getDate(2014, 12, 30)));
        obito.setDataObito(dataObito);
        dataEvento = dataObito;
        dataEvento.add(Calendar.HOUR,df.getNumberBetween(1, 24));
        obito.setDataEvento(dataEvento);
        listCausa = causaMortisRepository.findAll();
        obito.setPrimeiraCausaMortis(df.getItem(listCausa));
        obito.setSegundaCausaMortis(df.getItem(listCausa));
        obito.setTerceiraCausaMortis(df.getItem(listCausa));
        obito.setQuartaCausaMortis(df.getItem(listCausa));
        listSetor = setorRepository.findAll();
        obito.setSetor(df.getItem(listSetor));
        obito.setPaciente(null);
        obito.setTipoObito(df.getItem(list.getListTipoObito()));
        listPaciente = pacienteRepository.findAll();
        obito.setPaciente(df.getItem(listPaciente));
        
        return obito;
    }
}
