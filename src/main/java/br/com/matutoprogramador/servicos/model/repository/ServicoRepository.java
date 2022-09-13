package br.com.matutoprogramador.servicos.model.repository;

import br.com.matutoprogramador.servicos.model.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
}
