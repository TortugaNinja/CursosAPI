package ar.com.ada.api.cursos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.cursos.entities.*;
import ar.com.ada.api.cursos.models.request.CategoriaRequest;
import ar.com.ada.api.cursos.models.response.GenericResponse;
import ar.com.ada.api.cursos.services.*;

@RestController
public class CategoriaController {

    // Declarar un service

    @Autowired
    CategoriaService categoriaService;

    // Post: que recibios algo, que nos permite instanciar una Categoria y ponerle
    // datos.
    @PostMapping("/api/categorias")
    public ResponseEntity<GenericResponse> crearCategoria(@RequestBody CategoriaRequest categoriaR) {

        Categoria categoria = categoriaService.crearCategoria(categoriaR.nombre, categoriaR.descripcion);

        GenericResponse r = new GenericResponse();
        r.isOk = true;
        r.message = "Categoria Creada con exito";
        r.id = categoria.getCategoriaId();

        // Aca vamos a usar Ok
        // Cuando se crea, generalmetnte para los puristas, se usa el
        // CreatedAtAction(201)
        return ResponseEntity.ok(r);

    }

    @GetMapping("/api/categorias")
    public List<Categoria> getCategorias(@RequestParam(value = "nombre", required = false) String nombre) {
        List<Categoria> c;

        if (nombre == null) {
            c = categoriaService.buscarTodasOrdenadasPorNombre();
        } else {
            c = categoriaService.buscarTodasPorNombre(nombre);
        }

        return c;

    }
}