package modelo;

// esta clase representa una línea de una venta de juegos.
// por ejemplo: 2 unidades de Catan.
public class DetalleVentaJuego
{
	// juego vendido
	private JuegoMesa juego;
	
	// cantidad vendida
	private int cantidad;
	
	// precio unitario del juego en la venta
	private double precioUnitario;

	// Constructor
	public DetalleVentaJuego(JuegoMesa juego, int cantidad, double precioUnitario)
	{
		this.juego = juego;
		this.cantidad = cantidad;
		this.precioUnitario = precioUnitario;
	}

	// devuelve el juego
	public JuegoMesa getJuego()
	{
		return juego;
	}

	// devuelve la cantidad
	public int getCantidad()
	{
		return cantidad;
	}

	// devuelve el precio unitario
	public double getPrecioUnitario()
	{
		return precioUnitario;
	}

	// cambia la cantidad
	public void setCantidad(int cantidad)
	{
		this.cantidad = cantidad;
	}

	// cambia el precio unitario
	public void setPrecioUnitario(double precioUnitario)
	{
		this.precioUnitario = precioUnitario;
	}

	// calcula el subtotal de esta línea
	public double calcularSubtotal()
	{
		return cantidad * precioUnitario;
	}

	@Override
	public String toString()
	{
		return "DetalleVentaJuego [juego=" + juego.getNombre()
				+ ", cantidad=" + cantidad
				+ ", precioUnitario=" + precioUnitario
				+ ", subtotal=" + calcularSubtotal() + "]";
	}
}