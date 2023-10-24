package br.com.tabacetabacaria.domain.models;


import br.com.tabacetabacaria.domain.enums.TipoPagamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Campo não informado")

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany
    private List<Produto> produtos;

    private Double valorTotal;

    private TipoPagamento tipo;

    private LocalDateTime data;

    private Double calcularValorTotal(){
        Double valor = 0.0;
        for(Produto produto : produtos){
            valor+=produto.getPreco();
        }
        return valor;
    }
}
