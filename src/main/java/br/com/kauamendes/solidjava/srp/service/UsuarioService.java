package br.com.kauamendes.solidjava.srp.service;

import br.com.kauamendes.solidjava.common.dto.UsuarioDto;
import br.com.kauamendes.solidjava.common.entities.Usuario;
import br.com.kauamendes.solidjava.common.exception.RegistroNaoEncontradoException;
import br.com.kauamendes.solidjava.srp.mapper.UsuarioMapper;
import br.com.kauamendes.solidjava.srp.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private static final String USUARIO_NAO_ENCONTRADO = "Usuário não encontrado";

    public UsuarioDto salvar(UsuarioDto usuarioDto) {
        Usuario usuario = usuarioRepository.save(usuarioMapper.toEntity(usuarioDto));
        return usuarioMapper.toDto(usuario);
    }

    public UsuarioDto buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException(USUARIO_NAO_ENCONTRADO));
        return usuarioMapper.toDto(usuario);
    }

    public List<UsuarioDto> listar() {
       return usuarioRepository.findAll().stream()
               .map(usuarioMapper::toDto)
               .toList();
    }

    public UsuarioDto atualizar(Long id, UsuarioDto usuarioDto) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException(USUARIO_NAO_ENCONTRADO));

        usuarioExistente.setNome(usuarioDto.getNome());
        usuarioExistente.setEmail(usuarioDto.getEmail());

        Usuario usuarioAtualizado = usuarioRepository.save(usuarioExistente);
        return usuarioMapper.toDto(usuarioAtualizado);
    }

    public void excluir(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RegistroNaoEncontradoException(USUARIO_NAO_ENCONTRADO);
        }
        usuarioRepository.deleteById(id);
    }
}
