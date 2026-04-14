package modelo;

// Esta clase es abstracta porque no tiene sentido crear
// un Usuario genérico. Solo sirve como base para otras clases.
public abstract class Usuario
{
	// Identificador del usuario
	private String id;
	
	// Nombre del usuario
	private String nombre;
	
	// Login para iniciar sesión
	private String login;
	
	// Contraseña
	private String password;

	// Constructor
	public Usuario(String id, String nombre, String login, String password)
	{
		this.id = id;
		this.nombre = nombre;
		this.login = login;
		this.password = password;
	}

	// Devuelve el id
	public String getId()
	{
		return id;
	}

	// Devuelve el nombre
	public String getNombre()
	{
		return nombre;
	}

	// Devuelve el login
	public String getLogin()
	{
		return login;
	}

	// Devuelve la contraseña
	public String getPassword()
	{
		return password;
	}

	// Cambia el nombre
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	// Cambia el login
	public void setLogin(String login)
	{
		this.login = login;
	}

	// Cambia la contraseña
	public void setPassword(String password)
	{
		this.password = password;
	}
}