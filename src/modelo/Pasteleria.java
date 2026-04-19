package modelo;

// Esta clase representa un producto de pastelería.
// Hereda de ProductoMenu.
public class Pasteleria extends ProductoMenu
{
	

	// Constructor
	public Pasteleria(String nombre, double precio)
	{
		super(nombre, precio);
		
	}

	
	
	@Override
	public String toString()
	{
		return "Pasteleria [nombre=" + getNombre() + ", precio=" + getPrecio() + "]";
	}
}