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

public class EliminarAlergenos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo, lblEligaElAlergeno, lblAlergenoElegido, lblMensaje;
	private String[] alergeno;
	private String selectedAlergeno;
	private JTextField textFieldAlergeno;
	private JButton btnCancelar, btnEliminar;
	private VentanaPrincipalPaciente principal;
	private Controller_Interfaz controller = new Controller_Interfaz();
	private JComboBox<String> comboBoxAlergenos;
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
					EliminarAlergenos frame = new EliminarAlergenos();
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
	public EliminarAlergenos() {
		try {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 556, 395);
			contentPane = new JPanel();
			contentPane.setBackground(new Color(230, 230, 250));
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(contentPane);
			contentPane.setLayout(null);

			lblTitulo = new JLabel("Eliminar alergenos\r\n\r\n");
			lblTitulo.setBounds(186, 24, 176, 29);
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

			comboBoxAlergenos = new JComboBox<String>();
			comboBoxAlergenos.setVisible(false);
			comboBoxAlergenos.setBounds(305, 149, 190, 21);
			comboBoxAlergenos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					selectedAlergeno = comboBoxAlergenos.getSelectedItem().toString();
					lblAlergenoElegido.setVisible(true);
					textFieldAlergeno.setVisible(true);
					textFieldAlergeno.setText(selectedAlergeno);
				}
			});
			contentPane.add(comboBoxAlergenos);

			lblEligaElAlergeno = new JLabel("Eliga el alergeno que quiera eliminar");
			lblEligaElAlergeno.setVisible(false);
			lblEligaElAlergeno.setBounds(24, 144, 271, 29);
			lblEligaElAlergeno.setFont(new Font("Tahoma", Font.BOLD, 12));
			contentPane.add(lblEligaElAlergeno);

			lblAlergenoElegido = new JLabel("El alergeno eligido para eliminar ");
			lblAlergenoElegido.setBounds(24, 201, 271, 21);
			lblAlergenoElegido.setVisible(false);
			lblAlergenoElegido.setFont(new Font("Tahoma", Font.BOLD, 12));
			contentPane.add(lblAlergenoElegido);

			textFieldAlergeno = new JTextField();
			textFieldAlergeno.setEditable(false);
			textFieldAlergeno.setBounds(305, 203, 190, 19);
			textFieldAlergeno.setVisible(false);
			contentPane.add(textFieldAlergeno);
			textFieldAlergeno.setColumns(10);

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
						selectedAlergeno = comboBoxAlergenos.getSelectedItem().toString();
						Boolean actualizado = controller.eliminarAlergenos(paciente, selectedAlergeno);
						if (actualizado == true) {
							lblMensaje.setText("Alergeno eliminado con exito");
							lblMensaje.setForeground(Color.GREEN);
						} else {
							lblMensaje.setText("Alergeno no eliminado con exito");
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
							comboBoxAlergenos.setVisible(true);
							lblAlergenoElegido.setVisible(true);
							textFieldAlergeno.setVisible(true);
							DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
							alergeno = controller.alergenos(dni);
							model.addElement("");
							for (int i = 0; i < alergeno.length; i++) {
								model.addElement(alergeno[i]);
							}
							comboBoxAlergenos.setModel(model);
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
			JOptionPane.showMessageDialog(EliminarAlergenos.this,
					"El DNI " + formattedDni.getText() + " no tiene alergenos");
		}

	}
}
