package persistencia;

import logica.BoardgameCafe;

public interface IPersistenciaBoardGameCafe
{
	public void guardar(BoardgameCafe cafe);

	public BoardgameCafe cargar();
}