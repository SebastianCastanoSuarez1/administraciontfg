package view.paciente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controller.Controller_Interfaz;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;

public class VentanaMostrarPorDni extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldDni, textFieldNombre, textFieldApellidos, textFieldFechaNacimiento, textFieldSexo;
	JLabel lblSexo, lblFechaNacimiento, lblApellidos, lblNombre, lblDNI, lblIntroduzcaNombre;
	private Controller_Interfaz controllerInterfaz = new Controller_Interfaz();
	private JLabel lblLugarDeNacimiento;
	private JTextField textFieldLugarNacimiento;
	private JLabel lblAltura;
	private JTextField textFieldAltura;
	private JLabel lblPeso;
	private JTextField textFieldPeso;
	private JLabel lblGrupoSanguineo;
	private JTextField textFieldGrupoSanguineo;
	JFormattedTextField formattedTextFieldDni;
	private MaskFormatter mascara;
	JButton btnVolver;
	VentanaPrincipalPaciente principal;
	private JLabel lblMostrarPacientePor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMostrarPorDni frame = new VentanaMostrarPorDni();
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
	public VentanaMostrarPorDni() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 539, 524);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblIntroduzcaNombre = new JLabel("Introduzca el DNI del paciente");
		lblIntroduzcaNombre.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIntroduzcaNombre.setBounds(136, 78, 210, 22);
		contentPane.add(lblIntroduzcaNombre);

		lblDNI = new JLabel("DNI:");
		lblDNI.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDNI.setBounds(89, 162, 50, 22);
		contentPane.add(lblDNI);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombre.setBounds(89, 199, 50, 22);
		contentPane.add(lblNombre);

		lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblApellidos.setBounds(89, 231, 62, 22);
		contentPane.add(lblApellidos);
		try {
			mascara = new MaskFormatter("########?");
			mascara.setValidCharacters("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
			formattedTextFieldDni = new JFormattedTextField(mascara);
			formattedTextFieldDni.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String nombre = formattedTextFieldDni.getText();
					textFieldDni.setText(controllerInterfaz.findDniPorDni(nombre));
					textFieldNombre.setText(controllerInterfaz.findNombrePorDni(nombre));
					textFieldApellidos.setText(controllerInterfaz.findApellidosPorDni(nombre));
					textFieldFechaNacimiento.setText(controllerInterfaz.findFechaNacimientoPorDni(nombre));
					textFieldSexo.setText(controllerInterfaz.findSexoPorDni(nombre));
					textFieldLugarNacimiento.setText(controllerInterfaz.findLugarNacimientoPorDni(nombre));
					textFieldAltura.setText(controllerInterfaz.findAlturaPorDni(nombre)+"");
					textFieldPeso.setText(controllerInterfaz.findPesoPorDni(nombre)+"");
					textFieldGrupoSanguineo.setText(controllerInterfaz.findGrupoSanguineoPorDni(nombre));
					
				}
			});
			formattedTextFieldDni.setBounds(356, 81, 138, 19);
			contentPane.add(formattedTextFieldDni);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}

		lblFechaNacimiento = new JLabel("Fecha Nacimiento:");
		lblFechaNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFechaNacimiento.setBounds(89, 263, 119, 22);
		contentPane.add(lblFechaNacimiento);

		lblSexo = new JLabel("Sexo:");
		lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSexo.setBounds(89, 295, 119, 19);
		contentPane.add(lblSexo);

		
		textFieldDni = new JTextField();
		textFieldDni.setEditable(false);
		textFieldDni.setBounds(227, 165, 180, 19);
		contentPane.add(textFieldDni);
		textFieldDni.setColumns(10);

		textFieldNombre = new JTextField();
		textFieldNombre.setEditable(false);
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(227, 199, 180, 19);

		contentPane.add(textFieldNombre);

		textFieldApellidos = new JTextField();
		textFieldApellidos.setEditable(false);
		textFieldApellidos.setColumns(10);
		textFieldApellidos.setBounds(227, 234, 180, 19);

		contentPane.add(textFieldApellidos);

		textFieldFechaNacimiento = new JTextField();
		textFieldFechaNacimiento.setEditable(false);
		textFieldFechaNacimiento.setColumns(10);
		textFieldFechaNacimiento.setBounds(227, 266, 180, 19);

		contentPane.add(textFieldFechaNacimiento);

		textFieldSexo = new JTextField();
		textFieldSexo.setEditable(false);
		textFieldSexo.setColumns(10);
		textFieldSexo.setBounds(227, 296, 180, 19);

		contentPane.add(textFieldSexo);

		lblLugarDeNacimiento = new JLabel("Lugar de Nacimiento:");
		lblLugarDeNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLugarDeNacimiento.setBounds(89, 324, 119, 19);
		contentPane.add(lblLugarDeNacimiento);

		textFieldLugarNacimiento = new JTextField();
		textFieldLugarNacimiento.setEditable(false);
		textFieldLugarNacimiento.setColumns(10);
		textFieldLugarNacimiento.setBounds(227, 325, 180, 19);
		contentPane.add(textFieldLugarNacimiento);

		lblAltura = new JLabel("Altura:");
		lblAltura.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAltura.setBounds(89, 359, 119, 19);
		contentPane.add(lblAltura);

		textFieldAltura = new JTextField();
		textFieldAltura.setEditable(false);
		textFieldAltura.setColumns(10);
		textFieldAltura.setBounds(227, 360, 180, 19);
		contentPane.add(textFieldAltura);

		lblPeso = new JLabel("Peso:");
		lblPeso.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPeso.setBounds(89, 388, 119, 19);
		contentPane.add(lblPeso);

		textFieldPeso = new JTextField();
		textFieldPeso.setEditable(false);
		textFieldPeso.setColumns(10);
		textFieldPeso.setBounds(227, 389, 180, 19);
		contentPane.add(textFieldPeso);

		lblGrupoSanguineo = new JLabel("Grupo sanguineo:");
		lblGrupoSanguineo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGrupoSanguineo.setBounds(89, 419, 119, 19);
		contentPane.add(lblGrupoSanguineo);

		textFieldGrupoSanguineo = new JTextField();
		textFieldGrupoSanguineo.setEditable(false);
		textFieldGrupoSanguineo.setColumns(10);
		textFieldGrupoSanguineo.setBounds(227, 420, 180, 19);
		contentPane.add(textFieldGrupoSanguineo);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				principal = new VentanaPrincipalPaciente();
				principal.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBackground(new Color(240, 240, 240));
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVolver.setBounds(23, 79, 85, 21);
		contentPane.add(btnVolver);
		
		lblMostrarPacientePor = new JLabel("Mostrar paciente por DNI");
		lblMostrarPacientePor.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMostrarPacientePor.setBounds(134, 27, 244, 22);
		contentPane.add(lblMostrarPacientePor);
		
	}
}
