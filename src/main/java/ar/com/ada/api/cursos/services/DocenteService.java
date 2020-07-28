package ar.com.ada.api.cursos.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.cursos.entities.Docente;
import ar.com.ada.api.cursos.entities.Pais.PaisEnum;
import ar.com.ada.api.cursos.entities.Pais.TipoDocuEnum;
import ar.com.ada.api.cursos.repositories.DocenteRepository;

@Service
public class DocenteService {

    @Autowired
    DocenteRepository docenteRepo;
    public boolean crearDocente(Docente docente) {
        if (docenteRepo.existsDocente(docente.getPaisId(), docente.getTipoDocumentoId(), docente.getDocumento()))
            return false;
        docenteRepo.save(docente);
        return true;
    }

    public Docente crearDocente(String nombre, PaisEnum paisEnum, TipoDocuEnum TipoDocuEnum, String documento,
            Date fechaNacimiento) {
        Docente docente = new Docente();
        docente.setNombre(nombre);
        docente.setPaisId(paisEnum);
        docente.setTipoDocumentoId(TipoDocuEnum);
        docente.setDocumento(documento);
        docente.setFechaNacimiento(fechaNacimiento);
        // llamo al metodo creado en la linea 19
        boolean docenteCreado = crearDocente(docente);
        if (docenteCreado)
            return docente;
        else
            return null;

        // if (DocenteRepository.existsByNombre(Docente.getNombre()))
        // return null;
        // DocenteRepository.save(Docente);
        // return Docente;

    }

    public List<Docente> obtenerListaDocentes() {
        return docenteRepo.findAll();
    }
}