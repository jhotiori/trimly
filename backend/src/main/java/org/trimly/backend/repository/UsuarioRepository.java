package org.trimly.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.trimly.backend.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    List<UsuarioEntity> findByNomeLikeIgnoreCase(String nome);

    Optional<UsuarioEntity> findByEmail(String email);

    Boolean existsByEmail(String email);
}
