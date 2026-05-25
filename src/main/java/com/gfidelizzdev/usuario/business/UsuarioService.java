package com.gfidelizzdev.usuario.business;

import com.gfidelizzdev.usuario.business.converter.UsuarioConverter;
import com.gfidelizzdev.usuario.business.dto.UsuarioDTO;
import com.gfidelizzdev.usuario.infraestructure.entityy.Usuario;
import com.gfidelizzdev.usuario.infraestructure.exceptions.ConflictException;
import com.gfidelizzdev.usuario.infraestructure.exceptions.ResourceNotFoundException;
import com.gfidelizzdev.usuario.infraestructure.repository.UsuarioRepository;
import com.gfidelizzdev.usuario.infraestructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO) {
        emailExiste(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(
                usuarioRepository.save(usuario)
        );
    }

    public void emailExiste(String email) {
        try {
            boolean existe = verificaEmailExistente(email);
            if (existe) {
                throw new ConflictException("Email já cadastrado" + email);
            }
        } catch (ConflictException e) {
            throw new ConflictException("Email já cadastrado", e.getCause());
        }
    }

    public boolean verificaEmailExistente(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public UsuarioDTO buscarUsuarioPorEmail(String email) {
        return usuarioConverter.paraUsuarioDTO(
                usuarioRepository.findByEmail(email).orElseThrow(
                        () -> new ResourceNotFoundException("Email não encontrado" + email)));
    }

    public void deletaUsuarioPorEmail(String email) {
        usuarioRepository.deleteByEmail(email);
    }

    public UsuarioDTO atualizaDadosUsuario(String token, UsuarioDTO dto) {
//     Aqui busca o email do usuario pelo JWT token (tira a obrigatoriedade de passar o email)
        String email = jwtUtil.extractUsername(token.substring(7));

//        Criptogradia de senha
        dto.setSenha(dto.getSenha() != null ? passwordEncoder.encode(dto.getSenha()) : null);

//        Busca os dados do usuario no banco de dados
        Usuario usuarioEntity = usuarioRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("Email não localizado"));

        //Mesclou os dados que recebemos na requisição DTO com os dados do banco de dados
        Usuario usuario = usuarioConverter.updateUsuario(dto, usuarioEntity);

        //salvou os dados do usuario convertido e pegou o retorno e converteu pra DTO

        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }
}
