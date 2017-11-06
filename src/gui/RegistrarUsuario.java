package gui;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import clases.Usuario;
import control.Manager;

import javax.swing.JSeparator;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;


public class RegistrarUsuario extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;

	private JTextField textFieldNombre;
	private JTextField textFieldPrimerApellido;
	private JTextField textFieldDireccion;
	private JTextField textFieldTelefono;
	private JTextField textFieldEmail;
	private JTextField textFieldNumeroCuenta;
	private JTextField textFieldNombreUsuario;
	private JPasswordField passwordFieldContra;
	private JPasswordField passwordFieldRepiteContra;
	private JTextField textFieldSegundoApellido;
	
	private JButton btnCancelar;
	private JButton btnCrearUsu;
	private JButton btnLimpiarTodo;
	
	private JTextField textFieldDni;
	private Login log;
	private JSeparator separator_1;
	
	


	
	public RegistrarUsuario(Login login) {
		super(login);
		setModal(true);
		log=login;
		
		setTitle("Registro de usuario");
		this.addWindowListener(new WindowAdapter() {
			   public void windowClosing(WindowEvent evt) {
				   desconectarse();
			   }
			  });

		
		setBounds(100, 100, 764, 524);
		getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombre.setBounds(23, 85, 86, 14);
		getContentPane().add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(205, 84, 100, 20);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Primer apellido:");
		lblApellidos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblApellidos.setBounds(23, 124, 133, 14);
		getContentPane().add(lblApellidos);
		
		textFieldPrimerApellido = new JTextField();
		textFieldPrimerApellido.setBounds(205, 123, 100, 20);
		getContentPane().add(textFieldPrimerApellido);
		textFieldPrimerApellido.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDireccion.setBounds(26, 211, 83, 14);
		getContentPane().add(lblDireccion);
		
		textFieldDireccion = new JTextField();
		textFieldDireccion.setBounds(205, 210, 100, 20);
		getContentPane().add(textFieldDireccion);
		textFieldDireccion.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTelefono.setBounds(23, 252, 86, 14);
		getContentPane().add(lblTelefono);
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setBounds(205, 251, 100, 20);
		getContentPane().add(textFieldTelefono);
		textFieldTelefono.setColumns(10);
		
		JLabel lblEmail = new JLabel("e-mail:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmail.setBounds(23, 295, 86, 14);
		getContentPane().add(lblEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(205, 294, 100, 20);
		getContentPane().add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		JLabel lblNumeroDeCuenta = new JLabel("N\u00BA de cuenta bancaria:");
		lblNumeroDeCuenta.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNumeroDeCuenta.setBounds(23, 338, 172, 14);
		getContentPane().add(lblNumeroDeCuenta);
		
		textFieldNumeroCuenta = new JTextField();
		textFieldNumeroCuenta.setBounds(205, 337, 100, 20);
		getContentPane().add(textFieldNumeroCuenta);
		textFieldNumeroCuenta.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(23, 391, 725, 2);
		getContentPane().add(separator);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de  usuario:");
		lblNombreDeUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombreDeUsuario.setBounds(406, 84, 158, 14);
		getContentPane().add(lblNombreDeUsuario);
		
		textFieldNombreUsuario = new JTextField();
		textFieldNombreUsuario.setBounds(574, 83, 121, 20);
		getContentPane().add(textFieldNombreUsuario);
		textFieldNombreUsuario.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblContrasea.setBounds(404, 123, 86, 14);
		getContentPane().add(lblContrasea);
		
		passwordFieldContra = new JPasswordField();
		passwordFieldContra.setBounds(574, 122, 121, 20);
		getContentPane().add(passwordFieldContra);
		
		JLabel lblRepiteLaContrasea = new JLabel("Repite la contrase\u00F1a:");
		lblRepiteLaContrasea.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRepiteLaContrasea.setBounds(402, 164, 162, 14);
		getContentPane().add(lblRepiteLaContrasea);
		
		passwordFieldRepiteContra = new JPasswordField();
		passwordFieldRepiteContra.setBounds(574, 163, 121, 20);
		getContentPane().add(passwordFieldRepiteContra);
		
		JLabel lblNewLabel = new JLabel("Segundo apellido:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(23, 167, 133, 14);
		getContentPane().add(lblNewLabel);
		
		textFieldSegundoApellido = new JTextField();
		textFieldSegundoApellido.setBounds(205, 166, 100, 20);
		getContentPane().add(textFieldSegundoApellido);
		textFieldSegundoApellido.setColumns(10);
		
		btnLimpiarTodo = new JButton("Limpiar todo");
		btnLimpiarTodo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLimpiarTodo.setBounds(467, 236, 121, 33);
		getContentPane().add(btnLimpiarTodo);
		btnLimpiarTodo.addActionListener(this);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCancelar.setBounds(446, 417, 142, 41);
		getContentPane().add(btnCancelar);
		btnCancelar.addActionListener(this);
		
		btnCrearUsu = new JButton("Crear Usuario");
		btnCrearUsu.setBounds(171, 418, 137, 41);
		getContentPane().add(btnCrearUsu);
		
		JLabel lblDni = new JLabel("Dni:");
		lblDni.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDni.setBounds(23, 45, 86, 14);
		getContentPane().add(lblDni);
		
		textFieldDni = new JTextField();
		textFieldDni.setColumns(10);
		textFieldDni.setBounds(205, 44, 100, 20);
		getContentPane().add(textFieldDni);
		
		separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(370, 0, 11, 393);
		getContentPane().add(separator_1);
		btnCrearUsu.addActionListener(this);
		

	}



	@Override
	public void actionPerformed(ActionEvent e) {
		Manager manager = new Manager();
				
		if(e.getSource().equals(btnCrearUsu)) {
			boolean correcto=true;
			Usuario usu = new Usuario();
			
			if (this.comprobarVacios()) {
				
				JOptionPane.showMessageDialog(this, "Es obligatorio rellenar todos los campos", "Error", JOptionPane.WARNING_MESSAGE);
		
			}else{
				
				if(new String(passwordFieldContra.getPassword()).equalsIgnoreCase(new String(passwordFieldRepiteContra.getPassword()))) {
					usu.setDni(textFieldDni.getText());
					usu.setNombre(textFieldNombre.getText());
					usu.setApel1(textFieldPrimerApellido.getText());
					usu.setApel2(textFieldSegundoApellido.getText());
					usu.setDir(textFieldDireccion.getText());
					usu.setTfno(textFieldTelefono.getText());
					if (textFieldEmail.getText().matches("^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,4}$")){
						usu.setEmail(textFieldEmail.getText());
					}else{ //comprobacion email
						JOptionPane.showMessageDialog(this, "El email es incorrecto", "Error", JOptionPane.ERROR_MESSAGE);	
						correcto=false;
					}
					usu.setNumCuenta(textFieldNumeroCuenta.getText());
					usu.setNomUsu(textFieldNombreUsuario.getText());
					usu.setContraUsu(new String(passwordFieldContra.getPassword()));
				}else{//Si la contraseña no es igual entra aqui
					JOptionPane.showMessageDialog(this, "La contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
					correcto=false;
				}
				
				if(correcto) {
					try {
						if( manager.existeUsuario(usu.getDni(),usu.getNomUsu())) {
							manager.insertarUsuario(usu);
							JOptionPane.showMessageDialog(this, "El usuario ha sido insertado existosamente");
							this.desconectar();
						}else {
							JOptionPane.showMessageDialog(this, "Ese nombre de usuario o el dni ya existen", "Error", JOptionPane.ERROR_MESSAGE);
						}
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}
				
					
			}
			
		
		}else if(e.getSource().equals(btnCancelar)){
			this.desconectar();
		}
		
		
		else if(e.getSource().equals(btnLimpiarTodo)){
			this.limpiar();
		}
	
	}
	
	
	
	
	
	
	
	 



	@SuppressWarnings("unlikely-arg-type")
	private boolean comprobarVacios() {
		   boolean z=false;
		   if (textFieldDni.getText().equals("")||textFieldNombre.getText().equals("") || textFieldPrimerApellido.getText().equals("") || textFieldSegundoApellido.getText().equals("")
					|| textFieldDireccion.getText().equals("") || textFieldTelefono.getText().equals("")
					|| textFieldEmail.equals("") || textFieldNumeroCuenta.equals("")) {
			   z=true;
		   }   
		   return z;
	   }
	
	
	
	
	
	  //LimpiarCampos
	 
	private void limpiar() {
		textFieldDni.setText("");
		textFieldNombre.setText("");
		textFieldPrimerApellido.setText("");
		textFieldDireccion.setText("");
		textFieldTelefono.setText("");
		textFieldEmail.setText("");
		textFieldNumeroCuenta.setText("");
		textFieldNombreUsuario.setText("");
		textFieldSegundoApellido.setText("");
	}

	
	
	//Desconectarse
	
	private void desconectarse(){
		int response = JOptionPane.showConfirmDialog (null,"Seguro que quieres salir?","Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(response == JOptionPane.YES_OPTION){
			this.dispose();
		}else if(response == JOptionPane.NO_OPTION) {
			this.dispose();
			RegistrarUsuario nuevoUsu = new RegistrarUsuario(log);
			nuevoUsu.setResizable(false);
			nuevoUsu.setLocationRelativeTo(null);
			nuevoUsu.setVisible(true);
		}
	
	}
	  private void desconectar() {
			// TODO Auto-generated method stub
			this.dispose();
		}
}



