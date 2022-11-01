package com.pnt.moviteca2.service;

import com.pnt.moviteca2.Moviteca2ApplicationTests;
import com.pnt.moviteca2.domain.Actor;
import com.pnt.moviteca2.domain.Pelicula;
import com.pnt.moviteca2.repository.ActorRepository;
import com.pnt.moviteca2.repository.PeliculaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class PeliculaServiceTest extends Moviteca2ApplicationTests {
    @Autowired
    private PeliculaService peliculaService;

    @Autowired
    private PeliculaRepository peliculaRepository;
    @Autowired
    private ActorService actorService;

    @Autowired
    private ActorRepository actorRepository;

    @Test
    public void listarPeliculas_conDatosCompletos_retornaListaDePeliculas(){
        List<Pelicula> listadoPeliculas = peliculaService.traerTodas();
        assertNotNull(listadoPeliculas);
        assertFalse(listadoPeliculas.isEmpty());
        assertEquals(listadoPeliculas.size(), 4);
    }

    @Test
    public void listarPeliculas_ordenadasPorFechaEstrenoAsc_retornaListaOrdenadaPorFechaAsc(){
        List<Pelicula> listadoPeliculas = peliculaService.traerTodasOrdenadasPorFechaAsc();
        assertNotNull(listadoPeliculas);
        assertFalse(listadoPeliculas.isEmpty());
        assertEquals(listadoPeliculas.get(0).getId().toString(), "1");
    }

    @Test
    public void listarPeliculas_ordenadasPorFechaEstrenoDesc_retornaListaOrdenadaPorFechaDesc(){
        List<Pelicula> listadoPeliculas = peliculaService.traerTodasOrdenadasPorFechaDesc();
        assertNotNull(listadoPeliculas);
        assertFalse(listadoPeliculas.isEmpty());
        assertEquals(listadoPeliculas.get(1).getId().toString(), "3");
    }

    @Test
    public void listarPeliculas_filtradasPorTitulo_retornaListaPeliculasFiltradas(){
        List<Pelicula> listadoPeliculas = peliculaService.traerTodasFiltradasPorTitulo("Ince");
        assertNotNull(listadoPeliculas);
        assertFalse(listadoPeliculas.isEmpty());
        assertEquals(listadoPeliculas.get(0).getId().toString(), "2");
    }

    @Test
    public void crearPelicula_conDatosCompletos_guardaPelicula(){
        Pelicula pelicula = new Pelicula();
        pelicula.setId(null);
        pelicula.setTitulo("Memento");
        pelicula.setSinopsis("Es un tipo re loco que se olvida de todo");
        pelicula.setPosterUrl("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/neHrQLVfT3KPqvv30pNPyRb6chQ.jpg");
        LocalDate localDate = LocalDate.parse("2000-07-02");
        pelicula.setFechaEstreno(localDate);
        Pelicula peliculaGuardada = peliculaService.crear(pelicula);

        assertNotNull(peliculaGuardada);
        assertNotNull(peliculaGuardada.getId());

    }

    @Test
    public void eliminarPelicula_conId_eliminaPelicula(){
        peliculaService.eliminarPorId(1L);
        assertFalse(peliculaService.existePeliculaPorId(1L));
    }

    @Test
    public void traerPelicula_conIdExistente_devuelvePelicula(){
        Pelicula pelicula = peliculaService.traerPorId(1L);
        assertNotNull(pelicula);
    }

    @Test
    public void editarPelicula_conDatosValidos_guardaCambios(){
        Pelicula pelicula = peliculaService.traerPorId(1L);
        pelicula.setTitulo("Insomnia");
        pelicula.setSinopsis("Un tipo que no puede dormir para resolver un crimen");
        pelicula.setFechaEstreno(LocalDate.now());
        pelicula.setPosterUrl("https://http2.mlstatic.com/D_NQ_NP_977357-MLA29218757803_012019-V.jpg");
        peliculaService.editar(pelicula);
    }

    @Test
    public void agregarActor_conEntidadActor_guardaEnListadoActoresDePelicula(){
        Pelicula pelicula = peliculaService.traerPorId(1L);
        Actor actor = actorService.traerActorPorId(1L);

        Boolean actoresOk = peliculaService.agregarActor(pelicula, actor);

        Pelicula peliculaConActorNuevo = peliculaService.traerPorId(1L);

        assertEquals(peliculaConActorNuevo.getListaActores().size(), 2);
    }

    @Test
    public void agregarActor_conIdActorIdPelicula_guardaEnListadoActoresDePelicula(){

        Boolean actoresOk = peliculaService.agregarActorPorId(1L, 1L);

        Pelicula peliculaConActorNuevo = peliculaService.traerPorId(1L);

        assertEquals(peliculaConActorNuevo.getListaActores().size(), 2);
    }

    @Test
    public void quitarActor_conIdActorIdPelicula_quitaDeListadoActoresDePelicula(){
        Boolean actoresOk = peliculaService.quitarActorPorId(1L, 1L);
        Pelicula peliculaConActorNuevo = peliculaService.traerPorId(1L);
        assertEquals(peliculaConActorNuevo.getListaActores().size(), 1);
    }
}
