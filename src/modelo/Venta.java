package modelo;

// Esta clase representa una venta general.
// Sirve como base para VentaCafe.
public abstract class Venta
{
	// Código de la venta
	private String codigo;
	
	// Cliente que hizo la compra
	private Usuario comprador;
	
	private boolean cerrada;

	// Constructor
	public Venta(String codigo, Usuario comprador)
	{
		this.codigo = codigo;
		this.comprador = comprador;
		this.cerrada = false;
	}

	// Devuelve el código
	public String getCodigo()
	{
		return codigo;
	}

	public boolean isCerrada() {
		return this.cerrada;
	}
	
	// Devuelve el cliente
	public Usuario getComprador()
	{
		return comprador;
	}
	public void cerrar() {
		this.cerrada = true;
	}

	// Cada tipo de venta calcula su total de manera distinta
	public abstract double calcularTotal();
}