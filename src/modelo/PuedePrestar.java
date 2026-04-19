package modelo;
import java.util.List;

public interface PuedePrestar {
	
	    boolean puedePedirPrestamo();
	    void agregarJuegoPrestado(JuegoDeMesa juego);
	    void quitarJuegoPrestado(JuegoDeMesa juego);
	    List<JuegoDeMesa> getJuegosPrestados();
		

}
