package view.paciente;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controller.Controller_Interfaz;

public class VentanaMostrarPorAtributo extends JFrame {

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
	private MaskFormatter mascara;
	private JTextField textFieldAtributo;
	private JTextField textFieldValor;
	JButton btnVolver;
	VentanaPrincipalPaciente principal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMostrarPorAtributo frame = new VentanaMostrarPorAtributo();
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
	public VentanaMostrarPorAtributo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 539, 489);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblIntroduzcaNombre = new JLabel("Introduzca el nombre del atributo\r\n");
		lblIntroduzcaNombre.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIntroduzcaNombre.setBounds(122, 31, 231, 22);
		contentPane.add(lblIntroduzcaNombre);

		lblDNI = new JLabel("DNI:");
		lblDNI.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDNI.setBounds(95, 125, 50, 22);
		contentPane.add(lblDNI);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombre.setBounds(95, 162, 50, 22);
		contentPane.add(lblNombre);

		lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblApellidos.setBounds(95, 194, 62, 22);
		contentPane.add(lblApellidos);

		lblFechaNacimiento = new JLabel("Fecha Nacimiento:");
		lblFechaNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFechaNacimiento.setBounds(95, 226, 119, 22);
		contentPane.add(lblFechaNacimiento);

		lblSexo = new JLabel("Sexo:");
		lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSexo.setBounds(95, 258, 119, 19);
		contentPane.add(lblSexo);

		try {
			mascara = new MaskFormatter("########?");
			mascara.setValidCharacters("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");

		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		textFieldDni = new JTextField();
		textFieldDni.setEditable(false);
		textFieldDni.setBounds(233, 128, 180, 19);
		contentPane.add(textFieldDni);
		textFieldDni.setColumns(10);

		textFieldNombre = new JTextField();
		textFieldNombre.setEditable(false);
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(233, 162, 180, 19);

		contentPane.add(textFieldNombre);

		textFieldApellidos = new JTextField();
		textFieldApellidos.setEditable(false);
		textFieldApellidos.setColumns(10);
		textFieldApellidos.setBounds(233, 197, 180, 19);

		contentPane.add(textFieldApellidos);

		textFieldFechaNacimiento = new JTextField();
		textFieldFechaNacimiento.setEditable(false);
		textFieldFechaNacimiento.setColumns(10);
		textFieldFechaNacimiento.setBounds(233, 229, 180, 19);

		contentPane.add(textFieldFechaNacimiento);

		textFieldSexo = new JTextField();
		textFieldSexo.setEditable(false);
		textFieldSexo.setColumns(10);
		textFieldSexo.setBounds(233, 259, 180, 19);

		contentPane.add(textFieldSexo);

		lblLugarDeNacimiento = new JLabel("Lugar de Nacimiento:");
		lblLugarDeNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLugarDeNacimiento.setBounds(95, 287, 119, 19);
		contentPane.add(lblLugarDeNacimiento);

		textFieldLugarNacimiento = new JTextField();
		textFieldLugarNacimiento.setEditable(false);
		textFieldLugarNacimiento.setColumns(10);
		textFieldLugarNacimiento.setBounds(233, 288, 180, 19);
		contentPane.add(textFieldLugarNacimiento);

		lblAltura = new JLabel("Altura:");
		lblAltura.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAltura.setBounds(95, 322, 119, 19);
		contentPane.add(lblAltura);

		textFieldAltura = new JTextField();
		textFieldAltura.setEditable(false);
		textFieldAltura.setColumns(10);
		textFieldAltura.setBounds(233, 323, 180, 19);
		contentPane.add(textFieldAltura);

		lblPeso = new JLabel("Peso:");
		lblPeso.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPeso.setBounds(95, 351, 119, 19);
		contentPane.add(lblPeso);

		textFieldPeso = new JTextField();
		textFieldPeso.setEditable(false);
		textFieldPeso.setColumns(10);
		textFieldPeso.setBounds(233, 352, 180, 19);
		contentPane.add(textFieldPeso);

		lblGrupoSanguineo = new JLabel("Grupo sanguineo:");
		lblGrupoSanguineo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGrupoSanguineo.setBounds(95, 382, 119, 19);
		contentPane.add(lblGrupoSanguineo);

		textFieldGrupoSanguineo = new JTextField();
		textFieldGrupoSanguineo.setEditable(false);
		textFieldGrupoSanguineo.setColumns(10);
		textFieldGrupoSanguineo.setBounds(233, 383, 180, 19);
		contentPane.add(textFieldGrupoSanguineo);
		
		textFieldAtributo = new JTextField();
		textFieldAtributo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntroduzcaNombre.setText("Introduzca el valor de atributo");
				textFieldValor.setVisible(true);
				textFieldAtributo.setVisible(false);
				
				
			}
		});
		textFieldAtributo.setBounds(363, 34, 138, 19);
		contentPane.add(textFieldAtributo);
		textFieldAtributo.setColumns(10);
		
		textFieldValor = new JTextField();
		textFieldValor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String atributo = textFieldAtributo.getText();
				String valor = textFieldValor.getText();
				textFieldDni.setText(controllerInterfaz.findDniPorAtributo(atributo,valor));
				textFieldNombre.setText(controllerInterfaz.findNombrePorAtributo(atributo,valor));
				textFieldApellidos.setText(controllerInterfaz.findApellidosPorAtributo(atributo,valor));
				textFieldFechaNacimiento.setText(controllerInterfaz.findFechaNacimientoPorAtributo(atributo,valor));
				textFieldSexo.setText(controllerInterfaz.findSexoPorAtributo(atributo,valor));
				textFieldLugarNacimiento.setText(controllerInterfaz.findLugarNacimientoPorAtributo(atributo,valor));
				textFieldAltura.setText(controllerInterfaz.findAlturaPorAtributo(atributo,valor)+"");
				textFieldPeso.setText(controllerInterfaz.findPesoPorAtributo(atributo,valor)+"");
				textFieldGrupoSanguineo.setText(controllerInterfaz.findGrupoSanguineoPorAtributo(atributo,valor));
			}
		});
		textFieldValor.setVisible(false);
		textFieldValor.setColumns(10);
		textFieldValor.setBounds(363, 34, 138, 19);
		contentPane.add(textFieldValor);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				principal = new VentanaPrincipalPaciente();
				principal.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBackground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVolver.setBounds(27, 32, 85, 21);
		contentPane.add(btnVolver);
		
	}
}
