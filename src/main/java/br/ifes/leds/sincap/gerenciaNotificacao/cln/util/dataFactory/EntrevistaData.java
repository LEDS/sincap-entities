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
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Entrevista;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**Classe para a criação de objetos Entrevista randomicos.
 *
 * @author aleao
 * @version 1.0
 */
@Service
public class EntrevistaData {

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private EntrevistaRepository entrevistaRepository;
    @Autowired
    private Factory fabrica;
    @Autowired
    private ResponsavelData responsavelData;
    @Autowired
    private TestemunhaData testemunhaData;

    /**Método responsável por criar Objetos Entrevista randomico, sendo nescessário apenas passar
     * uma instancia DataFactory e a quantidade a ser criada.
     * @param df - instancia DataFacotry.
     * @param qtdEnt - quantidade de objetos a serem criados. 
     */
    @SuppressWarnings("unused")
    public void criaEntrevistaRandom(DataFactory df,Integer qtdEnt){
        for (int i = 0; i < qtdEnt; i++){
            salvaEntrevista(criaEntrevista(df));
        }
    }

     /**Método responsável por criar Objetos Entrevista randomico.
     * @param df - instancia DataFactory.
     * @return entrevista - objeto Entrevista Randomico.
     */
    public Entrevista criaEntrevista(DataFactory df) {
        Entrevista entrevista = fabrica.criaObjeto(Entrevista.class);
        Calendar dataEntrevista = Calendar.getInstance();
        Calendar dataCadastro = Calendar.getInstance();
        Date dataAtual = new Date();
        
        dataCadastro.setTime(df.getDateBetween(df.getDate(2000, 1, 1), dataAtual));
        entrevista.setDataCadastro(dataCadastro);
        dataEntrevista.setTime(df.getDateBetween(dataCadastro.getTime(), dataAtual));
        entrevista.setDataEntrevista(dataEntrevista);
        entrevista.setResponsavel(responsavelData.criarResponsavel(df));
        entrevista.setTestemunha1(testemunhaData.criarTestemunha(df));
        entrevista.setTestemunha2(testemunhaData.criarTestemunha(df));
        entrevista.setDoacaoAutorizada(df.chance(50));
        entrevista.setEntrevistaRealizada(df.chance(50));
        List<Funcionario> listFuncionario = funcionarioRepository.findAll();
        entrevista.setFuncionario(df.getItem(listFuncionario));
        
        return entrevista;
    }
    
    /** Método responsável por salvar um objeto Entrevista no banco de dados.
     * @param ent - Objeto Entrevista. 
     */
    public void salvaEntrevista(Entrevista ent){
        entrevistaRepository.save(ent);
    }
}
