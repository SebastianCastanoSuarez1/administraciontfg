package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.medico.VentanaPrincipalMedico;
import view.paciente.VentanaPrincipalPaciente;

public class VentanaPrincipalAdministrador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	VentanaPrincipalPaciente vpp;
	VentanaPrincipalMedico vpm;
	JLabel lblBienvenido;
	JLabel lblEligaLaOpcion;
	JButton btnMedico;
	JButton btnPaciente;/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipalAdministrador frame = new VentanaPrincipalAdministrador();
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
	public VentanaPrincipalAdministrador() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 468, 329);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblBienvenido = new JLabel("Bienvenido");
		lblBienvenido.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBienvenido.setBounds(170, 37, 108, 26);
		contentPane.add(lblBienvenido);
		
		JLabel labelImagen = new JLabel();
		labelImagen.setBounds(170, 100, 100, 100); // Ajusta estos valores según necesites
		contentPane.add(labelImagen);
		
		lblEligaLaOpcion = new JLabel("Eliga la opcion de prefiera\r\n");
		lblEligaLaOpcion.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblEligaLaOpcion.setBounds(103, 73, 241, 26);
		contentPane.add(lblEligaLaOpcion);
		
		btnMedico = new JButton("Ir a medicos");
		btnMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vpm = new VentanaPrincipalMedico();
				vpm.setVisible(true);
				dispose();
			}
		});
		btnMedico.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnMedico.setBounds(66, 157, 118, 43);
		contentPane.add(btnMedico);
		
		btnPaciente = new JButton("Ir a pacientes");
		btnPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vpp  = new VentanaPrincipalPaciente();
				vpp.setVisible(true);
				dispose();
			}
		});
		btnPaciente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPaciente.setBounds(237, 157, 131, 43);
		contentPane.add(btnPaciente);
	}
}
