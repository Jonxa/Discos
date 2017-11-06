package interfaz;

import java.util.ArrayList;

import clases.Compra;
import clases.Disco;
import clases.Usuario;

public abstract interface interfazManager {
	
	//USUARIO
	abstract boolean esAdmin(String nombreUsu, String passUsu);
	abstract boolean esUsuario(String nombreUsu, String passUsu) throws Exception;
	abstract boolean existeUsuario(String dni, String string) throws Exception;
	abstract void insertarUsuario(Usuario usu);
	abstract Usuario CargarDatos(String nombreU);
	abstract void ActualizarUsuario(Usuario usua);
	abstract  ArrayList<Usuario> getInactivos();
	abstract void borrarUsuario(Usuario usuario);
	
	//DISCOS
	abstract ArrayList<Disco> getDiscos();
	abstract  void insertarDisco(Disco x) ;
	abstract void borrarDisco(Disco a);
	
	//COMPRAS
	abstract ArrayList<Compra> comprasRealizadas(String nombreU);
	abstract void insertarCompra(Compra com);
}
