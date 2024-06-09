package view.medico;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import org.bson.Document;

import controller.Controller_Interfaz;
import controller.MedicoController_Interfaz;

public class VentanaAnadirPacientesCargo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFormattedTextField formattedDni;
	private JButton btnComprobar, btnCancelar, btnAceptar;
	private JLabel lblDNI, lblDniPacientes, lblMensaje;
	private MaskFormatter mascara;
	private VentanaPrincipalMedico principal;
	private MedicoController_Interfaz medicoController = new MedicoController_Interfaz();
	private Controller_Interfaz pacienteController = new Controller_Interfaz();
	private JLabel lblTitulo;
	private JButton btnAnadirCampo;
	private JButton btnEliminarCampo;
	private int textFieldXPosition = 288;
	private int textFieldYPosition = 130;
	private final int textFieldHeight = 20;
	private final int textFieldSpacing = 10;
	private List<JFormattedTextField> formattedTextFields = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAnadirPacientesCargo frame = new VentanaAnadirPacientesCargo();
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
	public VentanaAnadirPacientesCargo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 534, 396);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblDNI = new JLabel("DNI");
		lblDNI.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDNI.setBounds(56, 68, 34, 21);
		contentPane.add(lblDNI);

		try {
			mascara = new MaskFormatter("########?");
			mascara.setValidCharacters("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
			formattedDni = new JFormattedTextField(mascara);
			formattedDni.setBounds(130, 72, 177, 20);
			contentPane.add(formattedDni);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		btnComprobar = new JButton("Comprobar");
		btnComprobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Optional<Document> medicos = medicoController.findByDni(formattedDni.getText());

				if (medicos.isPresent()) {
					lblDniPacientes.setVisible(true);
					btnAnadirCampo.setVisible(true);
					btnEliminarCampo.setVisible(true);

				} else {
					String mensaje = "El medico con DNI " + formattedDni.getText() + " no existe ";
					JOptionPane.showMessageDialog(null, mensaje, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnComprobar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnComprobar.setBounds(334, 68, 113, 25);
		contentPane.add(btnComprobar);

		lblDniPacientes = new JLabel("Introduzca los DNI de los pacientes\r\n");
		lblDniPacientes.setVisible(false);
		lblDniPacientes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDniPacientes.setBounds(31, 126, 210, 21);
		contentPane.add(lblDniPacientes);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				principal = new VentanaPrincipalMedico();
				principal.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(147, 280, 95, 28);
		contentPane.add(btnCancelar);

		btnAceptar = new JButton("Aceptar\r\n");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Optional<Document> medico = medicoController.findByDni(formattedDni.getText());
				String dniMedico = formattedDni.getText();
				if (medico.isPresent()) {
					List<String> textFieldList = new ArrayList<>();
					for (JFormattedTextField tf : formattedTextFields) {
						if (!tf.getText().trim().isEmpty()) { 
							textFieldList.add(tf.getText().trim());
		                }
					}
					String[] dniPacientes = textFieldList.toArray(new String[0]);
					pacienteController.anadirDniMedico(dniPacientes, dniMedico);
					String nombreMedico = medicoController.findNombrePorDni(dniMedico);
					pacienteController.anadirNombreMedico(dniPacientes, nombreMedico);
					String apellidosMedico = medicoController.findApellidosPorDni(dniMedico);
					pacienteController.anadirApellidosMedico(dniPacientes, apellidosMedico);
					String especialidadMedico = medicoController.findEspecialidadPorDni(dniMedico);
					pacienteController.anadirEspecialidadMedico(dniPacientes, especialidadMedico);
					if (btnAceptar == e.getSource()) {
						Boolean anadido = medicoController.crearPacientesCargo(medico, dniPacientes);

						if (anadido == true) {
							lblMensaje.setText("Pacientes a cargo añadidos con exito");
							lblMensaje.setForeground(Color.GREEN);
						} else {
							lblMensaje.setText("Pacientes a cargo no añadidos con exito");
							lblMensaje.setForeground(Color.RED);
						}
					}

				} else {
					lblMensaje.setText("No existe el paciente con el DNI " + formattedDni.getText());
					lblMensaje.setForeground(Color.RED);
				}
			}
		});
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAceptar.setBounds(306, 280, 95, 28);
		contentPane.add(btnAceptar);

		lblMensaje = new JLabel("\r\n");
		lblMensaje.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMensaje.setBounds(118, 318, 304, 28);
		contentPane.add(lblMensaje);

		lblTitulo = new JLabel("Añadir pacientes a cargo a medico");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitulo.setBounds(118, 24, 271, 21);
		contentPane.add(lblTitulo);
		btnAnadirCampo = new JButton("Nuevo DNI paciente");
		btnAnadirCampo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anadirCampo();
			}
		});
		btnAnadirCampo.setVisible(false);
		btnAnadirCampo.setBounds(10, 158, 198, 23);
		contentPane.add(btnAnadirCampo);

		btnEliminarCampo = new JButton("Eliminar ultimo DNI paciente\r\n");
		btnEliminarCampo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarCampo();
			}
		});
		btnEliminarCampo.setVisible(false);
		btnEliminarCampo.setBounds(10, 192, 201, 23);
		contentPane.add(btnEliminarCampo);
	}

	private void anadirCampo() {
		try {
			JFormattedTextField campoNuevo;
			mascara = new MaskFormatter("########?");
			mascara.setValidCharacters("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
			campoNuevo = new JFormattedTextField(mascara);
			contentPane.add(formattedDni);
			campoNuevo.setBounds(textFieldXPosition, textFieldYPosition, 205, textFieldHeight);
			contentPane.add(campoNuevo);
			formattedTextFields.add(campoNuevo);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		textFieldYPosition += textFieldHeight + textFieldSpacing;

		contentPane.setPreferredSize(new Dimension(400, textFieldYPosition + textFieldHeight));
		contentPane.revalidate();
		contentPane.repaint();
	}

	private void eliminarCampo() {
		if (!formattedTextFields.isEmpty()) {
			JFormattedTextField lastField = formattedTextFields.remove(formattedTextFields.size() - 1);
			contentPane.remove(lastField);

			textFieldYPosition -= textFieldHeight + textFieldSpacing;

			contentPane.setPreferredSize(new Dimension(400, textFieldYPosition + textFieldHeight));
			contentPane.revalidate();
			contentPane.repaint();
		}
	}
}
