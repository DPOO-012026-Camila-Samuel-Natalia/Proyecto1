package presentacion;

import modelo.Administrador;
import modelo.Bebida;
import modelo.BoardgameCafe;
import modelo.Cliente;
import modelo.Cocinero;
import modelo.DetalleVentaCafe;
import modelo.DetalleVentaJuego;
import modelo.JuegoDeMesa;
import modelo.Mesa;
import modelo.Mesero;
import modelo.Pasteleria;
import modelo.Prestamo;
import modelo.SolicitudCambioTurno;
import modelo.SugerenciaPlatillo;
import modelo.VentaCafe;
import modelo.VentaJuego;
import modelo.Empleado;
import modelo.PuedePrestar;
import modelo.Usuario;
import modelo.Turno;
import modelo.ProductoMenu;

import persistencia.CentralPersistencia;
import persistencia.PersistenciaBoardGameCafeJSON;

public class Principal
{
	public static void main(String[] args)
	{
		BoardgameCafe cafe = new BoardgameCafe(20);

		// =========================
		// ADMINISTRADOR
		// =========================
		Administrador admin = new Administrador("A1", "Andres", "admin", "admin123");
		cafe.setAdministrador(admin);
		System.out.println("Administrador del sistema: " + cafe.getAdministrador());

		// =========================
		// CLIENTES
		// =========================
		Cliente c1 = new Cliente("1", "Samuel", "samuel123", "1234");
		Cliente c2 = new Cliente("2", "Laura", "laura456", "abcd");
		Cliente c3 = new Cliente("2", "Sofia", "sof1", "hola");

		cafe.agregarCliente(c1);
		cafe.agregarCliente(c2);
		cafe.agregarCliente(c3);

		// =========================
		// EMPLEADOS
		// =========================
		Mesero m1 = new Mesero("10", "Carlos", "carlosm", "1111");
		Cocinero co1 = new Cocinero("11", "Pedro", "pedroc", "2222");
		Mesero m2 = new Mesero("11", "Ana", "anam", "2222");
		cafe.agregarEmpleado(m1);
		cafe.agregarEmpleado(m2);
		cafe.agregarEmpleado(co1);
		m1.agregarJuegoQueExplica("Catan");
		
		

		// =========================
		// MESAS
		// =========================
		Mesa mesa1 = new Mesa(1, 4);
		Mesa mesa2 = new Mesa(2, 6);
		cafe.agregarMesa(mesa1);
		cafe.agregarMesa(mesa2);

		// =========================
		// JUEGOS
		// =========================
		JuegoDeMesa j1 = new JuegoDeMesa("Catan", 2000, "Hasbro", 3, 6, 10, "TABLERO", true, 1, 10, 50000);
		JuegoDeMesa j2 = new JuegoDeMesa("Uno",1980, "Mattel", 2, 10, 5, "CARTAS", false, 6, 5, 30000);
		JuegoDeMesa j3 = new JuegoDeMesa("Twister",1995, "Mattel", 2, 4, 5, "ACCION", false, 6, 5, 40000);
		cafe.agregarJuego(j1);
		cafe.agregarJuego(j2);
		cafe.agregarJuego(j3);

		
		
		
		// =========================
		// TURNOS
		// =========================
		Turno lunes = new Turno("LUNES");
		Turno martes = new Turno("MARTES");
		Turno miercoles = new Turno("MIERCOLES");
		Turno jueves = new Turno("JUEVES");
		Turno viernes = new Turno("VIERNES");
		Turno sabado = new Turno("SABADO");
		Turno domingo = new Turno("DOMINGO");
		
		cafe.agregarTurno(lunes);
		cafe.agregarTurno(martes);
		cafe.agregarTurno(miercoles);
		cafe.agregarTurno(jueves);
		cafe.agregarTurno(viernes);
		cafe.agregarTurno(sabado);
		cafe.agregarTurno(domingo);



		// =========================
		// TURNOS ASIGNAR E INICIAR
		// =========================
		cafe.asignarTurno(m1, lunes);
		cafe.asignarTurno(m2, lunes);
		cafe.asignarTurno(co1, lunes);
		m1.iniciarTurno();
		m2.iniciarTurno();
		co1.iniciarTurno();
		
		
		// =========================
		// PRODUCTOS MENÚ
		// =========================
		Bebida b1 = new Bebida("Cafe Americano", 7000, false, true);
		Bebida b2 = new Bebida("Cerveza", 9000, true, false);
		Pasteleria pa1 = new Pasteleria("Brownie", 8000);
		Pasteleria pa2 = new Pasteleria("Galleta", 5000);
		pa1.agregarAlergeno("gluten");

		cafe.agregarProductoMenu(b1);
		cafe.agregarProductoMenu(b2);
		cafe.agregarProductoMenu(pa1);
		cafe.agregarProductoMenu(pa2);

		System.out.println("Productos del menu:");
		for (ProductoMenu p : cafe.getProductosMenu()) {
		    System.out.println(p);
		}
		
		
		
		// =========================
		// FASE 1: PRÉSTAMOS
		// =========================
		System.out.println("\n===== FASE 1: PRÉSTAMOS =====");
		
		// Recibir clientes
		try {
		    cafe.recibirCliente(c1, 3, false, false);
		    System.out.println(c1.getNombre()+ "recibido en mesa: " + c1.getMesaActual().getNumero());
		} catch (IllegalStateException e) {
		    System.out.println("Error: " + e.getMessage());
		}

		try {
		    cafe.recibirCliente(c2, 2, true, false);
		    System.out.println(c2.getNombre()+"recibida en mesa: " + c2.getMesaActual().getNumero());
		} catch (IllegalStateException e) {
		    System.out.println("Error: " + e.getMessage());
		}
		
		System.out.println("\nCatálogo juegos disponibles para préstamo");
		for (JuegoDeMesa j : cafe.getJuegosDisponiblesPrestamo()) {
		    System.out.println(j);
		}
		
		// Realizar prestamos
		Prestamo pre1 = null;
		
		try {
		    pre1 = cafe.crearPrestamo(c1, j1);
		    System.out.println("Prestamo creado: " + pre1);
		    System.out.println("Copias disponibles Catan: " + j1.getTotalCopiasPrestamo());
		} catch (IllegalStateException e) {
		    System.out.println("Error: " + e.getMessage());
		}
		
		try {
		    Prestamo pre2 = cafe.crearPrestamo(c1, j2);
		    System.out.println("Segundo prestamo creado: " + pre2);
		} catch (IllegalStateException e) {
		    System.out.println("Error: " + e.getMessage());
		}
		
		
		System.out.println("\n Prueba error: prestar un tercer juego");
		try {
		    Prestamo pre3 = cafe.crearPrestamo(c1, j3);
		    System.out.println("Debio haber fallado");
		} catch (IllegalStateException e) {
		    System.out.println("Error esperado: " + e.getMessage());
		}
		
		System.out.println("\n Prueba error: juego no disponible");
		try {
		    cafe.crearPrestamo(c2, j1);  // todas las copias de Catan prestadas
		    System.out.println("Debio haber fallado");
		} catch (IllegalStateException e) {
		    System.out.println("Error esperado: " + e.getMessage());
		}

		// ERROR ESPERADO: cliente sin mesa
		System.out.println("\n Prueba error: cliente sin mesa ");
		try {
		    Cliente c4 = new Cliente("4", "Pedro", "pedro123", "perro");
		    Prestamo pre4 = cafe.crearPrestamo(c4, j2);
		    System.out.println("Debio haber fallado");
		} catch (IllegalStateException e) {
		    System.out.println("Error esperado: " + e.getMessage());
		}
		
		
		

		System.out.println();
		System.out.println("Estado general del sistema:");
		System.out.println(cafe);

		System.out.println();
		System.out.println("Copias disponibles de Catan: " + j1.getTotalCopiasPrestamo());
		System.out.println("Copias disponibles de Uno: " + j2.getTotalCopiasPrestamo());

		System.out.println();
		
		// Ver préstamos activos
				System.out.println("\n-- Préstamos activos --");
				for (Prestamo p : cafe.getPrestamosActivos()) {
				    System.out.println(p);
				}
				
		// Retirar cliente
		cafe.retirarCliente(c1);
		System.out.println("Cliente retirado");
		System.out.println(" Prestamo activo: " + pre1.isActivo());  // false
		System.out.println("  Copias disponibles Catan después de devolver: " + j1.getTotalCopiasPrestamo()); 
		System.out.println();
		
		System.out.println("Estado final del sistema:");
		System.out.println(cafe);

		
		
		// =========================
		// FASE 2: MENÚ Y VENTAS CAFÉ
		// =========================
		
		System.out.println("\n---- FASE 2: VENTAS CAFETERÍA ----");
		
		
		System.out.println("Venta cafe --");
		VentaCafe venta1 = null;
		try {
		    venta1 = cafe.registrarVentaCafe(c2, true);
		    cafe.agregarProductoAVenta(venta1, b1, 2);  // 2 cafes
		    cafe.agregarProductoAVenta(venta1, pa1, 1);  // 1 brownie
		    cafe.cerrarVentaCafe(venta1);
		    System.out.println("Venta cerrada: " + venta1);
		    System.out.println("Puntos de Laura: " + c2.getPuntosFidelidad());
		} catch (IllegalStateException e) {
		    System.out.println("Error: " + e.getMessage());
		}

		System.out.println();
		System.out.println("Venta del café registrada:");
		System.out.println(venta1);

		// ERROR ESPERADO: alcohol con menores
		System.out.println("Prueba error: alcohol con menores --");
		try {
		    VentaCafe ventaMenores = cafe.registrarVentaCafe(c2, false);
		    cafe.agregarProductoAVenta(ventaMenores, b2, 1);  // cerveza
		    System.out.println("Debio haber fallado");
		} catch (IllegalStateException e) {
		    System.out.println("Error esperado: " + e.getMessage());
		}

		// ERROR ESPERADO: bebida caliente con juego de accion
		System.out.println("Prueba error: bebida caliente con juego accion --");
		try {
		    Prestamo pAccion = cafe.crearPrestamo(c2, j3);  // j3 es ACCION
		    VentaCafe ventaAccion = cafe.registrarVentaCafe(c2, false);
		    cafe.agregarProductoAVenta(ventaAccion, b1, 1);  // cafe caliente
		    System.out.println("Debio haber fallado");
		} catch (IllegalStateException e) {
		    System.out.println("Error esperado: " + e.getMessage());
		}

		// ERROR ESPERADO: cliente sin mesa
		System.out.println("Prueba error: cliente sin mesa --");
		try {
		    cafe.registrarVentaCafe(c3, true);
		    System.out.println("Debio haber fallado");
		} catch (IllegalStateException e) {
		    System.out.println("Error esperado: " + e.getMessage());
		}
		
		
		
		
		System.out.println();
		System.out.println("Productos del menú registrados:");
		for (int i = 0; i < cafe.getProductosMenu().size(); i++)
		{
			System.out.println(cafe.getProductosMenu().get(i));
		}

		System.out.println();
		System.out.println("Ventas del café registradas:");
		for (VentaCafe v : cafe.getVentasCafe()) {
		    System.out.println(v);
		}
		
		System.out.println("\n-- Informe ventas cafeteria --");
		System.out.println("Total ventas cafe: $" + cafe.getTotalVentasCafe());
		
		

		// =========================
		// FASE 3: TURNOS Y SUGERENCIAS
		// =========================
		SolicitudCambioTurno solicitud1 = new SolicitudCambioTurno("S1", m1, "tarde", "Necesito cambiar horario");
		cafe.agregarSolicitudCambioTurno(solicitud1);
		solicitud1.aprobar();

		System.out.println();
		System.out.println("Solicitud de cambio de turno:");
		System.out.println(solicitud1);
		System.out.println("Nuevo turno del mesero: " + m1.getTurno());

		SugerenciaPlatillo sugerencia1 = new SugerenciaPlatillo("SP1", co1, "Cheesecake", "Postre frío con salsa de frutos rojos");
		cafe.agregarSugerenciaPlatillo(sugerencia1);
		sugerencia1.aprobar();

		System.out.println();
		System.out.println("Sugerencia de platillo:");
		System.out.println(sugerencia1);

		System.out.println();
		System.out.println("Solicitudes de cambio de turno registradas:");
		for (int i = 0; i < cafe.getSolicitudesCambioTurno().size(); i++)
		{
			System.out.println(cafe.getSolicitudesCambioTurno().get(i));
		}

		System.out.println();
		System.out.println("Sugerencias de platillos registradas:");
		for (int i = 0; i < cafe.getSugerenciasPlatillos().size(); i++)
		{
			System.out.println(cafe.getSugerenciasPlatillos().get(i));
		}

		// =========================
		// FASE 4: VENTAS DE JUEGOS
		// =========================
		VentaJuego ventaJuego1 = new VentaJuego("VG1", c1);
		ventaJuego1.agregarDetalle(new DetalleVentaJuego(j1, 1, 120000));
		ventaJuego1.agregarDetalle(new DetalleVentaJuego(j2, 2, 30000));
		cafe.agregarVentaJuego(ventaJuego1);

		System.out.println();
		System.out.println("Venta de juegos registrada:");
		System.out.println(ventaJuego1);

		System.out.println();
		System.out.println("Detalles de la venta de juegos:");
		for (int i = 0; i < ventaJuego1.getDetalles().size(); i++)
		{
			System.out.println(ventaJuego1.getDetalles().get(i));
		}

		System.out.println();
		System.out.println("Ventas de juegos registradas:");
		for (int i = 0; i < cafe.getVentasJuego().size(); i++)
		{
			System.out.println(cafe.getVentasJuego().get(i));
		}

		// =========================
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
}