/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.gerenciaNotificacao.cln.util.dataFactory;

import br.ifes.leds.reuse.utility.Factory;
import br.ifes.leds.sincap.controleInterno.cgd.HospitalRepository;
import br.ifes.leds.sincap.controleInterno.cgd.InstituicaoNotificadoraRepository;
import br.ifes.leds.sincap.controleInterno.cgd.SetorRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Hospital;
import br.ifes.leds.sincap.controleInterno.cln.cdp.InstituicaoNotificadora;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Setor;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.CausaMortisRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ObitoRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.PacienteRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CausaMortis;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CorpoEncaminhamento;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Obito;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Paciente;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoObito;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**Classe para a criação de objetos Obito randomicos.
 *
 * @author aleao
 * @version 1.0
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
    private HospitalRepository hospitalRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private Factory fabrica;
    
    private Obito obito;
    private Calendar dataObito;
    private Calendar dataEvento;
    private Date dataAtual;
    private Listas list = Listas.INSTANCE;
    
    private List<CausaMortis> listCausa;
    private List<Setor> listSetor;
    private List<Paciente> listPaciente;
    private List<CorpoEncaminhamento> listcorpoEncaminhamento;
    private List<TipoObito> listTipoObito;
    private List<Hospital> listHospital;
    
    /**Método responsável por criar Objetos Obito randomico, sendo nescessário apenas passar
     * uma instancia DataFactory e a quantidade a ser criada.
     * @param df - instancia DataFacotry.
     * @param qtdObt - quantidade de objetos a serem criados. 
     */
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
        obito = fabrica.criaObjeto(Obito.class);
        dataObito = Calendar.getInstance();
        dataEvento = Calendar.getInstance();
        dataAtual = new Date();
        listcorpoEncaminhamento = list.getListCorp();
        listTipoObito = list.getListTipoObito();
        
        obito.setAptoDoacao(df.chance(50));
        obito.setCorpoEncaminhamento(df.getItem(listcorpoEncaminhamento));
        dataObito.setTime(df.getDateBetween(df.getDate(2000, 01, 01), dataAtual));
        obito.setDataObito(dataObito);
        dataEvento.setTime(df.getDateBetween(dataObito.getTime(), dataAtual));
        obito.setDataCadastro(dataEvento);
        listCausa = causaMortisRepository.findAll();
        obito.setPrimeiraCausaMortis(df.getItem(listCausa));
        obito.setSegundaCausaMortis(df.getItem(listCausa));
        obito.setTerceiraCausaMortis(df.getItem(listCausa));
        obito.setQuartaCausaMortis(df.getItem(listCausa));
        listSetor = setorRepository.findAll();
        obito.setSetor(df.getItem(listSetor));
        obito.setTipoObito(df.getItem(listTipoObito));
        listPaciente = pacienteRepository.findAll();
        obito.setPaciente(df.getItem(listPaciente));
        listHospital = hospitalRepository.findAll();
        obito.setHospital(df.getItem(listHospital));

        return obito;
    }
}
