package br.com.unnamed.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "equipamento")
public class Equipamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario.id")
    @JsonBackReference
    private Usuario usuario;
    private String descricao;
    private String detalhes;
    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long userid;
}
