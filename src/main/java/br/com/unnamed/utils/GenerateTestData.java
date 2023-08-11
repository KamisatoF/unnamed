package br.com.unnamed.utils;

import br.com.unnamed.domain.*;
import br.com.unnamed.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@Service
public class GenerateTestData {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public static final String DEFAULT_PASSWORD = "Blue#99";

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Autowired
    ServicoRepository servicoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ContaBancariaRepository contaBancariaRepository;

    @Autowired
    EquipamentoRepository equipamentoRepository;

    @Autowired
    LocacaoRepository locacaoRepository;

    @PostConstruct
    public void setUp() {
        clearData();
        insertUserData1();
        insertUserData2();
        insertUserData3();
    }

    private void insertUserData1() {
        Usuario usuario = usuarioRepository.save(new Usuario(null, null, null, "Fabio Kamisato", "32717794140", "fabiokamisato@gmail.com", "11975792503", encoder.encode(DEFAULT_PASSWORD), false, null));

        List<ContaBancaria> cb = new ArrayList<>();
        cb.add(new ContaBancaria(null, usuario, "237", "71", "14160", "2", 1l));
        cb.add(new ContaBancaria(null, usuario, "102", "201", "19530", "4", 1l));
        cb.add(new ContaBancaria(null, usuario, "077", "001", "78021", "3", 1l));
        contaBancariaRepository.saveAll(cb);

        List<Equipamento> l = new ArrayList<>();
        l.add(new Equipamento(null, usuario, "Ar condicionado", "Split, 8 mil BTUs", 1l));
        l.add(new Equipamento(null, usuario, "TV", "50 polegadas, com conectividade por HDMI", 1l));
        l.add(new Equipamento(null, usuario, "Telefone Audioconfêrencia", "Telefone específico  para audioconfêrencia, SoundStation 2 Polycom", 1l));
        l.add(new Equipamento(null, usuario, " Câmera De Videoconferência", "Logitech BCC950 1080p Webcam", 1l));
        equipamentoRepository.saveAll(l);

        List<Servico> list = new ArrayList<>();
        list.add(new Servico(null, usuario, "Brunch completo",
                "O brunch completo serve até 10 pessoaos com frios e queijos, diferentes tipos de pães, patês e geleias, iogurte, bolos, pão de queijo, 3 sabores de sucos e refrigerante",
                new BigDecimal("400.00"), 1l));
        list.add(new Servico(null, usuario, "Snacks", "Os snacks servem até 10 pessoas e são compostos das seguintes opções: biscoitos doces e salgados, barras de sereal e chocolates", new BigDecimal("100.00"), 1l));
        list.add(new Servico(null, usuario, "Água gelada", "Água gelada dísponivel para até 10 pessoas", new BigDecimal("50.00"), 1l));
        list.add(new Servico(null, usuario, "Material de escritório", "Blocos para anotação, canetas e folhas para rascunho", new BigDecimal("100.00"), 1l));
        list.add(new Servico(null, usuario, "Café", "Café dísponivel durante todo o tempo da reunião.", new BigDecimal("70.00"), 1l));
        servicoRepository.saveAll(list);

        List<Locacao> locacaoList = new ArrayList<>();
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-01 13:00"), formatDate("2023-02-01 14:00"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-01 15:00"), formatDate("2023-02-01 17:00"), new BigDecimal("20"), new BigDecimal("0"), new BigDecimal("200"), new BigDecimal("220"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-01 09:00"), formatDate("2023-02-01 12:00"), new BigDecimal("25"), new BigDecimal("400"), new BigDecimal("250"), new BigDecimal("675"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-02 10:00"), formatDate("2023-02-02 11:00"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-03 14:00"), formatDate("2023-02-03 16:00"), new BigDecimal("20"), new BigDecimal("0"), new BigDecimal("200"), new BigDecimal("220"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-03 10:00"), formatDate("2023-02-03 13:00"), new BigDecimal("25"), new BigDecimal("50"), new BigDecimal("250"), new BigDecimal("325"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-03 17:00"), formatDate("2023-02-03 18:00"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-06 15:00"), formatDate("2023-02-06 17:00"), new BigDecimal("20"), new BigDecimal("70"), new BigDecimal("200"), new BigDecimal("290"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-08 09:00"), formatDate("2023-02-08 12:00"), new BigDecimal("25"), new BigDecimal("0"), new BigDecimal("250"), new BigDecimal("275"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-08 15:00"), formatDate("2023-02-08 16:00"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-09 09:00"), formatDate("2023-02-09 11:00"), new BigDecimal("20"), new BigDecimal("100"), new BigDecimal("200"), new BigDecimal("320"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-09 11:30"), formatDate("2023-02-09 12:30"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-09 13:00"), formatDate("2023-02-09 14:00"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-09 15:00"), formatDate("2023-02-09 16:00"), new BigDecimal("10"), new BigDecimal("20"), new BigDecimal("100"), new BigDecimal("130"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-09 16:30"), formatDate("2023-02-09 17:30"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-13 09:00"), formatDate("2023-02-13 12:00"), new BigDecimal("25"), new BigDecimal("100"), new BigDecimal("250"), new BigDecimal("375"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-13 13:00"), formatDate("2023-02-13 15:00"), new BigDecimal("20"), new BigDecimal("0"), new BigDecimal("200"), new BigDecimal("220"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-13 15:30"), formatDate("2023-02-13 16:30"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-13 17:00"), formatDate("2023-02-13 19:00"), new BigDecimal("20"), new BigDecimal("0"), new BigDecimal("200"), new BigDecimal("220"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-14 13:00"), formatDate("2023-02-14 16:00"), new BigDecimal("25"), new BigDecimal("400"), new BigDecimal("250"), new BigDecimal("675"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-14 16:30"), formatDate("2023-02-14 17:30"), new BigDecimal("10"), new BigDecimal("50"), new BigDecimal("100"), new BigDecimal("160"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-14 08:00"), formatDate("2023-02-14 11:00"), new BigDecimal("20"), new BigDecimal("0"), new BigDecimal("200"), new BigDecimal("220"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-15 09:00"), formatDate("2023-02-15 12:00"), new BigDecimal("25"), new BigDecimal("100"), new BigDecimal("250"), new BigDecimal("375"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-15 13:00"), formatDate("2023-02-15 16:00"), new BigDecimal("25"), new BigDecimal("0"), new BigDecimal("250"), new BigDecimal("275"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-16 08:00"), formatDate("2023-02-16 09:00"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-16 09:30"), formatDate("2023-02-16 11:30"), new BigDecimal("20"), new BigDecimal("0"), new BigDecimal("200"), new BigDecimal("220"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-16 13:00"), formatDate("2023-02-16 16:00"), new BigDecimal("25"), new BigDecimal("70"), new BigDecimal("250"), new BigDecimal("345"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-16 17:00"), formatDate("2023-02-16 18:00"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-17 08:00"), formatDate("2023-02-17 10:00"), new BigDecimal("20"), new BigDecimal("0"), new BigDecimal("200"), new BigDecimal("220"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-17 11:00"), formatDate("2023-02-17 12:00"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-17 13:00"), formatDate("2023-02-17 14:00"), new BigDecimal("10"), new BigDecimal("50"), new BigDecimal("100"), new BigDecimal("160"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-17 15:00"), formatDate("2023-02-17 17:00"), new BigDecimal("20"), new BigDecimal("0"), new BigDecimal("200"), new BigDecimal("220"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-23 13:00"), formatDate("2023-02-23 16:00"), new BigDecimal("25"), new BigDecimal("0"), new BigDecimal("250"), new BigDecimal("275"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-23 17:00"), formatDate("2023-02-23 18:00"), new BigDecimal("10"), new BigDecimal("30"), new BigDecimal("100"), new BigDecimal("140"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-23 09:30"), formatDate("2023-02-23 10:30"), new BigDecimal("20"), new BigDecimal("0"), new BigDecimal("200"), new BigDecimal("220"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-24 08:00"), formatDate("2023-02-24 10:00"), new BigDecimal("20"), new BigDecimal("20"), new BigDecimal("200"), new BigDecimal("240"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-24 11:00"), formatDate("2023-02-24 12:00"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-24 13:00"), formatDate("2023-02-24 14:00"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-24 15:00"), formatDate("2023-02-24 17:00"), new BigDecimal("20"), new BigDecimal("200"), new BigDecimal("200"), new BigDecimal("420"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-27 17:00"), formatDate("2023-02-27 20:00"), new BigDecimal("25"), new BigDecimal("400"), new BigDecimal("250"), new BigDecimal("375"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-27 08:00"), formatDate("2023-02-27 10:00"), new BigDecimal("20"), new BigDecimal("0"), new BigDecimal("200"), new BigDecimal("220"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-27 11:00"), formatDate("2023-02-27 12:00"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-28 10:00"), formatDate("2023-02-28 11:00"), new BigDecimal("10"), new BigDecimal("20"), new BigDecimal("100"), new BigDecimal("130"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-28 11:30"), formatDate("2023-02-28 12:30"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-28 13:00"), formatDate("2023-02-28 14:00"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Reunião Faria Lima", formatDate("2023-02-28 15:00"), formatDate("2023-02-28 17:00"), new BigDecimal("20"), new BigDecimal("120"), new BigDecimal("200"), new BigDecimal("360"), 1l));
        locacaoRepository.saveAll(locacaoList);
    }

    private Date formatDate(String dateAsString) {
        try {
            return sdf.parse(dateAsString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private void insertUserData2() {
        Usuario usuario = usuarioRepository.save(new Usuario(null, null, null, "Thais Cavenago", "46633516118", "thaiscavenago@gmail.com", "14993732512", encoder.encode(DEFAULT_PASSWORD), true, "on"));

        List<ContaBancaria> cb = new ArrayList<>();
        cb.add(new ContaBancaria(null, usuario, "341", "1354", "5160", "9", 1l));
        cb.add(new ContaBancaria(null, usuario, "033", "1830", "138330", "7", 1l));
        cb.add(new ContaBancaria(null, usuario, "077", "001", "503001", "8", 1l));
        contaBancariaRepository.saveAll(cb);

        List<Equipamento> l = new ArrayList<>();
        l.add(new Equipamento(null, usuario, "Projetor", "Projetor para 170 polegadas, com conectividade por HDMI, USB-C ou VGA", 1l));
        l.add(new Equipamento(null, usuario, "Sistema de som distribuído", "Sistema de som distríbuido pelo ambiente para que o som chegue em todo o auditório", 1l));
        l.add(new Equipamento(null, usuario, "Microfone", "4 microfones disponíveis", 1l));
        equipamentoRepository.saveAll(l);

        List<Servico> list = new ArrayList<>();
        list.add(new Servico(null, usuario, "Coffe break",
                "O brunch completo serve até 50 pessoaos com frios e queijos, diferentes tipos de pães, patês e geleias, iogurte, bolos, pão de queijo e frutas.",
                new BigDecimal("1000.00"), 1l));
        list.add(new Servico(null, usuario, "Snacks", "Os snacks servem até 50 pessoas e são compostos das seguintes opções: biscoitos doces e salgados, pão de queijo e salada de frutas", new BigDecimal("500.00"), 1l));
        list.add(new Servico(null, usuario, "Café", "Café dísponivel durante todo o tempo da reunião.", new BigDecimal("70.00"), 1l));
        list.add(new Servico(null, usuario, "Suporte Audiovisual", "Um especialista para ajudar com sua apresentação, ajustando a projeção, som, entre outros items.", new BigDecimal("70.00"), 1l));
        servicoRepository.saveAll(list);

        List<Locacao> locacaoList = new ArrayList<>();
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-01 13:00"), formatDate("2023-02-01 14:00"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-01 15:00"), formatDate("2023-02-01 17:00"), new BigDecimal("20"), new BigDecimal("0"), new BigDecimal("200"), new BigDecimal("220"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-01 09:00"), formatDate("2023-02-01 12:00"), new BigDecimal("25"), new BigDecimal("400"), new BigDecimal("250"), new BigDecimal("675"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-02 10:00"), formatDate("2023-02-02 11:00"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-03 14:00"), formatDate("2023-02-03 16:00"), new BigDecimal("20"), new BigDecimal("0"), new BigDecimal("200"), new BigDecimal("220"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-03 10:00"), formatDate("2023-02-03 13:00"), new BigDecimal("25"), new BigDecimal("50"), new BigDecimal("250"), new BigDecimal("325"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-03 17:00"), formatDate("2023-02-03 18:00"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-06 15:00"), formatDate("2023-02-06 17:00"), new BigDecimal("20"), new BigDecimal("70"), new BigDecimal("200"), new BigDecimal("290"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-08 09:00"), formatDate("2023-02-08 12:00"), new BigDecimal("25"), new BigDecimal("0"), new BigDecimal("250"), new BigDecimal("275"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-08 15:00"), formatDate("2023-02-08 16:00"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-09 09:00"), formatDate("2023-02-09 11:00"), new BigDecimal("20"), new BigDecimal("100"), new BigDecimal("200"), new BigDecimal("320"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-09 11:30"), formatDate("2023-02-09 12:30"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-09 13:00"), formatDate("2023-02-09 14:00"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-09 15:00"), formatDate("2023-02-09 16:00"), new BigDecimal("10"), new BigDecimal("20"), new BigDecimal("100"), new BigDecimal("130"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-09 16:30"), formatDate("2023-02-09 17:30"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-13 09:00"), formatDate("2023-02-13 12:00"), new BigDecimal("25"), new BigDecimal("100"), new BigDecimal("250"), new BigDecimal("375"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-13 13:00"), formatDate("2023-02-13 15:00"), new BigDecimal("20"), new BigDecimal("0"), new BigDecimal("200"), new BigDecimal("220"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-13 15:30"), formatDate("2023-02-13 16:30"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-13 17:00"), formatDate("2023-02-13 19:00"), new BigDecimal("20"), new BigDecimal("0"), new BigDecimal("200"), new BigDecimal("220"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-14 13:00"), formatDate("2023-02-14 16:00"), new BigDecimal("25"), new BigDecimal("400"), new BigDecimal("250"), new BigDecimal("675"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-14 16:30"), formatDate("2023-02-14 17:30"), new BigDecimal("10"), new BigDecimal("50"), new BigDecimal("100"), new BigDecimal("160"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-14 08:00"), formatDate("2023-02-14 11:00"), new BigDecimal("20"), new BigDecimal("0"), new BigDecimal("200"), new BigDecimal("220"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-15 09:00"), formatDate("2023-02-15 12:00"), new BigDecimal("25"), new BigDecimal("100"), new BigDecimal("250"), new BigDecimal("375"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-15 13:00"), formatDate("2023-02-15 16:00"), new BigDecimal("25"), new BigDecimal("0"), new BigDecimal("250"), new BigDecimal("275"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-16 08:00"), formatDate("2023-02-16 09:00"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-16 09:30"), formatDate("2023-02-16 11:30"), new BigDecimal("20"), new BigDecimal("0"), new BigDecimal("200"), new BigDecimal("220"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-16 13:00"), formatDate("2023-02-16 16:00"), new BigDecimal("25"), new BigDecimal("70"), new BigDecimal("250"), new BigDecimal("345"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-16 17:00"), formatDate("2023-02-16 18:00"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-17 08:00"), formatDate("2023-02-17 10:00"), new BigDecimal("20"), new BigDecimal("0"), new BigDecimal("200"), new BigDecimal("220"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-17 11:00"), formatDate("2023-02-17 12:00"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-17 13:00"), formatDate("2023-02-17 14:00"), new BigDecimal("10"), new BigDecimal("50"), new BigDecimal("100"), new BigDecimal("160"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-17 15:00"), formatDate("2023-02-17 17:00"), new BigDecimal("20"), new BigDecimal("0"), new BigDecimal("200"), new BigDecimal("220"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-23 13:00"), formatDate("2023-02-23 16:00"), new BigDecimal("25"), new BigDecimal("0"), new BigDecimal("250"), new BigDecimal("275"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-23 17:00"), formatDate("2023-02-23 18:00"), new BigDecimal("10"), new BigDecimal("30"), new BigDecimal("100"), new BigDecimal("140"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-23 09:30"), formatDate("2023-02-23 10:30"), new BigDecimal("20"), new BigDecimal("0"), new BigDecimal("200"), new BigDecimal("220"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-24 08:00"), formatDate("2023-02-24 10:00"), new BigDecimal("20"), new BigDecimal("20"), new BigDecimal("200"), new BigDecimal("240"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-24 11:00"), formatDate("2023-02-24 12:00"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-24 13:00"), formatDate("2023-02-24 14:00"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-24 15:00"), formatDate("2023-02-24 17:00"), new BigDecimal("20"), new BigDecimal("200"), new BigDecimal("200"), new BigDecimal("420"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-27 17:00"), formatDate("2023-02-27 20:00"), new BigDecimal("25"), new BigDecimal("400"), new BigDecimal("250"), new BigDecimal("375"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-27 08:00"), formatDate("2023-02-27 10:00"), new BigDecimal("20"), new BigDecimal("0"), new BigDecimal("200"), new BigDecimal("220"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-27 11:00"), formatDate("2023-02-27 12:00"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-28 10:00"), formatDate("2023-02-28 11:00"), new BigDecimal("10"), new BigDecimal("20"), new BigDecimal("100"), new BigDecimal("130"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-28 11:30"), formatDate("2023-02-28 12:30"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-28 13:00"), formatDate("2023-02-28 14:00"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Auditório Av Paulista", formatDate("2023-02-28 15:00"), formatDate("2023-02-28 17:00"), new BigDecimal("20"), new BigDecimal("120"), new BigDecimal("200"), new BigDecimal("360"), 1l));
        locacaoRepository.saveAll(locacaoList);
    }

    private void insertUserData3() {
        Usuario usuario = usuarioRepository.save(new Usuario(null, null, null, "Miguel Duarte", "19145792240", "miguelduarte@gmail.com", "31987671208", encoder.encode(DEFAULT_PASSWORD), false, null));

        List<ContaBancaria> cb = new ArrayList<>();
        cb.add(new ContaBancaria(null, usuario, "001", "1710", "4123512", "2", 1l));
        cb.add(new ContaBancaria(null, usuario, "104", "581", "17841", "7", 1l));
        cb.add(new ContaBancaria(null, usuario, "260", "001", "10400", "1", 1l));
        contaBancariaRepository.saveAll(cb);

        List<Equipamento> l = new ArrayList<>();
        l.add(new Equipamento(null, usuario, "TV", "55 polegadas, com conectividade por HDMI", 1l));
        l.add(new Equipamento(null, usuario, "Aparelho para videoconfêrencia", "O Logitech Conference Cam Connect CC300e contem uma câmera 1080p e viva-voz com cancelamento de ruído. Ideal para equipes com até 6 pessoas.", 1l));
        equipamentoRepository.saveAll(l);

        List<Servico> list = new ArrayList<>();
        list.add(new Servico(null, usuario, "Snacks", "Os snacks servem até 6 pessoas e são compostos das seguintes opções: biscoitos doces e salgados, pão de queijo e salada de frutas", new BigDecimal("70.00"), 1l));
        list.add(new Servico(null, usuario, "Água gelada", "Água gelada dísponivel para até 6 pessoas", new BigDecimal("15.00"), 1l));
        list.add(new Servico(null, usuario, "Material de escritório", "6 blocos para anotação e canetas", new BigDecimal("60.00"), 1l));
        list.add(new Servico(null, usuario, "Café", "Café dísponivel durante todo o tempo da reunião.", new BigDecimal("20.00"), 1l));
        servicoRepository.saveAll(list);

        List<Locacao> locacaoList = new ArrayList<>();
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-01 13:00"), formatDate("2023-02-01 14:00"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-01 15:00"), formatDate("2023-02-01 17:00"), new BigDecimal("20"), new BigDecimal("0"), new BigDecimal("200"), new BigDecimal("220"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-01 09:00"), formatDate("2023-02-01 12:00"), new BigDecimal("25"), new BigDecimal("400"), new BigDecimal("250"), new BigDecimal("675"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-02 10:00"), formatDate("2023-02-02 11:00"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-03 14:00"), formatDate("2023-02-03 16:00"), new BigDecimal("20"), new BigDecimal("0"), new BigDecimal("200"), new BigDecimal("220"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-03 10:00"), formatDate("2023-02-03 13:00"), new BigDecimal("25"), new BigDecimal("50"), new BigDecimal("250"), new BigDecimal("325"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-03 17:00"), formatDate("2023-02-03 18:00"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-06 15:00"), formatDate("2023-02-06 17:00"), new BigDecimal("20"), new BigDecimal("70"), new BigDecimal("200"), new BigDecimal("290"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-08 09:00"), formatDate("2023-02-08 12:00"), new BigDecimal("25"), new BigDecimal("0"), new BigDecimal("250"), new BigDecimal("275"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-08 15:00"), formatDate("2023-02-08 16:00"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-09 09:00"), formatDate("2023-02-09 11:00"), new BigDecimal("20"), new BigDecimal("100"), new BigDecimal("200"), new BigDecimal("320"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-09 11:30"), formatDate("2023-02-09 12:30"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-09 13:00"), formatDate("2023-02-09 14:00"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-09 15:00"), formatDate("2023-02-09 16:00"), new BigDecimal("10"), new BigDecimal("20"), new BigDecimal("100"), new BigDecimal("130"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-09 16:30"), formatDate("2023-02-09 17:30"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-13 09:00"), formatDate("2023-02-13 12:00"), new BigDecimal("25"), new BigDecimal("100"), new BigDecimal("250"), new BigDecimal("375"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-13 13:00"), formatDate("2023-02-13 15:00"), new BigDecimal("20"), new BigDecimal("0"), new BigDecimal("200"), new BigDecimal("220"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-13 15:30"), formatDate("2023-02-13 16:30"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-13 17:00"), formatDate("2023-02-13 19:00"), new BigDecimal("20"), new BigDecimal("0"), new BigDecimal("200"), new BigDecimal("220"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-14 13:00"), formatDate("2023-02-14 16:00"), new BigDecimal("25"), new BigDecimal("400"), new BigDecimal("250"), new BigDecimal("675"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-14 16:30"), formatDate("2023-02-14 17:30"), new BigDecimal("10"), new BigDecimal("50"), new BigDecimal("100"), new BigDecimal("160"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-14 08:00"), formatDate("2023-02-14 11:00"), new BigDecimal("20"), new BigDecimal("0"), new BigDecimal("200"), new BigDecimal("220"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-15 09:00"), formatDate("2023-02-15 12:00"), new BigDecimal("25"), new BigDecimal("100"), new BigDecimal("250"), new BigDecimal("375"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-15 13:00"), formatDate("2023-02-15 16:00"), new BigDecimal("25"), new BigDecimal("0"), new BigDecimal("250"), new BigDecimal("275"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-16 08:00"), formatDate("2023-02-16 09:00"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-16 09:30"), formatDate("2023-02-16 11:30"), new BigDecimal("20"), new BigDecimal("0"), new BigDecimal("200"), new BigDecimal("220"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-16 13:00"), formatDate("2023-02-16 16:00"), new BigDecimal("25"), new BigDecimal("70"), new BigDecimal("250"), new BigDecimal("345"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-16 17:00"), formatDate("2023-02-16 18:00"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-17 08:00"), formatDate("2023-02-17 10:00"), new BigDecimal("20"), new BigDecimal("0"), new BigDecimal("200"), new BigDecimal("220"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-17 11:00"), formatDate("2023-02-17 12:00"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-17 13:00"), formatDate("2023-02-17 14:00"), new BigDecimal("10"), new BigDecimal("50"), new BigDecimal("100"), new BigDecimal("160"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-17 15:00"), formatDate("2023-02-17 17:00"), new BigDecimal("20"), new BigDecimal("0"), new BigDecimal("200"), new BigDecimal("220"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-23 13:00"), formatDate("2023-02-23 16:00"), new BigDecimal("25"), new BigDecimal("0"), new BigDecimal("250"), new BigDecimal("275"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-23 17:00"), formatDate("2023-02-23 18:00"), new BigDecimal("10"), new BigDecimal("30"), new BigDecimal("100"), new BigDecimal("140"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-23 09:30"), formatDate("2023-02-23 10:30"), new BigDecimal("20"), new BigDecimal("0"), new BigDecimal("200"), new BigDecimal("220"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-24 08:00"), formatDate("2023-02-24 10:00"), new BigDecimal("20"), new BigDecimal("20"), new BigDecimal("200"), new BigDecimal("240"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-24 11:00"), formatDate("2023-02-24 12:00"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-24 13:00"), formatDate("2023-02-24 14:00"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-24 15:00"), formatDate("2023-02-24 17:00"), new BigDecimal("20"), new BigDecimal("200"), new BigDecimal("200"), new BigDecimal("420"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-27 17:00"), formatDate("2023-02-27 20:00"), new BigDecimal("25"), new BigDecimal("400"), new BigDecimal("250"), new BigDecimal("375"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-27 08:00"), formatDate("2023-02-27 10:00"), new BigDecimal("20"), new BigDecimal("0"), new BigDecimal("200"), new BigDecimal("220"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-27 11:00"), formatDate("2023-02-27 12:00"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-28 10:00"), formatDate("2023-02-28 11:00"), new BigDecimal("10"), new BigDecimal("20"), new BigDecimal("100"), new BigDecimal("130"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-28 11:30"), formatDate("2023-02-28 12:30"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-28 13:00"), formatDate("2023-02-28 14:00"), new BigDecimal("10"), new BigDecimal("0"), new BigDecimal("100"), new BigDecimal("110"), 1l));
        locacaoList.add(new Locacao(null, usuario, "Sala Savassi-BH", formatDate("2023-02-28 15:00"), formatDate("2023-02-28 17:00"), new BigDecimal("20"), new BigDecimal("120"), new BigDecimal("200"), new BigDecimal("360"), 1l));
        locacaoRepository.saveAll(locacaoList);
    }

    private void clearData() {
        servicoRepository.deleteAll();
        equipamentoRepository.deleteAll();
        contaBancariaRepository.deleteAll();
        locacaoRepository.deleteAll();
        usuarioRepository.deleteAll();
    }
}
