package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Collection;

public class BoardgameCafe
{
	private Administrador administrador;

	private HashMap<String, Cliente> clientes;
	private HashMap<String, Empleado> empleados;
	private ArrayList<Mesa> mesas;
	private ArrayList<JuegoDeMesa> juegos;
	//private ArrayList<CopiaJuego> copias;
	private ArrayList<Prestamo> historialPrestamos;
	private ArrayList<ProductoMenu> productosMenu;
	private ArrayList<VentaCafe> ventasCafe;
	private ArrayList<VentaJuego> ventasJuego;
	private ArrayList<SolicitudCambioTurno> solicitudesCambioTurno;
	private ArrayList<SugerenciaPlatillo> sugerenciasPlatillos;
	private int capacidadMaxima;
	private int clientesActuales;
	private ArrayList<Turno> turnos;

	
	public BoardgameCafe(int capacidadMaxima)
	{
		this.capacidadMaxima = capacidadMaxima;
		this.clientesActuales = 0;
		administrador = null;
		clientes = new HashMap<String, Cliente>();
		empleados = new HashMap<String, Empleado>();
		mesas = new ArrayList<Mesa>();
		juegos = new ArrayList<JuegoDeMesa>();
		//copias = new ArrayList<CopiaJuego>();
		historialPrestamos = new ArrayList<Prestamo>();
		productosMenu = new ArrayList<ProductoMenu>();
		ventasCafe = new ArrayList<VentaCafe>();
		ventasJuego = new ArrayList<VentaJuego>();
		solicitudesCambioTurno = new ArrayList<SolicitudCambioTurno>();
		sugerenciasPlatillos = new ArrayList<SugerenciaPlatillo>();
		turnos = new ArrayList<Turno>();
	}


	// GETTERS

	
	
	public Administrador getAdministrador()
	{
		return administrador;
	}

	public int getCapacidadMaxima() {
		return capacidadMaxima;
	}


	public int getClientesActuales() {
		return clientesActuales;
	}


	public Collection<Cliente> getClientes() {
	    return clientes.values();  // retorna todos los clientes
	}

	public Collection<Empleado> getEmpleados() {
	    return empleados.values();  // retorna todos los empleados
	}

	public ArrayList<Mesa> getMesas()
	{
		return mesas;
	}

	public ArrayList<JuegoDeMesa> getCatalogoJuegos()
	{
		return juegos;
	}

	//public ArrayList<CopiaJuego> getCopias()
	//{
	//	return copias;
	//}

	public ArrayList<Prestamo> getHistorialPrestamos()
	{
		return historialPrestamos;
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
	public ArrayList<Turno> getTurnos() { 
		return turnos; }
	

	// SETTERS

	public void setAdministrador(Administrador administrador)
	{
		this.administrador = administrador;
	}


	// AGREGAR


	public void agregarCliente(Cliente c) {
	    clientes.put(c.getLogin(), c);
	}


	public void agregarEmpleado(Empleado e) {
	    empleados.put(e.getLogin(), e);
	}

	public void agregarMesa(Mesa m)
	{
		mesas.add(m);
	}

	public void agregarJuego(JuegoDeMesa j)
	{
		juegos.add(j);
	}

	//public void agregarCopia(CopiaJuego c)
	//{
		//copias.add(c);
	//}

	public void agregarPrestamoDirecto(Prestamo p)
	{
		historialPrestamos.add(p);
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


	public Cliente buscarCliente(String login) {
	    return clientes.get(login);  // retorna null si no existe
	}

	public Empleado buscarEmpleado(String login) {
	    return empleados.get(login);
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

	public JuegoDeMesa buscarJuego(String nombre)
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
	
	public ArrayList<Prestamo> getPrestamosActivos() {
	    ArrayList<Prestamo> activos = new ArrayList<>();
	    for (Prestamo p : historialPrestamos) {
	        if (p.isActivo()) activos.add(p);
	    }
	    return activos;
	}

	// LOGICA DE NEGOCIO
	
	
	// Prestar juegos
	
	public Prestamo crearPrestamo(PuedePrestar solicitante, JuegoDeMesa juego) {

	    if (!solicitante.puedePedirPrestamo())
	        throw new IllegalStateException("No puede pedir prestamo");

	    if (!juego.estaDisponiblePrestamo())
	        throw new IllegalStateException("Juego no disponible");

	    if (solicitante instanceof Cliente) {
	        Mesa mesa = ((Cliente) solicitante).getMesaActual();
	        
	        if (juego.soloAdultos() && mesa.hayMenoresDeEdad())
	            throw new IllegalStateException("Juego no apto para menores");
	        
	        if (juego.getEdadMinima() > 0 && mesa.hayMenoresDeEdad())
	            throw new IllegalStateException("Juego no apto por edad minima");

	        if (mesa.getNumPersonas() < juego.getMinJugadores())
	            throw new IllegalStateException("Pocas personas para este juego");
	        
	        if (mesa.getNumPersonas() > juego.getMaxJugadores())
	            throw new IllegalStateException("Demasiadas personas para este juego");
	    }
	    
	    if (juego.isDificil()) {
	        boolean hayMesero = false;
	        for (Empleado e : empleados.values()) {
	            if (e instanceof Mesero && e.isEnTurno()) {
	                if (((Mesero) e).dominaJuego(juego.getNombre())) {
	                    hayMesero = true;
	                }
	            }
	        }
	        if (!hayMesero)
	            throw new IllegalStateException("No hay mesero que domine este juego dificil");
	    }
	    Prestamo p = new Prestamo((Usuario) solicitante, juego);
	    juego.prestar();
	    solicitante.agregarJuegoPrestado(juego);
	    historialPrestamos.add(p);
	    return p;
	}

	public void devolverPrestamo(Prestamo prestamo) {
	    
	    if (!prestamo.isActivo())
	        throw new IllegalStateException("Prestamo ya fue devuelto");
	    prestamo.getJuego().devolver();
	    ((PuedePrestar) prestamo.getSolicitante()).quitarJuegoPrestado(prestamo.getJuego());
	    prestamo.cerrar();
	}

	// Juegos disponibles para prestar
	public List<JuegoDeMesa> getJuegosDisponiblesPrestamo() {
	    List<JuegoDeMesa> disponibles = new ArrayList<>();
	    for (JuegoDeMesa j : juegos) {
	        if (j.estaDisponiblePrestamo()) disponibles.add(j);
	    }
	    return disponibles;
	}

	// Juegos disponibles para vender
	public List<JuegoDeMesa> getJuegosDisponiblesVenta() {
	    List<JuegoDeMesa> disponibles = new ArrayList<>();
	    for (JuegoDeMesa j : juegos) {
	        if (j.estaDisponibleVenta()) disponibles.add(j);
	    }
	    return disponibles;
	}
	// Mesas
	
    public Mesa getMesaDisponible() {
        for (Mesa m : mesas) {
            if (!m.ocupada()) return m;
        }
        return null;
    }

    public void recibirCliente(Cliente cliente, int numPersonas,
                               boolean ninos, boolean menoresDeEdad) {
        if (clientesActuales + numPersonas > capacidadMaxima)
            throw new IllegalStateException("Cafe lleno");

        Mesa mesa = getMesaDisponible();
        if (mesa == null)
            throw new IllegalStateException("No hay mesas disponibles");

        if (numPersonas > mesa.getCapacidad())
            throw new IllegalStateException("Supera capacidad de la mesa");

        mesa.ocupar(numPersonas, ninos, menoresDeEdad);
        cliente.asignarMesa(mesa);
        clientesActuales += numPersonas;
    }

    public void retirarCliente(Cliente cliente) {
        if (!cliente.estaEnCafe())
            throw new IllegalStateException("Cliente no esta en el cafe");

        // Devolver préstamos activos
        for (Prestamo p : historialPrestamos) {
            if (p.isActivo() && p.getSolicitante().equals(cliente)) {
                devolverPrestamo(p);
            }
        }

        clientesActuales -= cliente.getMesaActual().getNumPersonas();
        cliente.getMesaActual().liberar();
        cliente.liberarMesa();
    }

	
	// Ventas cafeteria
    public VentaCafe registrarVentaCafe(Cliente cliente, boolean cobraPropina) {
        if (!cliente.estaEnCafe())
            throw new IllegalStateException("Cliente no tiene mesa asignada");

        int numeroMesa = cliente.getMesaActual().getNumero();
        String codigo = "VC-" + numeroMesa + "-" + (ventasCafe.size() + 1);
        VentaCafe venta = new VentaCafe(codigo, cliente, cobraPropina);
        ventasCafe.add(venta);
        return venta;
    }

    public void agregarProductoAVenta(VentaCafe venta, ProductoMenu producto, int cantidad) {
        if (venta.isCerrada())
            throw new IllegalStateException("Venta ya cerrada");

        Mesa mesa = venta.getMesa();

        if (producto instanceof Bebida) {
            Bebida bebida = (Bebida) producto;
            if (bebida.isAlcoholica() && mesa.hayMenoresDeEdad())
                throw new IllegalStateException("No se puede servir alcohol con menores");
            if (bebida.isCaliente() && venta.getCliente().tieneJuegoAccion())
                throw new IllegalStateException("No se puede servir bebida caliente con juego de accion");
        }

        venta.agregarDetalle(new DetalleVentaCafe(producto, cantidad));
    }

    public void cerrarVentaCafe(VentaCafe venta) {
        if (venta.isCerrada())
            throw new IllegalStateException("Venta ya fue cerrada");
        if (venta.getDetalles().isEmpty())
            throw new IllegalStateException("La venta no tiene productos");

        venta.cerrar();
        venta.getCliente().agregarPuntos((int)(venta.calcularTotal() * 0.01));
    }

    public VentaCafe getVentaActivaCliente(Cliente cliente) {
        for (VentaCafe v : ventasCafe) {
            if (v.getComprador().equals(cliente) && !v.isCerrada())
                return v;
        }
        return null;
    }

  
	
	//Ventas Juegos
    
    public void aplicarCodigoDescuento(Cliente cliente, String codigo) {
        // Buscar el empleado que tiene ese código
        for (Empleado e : empleados.values()) {
            if (e.getCodigoDescuento().equals(codigo)) {
                cliente.setCodigoDescuento(codigo);
                return;
            }
        }
        throw new IllegalStateException("Codigo de descuento invalido");
    }
	
    public VentaJuego registrarVentaJuego(Usuario comprador, JuegoDeMesa juego, int cantidad) {
        if (!juego.estaDisponibleVenta())
            throw new IllegalStateException("No hay copias disponibles para venta");
        if (cantidad > juego.getCopiasVenta())
            throw new IllegalStateException("No hay suficientes copias");

        String codigo = "VJ-" + (ventasJuego.size() + 1);
        VentaJuego venta = new VentaJuego(codigo, comprador);
        venta.agregarDetalle(new DetalleVentaJuego(juego, cantidad));
        
     // Calcular descuento
        if (comprador instanceof Empleado) {
            venta.setDescuento(venta.calcularSubtotal() * 0.20);
        } else if (comprador instanceof Cliente) {
            Cliente c = (Cliente) comprador;
            if (c.tieneDescuento()) {
                // 10% de descuento por código de empleado
                venta.setDescuento(venta.calcularSubtotal() * 0.10);
            }
        }

        for (int i = 0; i < cantidad; i++) juego.vender();

        ventasJuego.add(venta);
        venta.cerrar();

        if (comprador instanceof Cliente)
            ((Cliente) comprador).agregarPuntos((int)(venta.calcularTotal() * 0.01));

        return venta;
    }
	
	
	// Empleados y turnos
	
    public void agregarTurno(Turno turno) {
        turnos.add(turno);
    }

    public void asignarTurno(Empleado empleado, Turno turno) {
        turno.agregarEmpleado(empleado);
        empleado.agregarTurno(turno);
    }

    public void quitarTurno(Empleado empleado, Turno turno) {
        turno.quitarEmpleado(empleado);
        empleado.quitarTurno(turno);
    }

    // Cambio general — empleado pide un turno nuevo
    public SolicitudCambioTurno solicitarCambioGeneral(Empleado empleado,Turno turnoQueQuiereQuitar,Turno turnoQueDesea,String motivo) {
		// Verificar que el empleado tenga ese turno
		if (!empleado.getTurnos().contains(turnoQueQuiereQuitar)) throw new IllegalStateException("El empleado no tiene ese turno");
		
		String codigo = "SCT-" + (solicitudesCambioTurno.size() + 1);
		SolicitudCambioTurno solicitud = new SolicitudCambioTurno(codigo, empleado,turnoQueQuiereQuitar,turnoQueDesea, motivo);
		solicitudesCambioTurno.add(solicitud);
		return solicitud;
	}

    // Intercambio — empleado quiere el turno de otro
	public SolicitudCambioTurno solicitarIntercambioTurno(Empleado solicitante,Empleado otroEmpleado,Turno turnoQueOfrece,Turno turnoQueDesea,String motivo) {
		// Verificar que el solicitante tenga ese turno
		if (!solicitante.getTurnos().contains(turnoQueOfrece))throw new IllegalStateException("El empleado no tiene ese turno");
		
		// Verificar que el otro empleado tenga el turno deseado
		if (!otroEmpleado.getTurnos().contains(turnoQueDesea))throw new IllegalStateException("El otro empleado no tiene ese turno");
		
		String codigo = "SCT-" + (solicitudesCambioTurno.size() + 1);
		SolicitudCambioTurno solicitud = new SolicitudCambioTurno(codigo, solicitante,otroEmpleado,turnoQueOfrece,turnoQueDesea, motivo);
		solicitudesCambioTurno.add(solicitud);
		return solicitud;
    }

	public void aprobarCambioGeneral(SolicitudCambioTurno solicitud) {
	    if (!solicitud.getEstado().equals("Pendiente"))
	        throw new IllegalStateException("Solicitud ya fue procesada");

	    // Verificar que queden los minimos
	    if (!solicitud.getTurnoQueOfrece()
	                  .tienePersonalMinimoSin(solicitud.getEmpleadoSolicitante()))
	        throw new IllegalStateException("No se puede aprobar, quedaría poco personal");

	    solicitud.aprobar();
	}

	public void aprobarIntercambioTurno(SolicitudCambioTurno solicitud) {
	    if (!solicitud.getEstado().equals("Pendiente"))
	        throw new IllegalStateException("Solicitud ya fue procesada");

	    // Verificar que queden minimos en ambos turnos
	    if (!solicitud.getTurnoQueOfrece()
	                  .tienePersonalMinimoSin(solicitud.getEmpleadoSolicitante()))
	        throw new IllegalStateException("No se puede aprobar, quedaría poco personal en turno ofrecido");

	    if (!solicitud.getTurnoSolicitado()
	                  .tienePersonalMinimoSin(solicitud.getEmpleadoIntercambio()))
	        throw new IllegalStateException("No se puede aprobar, quedaría poco personal en turno deseado");

	    solicitud.aprobar();
	}

    public void rechazarCambioTurno(SolicitudCambioTurno solicitud) {
        if (!solicitud.getEstado().equals("Pendiente"))
            throw new IllegalStateException("Solicitud ya fue procesada");
        solicitud.rechazar();
    }

 
    // Ver solicitudes pendientes
    public ArrayList<SolicitudCambioTurno> getSolicitudesPendientes() {
        ArrayList<SolicitudCambioTurno> pendientes = new ArrayList<>();
        for (SolicitudCambioTurno s : solicitudesCambioTurno) {
            if (s.getEstado().equals("Pendiente")) pendientes.add(s);
        }
        return pendientes;
    }
	
	
	
	// Administrador e informes
	
	 public void moverVentaAPrestamo(JuegoDeMesa juego) {
	     if (juego.getCopiasVenta() == 0)
	         throw new IllegalStateException("No hay copias en venta");
	     juego.moverVentaAPrestamo();
	 }


	 // Aprobar o rechazar sugerencia de platillo
	public void aprobarSugerencia(SugerenciaPlatillo sugerencia, String tipo, double precio) {
		if (!sugerencia.getEstado().equals("Pendiente"))throw new IllegalStateException("Sugerencia ya fue procesada");
		
		ProductoMenu nuevo = sugerencia.crearProducto(tipo, precio);
		productosMenu.add(nuevo);
		sugerencia.aprobar();
	}
	
	public void rechazarSugerencia(SugerenciaPlatillo sugerencia) {
		if (!sugerencia.getEstado().equals("Pendiente"))
		throw new IllegalStateException("Sugerencia ya fue procesada");
		sugerencia.rechazar();
	}

	 // Empleado crea sugerencia platillo
	 public SugerenciaPlatillo crearSugerencia(Empleado empleado, String nombrePlatillo, String descripcion) {
	String codigo = "SP-" + (sugerenciasPlatillos.size() + 1);
	SugerenciaPlatillo s = new SugerenciaPlatillo(codigo, empleado, nombrePlatillo, descripcion);
	sugerenciasPlatillos.add(s);
	return s;
	 }
	 
	 
	 //INFORMES

	// Total ventas cafetería
	public double getTotalVentasCafe() {
	    double total = 0;
	    for (VentaCafe v : ventasCafe) {
	        if (v.isCerrada()) total += v.calcularTotal();
	    }
	    return total;
	}

	// Total ventas juegos
	public double getTotalVentasJuego() {
	    double total = 0;
	    for (VentaJuego v : ventasJuego) {
	        total += v.calcularTotal();
	    }
	    return total;
	}

	// Total general
	public double getTotalVentas() {
	    return getTotalVentasCafe() + getTotalVentasJuego();
	}

	// Ventas por rubro separadas
	public String getInformeVentas() {
	    return "=== INFORME DE VENTAS ===" 
	        + "\nVentas cafeteria:  $" + getTotalVentasCafe()
	        + "\nVentas juegos:     $" + getTotalVentasJuego()
	        + "\nTotal:             $" + getTotalVentas()
	        + "\nNum ventas cafe:   " + ventasCafe.size()
	        + "\nNum ventas juegos: " + ventasJuego.size();
	}

	// Historial de préstamos de un juego específico
	public ArrayList<Prestamo> getHistorialPrestamosJuego(JuegoDeMesa juego) {
	    ArrayList<Prestamo> resultado = new ArrayList<>();
	    for (Prestamo p : historialPrestamos) {
	        if (p.getJuego().equals(juego)) resultado.add(p);
	    }
	    return resultado;
	}
	// Historial de prestamos completo es getHistorialPrestamos
	
	
	// Usuarios
	
	

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

		if (historialPrestamos.size() > 0)
		{
			texto += ", prestamosTotales=" + historialPrestamos.size();
			texto += ", prestamosActivos=" + getPrestamosActivos().size();
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