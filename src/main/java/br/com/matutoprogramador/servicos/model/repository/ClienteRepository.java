package br.com.matutoprogramador.servicos.model.repository;

import br.com.matutoprogramador.servicos.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
