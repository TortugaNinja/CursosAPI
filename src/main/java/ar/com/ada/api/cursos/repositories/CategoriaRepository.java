package ar.com.ada.api.cursos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.com.ada.api.cursos.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    @Query("select c from Categoria c order by nombre")
	List<Categoria> findAllOrderByNombre();


	List<Categoria> findByNombre(String nombre);

}