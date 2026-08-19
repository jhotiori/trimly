package org.trimly.backend.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.trimly.backend.dto.servico.ServicoCreateDTO;
import org.trimly.backend.dto.servico.ServicoResponseDTO;
import org.trimly.backend.dto.servico.ServicoUpdateDTO;
import org.trimly.backend.entity.enums.StatusServico;
import org.trimly.backend.service.ServicoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/servicos")
@RequiredArgsConstructor
public class ServicoController {
    private final ServicoService service;

    @GetMapping
    public ResponseEntity<List<ServicoResponseDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoResponseDTO> findById(@PathVariable Long id) {
        ServicoResponseDTO response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<List<ServicoResponseDTO>> findByStatus(
        @RequestParam(required = true) StatusServico status
    ) {
        List<ServicoResponseDTO> response = service.findByStatus(status);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ServicoResponseDTO> create(@Valid @RequestBody ServicoCreateDTO request) {
        ServicoResponseDTO response = service.save(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ServicoResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody ServicoUpdateDTO request) {
        ServicoResponseDTO response = service.update(id, request);
        return ResponseEntity.ok(response);
    }
}
