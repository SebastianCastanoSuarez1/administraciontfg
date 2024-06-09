package view.paciente;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.Optional;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import org.bson.Document;

import controller.Controller_Interfaz;

public class VentanaAgregarPaciente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblDNI, lblNombre, lblApellidos, lblFechaDeNacimiento, lblSexo, lblLugarDeNacimiento, lblAltura,
			lblPeso, lblGrupoSanguineo, lblMensaje;
	private MaskFormatter mascara;
	private JFormattedTextField formattedDni;
	private JTextField textFieldNombre, textFieldApellidos, textFieldLugarNacimiento, textFieldAltura, textFieldPeso;
	private JFormattedTextField formattedFechaNacimiento;
	private JComboBox<String> comboBoxSexo, comboBoxGrupoSanguineo;
	private JButton btnAceptar, btnCancelar;
	private final Controller_Interfaz controllerInterfaz = new Controller_Interfaz();
	private VentanaPrincipalPaciente vp;
	private JLabel lblAadirPaciente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAgregarPaciente frame = new VentanaAgregarPaciente();
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
	public VentanaAgregarPaciente() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 527, 414);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblDNI = new JLabel("DNI\r\n");
		lblDNI.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDNI.setBounds(10, 71, 42, 21);
		contentPane.add(lblDNI);

		lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombre.setBounds(234, 71, 60, 21);
		contentPane.add(lblNombre);

		lblApellidos = new JLabel("Apellidos");
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblApellidos.setBounds(10, 102, 60, 21);
		contentPane.add(lblApellidos);

		lblFechaDeNacimiento = new JLabel("Fecha_Nacimiento");
		lblFechaDeNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFechaDeNacimiento.setBounds(234, 102, 126, 21);
		contentPane.add(lblFechaDeNacimiento);

		lblSexo = new JLabel("Sexo");
		lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSexo.setBounds(10, 133, 89, 21);
		contentPane.add(lblSexo);

		lblLugarDeNacimiento = new JLabel("Lugar_Nacimiento");
		lblLugarDeNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLugarDeNacimiento.setBounds(234, 133, 126, 21);
		contentPane.add(lblLugarDeNacimiento);

		lblAltura = new JLabel("Altura");
		lblAltura.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAltura.setBounds(10, 164, 60, 21);
		contentPane.add(lblAltura);

		lblPeso = new JLabel("Peso\r\n");
		lblPeso.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPeso.setBounds(234, 164, 49, 21);
		contentPane.add(lblPeso);

		lblGrupoSanguineo = new JLabel("Grupo_Sanguineo");
		lblGrupoSanguineo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGrupoSanguineo.setBounds(10, 195, 138, 21);
		contentPane.add(lblGrupoSanguineo);

		try {
			mascara = new MaskFormatter("########?");
			mascara.setValidCharacters("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
			formattedDni = new JFormattedTextField(mascara);
			formattedDni.setBounds(62, 73, 138, 19);
			contentPane.add(formattedDni);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(304, 73, 140, 19);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		textFieldApellidos = new JTextField();
		textFieldApellidos.setColumns(10);
		textFieldApellidos.setBounds(80, 104, 140, 19);
		contentPane.add(textFieldApellidos);

		try {
			mascara = new MaskFormatter("##/##/####");
			mascara.setValidCharacters("0123456789");
			formattedFechaNacimiento = new JFormattedTextField(mascara);
			formattedFechaNacimiento.setBounds(365, 104, 138, 19);
			contentPane.add(formattedFechaNacimiento);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		comboBoxSexo = new JComboBox<String>();
		comboBoxSexo.setModel(new DefaultComboBoxModel<String>(new String[] { "", "Masculino", "Femenino" }));
		comboBoxSexo.setBounds(62, 133, 95, 21);
		contentPane.add(comboBoxSexo);

		textFieldLugarNacimiento = new JTextField();
		textFieldLugarNacimiento.setBounds(369, 135, 134, 19);
		contentPane.add(textFieldLugarNacimiento);
		textFieldLugarNacimiento.setColumns(10);

		textFieldAltura = new JTextField();
		textFieldAltura.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		textFieldAltura.setColumns(10);
		textFieldAltura.setBounds(62, 164, 134, 19);
		contentPane.add(textFieldAltura);

		textFieldPeso = new JTextField();
		textFieldPeso.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		textFieldPeso.setColumns(10);

		textFieldPeso.setBounds(286, 164, 134, 19);
		contentPane.add(textFieldPeso);

		comboBoxGrupoSanguineo = new JComboBox<String>();
		comboBoxGrupoSanguineo.setModel(new DefaultComboBoxModel<String>(
				new String[] { "", "A+", "A-", "B+", "B-", "AB+", "AB-", "0+", "0-" }));
		comboBoxGrupoSanguineo.setBounds(125, 196, 95, 21);
		contentPane.add(comboBoxGrupoSanguineo);
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anadirPaciente(e);
			}

			private void anadirPaciente(ActionEvent e) {
				if (btnAceptar == e.getSource()) {

					Optional<Document> medicoDNI = controllerInterfaz.comprobarDni(formattedDni.getText());
					if (medicoDNI.isPresent()) {
						lblMensaje.setText("El medico con DNI " + formattedDni.getText() + " ya esta añadido");
						lblMensaje.setForeground(Color.RED);
					} else {
						String alturaString = textFieldAltura.getText();
						Integer altura = Integer.parseInt(alturaString);
						String pesoString = textFieldPeso.getText();
						Integer peso = Integer.parseInt(pesoString);
						Document paciente = controllerInterfaz.anadirPacientenuevo(formattedDni.getText(),
								textFieldNombre.getText(), textFieldApellidos.getText(),
								formattedFechaNacimiento.getText(), comboBoxSexo.getSelectedItem().toString(),
								textFieldLugarNacimiento.getText(), altura, peso,
								comboBoxGrupoSanguineo.getSelectedItem().toString());
						Boolean anadido = controllerInterfaz.salvarPaciente(paciente);
						if (anadido == true) {
							lblMensaje.setText("El paciente ha sido añadido con exito");
							lblMensaje.setForeground(Color.GREEN);
						} else {
							lblMensaje.setText("El paciente no ha sido añadido con exito");
							lblMensaje.setForeground(Color.RED);
						}

					}

				}
			}
		});
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAceptar.setBounds(283, 278, 126, 34);
		contentPane.add(btnAceptar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp = new VentanaPrincipalPaciente();
				vp.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancelar.setBounds(80, 278, 126, 34);
		contentPane.add(btnCancelar);

		lblMensaje = new JLabel("");
		lblMensaje.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMensaje.setBounds(49, 333, 429, 34);
		contentPane.add(lblMensaje);
		
		lblAadirPaciente = new JLabel("Añadir paciente");
		lblAadirPaciente.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAadirPaciente.setBounds(184, 22, 201, 22);
		contentPane.add(lblAadirPaciente);

	}
}
