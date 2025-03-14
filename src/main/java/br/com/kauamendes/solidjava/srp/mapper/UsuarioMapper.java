package br.com.kauamendes.solidjava.srp.mapper;

import br.com.kauamendes.solidjava.common.dto.UsuarioDto;
import br.com.kauamendes.solidjava.common.entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "id", ignore = true)
    Usuario toEntity(UsuarioDto dto);

    UsuarioDto toDto(Usuario usuario);
}
