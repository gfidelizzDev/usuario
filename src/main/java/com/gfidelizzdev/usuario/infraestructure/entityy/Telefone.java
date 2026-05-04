package com.gfidelizzdev.usuario.infraestructure.entityy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "telefone")
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    correção
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @Column (name = "numero", length = 10)
    private String numero;
    @Column(name = "ddd", length = 2)
    private String ddd;

}
