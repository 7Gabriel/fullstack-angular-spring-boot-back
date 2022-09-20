package br.com.matutoprogramador.servicos.model.repository;

import br.com.matutoprogramador.servicos.model.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServicoRepository extends JpaRepository<Servico, Long> {

    @Query(value = "select * from servico c where c.id_cliente = :idCliente", nativeQuery = true)
    List<Servico> findByIdCliente(@Param("idCliente") Long idCliente);

    @Query("select s from Servico s join s.cliente c " +
            "where upper( c.name ) like upper( :nome ) and MONTH(s.dataServico) = :mes")
    List<Servico> findByNomeClienteAndMes(@Param("nome") String nome, @Param("mes") Integer mes);
}
