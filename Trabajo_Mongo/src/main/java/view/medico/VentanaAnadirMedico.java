package view.medico;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Optional;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import org.bson.Document;

import controller.MedicoController_Interfaz;

public class VentanaAnadirMedico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFormattedTextField formattedDni;
	private JTextField textFieldNombre, textFieldApellidos;
	private JLabel lblDNI, lblNombre, lblApellidos, lblEspecialidad, lblAnioExperiencia, lblMensaje;
	private JButton btnCancelar, btnAceptar;
	private MaskFormatter mascara;
	private MedicoController_Interfaz medico = new MedicoController_Interfaz();
	private JComboBox<String> comboBoxEspecialidades;
	private JFormattedTextField formattedFechaIncorporacion;
	private VentanaPrincipalMedico principal;
	private JLabel lblTitulo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAnadirMedico frame = new VentanaAnadirMedico();
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
	public VentanaAnadirMedico() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 498, 398);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblDNI = new JLabel("DNI");
		lblDNI.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDNI.setBounds(28, 68, 52, 24);
		contentPane.add(lblDNI);

		try {
			mascara = new MaskFormatter("########?");
			mascara.setValidCharacters("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
			formattedDni = new JFormattedTextField(mascara);
			formattedDni.setBounds(74, 72, 138, 19);
			contentPane.add(formattedDni);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		lblNombre = new JLabel("Nombre\r\n");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombre.setBounds(227, 68, 63, 24);
		contentPane.add(lblNombre);

		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(300, 73, 127, 19);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		lblApellidos = new JLabel("Apellidos");
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblApellidos.setBounds(28, 132, 63, 20);
		contentPane.add(lblApellidos);

		textFieldApellidos = new JTextField();
		textFieldApellidos.setBounds(101, 132, 111, 21);
		contentPane.add(textFieldApellidos);
		textFieldApellidos.setColumns(10);

		lblEspecialidad = new JLabel("Especialidad");
		lblEspecialidad.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEspecialidad.setBounds(242, 137, 79, 15);
		contentPane.add(lblEspecialidad);

		comboBoxEspecialidades = new JComboBox<String>();
		comboBoxEspecialidades.setModel(new DefaultComboBoxModel<String>(new String[] { "", "Cirugia", "Medico Familia",
				"Traumatologia", "Dermatologia", "Oftalmologia", "Pediatria", "Reumatologia", "Neurologia",
				"Enfermeria", "Fisioterapia", "Gastroenterologia" }));
		comboBoxEspecialidades.setBounds(330, 137, 119, 21);
		contentPane.add(comboBoxEspecialidades);

		try {
			mascara = new MaskFormatter("##/##/####");
			mascara.setValidCharacters("0123456789");
			formattedFechaIncorporacion = new JFormattedTextField(mascara);
			formattedFechaIncorporacion.setBounds(227, 203, 138, 19);
			contentPane.add(formattedFechaIncorporacion);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		lblAnioExperiencia = new JLabel("Fecha de incorporacion");
		lblAnioExperiencia.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAnioExperiencia.setBounds(74, 199, 154, 24);
		contentPane.add(lblAnioExperiencia);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				principal = new VentanaPrincipalMedico();
				principal.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCancelar.setBounds(119, 279, 85, 21);
		contentPane.add(btnCancelar);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String dni = formattedDni.getText();
				String nombre = textFieldNombre.getText();
				String apellidos = textFieldApellidos.getText();
				String especialidad = comboBoxEspecialidades.getSelectedItem().toString();
				String fecha_incorporcion = formattedFechaIncorporacion.getText();
				Optional<Document> medicoDNI = medico.comprobarDni(dni);
				if (medicoDNI.isPresent()) {
					lblMensaje.setText("El medico con DNI " + dni + " ya esta añadido");
					lblMensaje.setForeground(Color.RED);
				} else {
					if (isValidDNI(formattedDni.getText())) {

						Document medicos = medico.anadirMedicoNuevo(dni, nombre, apellidos, especialidad,
								fecha_incorporcion);
						Boolean anadido = medico.salvarMedico(medicos);
						if (anadido == true) {
							lblMensaje.setText("El medico ha sido añadido con exito");
							lblMensaje.setForeground(Color.GREEN);
							Timer timer = new Timer(1500, new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									principal = new VentanaPrincipalMedico();
									principal.setVisible(true);
									dispose();
								}
							});
							timer.setRepeats(false);
							timer.start();
						} else {
							lblMensaje.setText("El medico no ha sido añadido con exito");
							lblMensaje.setForeground(Color.RED);
						}
					} else {
						JOptionPane.showMessageDialog(VentanaAnadirMedico.this, "El DNI " + formattedDni.getText() + " no es correcto");

					}

				}
			}
		});
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAceptar.setBounds(280, 279, 85, 21);
		contentPane.add(btnAceptar);

		lblMensaje = new JLabel("");
		lblMensaje.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMensaje.setBounds(90, 327, 310, 24);
		contentPane.add(lblMensaje);

		lblTitulo = new JLabel("Añadir medico");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitulo.setBounds(170, 20, 154, 19);
		contentPane.add(lblTitulo);

	}

	public static boolean isValidDNI(String dni) {
		if (dni == null || dni.length() != 9) {
			return false;
		}

		String numberPart = dni.substring(0, 8);
		if (!numberPart.matches("\\d{8}")) {
			return false;
		}

		char letter = dni.charAt(8);
		if (!Character.isLetter(letter)) {
			return false;
		}

		String validLetters = "TRWAGMYFPDXBNJZSQVHLCKE";
		int number = Integer.parseInt(numberPart);
		char correctLetter = validLetters.charAt(number % 23);

		return Character.toUpperCase(letter) == correctLetter;
	}
}
