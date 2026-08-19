package org.trimly.backend.service;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.trimly.backend.dto.servico.ServicoCreateDTO;
import org.trimly.backend.dto.servico.ServicoMapper;
import org.trimly.backend.dto.servico.ServicoResponseDTO;
import org.trimly.backend.dto.servico.ServicoUpdateDTO;
import org.trimly.backend.entity.ServicoEntity;
import org.trimly.backend.entity.enums.StatusServico;
import org.trimly.backend.exception.servico.ServicoException;
import org.trimly.backend.repository.ServicoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServicoService {
    private final ServicoRepository repository;
    private final ServicoMapper mapper;

    public ServicoResponseDTO save(ServicoCreateDTO request) {
        ServicoEntity entity = mapper.toEntity(request);
        entity.setStatus(StatusServico.ATIVO);

        List<ServicoResponseDTO> existentes = findByNome(request.getNome());
        if (!existentes.isEmpty()) {
            throw new ServicoException("Serviço com o nome '" + request.getNome() + "' já existe");
        }

        entity = repository.save(entity);
        return mapper.toResponse(entity);
    }

    public ServicoResponseDTO update(Long id, ServicoUpdateDTO request) {
        ServicoEntity entity = this.findByIdRaw(id);

        String nome = request.getNome();
        if (nome != null && !nome.isBlank()) {
            entity.setNome(nome);
        }

        BigDecimal valor = request.getValor();
        if (valor != null && valor.compareTo(BigDecimal.ZERO) > 0) {
            entity.setValor(valor);
        }

        Integer duracao = request.getDuracao();
        if (duracao != null && duracao > 0) {
            entity.setDuracao(duracao);
        }

        entity = repository.save(entity);
        return mapper.toResponse(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<ServicoResponseDTO> findAll() {
        return mapper.toResponseList(repository.findAll());
    }

    public ServicoResponseDTO findById(Long id) {
        ServicoEntity entity = this.findByIdRaw(id);
        return mapper.toResponse(entity);
    }

    public ServicoEntity findByIdRaw(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Serviço com id " + id + " não encontrado"));
    }

    public List<ServicoResponseDTO> findByNome(String nome) {
        return mapper.toResponseList(repository.findByNomeLikeIgnoreCase(nome));
    }

    public List<ServicoResponseDTO> findByStatus(StatusServico status) {
        return mapper.toResponseList(repository.findByStatusEqualsIgnoreCase(status.toString()));
    }
}
