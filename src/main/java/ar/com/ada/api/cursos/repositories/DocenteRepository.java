package ar.com.ada.api.cursos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import ar.com.ada.api.cursos.entities.Docente;
import ar.com.ada.api.cursos.entities.Pais.PaisEnum;
import ar.com.ada.api.cursos.entities.Pais.TipoDocuEnum;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Integer> {
    
    // jpql query, se está referenciando al obj docente, conviene para prevenir sql
    // injection
    // todo lo que tiene =: ANTES es un PARAMETRO. Por ejemplo d.paisId=:paisId --> DONDE =:paisId ES PARAMETRO
    @Query("select CASE WHEN count(d) > 0 THEN true ELSE false END from Docente d where d.paisId=:paisId and d.tipoDocumentoId=:tipoDocumentoId and d.documento=:documento")
    boolean existsDocente(Integer paisId, Integer tipoDocumentoId, String documento);

    @Query("select d from Docente d where d.paisId=:pais and d.tipoDocumentoId=:tipoDocuEnum and d.documento=:documento")
    Docente buscarDocentePorDocu(Integer pais, Integer tipoDocuEnum, String documento);

    @Query("select d from Docente d order by nombre")
    List<Docente> obtenerListaDocentes();
    
}