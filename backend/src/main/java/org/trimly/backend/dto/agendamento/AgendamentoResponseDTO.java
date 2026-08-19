package org.trimly.backend.dto.agendamento;

import java.time.LocalDateTime;

import org.trimly.backend.entity.enums.StatusAgendamento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoResponseDTO {
    private Long id;
    private LocalDateTime horario;
    private Integer duracao;
    private StatusAgendamento status;
    private Long usuarioId;
    private Long servicoId;
}
