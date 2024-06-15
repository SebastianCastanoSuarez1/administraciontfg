package view.paciente;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import org.bson.Document;

import controller.Controller_Interfaz;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaAnadirMedicamentos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MaskFormatter mascara;
	private JFormattedTextField formattedDni;
	private JLabel lblDNI;
	private JButton btnComprobar;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JLabel lblMensaje;
	private final Controller_Interfaz controllerInterfaz = new Controller_Interfaz();
	private VentanaPrincipalPaciente vp;
	private JLabel lblAadirMedicamentos;
	private JButton btnAnadirCampo;
	private JButton btnEliminarCampo;
	private int textFieldXPosition = 317;
	private int textFieldYPosition = 135;
	private final int textFieldHeight = 20;
	private final int textFieldSpacing = 10;
	private List<JTextField> textFields = new ArrayList<>();
	private JLabel lblIntroduzcaMedicamentos;

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
		setBounds(100, 100, 584, 446);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblDNI = new JLabel("DNI");
		lblDNI.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDNI.setBounds(89, 70, 34, 21);
		contentPane.add(lblDNI);

		try {
			mascara = new MaskFormatter("########?");
			mascara.setValidCharacters("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
			formattedDni = new JFormattedTextField(mascara);
			formattedDni.setBounds(155, 74, 177, 20);
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
						lblIntroduzcaMedicamentos.setVisible(true);
						btnAnadirCampo.setVisible(true);
						btnEliminarCampo.setVisible(true);

					} else {
						String mensaje = "El paciente con DNI " + formattedDni.getText() + " no existe ";
						JOptionPane.showMessageDialog(null, mensaje, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnComprobar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnComprobar.setBounds(361, 70, 113, 25);
		contentPane.add(btnComprobar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp = new VentanaPrincipalPaciente();
				vp.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancelar.setBounds(157, 304, 85, 33);
		contentPane.add(btnCancelar);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Optional<Document> pacientes = controllerInterfaz.findByDni(formattedDni.getText());
				if (pacientes.isPresent()) {
					List<String> medicamentosList = new ArrayList<>();
					for (JTextField tf : textFields) {
						if (!tf.getText().trim().isEmpty()) {  
		                    medicamentosList.add(tf.getText().trim());
		                }

					}
					String[] listaMedicamentos = medicamentosList.toArray(new String[0]);

					if (btnAceptar == e.getSource()) {

						Boolean anadido = controllerInterfaz.anadirMedicamentos(pacientes, listaMedicamentos);

						if (anadido == true) {
							lblMensaje.setText("Medicamentos añadidos con exito");
							lblMensaje.setForeground(Color.GREEN);
							Timer timer = new Timer(1500, new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									vp = new VentanaPrincipalPaciente();
									vp.setVisible(true);
									dispose();
								}
							});
							timer.setRepeats(false);
							timer.start();
						} else {
							lblMensaje.setText("Medicamentos no añadidos con exito");
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
		btnAceptar.setBounds(329, 304, 85, 33);
		contentPane.add(btnAceptar);

		lblMensaje = new JLabel("");
		lblMensaje.setBounds(157, 363, 257, 21);
		contentPane.add(lblMensaje);

		lblAadirMedicamentos = new JLabel("Añadir medicamentos al paciente\r\n");
		lblAadirMedicamentos.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAadirMedicamentos.setBounds(168, 24, 262, 22);
		contentPane.add(lblAadirMedicamentos);

		lblIntroduzcaMedicamentos = new JLabel("Introduzca los medicamentos al paciente");
		lblIntroduzcaMedicamentos.setVisible(false);
		lblIntroduzcaMedicamentos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblIntroduzcaMedicamentos.setBounds(20, 134, 293, 21);
		contentPane.add(lblIntroduzcaMedicamentos);

		btnAnadirCampo = new JButton("Nuevo medicamento");
		btnAnadirCampo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anadirCampo();
			}
		});
		btnAnadirCampo.setVisible(false);
		btnAnadirCampo.setBounds(13, 166, 196, 23);
		contentPane.add(btnAnadirCampo);

		btnEliminarCampo = new JButton("Eliminar ultimo medicamento");
		btnEliminarCampo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarCampo();
			}
		});
		btnEliminarCampo.setVisible(false);
		btnEliminarCampo.setBounds(12, 200, 197, 23);
		contentPane.add(btnEliminarCampo);
	}

	private void anadirCampo() {
		JTextField campoNuevo = new JTextField();
		campoNuevo.setBounds(textFieldXPosition, textFieldYPosition, 222, textFieldHeight);
		contentPane.add(campoNuevo);
		textFields.add(campoNuevo);

		textFieldYPosition += textFieldHeight + textFieldSpacing;

		contentPane.setPreferredSize(new Dimension(400, textFieldYPosition + textFieldHeight));
		contentPane.revalidate();
		contentPane.repaint();
	}

	private void eliminarCampo() {
		if (!textFields.isEmpty()) {
			JTextField lastField = textFields.remove(textFields.size() - 1);
			contentPane.remove(lastField);

			textFieldYPosition -= textFieldHeight + textFieldSpacing;

			contentPane.setPreferredSize(new Dimension(400, textFieldYPosition + textFieldHeight));
			contentPane.revalidate();
			contentPane.repaint();
		}
	}
}
