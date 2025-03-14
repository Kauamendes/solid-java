package br.com.kauamendes.solidjava.srp.repository;

import br.com.kauamendes.solidjava.common.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
