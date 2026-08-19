package org.trimly.backend.service;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.trimly.backend.dto.usuario.UsuarioCreateDTO;
import org.trimly.backend.dto.usuario.UsuarioMapper;
import org.trimly.backend.dto.usuario.UsuarioResponseDTO;
import org.trimly.backend.dto.usuario.UsuarioUpdateDTO;
import org.trimly.backend.entity.UsuarioEntity;
import org.trimly.backend.entity.enums.CargoUsuario;
import org.trimly.backend.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UsuarioResponseDTO save(UsuarioCreateDTO request) {
        UsuarioEntity entity = mapper.toEntity(request);
        entity.setCargo(CargoUsuario.CLIENTE);
        entity.setSenha(passwordEncoder.encode(request.getSenha()));

        entity = repository.save(entity);
        return mapper.toResponse(entity);
    }

    public UsuarioResponseDTO update(Long id, UsuarioUpdateDTO request) {
        UsuarioEntity entity = this.findByIdRaw(id);

        String nome = request.getNome();
        if (nome != null && !nome.isBlank()) {
            entity.setNome(nome);
        }

        String email = request.getEmail();
        if (email != null && !email.isBlank()) {
            entity.setEmail(email);
        }

        String senha = request.getSenha();
        if (senha != null && !senha.isBlank()) {
            entity.setSenha(passwordEncoder.encode(senha));
        }

        entity = repository.save(entity);
        return mapper.toResponse(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<UsuarioResponseDTO> findAll() {
        return mapper.toResponseList(repository.findAll());
    }

    public UsuarioResponseDTO findById(Long id) {
        UsuarioEntity entity = this.findByIdRaw(id);
        return mapper.toResponse(entity);
    }

    public UsuarioEntity findByIdRaw(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario com id " + id + " não foi encontrado"));
    }

    public List<UsuarioResponseDTO> findByNome(String nome) {
        return mapper.toResponseList(repository.findByNomeLikeIgnoreCase(nome));
    }
}
