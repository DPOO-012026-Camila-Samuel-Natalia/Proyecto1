package modelo;

import java.util.ArrayList;
import java.util.List;


// un empleado generico. Solo sirve como base para Mesero y Cocinero.
public abstract class Empleado extends Usuario implements PuedePrestar {

    private ArrayList<Turno> turnos;
    private boolean enTurno;
    private List<JuegoDeMesa> juegosPrestados;
    private String codigoDescuento;

    
    public Empleado(String id, String nombre, String login, String password) {
        super(id, nombre, login, password);
        this.turnos = new ArrayList<>();
        this.enTurno = false;
        this.juegosPrestados = new ArrayList<>();
        this.codigoDescuento = "DESC-" + id;
    }

    //  TURNOS 
    public void agregarTurno(Turno turno)  { turnos.add(turno); }
    public void quitarTurno(Turno turno)   { turnos.remove(turno); }
    public ArrayList<Turno> getTurnos()    { return turnos; }

    // ESTADO
    public boolean isEnTurno()     { return enTurno; }
    
    public void iniciarTurno()  { enTurno = true; }
    public void terminarTurno() { enTurno = false; }

  

    //  DESCUENTOS 
    public String getCodigoDescuento() { return codigoDescuento; }

    // PUEDE PRESTAR 
    @Override
    public boolean puedePedirPrestamo() {
        if (enTurno) return false;
        return juegosPrestados.size() < 2;
    }

    @Override
    public void agregarJuegoPrestado(JuegoDeMesa juego) { juegosPrestados.add(juego); }

    @Override
    public void quitarJuegoPrestado(JuegoDeMesa juego)  { juegosPrestados.remove(juego); }

    @Override
    public List<JuegoDeMesa> getJuegosPrestados()       { return juegosPrestados; }

    // TO STRING 
    @Override
    public String toString() {
       
        String listaTurnos = "";
        for (int i = 0; i < turnos.size(); i++) {
            listaTurnos += turnos.get(i).toString();
            if (i < turnos.size() - 1) listaTurnos += ", ";
        }
        return "Empleado [id=" + getId() 
            + ", nombre=" + getNombre() 
            + ", enTurno=" + enTurno
            + ", turnos=[" + listaTurnos + "]]";
    }
}