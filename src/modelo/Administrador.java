package modelo;

// Esta clase representa al administrador.
public class Administrador extends Usuario
{
	// Constructor
	public Administrador(String id, String nombre, String login, String password)
	{
		super(id, nombre, login, password);
	}

	@Override
	public String toString()
	{
		return "Administrador [id=" + getId() + ", nombre=" + getNombre() + "]";
	}
}