package com.pnt.moviteca2.service;

import com.pnt.moviteca2.domain.Actor;
import com.pnt.moviteca2.domain.Pelicula;
import com.pnt.moviteca2.repository.ActorRepository;
import com.pnt.moviteca2.repository.PeliculaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ActorService {
    private final ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public List<Actor> traerActores() {
        return actorRepository.findAll();
    }

    public Actor traerActorPorId(long id) {
        return actorRepository.findById(id).get();
    }

    public Actor crearActor(Actor actor) {
        Actor actorNuevo = actorRepository.save(actor);
        return actorNuevo;
    }

    public List<Actor> traerActoresDePelicula(Pelicula pelicula) {

        return null;
    }
}
