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

/**
 *
 * @author aleao
 */
public enum Listas {

    INSTANCE;
    
    private final List<String> listaProfissoes;
    private final List<String> listCausaMortis;
    private final List<Sexo> listaSexo;
    private final List<EstadoCivil> listaEstado;
    private final List<Parentesco> listaParentesco;
    private final List<CorpoEncaminhamento> listCorpoEncaminhamento;
    private final List<TipoObito> listTipoObito;

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
        
        listaSexo = new ArrayList<>();
        
        listaSexo.add(Sexo.FEMININO);
        listaSexo.add(Sexo.MASCULINO);
        
        listaEstado = new ArrayList<>();
        
        listaEstado.add(EstadoCivil.CASADO);
        listaEstado.add(EstadoCivil.DIVORCIADO);
        listaEstado.add(EstadoCivil.SOLTEIRO);
        listaEstado.add(EstadoCivil.VIUVO);
        
        listaParentesco = new ArrayList<>();
        
        listaParentesco.add(Parentesco.AVOS);
        listaParentesco.add(Parentesco.CONJUGE);
        listaParentesco.add(Parentesco.IRMAOS);
        listaParentesco.add(Parentesco.PAIS);
        listaParentesco.add(Parentesco.TIOS);
        
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
    }
    
    
    public List<TipoObito> getListTipoObito(){
        return listTipoObito;
    }
    
    public List<String> getListCausaMortis(){
        return listCausaMortis;
    }
    public List<CorpoEncaminhamento> getListCorp(){
        return listCorpoEncaminhamento;
    }
    
    public List<String> getListProf() {
        return listaProfissoes;
    }
    
    public List<Sexo> getListSex(){
        return listaSexo;
    }
    
    public List<EstadoCivil> getListEst(){
        return listaEstado;
    }
    
    public List<Parentesco> getListPar(){
        return listaParentesco;
    }

}
