package br.gov.francisco.policiajudiciariacivil.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Table(name = "endereco")
@Entity
public class EnderecoEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(
            name = "endereco_seq",
            sequenceName = "endereco_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "endereco_seq"
    )
    @Column(name = "end_id")
    private Integer id;

    @Column(name = "end_tipo_logradouro", nullable = false, length = 50)
    @EqualsAndHashCode.Include
    private String tipoLogradouro;

    @Column(name = "end_logradouro", nullable = false, length = 200)
    @EqualsAndHashCode.Include
    private String logradouro;

    @Column(name = "end_numero", nullable = false)
    @EqualsAndHashCode.Include
    private Integer numero;

    @Column(name = "end_bairro", nullable = false, length = 100)
    @EqualsAndHashCode.Include
    private String bairro;

    @ManyToMany(mappedBy = "enderecos")
    private List<PessoaEntity> pessoas = new ArrayList<>();

}
