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
import br.ifes.leds.sincap.controleInterno.cgd.BancoOlhosRepository;
import br.ifes.leds.sincap.controleInterno.cgd.CaptadorRepository;
import br.ifes.leds.sincap.controleInterno.cgd.TelefoneRepository;
import br.ifes.leds.sincap.controleInterno.cln.cdp.BancoOlhos;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Captador;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Funcionario;
import br.ifes.leds.sincap.controleInterno.cln.cdp.Telefone;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.TipoDocumentoComFoto;
import br.ifes.leds.sincap.gerenciaNotificacao.cln.cdp.DocumentoComFoto;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static br.ifes.leds.reuse.utility.Factory.criaObjeto;

/**
 * Classe para a criação de objetos Captador randomicos.
 *
 * @author aleao
 * @version 1.0
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

    private BancoOlhos bancoOlhos;

    /**
     * Método responsável por criar Objetos Captador randomico, sendo nescessário apenas passar
     * uma instancia DataFactory e a quantidade a ser criada.
     *
     * @param df     - instancia DataFacotry.
     * @param qtdCap - quantidade de objetos a serem criados.
     */
    @SuppressWarnings("unused")
    public void criaCaptadorRandom(DataFactory df, Integer qtdCap) {
        for (int i = 0; i < qtdCap; i++) {
            Captador cap = criaCaptador(df);
            salvarCaptador(cap);
            atualizarBancoOlhos(associaBancoOlhos(df, cap));
        }
    }

    /**
     * Método responsável por criar Objetos Captador randomico.
     *
     * @param df - instancia DataFactory.
     * @return captador - objeto Captador Randomico.
     */
    public Captador criaCaptador(DataFactory df) {
        Captador captador = criaObjeto(Captador.class);
        Endereco endereco = criaObjeto(Endereco.class);
        Telefone telefone = criaObjeto(Telefone.class);
        bancoOlhos = criaObjeto(BancoOlhos.class);
        DocumentoComFoto documentoComFoto = criaObjeto(DocumentoComFoto.class);

        // Dados do Notificador
        documentoComFoto.setDocumento(df.getNumberText(9));
        documentoComFoto.setTipoDocumentoComFoto(TipoDocumentoComFoto.RG);

        captador.setSenha("123456");
        captador.setAtivo(true);
        captador.setCpf(df.getNumberText(3) + "." + df.getNumberText(3) + "." + df.getNumberText(3) + "-" + df.getNumberText(2));
        captador.setDocumentoSocial(documentoComFoto);
        captador.setEmail(df.getEmailAddress());
        captador.setNome(df.getName());

        // Endereco
        endereco.setLogradouro(df.getStreetName());
        endereco.setEstado(estadoRepository.findOne(1L));
        endereco.setCidade(cidadeRepository.findOne(1L));
        endereco.setBairro(bairroRepository.findOne(1L));
        endereco.setNumero(df.getNumberText(5));
        endereco.setComplemento(df.getStreetSuffix());
        endereco.setCep(df.getNumberText(8));
        captador.setEndereco(endereco);

        // Telefone
        telefone.setNumero("(" + df.getNumberText(2) + ")"
                + df.getNumberText(4) + "-" + df.getNumberText(4));
        captador.setTelefone(telefone);

        associaBancoOlhos(df, captador);

        salvarCaptador(captador);

        return captador;
    }

    /**
     * Método responsável por salvar um objeto Captador no banco de dados.
     *
     * @param c - Objeto Captador.
     */
    public void salvarCaptador(Captador c) {
        captadorRepository.save(c);
    }

    /**
     * Método responsável por associar um Captador a algum banco de Olhos randomico.
     *
     * @param df  - Objeto DataFactory
     * @param cap - Objeto Captador
     * @return bancoOlhos - Objeto Banco Olhos.
     */
    public BancoOlhos associaBancoOlhos(DataFactory df, Captador cap) {
        bancoOlhos = criaObjeto(BancoOlhos.class);
        List<BancoOlhos> listBancoOlhos;
        Set<Funcionario> setFuncionario;
        listBancoOlhos = bancoOlhosRepository.findAll();
        cap.setBancoOlhos(df.getItem(listBancoOlhos));
        bancoOlhos = cap.getBancoOlhos();
        setFuncionario = bancoOlhos.getFuncionarios();
        setFuncionario.add(cap);
        bancoOlhos.setFuncionarios(setFuncionario);

        return bancoOlhos;
    }

    /**
     * Método responsável por salvar o banco de olhos com o novo captador.
     *
     * @param bo - Objeto Banco Olhos.
     */
    public void atualizarBancoOlhos(BancoOlhos bo) {
        bancoOlhosRepository.save(bo);
    }
}
