package gui;


import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import clases.Usuario;
import control.Manager;

import javax.swing.JButton;

public class Deposito extends JDialog implements ActionListener {
	private JTextField textFieldNumC;
	private JTextField textFieldCarga;
	private JButton btnDepositar;
	private Usuario u;
	
	public Deposito(VentUsu usu,Usuario u) {
		super(usu);
		setModal(true);
		setTitle("Deposito");
		this.u=u;
		setBounds(100, 100, 283, 215);
		getContentPane().setLayout(null);
		
		JLabel lblNumeroCuenta = new JLabel("Numero Cuenta:");
		lblNumeroCuenta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNumeroCuenta.setBounds(27, 31, 123, 14);
		getContentPane().add(lblNumeroCuenta);
		
		JLabel lblNewLabel = new JLabel("Importe a cargar:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(27, 70, 100, 14);
		getContentPane().add(lblNewLabel);
		
		textFieldNumC = new JTextField();
		textFieldNumC.setEditable(false);
		textFieldNumC.setEnabled(false);
		textFieldNumC.setBounds(133, 28, 86, 20);
		getContentPane().add(textFieldNumC);
		textFieldNumC.setColumns(10);
		
		textFieldCarga = new JTextField();
		textFieldCarga.setBounds(137, 67, 46, 20);
		getContentPane().add(textFieldCarga);
		textFieldCarga.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u20AC");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(193, 70, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		btnDepositar = new JButton("Depositar");
		btnDepositar.setBounds(79, 115, 103, 30);
		getContentPane().add(btnDepositar);
		
		textFieldNumC.setText(u.getNumCuenta());
		btnDepositar.addActionListener(this);

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Manager manager = new Manager();
		if(e.getSource().equals(btnDepositar)) {
			if(textFieldCarga.getText().equals("0")||textFieldCarga.getText().equals("")||textFieldCarga.getText().contains(",")) {
				JOptionPane.showMessageDialog(this, "Revise el campo de importe", "Error", JOptionPane.ERROR_MESSAGE);
			}else {
					Float x= Float.parseFloat(textFieldCarga.getText());
					u.setBalance(u.getBalance()+ x);
					manager.ActualizarUsuario(u);
					JOptionPane.showMessageDialog(this, "Cantidad ingresada", "Insertado", JOptionPane.INFORMATION_MESSAGE);
					this.dispose();
				}
				
			}
			
		}
	}

