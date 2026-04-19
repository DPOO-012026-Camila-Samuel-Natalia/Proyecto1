package modelo;
import java.util.List;

public class Prestamo {
	private Usuario solicitante;
    private JuegoDeMesa juego;
    private boolean activo;

    public Prestamo(Usuario solicitante, JuegoDeMesa juego) {
        this.solicitante = solicitante;
        this.juego = juego;
        this.activo = true;
    }

    public void cerrar() {
        this.activo = false;
    }

	public Usuario getSolicitante() {
		return solicitante;
	}

	public JuegoDeMesa getJuego() {
		return juego;
	}

	public boolean isActivo() {
		return activo;
	}
    
    
}
