package modelo;

// esta clase es abstracta porque no tiene sentido crear
// un empleado generico. Solo sirve como base para Mesero y Cocinero.
public abstract class Empleado extends Usuario
{
	// turno del empleado
	private String turno;
	
	// indica si esta trabajando
	private boolean enTurno;
	
	// indica si esta atendiendo una tarea
	private boolean atendiendo;

	// Constructor
	public Empleado(String id, String nombre, String login, String password, String turno)
	{
		super(id, nombre, login, password);
		this.turno = turno;
		this.enTurno = false;
		this.atendiendo = false;
	}

	// devuelve el turno
	public String getTurno()
	{
		return turno;
	}

	// devuelve si esta en turno
	public boolean isEnTurno()
	{
		return enTurno;
	}

	// devuelve si esta atendiendo
	public boolean isAtendiendo()
	{
		return atendiendo;
	}

	// cambia el turno
	public void setTurno(String turno)
	{
		this.turno = turno;
	}

	// marca que empieza turno
	public void iniciarTurno()
	{
		enTurno = true;
	}

	// marca que termina turno
	public void terminarTurno()
	{
		enTurno = false;
		atendiendo = false;
	}

	// marca que empieza a atender
	public void empezarAtencion()
	{
		atendiendo = true;
	}

	// marca que termina de atender
	public void terminarAtencion()
	{
		atendiendo = false;
	}

	@Override
	public String toString()
	{
		return "Empleado [id=" + getId() + ", nombre=" + getNombre() + ", turno=" + turno + "]";
	}
}