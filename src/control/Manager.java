package control;

import java.util.ArrayList;

import clases.Compra;
import clases.Disco;
import clases.Usuario;
import interfaz.interfazManager;

public class Manager implements interfazManager {
	
	
	// Declara un DBManager de la BD elegida
	private DBManagerOracle dbManager=new DBManagerOracle();
	
	public void setDataBase(int numDatabase){
		if(numDatabase==0) {
			dbManager=new DBManagerOracle();
		}
	
	}
	//USUARIO
	public boolean esAdmin(String nombreUsu, String passUsu) {
			// Recibe nombre y contraseña si existe devuelve true
			return dbManager.esAdmin(nombreUsu, passUsu);
		}

		public boolean esUsuario(String nombreUsu, String passUsu) throws Exception{
			// Recibe nombre y contraseña si existe devuelve true
			return dbManager.esUsuario(nombreUsu, passUsu);
		}

	public boolean existeUsuario(String dni, String string) throws Exception {
		// Comprobarque el dni , nombre Usuario o email no esten en uso
		return dbManager.existeUsuario(dni, string);
	}

	public void insertarUsuario(Usuario usu) {
		//Llamar a DBManager para introducir el usuario
		dbManager.insertarUsuario(usu);
		
	}

	public Usuario CargarDatos(String nombreU) {
		// Carga todos los datos del usuario
		Usuario u= new Usuario();
		u=dbManager.CargarDatos(nombreU);
		return u;
	}

	public void ActualizarUsuario(Usuario usua) {
		dbManager.ActualizarUsuario(usua);
	}
	public ArrayList<Usuario> getInactivos() {
		// TODO Auto-generated method stub
		ArrayList<Usuario> x= dbManager.getInactivos();
		
		return x;
	}
	public void borrarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		dbManager.borrarUsuario(usuario);
	}

	
	//DISCOS
	public ArrayList<Disco> getDiscos() {
		// Devuelve todos los discos
		ArrayList<Disco> aux= new ArrayList<Disco>();
		aux= dbManager.getDiscos();
		return aux;
	}
	
	
	public void insertarDisco(Disco x) {
		// Inserta disco en la BD
		dbManager.insertarDisco(x);
	}
	public void borrarDisco(Disco a) {
		dbManager.borrarDisco(a);
	}


	
	
	//COMPRAS
	public ArrayList<Compra> comprasRealizadas(String nombreU) {
		// Devuelve las compras realizadas por un usuario
		ArrayList<Compra> aux= new ArrayList<Compra>();
		aux=dbManager.comprasRealizadas(nombreU);
		return aux;
	}

	public void insertarCompra(Compra com) {
		// TODO Auto-generated method stub
		dbManager.insertarCompra(com);
		
	}
	
	

	




}
