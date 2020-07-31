package ar.com.ada.api.cursos.models.request;

import java.util.Date;

import ar.com.ada.api.cursos.entities.Pais.PaisEnum;
import ar.com.ada.api.cursos.entities.Pais.TipoDocuEnum;

public class DocenteRequest {

    public String nombre;

    public PaisEnum paisId;
    
    public TipoDocuEnum tipoDocumentoId;

    public String documento;

    public Date fechaNacimiento;

    
}