package modelo;

public class Mesa {
	private int numero;
	private int capacidad;
	private int numPersonas;
	private boolean ninos; // menores de 5 años
	private boolean menoresDeEdad; //menores de 18 
	
	
	
	
	public Mesa(int numero, int capacidad) {
		super();
		this.numero = numero;
		this.capacidad = capacidad;
		this.numPersonas = 0;
		this.ninos = false;
		this.menoresDeEdad = false;
	}
	public int getNumero() {
		return numero;
	}
	public int getCapacidad() {
		return capacidad;
	}
	public int getNumPersonas() {
		return numPersonas;
	}
	public boolean isNinos() {
		return ninos;
	}
	public boolean hayMenoresDeEdad() {
		return menoresDeEdad;
	} 
	
	
	public boolean ocupada() {return numPersonas>0;}
	
	public void ocupar(int numPersonas, boolean ninos, boolean menoresDeEdad) {
		if (numPersonas > capacidad)
			throw new IllegalStateException("Supera la capacidad de la mesa");
		this.numPersonas = numPersonas;
		this.ninos = ninos;
		this.menoresDeEdad = menoresDeEdad;
	}
	
	
	public void liberar() {
		this.numPersonas = 0;
		this.ninos = false;
		this.menoresDeEdad = false;
	}
	
	
	
	
}

