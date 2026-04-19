package modelo;

// Esta clase representa una solicitud de cambio de turno.
// La hace un empleado y queda pendiente hasta que el administrador la apruebe o rechace.
public class SolicitudCambioTurno {
    private String codigo;
    private Empleado empleadoSolicitante;
    private Turno turnoSolicitado;
    private String estado;
    private String motivo;
    private String tipoCambio;            // "GENERAL" o "INTERCAMBIO"
    private Empleado empleadoIntercambio; // null si es cambio general
    private Turno turnoQueOfrece; // turno que quiere cambiar o intercambiar

    // Constructor cambio general
    public SolicitudCambioTurno(String codigo, Empleado empleadoSolicitante,Turno turnoQueOfrece,Turno turnoQueDesea,String motivo) {
		this.codigo = codigo;
		this.empleadoSolicitante = empleadoSolicitante;
		this.turnoQueOfrece = turnoQueOfrece;
		this.turnoSolicitado = turnoQueDesea;
		this.motivo = motivo;
		this.tipoCambio = "GENERAL";
		this.empleadoIntercambio = null;
		this.estado = "Pendiente";
		}

    // Constructor intercambio
    public SolicitudCambioTurno(String codigo, Empleado empleadoSolicitante,Empleado empleadoIntercambio,Turno turnoQueOfrece,Turno turnoQueDesea, String motivo) {
		this.codigo = codigo;
		this.empleadoSolicitante = empleadoSolicitante;
		this.empleadoIntercambio = empleadoIntercambio;
		this.turnoQueOfrece = turnoQueOfrece;
		this.turnoSolicitado = turnoQueDesea;
		this.motivo = motivo;
		this.tipoCambio = "INTERCAMBIO";
		this.estado = "Pendiente";
	}

    public void aprobar() {
        estado = "Aprobada";
        if (tipoCambio.equals("INTERCAMBIO")) {
            // Intercambiar turnos
            empleadoSolicitante.quitarTurno(turnoQueOfrece);
            empleadoSolicitante.agregarTurno(turnoSolicitado);

            empleadoIntercambio.quitarTurno(turnoSolicitado);
            empleadoIntercambio.agregarTurno(turnoQueOfrece);

            // Actualizar en los turnos también
            turnoQueOfrece.quitarEmpleado(empleadoSolicitante);
            turnoQueOfrece.agregarEmpleado(empleadoIntercambio);

            turnoSolicitado.quitarEmpleado(empleadoIntercambio);
            turnoSolicitado.agregarEmpleado(empleadoSolicitante);
        } else {
            // Cambio general
        	empleadoSolicitante.quitarTurno(turnoQueOfrece);
        	turnoQueOfrece.quitarEmpleado(empleadoSolicitante);

        	empleadoSolicitante.agregarTurno(turnoSolicitado);
        	turnoSolicitado.agregarEmpleado(empleadoSolicitante);
        }
    }


    public void rechazar() { estado = "Rechazada"; }

    
    
    @Override
    public String toString() {
        return "SolicitudCambioTurno [codigo=" + codigo
            + ", empleado=" + empleadoSolicitante.getNombre()
            + ", tipo=" + tipoCambio
            + ", turnoSolicitado=" + turnoSolicitado
            + ", estado=" + estado
            + ", motivo=" + motivo + "]";
    }

    
 // Getters
	public String getCodigo() {
		return codigo;
	}

	public Empleado getEmpleadoSolicitante() {
		return empleadoSolicitante;
	}

	public Turno getTurnoSolicitado() {
		return turnoSolicitado;
	}

	public String getEstado() {
		return estado;
	}

	public String getMotivo() {
		return motivo;
	}

	public String getTipoCambio() {
		return tipoCambio;
	}

	public Empleado getEmpleadoIntercambio() {
		return empleadoIntercambio;
	}

	public Turno getTurnoQueOfrece() {
		return turnoQueOfrece;
	}

	
	
	
}