package modelo;

// Esta clase representa una linea de una venta del cafe.
// por ejemplo: 2 cafes o 3 brownies.
public class DetalleVentaCafe
{
	// producto vendido
	private ProductoMenu producto;
	
	// cantidad vendida
	private int cantidad;

	// Constructor
	public DetalleVentaCafe(ProductoMenu producto, int cantidad)
	{
		this.producto = producto;
		this.cantidad = cantidad;
	}

	// devuelve el producto
	public ProductoMenu getProducto()
	{
		return producto;
	}

	// devuelve la cantidad
	public int getCantidad()
	{
		return cantidad;
	}

	// cambia la cantidad
	public void setCantidad(int cantidad)
	{
		this.cantidad = cantidad;
	}

	// calcula el subtotal de esta linea
	// subtotal = precio del producto * cantidad
	public double calcularSubtotal()
	{
		return producto.getPrecio() * cantidad;
	}

	@Override
	public String toString()
	{
		return "DetalleVentaCafe [producto=" + producto.getNombre()
				+ ", cantidad=" + cantidad + ", subtotal=" + calcularSubtotal() + "]";
	}
}