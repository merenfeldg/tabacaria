package br.com.tabacetabacaria.repository;

import br.com.tabacetabacaria.domain.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
