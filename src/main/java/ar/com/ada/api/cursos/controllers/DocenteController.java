package ar.com.ada.api.cursos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.cursos.entities.Curso;
import ar.com.ada.api.cursos.entities.Docente;
import ar.com.ada.api.cursos.entities.Inscripcion;
import ar.com.ada.api.cursos.models.request.DocenteRequest;
import ar.com.ada.api.cursos.models.request.InscripcionRequest;
import ar.com.ada.api.cursos.models.response.GenericResponse;
import ar.com.ada.api.cursos.services.CursoService;
import ar.com.ada.api.cursos.services.DocenteService;

@RestController
public class DocenteController {

    @Autowired
    DocenteService docenteService;

    @Autowired
    CursoService cursoService;

    @PostMapping("/api/docentes")
    @PreAuthorize("hasAuthority('CLAIM_userType_STAFF')")
    public ResponseEntity<GenericResponse> crearDocente(@RequestBody DocenteRequest docenteReq) {

        Docente docenteCreado = docenteService.crearDocente(docenteReq.nombre, docenteReq.paisId, docenteReq.tipoDocumentoId, docenteReq.documento, docenteReq.fechaNacimiento);

        if (docenteCreado == null) {

            return ResponseEntity.badRequest().build();

        } else {

            GenericResponse gR = new GenericResponse();
            gR.isOk = true;
            gR.message = "Docente creado/a con exito";
            gR.id = docenteCreado.getDocenteId();
            return ResponseEntity.ok(gR);

        }

    }

    @PostMapping("/api/docentes/{id}/cursos")
    @PreAuthorize("hasAuthority('CLAIM_userType_STAFF') or hasAuthority('CLAIM_userType_DOCENTE') and hasAuthority('CLAIM_entityId_'+#id)")
    public ResponseEntity<GenericResponse> asignar(@PathVariable Integer id, @RequestBody InscripcionRequest iR) {

        boolean asignado = cursoService.asignarDocente(id, iR.cursoId);
        GenericResponse gR = new GenericResponse();
        if (asignado == false) {
            gR.isOk = false;
            gR.message = "La asignación no pudo ser realizada";
            return ResponseEntity.badRequest().body(gR);
        } else {
            gR.isOk = true;
            gR.message = "La asignación se realizó con éxito";
            Docente docenteAsignado = docenteService.buscarPorId(id);
            gR.id = docenteAsignado.getDocenteId();
            return ResponseEntity.ok(gR);
        }

    }

    @GetMapping("/api/docentes")
    @PreAuthorize("hasAuthority('CLAIM_userType_STAFF')")
    public ResponseEntity<List<Docente>> getDocentes(@RequestParam(value = "nombre", required = false) String nombre) {
        List<Docente> d;

        if (nombre == null) {
            d = docenteService.buscarTodosOrdenadosPorNombre();
        } else {
            d = docenteService.buscarTodosPorNombre(nombre);
        }

        return ResponseEntity.ok(d);

    }

    
}