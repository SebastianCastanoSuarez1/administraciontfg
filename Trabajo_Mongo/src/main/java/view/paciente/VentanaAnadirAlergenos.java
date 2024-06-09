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
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import org.bson.Document;

import controller.Controller_Interfaz;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaAnadirAlergenos extends JFrame {

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
	private JLabel lblAadirAlergenos;
	private JButton btnAnadirCampo;
	private JButton btnEliminarCampo;
	private int textFieldXPosition = 288;
	private int textFieldYPosition = 135;
	private final int textFieldHeight = 20;
	private final int textFieldSpacing = 10;
	private List<JTextField> textFields = new ArrayList<>();
	private JLabel lblIntroduzcaAlergenos;

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
		setBounds(100, 100, 544, 427);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblDNI = new JLabel("DNI");
		lblDNI.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDNI.setBounds(70, 73, 34, 21);
		contentPane.add(lblDNI);

		try {
			mascara = new MaskFormatter("########?");
			mascara.setValidCharacters("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
			formattedDni = new JFormattedTextField(mascara);
			formattedDni.setBounds(136, 77, 177, 20);
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
						lblIntroduzcaAlergenos.setVisible(true);
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
		btnComprobar.setBounds(342, 73, 113, 25);
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
		btnCancelar.setBounds(132, 292, 85, 33);
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
					String[] listaAlergenos = medicamentosList.toArray(new String[0]);
					if (btnAceptar == e.getSource()) {
						Boolean anadido = controllerInterfaz.anadirAlergenos(pacientes, listaAlergenos);

						if (anadido == true) {
							lblMensaje.setText("Alergenos añadidos con exito");
							lblMensaje.setForeground(Color.GREEN);
						} else {
							lblMensaje.setText("Alergenos no añadidos con exito");
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
		btnAceptar.setBounds(298, 292, 85, 33);
		contentPane.add(btnAceptar);

		lblMensaje = new JLabel("");
		lblMensaje.setBounds(124, 356, 259, 21);
		contentPane.add(lblMensaje);

		lblAadirAlergenos = new JLabel("Añadir alergenos al paciente\r\n");
		lblAadirAlergenos.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAadirAlergenos.setBounds(159, 20, 225, 22);
		contentPane.add(lblAadirAlergenos);

		lblIntroduzcaAlergenos = new JLabel("Introduzca los alergenos al paciente");
		lblIntroduzcaAlergenos.setVisible(false);
		lblIntroduzcaAlergenos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblIntroduzcaAlergenos.setBounds(20, 134, 293, 21);
		contentPane.add(lblIntroduzcaAlergenos);

		btnAnadirCampo = new JButton("Nuevo alergeno");
		btnAnadirCampo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anadirCampo();
			}
		});
		btnAnadirCampo.setVisible(false);
		btnAnadirCampo.setBounds(20, 166, 177, 23);
		contentPane.add(btnAnadirCampo);

		btnEliminarCampo = new JButton("Eliminar ultimo alergeno\r\n");
		btnEliminarCampo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarCampo();
			}
		});
		btnEliminarCampo.setVisible(false);
		btnEliminarCampo.setBounds(20, 200, 177, 23);
		contentPane.add(btnEliminarCampo);
	}

	private void anadirCampo() {
		JTextField campoNuevo = new JTextField();
		campoNuevo.setBounds(textFieldXPosition, textFieldYPosition, 205, textFieldHeight);
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
