package com.pnt.moviteca2.web.controller;

import com.pnt.moviteca2.domain.Actor;
import com.pnt.moviteca2.domain.Pelicula;
import com.pnt.moviteca2.service.ActorService;
import com.pnt.moviteca2.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.util.ArrayList;

@Controller
@RequestMapping("/actores")
public class ActorController {

    @Autowired
    private ActorService actorService;
    @Autowired
    private PeliculaService peliculaService;

    @PostMapping("/crear")
    public RedirectView crear(Actor actor, RedirectAttributes flash) {
        RedirectView rv = new RedirectView("/peliculas/listado");
        actorService.crearActor(actor);
        flash.addFlashAttribute("success", "Actor creado correctamente!");
        return rv;
    }

    @PostMapping("/crearAgregarPelicula")
    public RedirectView crearAgregarPelicula(String nombre, String fechaNacimiento, String fotoUrl, String peliculaId, RedirectAttributes flash) {
        RedirectView rv = new RedirectView("/peliculas/listado");
        Actor actorCrear = new Actor();
        actorCrear.setNombre(nombre);
        actorCrear.setFechaNacimiento(LocalDate.parse(fechaNacimiento));
        actorCrear.setFotoUrl(fotoUrl);
        actorCrear.setListaPeliculas(new ArrayList<>());
        Actor actorNuevo = actorService.crearActor(actorCrear);
        peliculaService.agregarActorPorId(actorNuevo.getId(), Long.valueOf(peliculaId));
        flash.addFlashAttribute("success", "Actor creado y agregado correctamente!");
        return rv;
    }


    @PostMapping("/agregarPelicula")
    public RedirectView agregarPelicula(String peliculaId, String actorId, RedirectAttributes flash) {
        RedirectView rv = new RedirectView("/peliculas/listado");
        peliculaService.agregarActorPorId(Long.valueOf(actorId), Long.valueOf(peliculaId));
        flash.addFlashAttribute("success", "Actor agregado correctamente!");
        return rv;
    }

    @PostMapping("/quitarPelicula")
    public RedirectView quitarPelicula(String peliculaId, String actorId, RedirectAttributes flash) {
        RedirectView rv = new RedirectView("/peliculas/listado");
        peliculaService.quitarActorPorId(Long.valueOf(actorId), Long.valueOf(peliculaId));
        flash.addFlashAttribute("success", "Actor quitado correctamente!");
        return rv;
    }

}
