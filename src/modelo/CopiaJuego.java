package modelo;

// Esta clase representa una copia física de un juego.
public class CopiaJuego
{
	// Juego al que pertenece la copia
	private JuegoMesa juego;
	
	// Indica si la copia está disponible
	private boolean disponible;

	// Constructor
	public CopiaJuego(JuegoMesa juego)
	{
		this.juego = juego;
		this.disponible = true;
	}

	// Devuelve el juego
	public JuegoMesa getJuego()
	{
		return juego;
	}

	// Devuelve si la copia está disponible
	public boolean isDisponible()
	{
		return disponible;
	}

	// Cambia la disponibilidad
	public void setDisponible(boolean disponible)
	{
		this.disponible = disponible;
	}

	@Override
	public String toString()
	{
		return "CopiaJuego [juego=" + juego.getNombre() + ", disponible=" + disponible + "]";
	}
}