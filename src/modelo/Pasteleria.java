package modelo;

// Esta clase representa un producto de pastelería.
// Hereda de ProductoMenu.
public class Pasteleria extends ProductoMenu
{
	// Tipo del producto, por ejemplo: torta, galleta, brownie
	private String tipo;

	// Constructor
	public Pasteleria(String nombre, double precio, String tipo)
	{
		super(nombre, precio);
		this.tipo = tipo;
	}

	// Devuelve el tipo
	public String getTipo()
	{
		return tipo;
	}

	// Cambia el tipo
	public void setTipo(String tipo)
	{
		this.tipo = tipo;
	}

	@Override
	public String toString()
	{
		return "Pasteleria [nombre=" + getNombre() + ", precio=" + getPrecio() + ", tipo=" + tipo + "]";
	}
}