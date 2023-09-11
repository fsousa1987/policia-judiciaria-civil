package br.gov.francisco.policiajudiciariacivil.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "endereco")
@Entity
public class Endereco implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
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
    private String tipoLogradouro;

    @Column(name = "end_logradouro", nullable = false, length = 200)
    private String logradouro;

    @Column(name = "end_numero", nullable = false)
    private Integer numero;

    @Column(name = "end_bairro", nullable = false, length = 100)
    private String bairro;

}
