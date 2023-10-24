package br.com.tabacetabacaria.domain.models;


import br.com.tabacetabacaria.domain.enums.CategoriaProduto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    private static final String CAMPO_NAO_INFORMADO = "Campo não informado";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = CAMPO_NAO_INFORMADO)
    @Length(min = 2, max = 100, message = "Nome deve ter entre 2 a 100 caracteres")
    private String  nome;

    @NotBlank(message = CAMPO_NAO_INFORMADO)
    @Positive(message = "Valor tem que ser positivo")
    private Double preco;

    @NotBlank(message = CAMPO_NAO_INFORMADO)
    private CategoriaProduto categoria;
}