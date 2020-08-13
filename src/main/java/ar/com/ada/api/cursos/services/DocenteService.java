package ar.com.ada.api.cursos.services;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
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
        if (docenteRepo.existsDocente(docente.getPaisId().getValue(), docente.getTipoDocumentoId().getValue(), docente.getDocumento())) {
            return false;
        } else {
            docenteRepo.save(docente);
            return true;
        }
    }

    public Docente crearDocente(String nombre, PaisEnum paisId, TipoDocuEnum tipoDocumentoId, String documento,
            Date fechaNacimiento) {
        
        Docente docente = new Docente();
        docente.setNombre(nombre);
        docente.setPaisId(paisId.getValue());
        docente.setTipoDocumentoId(tipoDocumentoId);
        docente.setDocumento(documento);
        docente.setFechaNacimiento(fechaNacimiento);

        // llamo al metodo creado en la linea 25
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

    public Docente buscarPorId(Integer id) {
        Optional<Docente> opDocente = docenteRepo.findById(id);

        if (opDocente.isPresent())
            return opDocente.get();
        else
            return null;

    }

    public boolean docenteExiste(Docente docente) {

        if (docenteRepo.existsDocente(docente.getPaisId().getValue(), docente.getTipoDocumentoId().getValue(),
                docente.getDocumento()))
            return true;
        else
            return false;

    }



}