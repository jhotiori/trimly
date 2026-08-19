package org.trimly.backend.entity;

import jakarta.persistence.*;

import org.trimly.backend.entity.enums.StatusServico;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "servicos")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServicoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @Column(name = "valor", nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(name = "duracao", nullable = false)
    private Integer duracao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusServico status;
}
