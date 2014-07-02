/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.gerenciaNotificacao.cln.util.dataFactory;

import br.ifes.leds.reuse.utility.Factory;
import br.ifes.leds.sincap.controleInterno.cgd.FuncionarioRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Funcionario;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.EntrevistaRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.ResponsavelRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.TestemunhaRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Entrevista;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Responsavel;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Testemunha;
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
public class EntrevistaData {
    
    @Autowired
    private ResponsavelRepository responsavelRepository;
    @Autowired
    private TestemunhaRepository testemunhaRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private EntrevistaRepository entrevistaRepository;
    @Autowired
    private Factory fabrica;
    
    private Entrevista entrevista;
    private Calendar dataEntrevista;
    private Calendar dataCadastro;
    private List<Responsavel> listResponsavel;
    private List<Testemunha> listTestemunha;
    private List<Funcionario> listFuncionario;
    
    
    public void criaEntrevistaRandom(DataFactory df,Integer qtdEnt){
        for (int i = 0; i < qtdEnt; i++){
            salvaEntrevista(criaEntrevista(df));
        }
    }

    public Entrevista criaEntrevista(DataFactory df) {
        entrevista = fabrica.criaObjeto(Entrevista.class);
        dataEntrevista = Calendar.getInstance();
        dataCadastro = Calendar.getInstance();
        
        dataCadastro.setTime(df.getDateBetween(df.getDate(2000, 01, 01), df.getDate(2014, 12, 30)));
        entrevista.setDataCadastro(dataCadastro);
        dataEntrevista.setTime(df.getDateBetween(dataCadastro.getTime(), df.getDate(2014, 12, 30)));
        entrevista.setDataEntrevista(dataEntrevista);
        listResponsavel = responsavelRepository.findAll();
        entrevista.setResponsavel(df.getItem(listResponsavel));
        listTestemunha = testemunhaRepository.findAll();
        entrevista.setTestemunha1(df.getItem(listTestemunha));
        entrevista.setTestemunha2(df.getItem(listTestemunha));
        entrevista.setDoacaoAutorizada(df.chance(50));
        listFuncionario = funcionarioRepository.findAll();
        entrevista.setFuncionario(df.getItem(listFuncionario));
        
        return entrevista;
    }
    
    public void salvaEntrevista(Entrevista ent){
        entrevistaRepository.save(ent);
    }
}
