package view.medico;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controller.MedicoController_Interfaz;

public class VentanaMostrarPorAtributo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldDni, textFieldNombre, textFieldApellidos, textFieldEspecialidad, textFieldFechaIncorporacion;
	JLabel lblFechaIncorporacion, lblEspecialidad, lblApellidos, lblNombre, lblDNI, lblIntroduzcaNombre;
	private MedicoController_Interfaz controllerInterfaz = new MedicoController_Interfaz();
	private MaskFormatter mascara;
	private JTextField textFieldAtributo;
	private JTextField textFieldValor;
	JButton btnVolver;
	VentanaPrincipalMedico principal;
	private JLabel lblTitulo;

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
		lblIntroduzcaNombre.setBounds(123, 70, 231, 22);
		contentPane.add(lblIntroduzcaNombre);

		lblDNI = new JLabel("DNI:");
		lblDNI.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDNI.setBounds(96, 164, 50, 22);
		contentPane.add(lblDNI);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombre.setBounds(96, 201, 50, 22);
		contentPane.add(lblNombre);

		lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblApellidos.setBounds(96, 233, 62, 22);
		contentPane.add(lblApellidos);

		lblEspecialidad = new JLabel("Especialidad:");
		lblEspecialidad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEspecialidad.setBounds(96, 265, 119, 22);
		contentPane.add(lblEspecialidad);

		lblFechaIncorporacion = new JLabel("Fecha incorporacion:");
		lblFechaIncorporacion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFechaIncorporacion.setBounds(96, 297, 119, 19);
		contentPane.add(lblFechaIncorporacion);

		try {
			mascara = new MaskFormatter("########?");
			mascara.setValidCharacters("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");

		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		textFieldDni = new JTextField();
		textFieldDni.setEditable(false);
		textFieldDni.setBounds(234, 167, 180, 19);
		contentPane.add(textFieldDni);
		textFieldDni.setColumns(10);

		textFieldNombre = new JTextField();
		textFieldNombre.setEditable(false);
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(234, 201, 180, 19);

		contentPane.add(textFieldNombre);

		textFieldApellidos = new JTextField();
		textFieldApellidos.setEditable(false);
		textFieldApellidos.setColumns(10);
		textFieldApellidos.setBounds(234, 236, 180, 19);

		contentPane.add(textFieldApellidos);

		textFieldEspecialidad = new JTextField();
		textFieldEspecialidad.setEditable(false);
		textFieldEspecialidad.setColumns(10);
		textFieldEspecialidad.setBounds(234, 268, 180, 19);

		contentPane.add(textFieldEspecialidad);

		textFieldFechaIncorporacion = new JTextField();
		textFieldFechaIncorporacion.setEditable(false);
		textFieldFechaIncorporacion.setColumns(10);
		textFieldFechaIncorporacion.setBounds(234, 298, 180, 19);

		contentPane.add(textFieldFechaIncorporacion);
		
		textFieldAtributo = new JTextField();
		textFieldAtributo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntroduzcaNombre.setText("Introduzca el valor de atributo");
				textFieldValor.setVisible(true);
				textFieldAtributo.setVisible(false);
				
				
			}
		});
		textFieldAtributo.setBounds(364, 73, 138, 19);
		contentPane.add(textFieldAtributo);
		textFieldAtributo.setColumns(10);
		
		textFieldValor = new JTextField();
		textFieldValor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String atributo = textFieldAtributo.getText();
				String valor = textFieldValor.getText();
				try {
				
				textFieldDni.setText(controllerInterfaz.findDniPorAtributo(atributo,valor));
				textFieldNombre.setText(controllerInterfaz.findNombrePorAtributo(atributo,valor));
				textFieldApellidos.setText(controllerInterfaz.findApellidosPorAtributo(atributo,valor));
				textFieldEspecialidad.setText(controllerInterfaz.findEspcialidadPorAtributo(atributo,valor));
				textFieldFechaIncorporacion.setText(controllerInterfaz.findFechaIncorporacionPorAtributo(atributo,valor));
				}catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(VentanaMostrarPorAtributo.this,
							"El atributo " + atributo + " y el valor " + valor + " no existen en la base de datos");
				}
			}
		});
		textFieldValor.setVisible(false);
		textFieldValor.setColumns(10);
		textFieldValor.setBounds(364, 73, 138, 19);
		contentPane.add(textFieldValor);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				principal = new VentanaPrincipalMedico();
				principal.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBackground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVolver.setBounds(28, 71, 85, 21);
		contentPane.add(btnVolver);
		
		lblTitulo = new JLabel("Mostrar medico por atributo");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitulo.setBounds(151, 22, 244, 22);
		contentPane.add(lblTitulo);
		
	}
}
