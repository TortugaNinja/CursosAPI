package ar.com.ada.api.cursos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.cursos.entities.Docente;
import ar.com.ada.api.cursos.models.request.DocenteRequest;
import ar.com.ada.api.cursos.models.response.GenericResponse;
import ar.com.ada.api.cursos.services.DocenteService;

@RestController
public class DocenteController {

    @Autowired
    DocenteService docenteService;

    @PostMapping("/api/docentes")
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
    
}