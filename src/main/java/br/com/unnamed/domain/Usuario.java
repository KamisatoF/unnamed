package br.com.unnamed.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, orphanRemoval = false)
    @JsonManagedReference
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Servico> servicoList = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, orphanRemoval = false)
    @JsonManagedReference
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Locacao> locacaoList = new ArrayList<>();
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;
    private Boolean recebeInformacoesEmail;
    @Transient
    private String recebeInformacoesEmailString;

}
