package modelo;

// Esta clase representa una venta general.
// Sirve como base para VentaCafe.
public abstract class Venta
{
	// Código de la venta
	private String codigo;
	
	// Cliente que hizo la compra
	private Cliente cliente;

	// Constructor
	public Venta(String codigo, Cliente cliente)
	{
		this.codigo = codigo;
		this.cliente = cliente;
	}

	// Devuelve el código
	public String getCodigo()
	{
		return codigo;
	}

	// Devuelve el cliente
	public Cliente getCliente()
	{
		return cliente;
	}

	// Cada tipo de venta calcula su total de manera distinta
	public abstract double calcularTotal();
}