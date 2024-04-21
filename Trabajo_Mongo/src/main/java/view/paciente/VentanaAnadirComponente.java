package view.paciente;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class VentanaAnadirComponente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btn_List;
	private JButton btn_Valor;
	private JButton btn_List_Valor;
	private JLabel lblNewLabelTitle;
	private JButton btnCancelar;
	private VentanaOpcionAnadirPaciente voa;
	private VentanaComponentesLista vl;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAnadirComponente frame = new VentanaAnadirComponente();
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
	public VentanaAnadirComponente() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		btn_List = new JButton("Listas");
		btn_List.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vl = new VentanaComponentesLista();
				vl.setVisible(true);
				dispose();
			}
		});
		btn_List.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_List.setBounds(155, 127, 114, 47);
		contentPane.add(btn_List);

		btn_Valor = new JButton("Valores\r\n");
		btn_Valor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_Valor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaComponentesValores vc = new VentanaComponentesValores();
				vc.setVisible(true);
				dispose();
			}
		});
		btn_Valor.setBounds(290, 127, 114, 47);
		contentPane.add(btn_Valor);

		btn_List_Valor = new JButton("Listas y valores");
		btn_List_Valor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaComponentesListasValores vc = new VentanaComponentesListasValores();
				vc.setVisible(true);
				dispose();
			}
		});
		btn_List_Valor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_List_Valor.setBounds(20, 127, 114, 47);
		contentPane.add(btn_List_Valor);

		lblNewLabelTitle = new JLabel("Añadir componentes");
		lblNewLabelTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabelTitle.setBounds(137, 33, 224, 38);
		contentPane.add(lblNewLabelTitle);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				voa = new VentanaOpcionAnadirPaciente();
				voa.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancelar.setBounds(155, 206, 114, 47);
		contentPane.add(btnCancelar);
	}
}
