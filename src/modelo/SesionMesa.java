package modelo;

// esta clase representa la sesión actual de una mesa,
// es decir, las personas sentadas en ese momento.
public class SesionMesa
{
	// nnumero de personas en la mesa
	private int cantidadPersonas;
	
	// indica si hay menores de 5 años
	private boolean hayMenores5;
	
	// indica si hay menores de 18 años
	private boolean hayMenores18;

	// Constructor
	public SesionMesa(int cantidadPersonas, boolean hayMenores5, boolean hayMenores18)
	{
		this.cantidadPersonas = cantidadPersonas;
		this.hayMenores5 = hayMenores5;
		this.hayMenores18 = hayMenores18;
	}

	// devuelve la cantidad de personas
	public int getCantidadPersonas()
	{
		return cantidadPersonas;
	}

	// devuelve si hay menores de 5
	public boolean hayMenores5()
	{
		return hayMenores5;
	}

	// devuelve si hay menores de 18
	public boolean hayMenores18()
	{
		return hayMenores18;
	}

	@Override
	public String toString()
	{
		return "SesionMesa [cantidadPersonas=" + cantidadPersonas + ", hayMenores5=" + hayMenores5
				+ ", hayMenores18=" + hayMenores18 + "]";
	}
}