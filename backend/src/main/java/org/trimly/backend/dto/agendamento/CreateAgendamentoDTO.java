package org.trimly.backend.dto.agendamento;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public record CreateAgendamentoDTO(
    @NotNull(message = "A data é obrigatória")
    @FutureOrPresent(message = "A data do agendamento deve ser presente ou futura")
    LocalDate data,

    @NotNull(message = "O horário é obrigatório")
    LocalTime horario,

    @NotNull(message = "A duração é obrigatória")
    Integer duracao,

    @NotNull(message = "O ID do serviço é obrigatório")
    Long servicoId,

    @NotNull(message = "O ID do usuário é obrigatório")
    Long usuarioId

) {}