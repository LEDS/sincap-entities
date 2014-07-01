/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifes.leds.sincap.gerenciaNotificacao.cln.util.dataFactory;

import br.ifes.leds.reuse.endereco.cdp.Endereco;
import br.ifes.leds.reuse.endereco.cgd.BairroRepository;
import br.ifes.leds.reuse.endereco.cgd.CidadeRepository;
import br.ifes.leds.reuse.endereco.cgd.EnderecoRepository;
import br.ifes.leds.reuse.endereco.cgd.EstadoRepository;
import br.ifes.leds.reuse.utility.Factory;
import br.ifes.leds.sincap.controleInterno.cgd.BancoOlhosRepository;
import br.ifes.leds.sincap.controleInterno.cgd.CaptadorRepository;
import br.ifes.leds.sincap.controleInterno.cgd.TelefoneRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.BancoOlhos;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Captador;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import java.util.ArrayList;
import java.util.List;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author aleao
 */
@Service
public class CaptadorData {

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
    CaptadorRepository captadorRepository;
    @Autowired
    BancoOlhosRepository bancoOlhosRepository;
    @Autowired
    private Factory fabrica;
    
    private Endereco endereco;
    private Captador captador;
    private Telefone telefone;
    private List<BancoOlhos> listBancoOlhos;
    

    public void criaCaptadorRandom(DataFactory df, Integer qtdCap) {
        for (int i = 0; i < qtdCap; i++) {

            captador = fabrica.criaObjeto(Captador.class);
            endereco = fabrica.criaObjeto(Endereco.class);
            telefone = fabrica.criaObjeto(Telefone.class);
            listBancoOlhos = new ArrayList<>();

            // Dados do Notificador
            captador.setSenha("123456");
            captador.setAtivo(true);
            captador.setCpf(df.getNumberText(11));
            captador.setDocumentoSocial(df.getNumberText(9));
            captador.setEmail(df.getEmailAddress());
            captador.setNome(df.getName());

            // Endereco
            endereco.setLogradouro(df.getStreetName());
            endereco.setEstado(estadoRepository.findOne(new Long(1)));
            endereco.setCidade(cidadeRepository.findOne(new Long(1)));
            endereco.setBairro(bairroRepository.findOne(new Long(1)));
            endereco.setNumero(df.getNumberText(5));
            endereco.setComplemento(df.getStreetSuffix());
            endereco.setCep(df.getNumberText(8));
            captador.setEndereco(endereco);
            enderecoRepository.save(endereco);

            // Telefone
            telefone.setNumero(df.getNumberText(8));
            captador.setTelefone(telefone);
            telefoneRepository.save(telefone);

            // Banco Olhos
            listBancoOlhos = bancoOlhosRepository.findAll();
            captador.setBancoOlhos(df.getItem(listBancoOlhos));

            captadorRepository.save(captador);
        }
    }

    public void associaBancoOlhos() {

    }
}
