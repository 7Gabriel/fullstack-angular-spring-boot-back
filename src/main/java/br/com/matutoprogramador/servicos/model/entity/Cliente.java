package br.com.matutoprogramador.servicos.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
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

    private String name;

    @Column(nullable = false, length = 11)
    private String cpf;

    private LocalDate createdDate;

    @PrePersist
    public void setCreatedDate(){
        setCreatedDate(LocalDate.now());
    }
}
