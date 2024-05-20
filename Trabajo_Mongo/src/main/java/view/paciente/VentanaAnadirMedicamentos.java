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

public class VentanaAnadirMedicamentos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldMedicamento;
	private MaskFormatter mascara;
	JFormattedTextField formattedDni;
	JLabel lblDNI;
	JButton btnComprobar;
	JLabel lblMedicamento;
	JButton btnCancelar;
	JButton btnAceptar;
	JLabel lblMensaje;
	private final Controller_Interfaz controllerInterfaz = new Controller_Interfaz();
	VentanaPrincipalPaciente vp;
	private JLabel lblAadirMedicamentos;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAnadirMedicamentos frame = new VentanaAnadirMedicamentos();
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
	public VentanaAnadirMedicamentos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 584, 402);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblDNI = new JLabel("DNI");
		lblDNI.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDNI.setBounds(80, 83, 34, 21);
		contentPane.add(lblDNI);

		try {
			mascara = new MaskFormatter("########?");
			mascara.setValidCharacters("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
			formattedDni = new JFormattedTextField(mascara);
			formattedDni.setBounds(146, 87, 177, 20);
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
						lblMedicamento.setVisible(true);
						textFieldMedicamento.setVisible(true);

					} else {
						String mensaje = "El paciente con DNI " + formattedDni.getText() + " no existe ";
						JOptionPane.showMessageDialog(null, mensaje, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnComprobar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnComprobar.setBounds(352, 83, 113, 25);
		contentPane.add(btnComprobar);

		lblMedicamento = new JLabel("Meta medicamentos de esta forma: medicamento espacio medicamento\r\n");
		lblMedicamento.setVisible(false);
		lblMedicamento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMedicamento.setBounds(2, 149, 401, 35);
		contentPane.add(lblMedicamento);

		textFieldMedicamento = new JTextField();
		textFieldMedicamento.setVisible(false);
		textFieldMedicamento.setBounds(413, 158, 150, 19);
		contentPane.add(textFieldMedicamento);
		textFieldMedicamento.setColumns(10);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp = new VentanaPrincipalPaciente();
				vp.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancelar.setBounds(146, 234, 85, 33);
		contentPane.add(btnCancelar);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Optional<Document> pacientes = controllerInterfaz.findByDni(formattedDni.getText());
		        String medicamentos = textFieldMedicamento.getText();
		        String[] medicamentosList = medicamentos.split(" "); // Dividir en palabras, no en caracteres
		        
		        if(btnAceptar == e.getSource()) {
		        	Boolean anadido = controllerInterfaz.anadirMedicamentos(pacientes, medicamentosList);
		        	
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
		btnAceptar.setBounds(318, 234, 85, 33);
		contentPane.add(btnAceptar);

		lblMensaje = new JLabel("");
		lblMensaje.setBounds(146, 293, 257, 21);
		contentPane.add(lblMensaje);
		
		lblAadirMedicamentos = new JLabel("Añadir medicamentos al paciente\r\n");
		lblAadirMedicamentos.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAadirMedicamentos.setBounds(168, 24, 262, 22);
		contentPane.add(lblAadirMedicamentos);
	}
}
