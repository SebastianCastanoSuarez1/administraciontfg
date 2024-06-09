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

public class EliminarMedicamentos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo, lblEligaElAlergeno, lblAlergenoElegido, lblMensaje;
	private String[] medicamentos;
	private String selectedMedicamento;
	private JTextField textFieldMedicamento;
	private JButton btnCancelar, btnEliminar;
	private VentanaPrincipalPaciente principal;
	private Controller_Interfaz controller = new Controller_Interfaz();
	private JComboBox<String> comboBoxMedicamentos;
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
					EliminarMedicamentos frame = new EliminarMedicamentos();
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
	public EliminarMedicamentos() {
		try {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 556, 395);
			contentPane = new JPanel();
			contentPane.setBackground(new Color(230, 230, 250));
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(contentPane);
			contentPane.setLayout(null);

			lblTitulo = new JLabel("Eliminar medicamentos\r\n\r\n");
			lblTitulo.setBounds(187, 24, 175, 29);
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

			comboBoxMedicamentos = new JComboBox<String>();
			comboBoxMedicamentos.setVisible(false);
			comboBoxMedicamentos.setBounds(305, 149, 190, 21);
			comboBoxMedicamentos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					selectedMedicamento = comboBoxMedicamentos.getSelectedItem().toString();
					lblAlergenoElegido.setVisible(true);
					textFieldMedicamento.setVisible(true);
					textFieldMedicamento.setText(selectedMedicamento);
				}
			});
			contentPane.add(comboBoxMedicamentos);

			lblEligaElAlergeno = new JLabel("Eliga el medicamento que quiera eliminar");
			lblEligaElAlergeno.setVisible(false);
			lblEligaElAlergeno.setBounds(24, 144, 271, 29);
			lblEligaElAlergeno.setFont(new Font("Tahoma", Font.BOLD, 12));
			contentPane.add(lblEligaElAlergeno);

			lblAlergenoElegido = new JLabel("El medicamento eligido para eliminar ");
			lblAlergenoElegido.setBounds(24, 201, 271, 21);
			lblAlergenoElegido.setVisible(false);
			lblAlergenoElegido.setFont(new Font("Tahoma", Font.BOLD, 12));
			contentPane.add(lblAlergenoElegido);

			textFieldMedicamento = new JTextField();
			textFieldMedicamento.setEditable(false);
			textFieldMedicamento.setBounds(305, 203, 190, 19);
			textFieldMedicamento.setVisible(false);
			contentPane.add(textFieldMedicamento);
			textFieldMedicamento.setColumns(10);

			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBounds(127, 270, 85, 29);
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					principal = new VentanaPrincipalPaciente();
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

					Optional<Document> paciente = controller.findByDni(formattedDni.getText());
					if (paciente.isPresent()) {
						selectedMedicamento = comboBoxMedicamentos.getSelectedItem().toString();
						Boolean actualizado = controller.eliminarMedicamentos(paciente, selectedMedicamento);
						if (actualizado == true) {
							lblMensaje.setText("Medicamento eliminado con exito");
							lblMensaje.setForeground(Color.GREEN);
						} else {
							lblMensaje.setText("Medicamento no eliminado con exito");
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
						Optional<Document> pacientes = controller.comprobarDni(dni);

						if (pacientes.isPresent()) {
							lblEligaElAlergeno.setVisible(true);
							comboBoxMedicamentos.setVisible(true);
							lblAlergenoElegido.setVisible(true);
							textFieldMedicamento.setVisible(true);
							DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
							medicamentos = controller.medicamentos(dni);
							model.addElement("");
							for (int i = 0; i < medicamentos.length; i++) {
								model.addElement(medicamentos[i]);
							}
							comboBoxMedicamentos.setModel(model);
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
			JOptionPane.showMessageDialog(EliminarMedicamentos.this,
					"El DNI " + formattedDni.getText() + " no tiene mediamentos");
		}

	}
}
