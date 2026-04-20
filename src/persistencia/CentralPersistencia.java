package persistencia;

import modelo.BoardgameCafe;

// Esta clase funciona como intermediaria entre el sistema y la persistencia
// Permite desacoplar la logica del tipo de almacenamiento (JSON, plano, etc.)
public class CentralPersistencia
{
    // Referencia a la implementacion de persistencia (JSON en este caso)
    private IPersistenciaBoardGameCafe persistencia;

    // Constructor: recibe la implementacion concreta
    public CentralPersistencia(IPersistenciaBoardGameCafe persistencia)
    {
        this.persistencia = persistencia;
    }

    // Metodo para guardar el sistema
    public void guardar(BoardgameCafe cafe)
    {
        persistencia.guardar(cafe);
    }

    // Metodo para cargar el sistema
    public BoardgameCafe cargar()
    {
        return persistencia.cargar();
    }
}