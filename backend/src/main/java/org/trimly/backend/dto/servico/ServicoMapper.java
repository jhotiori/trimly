package org.trimly.backend.dto.servico;

import java.util.List;

import org.springframework.stereotype.Component;
import org.trimly.backend.entity.ServicoEntity;
import org.trimly.backend.entity.enums.StatusServico;

@Component
public class ServicoMapper {
    public ServicoEntity toEntity(ServicoCreateDTO request) {
        ServicoEntity entity = new ServicoEntity();

        entity.setNome(request.getNome());
        entity.setValor(request.getValor());
        entity.setDuracao(request.getDuracao());
        entity.setStatus(StatusServico.ATIVO);

        return entity;
    }

    public ServicoResponseDTO toResponse(ServicoEntity entity) {
        ServicoResponseDTO response = new ServicoResponseDTO();

        response.setId(entity.getId());
        response.setNome(entity.getNome());
        response.setValor(entity.getValor());
        response.setDuracao(entity.getDuracao());
        response.setStatus(entity.getStatus());

        return response;
    }

    public List<ServicoResponseDTO> toResponseList(List<? extends ServicoEntity> original) {
        return original.stream().map(this::toResponse).toList();
    }
}
