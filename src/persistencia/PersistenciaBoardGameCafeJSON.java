package persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import logica.Administrador;
import logica.BoardgameCafe;
import logica.Cliente;
import logica.Cocinero;
import logica.CopiaJuego;
import logica.Empleado;
import logica.JuegoMesa;
import logica.Mesa;
import logica.Mesero;

public class PersistenciaBoardGameCafeJSON implements IPersistenciaBoardGameCafe
{
	private String ruta;

	public PersistenciaBoardGameCafeJSON(String ruta)
	{
		this.ruta = ruta;
	}

	@Override
	public void guardar(BoardgameCafe cafe)
	{
		try
		{
			FileWriter writer = new FileWriter(ruta);

			writer.write("{\n");

			// ADMINISTRADOR
			writer.write("\"administrador\": [\n");
			if (cafe.getAdministrador() != null)
			{
				Administrador a = cafe.getAdministrador();
				writer.write("\"" + a.getId() + "|" + a.getNombre() + "|" + a.getLogin() + "|" + a.getPassword() + "\"\n");
			}
			writer.write("],\n");

			// CLIENTES
			writer.write("\"clientes\": [\n");
			for (int i = 0; i < cafe.getClientes().size(); i++)
			{
				Cliente c = cafe.getClientes().get(i);
				writer.write("\"" + c.getId() + "|" + c.getNombre() + "|" + c.getLogin() + "|" + c.getPassword() + "|" + c.getEdad() + "|" + c.getPuntosFidelidad() + "\"");
				if (i < cafe.getClientes().size() - 1) writer.write(",");
				writer.write("\n");
			}
			writer.write("],\n");

			// EMPLEADOS
			writer.write("\"empleados\": [\n");
			for (int i = 0; i < cafe.getEmpleados().size(); i++)
			{
				Empleado e = cafe.getEmpleados().get(i);

				if (e instanceof Mesero)
				{
					writer.write("\"Mesero|" + e.getId() + "|" + e.getNombre() + "|" + e.getLogin() + "|" + e.getPassword() + "|" + e.getTurno() + "\"");
				}
				else if (e instanceof Cocinero)
				{
					Cocinero c = (Cocinero) e;
					writer.write("\"Cocinero|" + c.getId() + "|" + c.getNombre() + "|" + c.getLogin() + "|" + c.getPassword() + "|" + c.getTurno() + "|" + c.getEspecialidad() + "\"");
				}

				if (i < cafe.getEmpleados().size() - 1) writer.write(",");
				writer.write("\n");
			}
			writer.write("],\n");

			// MESAS
			writer.write("\"mesas\": [\n");
			for (int i = 0; i < cafe.getMesas().size(); i++)
			{
				Mesa m = cafe.getMesas().get(i);

				boolean tieneSesion = m.getSesionActual() != null;
				int cantidad = 0;
				boolean menores5 = false;
				boolean menores18 = false;

				if (tieneSesion)
				{
					cantidad = m.getSesionActual().getCantidadPersonas();
					menores5 = m.getSesionActual().hayMenores5();
					menores18 = m.getSesionActual().hayMenores18();
				}

				writer.write("\"" + m.getNumero() + "|" + m.getCapacidad() + "|" + tieneSesion + "|" + cantidad + "|" + menores5 + "|" + menores18 + "\"");
				if (i < cafe.getMesas().size() - 1) writer.write(",");
				writer.write("\n");
			}
			writer.write("],\n");

			// JUEGOS
			writer.write("\"juegos\": [\n");
			for (int i = 0; i < cafe.getJuegos().size(); i++)
			{
				JuegoMesa j = cafe.getJuegos().get(i);
				writer.write("\"" + j.getNombre() + "|" + j.getMinJugadores() + "|" + j.getMaxJugadores() + "|" + j.getEdadMinima() + "\"");
				if (i < cafe.getJuegos().size() - 1) writer.write(",");
				writer.write("\n");
			}
			writer.write("],\n");

			// COPIAS
			writer.write("\"copias\": [\n");
			for (int i = 0; i < cafe.getCopias().size(); i++)
			{
				CopiaJuego c = cafe.getCopias().get(i);
				writer.write("\"" + c.getJuego().getNombre() + "|" + c.isDisponible() + "\"");
				if (i < cafe.getCopias().size() - 1) writer.write(",");
				writer.write("\n");
			}
			writer.write("]\n");

			writer.write("}");
			writer.close();
		}
		catch (IOException e)
		{
			System.out.println("Error guardando: " + e.getMessage());
		}
	}

	@Override
	public BoardgameCafe cargar()
	{
		BoardgameCafe cafe = new BoardgameCafe();

		try
		{
			BufferedReader br = new BufferedReader(new FileReader(ruta));
			StringBuilder contenido = new StringBuilder();
			String linea = br.readLine();

			while (linea != null)
			{
				contenido.append(linea).append("\n");
				linea = br.readLine();
			}
			br.close();

			String texto = contenido.toString();

			cargarAdministrador(cafe, extraerArreglo(texto, "administrador"));
			cargarClientes(cafe, extraerArreglo(texto, "clientes"));
			cargarEmpleados(cafe, extraerArreglo(texto, "empleados"));
			cargarMesas(cafe, extraerArreglo(texto, "mesas"));
			cargarJuegos(cafe, extraerArreglo(texto, "juegos"));
			cargarCopias(cafe, extraerArreglo(texto, "copias"));
		}
		catch (IOException e)
		{
			System.out.println("Error cargando: " + e.getMessage());
		}

		return cafe;
	}

	private void cargarAdministrador(BoardgameCafe cafe, java.util.ArrayList<String> datos)
	{
		for (int i = 0; i < datos.size(); i++)
		{
			String[] p = datos.get(i).split("\\|");
			if (p.length == 4)
			{
				cafe.setAdministrador(new Administrador(p[0], p[1], p[2], p[3]));
			}
		}
	}

	private void cargarClientes(BoardgameCafe cafe, java.util.ArrayList<String> datos)
	{
		for (int i = 0; i < datos.size(); i++)
		{
			String[] p = datos.get(i).split("\\|");
			if (p.length == 6)
			{
				Cliente c = new Cliente(p[0], p[1], p[2], p[3], Integer.parseInt(p[4]));
				c.agregarPuntos(Integer.parseInt(p[5]));
				cafe.agregarCliente(c);
			}
		}
	}

	private void cargarEmpleados(BoardgameCafe cafe, java.util.ArrayList<String> datos)
	{
		for (int i = 0; i < datos.size(); i++)
		{
			String[] p = datos.get(i).split("\\|");

			if (p[0].equals("Mesero") && p.length == 6)
			{
				cafe.agregarEmpleado(new Mesero(p[1], p[2], p[3], p[4], p[5]));
			}
			else if (p[0].equals("Cocinero") && p.length == 7)
			{
				cafe.agregarEmpleado(new Cocinero(p[1], p[2], p[3], p[4], p[5], p[6]));
			}
		}
	}

	private void cargarMesas(BoardgameCafe cafe, java.util.ArrayList<String> datos)
	{
		for (int i = 0; i < datos.size(); i++)
		{
			String[] p = datos.get(i).split("\\|");

			if (p.length == 6)
			{
				Mesa m = new Mesa(Integer.parseInt(p[0]), Integer.parseInt(p[1]));

				if (Boolean.parseBoolean(p[2]))
				{
					m.iniciarSesion(
							Integer.parseInt(p[3]),
							Boolean.parseBoolean(p[4]),
							Boolean.parseBoolean(p[5]));
				}

				cafe.agregarMesa(m);
			}
		}
	}

	private void cargarJuegos(BoardgameCafe cafe, java.util.ArrayList<String> datos)
	{
		for (int i = 0; i < datos.size(); i++)
		{
			String[] p = datos.get(i).split("\\|");
			if (p.length == 4)
			{
				cafe.agregarJuego(new JuegoMesa(p[0], Integer.parseInt(p[1]), Integer.parseInt(p[2]), Integer.parseInt(p[3])));
			}
		}
	}

	private void cargarCopias(BoardgameCafe cafe, java.util.ArrayList<String> datos)
	{
		for (int i = 0; i < datos.size(); i++)
		{
			String[] p = datos.get(i).split("\\|");
			if (p.length == 2)
			{
				JuegoMesa juego = cafe.buscarJuego(p[0]);
				if (juego != null)
				{
					CopiaJuego copia = new CopiaJuego(juego);
					copia.setDisponible(Boolean.parseBoolean(p[1]));
					cafe.agregarCopia(copia);
				}
			}
		}
	}

	private java.util.ArrayList<String> extraerArreglo(String texto, String clave)
	{
		java.util.ArrayList<String> datos = new java.util.ArrayList<String>();

		String marcador = "\"" + clave + "\": [";
		int inicio = texto.indexOf(marcador);

		if (inicio != -1)
		{
			int inicioArreglo = texto.indexOf("[", inicio);
			int finArreglo = texto.indexOf("]", inicioArreglo);

			String contenido = texto.substring(inicioArreglo + 1, finArreglo);
			String[] lineas = contenido.split("\n");

			for (int i = 0; i < lineas.length; i++)
			{
				String linea = lineas[i].trim();
				linea = linea.replace("\"", "");
				linea = linea.replace(",", "");
				linea = linea.trim();

				if (!linea.equals(""))
				{
					datos.add(linea);
				}
			}
		}

		return datos;
	}
}