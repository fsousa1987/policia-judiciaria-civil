package br.gov.francisco.policiajudiciariacivil.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "unidade")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UnidadeEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -1077625586025473452L;

    @Id
    @SequenceGenerator(
            name = "unidade_seq",
            sequenceName = "unidade_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "unidade_seq"
    )
    @Column(name = "unid_id")
    private Integer id;

    @Column(name = "unid_nome", nullable = false, length = 200)
    private String unidadeNome;

    @Column(name = "unid_sigla", nullable = false, length = 20)
    private String unidadeSigla;

    @ManyToMany(mappedBy = "unidades")
    private List<EnderecoEntity> enderecos = new ArrayList<>();

}
