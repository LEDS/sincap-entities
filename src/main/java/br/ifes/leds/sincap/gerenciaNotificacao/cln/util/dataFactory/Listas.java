/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifes.leds.sincap.gerenciaNotificacao.cln.util.dataFactory;

import br.ifes.leds.sincap.controleInterno.cln.cdp.Sexo;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.CorpoEncaminhamento;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.EstadoCivil;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Parentesco;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoObito;

import java.util.ArrayList;
import java.util.List;

/** Classe responsável por criar as listas que serão utilizadas por outras classes.
 * Esta foi criada pois, a framework DataFactory não possui algumas informações randomicas.
 *
 * @author aleao
 * @version 1.0
 */
public enum Listas {

    INSTANCE;
    
    private final List<String> listaProfissoes;
    private final List<String> listaReligioes;
    private final List<String> listaGrauEscolaridade;
    private final List<String> listCausaMortis;
    private final List<Sexo> listaSexo;
    private final List<EstadoCivil> listaEstado;
    private final List<Parentesco> listaParentesco;
    private final List<CorpoEncaminhamento> listCorpoEncaminhamento;
    private final List<TipoObito> listTipoObito;
    private final List<String> listTipoNaoDoacao;

    /**Metodo construtor onde as lista são inicializadas.
     */
    private Listas() {
        listaProfissoes = new ArrayList<>();
        
        listaProfissoes.add("Advogado(a)");
        listaProfissoes.add("Agente de inseminação artificial");
        listaProfissoes.add("Agente Oficial da Propriedade Industrial");
        listaProfissoes.add("Ajudante de Cozinheiro(a)(Profissão Marítima)");
        listaProfissoes.add("Ajudante de Maquinista");
        listaProfissoes.add("Arquiteto(a)");
        listaProfissoes.add("Arrais de pesca local (m/f)");
        listaProfissoes.add("Arrais de pesca (m/f)");
        listaProfissoes.add("Biólogo(a)");
        listaProfissoes.add("Contramestre (m/f)");
        listaProfissoes.add("Contramestre pescador (m/f)");
        listaProfissoes.add("Cozinheiro (a) (Profissão Marítima)");
        listaProfissoes.add("Despachante Oficial (m/f)");
        listaProfissoes.add("Dietista (m/f)");
        listaProfissoes.add("Director(a) de escola de condução");
        listaProfissoes.add("Director(a) técnico da actividade transitária");
        listaProfissoes.add("Docente do Ensino Superior Politécnico");
        listaProfissoes.add("Docente do Ensino Superior Universitário");
        listaProfissoes.add("Educador(a) de Infância");
        listaProfissoes.add("Electricista(m/f)(Profissão Marítima)");
        listaProfissoes.add("Empregado(a) de câmaras (Profissão Marítima)");
        listaProfissoes.add("Enfermeiro(a) Especialista em Enfermagem de Saúde Materna e Obstétrica");
        listaProfissoes.add("Enfermeiro(a) Responsável por Cuidados Gerais");
        listaProfissoes.add("Engenheiro(a) Agrónomo(a)");
        listaProfissoes.add("Engenheiro(a) Civil");
        listaProfissoes.add("Engenheiro(a) de Materiais");
        listaProfissoes.add("Engenheiro(a) do Ambiente");
        listaProfissoes.add("Engenheiro(a) Electrotécnico(a)");
        listaProfissoes.add("Engenheiro(a) Florestal");
        listaProfissoes.add("Engenheiro(a) Geógrafo(a)");
        listaProfissoes.add("Engenheiro(a) Geológo(a) e de Minas");
        listaProfissoes.add("Engenheiro(a) Informático(a)");
        listaProfissoes.add("Engenheiro(a) Mecânico(a)");
        listaProfissoes.add("Engenheiro(a) Metalúrgico(a) e de Materiais");
        listaProfissoes.add("Engenheiro(a) Naval");
        listaProfissoes.add("Engenheiro(a) Químico(a) e Biológico(a)");
        listaProfissoes.add("Engenheiro(a) Técnico(a) de Aeronáutica");
        listaProfissoes.add("Engenheiro(a) Técnico(a) de Agrária");
        listaProfissoes.add("Engenheiro(a) Técnico(a) de Alimentar");
        listaProfissoes.add("Engenheiro(a) Técnico(a) de Ambiente");
        listaProfissoes.add("Engenheiro(a) Técnico(a) de Civil");
        listaProfissoes.add("Engenheiro(a) Técnico(a) de Electrónica e Telecomunicações");
        listaProfissoes.add("Engenheiro(a) Técnico(a) de Energia e Sistemas de Potência");
        listaProfissoes.add("Engenheiro(a) Técnico(a) de Geográfica/Topográfica");
        listaProfissoes.add("Engenheiro(a) Técnico(a) de Geotecnia e Minas");
        listaProfissoes.add("Engenheiro(a) Técnico(a) de Industrial e da Qualidade");
        listaProfissoes.add("Engenheiro(a) Técnico(a) de Informática");
        listaProfissoes.add("Engenheiro(a) Técnico(a) de Mecânica");
        listaProfissoes.add("Engenheiro(a) Técnico(a) de Proteção Civil");
        listaProfissoes.add("Engenheiro(a) Técnico(a) de Química");
        listaProfissoes.add("Engenheiro(a) Técnico(a) de Segurança");
        listaProfissoes.add("Engenheiro(a) Técnico(a) de Transportes");

        listaReligioes = new ArrayList<>();

        listaReligioes.add("Católico(a)");
        listaReligioes.add("Evangélico(a)");
        listaReligioes.add("Budista");

        listaGrauEscolaridade = new ArrayList<>();

        listaGrauEscolaridade.add("Ensino Fundamental incompleto");
        listaGrauEscolaridade.add("Ensino Fundamental completo");
        listaGrauEscolaridade.add("Ensino Médio incompleto");
        listaGrauEscolaridade.add("Ensino Médio completo");
        listaGrauEscolaridade.add("Ensino Superior incompleto");
        listaGrauEscolaridade.add("Ensino Superior completo");

        
        listaSexo = new ArrayList<>();
        
        listaSexo.add(Sexo.FEMININO);
        listaSexo.add(Sexo.MASCULINO);
        
        listaEstado = new ArrayList<>();
        
        listaEstado.add(EstadoCivil.UNIAO_ESTAVEL);
        listaEstado.add(EstadoCivil.DIVORCIADO);
        listaEstado.add(EstadoCivil.SOLTEIRO);
        listaEstado.add(EstadoCivil.VIUVO);
        
        listaParentesco = new ArrayList<>();
        
        listaParentesco.add(Parentesco.AVOS);
        listaParentesco.add(Parentesco.CONJUGE);
        listaParentesco.add(Parentesco.IRMAOS);
        listaParentesco.add(Parentesco.PAIS);
        listaParentesco.add(Parentesco.TIOS);
        listaParentesco.add(Parentesco.AUTORIZACAO_JUDICIAL);
        
        listCorpoEncaminhamento = new ArrayList<>();
        
        listCorpoEncaminhamento.add(CorpoEncaminhamento.IML);
        listCorpoEncaminhamento.add(CorpoEncaminhamento.NAO_ENCAMINHADO);
        listCorpoEncaminhamento.add(CorpoEncaminhamento.SVO);
        
        
        listCausaMortis = new ArrayList<>();
        
        listCausaMortis.add("Nascimento Prematuro");
        listCausaMortis.add("Acidente de Trânsito");
        listCausaMortis.add("Diabetes");
        listCausaMortis.add("Câncer nos Pulmões");
        listCausaMortis.add("Câncer na Traquéia ");
        listCausaMortis.add("Câncer nos Brônquios");
        listCausaMortis.add("HIV/AIDS");
        listCausaMortis.add("Diarréia");
        listCausaMortis.add("Doença Pulmonar Obstrutiva Crônica");
        listCausaMortis.add("Pneumonia");
        listCausaMortis.add("Infarto");
        listCausaMortis.add("Cardiopatia Isquêmica");
        
        listTipoObito = new ArrayList<>();
        
        listTipoObito.add(TipoObito.ME);
        listTipoObito.add(TipoObito.PCR);
        
        listTipoNaoDoacao = new ArrayList<>();
        
        listTipoNaoDoacao.add("Recusa Familiar");
        listTipoNaoDoacao.add("Problemas Logísticos");
        listTipoNaoDoacao.add("Problemas Estruturais");
        listTipoNaoDoacao.add("Contra-indicação Médica");
    }
    
    /**Método responsável por retornar uma lista de não doações.
     * @return listTipoNaoDoacao - lista de não doações.
     */
    public List<String> getListTipoNaoDoacao(){
        return listTipoNaoDoacao;
    }
    
    /**Método responsável por retornar uma lista de tipo Obito.
     * @return listTipoObito - lista de tipo obito.
     */
    public List<TipoObito> getListTipoObito(){
        return listTipoObito;
    }
    
    /**Método responsável por retornar uma lista de causa mortis.
     * @return listCausaMortis - lista de causa mortis.
     */
    public List<String> getListCausaMortis(){
        return listCausaMortis;
    }
    
    /**Método responsável por retornar uma lista de CorpoEncaminhamento.
     * @return listCorpoEncaminhamento - lista de CorpoEncaminhamento.
     */
    public List<CorpoEncaminhamento> getListCorp(){
        return listCorpoEncaminhamento;
    }
    
    /**Método responsável por retornar uma lista de Profissões.
     * @return listaProfissoes - lista de Profissões.
     */
    public List<String> getListProf() {
        return listaProfissoes;
    }

    /**Método responsável por retornar uma lista de Religiões.
     * @return listaReligioes - lista de Religiões.
     */
    public List<String> getListaReligioes() {
        return listaReligioes;
    }

    /**Método responsável por retornar uma lista de Graus de Ecolaridades.
     * @return listaGrauEscolaridade - lista de Graus de Escolaridade.
     */
    public List<String> getListaGrauEscolaridade() {
        return listaGrauEscolaridade;
    }

    /**Método responsável por retornar uma lista de Sexo.
     * @return listaSexo - lista de Sexo.
     */
    public List<Sexo> getListSex(){
        return listaSexo;
    }
    
    /**Método responsável por retornar uma lista de EstadoCivil.
     * @return listaEstado - lista de EstadoCivil.
     */
    public List<EstadoCivil> getListEst(){
        return listaEstado;
    }
    
    /**Método responsável por retornar uma lista de grau de parentesco.
     * @return listaParentesco - lista de grau de parentesco.
     */
    public List<Parentesco> getListPar(){
        return listaParentesco;
    }

}
