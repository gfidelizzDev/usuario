package com.gfidelizzdev.usuario.infraestructure.repository;

import com.gfidelizzdev.aprendospring.infraestructure.entityy.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelefoneRepository extends JpaRepository<Telefone,Long> {
}
