package view.paciente;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Optional;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import org.bson.Document;
import controller.Controller_Interfaz;

public class VentanaLista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MaskFormatter mascara;
	private JLabel lblDNI;
	private JButton btnComprobar;
	private JFormattedTextField formattedDni;
	private final Controller_Interfaz controllerInterfaz = new Controller_Interfaz();
	private JLabel lblNombreAtributo;
	private JTextField textFieldNombreAtributo;
	private JTextField textFieldLista;
	private JLabel lblLista;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private VentanaOpcionAnadirPaciente voa;
	private JLabel lblMensaje;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLista frame = new VentanaLista();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaLista() {
		setBackground(new Color(230, 230, 250));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 489, 341);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblDNI = new JLabel("DNI");
		lblDNI.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDNI.setBounds(39, 10, 55, 40);
		contentPane.add(lblDNI);
		
		btnComprobar = new JButton("Comprobar");
		btnComprobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnComprobar == e.getSource()) {
					Optional<Document> pacientes = controllerInterfaz.findByDni(formattedDni.getText());
					
					if(pacientes.isPresent()) {
						lblNombreAtributo.setVisible(true);
						textFieldNombreAtributo.setVisible(true);
					}else{
						String mensaje = "El paciente con DNI " + formattedDni.getText() + " no existe "; 
						JOptionPane.showMessageDialog(null, mensaje, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnComprobar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnComprobar.setBounds(297, 19, 98, 27);
		contentPane.add(btnComprobar);
		
		try {
            mascara = new MaskFormatter("########?");
            mascara.setValidCharacters("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            formattedDni = new JFormattedTextField(mascara);
    		formattedDni.setBounds(118, 21, 148, 26);
    		contentPane.add(formattedDni);
    		
    		lblNombreAtributo = new JLabel("Introduzca el nombre del atributo");
    		lblNombreAtributo.setVisible(false);
    		lblNombreAtributo.setFont(new Font("Tahoma", Font.PLAIN, 14));
    		lblNombreAtributo.setBounds(10, 76, 213, 27);
    		contentPane.add(lblNombreAtributo);
    		
    		textFieldNombreAtributo = new JTextField();
    		textFieldNombreAtributo.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				String atributo = textFieldNombreAtributo.getText().toString();
    				if(!atributo.equals("")) {
    					lblLista.setVisible(true);
    					textFieldLista.setVisible(true);
    				}else {
    					lblLista.setVisible(false);
    					textFieldLista.setVisible(false);
    				}
    			}
    		});
    		textFieldNombreAtributo.setVisible(false);
    		textFieldNombreAtributo.setBounds(233, 75, 182, 34);
    		contentPane.add(textFieldNombreAtributo);
    		textFieldNombreAtributo.setColumns(10);
    		
    		lblLista = new JLabel("Introduzca la lista de los valores\r\n");
    		lblLista.setVisible(false);
    		lblLista.setFont(new Font("Tahoma", Font.PLAIN, 14));
    		lblLista.setBounds(10, 133, 213, 27);
    		contentPane.add(lblLista);
    		
    		textFieldLista = new JTextField();
    		textFieldLista.setVisible(false);
    		textFieldLista.setColumns(10);
    		textFieldLista.setBounds(233, 132, 182, 34);
    		contentPane.add(textFieldLista);
    		
    		btnCancelar = new JButton("Cancelar\r\n");
    		btnCancelar.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				voa = new VentanaOpcionAnadirPaciente();
    				voa.setVisible(true);
    				dispose();
    			}
    		});
    		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
    		btnCancelar.setBounds(98, 196, 103, 34);
    		contentPane.add(btnCancelar);
    		
    		btnAceptar = new JButton("Aceptar");
    		btnAceptar.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				
					Optional<Document> pacientes = controllerInterfaz.findByDni(formattedDni.getText());
					String atributo = textFieldNombreAtributo.getText();
    				String listas = textFieldLista.getText();
    				String [] lista = listas.split(" ");
    				Boolean anadido = controllerInterfaz.anadirLista(pacientes, atributo, lista);
    				if(anadido == true) {
						lblMensaje.setText("El paciente ha sido actualizado con exito");
						lblMensaje.setForeground(Color.GREEN);
					}else {
						lblMensaje.setText("El paciente no ha sido actualizado con exito");
						lblMensaje.setForeground(Color.RED);
					}

    			}
    		});
    		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 14));
    		btnAceptar.setBounds(245, 196, 103, 34);
    		contentPane.add(btnAceptar);
    		
    		lblMensaje = new JLabel("");
    		lblMensaje.setFont(new Font("Tahoma", Font.PLAIN, 12));
    		lblMensaje.setBounds(10, 273, 465, 21);
    		contentPane.add(lblMensaje);

    		
    		
        } catch (ParseException e) {
            e.printStackTrace();
        }
		
	}
}
