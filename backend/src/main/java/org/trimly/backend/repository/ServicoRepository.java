package org.trimly.backend.repository;

import java.security.Provider.Service;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.trimly.backend.entity.ServicoEntity;

public interface ServicoRepository extends JpaRepository<ServicoEntity, Long> {
    List<ServicoEntity> findByNomeLikeIgnoreCase(String nome);

    List<ServicoEntity> findByStatusEqualsIgnoreCase(String status);
}
