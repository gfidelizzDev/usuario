package com.gfidelizzdev.usuario.infraestructure.entityy;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "endereco")
@Builder
public class Endereco {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    //    correção
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column (name = "rua")
    private String rua ;
    @Column (name = "numero")
    private Long numero;
    @Column ( name = "complemento", length = 10)
    private String complemento;
    @Column (name = "cidade", length = 100)
    private String cidade;
    @Column (name = "estado", length = 2)
    private String estado;
    @Column (name = "cep" , length = 10)
    private String cep;
}
