package clases;

import java.util.Date;

public class Usuario {
	
	private String dni;
	private String nombre;
	private String apel1;
	private String apel2;
	private String dir;
	private String tfno;
	private String email;
	private String numCuenta;
	private String nomUsu;
	private String contraUsu;
	private Float Balance;
	private Date ultimaCon;
	
	public Usuario() {
		super();
		Balance=(float) 0;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApel1() {
		return apel1;
	}

	public void setApel1(String apel1) {
		this.apel1 = apel1;
	}

	public String getApel2() {
		return apel2;
	}

	public void setApel2(String apel2) {
		this.apel2 = apel2;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getTfno() {
		return tfno;
	}

	public void setTfno(String tfno) {
		this.tfno = tfno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumCuenta() {
		return numCuenta;
	}

	public void setNumCuenta(String numCuenta) {
		this.numCuenta = numCuenta;
	}

	public String getNomUsu() {
		return nomUsu;
	}

	public void setNomUsu(String nomUsu) {
		this.nomUsu = nomUsu;
	}

	public String getContraUsu() {
		return contraUsu;
	}

	public void setContraUsu(String contraUsu) {
		this.contraUsu = contraUsu;
	}
	public Float getBalance() {
		return Balance;
	}

	public void setBalance(Float balance) {
		Balance = balance;
	}
	
	public Date getUltimaCon() {
		return ultimaCon;
	}

	public void setUltimaCon(Date ultimaCon) {
		this.ultimaCon = ultimaCon;
	}

	public String toString() {
		return nombre + " "+apel1+" "+apel2;
	}


	
	
}

