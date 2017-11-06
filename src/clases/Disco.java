package clases;

import java.util.ArrayList;
import java.util.Date;



public class Disco {
	
	private int codDisco;
	private String titulo;
	private ArrayList<String> canciones = new ArrayList <String>();
	private String nomAr;
	private ArrayList<Genero> generos = new ArrayList <Genero>();
	private Date fechaPubli;
	private float precio;
	private int stock;
	
	public int getCodDisco() {
		return codDisco;
	}
	public void setCodDisco(int codDisco) {
		this.codDisco = codDisco;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public ArrayList<String> getCanciones() {
		return canciones;
	}
	public void setCanciones(ArrayList<String> canciones) {
		this.canciones = canciones;
	}
	public String getnomAr() {
		return nomAr;
	}
	public void setnomAr(String nomAr) {
		this.nomAr = nomAr;
	}
	public ArrayList<Genero> getGeneros() {
		return generos;
	}
	public void setGeneros(ArrayList<Genero> generos) {
		this.generos = generos;
	}
	public Date getFechaPubli() {
		return fechaPubli;
	}
	public void setFechaPubli(Date fechaPubli) {
		this.fechaPubli = fechaPubli;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public void setDatos() {
		System.out.println(titulo+" "+nomAr+" "+fechaPubli+" "+precio+" "+stock);
	}
	
	




}