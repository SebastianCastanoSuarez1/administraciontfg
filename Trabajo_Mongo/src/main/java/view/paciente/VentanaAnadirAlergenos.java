package view.paciente;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaAnadirAlergenos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldAlergeno;
	private MaskFormatter mascara;
	JFormattedTextField formattedDni;
	JLabel lblDNI;
	JButton btnComprobar;
	JLabel lblAlergeno;
	JButton btnCancelar;
	JButton btnAceptar;
	JLabel lblMensaje;
	private final Controller_Interfaz controllerInterfaz = new Controller_Interfaz();
	VentanaPrincipalPaciente vp;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAnadirAlergenos frame = new VentanaAnadirAlergenos();
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
	public VentanaAnadirAlergenos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 544, 325);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblDNI = new JLabel("DNI");
		lblDNI.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDNI.setBounds(70, 38, 34, 21);
		contentPane.add(lblDNI);

		try {
			mascara = new MaskFormatter("########?");
			mascara.setValidCharacters("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
			formattedDni = new JFormattedTextField(mascara);
			formattedDni.setBounds(136, 42, 177, 20);
			contentPane.add(formattedDni);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		btnComprobar = new JButton("Comprobar");
		btnComprobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnComprobar == e.getSource()) {
					Optional<Document> pacientes = controllerInterfaz.findByDni(formattedDni.getText());

					if (pacientes.isPresent()) {
						lblAlergeno.setVisible(true);
						textFieldAlergeno.setVisible(true);

					} else {
						String mensaje = "El paciente con DNI " + formattedDni.getText() + " no existe ";
						JOptionPane.showMessageDialog(null, mensaje, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnComprobar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnComprobar.setBounds(342, 38, 113, 25);
		contentPane.add(btnComprobar);

		lblAlergeno = new JLabel("Meta alergenos de esta forma: alergeno espacio aleregeno");
		lblAlergeno.setVisible(false);
		lblAlergeno.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAlergeno.setBounds(10, 103, 337, 35);
		contentPane.add(lblAlergeno);

		textFieldAlergeno = new JTextField();
		textFieldAlergeno.setVisible(false);
		textFieldAlergeno.setBounds(357, 112, 150, 19);
		contentPane.add(textFieldAlergeno);
		textFieldAlergeno.setColumns(10);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp = new VentanaPrincipalPaciente();
				vp.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancelar.setBounds(117, 189, 85, 33);
		contentPane.add(btnCancelar);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Optional<Document> pacientes = controllerInterfaz.findByDni(formattedDni.getText());
		        String alergenos = textFieldAlergeno.getText();
		        String[] alergenosList = alergenos.split(" "); // Dividir en palabras, no en caracteres
		        
		        // Dividir en palabras, no en caracteres
		        if(btnAceptar == e.getSource()) {
		        	Boolean anadido = controllerInterfaz.anadirAlergenos(pacientes, alergenosList);
		        	
		            if(anadido== true) {
						lblMensaje.setText("El paciente ha sido actualizado con exito");
						lblMensaje.setForeground(Color.GREEN);
					}else {
						lblMensaje.setText("El paciente no ha sido actualizado con exito");
						lblMensaje.setForeground(Color.RED);
					}
		        }
			}
		});
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAceptar.setBounds(288, 189, 85, 33);
		contentPane.add(btnAceptar);

		lblMensaje = new JLabel("");
		lblMensaje.setBounds(136, 248, 259, 21);
		contentPane.add(lblMensaje);
	}
}
