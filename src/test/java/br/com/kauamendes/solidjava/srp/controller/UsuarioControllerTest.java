package br.com.kauamendes.solidjava.srp.controller;

import br.com.kauamendes.solidjava.common.dto.UsuarioDto;
import br.com.kauamendes.solidjava.srp.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UsuarioController.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UsuarioService usuarioServiceMock;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new UsuarioController(usuarioServiceMock)).build();
    }

    @Nested
    class Dado_um_usuario_valido {

        private UsuarioDto usuarioDto;
        private ResultActions resposta;

        @BeforeEach
        void setUp() {
            usuarioDto = UsuarioDto.builder()
                    .id(1L)
                    .nome("João")
                    .email("joao@email.com")
                    .build();
        }

        @Nested
        class Quando_criar_usuario {



            @BeforeEach
            void setUp() throws Exception {
                when(usuarioServiceMock.salvar(any(UsuarioDto.class))).thenReturn(usuarioDto);
                resposta = mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuarioDto)));
            }

            @Test
            void Entao_deve_retornar_status_created_e_usuario_criado() throws Exception {
                resposta.andExpect(status().isCreated())
                        .andExpect(jsonPath("$.id").value(usuarioDto.getId()))
                        .andExpect(jsonPath("$.nome").value(usuarioDto.getNome()))
                        .andExpect(jsonPath("$.email").value(usuarioDto.getEmail()));
            }
        }

        @Nested
        class Quando_atualizar_usuario {

            @BeforeEach
            void setUp() throws Exception {
                when(usuarioServiceMock.atualizar(anyLong(), any(UsuarioDto.class))).thenReturn(usuarioDto);
                resposta = mockMvc.perform(put("/usuarios/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuarioDto)));
            }

            @Test
            void Entao_deve_retornar_status_ok_e_usuario_atualizado() throws Exception {
                resposta.andExpect(status().isOk())
                        .andExpect(jsonPath("$.id").value(usuarioDto.getId()))
                        .andExpect(jsonPath("$.nome").value(usuarioDto.getNome()))
                        .andExpect(jsonPath("$.email").value(usuarioDto.getEmail()));
            }
        }

        @Nested
        class Quando_buscar_usuario_por_id {

            @BeforeEach
            void setUp() throws Exception {
                when(usuarioServiceMock.buscarPorId(anyLong())).thenReturn(usuarioDto);
                resposta = mockMvc.perform(get("/usuarios/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON));
            }

            @Test
            void Entao_deve_retornar_status_ok_e_usuario_encontrado() throws Exception {
                resposta.andExpect(status().isOk())
                        .andExpect(jsonPath("$.id").value(usuarioDto.getId()))
                        .andExpect(jsonPath("$.nome").value(usuarioDto.getNome()))
                        .andExpect(jsonPath("$.email").value(usuarioDto.getEmail()));
            }
        }

        @Nested
        class Quando_listar_usuarios {

            @BeforeEach
            void setUp() throws Exception {
                List<UsuarioDto> usuarios = Collections.singletonList(usuarioDto);
                when(usuarioServiceMock.listar()).thenReturn(usuarios);

                resposta = mockMvc.perform(get("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON));
            }

            @Test
            void Entao_deve_retornar_status_ok_e_lista_de_usuarios() throws Exception {
                resposta.andExpect(status().isOk())
                        .andExpect(jsonPath("$[0].id").value(usuarioDto.getId()))
                        .andExpect(jsonPath("$[0].nome").value(usuarioDto.getNome()))
                        .andExpect(jsonPath("$[0].email").value(usuarioDto.getEmail()));
            }
        }

        @Nested
        class Quando_excluir_usuario {

            @BeforeEach
            void setUp() throws Exception {
                doNothing().when(usuarioServiceMock).excluir(anyLong());

                resposta = mockMvc.perform(delete("/usuarios/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON));
            }

            @Test
            void Entao_deve_retornar_status_no_content() throws Exception {
                resposta.andExpect(status().isNoContent());
            }
        }
    }

    @Nested
    class Dado_um_usuario_invalido {

        private UsuarioDto usuarioDto;
        private ResultActions resposta;

        @BeforeEach
        void setUp() {
            usuarioDto = UsuarioDto.builder()
                    .id(1L)
                    .nome("J")
                    .email("joaoemail.com")
                    .build();
        }

        @Nested
        class Quando_criar_usuario {


            @BeforeEach
            void setUp() throws Exception {
                when(usuarioServiceMock.salvar(any(UsuarioDto.class))).thenReturn(usuarioDto);
                resposta = mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuarioDto)));
            }

            @Test
            void Entao_deve_retornar_status_400() throws Exception {
                resposta.andExpect(status().is4xxClientError());
            }
        }
    }
}
