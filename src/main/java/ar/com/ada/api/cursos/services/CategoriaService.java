package ar.com.ada.api.cursos.services;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import ar.com.ada.api.cursos.entities.*;
import ar.com.ada.api.cursos.models.request.CategoriaRequest;
import ar.com.ada.api.cursos.repositories.CategoriaRepository;
import java.util.*;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository repoCategoria;

    public Categoria crearCategoria(Categoria categoria) {
        return repoCategoria.save(categoria);
    }

    public Categoria crearCategoria(String nombre, String descripcion) {
        Categoria categoria = new Categoria();
        categoria.setNombre(nombre);
        categoria.setDescripcion(descripcion);
        repoCategoria.save(categoria);
        return categoria;
    }

    public Categoria buscarPorId(Integer id) {
        Optional<Categoria> opCategoria = repoCategoria.findById(id);

        // Si tiene un valor de categoria en el elemento que trajo.
        // Camion con heladera dentro. hasta que no abrimos la puerta no sabemos si la
        // trajo.
        if (opCategoria.isPresent())
            return opCategoria.get();
        else
            return null;

    }

	public List<Categoria> buscarTodasOrdenadasPorNombre() {
		return repoCategoria.findAllOrderByNombre();
	}

	public List<Categoria> buscarTodasPorNombre(String nombre) {
		return repoCategoria.findByNombre(nombre);
	}
}