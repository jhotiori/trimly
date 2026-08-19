package org.trimly.backend.dto.disponibilidade;

import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;

import org.trimly.backend.entity.enums.DiaSemana;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DisponibilidadeCreateDTO {
    @NotNull(message = "dia da semana não pode ser nulo")
    private DiaSemana diaSemana;

    @NotNull(message = "hora de início não pode ser nula")
    private LocalTime horaInicio;

    @NotNull(message = "hora de fim não pode ser nula")
    private LocalTime horaFim;
}
