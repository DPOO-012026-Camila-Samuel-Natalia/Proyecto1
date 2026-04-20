package modelo;

// Esta clase representa un producto general del menú.
// Sirve como base para Bebida y Pasteleria.
public abstract class ProductoMenu
{
	// Nombre del producto
	private String nombre;
	
	// Precio base del producto
	private double precio;

	// Constructor
	public ProductoMenu(String nombre, double precio)
	{
		this.nombre = nombre;
		this.precio = precio;
	}

	// Devuelve el nombre
	public String getNombre()
	{
		return nombre;
	}

	// Devuelve el precio
	public double getPrecio()
	{
		return precio;
	}

	// Cambia el nombre
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	// Cambia el precio
	public void setPrecio(double precio)
	{
		this.precio = precio;
	}

	@Override
	public String toString()
	{
		return "ProductoMenu [nombre=" + nombre + ", precio=" + precio + "]";
	}
}