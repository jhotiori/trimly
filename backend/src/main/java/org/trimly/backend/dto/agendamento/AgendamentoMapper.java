package org.trimly.backend.dto.agendamento;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;
import org.trimly.backend.entity.AgendamentoEntity;
import org.trimly.backend.entity.ServicoEntity;
import org.trimly.backend.entity.UsuarioEntity;
import org.trimly.backend.entity.enums.StatusAgendamento;

@Component
public class AgendamentoMapper {
    public AgendamentoEntity toEntity(AgendamentoCreateDTO request, UsuarioEntity usuario, ServicoEntity servico) {
        AgendamentoEntity entity = new AgendamentoEntity();

        entity.setHorario(LocalDateTime.of(request.getData(), request.getHorario()));
        entity.setDuracao(servico.getDuracao());
        entity.setStatus(StatusAgendamento.AGENDADO);
        entity.setUsuario(usuario);
        entity.setServico(servico);

        return entity;
    }

    public AgendamentoResponseDTO toResponse(AgendamentoEntity entity) {
        AgendamentoResponseDTO response = new AgendamentoResponseDTO();

        response.setId(entity.getId());
        response.setHorario(entity.getHorario());
        response.setDuracao(entity.getDuracao());
        response.setStatus(entity.getStatus());
        response.setUsuarioId(entity.getUsuario().getId());
        response.setServicoId(entity.getServico().getId());

        return response;
    }

    public List<AgendamentoResponseDTO> toResponseList(List<? extends AgendamentoEntity> original) {
        return original.stream().map(this::toResponse).toList();
    }
}
