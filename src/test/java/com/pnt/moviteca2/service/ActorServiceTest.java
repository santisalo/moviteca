package com.pnt.moviteca2.service;

import com.pnt.moviteca2.Moviteca2ApplicationTests;
import com.pnt.moviteca2.domain.Actor;
import com.pnt.moviteca2.domain.Pelicula;
import com.pnt.moviteca2.repository.ActorRepository;
import com.pnt.moviteca2.repository.PeliculaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class ActorServiceTest extends Moviteca2ApplicationTests {
    @Autowired
    private ActorService actorService;

    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private PeliculaService peliculaService;

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Test
    public void listarActores_conDatosCompletos_retornaListaDeActores() {
        List<Actor> listaActores = actorService.traerActores();
        assertNotNull(listaActores);
        assertFalse(listaActores.isEmpty());
        assertEquals(listaActores.size(), 1);
    }

    @Test
    public void traerActor_conId_retornaActor() {
        Actor actor = actorService.traerActorPorId(1L);
        assertNotNull(actor);
    }

    @Test
    public void crearActor_conDatosValidos_creaActor(){
        Actor actor = new Actor();
        actor.setNombre("Oscar Isaac");
        actor.setFechaNacimiento(LocalDate.parse("1979-05-02"));
        actor.setFotoUrl("https://static.wikia.nocookie.net/esstarwars/images/6/6d/OscarIsaac.jpg/revision/latest?cb=20140528095859");
        actorService.crearActor(actor);
        assertEquals(actorService.traerActorPorId(5L).getNombre(),"Oscar Isaac");
    }

}
