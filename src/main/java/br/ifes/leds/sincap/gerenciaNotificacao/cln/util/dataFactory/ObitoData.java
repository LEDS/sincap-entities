/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.gerenciaNotificacao.cln.util.dataFactory;

import br.ifes.leds.sincap.controleInterno.cgd.HospitalRepository;
import br.ifes.leds.sincap.controleInterno.cgd.SetorRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Hospital;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Setor;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.CausaMortisRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ObitoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.PacienteRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.*;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static br.ifes.leds.reuse.utility.Factory.criaObjeto;

/**Classe para a criação de objetos Obito randomicos.
 *
 * @author aleao
 * @version 1.0
 */
@Service
public class ObitoData {
    @Autowired
    ObitoRepository obitoRepository;
    @Autowired
    CausaMortisRepository causaMortisRepository;
    @Autowired
    SetorRepository setorRepository;
    @Autowired
    HospitalRepository hospitalRepository;
    @Autowired
    PacienteRepository pacienteRepository;
    @Autowired
    CausaMortisData causaMortisData;
    @Autowired
    PacienteData pacienteData;
    private final Listas list = Listas.INSTANCE;

    /**Método responsável por criar Objetos Obito randomico, sendo nescessário apenas passar
     * uma instancia DataFactory e a quantidade a ser criada.
     * @param df - instancia DataFacotry.
     * @param qtdObt - quantidade de objetos a serem criados. 
     */
    @SuppressWarnings("unused")
    public void criaObitoRandom(DataFactory df, Integer qtdObt){
        for (int i = 0; i < qtdObt; i++){
           salvaObito(criaObito(df));
        }
    }

    /** Método responsável por salvar um objeto Obito no banco de dados.
     * @param ob - Objeto Obito. 
     */
    public void salvaObito(Obito ob){
        obitoRepository.save(ob);
    }
    
    /**Método responsável por criar Objetos Obito randomico.
     * @param df - instancia DataFactory.
     * @return obito - objeto Obito Randomico.
     */
    public Obito criaObito(DataFactory df) {
        Obito obito = criaObjeto(Obito.class);
        Calendar dataObito = Calendar.getInstance();
        Calendar dataEvento = Calendar.getInstance();
        Date dataAtual = criaObjeto(Date.class);
        List<CorpoEncaminhamento> listcorpoEncaminhamento = list.getListCorp();
        List<TipoObito> listTipoObito = list.getListTipoObito();

        
        obito.setAptoDoacao(df.chance(50));
        obito.setCorpoEncaminhamento(df.getItem(listcorpoEncaminhamento));
        obito.setPaciente(pacienteData.criarPaciente(df));
        dataObito.setTime(df.getDateBetween(obito.getPaciente().getDataInternacao().getTime(), dataAtual));
        obito.setDataObito(dataObito);
        dataEvento.setTime(df.getDateBetween(dataObito.getTime(), dataAtual));
        obito.setDataCadastro(dataEvento);
        obito.setPrimeiraCausaMortis(causaMortisData.criaCausaMortis(df));
        obito.setSegundaCausaMortis(causaMortisData.criaCausaMortis(df));
        obito.setTerceiraCausaMortis(causaMortisData.criaCausaMortis(df));
        obito.setQuartaCausaMortis(causaMortisData.criaCausaMortis(df));
        List<Setor> listSetor = setorRepository.findAll();
        obito.setSetor(df.getItem(listSetor));
        obito.setTipoObito(df.getItem(listTipoObito));
        List<Hospital> listHospital = hospitalRepository.findAll();
        obito.setHospital(df.getItem(listHospital));

        return obito;
    }
}
