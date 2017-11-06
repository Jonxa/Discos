package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import control.Manager;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class VentBD extends JDialog implements ActionListener {
	
	
	
	
	
	private static final long serialVersionUID = 1L;
	private JButton btnBD;
	private Login log;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxBD;

	@SuppressWarnings("unchecked")
	public VentBD(Login login) {
		super(login);
		setModal(true);
		log=login;
		setTitle("Elige BD");
		
		
		this.addWindowListener(new WindowAdapter() {
			   public void windowClosing(WindowEvent evt) {
				   desconectarse();
			   }
			  });
		setBounds(100, 100, 450, 137);
		getContentPane().setLayout(null);
		
		JLabel lblBDopc = new JLabel("Elige base de datos:");
		lblBDopc.setBounds(10, 38, 135, 14);
		getContentPane().add(lblBDopc);
		
		
		comboBoxBD = new JComboBox<String>();
		comboBoxBD.addItem("Oracle");
		comboBoxBD.setSelectedIndex(-1);
		comboBoxBD.setBounds(147, 35, 146, 20);
		getContentPane().add(comboBoxBD);
		
		btnBD = new JButton("Aceptar");
		btnBD.setBounds(322, 34, 89, 23);
		getContentPane().add(btnBD);
		
		btnBD.addActionListener(this);

	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(btnBD)) {
			Manager manager= new Manager();
				if(comboBoxBD.getSelectedIndex()==0) {
					manager.setDataBase(0);
					this.dispose();
					
				}else {
					JOptionPane.showMessageDialog(null, "Elige alguna Base de Datos");
				}
		}
	}
	
	private void desconectarse() {
		this.dispose();
		VentBD bd = new VentBD(log);
		bd.setResizable(false);
		bd.setLocationRelativeTo(null);
		bd.setVisible(true);
	}
}
