package view.medico;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.MedicoController_Interfaz;

public class VentanaMostrarPorNombre extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldEscribirNombre, textFieldDni, textFieldNombre, textFieldApellidos,
			textFieldEspecialidad, textFieldFechaIncorporacion;
	JLabel lblFechaIncorporacion, lblEspecialidad, lblApellidos, lblNombre, lblDNI, lblIntroduzcaNombre;
	private MedicoController_Interfaz controllerInterfaz = new MedicoController_Interfaz();
	JButton btnVolver;
	VentanaPrincipalMedico principal;
	private JLabel lblMostrarMedicoPor;

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
		lblIntroduzcaNombre.setBounds(114, 104, 234, 22);
		contentPane.add(lblIntroduzcaNombre);

		lblDNI = new JLabel("DNI:");
		lblDNI.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDNI.setBounds(92, 163, 50, 22);
		contentPane.add(lblDNI);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombre.setBounds(92, 200, 50, 22);
		contentPane.add(lblNombre);

		lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblApellidos.setBounds(92, 232, 62, 22);
		contentPane.add(lblApellidos);

		lblEspecialidad = new JLabel("Especialidad:\r\n");
		lblEspecialidad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEspecialidad.setBounds(92, 264, 119, 22);
		contentPane.add(lblEspecialidad);

		lblFechaIncorporacion = new JLabel("Fecha incorporacion:");
		lblFechaIncorporacion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFechaIncorporacion.setBounds(92, 296, 119, 19);
		contentPane.add(lblFechaIncorporacion);

		textFieldEscribirNombre = new JTextField();
		textFieldEscribirNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = textFieldEscribirNombre.getText();
				textFieldDni.setText(controllerInterfaz.findDniPorNombre(nombre));
				textFieldNombre.setText(controllerInterfaz.findNombrePorNombre(nombre));
				textFieldApellidos.setText(controllerInterfaz.findApellidosPorNombre(nombre));
				textFieldEspecialidad.setText(controllerInterfaz.findEspecialidadPorNombre(nombre));
				textFieldFechaIncorporacion.setText(controllerInterfaz.findFechaIncorporacionPorNombre(nombre));
				
				
			}
		});
		textFieldEscribirNombre.setBounds(348, 107, 167, 19);
		contentPane.add(textFieldEscribirNombre);
		textFieldEscribirNombre.setColumns(10);

		textFieldDni = new JTextField();
		textFieldDni.setEditable(false);
		textFieldDni.setBounds(230, 166, 180, 19);
		contentPane.add(textFieldDni);
		textFieldDni.setColumns(10);

		textFieldNombre = new JTextField();
		textFieldNombre.setEditable(false);
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(230, 200, 180, 19);

		contentPane.add(textFieldNombre);

		textFieldApellidos = new JTextField();
		textFieldApellidos.setEditable(false);
		textFieldApellidos.setColumns(10);
		textFieldApellidos.setBounds(230, 235, 180, 19);

		contentPane.add(textFieldApellidos);

		textFieldEspecialidad = new JTextField();
		textFieldEspecialidad.setEditable(false);
		textFieldEspecialidad.setColumns(10);
		textFieldEspecialidad.setBounds(230, 267, 180, 19);

		contentPane.add(textFieldEspecialidad);

		textFieldFechaIncorporacion = new JTextField();
		textFieldFechaIncorporacion.setEditable(false);
		textFieldFechaIncorporacion.setColumns(10);
		textFieldFechaIncorporacion.setBounds(230, 297, 180, 19);

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
		btnVolver.setBounds(19, 105, 85, 21);
		contentPane.add(btnVolver);
		
		lblMostrarMedicoPor = new JLabel("Mostrar medico por nombre");
		lblMostrarMedicoPor.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMostrarMedicoPor.setBounds(140, 29, 234, 22);
		contentPane.add(lblMostrarMedicoPor);
	}
}
