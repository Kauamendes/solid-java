package br.com.kauamendes.solidjava.srp.service;

import br.com.kauamendes.solidjava.common.dto.UsuarioDto;
import br.com.kauamendes.solidjava.common.exception.RegistroNaoEncontradoException;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    private UsuarioDto usuarioDto;

    @Nested
    class Dado_um_usuario_valido {

        @BeforeEach
        void setUp() {
            usuarioDto = UsuarioDto.builder()
                    .nome("João")
                    .email("joao@email.com")
                    .build();
        }

        @Nested
        class Quando_salvar_usuario {

            private UsuarioDto usuarioSalvo;

            @BeforeEach
            void setUp() {
                usuarioSalvo = usuarioService.salvar(usuarioDto);
            }

            @Test
            void Entao_deve_retornar_usuario_criado() {
                assertNotNull(usuarioDto);
                assertEquals("João", usuarioSalvo.getNome());
                assertEquals("joao@email.com", usuarioSalvo.getEmail());
            }

            @Nested
            class Quando_excluir_usuario {

                @Test
                void Entao_deve_excluir_usuario_sem_excecoes() {
                    assertDoesNotThrow(() -> usuarioService.excluir(usuarioSalvo.getId()));
                }
            }
        }
    }

    @Nested
    class Dado_um_usuario_existente_na_base {

        @BeforeEach
        void setUp() {
            usuarioDto = UsuarioDto.builder()
                    .nome("Pedro")
                    .email("pedro@email.com")
                    .build();
            usuarioDto = usuarioService.salvar(usuarioDto);
        }

        @Nested
        class Quando_listar_usuarios {

            @Test
            void Entao_deve_retornar_lista_de_usuarios() {
                List<UsuarioDto> resultado = usuarioService.listar();

                assertNotNull(resultado);
                assertFalse(resultado.isEmpty());
            }
        }

        @Nested
        class Quando_buscar_usuario_por_id {

            @Test
            void Entao_deve_retornar_usuario() {
                UsuarioDto resultado = usuarioService.buscarPorId(usuarioDto.getId());

                assertNotNull(resultado);
                assertEquals("Pedro", resultado.getNome());
                assertEquals("pedro@email.com", resultado.getEmail());
            }
        }

        @Nested
        class Quando_atualizar_usuario {

            private UsuarioDto usuarioAtualizado;

            @BeforeEach
            void setUp() {
                usuarioDto.setNome("Usuario Atualizado");
                usuarioDto.setEmail("pedro@emailatualizado.com");
                usuarioAtualizado = usuarioService.atualizar(usuarioDto.getId(), usuarioDto);
            }

            @Test
            void Entao_deve_retornar_usuario_atualizado() {
                assertNotNull(usuarioAtualizado);
                assertEquals("Usuario Atualizado", usuarioAtualizado.getNome());
                assertEquals("pedro@emailatualizado.com", usuarioAtualizado.getEmail());
            }
        }
    }

    @Nested
    class Dado_um_usuario_nao_existente_na_base {

        @BeforeEach
        void setUp() {
            usuarioDto = UsuarioDto.builder()
                    .nome("João")
                    .email("joao@email.com")
                    .build();
            usuarioService.salvar(usuarioDto);
        }

        @Nested
        class Quando_buscar_usuario_por_id_inexistente {

            @Test
            void Entao_deve_lancar_excecao_registro_nao_encontrado() {
                assertThrows(RegistroNaoEncontradoException.class, () -> usuarioService.buscarPorId(10L));
            }
        }

        @Nested
        class Quando_atualizar_usuario_inexistente {

            @Test
            void Entao_deve_lancar_excecao_registro_nao_encontrado() {
                assertThrows(RegistroNaoEncontradoException.class, () -> usuarioService.atualizar(10L, usuarioDto));
            }
        }

        @Nested
        class Quando_excluir_usuario_inexistente {

            @Test
            void Entao_deve_lancar_excecao_usuario_nao_encontrado() {
                assertThrows(RegistroNaoEncontradoException.class, () -> usuarioService.excluir(10L));
            }
        }
    }
}
