package persistencia;

import modelo.BoardgameCafe;

// Esta interfaz define las operaciones basicas de persistencia
// Es decir, que metodos deben implementar todas las clases de persistencia
public interface IPersistenciaBoardGameCafe
{
    // Guarda el estado completo del cafe en un archivo
    public void guardar(BoardgameCafe cafe);

    // Carga el estado del cafe desde un archivo
    public BoardgameCafe cargar();
}