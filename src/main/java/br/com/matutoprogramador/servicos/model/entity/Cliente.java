package br.com.matutoprogramador.servicos.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    @NotEmpty
    private String name;

    @Column(nullable = false, length = 11)
    @NotNull
    @CPF
    private String cpf;

    private LocalDate createdDate;

    @PrePersist
    public void setCreatedDate(){
        setCreatedDate(LocalDate.now());
    }
}
