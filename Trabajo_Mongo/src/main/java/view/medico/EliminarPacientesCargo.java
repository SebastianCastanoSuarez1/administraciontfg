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
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import org.bson.Document;

import controller.Controller_Interfaz;
import controller.MedicoController_Interfaz;

public class EliminarPacientesCargo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo, lblEligaElPaciente, lblPacienteElegido, lblMensaje;
	private String[] pacientesCargo;
	private String selectedPacientesCargo;
	private JTextField textFieldPaciente;
	private JButton btnCancelar, btnEliminar;
	private VentanaPrincipalMedico principal;
	private MedicoController_Interfaz controllerMedico = new MedicoController_Interfaz();
	private Controller_Interfaz controllerInterfaz = new Controller_Interfaz();
	private JComboBox<String> comboBoxPacientes;
	private JLabel lblDNI;
	private JFormattedTextField formattedDni;
	private JButton btnComprobar;
	private MaskFormatter mascara;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EliminarPacientesCargo frame = new EliminarPacientesCargo();
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
	public EliminarPacientesCargo() {
		try {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 556, 395);
			contentPane = new JPanel();
			contentPane.setBackground(new Color(230, 230, 250));
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(contentPane);
			contentPane.setLayout(null);

			lblTitulo = new JLabel("Eliminar pacientes a cargo\r\n\r\n");
			lblTitulo.setBounds(161, 24, 201, 29);
			lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
			contentPane.add(lblTitulo);

			try {
				mascara = new MaskFormatter("########?");
				mascara.setValidCharacters("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
				formattedDni = new JFormattedTextField(mascara);
				formattedDni.setBounds(161, 73, 148, 26);
				contentPane.add(formattedDni);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			comboBoxPacientes = new JComboBox<String>();
			comboBoxPacientes.setVisible(false);
			comboBoxPacientes.setBounds(305, 149, 190, 21);
			comboBoxPacientes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					selectedPacientesCargo = comboBoxPacientes.getSelectedItem().toString();
					lblPacienteElegido.setVisible(true);
					textFieldPaciente.setVisible(true);
					textFieldPaciente.setText(selectedPacientesCargo);
				}
			});
			contentPane.add(comboBoxPacientes);

			lblEligaElPaciente = new JLabel("Eliga el paciente que quiera eliminar");
			lblEligaElPaciente.setVisible(false);
			lblEligaElPaciente.setBounds(24, 144, 271, 29);
			lblEligaElPaciente.setFont(new Font("Tahoma", Font.BOLD, 12));
			contentPane.add(lblEligaElPaciente);

			lblPacienteElegido = new JLabel("El paciente eligido para eliminar ");
			lblPacienteElegido.setBounds(24, 201, 271, 21);
			lblPacienteElegido.setVisible(false);
			lblPacienteElegido.setFont(new Font("Tahoma", Font.BOLD, 12));
			contentPane.add(lblPacienteElegido);

			textFieldPaciente = new JTextField();
			textFieldPaciente.setEditable(false);
			textFieldPaciente.setBounds(305, 203, 190, 19);
			textFieldPaciente.setVisible(false);
			contentPane.add(textFieldPaciente);
			textFieldPaciente.setColumns(10);

			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBounds(127, 270, 85, 29);
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					principal = new VentanaPrincipalMedico();
					principal.setVisible(true);
					dispose();

				}
			});
			btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
			contentPane.add(btnCancelar);

			btnEliminar = new JButton("Eliminar");
			btnEliminar.setBounds(324, 270, 85, 29);
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					Optional<Document> paciente = controllerMedico.findByDni(formattedDni.getText());
					if (paciente.isPresent()) {
						selectedPacientesCargo = comboBoxPacientes.getSelectedItem().toString();
						Boolean actualizado = controllerMedico.eliminarPacienteCargo(paciente, selectedPacientesCargo);
						Boolean actualizado1 = controllerInterfaz.eliminarDniMedico(selectedPacientesCargo, "Dni_Medico");
						Boolean actualizado2 = controllerInterfaz.eliminarNombreiMedico(selectedPacientesCargo, "Nombre_Medico");
						Boolean actualizado3 = controllerInterfaz.eliminarApellidosMedico(selectedPacientesCargo, "Apellidos_Medico");
						Boolean actualizado4 = controllerInterfaz.eliminarEspecialidadMedico(selectedPacientesCargo, "Especialidad_Medico");

						if (actualizado == true && actualizado1 && actualizado2 && actualizado3 && actualizado4) {
							lblMensaje.setText("Paciente a cargo eliminado con exito");
							lblMensaje.setForeground(Color.GREEN);
						} else {
							lblMensaje.setText("Paciente a cargo no eliminado");
							lblMensaje.setForeground(Color.RED);
						}
					} else {
						lblMensaje.setText("No existe el paciente con el DNI " + formattedDni.getText());
						lblMensaje.setForeground(Color.RED);
					}
				}
			});
			btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 12));
			contentPane.add(btnEliminar);

			lblMensaje = new JLabel("");
			lblMensaje.setBounds(102, 324, 357, 21);
			lblMensaje.setFont(new Font("Tahoma", Font.PLAIN, 12));
			contentPane.add(lblMensaje);

			lblDNI = new JLabel("DNI");
			lblDNI.setFont(new Font("Tahoma", Font.BOLD, 17));
			lblDNI.setBounds(89, 63, 55, 40);
			contentPane.add(lblDNI);

			btnComprobar = new JButton("Comprobar");
			btnComprobar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (btnComprobar == e.getSource()) {
						String dni = formattedDni.getText();
						Optional<Document> pacientes = controllerMedico.comprobarDni(dni);

						if (pacientes.isPresent()) {
							lblEligaElPaciente.setVisible(true);
							comboBoxPacientes.setVisible(true);
							lblPacienteElegido.setVisible(true);
							textFieldPaciente.setVisible(true);
							DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
							pacientesCargo = controllerMedico.pacientesCargo(dni);
							model.addElement("");
							for (int i = 0; i < pacientesCargo.length; i++) {
								model.addElement(pacientesCargo[i]);
							}
							comboBoxPacientes.setModel(model);
						} else {
							String mensaje = "El paciente con DNI " + formattedDni.getText() + " no existe ";
							JOptionPane.showMessageDialog(null, mensaje, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			});
			btnComprobar.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnComprobar.setBounds(324, 72, 98, 27);
			contentPane.add(btnComprobar);
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(EliminarPacientesCargo.this,
					"El DNI " + formattedDni.getText() + " no pacientes a cargo");
		}

	}
}
