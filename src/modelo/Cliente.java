package modelo;
import java.util.List;
import java.util.ArrayList;

public class Cliente extends Usuario implements PuedePrestar{

	private int puntosFidelidad = 0;
	private Mesa mesaActual = null;
	private List<JuegoDeMesa> juegosPrestados = new ArrayList<>() ;  // max 2
	

	public Cliente(String id, String nombre, String login, String password) {
		super(id, nombre, login, password);
		
	}


	public int getPuntosFidelidad() {
		return puntosFidelidad;
	}

	public Mesa getMesaActual() {
		return mesaActual;
	}


	public boolean tieneJuegoAccion() {
	    for (JuegoDeMesa j : juegosPrestados) {
	        if (j.getCategoria().equals("ACCION")) return true;
	    }
	    return false;
	}


	public boolean estaEnCafe() {
		if (mesaActual == null) {
			return false;}
		return true;  }

	public void asignarMesa(Mesa mesa) {
		this.mesaActual = mesa;
	}

	public void liberarMesa() {
		this.mesaActual = null;
	}

	public void agregarPuntos (int puntos) {
		this.puntosFidelidad += puntos;
	}

	public void usarPuntos (int puntos) {
		if (puntos > this.puntosFidelidad) {
			throw new IllegalStateException("Puntos insuficientes");}
		this.puntosFidelidad -= puntos;
	}
	
	@Override
    public boolean puedePedirPrestamo() {
        return (juegosPrestados.size() < 2) && this.estaEnCafe();
        
    }

    @Override
    public void agregarJuegoPrestado(JuegoDeMesa juego) {
        juegosPrestados.add(juego);
    }

    @Override
    public void quitarJuegoPrestado(JuegoDeMesa juego) {
        juegosPrestados.remove(juego);
    }

    @Override
    public List<JuegoDeMesa> getJuegosPrestados() { return juegosPrestados; }
}
	


