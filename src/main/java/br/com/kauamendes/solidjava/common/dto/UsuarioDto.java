package br.com.kauamendes.solidjava.common.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {

    private Long id;

    @NotEmpty
    @Size(min = 2)
    private String nome;

    @NotEmpty
    @Email
    private String email;
}
