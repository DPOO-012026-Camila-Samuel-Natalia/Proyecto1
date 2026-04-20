package persistencia;

import java.io.*;
import java.util.Collection;

import modelo.*;

// Esta clase guarda y carga el sistema en archivo de texto
public class PersistenciaBoardGameCafeJSON implements IPersistenciaBoardGameCafe
{
    private String ruta;

    public PersistenciaBoardGameCafeJSON(String ruta)
    {
        this.ruta = ruta;
    }

    // =========================
    // GUARDAR
    // =========================
    @Override
    public void guardar(BoardgameCafe cafe)
    {
        try
        {
            PrintWriter out = new PrintWriter(new FileWriter(ruta));

            // Capacidad
            out.println("CAPACIDAD;" + cafe.getCapacidadMaxima());

            // ADMIN
            Administrador a = cafe.getAdministrador();
            if (a != null)
            {
                out.println("ADMIN;" + a.getId() + ";" + a.getNombre() + ";" + a.getLogin() + ";" + a.getPassword());
            }

            // CLIENTES
            Collection<Cliente> clientes = cafe.getClientes();
            for (Cliente c : clientes)
            {
                String mesa = "null";
                if (c.getMesaActual() != null)
                {
                    mesa = "" + c.getMesaActual().getNumero();
                }

                String codigo = c.getCodigoDescuento();
                if (codigo == null) codigo = "null";

                out.println("CLIENTE;" + c.getId() + ";" + c.getNombre() + ";" + c.getLogin() + ";" + c.getPassword()
                        + ";" + c.getPuntosFidelidad() + ";" + mesa + ";" + codigo);
            }

            // EMPLEADOS
            Collection<Empleado> empleados = cafe.getEmpleados();
            for (Empleado e : empleados)
            {
                String tipo = "COCINERO";
                if (e instanceof Mesero)
                {
                    tipo = "MESERO";
                }

                out.println("EMPLEADO;" + tipo + ";" + e.getId() + ";" + e.getNombre() + ";" + e.getLogin() + ";"
                        + e.getPassword() + ";" + e.isEnTurno());

                // Juegos que explica
                if (e instanceof Mesero)
                {
                    Mesero m = (Mesero) e;
                    for (String juego : m.getJuegosQueExplica())
                    {
                        out.println("EXPLICA;" + m.getLogin() + ";" + juego);
                    }
                }
            }

            // MESAS
            for (Mesa m : cafe.getMesas())
            {
                out.println("MESA;" + m.getNumero() + ";" + m.getCapacidad() + ";" + m.getNumPersonas() + ";"
                        + m.isNinos() + ";" + m.hayMenoresDeEdad());
            }

            // JUEGOS
            for (JuegoDeMesa j : cafe.getCatalogoJuegos())
            {
                out.println("JUEGO;" + j.getNombre() + ";" + j.getMinJugadores() + ";" + j.getMaxJugadores() + ";"
                        + j.getEdadMinima() + ";" + j.getCategoria() + ";" + j.isDificil() + ";"
                        + j.getTotalCopiasPrestamo() + ";" + j.getCopiasVenta() + ";" + j.getPrecioUnitario()
                        + ";" + j.getCopiasEnUso());
            }

            // PRODUCTOS
            for (ProductoMenu p : cafe.getProductosMenu())
            {
                if (p instanceof Bebida)
                {
                    Bebida b = (Bebida) p;
                    out.println("BEBIDA;" + b.getNombre() + ";" + b.getPrecio() + ";" + b.isAlcoholica() + ";"
                            + b.isCaliente());
                }
                else if (p instanceof Pasteleria)
                {
                    Pasteleria pa = (Pasteleria) p;
                    out.println("PASTELERIA;" + pa.getNombre() + ";" + pa.getPrecio());
                }
            }

            // TURNOS
            for (Turno t : cafe.getTurnos())
            {
                out.println("TURNO;" + t.getDiaSemana());

                for (Empleado e : t.getEmpleados())
                {
                    out.println("TURNO_EMPLEADO;" + t.getDiaSemana() + ";" + e.getLogin());
                }
            }

            out.close();
        }
        catch (IOException e)
        {
            System.out.println("Error guardando: " + e.getMessage());
        }
    }

    // =========================
    // CARGAR (SIN break)
    // =========================
    @Override
    public BoardgameCafe cargar()
    {
        BoardgameCafe cafe = null;

        try
        {
            BufferedReader br = new BufferedReader(new FileReader(ruta));
            String linea = br.readLine();

            // Buscar capacidad SIN break
            while (linea != null)
            {
                String[] p = linea.split(";");

                if (p[0].equals("CAPACIDAD") && cafe == null)
                {
                    cafe = new BoardgameCafe(Integer.parseInt(p[1]));
                }

                linea = br.readLine();
            }

            br.close();

            if (cafe == null)
            {
                return null;
            }

            br = new BufferedReader(new FileReader(ruta));
            linea = br.readLine();

            while (linea != null)
            {
                String[] p = linea.split(";");

                if (p[0].equals("ADMIN"))
                {
                    cafe.setAdministrador(new Administrador(p[1], p[2], p[3], p[4]));
                }

                else if (p[0].equals("CLIENTE"))
                {
                    Cliente c = new Cliente(p[1], p[2], p[3], p[4]);
                    c.agregarPuntos(Integer.parseInt(p[5]));
                    cafe.agregarCliente(c);
                }

                else if (p[0].equals("EMPLEADO"))
                {
                    Empleado e;

                    if (p[1].equals("MESERO"))
                    {
                        e = new Mesero(p[2], p[3], p[4], p[5]);
                    }
                    else
                    {
                        e = new Cocinero(p[2], p[3], p[4], p[5]);
                    }

                    if (Boolean.parseBoolean(p[6]))
                    {
                        e.iniciarTurno();
                    }

                    cafe.agregarEmpleado(e);
                }

                else if (p[0].equals("MESA"))
                {
                    Mesa m = new Mesa(Integer.parseInt(p[1]), Integer.parseInt(p[2]));

                    if (Integer.parseInt(p[3]) > 0)
                    {
                        m.ocupar(Integer.parseInt(p[3]),
                                Boolean.parseBoolean(p[4]),
                                Boolean.parseBoolean(p[5]));
                    }

                    cafe.agregarMesa(m);
                }

                else if (p[0].equals("JUEGO"))
                {
                    JuegoDeMesa j = new JuegoDeMesa(
                            p[1],
                            0,
                            "NA",
                            Integer.parseInt(p[2]),
                            Integer.parseInt(p[3]),
                            Integer.parseInt(p[4]),
                            p[5],
                            Boolean.parseBoolean(p[6]),
                            Integer.parseInt(p[7]),
                            Integer.parseInt(p[8]),
                            Double.parseDouble(p[9])
                    );

                    j.setCopiasEnUso(Integer.parseInt(p[10]));
                    cafe.agregarJuego(j);
                }

                else if (p[0].equals("TURNO"))
                {
                    cafe.agregarTurno(new Turno(p[1]));
                }

<<<<<<< Updated upstream
		return datos;
	}
}





//=========================
		// PERSISTENCIA
		// =========================

		// Se crea la persistencia apuntando a la carpeta datos dentro de src
		PersistenciaBoardGameCafeJSON persistenciaJSON = new PersistenciaBoardGameCafeJSON("src/datos/boardgamecafe.json");

		// Se crea la central de persistencia
		CentralPersistencia central = new CentralPersistencia(persistenciaJSON);

		// Se guarda el sistema
		central.guardar(cafe);

		System.out.println();
		System.out.println("Se guardó el sistema en el archivo JSON.");

		// Se carga el sistema desde el archivo
		BoardgameCafe cafeCargado = central.cargar();

		System.out.println("Sistema cargado desde persistencia:");
		System.out.println(cafeCargado);
	}
=======
                linea = br.readLine();
            }

            br.close();
        }
        catch (IOException e)
        {
            System.out.println("Error cargando: " + e.getMessage());
        }

        return cafe;
    }
}
>>>>>>> Stashed changes
