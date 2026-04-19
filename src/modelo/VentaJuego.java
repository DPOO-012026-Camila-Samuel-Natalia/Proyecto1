package modelo;

import java.util.ArrayList;
import java.text.SimpleDateFormat;

// Esta clase representa una venta de juegos.
// Hereda de Venta.
public class VentaJuego extends Venta
{
	// Lista de detalles de la venta
	private ArrayList<DetalleVentaJuego> detalles;

	// Constructor
	public VentaJuego(String codigo, Usuario comprador)
	{
		super(codigo, comprador);
		this.detalles = new ArrayList<DetalleVentaJuego>();
	}

	// Devuelve la lista de detalles
	public ArrayList<DetalleVentaJuego> getDetalles()
	{
		return detalles;
	}

	// Agrega un detalle a la venta
	public void agregarDetalle(DetalleVentaJuego detalle)
	{
		detalles.add(detalle);
	}

	// Calcula el subtotal de la venta
	public double calcularSubtotal()
	{
		double subtotal = 0;

		for (int i = 0; i < detalles.size(); i++)
		{
			subtotal += detalles.get(i).calcularSubtotal();
		}

		return subtotal;
	}

	// Calcula IVA del 19%
	public double calcularIVA()
	{
		return calcularSubtotal() * 0.19;
	}

	// Calcula el total final
	@Override
	public double calcularTotal()
	{
		return calcularSubtotal() + calcularIVA() - getDescuento();
	}

	@Override
	public String toString()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String fechaFormateada = sdf.format(getFecha());
		return "VentaJuego [codigo=" + getCodigo()
				+ ", cliente= " + getComprador().getNombre()
				+ ", subtotal= " + calcularSubtotal()
				+ ", iva= " + calcularIVA()
				+ ", total= " + calcularTotal() 
				+ ",Fecha= " + fechaFormateada + "]";
	}
}