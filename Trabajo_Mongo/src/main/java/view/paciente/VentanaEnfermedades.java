package view.paciente;

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
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import org.bson.Document;

import controller.Controller_Interfaz;

public class VentanaEnfermedades extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> comboBoxDetalles;
	private MaskFormatter mascara;
	private JFormattedTextField formattedFechaBaja;
	private JButton btnCancelar, btnAcepatr;
	private JLabel lblEnfermedad, lblFechaBaja, lblDetalles, lblTratamiento, lblMedicamentos, lblInforme;
	private JTextField textFieldEnfermedad, textFieldTratamiento, textFieldMedicamentos, textFieldinforme;
	private final Controller_Interfaz controllerInterfaz = new Controller_Interfaz();
	VentanaPrincipalPaciente paciente;
	JLabel lblDNI;
	JButton btnComprobar;
	private JLabel lblMensaje;
	JFormattedTextField formattedDni;
	private JLabel lblAadirEnfermedadesAl;
	JLabel lblFechaAlta;
	JFormattedTextField formattedFechaAlta;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEnfermedades frame = new VentanaEnfermedades();
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
	public VentanaEnfermedades() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 504, 502);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblEnfermedad = new JLabel("Enfermedad\r\n");
		lblEnfermedad.setVisible(false);
		lblEnfermedad.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEnfermedad.setBounds(78, 124, 112, 20);
		contentPane.add(lblEnfermedad);

		textFieldEnfermedad = new JTextField();
		textFieldEnfermedad.setVisible(false);
		textFieldEnfermedad.setColumns(10);
		textFieldEnfermedad.setBounds(200, 126, 143, 20);
		contentPane.add(textFieldEnfermedad);

		lblFechaBaja = new JLabel("Fecha baja");
		lblFechaBaja.setVisible(false);
		lblFechaBaja.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFechaBaja.setBounds(78, 167, 112, 20);
		contentPane.add(lblFechaBaja);

		lblDetalles = new JLabel("Detalles");
		lblDetalles.setVisible(false);
		lblDetalles.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDetalles.setBounds(78, 241, 77, 14);
		contentPane.add(lblDetalles);

		comboBoxDetalles = new JComboBox<String>();
		comboBoxDetalles.setVisible(false);
		comboBoxDetalles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String opcion = comboBoxDetalles.getSelectedItem().toString();
				switch (opcion) {
				case "Si":
					lblTratamiento.setVisible(true);
					textFieldTratamiento.setVisible(true);
					lblMedicamentos.setVisible(true);
					textFieldMedicamentos.setVisible(true);
					textFieldinforme.setVisible(true);
					lblInforme.setVisible(true);

					break;
				case "No":
					break;
				}
			}
		});
		comboBoxDetalles.setModel(new DefaultComboBoxModel<String>(new String[] { "No", "Si" }));
		comboBoxDetalles.setBounds(194, 239, 53, 22);
		contentPane.add(comboBoxDetalles);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paciente = new VentanaPrincipalPaciente();
				paciente.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(129, 388, 89, 23);
		contentPane.add(btnCancelar);

		btnAcepatr = new JButton("Aceptar");
		btnAcepatr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnAcepatr == e.getSource()) {
			        Optional<Document> pacientes = controllerInterfaz.findByDni(formattedDni.getText());
			        Document enfermedades = crearDocumentosEnfermedades();
					Boolean anadido = controllerInterfaz.anadirVariables(pacientes,enfermedades);
		            if(anadido == true) {
						lblMensaje.setText("Enfermedades añadidas con exito");
						lblMensaje.setForeground(Color.GREEN);
					}else {
						lblMensaje.setText("Enfermedades no añadidas con exito");
						lblMensaje.setForeground(Color.RED);
					}

				}
			}

		});
		btnAcepatr.setBounds(296, 388, 89, 23);
		contentPane.add(btnAcepatr);

		lblTratamiento = new JLabel("Tratamiento");
		lblTratamiento.setVisible(false);
		lblTratamiento.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTratamiento.setBounds(78, 278, 112, 14);
		contentPane.add(lblTratamiento);

		textFieldTratamiento = new JTextField();
		textFieldTratamiento.setVisible(false);
		textFieldTratamiento.setColumns(10);
		textFieldTratamiento.setBounds(200, 277, 134, 20);
		contentPane.add(textFieldTratamiento);

		lblMedicamentos = new JLabel("Medicamentos");
		lblMedicamentos.setVisible(false);
		lblMedicamentos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMedicamentos.setBounds(78, 309, 112, 14);
		contentPane.add(lblMedicamentos);

		textFieldMedicamentos = new JTextField();
		textFieldMedicamentos.setVisible(false);
		textFieldMedicamentos.setColumns(10);
		textFieldMedicamentos.setBounds(200, 308, 134, 20);
		contentPane.add(textFieldMedicamentos);

		textFieldinforme = new JTextField();
		textFieldinforme.setVisible(false);
		textFieldinforme.setColumns(10);
		textFieldinforme.setBounds(200, 339, 134, 20);
		contentPane.add(textFieldinforme);

		lblInforme = new JLabel("Informe");
		lblInforme.setVisible(false);
		lblInforme.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblInforme.setBounds(78, 342, 94, 14);
		contentPane.add(lblInforme);
		try {
			mascara = new MaskFormatter("##/##/####");
			mascara.setValidCharacters("0123456789");
			formattedFechaBaja = new JFormattedTextField(mascara);
			formattedFechaBaja.setVisible(false);
			formattedFechaBaja.setBounds(200, 170, 143, 19);
			contentPane.add(formattedFechaBaja);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		lblDNI = new JLabel("DNI");
		lblDNI.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDNI.setBounds(63, 62, 34, 21);
		contentPane.add(lblDNI);

		btnComprobar = new JButton("Comprobar");
		btnComprobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnComprobar == e.getSource()) {
					Optional<Document> pacientes = controllerInterfaz.findByDni(formattedDni.getText());

					if (pacientes.isPresent()) {
						lblFechaBaja.setVisible(true);
						lblEnfermedad.setVisible(true);
						lblDetalles.setVisible(true);
						textFieldEnfermedad.setVisible(true);
						formattedFechaBaja.setVisible(true);
						comboBoxDetalles.setVisible(true);
						lblFechaAlta.setVisible(true);
						formattedFechaAlta.setVisible(true);

					} else {
						String mensaje = "El paciente con DNI " + formattedDni.getText() + " no existe ";
						JOptionPane.showMessageDialog(null, mensaje, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnComprobar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnComprobar.setBounds(332, 58, 113, 25);
		contentPane.add(btnComprobar);
		
		lblMensaje = new JLabel("");
		lblMensaje.setBounds(129, 435, 241, 20);
		contentPane.add(lblMensaje);
		
		try {
			
			mascara = new MaskFormatter("########?");
			mascara.setValidCharacters("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
			formattedDni = new JFormattedTextField(mascara);
			formattedDni.setBounds(129, 62, 177, 20);
			contentPane.add(formattedDni);
			
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		lblAadirEnfermedadesAl = new JLabel("Añadir enfermedades al historial medico");
		lblAadirEnfermedadesAl.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAadirEnfermedadesAl.setBounds(82, 10, 343, 22);
		contentPane.add(lblAadirEnfermedadesAl);
		
		lblFechaAlta = new JLabel("Fecha alta");
		lblFechaAlta.setVisible(false);
		lblFechaAlta.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFechaAlta.setBounds(78, 201, 112, 20);
		contentPane.add(lblFechaAlta);
		
		try {
			mascara = new MaskFormatter("##/##/####");
			mascara.setValidCharacters("0123456789");
			formattedFechaAlta = new JFormattedTextField(mascara);
			formattedFechaAlta.setVisible(false);
			formattedFechaAlta.setBounds(200, 204, 143, 19);
			contentPane.add(formattedFechaAlta);
			
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}

	public Document crearDocumentosEnfermedades() {
		Optional<Document> pacientes = controllerInterfaz.findByDni(formattedDni.getText());

		String enfermedad = textFieldEnfermedad.getText();
		String fecha_baja = formattedFechaBaja.getText().toString();
		String fecha_alta = formattedFechaAlta.getText().toString();
		String medicamentos = textFieldMedicamentos.getText();
		String tratamiento = textFieldTratamiento.getText();
		String informe = textFieldinforme.getText();
		String[] historialMedicoMedicamentos = medicamentos.split(" ");
		Document enfermedades = controllerInterfaz.crearDocumentoEnfermedades(pacientes, enfermedad, fecha_baja,fecha_alta,
				historialMedicoMedicamentos, tratamiento, informe);
		return enfermedades;
	}
}
