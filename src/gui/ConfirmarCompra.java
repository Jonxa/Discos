package gui;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import clases.Compra;
import clases.Disco;
import clases.LineaCompra;
import clases.Usuario;
import control.Manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JScrollPane;

public class ConfirmarCompra extends JDialog implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tableCompra;
	private JButton btnConfirmar ;
	private JButton btnQuitar;
	private Usuario usu1;
	private ArrayList<LineaCompra> util;


	public ConfirmarCompra(VentUsu usu, ArrayList<LineaCompra> compras,Usuario u) {
		super(usu);
		setModal(true);
		usu1=u;
		util=compras;
		setBounds(100, 100, 570, 223);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 24, 487, 96);
		getContentPane().add(scrollPane);
		
		tableCompra = new JTable();
		scrollPane.setViewportView(tableCompra);
		tableCompra.setDefaultEditor(Object.class, null);
		
		btnConfirmar = new JButton("Confirmar Compra");
		btnConfirmar.setBounds(358, 151, 154, 23);
		getContentPane().add(btnConfirmar);
		
		btnQuitar = new JButton("Quitar Disco");
		btnQuitar.setBounds(40, 151, 142, 23);
		getContentPane().add(btnQuitar);
		
		btnConfirmar.addActionListener(this);
		btnQuitar.addActionListener(this);
		
		cargarCompras(compras);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Manager manager = new Manager();
		Compra com=new Compra();
		com.setIDCliente(usu1.getDni());
		com.setCompras(util);
		
		if(e.getSource().equals(btnQuitar)) {
			//Quitar el disco de la tabla y de la clase compra
			if(tableCompra.getSelectedRow()!= -1) {
				for (int i = 0; i < util.size(); i++) {
					if(util.get(i).getTitulo().equals( tableCompra.getModel().getValueAt(tableCompra.getSelectedRow(), 0).toString())) {
						util.remove(i);
						com.setCompras(util);
						cargarCompras(util);
						break;
					}
				}
			}else {
				JOptionPane.showMessageDialog(this, "Selecciona un Disco", "Error", JOptionPane.ERROR_MESSAGE);
			}
			 
			
		}else if(e.getSource().equals(btnConfirmar)) {
			//Mirar si tiene el balance suficiente para hacer la compra si es asi sacar dialog con compra hecha si no error
			if(com.getImporte() < usu1.getBalance()) {
				//Realizar compra
				int response = JOptionPane.showConfirmDialog (null,"La compra asciende a "+com.getImporte()+", Comprar?","Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(response == JOptionPane.YES_OPTION){
					manager.insertarCompra(com);
					usu1.setBalance(usu1.getBalance()-com.getImporte());
					manager.ActualizarUsuario(usu1);
					util.clear();
					this.dispose();
				}
			}else {
				  JOptionPane.showMessageDialog(null, "No hay dinero para realizar la compra", "Warning",JOptionPane.WARNING_MESSAGE);
			}
			
		}
	}
	
	
	private void cargarCompras(ArrayList<LineaCompra> com) {
		// TODO Auto-generated method stub
		List<Object[]> list = new ArrayList<Object[]>();
	    for (int i = 0; i < com.size(); i++) {
	        list.add(new Object[] { 
	                                 com.get(i).getTitulo(), 
	                                 com.get(i).getCantidad(),
	                                 com.get(i).getPrecio()+"€",
	                                (com.get(i).getCantidad() * com.get(i).getPrecio())+"€"
	                         
	                              });

	    }
	    tableCompra.setModel(new DefaultTableModel(list.toArray(new Object[][] {}), 
	                        new String[] {"Titulo", "Cantidad", "Precio","Precio Total"}));
	}
	
	
	
	
	
}
