package modelo;

import java.util.ArrayList;

public class BoardgameCafe
{
	private Administrador administrador;

	private ArrayList<Cliente> clientes;
	private ArrayList<Empleado> empleados;
	private ArrayList<Mesa> mesas;
	private ArrayList<JuegoMesa> juegos;
	private ArrayList<CopiaJuego> copias;
	private ArrayList<Prestamo> prestamos;
	private ArrayList<ProductoMenu> productosMenu;
	private ArrayList<VentaCafe> ventasCafe;
	private ArrayList<VentaJuego> ventasJuego;
	private ArrayList<SolicitudCambioTurno> solicitudesCambioTurno;
	private ArrayList<SugerenciaPlatillo> sugerenciasPlatillos;

	public BoardgameCafe()
	{
		administrador = null;
		clientes = new ArrayList<Cliente>();
		empleados = new ArrayList<Empleado>();
		mesas = new ArrayList<Mesa>();
		juegos = new ArrayList<JuegoMesa>();
		copias = new ArrayList<CopiaJuego>();
		prestamos = new ArrayList<Prestamo>();
		productosMenu = new ArrayList<ProductoMenu>();
		ventasCafe = new ArrayList<VentaCafe>();
		ventasJuego = new ArrayList<VentaJuego>();
		solicitudesCambioTurno = new ArrayList<SolicitudCambioTurno>();
		sugerenciasPlatillos = new ArrayList<SugerenciaPlatillo>();
	}


	// GETTERS


	public Administrador getAdministrador()
	{
		return administrador;
	}

	public ArrayList<Cliente> getClientes()
	{
		return clientes;
	}

	public ArrayList<Empleado> getEmpleados()
	{
		return empleados;
	}

	public ArrayList<Mesa> getMesas()
	{
		return mesas;
	}

	public ArrayList<JuegoMesa> getJuegos()
	{
		return juegos;
	}

	public ArrayList<CopiaJuego> getCopias()
	{
		return copias;
	}

	public ArrayList<Prestamo> getPrestamos()
	{
		return prestamos;
	}

	public ArrayList<ProductoMenu> getProductosMenu()
	{
		return productosMenu;
	}

	public ArrayList<VentaCafe> getVentasCafe()
	{
		return ventasCafe;
	}

	public ArrayList<VentaJuego> getVentasJuego()
	{
		return ventasJuego;
	}

	public ArrayList<SolicitudCambioTurno> getSolicitudesCambioTurno()
	{
		return solicitudesCambioTurno;
	}

	public ArrayList<SugerenciaPlatillo> getSugerenciasPlatillos()
	{
		return sugerenciasPlatillos;
	}


	// SETTERS

	public void setAdministrador(Administrador administrador)
	{
		this.administrador = administrador;
	}


	// AGREGAR


	public void agregarCliente(Cliente c)
	{
		clientes.add(c);
	}

	public void agregarEmpleado(Empleado e)
	{
		empleados.add(e);
	}

	public void agregarMesa(Mesa m)
	{
		mesas.add(m);
	}

	public void agregarJuego(JuegoMesa j)
	{
		juegos.add(j);
	}

	public void agregarCopia(CopiaJuego c)
	{
		copias.add(c);
	}

	public void agregarPrestamoDirecto(Prestamo p)
	{
		prestamos.add(p);
	}

	public void agregarProductoMenu(ProductoMenu p)
	{
		productosMenu.add(p);
	}

	public void agregarVentaCafe(VentaCafe v)
	{
		ventasCafe.add(v);
	}

	public void agregarVentaJuego(VentaJuego v)
	{
		ventasJuego.add(v);
	}

	public void agregarSolicitudCambioTurno(SolicitudCambioTurno s)
	{
		solicitudesCambioTurno.add(s);
	}

	public void agregarSugerenciaPlatillo(SugerenciaPlatillo s)
	{
		sugerenciasPlatillos.add(s);
	}


	// BUSCAR


	public Cliente buscarCliente(String id)
	{
		for (int i = 0; i < clientes.size(); i++)
		{
			if (clientes.get(i).getId().equals(id))
			{
				return clientes.get(i);
			}
		}
		return null;
	}

	public Empleado buscarEmpleado(String id)
	{
		for (int i = 0; i < empleados.size(); i++)
		{
			if (empleados.get(i).getId().equals(id))
			{
				return empleados.get(i);
			}
		}
		return null;
	}

	public Mesa buscarMesa(int numero)
	{
		for (int i = 0; i < mesas.size(); i++)
		{
			if (mesas.get(i).getNumero() == numero)
			{
				return mesas.get(i);
			}
		}
		return null;
	}

	public JuegoMesa buscarJuego(String nombre)
	{
		for (int i = 0; i < juegos.size(); i++)
		{
			if (juegos.get(i).getNombre().equals(nombre))
			{
				return juegos.get(i);
			}
		}
		return null;
	}

	public ProductoMenu buscarProductoMenu(String nombre)
	{
		for (int i = 0; i < productosMenu.size(); i++)
		{
			if (productosMenu.get(i).getNombre().equals(nombre))
			{
				return productosMenu.get(i);
			}
		}
		return null;
	}


	// LOGICA DE NEGOCIO


	public boolean prestarJuego(String idCliente, int numeroMesa, String nombreJuego)
	{
		Cliente c = buscarCliente(idCliente);
		Mesa m = buscarMesa(numeroMesa);
		JuegoMesa j = buscarJuego(nombreJuego);

		if (c == null || m == null || j == null)
		{
			return false;
		}

		if (m.getSesionActual() == null)
		{
			return false;
		}

		if (c.getEdad() < j.getEdadMinima())
		{
			return false;
		}

		if (!m.puedeAgregarOtroJuego())
		{
			return false;
		}

		for (int i = 0; i < copias.size(); i++)
		{
			CopiaJuego copia = copias.get(i);

			if (copia.getJuego().getNombre().equals(nombreJuego) && copia.isDisponible())
			{
				Prestamo p = new Prestamo(c, m, copia);
				prestamos.add(p);
				m.agregarPrestamo(p);
				copia.setDisponible(false);
				return true;
			}
		}

		return false;
	}

	public void devolverJuego(Prestamo p)
	{
		p.finalizarPrestamo();
		p.getCopia().setDisponible(true);
		p.getMesa().quitarPrestamo(p);
	}

	public int contarCopiasDisponibles(String nombre)
	{
		int cantidad = 0;

		for (int i = 0; i < copias.size(); i++)
		{
			if (copias.get(i).getJuego().getNombre().equals(nombre) && copias.get(i).isDisponible())
			{
				cantidad++;
			}
		}

		return cantidad;
	}

	public int contarPrestamosActivos()
	{
		int cantidad = 0;

		for (int i = 0; i < prestamos.size(); i++)
		{
			if (prestamos.get(i).isActivo())
			{
				cantidad++;
			}
		}

		return cantidad;
	}


	// El toString


	@Override
	public String toString()
	{
		String texto = "BoardgameCafe [";

		if (administrador != null)
		{
			texto += "administrador=" + administrador.getNombre();
		}
		else
		{
			texto += "administrador=Sin administrador";
		}

		texto += ", clientes=" + clientes.size();
		texto += ", empleados=" + empleados.size();
		texto += ", mesas=" + mesas.size();
		texto += ", juegos=" + juegos.size();
		texto += ", copias=" + copias.size();

		if (prestamos.size() > 0)
		{
			texto += ", prestamosTotales=" + prestamos.size();
			texto += ", prestamosActivos=" + contarPrestamosActivos();
		}

		if (productosMenu.size() > 0)
		{
			texto += ", productosMenu=" + productosMenu.size();
		}

		if (ventasCafe.size() > 0)
		{
			texto += ", ventasCafe=" + ventasCafe.size();
		}

		if (ventasJuego.size() > 0)
		{
			texto += ", ventasJuego=" + ventasJuego.size();
		}

		if (solicitudesCambioTurno.size() > 0)
		{
			texto += ", solicitudesCambioTurno=" + solicitudesCambioTurno.size();
		}

		if (sugerenciasPlatillos.size() > 0)
		{
			texto += ", sugerenciasPlatillos=" + sugerenciasPlatillos.size();
		}

		texto += "]";

		return texto;
	}
}