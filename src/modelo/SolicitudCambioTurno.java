package modelo;

// Esta clase representa una solicitud de cambio de turno.
// La hace un empleado y queda pendiente hasta que el administrador la apruebe o rechace.
public class SolicitudCambioTurno
{
	// Código único de la solicitud
	private String codigo;
	
	// Empleado que hace la solicitud
	private Empleado empleadoSolicitante;
	
	// Nuevo turno que desea pedir
	private String turnoSolicitado;
	
	// Estado de la solicitud: Pendiente, Aprobada o Rechazada
	private String estado;
	
	// Motivo de la solicitud
	private String motivo;

	// Constructor
	public SolicitudCambioTurno(String codigo, Empleado empleadoSolicitante, String turnoSolicitado, String motivo)
	{
		this.codigo = codigo;
		this.empleadoSolicitante = empleadoSolicitante;
		this.turnoSolicitado = turnoSolicitado;
		this.motivo = motivo;
		
		// Toda solicitud empieza en estado pendiente
		this.estado = "Pendiente";
	}

	// Devuelve el código
	public String getCodigo()
	{
		return codigo;
	}

	// Devuelve el empleado que hizo la solicitud
	public Empleado getEmpleadoSolicitante()
	{
		return empleadoSolicitante;
	}

	// Devuelve el turno solicitado
	public String getTurnoSolicitado()
	{
		return turnoSolicitado;
	}

	// Devuelve el estado actual
	public String getEstado()
	{
		return estado;
	}

	// Devuelve el motivo
	public String getMotivo()
	{
		return motivo;
	}

	// Aprueba la solicitud
	// Además cambia el turno del empleado al nuevo turno solicitado
	public void aprobar()
	{
		estado = "Aprobada";
		empleadoSolicitante.setTurno(turnoSolicitado);
	}

	// Rechaza la solicitud
	public void rechazar()
	{
		estado = "Rechazada";
	}

	@Override
	public String toString()
	{
		return "SolicitudCambioTurno [codigo=" + codigo
				+ ", empleado=" + empleadoSolicitante.getNombre()
				+ ", turnoSolicitado=" + turnoSolicitado
				+ ", estado=" + estado
				+ ", motivo=" + motivo + "]";
	}
}