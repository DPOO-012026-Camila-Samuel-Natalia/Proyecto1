package modelo;

// Esta clase representa un juego del catálogo.
public class JuegoMesa
{
	// Nombre del juego
	private String nombre;
	
	// Mínimo de jugadores
	private int minJugadores;
	
	// Máximo de jugadores
	private int maxJugadores;
	
	// Edad mínima requerida
	private int edadMinima;

	// Constructor
	public JuegoMesa(String nombre, int minJugadores, int maxJugadores, int edadMinima)
	{
		this.nombre = nombre;
		this.minJugadores = minJugadores;
		this.maxJugadores = maxJugadores;
		this.edadMinima = edadMinima;
	}

	// Devuelve el nombre
	public String getNombre()
	{
		return nombre;
	}

	// Devuelve el mínimo de jugadores
	public int getMinJugadores()
	{
		return minJugadores;
	}

	// Devuelve el máximo de jugadores
	public int getMaxJugadores()
	{
		return maxJugadores;
	}

	// Devuelve la edad mínima
	public int getEdadMinima()
	{
		return edadMinima;
	}

	@Override
	public String toString()
	{
		return "JuegoMesa [nombre=" + nombre + ", minJugadores=" + minJugadores
				+ ", maxJugadores=" + maxJugadores + ", edadMinima=" + edadMinima + "]";
	}
}