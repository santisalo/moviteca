package com.pnt.moviteca2.repository;

import com.pnt.moviteca2.domain.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
    List<Pelicula> findAll();

    List<Pelicula> findAllByOrderByFechaEstrenoAsc();

    List<Pelicula> findAllByOrderByFechaEstrenoDesc();

    List<Pelicula> findByTituloLike(String query);
}
