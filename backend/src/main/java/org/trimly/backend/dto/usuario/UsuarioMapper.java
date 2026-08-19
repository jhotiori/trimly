package org.trimly.backend.dto.usuario;

import java.util.List;

import org.hibernate.mapping.Any;
import org.springframework.stereotype.Component;
import org.trimly.backend.entity.UsuarioEntity;

@Component
public class UsuarioMapper {
    public UsuarioEntity toEntity(UsuarioCreateDTO request) {
        UsuarioEntity entity = new UsuarioEntity();

        entity.setNome(request.getNome());
        entity.setEmail(request.getEmail());

        return entity;
    }

    public UsuarioResponseDTO toResponse(UsuarioEntity entity) {
        UsuarioResponseDTO response = new UsuarioResponseDTO();

        response.setId(entity.getId());
        response.setNome(entity.getNome());
        response.setEmail(entity.getEmail());
        response.setCargo(entity.getCargo());

        return response;
    }

    public List<UsuarioResponseDTO> toResponseList(List<? extends UsuarioEntity> original) {
        return original.stream().map(this::toResponse).toList();
    }
}
