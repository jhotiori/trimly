package org.trimly.backend.dto.servico;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ServicoCreateDTO {
    @NotBlank(message = "nome não pode ser vazio")
    private String nome;

    @NotNull(message = "valor não pode ser vazio")
    @Positive(message = "valor deve ser positivo")
    private BigDecimal valor;

    @NotNull(message = "duração não pode ser vazia")
    @Positive(message = "duração deve ser positiva")
    private Integer duracao;
}
