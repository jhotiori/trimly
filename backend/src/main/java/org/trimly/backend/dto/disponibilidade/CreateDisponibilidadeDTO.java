package org.trimly.backend.dto.disponibilidade;

import jakarta.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.time.LocalTime;

public record CreateDisponibilidadeDTO(
    @NotNull(message = "O dia da semana é obrigatório")
    DayOfWeek diaSemana,

    @NotNull(message = "A hora de início é obrigatória")
    LocalTime horaInicio,

    @NotNull(message = "A hora de fim é obrigatória")
    LocalTime horaFim
) {}