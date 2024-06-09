package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import view.medico.VentanaPrincipalMedico;
import view.paciente.VentanaPrincipalPaciente;

public class VentanaPrincipalAdministrador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private VentanaPrincipalPaciente vpp;
	private VentanaPrincipalMedico vpm;
	private JLabel lblBienvenido, lblEligaLaOpcion;
	private JLabel lblLogo;
	private JLabel lblIrMedicos;
	private JLabel lblLogo_1;
	private JLabel lblIrAPacientes;

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
		setBounds(100, 100, 497, 364);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblBienvenido = new JLabel("Bienvenido");
		lblBienvenido.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBienvenido.setBounds(191, 22, 108, 26);
		contentPane.add(lblBienvenido);

		lblEligaLaOpcion = new JLabel("Eliga la opcion de prefiera\r\n");
		lblEligaLaOpcion.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblEligaLaOpcion.setBounds(124, 58, 254, 26);
		contentPane.add(lblEligaLaOpcion);

		lblLogo = new JLabel();
		lblLogo.setBackground(new Color(230, 230, 250));
		lblLogo.setBounds(39, 109, 179, 142);
		contentPane.add(lblLogo);
		lblLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				vpm = new VentanaPrincipalMedico();
				vpm.setVisible(true);
				dispose();
			}
		});

		ImageIcon logo = new ImageIcon("src\\main\\resources\\multimedia\\medico.png");
		Image img = logo.getImage();
		int labelWidth = lblLogo.getWidth();
		int labelHeight = lblLogo.getHeight();
		Image newImg = img.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
		logo = new ImageIcon(newImg);
		lblLogo.setIcon(logo);

		lblIrMedicos = new JLabel("Ir a medicos");
		lblIrMedicos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				vpm = new VentanaPrincipalMedico();
				vpm.setVisible(true);
				dispose();
			}
		});
		lblIrMedicos.setHorizontalAlignment(SwingConstants.CENTER);
		lblIrMedicos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIrMedicos.setBounds(32, 266, 198, 20);
		contentPane.add(lblIrMedicos);

		lblLogo_1 = new JLabel();
		lblLogo_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				vpp = new VentanaPrincipalPaciente();
				vpp.setVisible(true);
				dispose();
			}
		});
		lblLogo_1.setBackground(new Color(230, 230, 250));
		lblLogo_1.setBounds(268, 127, 188, 142);
		contentPane.add(lblLogo_1);
		
		ImageIcon logo1 = new ImageIcon("src\\main\\resources\\multimedia\\paciente.png");
		Image img1 = logo1.getImage();
		int labelWidth1 = lblLogo_1.getWidth();
		int labelHeight1 = lblLogo_1.getHeight();
		Image newImg1 = img1.getScaledInstance(labelWidth1, labelHeight1, Image.SCALE_SMOOTH);
		logo1 = new ImageIcon(newImg1);
		lblLogo_1.setIcon(logo1);

		lblIrAPacientes = new JLabel("Ir a pacientes");
		lblIrAPacientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				vpp = new VentanaPrincipalPaciente();
				vpp.setVisible(true);
				dispose();
			}
		});
		lblIrAPacientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblIrAPacientes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIrAPacientes.setBounds(252, 266, 198, 20);
		contentPane.add(lblIrAPacientes);
	}
}
