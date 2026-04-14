package modelo;

// esta clase representa a un cocinero.
public class Cocinero extends Empleado
{
	// especialidad del cocinero
	private String especialidad;

	// Constructor
	public Cocinero(String id, String nombre, String login, String password, String turno, String especialidad)
	{
		super(id, nombre, login, password, turno);
		this.especialidad = especialidad;
	}

	// devuelve la especialidad
	public String getEspecialidad()
	{
		return especialidad;
	}

	// cambia la especialidad
	public void setEspecialidad(String especialidad)
	{
		this.especialidad = especialidad;
	}

	@Override
	public String toString()
	{
		return "Cocinero [id=" + getId() + ", nombre=" + getNombre() + ", especialidad=" + especialidad + "]";
	}
}