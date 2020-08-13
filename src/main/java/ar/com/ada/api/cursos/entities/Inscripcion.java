package ar.com.ada.api.cursos.entities;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "inscripcion")
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inscripcion_id")
    private Integer inscripcionId;
    @ManyToOne
    @JoinColumn(name = "curso_id", referencedColumnName = "curso_id")
    private Curso curso;
    private Date fecha;
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id")
    private Usuario usuario;
    @Column(name = "estado_inscripcion_id")
    private Integer estadoInscripcionEnum;

    public enum EstadoInscripcionEnum {

        INACTIVA(0), ACTIVA(1);

        private final Integer value;
     

        // NOTE: Enum constructor tiene que estar en privado
        private EstadoInscripcionEnum(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public static EstadoInscripcionEnum parse(Integer id) {
            EstadoInscripcionEnum status = null; // Default
            for (EstadoInscripcionEnum itemEstado : EstadoInscripcionEnum.values()) {
                if (itemEstado.getValue().equals(id)) {
                    status = itemEstado;
                    break;
                }
            }

            return status;
        }
    }

    public Integer getInscripcionId() {
        return inscripcionId;
    }

    public void setInscripcionId(Integer inscripcionId) {
        this.inscripcionId = inscripcionId;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
        this.curso.getInscripciones().add(this);
        this.setEstadoInscripcionEnum(EstadoInscripcionEnum.ACTIVA);
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        this.usuario.getInscripciones().add(this);
    }

    public EstadoInscripcionEnum getEstadoInscripcionEnum() {
        return EstadoInscripcionEnum.parse(this.estadoInscripcionEnum);
    }

    public void setEstadoInscripcionEnum(EstadoInscripcionEnum estadoInscripcionEnum) {
        this.estadoInscripcionEnum = estadoInscripcionEnum.getValue();
    }
}