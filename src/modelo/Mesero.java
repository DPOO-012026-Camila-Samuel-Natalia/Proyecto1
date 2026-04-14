package modelo;

import java.util.ArrayList;

// Esta clase representa a un mesero.
public class Mesero extends Empleado
{
	// Juegos que el mesero sabe explicar
	private ArrayList<String> juegosQueExplica;

	// Constructor
	public Mesero(String id, String nombre, String login, String password, String turno)
	{
		super(id, nombre, login, password, turno);
		this.juegosQueExplica = new ArrayList<String>();
	}

	// Devuelve la lista de juegos que sabe explicar
	public ArrayList<String> getJuegosQueExplica()
	{
		return juegosQueExplica;
	}

	// Agrega un juego a la lista
	public void agregarJuegoQueExplica(String nombreJuego)
	{
		if (nombreJuego != null && !nombreJuego.equals(""))
		{
			juegosQueExplica.add(nombreJuego);
		}
	}

	// Revisa si sabe explicar un juego
	public boolean sabeExplicarJuego(String nombreJuego)
	{
		for (int i = 0; i < juegosQueExplica.size(); i++)
		{
			if (juegosQueExplica.get(i).equals(nombreJuego))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString()
	{
		return "Mesero [id=" + getId() + ", nombre=" + getNombre() + ", turno=" + getTurno() + "]";
	}
}