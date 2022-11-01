package com.pnt.moviteca2.repository;

import com.pnt.moviteca2.domain.Actor;
import com.pnt.moviteca2.domain.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActorRepository  extends JpaRepository<Actor, Long> {
}
