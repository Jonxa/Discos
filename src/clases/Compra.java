package clases;

import java.util.ArrayList;
import java.util.Date;


public class Compra {
	
	private String IDCliente; //DNI
	private Date fechaCompra;
	private ArrayList<LineaCompra> Compras = new ArrayList<LineaCompra>();
	
	public String getIDCliente() {
		return IDCliente;
	}
	public void setIDCliente(String iDCliente) {
		IDCliente = iDCliente;
	}
	public Date getFechaCompra() {
		return fechaCompra;
	}
	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
	public ArrayList<LineaCompra> getCompras() {
		return Compras;
	}
	public void setCompras(ArrayList<LineaCompra> compras) {
		Compras = compras;
	}
	public int tamñoCompra() {
		return Compras.size();
	}
	public float getImporte() {
		float x=0;
		for (int i = 0; i < Compras.size(); i++) {
			x= x +(Compras.get(i).getPrecio()*Compras.get(i).getCantidad());
		}
		return x;
	}
	
	
}