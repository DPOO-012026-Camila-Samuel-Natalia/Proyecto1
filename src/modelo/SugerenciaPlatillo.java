package modelo;

// Esta clase representa una sugerencia de nuevo platillo hecha por un empleado.
public class SugerenciaPlatillo
{
	// Código único de la sugerencia
	private String codigo;
	
	// Empleado que propone el platillo
	private Empleado empleado;
	
	// Nombre del platillo sugerido
	private String nombrePlatillo;
	
	// Descripción breve del platillo
	private String descripcion;
	
	// Estado de la sugerencia: Pendiente, Aprobada o Rechazada
	private String estado;
	

	// Constructor
	public SugerenciaPlatillo(String codigo, Empleado empleado, String nombrePlatillo, String descripcion)
	{
		this.codigo = codigo;
		this.empleado = empleado;
		this.nombrePlatillo = nombrePlatillo;
		this.descripcion = descripcion;
		
		// Toda sugerencia empieza pendiente
		this.estado = "Pendiente";
	}

	// Devuelve el código
	public String getCodigo()
	{
		return codigo;
	}

	// Devuelve el empleado
	public Empleado getEmpleado()
	{
		return empleado;
	}

	// Devuelve el nombre del platillo
	public String getNombrePlatillo()
	{
		return nombrePlatillo;
	}

	// Devuelve la descripción
	public String getDescripcion()
	{
		return descripcion;
	}

	// Devuelve el estado
	public String getEstado()
	{
		return estado;
	}

	// Aprueba la sugerencia
	public void aprobar()
	{
		estado = "Aprobada";
	}

	// Rechaza la sugerencia
	public void rechazar()
	{
		estado = "Rechazada";
	}

	@Override
	public String toString()
	{
		return "SugerenciaPlatillo [codigo=" + codigo
				+ ", empleado=" + empleado.getNombre()
				+ ", nombrePlatillo=" + nombrePlatillo
				+ ", estado=" + estado + "]";
	}

	public ProductoMenu crearProducto(String tipo, double precio) {
	    if (tipo.equals("BEBIDA")) {
	        return new Bebida(nombrePlatillo, precio, false, false);
	    } else {
	        return new Pasteleria(nombrePlatillo, precio);
	    }
	}


}