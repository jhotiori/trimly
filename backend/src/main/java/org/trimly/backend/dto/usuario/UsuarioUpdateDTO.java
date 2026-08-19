package org.trimly.backend.dto.usuario;

import jakarta.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioUpdateDTO {
    private String nome;

    @Email(message = "não foi recebido um formato de e-mail válido")
    private String email;

    private String senha;
}
