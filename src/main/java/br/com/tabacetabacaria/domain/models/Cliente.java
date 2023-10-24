package br.com.tabacetabacaria.domain.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.List;

@Entity
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    private static final String CAMPO_NAO_INFORMADO = "Campo não informado";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = CAMPO_NAO_INFORMADO)
    @Length(min = 2, max = 100, message = "Nome deve ter entre 2 a 100 caracteres")
    private String nome;

    @NotBlank(message = CAMPO_NAO_INFORMADO)
    @Length(min = 2, max = 100, message = "Sobrenome deve ter entre 2 a 100 caracteres")
    private String sobrenome;

    @Email(message = CAMPO_NAO_INFORMADO)
    @NotBlank(message = "Campo não informado")
    private String email;

    @NotBlank(message = CAMPO_NAO_INFORMADO)
    private LocalDate dataNascimento;

    @OneToMany
    @JoinColumn(name = "endereco_id_cliente")
    private List<Endereco> endereco;
}
