package org.trimly.backend.dto.agendamento;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.FutureOrPresent;
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
public class AgendamentoCreateDTO {
    @NotNull(message = "Data não pode ser nula")
    @FutureOrPresent(message = "Data deve estar no presente ou futuro")
    private LocalDate data;

    @NotNull(message = "Horário não pode ser nulo")
    private LocalTime horario;

    @NotNull(message = "Usuário não pode ser nulo")
    @Positive(message = "Id de Usuário deve ser positivo")
    private Long usuarioId;

    @NotNull(message = "Serviço não pode ser nulo")
    @Positive(message = "Id de Serviço deve ser positivo")
    private Long servicoId;
}
