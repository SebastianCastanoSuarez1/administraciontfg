package view.medico;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controller.MedicoController_Interfaz;

public class VentanaMostrarPorDni extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldDni, textFieldNombre, textFieldApellidos, textFieldEspecialidad, textFieldFechaIncorporacion;
	JLabel lblFechaIncorporacion, lblEspecialidad, lblApellidos, lblNombre, lblDNI, lblIntroduzcaNombre;
	private MedicoController_Interfaz controllerInterfaz = new MedicoController_Interfaz();
	JFormattedTextField formattedTextFieldDni;
	private MaskFormatter mascara;
	JButton btnVolver;
	VentanaPrincipalMedico principal;

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
		setBounds(100, 100, 539, 489);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblIntroduzcaNombre = new JLabel("Introduzca el DNI del paciente");
		lblIntroduzcaNombre.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIntroduzcaNombre.setBounds(140, 31, 210, 22);
		contentPane.add(lblIntroduzcaNombre);

		lblDNI = new JLabel("DNI:");
		lblDNI.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDNI.setBounds(93, 115, 50, 22);
		contentPane.add(lblDNI);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombre.setBounds(93, 152, 50, 22);
		contentPane.add(lblNombre);

		lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblApellidos.setBounds(93, 184, 62, 22);
		contentPane.add(lblApellidos);

		lblEspecialidad = new JLabel("Especialdad:");
		lblEspecialidad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEspecialidad.setBounds(93, 216, 119, 22);
		contentPane.add(lblEspecialidad);

		lblFechaIncorporacion = new JLabel("Fecha incorporacion:");
		lblFechaIncorporacion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFechaIncorporacion.setBounds(93, 248, 119, 19);
		contentPane.add(lblFechaIncorporacion);

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
					textFieldEspecialidad.setText(controllerInterfaz.findEspecialidadPorDni(nombre));
					textFieldFechaIncorporacion.setText(controllerInterfaz.findFechaIncoporacionPorDni(nombre));
					
				}
			});
			formattedTextFieldDni.setBounds(360, 34, 138, 19);
			contentPane.add(formattedTextFieldDni);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		textFieldDni = new JTextField();
		textFieldDni.setEditable(false);
		textFieldDni.setBounds(231, 118, 180, 19);
		contentPane.add(textFieldDni);
		textFieldDni.setColumns(10);

		textFieldNombre = new JTextField();
		textFieldNombre.setEditable(false);
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(231, 152, 180, 19);

		contentPane.add(textFieldNombre);

		textFieldApellidos = new JTextField();
		textFieldApellidos.setEditable(false);
		textFieldApellidos.setColumns(10);
		textFieldApellidos.setBounds(231, 187, 180, 19);

		contentPane.add(textFieldApellidos);

		textFieldEspecialidad = new JTextField();
		textFieldEspecialidad.setEditable(false);
		textFieldEspecialidad.setColumns(10);
		textFieldEspecialidad.setBounds(231, 219, 180, 19);

		contentPane.add(textFieldEspecialidad);

		textFieldFechaIncorporacion = new JTextField();
		textFieldFechaIncorporacion.setEditable(false);
		textFieldFechaIncorporacion.setColumns(10);
		textFieldFechaIncorporacion.setBounds(231, 249, 180, 19);

		contentPane.add(textFieldFechaIncorporacion);
		
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
		btnVolver.setBounds(27, 32, 85, 21);
		contentPane.add(btnVolver);
		
	}
}
