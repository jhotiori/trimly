package org.trimly.backend.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.trimly.backend.dto.agendamento.AgendamentoCreateDTO;
import org.trimly.backend.dto.agendamento.AgendamentoMapper;
import org.trimly.backend.dto.agendamento.AgendamentoResponseDTO;
import org.trimly.backend.dto.agendamento.AgendamentoUpdateDTO;
import org.trimly.backend.dto.disponibilidade.DisponibilidadeResponseDTO;
import org.trimly.backend.entity.AgendamentoEntity;
import org.trimly.backend.entity.ServicoEntity;
import org.trimly.backend.entity.UsuarioEntity;
import org.trimly.backend.entity.enums.DiaSemana;
import org.trimly.backend.entity.enums.StatusAgendamento;
import org.trimly.backend.exception.agendamento.AgendamentoConflitoException;
import org.trimly.backend.exception.agendamento.AgendamentoException;
import org.trimly.backend.exception.agendamento.AgendamentoForaDoHorarioException;
import org.trimly.backend.exception.agendamento.AgendamentoSemDisponibilidadeException;
import org.trimly.backend.repository.AgendamentoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AgendamentoService {
    private final UsuarioService usuarioService;
    private final ServicoService servicoService;
    private final DisponibilidadeService disponibilidadeService;

    private final AgendamentoRepository repository;
    private final AgendamentoMapper mapper;

    public AgendamentoResponseDTO save(AgendamentoCreateDTO request) {
        UsuarioEntity usuario = usuarioService.findByIdRaw(request.getUsuarioId());
        ServicoEntity servico = servicoService.findByIdRaw(request.getServicoId());
        AgendamentoEntity entity = mapper.toEntity(request, usuario, servico);

        // 1. Calcula início e fim do novo agendamento
        LocalDateTime inicioAgendamento = entity.getHorario();
        LocalDateTime fimAgendamento = calculateFimAgendamento(
                inicioAgendamento,
                servico.getDuracao());

        // 2. Valida período do agendamento
        validateHorarioFuturo(
                inicioAgendamento,
                fimAgendamento);

        // 3. Valida disponibilidade da barbearia
        validateDisponibilidade(
                inicioAgendamento,
                fimAgendamento);

        // 4. Valida conflitos com outros agendamentos
        validateConflitoDeHorario(
                null,
                inicioAgendamento,
                fimAgendamento);

        // 5. Salva
        entity = repository.save(entity);
        return mapper.toResponse(entity);
    }

    public AgendamentoResponseDTO update(
            Long agendamentoId,
            AgendamentoUpdateDTO request) {

        AgendamentoEntity entity = this.findByIdRaw(agendamentoId);
        validateAgendamentoPodeSerAlterado(entity);

        if (request.getHorario() == null && request.getServicoId() == null) {
            throw new AgendamentoException(
                    "É necessário informar pelo menos um campo para atualizar");
        }

        // 1. Determina o novo horário
        LocalDateTime novoHorario = request.getHorario() != null
                ? request.getHorario()
                : entity.getHorario();

        // 2. Determina o novo serviço
        ServicoEntity novoServico = request.getServicoId() != null
                ? servicoService.findByIdRaw(request.getServicoId())
                : entity.getServico();

        // 3. Determina a nova duração
        Integer novaDuracao = request.getServicoId() != null
                ? novoServico.getDuracao()
                : entity.getDuracao();

        // 4. Calcula o novo fim
        LocalDateTime novoFimHorario = calculateFimAgendamento(
                novoHorario,
                novaDuracao);

        // 5. Valida período
        validateHorarioFuturo(
                novoHorario,
                novoFimHorario);

        // 6. Valida disponibilidade
        validateDisponibilidade(
                novoHorario,
                novoFimHorario);

        // 7. Valida conflitos, ignorando o próprio agendamento
        validateConflitoDeHorario(
                entity.getId(),
                novoHorario,
                novoFimHorario);

        // 8. Aplica as alterações
        entity.setHorario(novoHorario);
        entity.setServico(novoServico);
        entity.setDuracao(novaDuracao);

        // 9. Salva
        entity = repository.save(entity);
        return mapper.toResponse(entity);
    }

    public AgendamentoEntity findByIdRaw(Long id) {
        AgendamentoEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento com id " + id + " não foi encontrado"));
        return entity;
    }

    public List<AgendamentoResponseDTO> findByStatus(StatusAgendamento status) {
        return mapper.toResponseList(repository.findByStatus(status));
    }

    public List<AgendamentoResponseDTO> findByStatusAndPeriodo(StatusAgendamento status, LocalDateTime inicio,
            LocalDateTime fim) {
        return mapper.toResponseList(
                repository.findByStatusAndHorarioGreaterThanEqualAndHorarioLessThan(status, inicio, fim));
    }

    private void validateDisponibilidade(
            LocalDateTime inicioAgendamento,
            LocalDateTime fimAgendamento) {
        DiaSemana diaSemanaAgendamento = DiaSemana.from(inicioAgendamento.getDayOfWeek());
        List<DisponibilidadeResponseDTO> disponibilidades = disponibilidadeService
                .findByDiaSemana(diaSemanaAgendamento);

        if (disponibilidades.isEmpty()) {
            throw new AgendamentoSemDisponibilidadeException(
                    "Não há nenhuma disponibilidade no dia '"
                            + diaSemanaAgendamento
                            + "' para realizar este agendamento");
        }

        LocalTime horaInicioAgendamento = inicioAgendamento.toLocalTime();
        LocalTime horaFimAgendamento = fimAgendamento.toLocalTime();

        boolean estaDentroDeUmaDisponibilidade = false;
        for (DisponibilidadeResponseDTO disponibilidade : disponibilidades) {
            LocalTime horaInicioDisponibilidade = disponibilidade.getHoraInicio();
            LocalTime horaFimDisponibilidade = disponibilidade.getHoraFim();

            boolean inicioEstaDentro = !horaInicioAgendamento.isBefore(horaInicioDisponibilidade);
            boolean fimEstaDentro = !horaFimAgendamento.isAfter(horaFimDisponibilidade);

            if (inicioEstaDentro && fimEstaDentro) {
                estaDentroDeUmaDisponibilidade = true;
                break;
            }
        }

        if (!estaDentroDeUmaDisponibilidade) {
            throw new AgendamentoSemDisponibilidadeException(
                    "Não há disponibilidade suficiente no dia '"
                            + diaSemanaAgendamento
                            + "' para realizar este agendamento");
        }
    }

    private void validateConflitoDeHorario(
            Long agendamentoId,
            LocalDateTime inicioNovoAgendamento,
            LocalDateTime fimNovoAgendamento) {
        LocalDate dataAgendamento = inicioNovoAgendamento.toLocalDate();
        LocalDateTime inicioDoDia = dataAgendamento.atStartOfDay();
        LocalDateTime inicioDoProximoDia = dataAgendamento.plusDays(1).atStartOfDay();
        List<AgendamentoEntity> agendamentosExistentes = repository
                .findByStatusAndHorarioGreaterThanEqualAndHorarioLessThan(
                        StatusAgendamento.AGENDADO,
                        inicioDoDia,
                        inicioDoProximoDia);

        for (AgendamentoEntity agendamentoExistente : agendamentosExistentes) {
            // Durante um update, ignora o próprio agendamento.
            if (agendamentoId != null && agendamentoExistente.getId().equals(agendamentoId)) {
                continue;
            }

            LocalDateTime inicioAgendamentoExistente = agendamentoExistente.getHorario();
            LocalDateTime fimAgendamentoExistente = calculateFimAgendamento(
                    inicioAgendamentoExistente,
                    agendamentoExistente.getDuracao());

            boolean inicioExistenteAntesDoFimNovo = inicioAgendamentoExistente.isBefore(fimNovoAgendamento);
            boolean fimExistenteDepoisDoInicioNovo = fimAgendamentoExistente.isAfter(inicioNovoAgendamento);
            boolean existeConflito = inicioExistenteAntesDoFimNovo && fimExistenteDepoisDoInicioNovo;

            if (existeConflito) {
                throw new AgendamentoConflitoException(
                        "O horário escolhido já está ocupado por outro agendamento");
            }
        }
    }

    private void validateHorarioFuturo(
            LocalDateTime inicioAgendamento,
            LocalDateTime fimAgendamento) {
        if (!inicioAgendamento.toLocalDate().equals(fimAgendamento.toLocalDate())) {
            throw new AgendamentoForaDoHorarioException(
                    "O agendamento não pode ultrapassar o horário de um dia para o outro");
        }
    }

    private void validateAgendamentoPodeSerAlterado(
            AgendamentoEntity agendamento) {
        if (agendamento.getStatus() != StatusAgendamento.AGENDADO) {
            throw new AgendamentoException(
                    "O agendamento não pode ser alterado neste status");
        }
    }

    private LocalDateTime calculateFimAgendamento(
            LocalDateTime inicioAgendamento,
            Integer duracao) {
        return inicioAgendamento.plusMinutes(duracao);
    }
}
