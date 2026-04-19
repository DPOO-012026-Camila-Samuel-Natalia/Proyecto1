package modelo;
import java.util.ArrayList;

public class Turno {
	    private String diaSemana;
	    private String horaInicio;
	    private String horaFin;
	    private ArrayList<Empleado> empleados;  // quiénes trabajan en este turno

	    public Turno(String diaSemana, String horaInicio, String horaFin) {
	        this.diaSemana = diaSemana;
	        this.horaInicio = horaInicio;
	        this.horaFin = horaFin;
	        this.empleados = new ArrayList<>();
	    }

	    public void agregarEmpleado(Empleado e) { empleados.add(e); }
	    public void quitarEmpleado(Empleado e)  { empleados.remove(e); }

	    // Verifica que el turno tenga mínimo 1 cocinero y 2 meseros sin el empleado dado por parametro
	    public boolean tienePersonalMinimoSin(Empleado empleado) {
	        int cocineros = 0;
	        int meseros = 0;
	        for (Empleado e : empleados) {
	            if (e.equals(empleado)) continue; // asi ignora este
	            if (e instanceof Cocinero) cocineros++;
	            if (e instanceof Mesero)   meseros++;
	        }
	        return cocineros >= 1 && meseros >= 2;
	    }
	    public ArrayList<Empleado> getEmpleados() { return empleados; }
	    public String getDiaSemana()  { return diaSemana; }
	    public String getHoraInicio() { return horaInicio; }
	    public String getHoraFin()    { return horaFin; }

	    @Override
	    public String toString() {
	        return diaSemana + " " + horaInicio + "-" + horaFin;
	    }
	}