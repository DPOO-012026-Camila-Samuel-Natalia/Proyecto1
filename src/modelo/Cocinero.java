package modelo;

// esta clase representa a un cocinero.
public class Cocinero extends Empleado
{
	// especialidad del cocinero
	private String especialidad;

	// Constructor
	
	public Cocinero(String id, String nombre, String login, String password, double descuentoPropio,
			double descuentoCompartido, String especialidad) {
		super(id, nombre, login, password, descuentoPropio, descuentoCompartido);
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

	
}