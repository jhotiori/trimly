package org.trimly.backend.dto.servico;

import java.math.BigDecimal;

import org.trimly.backend.entity.enums.StatusServico;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ServicoResponseDTO {
    private Long id;
    private String nome;
    private BigDecimal valor;
    private Integer duracao;
    private StatusServico status;
}
