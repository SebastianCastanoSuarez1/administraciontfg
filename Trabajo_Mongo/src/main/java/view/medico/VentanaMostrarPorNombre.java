package view.medico;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller_Interfaz;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaMostrarPorNombre extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldEscribirNombre, textFieldDni, textFieldNombre, textFieldApellidos,
			textFieldFechaNacimiento, textFieldSexo;
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
	JButton btnVolver;
	VentanaPrincipalMedico principal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMostrarPorNombre frame = new VentanaMostrarPorNombre();
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
	public VentanaMostrarPorNombre() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 539, 489);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblIntroduzcaNombre = new JLabel("Introduzca el nombre del paciente");
		lblIntroduzcaNombre.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIntroduzcaNombre.setBounds(114, 32, 234, 22);
		contentPane.add(lblIntroduzcaNombre);

		lblDNI = new JLabel("DNI:");
		lblDNI.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDNI.setBounds(92, 91, 50, 22);
		contentPane.add(lblDNI);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombre.setBounds(92, 128, 50, 22);
		contentPane.add(lblNombre);

		lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblApellidos.setBounds(92, 160, 62, 22);
		contentPane.add(lblApellidos);

		lblFechaNacimiento = new JLabel("Fecha Nacimiento:");
		lblFechaNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFechaNacimiento.setBounds(92, 192, 119, 22);
		contentPane.add(lblFechaNacimiento);

		lblSexo = new JLabel("Sexo:");
		lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSexo.setBounds(92, 224, 119, 19);
		contentPane.add(lblSexo);

		textFieldEscribirNombre = new JTextField();
		textFieldEscribirNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = textFieldEscribirNombre.getText();
				textFieldDni.setText(controllerInterfaz.findDniPorNombre(nombre));
				textFieldNombre.setText(controllerInterfaz.findNombrePorNombre(nombre));
				textFieldApellidos.setText(controllerInterfaz.findApellidosPorNombre(nombre));
				textFieldFechaNacimiento.setText(controllerInterfaz.findFechaNacimientoPorNombre(nombre));
				textFieldSexo.setText(controllerInterfaz.findSexoPorNombre(nombre));
				textFieldLugarNacimiento.setText(controllerInterfaz.findLugarNacimientoPorNombre(nombre));
				textFieldAltura.setText(controllerInterfaz.findAlturaPorNombre(nombre)+"");
				textFieldPeso.setText(controllerInterfaz.findPesoPorNombre(nombre)+"");
				textFieldGrupoSanguineo.setText(controllerInterfaz.findGrupoSanguineoPorNombre(nombre));
				
			}
		});
		textFieldEscribirNombre.setBounds(348, 35, 167, 19);
		contentPane.add(textFieldEscribirNombre);
		textFieldEscribirNombre.setColumns(10);

		textFieldDni = new JTextField();
		textFieldDni.setEditable(false);
		textFieldDni.setBounds(230, 94, 180, 19);
		contentPane.add(textFieldDni);
		textFieldDni.setColumns(10);

		textFieldNombre = new JTextField();
		textFieldNombre.setEditable(false);
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(230, 128, 180, 19);

		contentPane.add(textFieldNombre);

		textFieldApellidos = new JTextField();
		textFieldApellidos.setEditable(false);
		textFieldApellidos.setColumns(10);
		textFieldApellidos.setBounds(230, 163, 180, 19);

		contentPane.add(textFieldApellidos);

		textFieldFechaNacimiento = new JTextField();
		textFieldFechaNacimiento.setEditable(false);
		textFieldFechaNacimiento.setColumns(10);
		textFieldFechaNacimiento.setBounds(230, 195, 180, 19);

		contentPane.add(textFieldFechaNacimiento);

		textFieldSexo = new JTextField();
		textFieldSexo.setEditable(false);
		textFieldSexo.setColumns(10);
		textFieldSexo.setBounds(230, 225, 180, 19);

		contentPane.add(textFieldSexo);
		
		lblLugarDeNacimiento = new JLabel("Lugar de Nacimiento:");
		lblLugarDeNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLugarDeNacimiento.setBounds(92, 253, 119, 19);
		contentPane.add(lblLugarDeNacimiento);
		
		textFieldLugarNacimiento = new JTextField();
		textFieldLugarNacimiento.setEditable(false);
		textFieldLugarNacimiento.setColumns(10);
		textFieldLugarNacimiento.setBounds(230, 254, 180, 19);
		contentPane.add(textFieldLugarNacimiento);
		
		lblAltura = new JLabel("Altura:");
		lblAltura.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAltura.setBounds(92, 288, 119, 19);
		contentPane.add(lblAltura);
		
		textFieldAltura = new JTextField();
		textFieldAltura.setEditable(false);
		textFieldAltura.setColumns(10);
		textFieldAltura.setBounds(230, 289, 180, 19);
		contentPane.add(textFieldAltura);
		
		lblPeso = new JLabel("Peso:");
		lblPeso.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPeso.setBounds(92, 317, 119, 19);
		contentPane.add(lblPeso);
		
		textFieldPeso = new JTextField();
		textFieldPeso.setEditable(false);
		textFieldPeso.setColumns(10);
		textFieldPeso.setBounds(230, 318, 180, 19);
		contentPane.add(textFieldPeso);
		
		lblGrupoSanguineo = new JLabel("Grupo sanguineo:");
		lblGrupoSanguineo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGrupoSanguineo.setBounds(92, 348, 119, 19);
		contentPane.add(lblGrupoSanguineo);
		
		textFieldGrupoSanguineo = new JTextField();
		textFieldGrupoSanguineo.setEditable(false);
		textFieldGrupoSanguineo.setColumns(10);
		textFieldGrupoSanguineo.setBounds(230, 349, 180, 19);
		contentPane.add(textFieldGrupoSanguineo);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				principal = new VentanaPrincipalMedico();
				principal.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBackground(new Color(240, 240, 240));
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVolver.setBounds(19, 33, 85, 21);
		contentPane.add(btnVolver);
	}
}
