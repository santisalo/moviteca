package com.pnt.moviteca2.service;

import com.pnt.moviteca2.domain.Actor;
import com.pnt.moviteca2.domain.Pelicula;
import com.pnt.moviteca2.repository.ActorRepository;
import com.pnt.moviteca2.repository.PeliculaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PeliculaService {
    private final PeliculaRepository peliculaRepository;
    private final ActorRepository actorRepository;

    public PeliculaService(PeliculaRepository peliculaRepository, ActorRepository actorRepository) {
        this.peliculaRepository = peliculaRepository;
        this.actorRepository = actorRepository;
    }
    public List<Pelicula> traerTodas() { return peliculaRepository.findAll(); };

    public List<Pelicula> traerTodasOrdenadasPorFechaAsc() {
        return peliculaRepository.findAllByOrderByFechaEstrenoAsc();
    }

    public List<Pelicula> traerTodasOrdenadasPorFechaDesc() {
        return peliculaRepository.findAllByOrderByFechaEstrenoDesc();
    }

    public List<Pelicula> traerTodasFiltradasPorTitulo(String query) {
        return peliculaRepository.findByTituloLike("%"+query+"%");
    }

    public Pelicula crear(Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }

    public boolean eliminarPorId(Long id) {
        try {
            Pelicula pelicula = peliculaRepository.findById(id).get();
            pelicula.quitarActores();
            peliculaRepository.delete(pelicula);
            System.out.println("ID ELIMINADO");
            System.out.println(id);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }


    public Pelicula traerPorId(Long id) {
        return peliculaRepository.findById(id).get();
    }

    public Boolean existePeliculaPorId(Long id) {
        return peliculaRepository.existsById(id);
    }

    public Boolean editar(Pelicula pelicula) {
        peliculaRepository.save(pelicula);
        return true;
    }

    public Boolean agregarActor(Pelicula pelicula, Actor actor) {
        Pelicula peliculaConActorNuevo = pelicula;
        peliculaConActorNuevo.agregarActor(actor);
        return true;
    }

    public Boolean agregarActorPorId(long actorId, long peliculaId) {
        Pelicula peliculaConActorNuevo = peliculaRepository.findById(peliculaId).get();
        Actor actor = actorRepository.findById(actorId).get();
        peliculaConActorNuevo.agregarActor(actor);
        return true;
    }

    public Boolean quitarActorPorId(long actorId, long peliculaId) {
        Pelicula peliculaSinActor = peliculaRepository.findById(peliculaId).get();
        Actor actor = actorRepository.findById(actorId).get();
        peliculaSinActor.quitarActor(actor);
        return true;
    }
}
