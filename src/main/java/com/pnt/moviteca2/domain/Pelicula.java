package com.pnt.moviteca2.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column
    private String sinopsis;

    @Column
    private String posterUrl;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaEstreno;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "actor_pelicula",
            joinColumns = @JoinColumn(name="pelicula_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private List<Actor> listaActores;

    public void agregarActor(Actor actor){
        listaActores.add(actor);
        actor.getListaPeliculas().add(this);
    }
    public void quitarActor(Actor actor){
        listaActores.remove(actor);
        actor.getListaPeliculas().remove(this);
    }
    public void quitarActores(){
        for (Actor actor : listaActores) {
            actor.getListaPeliculas().remove(this);
        }
        listaActores = new ArrayList();
    }
}