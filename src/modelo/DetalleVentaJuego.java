package modelo;

// esta clase representa una línea de una venta de juegos.
// por ejemplo: 2 unidades de Catan.
public class DetalleVentaJuego
{
	// juego vendido
	private JuegoDeMesa juego;
	
	// cantidad vendida
	private int cantidad;
	
	// precio unitario del juego en la venta
	private double precioUnitario;

	// Constructor
	public DetalleVentaJuego(JuegoDeMesa juego, int cantidad)
	{
		this.juego = juego;
		this.cantidad = cantidad;
		this.precioUnitario = this.juego.getPrecioUnitario();
	}

	// devuelve el juego
	public JuegoDeMesa getJuego()
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