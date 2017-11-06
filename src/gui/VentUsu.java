package gui;



import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import clases.Usuario;
import clases.Compra;
import clases.Disco;
import clases.LineaCompra;
import control.Manager;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JPasswordField;

public class VentUsu extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	
	
	
	//General
	private JButton btnCajero;
	private JLabel lblBalance;
	private JLabel lblUsu;


	//Busqueda
	private JTextField textFieldBusqueda;
	private JComboBox cbBusqueda;
	private JButton btnBuscar;
	private JTable tableBusqueda;
	private JButton btnComprar;
	private JButton btnCarro;
	private JTextField textFieldUds;
	private ArrayList<Disco> todosD = new ArrayList<Disco>();
	private ArrayList<Disco> busqueda = new ArrayList<Disco>();
	private ArrayList<LineaCompra> compras = new ArrayList<LineaCompra>();
	
	//Editar Usuario
	private JButton btnDesconectarse;
	private JButton btnCambiarPass;
	private JButton btnValidar;
	private JButton btnActUsu;
	
	
	private JLabel lblFecha;
	private JLabel lblNombreApel;
	private JLabel lblAntigua;
	private JLabel lblNueva;
	private JLabel lblRepita;
	
	private JTextField txtFieldID;
	private JTextField textFieldnumC;
	private JTextField textFieldDir;
	private JTextField textFieldTfno;
	private JTextField textFieldEmail;
	private JPasswordField pfCon;
	
	private JPasswordField pfAnt;
	private JPasswordField pfNue1;
	private JPasswordField pfNue2;
	
	
	//Historial Compras
	private JButton btnActualizar;
	private JTable tableHisto;
	private ArrayList<Compra> hist = new ArrayList<Compra>();
	
	//Otros
	private Login log;
	private String x;
	private Manager manager= new Manager();
	private Usuario u=new Usuario();
	
		
	
	
	public VentUsu(Login login,String nomUsu) {
		super(login);
		setModal(true);
		setTitle("Usuario");
		log=login;
		x=nomUsu;
		
		u= manager.CargarDatos(x);
		
		this.addWindowListener(new WindowAdapter() {
			   public void windowClosing(WindowEvent evt) {
				   desconectarse();
			   }
			  });
		setBounds(100, 100, 762, 441);
		getContentPane().setLayout(null);
		
		JLabel lbl1 = new JLabel("Balance:");
		lbl1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl1.setBounds(482, 24, 75, 14);
		getContentPane().add(lbl1);
		
		lblBalance = new JLabel("Euros\r\n");
		lblBalance.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblBalance.setBounds(554, 18, 69, 27);
		getContentPane().add(lblBalance);
		
		JLabel lblDiscosSl = new JLabel("DISCOS S.L");
		lblDiscosSl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDiscosSl.setBounds(39, 11, 138, 27);
		getContentPane().add(lblDiscosSl);
		
		btnCajero = new JButton("Cajero");
		btnCajero.setBounds(623, 11, 108, 34);
		getContentPane().add(btnCajero);
		tabbedPane.setBounds(0, 58, 746, 345);
		getContentPane().add(tabbedPane);
		
		JPanel panelBusqueda = new JPanel();
		tabbedPane.addTab("Busqueda Disco", null, panelBusqueda, null);
		panelBusqueda.setLayout(null);
		
		JLabel lblBuscar = new JLabel("Buscar:");
		lblBuscar.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBuscar.setBounds(35, 11, 89, 26);
		panelBusqueda.add(lblBuscar);
		
		textFieldBusqueda = new JTextField();
		textFieldBusqueda.setBounds(107, 16, 232, 20);
		panelBusqueda.add(textFieldBusqueda);
		textFieldBusqueda.setColumns(10);
		
		cbBusqueda = new JComboBox();
		cbBusqueda.addItem("Titulo");
		cbBusqueda.addItem("Artista");
		cbBusqueda.addItem("Genero");
		cbBusqueda.setSelectedIndex(-1);
		cbBusqueda.setBounds(385, 16, 145, 21);
		panelBusqueda.add(cbBusqueda);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(569, 15, 123, 26);
		panelBusqueda.add(btnBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 60, 669, 198);
		panelBusqueda.add(scrollPane);
		
		tableBusqueda = new JTable();
		scrollPane.setViewportView(tableBusqueda);
		tableBusqueda.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableBusqueda.setDefaultEditor(Object.class, null);
		
		btnComprar = new JButton("Comprar");
		btnComprar.setBounds(607, 280, 97, 26);
		panelBusqueda.add(btnComprar);
		
		JLabel lblUnidades = new JLabel("Unidades:");
		lblUnidades.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUnidades.setBounds(276, 284, 76, 14);
		panelBusqueda.add(lblUnidades);
		
		textFieldUds = new JTextField();
		textFieldUds.setBounds(364, 283, 46, 20);
		panelBusqueda.add(textFieldUds);
		textFieldUds.setColumns(10);
		
		btnCarro = new JButton("A\u00F1adir Carro");
		btnCarro.setBounds(433, 280, 116, 26);
		panelBusqueda.add(btnCarro);
		
		
		
		JPanel panelUsu = new JPanel();
		tabbedPane.addTab("Editar Usuario", null, panelUsu, null);
		panelUsu.setLayout(null);
		
		lblNombreApel = new JLabel("Nombre + Apel");
		lblNombreApel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombreApel.setBounds(45, 11, 146, 14);
		panelUsu.add(lblNombreApel);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblId.setBounds(29, 60, 32, 14);
		panelUsu.add(lblId);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(-13, 36, 744, 3);
		panelUsu.add(separator);
		
		txtFieldID = new JTextField();
		txtFieldID.setEditable(false);
		txtFieldID.setEnabled(false);
		txtFieldID.setBounds(57, 57, 128, 20);
		panelUsu.add(txtFieldID);
		txtFieldID.setColumns(10);
		
		btnDesconectarse = new JButton("Cerrar Sesion");
		btnDesconectarse.setBounds(211, 56, 114, 23);
		panelUsu.add(btnDesconectarse);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblContrasea.setBounds(393, 162, 68, 20);
		panelUsu.add(lblContrasea);
		
		pfCon = new JPasswordField();
		pfCon.setBounds(471, 162, 128, 17);
		panelUsu.add(pfCon);
		
		btnCambiarPass = new JButton("Cambiar");
		btnCambiarPass.setBounds(619, 161, 89, 23);
		panelUsu.add(btnCambiarPass);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmail.setBounds(29, 123, 68, 20);
		panelUsu.add(lblEmail);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTelefono.setBounds(29, 169, 68, 20);
		panelUsu.add(lblTelefono);
		
		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDireccion.setBounds(29, 213, 68, 20);
		panelUsu.add(lblDireccion);
		
		JLabel lblNumeroCuenta = new JLabel("Numero Cuenta:");
		lblNumeroCuenta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNumeroCuenta.setBounds(29, 266, 98, 20);
		panelUsu.add(lblNumeroCuenta);
		
		textFieldnumC = new JTextField();
		textFieldnumC.setBounds(130, 266, 106, 20);
		panelUsu.add(textFieldnumC);
		textFieldnumC.setColumns(10);
		
		textFieldDir = new JTextField();
		textFieldDir.setBounds(130, 213, 106, 20);
		panelUsu.add(textFieldDir);
		textFieldDir.setColumns(10);
		
		textFieldTfno = new JTextField();
		textFieldTfno.setBounds(130, 169, 106, 20);
		panelUsu.add(textFieldTfno);
		textFieldTfno.setColumns(10);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(130, 123, 106, 20);
		panelUsu.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		btnActUsu = new JButton("Actualizar");
		btnActUsu.setBounds(266, 265, 106, 23);
		panelUsu.add(btnActUsu);
		
		lblAntigua = new JLabel("Antigua contrase\u00F1a:");
		lblAntigua.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAntigua.setBounds(393, 202, 114, 20);
		panelUsu.add(lblAntigua);
		
		lblNueva = new JLabel("Nueva Contrase\u00F1a:");
		lblNueva.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNueva.setBounds(393, 233, 106, 20);
		panelUsu.add(lblNueva);
		
		lblRepita = new JLabel("Repita contrase\u00F1a:");
		lblRepita.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRepita.setBounds(393, 264, 128, 20);
		panelUsu.add(lblRepita);
		
		pfAnt = new JPasswordField();
		pfAnt.setBounds(517, 202, 120, 20);
		panelUsu.add(pfAnt);
		
		pfNue1 = new JPasswordField();
		pfNue1.setBounds(517, 233, 120, 20);
		panelUsu.add(pfNue1);
		
		pfNue2 = new JPasswordField();
		pfNue2.setBounds(517, 264, 120, 20);
		panelUsu.add(pfNue2);
		
		btnValidar = new JButton("Validar");
		btnValidar.setBounds(642, 263, 89, 23);
		panelUsu.add(btnValidar);
		
		lblFecha = new JLabel("FECHA");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFecha.setBounds(503, 60, 150, 54);
		panelUsu.add(lblFecha);
		
		JLabel lblNewLabel_1 = new JLabel("Ultima Conexion:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(374, 77, 133, 20);
		panelUsu.add(lblNewLabel_1);
		
		JPanel panelHisto = new JPanel();
		tabbedPane.addTab("Historial Compras\r\n", null, panelHisto, null);
		panelHisto.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Historial Compras:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(29, 11, 145, 14);
		panelHisto.add(lblNewLabel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 43, 618, 248);
		panelHisto.add(scrollPane_1);
		
		tableHisto = new JTable();
		tableHisto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(tableHisto);
		tableHisto.setDefaultEditor(Object.class, null);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(638, 261, 103, 30);
		panelHisto.add(btnActualizar);
		
		lblUsu = new JLabel("Usu");
		lblUsu.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsu.setBounds(230, 26, 92, 14);
		getContentPane().add(lblUsu);
		

		//Action Listeners
		btnCajero.addActionListener(this);
		
		btnBuscar.addActionListener(this);
		btnCarro.addActionListener(this);
		btnComprar.addActionListener(this);
	
		
		btnDesconectarse.addActionListener(this);
		btnCambiarPass.addActionListener(this);
		btnValidar.addActionListener(this);
		btnActUsu.addActionListener(this);
		
		btnActualizar.addActionListener(this);
		
		
		

		
		//Oculta el cambio de pass
		lblAntigua.setVisible(false);
		lblNueva.setVisible(false);
		lblRepita.setVisible(false);
		pfAnt.setVisible(false);
		pfNue1.setVisible(false);
		pfNue2.setVisible(false);
		btnValidar.setVisible(false);
		
		//Cargar datos
		lblBalance.setText(Float.toString(u.getBalance())+"€");
		lblUsu.setText(nomUsu);
		
		
		cargarDatosUsu();
		cargarHistorico(nomUsu);
		
		
		Date dateobj = new Date();
		u.setUltimaCon(dateobj);
		manager.ActualizarUsuario(u);
		
		todosD=manager.getDiscos();
		cargarBusqueda(todosD);
		
		
	}


	


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		LineaCompra aux;
		String titulo,precio;
		int carro,stock;
		boolean esta=false;
		if(e.getSource().equals(btnCajero)) {
			//Abre una ventana para que pueda recargar saldo
			Deposito de = new Deposito(this,u);
			de.setResizable(false);
			de.setLocationRelativeTo(null);
			de.setVisible(true);
			lblBalance.setText(String.valueOf(u.getBalance())+"€");
			
			
		}else if (e.getSource().equals(btnCarro)) {
			//Comprobar que sea numero las uds
			try {
			     carro=Integer.parseInt(textFieldUds.getText());//Unidades que quiere comprar
			     if(carro <= 0 ) {
			    	 JOptionPane.showMessageDialog(this, "Introduzca un numero mayor", "Error", JOptionPane.ERROR_MESSAGE);
			     }else {
			    	 //Comprobar que hay Libro seleccionado y añadir al Array compra
			    	 esta=false;
			    	 if(tableBusqueda.getSelectedRow()!= -1) {
			    		 stock=Integer.parseInt( tableBusqueda.getValueAt(tableBusqueda.getSelectedRow(), 4).toString());//Stock disponible
			    		 for (int i = 0; i < compras.size(); i++) {
							if(compras.get(i).getTitulo().equals(tableBusqueda.getModel().getValueAt(tableBusqueda.getSelectedRow(), 0).toString())) {
								if((compras.get(i).getCantidad()+carro)<= stock) {
									compras.get(i).setCantidad(compras.get(i).getCantidad()+carro);
									esta=true;
									break;
								}else {
									JOptionPane.showMessageDialog(this, "No hay Stock suficiente", "Error", JOptionPane.ERROR_MESSAGE);
									esta=true;
									break;
								}
									
								
								
							}
						}
			    		 if(!esta) {
			    			 if(carro <= stock) {
			    				 aux=new LineaCompra();
					    		 titulo = tableBusqueda.getModel().getValueAt(tableBusqueda.getSelectedRow(), 0).toString();//Devuelve el titulo
					    		 precio = tableBusqueda.getModel().getValueAt(tableBusqueda.getSelectedRow(),3).toString();//Devuelve el precio
					    		 precio= precio.substring(0, precio.length() - 1);
					    		 aux.setCantidad(carro);
					    		 aux.setPrecio(Float.parseFloat(precio));
					    		 aux.setTitulo(titulo);
					    		 compras.add(aux);
			    			 }else {
			    				 JOptionPane.showMessageDialog(this, "No hay Stock suficiente", "Error", JOptionPane.ERROR_MESSAGE);
			    			 }
			    				 
			    			 
			    			
			    		 }
			    		 
			    		 
			    	 }else 
			    		 JOptionPane.showMessageDialog(this, "Selecciona un Libro", "Error", JOptionPane.ERROR_MESSAGE);
			     }
			}
			catch (NumberFormatException o) {
				JOptionPane.showMessageDialog(this, "Introduzca un numero", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			
		}else if(e.getSource().equals(btnBuscar)) {
			//Carga en la tabla los discos si no se encuentran dara un warning
			busqueda.clear();
			if(textFieldBusqueda.getText().equals("")||cbBusqueda.getSelectedIndex()==-1) {
				JOptionPane.showMessageDialog(this, "Rellene todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
			}else {
				if(cbBusqueda.getSelectedIndex()==0) {
					for (int i = 0; i <todosD.size(); i++) {
						if(todosD.get(i).getTitulo().toLowerCase().contains(textFieldBusqueda.getText().toLowerCase()) ) {
							busqueda.add(todosD.get(i));
						}
					}
					cargarBusqueda(busqueda);
				}else if(cbBusqueda.getSelectedIndex()==1) {
					for (int i = 0; i <todosD.size(); i++) {
						if(todosD.get(i).getnomAr().toLowerCase().contains(textFieldBusqueda.getText().toLowerCase()) ) {
							busqueda.add(todosD.get(i));
						}
					}
					cargarBusqueda(busqueda);	
				}else if(cbBusqueda.getSelectedIndex()==2) {
					for (int i = 0; i <todosD.size(); i++) {
						if(todosD.get(i).getGeneros().get(i).getGen().toLowerCase().contains(textFieldBusqueda.getText().toLowerCase()) ) {
							busqueda.add(todosD.get(i));
						}
					}
					cargarBusqueda(busqueda);
				}
			}
			
			
			
			
		}else if(e.getSource().equals(btnComprar)) {
			//Abre una ventana de compra si hay algun disco en la compra
			if(compras.size() > 0 ) {
				ConfirmarCompra com = new ConfirmarCompra(this,compras,u);
				com.setResizable(false);
				com.setLocationRelativeTo(null);
				com.setVisible(true);
			}else {
				JOptionPane.showMessageDialog(null, "Introduce algun disco primero", "Warning",JOptionPane.WARNING_MESSAGE);
			}
			todosD=manager.getDiscos();
			cargarBusqueda(todosD);
			lblBalance.setText(String.valueOf(u.getBalance())+"€");
			
		}else if(e.getSource().equals(btnDesconectarse)) {
			//Sale a la ventana login
			this.desconectar();
			
		}else if(e.getSource().equals(btnCambiarPass)) {
			//Activa los botones necesarios para cambiar la contraseña
			pfCon.setEnabled(false);
			btnCambiarPass.setEnabled(false);
			lblAntigua.setVisible(true);
			lblNueva.setVisible(true);
			lblRepita.setVisible(true);
			pfAnt.setVisible(true);
			pfNue1.setVisible(true);
			pfNue2.setVisible(true);
			btnValidar.setVisible(true);
		}else if(e.getSource().equals(btnValidar)) {
			//confirma que estan bien los campos, si no lo esta dara otro warning
			String mensaje="Contraseña antigua incorrecta";
			System.out.println(u.getContraUsu());
			if(String.valueOf(pfAnt.getPassword()).equals(u.getContraUsu())) {
				mensaje="Contraseñas no coinciden";
				
				if(new String(pfNue1.getPassword()).equalsIgnoreCase(new String(pfNue2.getPassword()))){
					u.setContraUsu(String.valueOf(pfNue1.getPassword()));
					manager.ActualizarUsuario(u);
					pfAnt.setText("");
					pfNue1.setText("");
					pfNue2.setText("");
					mensaje="Contraseña Actualizada";
					lblAntigua.setVisible(false);
					lblNueva.setVisible(false);
					lblRepita.setVisible(false);
					pfAnt.setVisible(false);
					pfNue1.setVisible(false);
					pfNue2.setVisible(false);
					btnValidar.setVisible(false);
					pfCon.setEnabled(true);
					btnCambiarPass.setEnabled(true);
					pfCon.setText(pfNue1.getPassword().toString());
				}
			}
			JOptionPane.showMessageDialog(null, mensaje);
				
		}else if(e.getSource().equals(btnActUsu)) {
			//Actualiza el usuario
			
			if (textFieldEmail.getText().matches("^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,4}$")) {
				u.setEmail(textFieldEmail.getText());
				u.setDir(textFieldDir.getText());
				u.setTfno(textFieldTfno.getText());
				u.setNumCuenta(textFieldnumC.getText());
				manager.ActualizarUsuario(u);
				JOptionPane.showMessageDialog(null, "Datos Actualizados");
			}else {
				JOptionPane.showMessageDialog(null, "Correo no valido");
			}
			
		}else if(e.getSource().equals(btnActualizar)) {
			//Actualiza las compras hechas durante esta sesion
			this.cargarHistorico(u.getNomUsu());
		}
				
		
	}
	
	
	
	private void cargarHistorico(String nomUsu) {
		// Obtener array de compras(hist) y mostrarlas en tableHisto
			hist=manager.comprasRealizadas(nomUsu);
		
			List<Object[]> list = new ArrayList<Object[]>();
		    for (int i = 0; i < hist.size(); i++) {
		        list.add(new Object[] { 
		                                  hist.get(i).getIDCliente(), 
		                                  hist.get(i).getFechaCompra(),
		                                  String.format("%.2f", hist.get(i).getImporte()*0.79)+"€",
		                                  String.format("%.2f", hist.get(i).getImporte()*0.21)+"€",
		                                  String.format("%.2f", hist.get(i).getImporte())+"€"
		                              });

		    }
		    tableHisto.setModel(new DefaultTableModel(list.toArray(new Object[][] {}), 
		                        new String[] {"DNI", "Fecha Compra","Precio sin IVA","IVA", "Precio TOTAL"}));
		}
		
		
	
	

	private void cargarDatosUsu() {
		// colocar los datos en la pestaña editar usu
		txtFieldID.setText(u.getDni());
		textFieldDir.setText(u.getDir());
		textFieldEmail.setText(u.getEmail());
		textFieldnumC.setText(u.getNumCuenta());
		textFieldTfno.setText(u.getTfno());
		lblNombreApel.setText(u.toString());
		lblFecha.setText(u.getUltimaCon().toString());
		pfCon.setText(u.getContraUsu());
		
	}
	
	private void cargarBusqueda(ArrayList<Disco> busq) {
		// TODO Auto-generated method stub
	
		List<Object[]> list = new ArrayList<Object[]>();
	    for (int i = 0; i < busq.size(); i++) {
	        list.add(new Object[] { 
	                                  busq.get(i).getTitulo(), 
	                                  busq.get(i).getnomAr(),
	                                  busq.get(i).getFechaPubli(),
	                                  busq.get(i).getPrecio()+"€",
	                                  busq.get(i).getStock()
	                         
	                              });

	    }
	    tableBusqueda.setModel(new DefaultTableModel(list.toArray(new Object[][] {}), 
	                        new String[] {"Titulo", "Artista", "Fecha Publicacion","Precio","Stock"}));
	    
	  
	}
	

	
	//Desconexiones
	

	private void desconectarse(){
		int response = JOptionPane.showConfirmDialog (null,"Seguro que quieres salir?","Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(response == JOptionPane.YES_OPTION){
			log.dispose();
			this.dispose();
		}else if(response == JOptionPane.NO_OPTION) {
			this.dispose();
			VentUsu Usu = new VentUsu(log,x);
			Usu.setResizable(false);
			Usu.setLocationRelativeTo(null);
			Usu.setVisible(true);
		}
	
	}
	  private void desconectar() {
			// TODO Auto-generated method stub
			this.dispose();
		
	  }
}

