package persistencia;

import logica.BoardgameCafe;

public class CentralPersistencia
{
	private IPersistenciaBoardGameCafe persistencia;

	public CentralPersistencia(IPersistenciaBoardGameCafe persistencia)
	{
		this.persistencia = persistencia;
	}

	public void guardar(BoardgameCafe cafe)
	{
		persistencia.guardar(cafe);
	}

	public BoardgameCafe cargar()
	{
		return persistencia.cargar();
	}
}