package modelo;

import java.util.List;

public class Platillo {

	private String nombre;
	private double precio;
	private String tipo;
	private boolean esAlcoholico;
	private boolean esCaliente;
	private List<String> alergenos;
	
	public Platillo(String nombre, double precio, String tipo, boolean esAlcoholico, boolean esCaliente,
			List<String> alergenos) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.tipo = tipo; // "CARTAS", "TABLERO" o "ACCION"
		this.esAlcoholico = esAlcoholico;
		this.esCaliente = esCaliente;
		this.alergenos = alergenos;
	}
	
	public void agregarAlergeno(String alergeno) {
		alergenos.add(alergeno);
	}
	
	public boolean tieneAlergeno(String alergeno) {
		return alergenos.contains(alergeno);
	}

	public String getNombre() {
		return nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public String getTipo() {
		return tipo;
	}

	public boolean isEsAlcoholico() {
		return esAlcoholico;
	}

	public boolean isEsCaliente() {
		return esCaliente;
	}

	public List<String> getAlergenos() {
		return alergenos;
	}
	
	
	
}
