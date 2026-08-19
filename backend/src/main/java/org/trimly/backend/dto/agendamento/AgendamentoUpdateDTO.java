package org.trimly.backend.dto.agendamento;

import java.time.LocalDateTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoUpdateDTO {
    @FutureOrPresent(message = "Horario deve estar no presente ou futuro")
    LocalDateTime horario;

    @Positive(message = "Id do Serviço deve ser positivo")
    Long servicoId;
}
