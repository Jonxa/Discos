package gui;

import javax.swing.JDialog;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
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

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import com.toedter.calendar.JDateChooser;

import clases.Disco;
import clases.Genero;
import clases.Usuario;
import control.Manager;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class VentAdmin extends JDialog implements ActionListener{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Insertar
	
	private JTextField textTituloI;
	private JTextField textArtistaI;
	private JDateChooser dateChooserI;
	private JTextField textPrecioI;
	private JTextField textStockI;
	private JTextField textGeneroI;
	private JTextField textCancI;

	private JComboBox<String> cbGenI;
	
	private JTable tableGeneroI;
	private JTable tableCancionI;
	
	private JButton btnInsertarI;
	private JButton btnLimpiarI;
	private JButton btnAñaGenI;
	private JButton btnLimGenI;
	private JButton btnAñaCancI;
	private JButton btnQuitarI;
	
	private ArrayList<Genero> generoI = new ArrayList<Genero>();  
	private ArrayList<String> cancionI = new ArrayList<String>();  
	
	
	//Modificar/Borrar
	private JTextField textArtistaM;
	private JDateChooser dateChooserM;
	private JTextField textPrecioM;
	private JTextField textStockM;
	private JTextField textGenM;
	private JTextField textCancM;
	
	private JComboBox<String> cbTitulo;
	private JComboBox<String> cbGenM;
	
	private JTable tablecancionesM;
	private JTable tableGenerosM;
	
	private JButton btnCargarM;
	private JButton btnAct;
	private JButton btnBorrarD;
	private JButton btnAñaGenM;
	private JButton btnLimGenM;
	private JButton btnAñaCancM;
	private JButton btnQuitarM ;
	//Borrar Usu	
	private JTable tableUsu;
	
	private JButton btnBorrarU;
	//Otros
	private Login log;
	private Manager manager = new Manager();
	private ArrayList<Disco> todosD = new ArrayList<Disco>(); //Todos los discos
	private ArrayList<Usuario> inac = new ArrayList<Usuario>(); //Usuarios inactivos
	private ArrayList<Genero> generos = new ArrayList<Genero>(); //Todos los generos existentes con cod
	private Disco cargado;
	
	

	public VentAdmin(Login login) {
		super(login);
		setModal(true);
		setTitle("Administrador");
		log=login;
		this.addWindowListener(new WindowAdapter() {
			   public void windowClosing(WindowEvent evt) {
				   desconectarse();
			   }
			  });
		setBounds(100, 100, 756, 465);
		getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 740, 427);
		getContentPane().add(tabbedPane);
		
		JPanel panelInsertar = new JPanel();
		tabbedPane.addTab("Insertar Disco", null, panelInsertar, null);
		panelInsertar.setLayout(null);
		
		JLabel label = new JLabel("T\u00EDtulo:");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(28, 32, 46, 14);
		panelInsertar.add(label);
		
		JLabel label_1 = new JLabel("Artista:");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_1.setBounds(28, 65, 74, 14);
		panelInsertar.add(label_1);
		
		JLabel label_2 = new JLabel("Fecha de publicaci\u00F3n:");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_2.setBounds(28, 102, 153, 14);
		panelInsertar.add(label_2);
		
		JLabel label_3 = new JLabel("Precio:");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_3.setBounds(28, 140, 60, 14);
		panelInsertar.add(label_3);
		
		JLabel label_4 = new JLabel("Stock:");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_4.setBounds(28, 178, 46, 14);
		panelInsertar.add(label_4);
		
		JLabel label_5 = new JLabel("G\u00E9neros:");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_5.setBounds(338, 29, 74, 14);
		panelInsertar.add(label_5);
		
		JLabel lblGeneroNuevo = new JLabel("Genero nuevo");
		lblGeneroNuevo.setBounds(542, 56, 119, 20);
		panelInsertar.add(lblGeneroNuevo);
		
		textGeneroI = new JTextField();
		textGeneroI.setColumns(10);
		textGeneroI.setBounds(542, 87, 110, 23);
		panelInsertar.add(textGeneroI);
		
		btnAñaGenI = new JButton("+");
		btnAñaGenI.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnAñaGenI.setBounds(542, 121, 52, 32);
		panelInsertar.add(btnAñaGenI);
		
		btnLimGenI = new JButton("Limpiar");
		btnLimGenI.setBounds(542, 159, 119, 33);
		panelInsertar.add(btnLimGenI);
		
		cbGenI = new JComboBox<String>();
		cbGenI.setSelectedIndex(-1);
		cbGenI.setBounds(317, 170, 183, 18);
		panelInsertar.add(cbGenI);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(317, 65, 183, 87);
		panelInsertar.add(scrollPane);
		
		tableGeneroI = new JTable();
		tableGeneroI.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tableGeneroI);
		tableGeneroI.setDefaultEditor(Object.class, null);
		
		JLabel label_6 = new JLabel("Canciones:");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_6.setBounds(317, 213, 102, 14);
		panelInsertar.add(label_6);
		
		JLabel label_7 = new JLabel("A\u00F1ade Cancion");
		label_7.setBounds(532, 225, 119, 20);
		panelInsertar.add(label_7);
		
		textCancI = new JTextField();
		textCancI.setColumns(10);
		textCancI.setBounds(532, 256, 102, 20);
		panelInsertar.add(textCancI);
		
		btnAñaCancI = new JButton("A\u00F1adir");
		btnAñaCancI.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAñaCancI.setBounds(532, 295, 102, 33);
		panelInsertar.add(btnAñaCancI);
		
		btnQuitarI = new JButton("Quitar");
		btnQuitarI.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnQuitarI.setBounds(532, 331, 102, 33);
		panelInsertar.add(btnQuitarI);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(317, 244, 183, 120);
		panelInsertar.add(scrollPane_1);
		
		tableCancionI = new JTable();
		tableCancionI.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(tableCancionI);
		tableCancionI.setDefaultEditor(Object.class, null);
		
		dateChooserI = new JDateChooser();
		dateChooserI.setBounds(184, 102, 110, 20);
		panelInsertar.add(dateChooserI);
		
		textPrecioI = new JTextField();
		textPrecioI.setColumns(10);
		textPrecioI.setBounds(184, 140, 52, 20);
		panelInsertar.add(textPrecioI);
		
		textStockI = new JTextField();
		textStockI.setColumns(10);
		textStockI.setBounds(184, 177, 46, 20);
		panelInsertar.add(textStockI);
		
		textArtistaI = new JTextField();
		textArtistaI.setColumns(10);
		textArtistaI.setBounds(184, 64, 110, 20);
		panelInsertar.add(textArtistaI);
		
		textTituloI = new JTextField();
		textTituloI.setColumns(10);
		textTituloI.setBounds(184, 31, 110, 20);
		panelInsertar.add(textTituloI);
		
		btnInsertarI = new JButton("Insertar");
		btnInsertarI.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnInsertarI.setBounds(91, 247, 102, 36);
		panelInsertar.add(btnInsertarI);
		
		btnLimpiarI = new JButton("Limpiar todo");
		btnLimpiarI.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLimpiarI.setBounds(73, 315, 133, 36);
		panelInsertar.add(btnLimpiarI);
		
		JPanel panelmodificarborrarDisco = new JPanel();
		tabbedPane.addTab("Modificar/Borrar Disco", null, panelmodificarborrarDisco, null);
		panelmodificarborrarDisco.setLayout(null);
		
		cbTitulo = new JComboBox<String>();
		cbTitulo.setBounds(88, 11, 182, 27);
		panelmodificarborrarDisco.add(cbTitulo);
		
		JLabel label_8 = new JLabel("T\u00EDtulo:");
		label_8.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_8.setBounds(21, 24, 46, 14);
		panelmodificarborrarDisco.add(label_8);
		
		btnCargarM = new JButton("Cargar Datos");
		btnCargarM.setBounds(121, 49, 109, 30);
		panelmodificarborrarDisco.add(btnCargarM);
		
		JLabel label_9 = new JLabel("Artista:");
		label_9.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_9.setBounds(21, 110, 102, 14);
		panelmodificarborrarDisco.add(label_9);
		
		textArtistaM = new JTextField();
		textArtistaM.setEnabled(false);
		textArtistaM.setColumns(10);
		textArtistaM.setBounds(175, 109, 109, 20);
		panelmodificarborrarDisco.add(textArtistaM);
		
		dateChooserM = new JDateChooser();
		dateChooserM.setBounds(175, 158, 95, 20);
		panelmodificarborrarDisco.add(dateChooserM);
		dateChooserM.setEnabled(false);
		
		JLabel label_10 = new JLabel("Fecha de publicaci\u00F3n:");
		label_10.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_10.setBounds(21, 164, 153, 14);
		panelmodificarborrarDisco.add(label_10);
		
		JLabel label_11 = new JLabel("Precio:");
		label_11.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_11.setBounds(21, 222, 60, 14);
		panelmodificarborrarDisco.add(label_11);
		
		textPrecioM = new JTextField();
		textPrecioM.setEnabled(false);
		textPrecioM.setColumns(10);
		textPrecioM.setBounds(175, 221, 46, 20);
		panelmodificarborrarDisco.add(textPrecioM);
		
		JLabel label_12 = new JLabel("Stock:");
		label_12.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_12.setBounds(18, 269, 46, 18);
		panelmodificarborrarDisco.add(label_12);
		
		textStockM = new JTextField();
		textStockM.setEnabled(false);
		textStockM.setColumns(10);
		textStockM.setBounds(170, 269, 46, 22);
		panelmodificarborrarDisco.add(textStockM);
		
		btnAct = new JButton("Actualizar");
		btnAct.setEnabled(false);
		btnAct.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAct.setBounds(16, 328, 100, 34);
		panelmodificarborrarDisco.add(btnAct);
		
		btnBorrarD = new JButton("Borrar");
		btnBorrarD.setEnabled(false);
		btnBorrarD.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnBorrarD.setBounds(158, 328, 126, 34);
		panelmodificarborrarDisco.add(btnBorrarD);
		
		JLabel label_13 = new JLabel("G\u00E9neros:");
		label_13.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_13.setBounds(325, 24, 74, 14);
		panelmodificarborrarDisco.add(label_13);
		
		JLabel label_14 = new JLabel("Genero nuevo");
		label_14.setBounds(550, 54, 119, 20);
		panelmodificarborrarDisco.add(label_14);
		
		textGenM = new JTextField();
		textGenM.setEnabled(false);
		textGenM.setColumns(10);
		textGenM.setBounds(550, 85, 110, 23);
		panelmodificarborrarDisco.add(textGenM);
		
		btnAñaGenM = new JButton("+");
		btnAñaGenM.setEnabled(false);
		btnAñaGenM.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnAñaGenM.setBounds(550, 119, 52, 32);
		panelmodificarborrarDisco.add(btnAñaGenM);
		
		btnLimGenM = new JButton("Limpiar");
		btnLimGenM.setEnabled(false);
		btnLimGenM.setBounds(550, 157, 119, 33);
		panelmodificarborrarDisco.add(btnLimGenM);
		
		cbGenM = new JComboBox<String>();
		cbGenM.setEnabled(false);
		cbGenM.setSelectedIndex(-1);
		cbGenM.setBounds(325, 168, 183, 18);
		panelmodificarborrarDisco.add(cbGenM);
		
		JLabel label_15 = new JLabel("Canciones:");
		label_15.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_15.setBounds(325, 211, 102, 14);
		panelmodificarborrarDisco.add(label_15);
		
		JLabel label_16 = new JLabel("A\u00F1ade Cancion");
		label_16.setBounds(540, 223, 119, 20);
		panelmodificarborrarDisco.add(label_16);
		
		textCancM = new JTextField();
		textCancM.setEnabled(false);
		textCancM.setColumns(10);
		textCancM.setBounds(540, 254, 102, 20);
		panelmodificarborrarDisco.add(textCancM);
		
		btnAñaCancM = new JButton("A\u00F1adir");
		btnAñaCancM.setEnabled(false);
		btnAñaCancM.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAñaCancM.setBounds(540, 293, 102, 33);
		panelmodificarborrarDisco.add(btnAñaCancM);
		
		btnQuitarM = new JButton("Quitar");
		btnQuitarM.setEnabled(false);
		btnQuitarM.setBounds(540, 329, 102, 33);
		panelmodificarborrarDisco.add(btnQuitarM);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(325, 254, 196, 111);
		panelmodificarborrarDisco.add(scrollPane_2);
		
		tablecancionesM = new JTable();
		tablecancionesM.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablecancionesM.setEnabled(false);
		tablecancionesM.setDefaultEditor(Object.class, null);
		scrollPane_2.setViewportView(tablecancionesM);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(325, 57, 183, 94);
		panelmodificarborrarDisco.add(scrollPane_3);
		
		tableGenerosM = new JTable();
		tableGenerosM.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableGenerosM.setEnabled(false);
		scrollPane_3.setViewportView(tableGenerosM);
		tableGenerosM.setDefaultEditor(Object.class, null);
		
		JPanel panelBorrarUsu = new JPanel();
		tabbedPane.addTab("Borrar Usuarios Inactivos", null, panelBorrarUsu, null);
		panelBorrarUsu.setLayout(null);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(10, 28, 535, 341);
		panelBorrarUsu.add(scrollPane_4);
		
		tableUsu = new JTable();
		scrollPane_4.setViewportView(tableUsu);
		tableUsu.setDefaultEditor(Object.class, null);
		
		
		btnBorrarU = new JButton("Borrar");
		btnBorrarU.setBounds(596, 322, 118, 43);
		panelBorrarUsu.add(btnBorrarU);
		
		
		//Action Listener
		btnInsertarI.addActionListener(this);
		btnAñaGenI.addActionListener(this);
		btnLimGenI.addActionListener(this);
		btnAñaCancI.addActionListener(this);
		btnQuitarI.addActionListener(this);
		btnLimpiarI.addActionListener(this);
		btnCargarM.addActionListener(this);
		btnAct.addActionListener(this);
		btnBorrarD.addActionListener(this);
		btnAñaGenM.addActionListener(this);
		btnLimGenM.addActionListener(this);
		btnAñaCancM.addActionListener(this);
		btnQuitarM.addActionListener(this);
		btnBorrarU.addActionListener(this);
		
		
		
		

		cargarTitulos();
		cargarUsuarios();
		cargarGeneros();
		


	}












	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnInsertarI)) {
			//Inserta disco en la BD
			boolean esta=false;
			if(textTituloI.getText().equals("") || textArtistaI.getText().equals("")||dateChooserI.getDate()==null
					||textPrecioI.getText().equals("")|| textStockI.getText().equals("")||tableGeneroI.getRowCount()==0||tableCancionI.getRowCount()==0) {
				JOptionPane.showMessageDialog(null, "Rellena todos los campos");
			}else {
				int response = JOptionPane.showConfirmDialog (null,"Seguro que es ese titulo? Luego no se podra cambiar","Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(response == JOptionPane.YES_OPTION){
					for (int i = 0; i < todosD.size(); i++) {
						if(todosD.get(i).getTitulo().equals(textTituloI.getText())) {
							JOptionPane.showMessageDialog(null, "Ese titulo ya existe");
							esta=true;
							break;
						}
					}
					if(!esta) {
						//Insertar disco
						try {
							Disco insertar=new Disco();
							insertar.setTitulo(textTituloI.getText());
							insertar.setnomAr(textArtistaI.getText());
							insertar.setFechaPubli(dateChooserI.getDate());
							insertar.setPrecio(Float.parseFloat(textPrecioI.getText()));
							insertar.setStock(Integer.parseInt(textStockI.getText()));
							insertar.setGeneros(generoI);
							insertar.setCanciones(cancionI);
							manager.insertarDisco(insertar);
							btnLimpiarI.doClick();
							cargarTitulos();
							cargarGeneros();
						}catch(NumberFormatException w) {
							JOptionPane.showMessageDialog(null, "Revisa que los campos esten bien");
						}
						
						
					}
					
				}
			}
			
			
		}else if(e.getSource().equals(btnAñaGenI)) {
			
			//Inserta genero en la tabla
			if(cbGenI.getSelectedIndex()==-1 && textGeneroI.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Elige un genero");
			}else if (cbGenI.getSelectedIndex()!=-1) {
				generoI.add(generos.get(cbGenI.getSelectedIndex()));
			}else {
				Genero aux=new Genero();
				aux.setGen(textGeneroI.getText());
				generoI.add(aux);
			}
			
			textGeneroI.setText("");
			cbGenI.setSelectedIndex(-1);
			cargarTablaGenI(generoI);
			
		}else if(e.getSource().equals(btnLimGenI)) {
			//Limpia la tabla
			generoI.clear();
			cargarTablaGenI(generoI);
			
		}else if(e.getSource().equals(btnAñaCancI)) {
			//A�ade una cancion en la tabla
			if(textCancI.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Introduce una cancion");
			}else if(cancionI.size()==13) {
				JOptionPane.showMessageDialog(null, "Limite de canciones");
			}else {
				cancionI.add(textCancI.getText());
				textCancI.setText("");
				cargarTablaCanI(cancionI);
			}
			
			
			
		}else if(e.getSource().equals(btnQuitarI)) {
			//Quita una cancion de la tabla
			if(tableCancionI.getSelectedRow()!= -1) {
				for (int i = 0; i < cancionI.size(); i++) {
					if(cancionI.get(i).equals(tableCancionI.getModel().getValueAt(tableCancionI.getSelectedRow(), 0).toString())) {
						cancionI.remove(i);
						cargarTablaCanI(cancionI);
						break;
					}
				}
			}else {
				JOptionPane.showMessageDialog(this, "Selecciona una cancion", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			
			
		}else if(e.getSource().equals(btnLimpiarI)) {
			//Limpia todos los campos
			textArtistaI.setText("");
			textCancI.setText("");
			textGeneroI.setText("");
			textPrecioI.setText("");
			textStockI.setText("");
			textTituloI.setText("");
			dateChooserI.setDate(null);
			generoI.clear();
			cancionI.clear();
			cargarTablaCanI(cancionI);
			cargarTablaGenI(generoI);
			cbGenI.setSelectedIndex(-1);
			
			
			
		}else if(e.getSource().equals(btnCargarM)) {
			//Carga todos los datos de un titulo elegido
			if(cbTitulo.getSelectedIndex()!=-1) {
				cargado=new Disco();
				cargado=todosD.get(cbTitulo.getSelectedIndex());
				textArtistaM.setEnabled(true);
				textCancM.setEnabled(true);
				textGenM.setEnabled(true);
				textPrecioM.setEnabled(true);
				textStockM.setEnabled(true);
				dateChooserM.setEnabled(true);
				
				tablecancionesM.setEnabled(true);
				tableGenerosM.setEnabled(true);
				
				cbGenM.setEnabled(true);
				
				
				btnAñaCancM.setEnabled(true);
				btnBorrarD.setEnabled(true);
				btnAct.setEnabled(true);
				btnQuitarM.setEnabled(true);
				btnAñaGenM.setEnabled(true);
				btnLimGenM.setEnabled(true);
				
				textArtistaM.setText(cargado.getnomAr());
				textPrecioM.setText(String.valueOf(cargado.getPrecio()));
				textStockM.setText(String.valueOf(cargado.getStock()));
				dateChooserM.setDate(cargado.getFechaPubli());
				
				cargarTablaGenM(cargado.getGeneros());
				cargarTablaCanM(cargado.getCanciones());
				
				
		
				
			}
			
			
		}else if(e.getSource().equals(btnAct)) {
			//Actualiza los datos del libro
			
			Disco act= new Disco();
			try {
				act.setTitulo(cargado.getTitulo());
				act.setnomAr(textArtistaM.getText());
				act.setFechaPubli(dateChooserM.getDate());
				act.setPrecio(Float.parseFloat(textPrecioM.getText()));
				act.setStock(Integer.parseInt(textStockM.getText()));
				act.setGeneros(cargado.getGeneros());
				act.setCanciones(cargado.getCanciones());
				
				
				
				manager.borrarDisco(act);
				manager.insertarDisco(act);
				desactivar();
				
				cargarTitulos();
				cargarGeneros();
				
				JOptionPane.showMessageDialog(this, "Actualizado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
			}catch(NumberFormatException w) {
				JOptionPane.showMessageDialog(null, "Revisa que los campos esten bien");
			}
	
		}else if(e.getSource().equals(btnBorrarD)) {
			//Borra el disco seleccionado
			desactivar();
			int response = JOptionPane.showConfirmDialog (null,"Seguro que quieres borrar el titulo? ","Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(response == JOptionPane.YES_OPTION) {
				manager.borrarDisco(cargado);
				cargarTitulos();
			}
		
			
		}else if(e.getSource().equals(btnAñaGenM)) {
			//A�ade genero
			ArrayList<Genero> aux = cargado.getGeneros();
			if(cbGenM.getSelectedIndex()==-1 && textGenM.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Elige un genero");
			}else if (cbGenM.getSelectedIndex()!=-1) {	
				aux.add(generos.get(cbGenM.getSelectedIndex()));
			}else {
				Genero au=new Genero();
				au.setGen(textGenM.getText());
				aux.add(au);
			}
			
			cargado.setGeneros(aux);
			textGenM.setText("");
			cbGenM.setSelectedIndex(-1);
			cargarTablaGenM(cargado.getGeneros());
			
		}else if(e.getSource().equals(btnLimGenM)) {
			//Limpia todos los generos
			cargado.getGeneros().clear();
			cargarTablaGenM(cargado.getGeneros());
			
		}else if(e.getSource().equals(btnAñaCancM)) {
			//A�ade cancion
			if(textCancM.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Introduce una cancion");
			}else if(cargado.getCanciones().size()==13) {
				JOptionPane.showMessageDialog(null, "Limite de canciones");
			}else {
				cargado.getCanciones().add(textCancM.getText());
				textCancM.setText("");
				cargarTablaCanM(cargado.getCanciones());
			}
		}else if(e.getSource().equals(btnQuitarM)) {
			//Quita Cancion
			if(tablecancionesM.getSelectedRow()!= -1) {
				for (int i = 0; i < cargado.getCanciones().size(); i++) {
					if(cargado.getCanciones().get(i).equals(tablecancionesM.getModel().getValueAt(tablecancionesM.getSelectedRow(), 0).toString())) {
						cargado.getCanciones().remove(i);
						cargarTablaCanM(cargado.getCanciones());
						break;
					}
				}
			}else {
				JOptionPane.showMessageDialog(this, "Selecciona una cancion", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}else if(e.getSource().equals(btnBorrarU)) {
			//Borra Usuario seleccionado
			if(tableUsu.getSelectedRow()!= -1) {
				for (int i = 0; i < inac.size(); i++) {
					if(inac.get(i).getDni().equals(tableUsu.getModel().getValueAt(tableUsu.getSelectedRow(), 0).toString())){
						manager.borrarUsuario(inac.get(i));
						cargarUsuarios();
						break;
					}
				}
			}
			
			
		}
	}
	
	private void desactivar() {
		// TODO Auto-generated method stub
		textArtistaM.setEnabled(false);
		textCancM.setEnabled(false);
		textGenM.setEnabled(false);
		textPrecioM.setEnabled(false);
		textStockM.setEnabled(false);
		dateChooserM.setEnabled(false);
		
		tablecancionesM.setEnabled(false);
		tableGenerosM.setEnabled(false);
		
		cbGenM.setEnabled(false);
		
		
		btnAñaCancM.setEnabled(false);
		btnBorrarD.setEnabled(false);
		btnAct.setEnabled(false);
		btnQuitarM.setEnabled(false);
		btnAñaGenM.setEnabled(false);
		btnLimGenM.setEnabled(false);
		
		cargado.getCanciones().clear();
		cargado.getGeneros().clear();
		
		
		textArtistaM.setText("");
		textCancM.setText("");
		textGenM.setText("");
		textPrecioM.setText("");
		textStockM.setText("");
		dateChooserM.setDate(null);
		
		cargarTablaCanM(cargado.getCanciones());
		cargarTablaGenM(cargado.getGeneros());
	}












	private void cargarTablaGenI(ArrayList<Genero> generosI) {
		// TODO Auto-generated method stub
		List<Object[]> list = new ArrayList<Object[]>();
	    for (int i = 0; i < generosI.size(); i++) {
	        list.add(new Object[] { 
	                                 generosI.get(i).getGen(), 
	                              
	                         
	                              });

	    }
	    tableGeneroI.setModel(new DefaultTableModel(list.toArray(new Object[][] {}), 
	                        new String[] {"Genero"}));
	}
	
	
	
	private void cargarTablaCanI(ArrayList<String> canciones) {
		// TODO Auto-generated method stub
		List<Object[]> list = new ArrayList<Object[]>();
	    for (int i = 0; i < canciones.size(); i++) {
	        list.add(new Object[] { 
	        		canciones.get(i), 
	                              
	                         
	                              });

	    }
	    tableCancionI.setModel(new DefaultTableModel(list.toArray(new Object[][] {}), 
	                        new String[] {"Cancion"}));
	}
	
	
	
	
	
	
	private void cargarTablaCanM(ArrayList<String> canciones) {
		// TODO Auto-generated method stub
		List<Object[]> list = new ArrayList<Object[]>();
	    for (int i = 0; i < canciones.size(); i++) {
	        list.add(new Object[] { 
	                                  canciones.get(i), 
	                              
	                         
	                              });

	    }
	    tablecancionesM.setModel(new DefaultTableModel(list.toArray(new Object[][] {}), 
	                        new String[] {"Cancion"}));
	}






	private void cargarTablaGenM(ArrayList<Genero> generosM) {
		// TODO Auto-generated method stub
		List<Object[]> list = new ArrayList<Object[]>();
	    for (int i = 0; i < generosM.size(); i++) {
	        list.add(new Object[] { 
	                                 generosM.get(i).getGen(), 
	                              
	                         
	                              });

	    }
	    tableGenerosM.setModel(new DefaultTableModel(list.toArray(new Object[][] {}), 
	                        new String[] {"Genero"}));
	}

	






	private void cargarUsuarios() {
		// cargar usuarios inactivos en la tabla
		inac= manager.getInactivos();
		List<Object[]> list = new ArrayList<Object[]>();
	   for (int i = 0; i < inac.size(); i++) {
	        list.add(new Object[] { 
	                                 inac.get(i).getDni(),
	                                 inac.get(i).getNomUsu(),
	                                 inac.get(i).getEmail(),
	                                 inac.get(i).getBalance()
	                              
	                         
	                              });
	    }
	    tableUsu.setModel(new DefaultTableModel(list.toArray(new Object[][] {}), 
	                        new String[] {"DNI","Nombre Usuario","Email","Balance"}));
		
	}



	private void cargarTitulos() {
		// cargar los titulos de todas las peliculas existentes en la combo
		todosD=manager.getDiscos();
		cbTitulo.removeAllItems();
		for (int i = 0; i < todosD.size(); i++) {
			cbTitulo.addItem(todosD.get(i).getTitulo());
			
		}
		cbTitulo.setSelectedIndex(-1);
		
	}

	private void cargarGeneros() {
		// TODO Auto-generated method stub
		cbGenI.removeAllItems();
		cbGenM.removeAllItems();
		Genero aux;
		boolean esta;
		for(int i = 0; i < todosD.size(); i++) {
			
			for (int j = 0; j < todosD.get(i).getGeneros().size(); j++) {
				esta=false;
					for (int z = 0; z <generos.size(); z++) {
							if(generos.get(z).getGen().equals(todosD.get(i).getGeneros().get(j).getGen())) {
								
								esta=true;
								break;
							}
					}
						if(!esta) {
							aux=new Genero();
							aux=todosD.get(i).getGeneros().get(j);
							generos.add(aux);
						}
					
				
			}
		}
		for (int w = 0; w < generos.size(); w++) {
			cbGenI.addItem(generos.get(w).getGen());
			cbGenM.addItem(generos.get(w).getGen());
		}
	
		cbGenI.setSelectedIndex(-1);
		cbGenM.setSelectedIndex(-1);
		
	}

	
	
	
	private void desconectarse() {
		int response = JOptionPane.showConfirmDialog (null,"Seguro que quieres salir?","Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(response == JOptionPane.YES_OPTION){
			log.dispose();
			this.dispose();
		}else if(response == JOptionPane.NO_OPTION) {
			this.dispose();
			VentAdmin Usu = new VentAdmin(log);
			Usu.setResizable(false);
			Usu.setLocationRelativeTo(null);
			Usu.setVisible(true);
		}
		
	}
}
