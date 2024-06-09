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
import javax.swing.UIManager;

public class VentanaModificarPaciente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFormattedTextField formattedDni;
	private JFormattedTextField formattedFechaNacimiento;
	private JButton btnComprobar;
	private final Controller_Interfaz controllerInterfaz = new Controller_Interfaz();
	private MaskFormatter mascara;
	private JLabel lblDNI;
	private VentanaPrincipalPaciente vp;
	private JLabel lblGrupoSanguineo;
	private JTextField textFieldNombre;
	private JTextField textFieldApellidos;
	private JTextField textFieldAltura;
	private JTextField textFieldPeso;
	private JButton saveButton;
	private JComboBox<String> bloodComboBox;
	private JComboBox<String> GendercomboBox;
	private JButton btnCancelar;
	private JLabel lblTitulo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaModificarPaciente frame = new VentanaModificarPaciente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaModificarPaciente() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 631, 469);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblDNI = new JLabel("DNI");
		lblDNI.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDNI.setBounds(125, 63, 55, 40);
		contentPane.add(lblDNI);

		try {
			mascara = new MaskFormatter("########?");
			mascara.setValidCharacters("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
			formattedDni = new JFormattedTextField(mascara);
			formattedDni.setBounds(209, 74, 148, 26);
			contentPane.add(formattedDni);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		btnComprobar = new JButton("Comprobar");
		btnComprobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnComprobar == e.getSource()) {
					String dni = formattedDni.getText();
					Optional<Document> pacientes = controllerInterfaz.comprobarDni(dni);

					if (pacientes.isPresent()) {
						formattedDni.setEnabled(false);
						habilitarCampos(true);
						cargarDatosDocumento(dni);
						saveButton.setEnabled(true);
					} else {
						String mensaje = "El paciente con DNI " + formattedDni.getText() + " no existe ";
						JOptionPane.showMessageDialog(null, mensaje, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnComprobar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnComprobar.setBounds(385, 72, 98, 27);
		contentPane.add(btnComprobar);

		lblGrupoSanguineo = new JLabel("Grupo sanguineo:");
		lblGrupoSanguineo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblGrupoSanguineo.setBounds(32, 303, 104, 21);
		contentPane.add(lblGrupoSanguineo);

		textFieldNombre = new JTextField();
		textFieldNombre.setEnabled(false);
		textFieldNombre.setBounds(151, 145, 150, 20);
		contentPane.add(textFieldNombre);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNombre.setBounds(32, 145, 70, 20);
		contentPane.add(lblNombre);

		textFieldApellidos = new JTextField();
		textFieldApellidos.setEnabled(false);
		textFieldApellidos.setBounds(457, 145, 150, 20);
		contentPane.add(textFieldApellidos);

		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblApellidos.setBounds(352, 144, 70, 20);
		contentPane.add(lblApellidos);

		try {
			MaskFormatter dobMask = new MaskFormatter("##/##/####");
			dobMask.setValidCharacters("0123456789");
			formattedFechaNacimiento = new JFormattedTextField(dobMask);
			formattedFechaNacimiento.setBounds(151, 198, 150, 20);
			formattedFechaNacimiento.setEnabled(false);
			contentPane.add(formattedFechaNacimiento);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento:");
		lblFechaNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblFechaNacimiento.setBounds(32, 198, 128, 20);
		contentPane.add(lblFechaNacimiento);

		textFieldAltura = new JTextField();
		textFieldAltura.setEnabled(false);
		textFieldAltura.setColumns(10);
		textFieldAltura.setBounds(457, 198, 150, 20);
		contentPane.add(textFieldAltura);

		textFieldPeso = new JTextField();
		textFieldPeso.setEnabled(false);
		textFieldPeso.setColumns(10);
		textFieldPeso.setBounds(457, 245, 150, 20);
		contentPane.add(textFieldPeso);

		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSexo.setBounds(32, 249, 70, 13);
		contentPane.add(lblSexo);

		GendercomboBox = new JComboBox<String>();
		GendercomboBox.setEnabled(false);
		GendercomboBox.setModel(new DefaultComboBoxModel<>(new String[] { "", "Masculino", "Femenino" }));
		GendercomboBox.setBounds(151, 244, 150, 21);
		contentPane.add(GendercomboBox);

		JLabel alturaLbl = new JLabel("Altura:");
		alturaLbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
		alturaLbl.setBounds(352, 198, 70, 16);
		contentPane.add(alturaLbl);

		JLabel lblPeso = new JLabel("Peso:");
		lblPeso.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPeso.setBounds(352, 248, 70, 13);
		contentPane.add(lblPeso);

		bloodComboBox = new JComboBox<String>();
		bloodComboBox.setEnabled(false);
		bloodComboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "", "A+", "A-", "B+", "B-", "AB+", "AB-", "0+", "0-" }));
		bloodComboBox.setBounds(151, 303, 150, 21);
		contentPane.add(bloodComboBox);

		saveButton = new JButton("Aceptar");
		saveButton.setEnabled(false);
		saveButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		saveButton.setBorderPainted(false);
		saveButton.setBackground(new Color(240, 240, 240));
		saveButton.setBounds(343, 362, 122, 35);
		contentPane.add(saveButton);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp = new VentanaPrincipalPaciente();
				vp.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCancelar.setBorderPainted(false);
		btnCancelar.setBackground(UIManager.getColor("Button.background"));
		btnCancelar.setBounds(155, 362, 122, 35);
		contentPane.add(btnCancelar);
		
		lblTitulo = new JLabel("Modificar paciente");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitulo.setBounds(224, 23, 168, 21);
		contentPane.add(lblTitulo);

		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == saveButton) {
					actualizarPaciente();
				}
			}
		});
	}

	private void habilitarCampos(boolean habilitar) {
		textFieldNombre.setEnabled(habilitar);
		textFieldApellidos.setEnabled(habilitar);
		formattedFechaNacimiento.setEnabled(habilitar);
		textFieldAltura.setEnabled(habilitar);
		textFieldPeso.setEnabled(habilitar);
		GendercomboBox.setEnabled(habilitar);
		bloodComboBox.setEnabled(habilitar);
		lblGrupoSanguineo.setEnabled(habilitar);

		JLabel[] labels = { new JLabel("Nombre:"), new JLabel("Apellidos:"), new JLabel("Fecha Nacimiento:"),
				new JLabel("Sexo:"), new JLabel("Altura:"), new JLabel("Peso:") };

		for (JLabel label : labels) {
			label.setEnabled(habilitar);
		}
	}

	private void cargarDatosDocumento(String dni) {
		Optional<Document> documento = controllerInterfaz.findByDni(dni);
		documento.ifPresent(usuarioDatos -> {
			textFieldNombre.setText(usuarioDatos.getString("Nombre"));
			textFieldApellidos.setText(usuarioDatos.getString("Apellidos"));
			formattedFechaNacimiento.setText(usuarioDatos.getString("Fecha_Nacimiento"));
			textFieldAltura.setText(String.valueOf(usuarioDatos.getInteger("Altura")));
			textFieldPeso.setText(String.valueOf(usuarioDatos.getInteger("Peso")));
			GendercomboBox.setSelectedItem(usuarioDatos.getString("Sexo"));
			bloodComboBox.setSelectedItem(usuarioDatos.getString("Grupo_Sanguineo"));
		});
	}

	private void actualizarPaciente() {
		String dni = formattedDni.getText();
		String nombre = textFieldNombre.getText();
		String apellidos = textFieldApellidos.getText();
		String fechaNacimiento = formattedFechaNacimiento.getText();
		int altura = 0;
		int peso = 0;
		String sexo = (String) GendercomboBox.getSelectedItem();
		String grupoSanguineo = (String) bloodComboBox.getSelectedItem();

		try {
			altura = Integer.parseInt(textFieldAltura.getText());
			peso = Integer.parseInt(textFieldPeso.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Altura y Peso deben ser números enteros.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		Document paciente = new Document("Dni", dni).append("Nombre", nombre).append("Apellidos", apellidos)
				.append("Fecha_Nacimiento", fechaNacimiento).append("Altura", altura).append("Peso", peso)
				.append("Sexo", sexo).append("Grupo_Sanguineo", grupoSanguineo);

		boolean actualizado = controllerInterfaz.updateData(dni, paciente);

		if (actualizado) {
			JOptionPane.showMessageDialog(this, "Datos del paciente actualizados correctamente.",
					"Actualización exitosa", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "Hubo un problema al actualizar los datos del paciente.",
					"Error de actualización", JOptionPane.ERROR_MESSAGE);
		}
	
	}
}
