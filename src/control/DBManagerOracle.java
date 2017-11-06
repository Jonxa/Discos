package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import clases.Compra;
import clases.Disco;
import clases.Genero;
import clases.LineaCompra;
import clases.Usuario;
import oracle.sql.ARRAY;

public class DBManagerOracle extends DBManager{


		private Connection con;
		
		//Conexiones
		public void open(){  
		try{  
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "dam1718", "dam17");
		}catch(ClassNotFoundException e){
			System.out.println("Falta el JDBC Driver");
		}  catch (SQLException e) {
			  e.printStackTrace();
		}
		  
		}  
		public void close() {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//Usuarios
		public boolean esAdmin(String nombreUsu, String passUsu) {
			boolean z=false;
			this.open();
			String sql = "SELECT * FROM TAdministrador WHERE nomAdmin = '"+nombreUsu+"' AND contraAdmin = '"+passUsu+"'";
			try {
				PreparedStatement select = con.prepareStatement(sql);
				System.out.println(sql);
				 ResultSet rs = select.executeQuery(sql);
				 
				 if (rs.next()) {                           
					 z=true;
				 }else if(!rs.next()){
					 z=false;
				 }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			
			this.close();
			return z;
		}
		
		public boolean esUsuario(String nombreUsu, String passUsu) {
			boolean z=false;
			this.open();
			String sql ="SELECT * FROM TUsuario WHERE nomUsu = '"+nombreUsu+"' AND contraUsu = '"+passUsu+"'";
			try {
				PreparedStatement select = con.prepareStatement(sql);
				System.out.println(sql);
				ResultSet rs = select.executeQuery(sql);
				 if (rs.next()) {                           
					 z=true;
				 }else if(!rs.next()){
					 z=false;
				 }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			
			this.close();
			return z;
		}
		//Registro
		public boolean existeUsuario(String dni,String nomUsu) throws Exception {
			boolean z=false;
			this.open();
			String sql ="SELECT * FROM TUsuario WHERE dni = '"+dni+"' OR nomUsu = '"+nomUsu+"'";
			try {
				PreparedStatement select = con.prepareStatement(sql);
				System.out.println(sql);
				ResultSet rs = select.executeQuery(sql);
				 if (rs.next()) {                           
					 z=false;
				 }else if(!rs.next()){
					 z=true;
				 }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			
			this.close();
			return z;
		}
		//Usuario
		public void insertarUsuario(Usuario usu){
		
			this.open();
			String sql ="INSERT INTO TUsuario VALUES ('"+usu.getDni()+"','"+usu.getNomUsu()+"','"+usu.getContraUsu()+"','"+usu.getNombre()+"','"+usu.getApel1()+"','"+usu.getApel2()+"','"+usu.getDir()+"','"+usu.getTfno()+"','"+usu.getEmail()+"','"+usu.getNumCuenta()+"',0,SYSDATE)";
			try {
				PreparedStatement insert = con.prepareStatement(sql);
				System.out.println(sql);
				insert.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			
			this.close();
		}
		
		public Usuario CargarDatos(String nombreU){
			this.open();
			Usuario u=new Usuario();
			String sql ="SELECT * FROM TUsuario WHERE nomUsu = '"+nombreU+"'";
			try {
				PreparedStatement select = con.prepareStatement(sql);
				System.out.println(sql);
				ResultSet rs = select.executeQuery(sql);
				if(rs.next()) {
					u.setDni(rs.getString(1));
					u.setNomUsu(rs.getString(2));
					u.setContraUsu(rs.getString(3));
					u.setNombre(rs.getString(4));
					u.setApel1(rs.getString(5));
					u.setApel2(rs.getString(6));
					u.setDir(rs.getString(7));
					u.setTfno(rs.getString(8));
					u.setEmail(rs.getString(9));
					u.setNumCuenta(rs.getString(10));
					u.setBalance(rs.getFloat(11));
					u.setUltimaCon(rs.getDate(12));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			
			this.close();
			return u;
		}
		
		
		public void ActualizarUsuario(Usuario usua){
			//contraUsu,dir,tfno,email,numCuenta,balance,ultimaCon
			this.open();
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String today=formatter.format(usua.getUltimaCon());  
			String sql ="UPDATE TUsuario SET contraUsu = '"+usua.getContraUsu()+"',dir = '"+usua.getDir()+"', tfno = '"+usua.getTfno()+"', email='"+usua.getEmail()+"', numCuenta='"+usua.getNumCuenta()+"', balance="+usua.getBalance()+", ultimaCon='"+today+"' WHERE dni='"+usua.getDni()+"'";
			System.out.println(sql);
			try {
				PreparedStatement update = con.prepareStatement(sql);
				update.executeUpdate();
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.close();
		}
		
		public ArrayList<Usuario> getInactivos() {
			// TODO Auto-generated method stub
			ArrayList<Usuario> x= new ArrayList<Usuario>();
			Usuario aux;
			this.open();
			String sql = "SELECT * FROM TUsuario where MONTHS_BETWEEN ((SYSDATE),(ultimaCon))>6";
			try {
				PreparedStatement select = con.prepareStatement(sql);
				System.out.println(sql);
				 ResultSet rs = select.executeQuery(sql);
				 
				 while(rs.next()) {
					 aux= new Usuario();
					 aux.setDni(rs.getString(1));
					 aux.setNomUsu(rs.getString(2));
					 aux.setContraUsu(rs.getString(3));
					 aux.setNombre(rs.getString(4));
					 aux.setApel1(rs.getString(5));
					 aux.setApel2(rs.getString(6));
					 aux.setDir(rs.getString(7));
					 aux.setTfno(rs.getString(8));
					 aux.setEmail(rs.getString(9));
					 aux.setNumCuenta(rs.getString(10));
					 aux.setBalance(rs.getFloat(11));
					 aux.setUltimaCon(rs.getDate(12));
					 x.add(aux);
				 
				 }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			
			this.close();
			return x;
		}
		
		public void borrarUsuario(Usuario u){
			this.open();
			String sql = "Delete from TUsuario where dni ='"+u.getDni().trim()+"'";
			try {
				PreparedStatement delete = con.prepareStatement(sql);
				System.out.println(sql);
				delete.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
			this.close();
		}
		
		
		//DISCOS
		public ArrayList<Disco> getDiscos(){
			ArrayList<Disco> x = new ArrayList<Disco>();
			Disco util=new Disco();
			this.open();
			String sql = "SELECT codDisco,titulo,nomAr,fechaPubli,precio,stock FROM TDisco";
			try {
				PreparedStatement select = con.prepareStatement(sql);
				System.out.println(sql);
				ResultSet rs = select.executeQuery(sql);
				
				while(rs.next()) {
					util=new Disco();
					util.setCodDisco(rs.getInt(1));
					util.setTitulo(rs.getString(2));
					util.setnomAr(rs.getString(3));
					util.setFechaPubli(rs.getDate(4));
					util.setPrecio(rs.getFloat(5));
					util.setStock(rs.getInt(6));
					util.setGeneros(getGeneros(rs.getInt(1)));
					util.setCanciones(getCanciones(rs.getInt(1)));
					x.add(util);
				};
				
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
			this.close();
			return x;
			
			
			
			
		}
		private ArrayList<String> getCanciones(int codDisco) {
			// TODO Auto-generated method stub
			ArrayList<String> x=new ArrayList<String>();
			String[] values = null;
			ARRAY array;
			String sql = "SELECT canciones from TDisco where codDisco = "+codDisco;
			try {
				PreparedStatement select = con.prepareStatement(sql);
				System.out.println(sql);
				ResultSet rs = select.executeQuery(sql);
				while(rs.next()){
					array = (ARRAY) rs.getArray(1);
					values = (String[]) array.getArray();
				};
				
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
			for (int i = 0; i < values.length; i++) {
				 x.add(values[i]);	
			}
			
			return x;
		}
		private ArrayList<Genero> getGeneros(int codDisco) {
			// TODO Auto-generated method stub
			ArrayList<Genero> x = new ArrayList<Genero>();
			Genero util;
			String sql = "SELECT TIP_GENEROS.* FROM TDISCO,TABLE(GENEROS) TIP_GENEROS where codDisco = "+codDisco;
			try {
				PreparedStatement select = con.prepareStatement(sql);
				System.out.println(sql);
				ResultSet rs = select.executeQuery(sql);
				while(rs.next()){
					util=new Genero();
					util.setCodGen(rs.getInt(1));
					util.setGen(rs.getString(2));
					x.add(util);
				};
				
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
			
			
			
			return x;
		}
		public void reducirStock(String titulo, int cantidad) {
			//Reduce el stock cuando se realiza una compra
			
			String sql ="UPDATE TDisco set stock=stock-"+cantidad+" where titulo = '"+titulo+"'";
		
			try {
				PreparedStatement update = con.prepareStatement(sql);
				System.out.println(sql);
				update.executeUpdate();
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		
		
		
		public void insertarDisco(Disco x){
			this.open();
			ArrayList<Genero> aux= x.getGeneros();
			ArrayList<String> aux1= x.getCanciones();
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String fechaPubli=formatter.format(x.getFechaPubli()); 
			String sql3="";
			String sql ="Select max(codDisco) from TDisco";
			try {
				PreparedStatement select = con.prepareStatement(sql);
				System.out.println(sql);
				ResultSet rs = select.executeQuery(sql);
				if(rs.next()) {
					x.setCodDisco(rs.getInt(1)+1);
					}else {
					x.setCodDisco(1);
				}
			
			String sql2 ="INSERT INTO TDisco VALUES ("+x.getCodDisco()+",'"+x.getTitulo()+"','"+x.getnomAr()+"','"
			+fechaPubli+"',"+x.getPrecio()+","+x.getStock()+",TIP_GENEROS(";
				for (int i = 0; i < aux.size(); i++) {
					if(aux.get(i).getCodGen()==-1) {
						sql3=sql3+"GENERO("+getMaxGenero()+",'"+aux.get(i).getGen()+"'),";
					}else {
						sql3=sql3+"GENERO("+aux.get(i).getCodGen()+",'"+aux.get(i).getGen()+"'),";
					}	
				} 
				sql3=sql3.substring(0, sql3.length()-1) + "),CANCION(";
				sql2=sql2+sql3;
				sql3="";
				for (int i = 0; i < aux1.size(); i++) {
					sql3=sql3+"'"+aux1.get(i)+"',";
					
				}
				sql3=sql3.substring(0, sql3.length()-1) + "))";
				sql2=sql2+sql3;
				
				PreparedStatement insert = con.prepareStatement(sql2);
				System.out.println(sql2);
				insert.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			
			this.close();
			
			
			
		}
		
		
		
		private int getMaxGenero() {
			// TODO Auto-generated method stub
			int x = 0;
			String sql ="Select max(codDisco) from TDisco";
			try {
				PreparedStatement select = con.prepareStatement(sql);
				System.out.println(sql);
				ResultSet rs = select.executeQuery(sql);
				if(rs.next()) {
					x=rs.getInt(1)+1;
					}else {
					x=1;
				}
			
			
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return x;
		}
		
		public void borrarDisco(Disco act){
			this.open();
			String sql = "Delete from TDisco where titulo ='"+act.getTitulo()+"'";
			try {
				PreparedStatement delete = con.prepareStatement(sql);
				System.out.println(sql);
				delete.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
			this.close();
		}
		
		
		//COMPRAS
		public ArrayList<Compra> comprasRealizadas(String nombreU) {
			ArrayList<Compra> x= new ArrayList<Compra>();
			Compra aux;
			this.open();
			String sql ="Select IDVenta,DEREF(IDCliente).dni,fechaCompra from TCompra";
			;
			try {
				PreparedStatement select = con.prepareStatement(sql);
				System.out.println(sql);
				ResultSet rs = select.executeQuery(sql);
				while(rs.next()) {
					aux=new Compra();
					aux.setIDCliente(rs.getString(2));
					aux.setFechaCompra(rs.getDate(3));
					aux.setCompras(getCompras(nombreU,rs.getInt(1)));
					x.add(aux);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			this.close();
			return x;
			
		}
		
		private ArrayList<LineaCompra> getCompras(String nombreU, int idv) {
			// TODO Auto-generated method stub
			ArrayList<LineaCompra> x= new ArrayList<LineaCompra>();
			LineaCompra aux;
			String sql ="Select DEREF(DETALLE.IDProducto).titulo DISCO,DETALLE.Cantidad," + 
					"DETALLE.Cantidad*DEREF(DETALLE.IDProducto).precio Importe from TCompra P,TABLE(P.Lineas) DETALLE WHERE DEREF(P.IDCliente).nomUsu = '"+nombreU+"' AND IDVenta = "+idv+"";
			try {
				PreparedStatement select = con.prepareStatement(sql);
				System.out.println(sql);
				ResultSet rs = select.executeQuery(sql);
				
				while(rs.next()) {
					aux=new LineaCompra();
					aux.setTitulo(rs.getString(1));
					aux.setCantidad(rs.getInt(2));
					aux.setPrecio(rs.getFloat(3));
					x.add(aux);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return x;
		}
		public void insertarCompra(Compra x) {
			this.open();
			int id;
			String sql ="Select max(IDVenta) from TCompra";
			try {
				PreparedStatement select = con.prepareStatement(sql);
				System.out.println(sql);
				ResultSet rs = select.executeQuery(sql);
				if(rs.next()) {
					id=(rs.getInt(1)+1);
					}else {
					id=1;
				}
				String sql2 = "Insert into TCompra Select "+id+",REF(U),SYSDATE,LineasCompra() FROM TUsuario U where U.dni= '"+x.getIDCliente().trim()+"'";
				
				
				PreparedStatement insert = con.prepareStatement(sql2);
				System.out.println(sql2);
				insert.executeUpdate();
				for (int i = 0; i < x.getCompras().size(); i++) {
					String sql3 = "Insert into table (Select C.Lineas from TCompra C where C.IDVenta = "+id+")"
							+ "(Select REF(D),"+x.getCompras().get(i).getCantidad()+" from TDisco D where titulo = '"+x.getCompras().get(i).getTitulo()+"')";
					 insert = con.prepareStatement(sql3);
					System.out.println(sql3);
					reducirStock(x.getCompras().get(i).getTitulo(), x.getCompras().get(i).getCantidad());
					insert.executeUpdate();
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			
			this.close();
			
		}
		
	
		
		
	
}