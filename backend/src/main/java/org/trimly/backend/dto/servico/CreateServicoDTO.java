package org.trimly.backend.dto.servico;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record CreateServicoDTO(
    @NotBlank(message = "O nome do serviço é obrigatório")
    String nome,

    @NotNull(message = "O valor é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "O valor deve ser maior que zero")
    BigDecimal valor,

    @NotNull(message = "A duração é obrigatória")
    @Min(value = 1, message = "A duração deve ser de pelo menos 1 minuto")
    Integer duracao
) {}