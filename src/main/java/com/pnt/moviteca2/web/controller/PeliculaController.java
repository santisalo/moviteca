package com.pnt.moviteca2.web.controller;

import com.pnt.moviteca2.domain.Actor;
import com.pnt.moviteca2.domain.Pelicula;
import com.pnt.moviteca2.service.ActorService;
import com.pnt.moviteca2.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/peliculas")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    @Autowired
    private ActorService actorService;

    @GetMapping("/listado")
    public String listar(Model model) {
        List<Pelicula> peliculas = peliculaService.traerTodas();
        List<Actor> actores = actorService.traerActores();
        model.addAttribute("actores", actores);
        model.addAttribute("peliculas", peliculas);
        model.addAttribute("nuevaPelicula", new Pelicula());
        model.addAttribute("nuevoActor", new Actor());
        model.addAttribute("titulo", "Listado de Películas");
        return "peliculas";
    }
    @GetMapping("/listado/ultimosEstrenos")
    public String listarOrderByFechaEstrenoDesc(Model model) {
        List<Pelicula> peliculas = peliculaService.traerTodasOrdenadasPorFechaDesc();
        List<Actor> actores = actorService.traerActores();
        model.addAttribute("actores", actores);
        model.addAttribute("peliculas", peliculas);
        model.addAttribute("nuevaPelicula", new Pelicula());
        model.addAttribute("nuevoActor", new Actor());
        model.addAttribute("titulo", "Últimos Estrenos");
        return "peliculas";
    }
    @GetMapping("/listado/masAntiguas")
    public String listarOrderByFechaEstrenoAsc(Model model) {
        List<Pelicula> peliculas = peliculaService.traerTodasOrdenadasPorFechaAsc();
        List<Actor> actores = actorService.traerActores();
        model.addAttribute("actores", actores);
        model.addAttribute("peliculas", peliculas);
        model.addAttribute("nuevaPelicula", new Pelicula());
        model.addAttribute("nuevoActor", new Actor());
        model.addAttribute("titulo", "Mas Antiguas");
        return "peliculas";
    }

    @GetMapping("/listado/busqueda")
    public ModelAndView listarOrderByFechaEstrenoAsc(@RequestParam(name = "q") String q, Model model, RedirectAttributes flash) {
        ModelAndView rv = new ModelAndView("peliculas");
        List<Pelicula> peliculas = peliculaService.traerTodasFiltradasPorTitulo(q);
        List<Actor> actores = actorService.traerActores();
        model.addAttribute("actores", actores);
        model.addAttribute("peliculas", peliculas);
        model.addAttribute("nuevaPelicula", new Pelicula());
        model.addAttribute("nuevoActor", new Actor());
        model.addAttribute("titulo", "Resultado de Búsqueda");
        if (peliculas.size() == 0) {
            model.addAttribute("danger", "¡No hay resultados!");
        }

        return rv;
    }

    @PostMapping("/crear")
    public RedirectView crear(Pelicula pelicula, RedirectAttributes flash) {
        RedirectView rv = new RedirectView("/peliculas/listado");
        peliculaService.crear(pelicula);
        flash.addFlashAttribute("success", "Pelicula creada correctamente!");
        return rv;
    }

    @PostMapping("/eliminar")
    public RedirectView eliminar(String id, RedirectAttributes flash) {
        RedirectView rv = new RedirectView("/peliculas/listado");
        Boolean peliculaEliminada = peliculaService.eliminarPorId(Long.valueOf(id));
        if (peliculaEliminada) {
            flash.addFlashAttribute("success", "Pelicula eliminada correctamente!");
        } else {
            flash.addFlashAttribute("danger", "Error al eliminar pelicula!");
        }
        return rv;
    }

    @PostMapping("/editar")
    public RedirectView editar(Pelicula pelicula, RedirectAttributes flash) {
        RedirectView rv = new RedirectView("/peliculas/listado");
        Pelicula peliculaActual = peliculaService.traerPorId(pelicula.getId());
        List<Actor> actores = peliculaActual.getListaActores();
        pelicula.setListaActores(actores);
        peliculaService.editar(pelicula);
        flash.addFlashAttribute("success", "Pelicula editada correctamente!");
        return rv;
    }
}
