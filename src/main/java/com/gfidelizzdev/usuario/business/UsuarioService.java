package com.gfidelizzdev.usuario.business;

import com.gfidelizzdev.usuario.business.converter.UsuarioConverter;
import com.gfidelizzdev.usuario.business.dto.UsuarioDTO;
import com.gfidelizzdev.usuario.infraestructure.entityy.Usuario;
import com.gfidelizzdev.usuario.infraestructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;


    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(
                usuarioRepository.save(usuario)
        );
    }

}
