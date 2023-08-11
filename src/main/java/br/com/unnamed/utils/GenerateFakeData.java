package br.com.unnamed.utils;

import br.com.unnamed.domain.ContaBancaria;
import br.com.unnamed.domain.Equipamento;
import br.com.unnamed.domain.Servico;
import br.com.unnamed.domain.Usuario;
import br.com.unnamed.repository.ContaBancariaRepository;
import br.com.unnamed.repository.EquipamentoRepository;
import br.com.unnamed.repository.ServicoRepository;
import br.com.unnamed.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//@Service
public class GenerateFakeData {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    ServicoRepository servicoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ContaBancariaRepository contaBancariaRepository;

    @Autowired
    EquipamentoRepository equipamentoRepository;

    @PostConstruct
    public void setUp() {
        clearData();
        List<Usuario> userList = createFakeUser();
        createFakeServico(userList);
        createFakeEquipamento(userList);
        createFakeContaBancaria(userList);
    }

    private void createFakeContaBancaria(List<Usuario> userList) {
        List<ContaBancaria> cb = new ArrayList<>();
        userList.forEach(z -> {
            cb.add(new ContaBancaria(null, z, "237", "71", "14160", "2", 1l));
            cb.add(new ContaBancaria(null, z, "336", "201", "19530", "7", 1l));
            cb.add(new ContaBancaria(null, z, "341", "119", "78021", "1", 1l));
        });

        contaBancariaRepository.saveAll(cb);
    }

    private void createFakeEquipamento(List<Usuario> userList) {
        List<Equipamento> l = new ArrayList<>();
        userList.forEach(z -> {
            l.add(new Equipamento(null, z, "TV", "50 polegadas, com conectividade por HDMI", 1l));
            l.add(new Equipamento(null, z, "Ar condicionado", "Split, 8 mil BTUs", 1l));
            l.add(new Equipamento(null, z, "Telefone", "Especifico para confêrencias, com microfone multidirecional", 1l));
        });

        equipamentoRepository.saveAll(l);
    }

    private void clearData() {
        servicoRepository.deleteAll();
        equipamentoRepository.deleteAll();
        contaBancariaRepository.deleteAll();
        usuarioRepository.deleteAll();
    }

    private List<Usuario> createFakeUser() {
        List<Usuario> list = new ArrayList<>();
        list.add(new Usuario(null, null, null, "Fabio Kamisato", "35346199519", "fabiokamisato@gmail.com", "11975792503", encoder.encode("Blue#99"), false, null));
        list.add(new Usuario(null, null, null, "Thais Cavenago", "45283775860", "thaiscavenago@gmail.com", "14975792502", encoder.encode("Blue#00"), true, "on"));
        list.add(new Usuario(null, null, null, "teste", "15283771860", "a@a", "14975792502", encoder.encode("a"), true, "on"));

        return usuarioRepository.saveAll(list);
    }

    private void createFakeServico(List<Usuario> userList) {
        List<Servico> list = new ArrayList<>();
        userList.forEach(user -> {
            list.add(new Servico(null, user, "Brunch completo",
                    "O brunch completo serve até 10 pessoaos com frios e queijos, diferentes tipos de pães, patês e geleias, iogurte, bolos, pão de queijo e frutas.",
                    new BigDecimal("350.00"), 1l));
            list.add(new Servico(null, user, "Snacks", "Os snacks servem até 10 pessoas e são compostos das seguintes opções: biscoitos doces e salgados, pão de queijo e salada de frutas", new BigDecimal("100.00"), 1l));
            list.add(new Servico(null, user, "Água gelada", "Água gelada dísponivel para até 10 pessoas", new BigDecimal("50.00"), 1l));
            list.add(new Servico(null, user, "Material de escritório", "10 blocos para anotação e canetas", new BigDecimal("100.00"), 1l));
            list.add(new Servico(null, user, "Café", "Café dísponivel durante todo o tempo da reunião.", new BigDecimal("70.00"), 1l));
            list.add(new Servico(null, user, "Suporte Audiovisual", "Um especialista para ajudar com sua apresentação, ajustando a projeção, som, entre outros items.", new BigDecimal("70.00"), 1l));

            servicoRepository.saveAll(list);
        });
    }
}
