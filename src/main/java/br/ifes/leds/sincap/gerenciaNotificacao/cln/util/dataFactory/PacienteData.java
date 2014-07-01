/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.gerenciaNotificacao.cln.util.dataFactory;

import java.util.Calendar;
import java.util.List;

import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifes.leds.reuse.endereco.cdp.Endereco;
import br.ifes.leds.reuse.endereco.cgd.BairroRepository;
import br.ifes.leds.reuse.endereco.cgd.CidadeRepository;
import br.ifes.leds.reuse.endereco.cgd.EnderecoRepository;
import br.ifes.leds.reuse.endereco.cgd.EstadoRepository;
import br.ifes.leds.reuse.utility.Factory;
import br.ifes.leds.sincap.controleInterno.cgd.TelefoneRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Sexo;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import br.ifes.leds.sincap.gerenciaNotificacao.cgd.PacienteRepository;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.EstadoCivil;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.Paciente;

/**
 *
 * @author aleao
 */
@Service
public class PacienteData {

    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    TelefoneRepository telefoneRepository;
    @Autowired
    EnderecoRepository enderecoRepository;
    @Autowired
    BairroRepository bairroRepository;
    @Autowired
    CidadeRepository cidadeRepository;
    @Autowired
    EstadoRepository estadoRepository;
    @Autowired
    private Factory fabrica;

    private Paciente paciente;
    private Calendar dataNascimento;
    private Calendar dataInternacao;
    private List<String> listaProfissao;
    private List<EstadoCivil> listaEstadoCivil;
    private List<Sexo> listaSexo;
    private Endereco endereco;
    private Telefone telefone;
    private Listas list = Listas.INSTANCE;

    public void criaPacienteRandom(DataFactory df, Integer qtdPac) {
        for (int i = 0; i < qtdPac; i++) {

            criarPaciente(df);
            enderecoRepository.save(endereco);

            telefoneRepository.save(telefone);

            pacienteRepository.save(paciente);
        }
    }

    public Paciente criarPaciente(DataFactory df) {
        paciente = fabrica.criaObjeto(Paciente.class);
        dataNascimento = Calendar.getInstance();
        dataInternacao = Calendar.getInstance();
        listaProfissao = list.getListProf();
        listaSexo = list.getListSex();
        listaEstadoCivil = list.getListEst();
        endereco = fabrica.criaObjeto(Endereco.class);
        telefone = fabrica.criaObjeto(Telefone.class);

        gerarDadosPaciente(df);

        gerarDadosEndereco(df);

        // Telefone
        telefone.setNumero("(" + df.getNumberText(2) + ")"
                + df.getNumberText(4) + "-" + df.getNumberText(4));
        paciente.setTelefone(telefone);

        return paciente;
    }

    private void gerarDadosEndereco(DataFactory df) {
        // Endereco
        endereco.setLogradouro(df.getStreetName());
        endereco.setEstado(estadoRepository.findOne(new Long(1)));
        endereco.setCidade(cidadeRepository.findOne(new Long(1)));
        endereco.setBairro(bairroRepository.findOne(new Long(1)));
        endereco.setNumero(df.getNumberText(5));
        endereco.setComplemento(df.getStreetSuffix());
        endereco.setCep(df.getNumberText(8));
        paciente.setEndereco(endereco);
    }

    private void gerarDadosPaciente(DataFactory df) {
        // Dados do Paciente.
        paciente.setNome(df.getName());
        paciente.setNomeMae(df.getName());
        paciente.setNacionalidade("Brasileiro");
        paciente.setEstadoCivil(df.getItem(listaEstadoCivil));
        paciente.setSexo(df.getItem(listaSexo));
        dataNascimento.setTime(df.getBirthDate());
        paciente.setDataNascimento(dataNascimento);
        dataInternacao.setTime(df.getDateBetween(df.getDate(2000, 01, 01),
                df.getDate(2014, 01, 01)));
        paciente.setDataInternacao(dataInternacao);
        paciente.setDocumentoSocial(df.getNumberText(9));
        paciente.setNumeroProntuario(df.getNumberText(7));
        paciente.setNumeroSUS(df.getNumberText(7));
        paciente.setProfissao(df.getItem(listaProfissao));
    }
}
