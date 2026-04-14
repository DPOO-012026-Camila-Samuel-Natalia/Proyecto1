package presentacion;

import logica.Administrador;
import logica.Bebida;
import logica.BoardgameCafe;
import logica.Cliente;
import logica.Cocinero;
import logica.CopiaJuego;
import logica.DetalleVentaCafe;
import logica.DetalleVentaJuego;
import logica.JuegoMesa;
import logica.Mesa;
import logica.Mesero;
import logica.Pasteleria;
import logica.Prestamo;
import logica.SolicitudCambioTurno;
import logica.SugerenciaPlatillo;
import logica.VentaCafe;
import logica.VentaJuego;
import persistencia.CentralPersistencia;
import persistencia.PersistenciaBoardGameCafeJSON;

public class Principal
{
	public static void main(String[] args)
	{
		BoardgameCafe cafe = new BoardgameCafe();

		// =========================
		// ADMINISTRADOR
		// =========================
		Administrador admin = new Administrador("A1", "Andres", "admin", "admin123");
		cafe.setAdministrador(admin);
		System.out.println("Administrador del sistema: " + cafe.getAdministrador());

		// =========================
		// CLIENTES
		// =========================
		Cliente c1 = new Cliente("1", "Samuel", "samuel123", "1234", 19);
		Cliente c2 = new Cliente("2", "Laura", "laura456", "abcd", 12);
		cafe.agregarCliente(c1);
		cafe.agregarCliente(c2);

		// =========================
		// EMPLEADOS
		// =========================
		Mesero m1 = new Mesero("10", "Carlos", "carlosm", "1111", "mañana");
		Cocinero co1 = new Cocinero("11", "Pedro", "pedroc", "2222", "tarde", "postres");
		cafe.agregarEmpleado(m1);
		cafe.agregarEmpleado(co1);

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
		JuegoMesa j1 = new JuegoMesa("Catan", 3, 4, 10);
		JuegoMesa j2 = new JuegoMesa("Uno", 2, 10, 5);
		cafe.agregarJuego(j1);
		cafe.agregarJuego(j2);

		// =========================
		// COPIAS
		// =========================
		CopiaJuego copia1 = new CopiaJuego(j1);
		CopiaJuego copia2 = new CopiaJuego(j1);
		CopiaJuego copia3 = new CopiaJuego(j2);
		cafe.agregarCopia(copia1);
		cafe.agregarCopia(copia2);
		cafe.agregarCopia(copia3);

		// =========================
		// SESIÓN DE MESA
		// =========================
		mesa1.iniciarSesion(4, false, true);

		// =========================
		// FASE 1: PRÉSTAMOS
		// =========================
		boolean prestamo1 = cafe.prestarJuego("1", 1, "Catan");
		System.out.println("¿Se pudo prestar Catan a Samuel? " + prestamo1);

		boolean prestamo2 = cafe.prestarJuego("2", 1, "Uno");
		System.out.println("¿Se pudo prestar Uno a Laura? " + prestamo2);

		boolean prestamo3 = cafe.prestarJuego("1", 1, "Catan");
		System.out.println("¿Se pudo prestar un tercer juego en la misma mesa? " + prestamo3);

		System.out.println();
		System.out.println("Estado general del sistema:");
		System.out.println(cafe);

		System.out.println();
		System.out.println("Copias disponibles de Catan: " + cafe.contarCopiasDisponibles("Catan"));
		System.out.println("Copias disponibles de Uno: " + cafe.contarCopiasDisponibles("Uno"));

		System.out.println();
		System.out.println("Préstamos registrados:");
		for (int i = 0; i < cafe.getPrestamos().size(); i++)
		{
			System.out.println(cafe.getPrestamos().get(i));
		}

		if (cafe.getPrestamos().size() > 0)
		{
			Prestamo primerPrestamo = cafe.getPrestamos().get(0);
			cafe.devolverJuego(primerPrestamo);

			System.out.println();
			System.out.println("Se devolvió el primer préstamo.");
			System.out.println(primerPrestamo);
		}

		System.out.println();
		System.out.println("Estado final del sistema:");
		System.out.println(cafe);

		System.out.println();
		System.out.println("Copias disponibles de Catan después de devolver: " + cafe.contarCopiasDisponibles("Catan"));

		// =========================
		// FASE 2: MENÚ Y VENTAS CAFÉ
		// =========================
		Bebida b1 = new Bebida("Cafe Americano", 7000, false, true);
		Bebida b2 = new Bebida("Cerveza", 9000, true, false);
		Pasteleria p1 = new Pasteleria("Brownie", 8000, "postre");
		Pasteleria p2 = new Pasteleria("Galleta", 5000, "galleta");

		cafe.agregarProductoMenu(b1);
		cafe.agregarProductoMenu(b2);
		cafe.agregarProductoMenu(p1);
		cafe.agregarProductoMenu(p2);

		VentaCafe venta1 = new VentaCafe("V1", c1, mesa1, true);
		venta1.agregarDetalle(new DetalleVentaCafe(b1, 2));
		venta1.agregarDetalle(new DetalleVentaCafe(p1, 1));
		venta1.agregarDetalle(new DetalleVentaCafe(p2, 3));
		cafe.agregarVentaCafe(venta1);

		System.out.println();
		System.out.println("Venta del café registrada:");
		System.out.println(venta1);

		System.out.println();
		System.out.println("Detalles de la venta:");
		for (int i = 0; i < venta1.getDetalles().size(); i++)
		{
			System.out.println(venta1.getDetalles().get(i));
		}

		System.out.println();
		System.out.println("Productos del menú registrados:");
		for (int i = 0; i < cafe.getProductosMenu().size(); i++)
		{
			System.out.println(cafe.getProductosMenu().get(i));
		}

		System.out.println();
		System.out.println("Ventas del café registradas:");
		for (int i = 0; i < cafe.getVentasCafe().size(); i++)
		{
			System.out.println(cafe.getVentasCafe().get(i));
		}

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