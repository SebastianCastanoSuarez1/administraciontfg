package view.medico;

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

public class VentanaPrincipalMedico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnCancelar;
	VentanaAnadirMedico agregar;
	VentanaEliminarMedico eliminar;
	VentanaModificarMedico modificar;
	VentanaAnadirPacientesCargo pacientesCargo;
	private VentanaPrincipalAdministrador principal;
	VentanaMostrarPorNombre mostrarNombre;
	private JMenuBar menuBar;
	JMenu mnAnadirMedicos, mnEliminarMedico, mnMostrar, mnModificar;
	JMenuItem mntmAnadirNuevoMedico, mntmAnadirPacientesCargo, mntmEliminarMedico, mntmMostrarMedico,
			mntmModificarPaciente, mntmMostrarPorDNI, mntmMostrarPorNombre, mntmMostrarPorAtributo;
	JLabel lblNewLabel;
	VentanaMostrarPorDni mostrarDni;
	VentanaMostrarPorAtributo mostrarAtributo;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipalMedico frame = new VentanaPrincipalMedico();
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
	public VentanaPrincipalMedico() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 416, 300);
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

		mnAnadirMedicos = new JMenu("Añadir medicos");
		mnAnadirMedicos.setBorder(new LineBorder(new Color(0, 0, 0)));
		menuBar.add(mnAnadirMedicos);

		mntmAnadirNuevoMedico = new JMenuItem("Añadir nuevo medico\r\n");
		mntmAnadirNuevoMedico.setBorder(new LineBorder(new Color(0, 0, 0)));
		mntmAnadirNuevoMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregar = new VentanaAnadirMedico();
				agregar.setVisible(true);
				dispose();
			}
		});
		mnAnadirMedicos.add(mntmAnadirNuevoMedico);

		mntmAnadirPacientesCargo = new JMenuItem("Añadir pacientes a cargo");
		mntmAnadirPacientesCargo.setBorder(new LineBorder(new Color(0, 0, 0)));
		mntmAnadirPacientesCargo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pacientesCargo = new VentanaAnadirPacientesCargo();
				pacientesCargo.setVisible(true);
				dispose();
			}
		});
		mnAnadirMedicos.add(mntmAnadirPacientesCargo);

		mnEliminarMedico = new JMenu("Eliminar medico");
		mnEliminarMedico.setBorder(new LineBorder(new Color(0, 0, 0)));
		menuBar.add(mnEliminarMedico);

		mntmEliminarMedico = new JMenuItem("Eliminar medico");
		mntmEliminarMedico.setBorder(new LineBorder(new Color(0, 0, 0)));
		mntmEliminarMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar = new VentanaEliminarMedico();
				eliminar.setVisible(true);
				dispose();
			}
		});
		mnEliminarMedico.add(mntmEliminarMedico);

		mnMostrar = new JMenu("Mostrar medico");
		mnMostrar.setBorder(new LineBorder(new Color(0, 0, 0)));
		menuBar.add(mnMostrar);

		mntmMostrarMedico = new JMenuItem("Mostrar todos los medicos");
		mntmMostrarMedico.setBorder(new LineBorder(new Color(0, 0, 0)));
		mntmMostrarMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		mnMostrar.add(mntmMostrarMedico);

		mntmMostrarPorDNI = new JMenuItem("Mostrar medico por DNI");
		mntmMostrarPorDNI.setBorder(new LineBorder(new Color(0, 0, 0)));
		mntmMostrarPorDNI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarDni = new VentanaMostrarPorDni();
				mostrarDni.setVisible(true);
				dispose();
			}
		});
		mnMostrar.add(mntmMostrarPorDNI);

		mntmMostrarPorNombre = new JMenuItem("Mostrar medico por nombre");
		mntmMostrarPorNombre.setBorder(new LineBorder(new Color(0, 0, 0)));
		mntmMostrarPorNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarNombre = new VentanaMostrarPorNombre();
				mostrarNombre.setVisible(true);
				dispose();
			}
		});
		mnMostrar.add(mntmMostrarPorNombre);

		mntmMostrarPorAtributo = new JMenuItem("Mostrar medico por atributo");
		mntmMostrarPorAtributo.setBorder(new LineBorder(new Color(0, 0, 0)));
		mntmMostrarPorAtributo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarAtributo = new VentanaMostrarPorAtributo();
				mostrarAtributo.setVisible(true);
				dispose();
			}
		});
		mnMostrar.add(mntmMostrarPorAtributo);

		mnModificar = new JMenu("Modificar medico");
		mnModificar.setBorder(new LineBorder(new Color(0, 0, 0)));
		menuBar.add(mnModificar);

		mntmModificarPaciente = new JMenuItem("Modificar medico");
		mntmModificarPaciente.setBorder(new LineBorder(new Color(0, 0, 0)));
		mntmModificarPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar = new VentanaModificarMedico();
				modificar.setVisible(true);
				dispose();
			}
		});
		mnModificar.add(mntmModificarPaciente);
	}
}
