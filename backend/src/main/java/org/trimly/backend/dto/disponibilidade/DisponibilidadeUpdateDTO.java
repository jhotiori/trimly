package org.trimly.backend.dto.disponibilidade;

import java.time.LocalTime;

import org.trimly.backend.entity.enums.DiaSemana;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DisponibilidadeUpdateDTO {
    private DiaSemana diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFim;
}
