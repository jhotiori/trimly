package org.trimly.backend.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioCreateDTO {

    @NotBlank(message = "nome não pode ser vazio")
    private String nome;

    @NotBlank(message = "email não pode ser vazio")
    @Email(message = "não foi recebido um formato de e-mail válido")
    private String email;

    @NotBlank(message = "senha não pode ser vazia")
    private String senha;
}
