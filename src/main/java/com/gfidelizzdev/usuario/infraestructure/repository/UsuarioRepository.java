package com.gfidelizzdev.usuario.infraestructure.repository;

import com.gfidelizzdev.aprendospring.infraestructure.entityy.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    boolean existsByEmail(String email);

    Optional<Object> findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);
}
