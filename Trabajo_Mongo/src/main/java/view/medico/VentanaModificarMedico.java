package view.medico;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Optional;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import org.bson.Document;

import controller.MedicoController_Interfaz;

public class VentanaModificarMedico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> comboBoxAtributo;
	private JLabel lblValor;
	private JFormattedTextField formattedDni;
	private JButton btnComprobar;
	private final MedicoController_Interfaz medicoInterfaz = new MedicoController_Interfaz();
	private MaskFormatter mascara;
	private JLabel lblDNI;
	private JLabel lblTexto;
	private JButton btnCancelar;
	private JButton btnGuardar;
	private VentanaPrincipalMedico vm;
	private JLabel lblNombre;
	private JTextField textFieldValorAtributo;
	private JTextField textFieldNombre;
	private JLabel lblMensaje;
	private JTextField textFieldValorAntiguo;
	JLabel lblValorAntiguoAtributo;
	String atributo;
	String dni;
	String valor;
	String atributoOtro;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaModificarMedico frame = new VentanaModificarMedico();
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
	public VentanaModificarMedico() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 432);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblTexto = new JLabel("Que atributo quiere modificar");
		lblTexto.setVisible(false);
		lblTexto.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTexto.setBounds(10, 92, 235, 30);
		contentPane.add(lblTexto);

		comboBoxAtributo = new JComboBox<String>();
		comboBoxAtributo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dni = formattedDni.getText();
				atributo = comboBoxAtributo.getSelectedItem().toString();
				String valor = medicoInterfaz.findAtribtuto(dni, atributo);
				if (atributo.equals("Otro")) {
					lblNombre.setVisible(true);
					textFieldNombre.setVisible(true);
//					lblValorAntiguoAtributo.setVisible(true);
//					textFieldValorAntiguo.setVisible(true);
//					textFieldValorAntiguo.setText(valor);
				} else {
					lblValor.setVisible(true);
					textFieldValorAtributo.setVisible(true);
					lblValorAntiguoAtributo.setVisible(true);
					textFieldValorAntiguo.setVisible(true);
					textFieldValorAntiguo.setText(valor);
				}
			}
		});
		comboBoxAtributo.setVisible(false);
		comboBoxAtributo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxAtributo.setModel(new DefaultComboBoxModel<String>(
				new String[] { "", "Nombre", "Apellidos", "Especialidad", "Fecha_Incorporacion","Otro"}));

		comboBoxAtributo.setBounds(258, 97, 136, 21);
		contentPane.add(comboBoxAtributo);

		lblValor = new JLabel("Introduzca el nuevo valor del atributo\r\n");
		lblValor.setVisible(false);
		lblValor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblValor.setBounds(10, 240, 286, 21);
		contentPane.add(lblValor);

		lblDNI = new JLabel("DNI");
		lblDNI.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDNI.setBounds(92, 17, 55, 30);
		contentPane.add(lblDNI);

		try {
			mascara = new MaskFormatter("########?");
			mascara.setValidCharacters("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
			formattedDni = new JFormattedTextField(mascara);
			formattedDni.setBounds(170, 23, 148, 26);
			contentPane.add(formattedDni);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		btnComprobar = new JButton("Comprobar");
		btnComprobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnComprobar == e.getSource()) {
					dni = formattedDni.getText().toString();
					Optional<Document> medicos = medicoInterfaz.comprobarDni(dni);

					if (medicos.isPresent()) {
						lblTexto.setVisible(true);
						comboBoxAtributo.setVisible(true);
					} else {
						String mensaje = "El medico con DNI " + formattedDni.getText() + " no existe ";
						JOptionPane.showMessageDialog(null, mensaje, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnComprobar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnComprobar.setBounds(350, 21, 98, 27);
		contentPane.add(btnComprobar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vm = new VentanaPrincipalMedico();
				vm.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(129, 300, 85, 30);
		contentPane.add(btnCancelar);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnGuardar == e.getSource()) {
					dni = formattedDni.getText().toString();
					atributo = comboBoxAtributo.getSelectedItem().toString();
					valor = textFieldValorAtributo.getText();
					switch (atributo) {
					case "Nombre":

						medicoInterfaz.valorAtributoNuevo(dni, atributo, valor);
						lblMensaje.setText("Medico actualizado correctamente");
						lblMensaje.setForeground(Color.GREEN);
						break;
					case "Apellidos":

						medicoInterfaz.valorAtributoNuevo(dni, atributo, valor);
						lblMensaje.setText("Medico actualizado correctamente");
						lblMensaje.setForeground(Color.GREEN);
						break;
					case "Especialidad":

						medicoInterfaz.valorAtributoNuevo(dni, atributo, valor);
						lblMensaje.setText("Medico actualizado correctamente");
						lblMensaje.setForeground(Color.GREEN);
						break;
					case "Fecha_Incorporacion":

						medicoInterfaz.valorAtributoNuevo(dni, atributo, valor);
						lblMensaje.setText("Medico actualizado correctamente");
						lblMensaje.setForeground(Color.GREEN);
						break;
					case "Otro":
						String atributoOtro = textFieldNombre.getText();
						medicoInterfaz.valorAtributoNuevo(dni, atributoOtro, valor);
						lblMensaje.setText("Medico actualizado correctamente");
						lblMensaje.setForeground(Color.GREEN);
						break;

					default:
						break;
					}
				}
			}

		});
		btnGuardar.setBounds(273, 300, 98, 30);
		contentPane.add(btnGuardar);

		textFieldValorAtributo = new JTextField();
		textFieldValorAtributo.setVisible(false);
		textFieldValorAtributo.setBounds(10, 271, 258, 19);
		contentPane.add(textFieldValorAtributo);
		textFieldValorAtributo.setColumns(10);

		lblNombre = new JLabel("Introduzca el nombre de atributo\r\n");
		lblNombre.setVisible(false);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombre.setBounds(10, 188, 258, 13);
		contentPane.add(lblNombre);

		textFieldNombre = new JTextField();
		textFieldNombre.setVisible(false);
		textFieldNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atributoOtro = textFieldNombre.getText();

				if (!atributoOtro.equals("")) {
					lblValor.setVisible(true);
					textFieldValorAtributo.setVisible(true);
				} else {
					lblValor.setVisible(false);
					textFieldValorAtributo.setVisible(false);
				}
			}
		});
		textFieldNombre.setBounds(10, 211, 258, 19);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		lblMensaje = new JLabel("");
		lblMensaje.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMensaje.setBounds(92, 340, 337, 25);
		contentPane.add(lblMensaje);
		
		
		lblValorAntiguoAtributo = new JLabel("Valor antiguo del atributo seleccionado");
		lblValorAntiguoAtributo.setVisible(false);
		lblValorAntiguoAtributo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblValorAntiguoAtributo.setBounds(10, 125, 286, 30);
		contentPane.add(lblValorAntiguoAtributo);
		
		textFieldValorAntiguo = new JTextField();
		textFieldValorAntiguo.setVisible(false);
		
		textFieldValorAntiguo.setBounds(10, 159, 258, 19);
		contentPane.add(textFieldValorAntiguo);
		textFieldValorAntiguo.setColumns(10);

	}
}