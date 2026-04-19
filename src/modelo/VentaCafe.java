package modelo;

import java.util.ArrayList;

// Esta clase representa una venta del café.
// Hereda de Venta.
public class VentaCafe extends Venta
{
	// Lista de productos vendidos en esta venta
	private ArrayList<DetalleVentaCafe> detalles;

	
	// Indica si se cobra propina
	private boolean cobraPropina;

	// Constructor
	public VentaCafe(String codigo, Cliente cliente, boolean cobraPropina)
	{
		super(codigo, cliente);
		this.cobraPropina = cobraPropina;
		this.detalles = new ArrayList<DetalleVentaCafe>();
	}

	// Devuelve la lista de detalles
	public ArrayList<DetalleVentaCafe> getDetalles()
	{
		return detalles;
	}

	// Devuelve la mesa
	public Mesa getMesa() {
	    if (!(getComprador() instanceof Cliente))
	        throw new IllegalStateException("El comprador no es un cliente");
	    
	    Cliente cliente = (Cliente) getComprador();
	    
	    if (!cliente.estaEnCafe())
	        throw new IllegalStateException("Cliente no tiene mesa asignada");
	    
	    return cliente.getMesaActual();
	}

	// Devuelve si cobra propina
	public boolean isCobraPropina()
	{
		return cobraPropina;
	}

	// Agrega un detalle a la venta
	public void agregarDetalle(DetalleVentaCafe detalle)
	{
		detalles.add(detalle);
	}

	// Calcula el subtotal sumando todos los detalles
	public double calcularSubtotal()
	{
		double subtotal = 0;

		for (int i = 0; i < detalles.size(); i++)
		{
			subtotal += detalles.get(i).calcularSubtotal();
		}

		return subtotal;
	}

	// Calcula el impuesto al consumo (8%)
	public double calcularImpuestoConsumo()
	{
		return calcularSubtotal() * 0.08;
	}

	// Calcula la propina (10%) si aplica
	public double calcularPropina()
	{
		double propina = 0;

		if (cobraPropina)
		{
			propina = calcularSubtotal() * 0.10;
		}

		return propina;
	}

	public Cliente getCliente() {
        return (Cliente) getComprador();
    }
	// Calcula el total final
	@Override
	public double calcularTotal()
	{
		return calcularSubtotal() + calcularImpuestoConsumo() + calcularPropina();
	}

	@Override
	public String toString()
	{
		return "VentaCafe [codigo=" + getCodigo() + ", cliente=" + getComprador().getNombre()
				+ ", mesa=" + this.getMesa().getNumero() + ", subtotal=" + calcularSubtotal()
				+ ", impuesto=" + calcularImpuestoConsumo()
				+ ", propina=" + calcularPropina()
				+ ", total=" + calcularTotal() + "]";
	}
}