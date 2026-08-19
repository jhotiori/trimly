package org.trimly.backend.dto.servico;

import java.math.BigDecimal;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import org.trimly.backend.entity.enums.StatusServico;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ServicoUpdateDTO {
    private String nome;

    @Positive(message = "valor deve ser positivo")
    private BigDecimal valor;

    @Positive(message = "duração deve ser positiva")
    private Integer duracao;

    private StatusServico status;
}
