package view.paciente;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.VentanaPrincipalAdministrador;
import javax.swing.border.LineBorder;

public class VentanaPrincipalPaciente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnCancelar;
	VentanaAgregarPaciente agregar;
	VentanaAñadirHistorialMedico historial;
	private VentanaModificarPaciente modificar;
	private VentanaEliminarPaciente eliminar;
	private VentanaPrincipalAdministrador principal;
	VentanaMostrarPorNombre mostrarNombre;
	private JMenuBar menuBar;
	JMenu mnAnadirPacientes, mnEliminarPaciente, mnMostrar, mnModificar;
	JMenuItem mntmAnadirNuevoPaciente, mntmAnadiHistorialMedico, mntmEliminarPaciente, mntmMostrarPaciente,
			mntmModificarPaciente, mntmMostrarPorDNI, mntmMostrarPorNombre, mntmMostrarPorAtributo;
	JLabel lblNewLabel;
	VentanaMostrarPorDni mostrarDni;
	VentanaMostrarPorAtributo mostrarAtributo;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipalPaciente frame = new VentanaPrincipalPaciente();
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
	public VentanaPrincipalPaciente() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNewLabel = new JLabel("Clinica DABAS\r\n");
		lblNewLabel.setBackground(new Color(255, 255, 128));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(150, 10, 148, 45);
		contentPane.add(lblNewLabel);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				principal = new VentanaPrincipalAdministrador();
				principal.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(150, 181, 136, 23);
		contentPane.add(btnCancelar);

		menuBar = new JMenuBar();
		menuBar.setBorder(new LineBorder(new Color(0, 0, 0)));
		menuBar.setBackground(new Color(230, 230, 250));
		menuBar.setBounds(0, 65, 436, 22);
		contentPane.add(menuBar);

		mnAnadirPacientes = new JMenu("Añadir pacientes");
		mnAnadirPacientes.setBorder(new LineBorder(new Color(0, 0, 0)));
		menuBar.add(mnAnadirPacientes);

		mntmAnadirNuevoPaciente = new JMenuItem("Añadir nuevo paciente");
		mntmAnadirNuevoPaciente.setBorder(new LineBorder(new Color(0, 0, 0)));
		mntmAnadirNuevoPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregar = new VentanaAgregarPaciente();
				agregar.setVisible(true);
				dispose();
			}
		});
		mnAnadirPacientes.add(mntmAnadirNuevoPaciente);

		mntmAnadiHistorialMedico = new JMenuItem("Añadir historial medico");
		mntmAnadiHistorialMedico.setBorder(new LineBorder(new Color(0, 0, 0)));
		mntmAnadiHistorialMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				historial = new VentanaAñadirHistorialMedico();
				historial.setVisible(true);
				dispose();
			}
		});
		mnAnadirPacientes.add(mntmAnadiHistorialMedico);

		mnEliminarPaciente = new JMenu("Eliminar paciente");
		mnEliminarPaciente.setBorder(new LineBorder(new Color(0, 0, 0)));
		menuBar.add(mnEliminarPaciente);

		mntmEliminarPaciente = new JMenuItem("Eliminar paciente");
		mntmEliminarPaciente.setBorder(new LineBorder(new Color(0, 0, 0)));
		mntmEliminarPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar = new VentanaEliminarPaciente();
				eliminar.setVisible(true);
				dispose();
			}
		});
		mnEliminarPaciente.add(mntmEliminarPaciente);

		mnMostrar = new JMenu("Mostrar paciente");
		mnMostrar.setBorder(new LineBorder(new Color(0, 0, 0)));
		menuBar.add(mnMostrar);

		mntmMostrarPaciente = new JMenuItem("Mostrar todos los pacientes");
		mntmMostrarPaciente.setBorder(new LineBorder(new Color(0, 0, 0)));
		mntmMostrarPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		mnMostrar.add(mntmMostrarPaciente);

		mntmMostrarPorDNI = new JMenuItem("Mostrar paciente por DNI");
		mntmMostrarPorDNI.setBorder(new LineBorder(new Color(0, 0, 0)));
		mntmMostrarPorDNI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarDni = new VentanaMostrarPorDni();
				mostrarDni.setVisible(true);
				dispose();
			}
		});
		mnMostrar.add(mntmMostrarPorDNI);

		mntmMostrarPorNombre = new JMenuItem("Mostrar paciente por nombre");
		mntmMostrarPorNombre.setBorder(new LineBorder(new Color(0, 0, 0)));
		mntmMostrarPorNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarNombre = new VentanaMostrarPorNombre();
				mostrarNombre.setVisible(true);
				dispose();
			}
		});
		mnMostrar.add(mntmMostrarPorNombre);

		mntmMostrarPorAtributo = new JMenuItem("Mostrar paciente por atributo");
		mntmMostrarPorAtributo.setBorder(new LineBorder(new Color(0, 0, 0)));
		mntmMostrarPorAtributo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarAtributo = new VentanaMostrarPorAtributo();
				mostrarAtributo.setVisible(true);
				dispose();
			}
		});
		mnMostrar.add(mntmMostrarPorAtributo);

		mnModificar = new JMenu("Modificar paciente");
		mnModificar.setBorder(new LineBorder(new Color(0, 0, 0)));
		menuBar.add(mnModificar);

		mntmModificarPaciente = new JMenuItem("Modificar paciente");
		mntmModificarPaciente.setBorder(new LineBorder(new Color(0, 0, 0)));
		mntmModificarPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar = new VentanaModificarPaciente();
				modificar.setVisible(true);
				dispose();
			}
		});
		mnModificar.add(mntmModificarPaciente);
	}
}
