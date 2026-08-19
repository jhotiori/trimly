package org.trimly.backend.service;

import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.trimly.backend.dto.disponibilidade.DisponibilidadeCreateDTO;
import org.trimly.backend.dto.disponibilidade.DisponibilidadeMapper;
import org.trimly.backend.dto.disponibilidade.DisponibilidadeResponseDTO;
import org.trimly.backend.dto.disponibilidade.DisponibilidadeUpdateDTO;
import org.trimly.backend.entity.DisponibilidadeEntity;
import org.trimly.backend.entity.enums.DiaSemana;
import org.trimly.backend.exception.disponibilidade.DisponibilidadeException;
import org.trimly.backend.repository.DisponibilidadeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DisponibilidadeService {
    private final DisponibilidadeRepository repository;
    private final DisponibilidadeMapper mapper;

    public DisponibilidadeResponseDTO save(DisponibilidadeCreateDTO request) {
        DisponibilidadeEntity entity = mapper.toEntity(request);
        validateDisponibilidadeHorarios(entity);

        entity = repository.save(entity);
        return mapper.toResponse(entity);
    }

    public DisponibilidadeResponseDTO update(Long id, DisponibilidadeUpdateDTO request) {
        DisponibilidadeEntity entity = this.findByIdRaw(id);

        DiaSemana diaSemana = request.getDiaSemana();
        if (diaSemana != null) {
            entity.setDiaSemana(diaSemana);
        }

        LocalTime horaInicio = request.getHoraInicio();
        if (horaInicio != null) {
            entity.setHoraInicio(horaInicio);
        }

        LocalTime horaFim = request.getHoraFim();
        if (horaFim != null) {
            entity.setHoraFim(horaFim);
        }

        validateDisponibilidadeHorarios(entity);
        entity = repository.save(entity);
        return mapper.toResponse(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public DisponibilidadeResponseDTO findById(Long id) {
        DisponibilidadeEntity entity = this.findByIdRaw(id);
        return mapper.toResponse(entity);
    }

    public DisponibilidadeEntity findByIdRaw(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Disponibilidade com id " + id + " não foi encontrada"));
    }

    public List<DisponibilidadeResponseDTO> findByDiaSemana(DiaSemana diaSemana) {
        return mapper.toResponseList(repository.findByDiaSemana(diaSemana));
    }

    private void validateDisponibilidadeHorarios(DisponibilidadeEntity entity) {
        if (!entity.getHoraInicio().isBefore(entity.getHoraFim())) {
            throw new DisponibilidadeException("Horário de inicio deve ser menor do que o horário de fim");
        }
    }

}
