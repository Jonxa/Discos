package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import control.Manager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Toolkit;



public class Login extends JFrame implements ActionListener {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JTextField textFieldNomUsu;
	private JPasswordField passwordFieldContra;
	private JButton btnRegistrarse;
	private JButton btnLogin;


	
	public Login() throws Exception {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Public\\Pictures\\Sample Pictures\\Penguins.jpg"));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Login");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNombreDeUsuario = new JLabel("Nombre de usuario:");
		lblNombreDeUsuario.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombreDeUsuario.setBounds(27, 70, 147, 14);
		contentPane.add(lblNombreDeUsuario);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblContrasea.setBounds(27, 132, 147, 14);
		contentPane.add(lblContrasea);

		textFieldNomUsu = new JTextField();
		textFieldNomUsu.setBounds(203, 69, 162, 23);
		contentPane.add(textFieldNomUsu);
		textFieldNomUsu.setColumns(10);

		passwordFieldContra = new JPasswordField();
		passwordFieldContra.setBounds(203, 126, 162, 23);
		contentPane.add(passwordFieldContra);

		btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnRegistrarse.setBounds(69, 204, 105, 32);
		contentPane.add(btnRegistrarse);
		
		btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLogin.setBounds(233, 204, 105, 32);
		contentPane.add(btnLogin);
		
		btnRegistrarse.addActionListener(this);
		btnLogin.addActionListener(this);
		
		elegirBD();
		
		 

	}

	private void elegirBD() {
		// TODO Auto-generated method stub
		
		try {
			VentBD bd = new VentBD(this);
			bd.setResizable(false);
			bd.setLocationRelativeTo(null);
			bd.setVisible(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void actionPerformed(ActionEvent e) {
		Manager manager = new Manager();
		if (e.getSource().equals(btnLogin)) {
		
	
			String nombreUsu = textFieldNomUsu.getText();
			String passUsu = new String(passwordFieldContra.getPassword());
			
			
		try {	
			if(manager.esAdmin(nombreUsu, passUsu)) {
				
				VentAdmin ad = new VentAdmin(this);
				ad.setResizable(false);
				ad.setLocationRelativeTo(null);
				ad.setVisible(true);

				
			}else if(manager.esUsuario(nombreUsu,passUsu)) {
				VentUsu usu = new VentUsu(this, nombreUsu);
				usu.setResizable(false);
				usu.setLocationRelativeTo(null);
				usu.setVisible(true);
				
			}else {
				JOptionPane.showMessageDialog(this, "Nombre de Usuario o contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		} catch (Exception w) {
			w.printStackTrace();
		}
			
			
	
		}

		if (e.getSource().equals(btnRegistrarse)) {
			RegistrarUsuario nuevoUsu = new RegistrarUsuario(this);
			nuevoUsu.setResizable(false);
			nuevoUsu.setLocationRelativeTo(null);
			nuevoUsu.setVisible(true);
		}

	}
}