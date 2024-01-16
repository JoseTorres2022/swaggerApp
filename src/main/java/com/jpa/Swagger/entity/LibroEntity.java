package com.jpa.Swagger.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "libro")
public class LibroEntity {
    @Id//indicando q es el primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private Integer estado;
    //NOTA: PRIMERO HAZ TU EN BD PARA APLICAR ESTO
    @ManyToOne //tenemos una relacion con editor de uno a uno guiandonos de nuestro diagrama que hemos echo en nuestra BD
    @JoinColumn(name = "editor_id")//indicando con q columna de la bd va a hacer el match o relacion
    private EditorEntity editor;
    @ManyToMany
    @JoinTable(
            name = "libro_autor",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private Set<AutorEntity> autores = new HashSet<>();

    @ManyToMany//si la tabla intermedia solo tiene dos campos y no mas usa esta forma.Sino crear la entidad LibroCategoria agregandoo los campos extra que haya
    @JoinTable(//es jointable ya q la relacion de muchos a muchos,se relaciona por una tercera tabla
        name = "libro_categoria",//y esa tercerca tabla es libro_categoria
        joinColumns = @JoinColumn(name = "libro_id"),//colocamos el primer id
        inverseJoinColumns = @JoinColumn(name = "categoria_id")//colocamos el otro id de la otra tabla
    )//anotaciones de este atributo
    private Set<CategoriaEntity> categorias = new HashSet<>();//agrego id de categorias


}
