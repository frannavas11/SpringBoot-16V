package com.example.demo.repositories;

import com.example.demo.entities.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends BaseRepository<Persona, Long> {
    //anotacion metodo query
    List<Persona> findByNombreContainingOrApellidoContaining(String nombre, String apellido);
    boolean existsByDni(int dni);

    Page<Persona> findByNombreContainingOrApellidoContaining(String nombre, String apellido,Pageable pageable);

    //anotacion jpql parametros indexados
    @Query(value = "SELECT p FROM Persona p WHERE p.nombre LIKE %:filtro% OR p.apellido LIKE %:filtro%")
    List<Persona> search(@Param("filtro") String filtro);

    @Query(value = "SELECT p FROM Persona p WHERE p.nombre LIKE %:filtro% OR p.apellido LIKE %:filtro%")
    Page<Persona> search(@Param("filtro") String filtro,Pageable pageable);

    @Query(value = "SELECT * FROM persona WHERE persona.nombre LIKE CONCAT('%', ?1, '%') OR persona.apellido LIKE CONCAT('%', ?1, '%')", nativeQuery = true)
    List<Persona> search1(String filtro);

    @Query(value = "SELECT * FROM persona WHERE persona.nombre LIKE CONCAT('%', ?1, '%') OR persona.apellido LIKE CONCAT('%', ?1, '%')",
            countQuery ="SELECT COUNT(*) FROM persona",
            nativeQuery = true)
    Page<Persona> search1(String filtro, Pageable pageable);
}
