package br.gov.francisco.policiajudiciariacivil.entity;

import br.gov.francisco.policiajudiciariacivil.enums.Sexo;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "pessoa")
@Entity
public class Pessoa implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @Id
    @SequenceGenerator(
            name = "pessoa_seq",
            sequenceName = "pessoa_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pessoa_seq"
    )
    @Column(name = "pes_id")
    private Integer id;

    @Column(name = "pes_nome", nullable = false)
    private String nome;

    @Column(name = "pes_data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    @Column(name = "pes_sexo", nullable = false)
    private Sexo sexo;

    @Column(name = "pes_mae", nullable = false)
    private String nomeMae;

    @Column(name = "pes_pai", nullable = false)
    private String nomePai;

    @ManyToMany
    @JoinTable(
            name = "pessoa_endereco",
            joinColumns = @JoinColumn(name = "pes_id"),
            inverseJoinColumns = @JoinColumn(name = "end_id")
    )
    private Set<Endereco> enderecos = new HashSet<>();

}
