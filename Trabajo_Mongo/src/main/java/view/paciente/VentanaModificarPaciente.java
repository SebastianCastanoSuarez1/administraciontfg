package view.paciente;


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

import controller.Controller_Interfaz;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class VentanaModificarPaciente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> comboBoxAtributo;
	private JFormattedTextField formattedDni;
	private final Controller_Interfaz controllerInterfaz = new Controller_Interfaz();
	private MaskFormatter mascara;
	private JButton btnComprobar, btnCancelar, btnGuardar;
	private JTextField textFieldValorAtributo,textFieldNombre;
	private JLabel lblValor, lblDNI, lblTexto, lblNombre,lblMensaje;
	private VentanaPrincipalPaciente vp;
	private JScrollPane scrollPaneMostrar;
	private JTextArea textAreaMostrar;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaModificarPaciente frame = new VentanaModificarPaciente();
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
	public VentanaModificarPaciente() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 705, 432);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTexto = new JLabel("Que atributo quiere modificar");
		lblTexto.setVisible(false);
		lblTexto.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTexto.setBounds(10, 83, 235, 30);
		contentPane.add(lblTexto);
		
		comboBoxAtributo = new JComboBox<String>();
		comboBoxAtributo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String otro = comboBoxAtributo.getSelectedItem().toString();
				if(otro.equals("Otro")) {
					lblNombre.setVisible(true);
					textFieldNombre.setVisible(true);
				}else {
					lblValor.setVisible(true);
					textFieldValorAtributo.setVisible(true);
					
				}
			}
		});
		comboBoxAtributo.setVisible(false);
		comboBoxAtributo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxAtributo.setModel(
				new DefaultComboBoxModel<String>(new String[] { "", "Nombre", "Apellidos", "Fecha_Nacimiento",
						"Sexo", "Lugar_Nacimiento", "Altura", "Peso", "Grupo_Sanguineo", "Enfemedad", "Tipo", "Otro" }));

		comboBoxAtributo.setBounds(271, 88, 136, 21);
		contentPane.add(comboBoxAtributo);
		
		lblValor = new JLabel("Introduzca el nuevo valor del atributo\r\n");
		lblValor.setVisible(false);
		lblValor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblValor.setBounds(10, 207, 286, 21);
		contentPane.add(lblValor);
		
		lblDNI = new JLabel("DNI");
		lblDNI.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDNI.setBounds(71, 23, 44, 30);
		contentPane.add(lblDNI);
		
		try {
            mascara = new MaskFormatter("########?");
            mascara.setValidCharacters("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            formattedDni = new JFormattedTextField(mascara);
    		formattedDni.setBounds(154, 29, 148, 26);
    		contentPane.add(formattedDni);
    		
    		
        } catch (ParseException e) {
            e.printStackTrace();
        }
		
		btnComprobar = new JButton("Comprobar");
		btnComprobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnComprobar == e.getSource()) {
					String dni = formattedDni.getText().toString();
					Optional<Document> pacientes = controllerInterfaz.comprobarDni(dni);
					
					if(pacientes.isPresent()) {
						lblTexto.setVisible(true);
						comboBoxAtributo.setVisible(true);
						scrollPaneMostrar.setVisible(true);
						textAreaMostrar.setVisible(true);
						textAreaMostrar.setText(controllerInterfaz.mostrar(pacientes));
					}else{
						String mensaje = "El paciente con DNI " + formattedDni.getText() + " no existe "; 
						JOptionPane.showMessageDialog(null, mensaje, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnComprobar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnComprobar.setBounds(347, 27, 98, 27);
		contentPane.add(btnComprobar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp = new VentanaPrincipalPaciente();
				vp.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(88, 295, 86, 30);
		contentPane.add(btnCancelar);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnGuardar == e.getSource()) {
					String dni = formattedDni.getText().toString();
					String atributo = comboBoxAtributo.getSelectedItem().toString();
					String valor = textFieldValorAtributo.getText();
			 		switch (atributo) {
					case "Nombre":
						
						controllerInterfaz.valorAtributoNuevo(dni,atributo,valor);
						lblMensaje.setText("Paciente actualizado correctamente");
						lblMensaje.setForeground(Color.GREEN);

						break;
					case "Apellidos":
						
						controllerInterfaz.valorAtributoNuevo(dni,atributo,valor);
						lblMensaje.setText("Paciente actualizado correctamente");
						lblMensaje.setForeground(Color.GREEN);

						break;
					case "Fecha_Nacimiento":
						
						if(textFieldValorAtributo.getText().matches("^(0[1-9]|1[0-9]|2[0-9]|3[01])/(0[1-9]|1[012])/((19|20)\\\\d\\\\d)$")) {
							controllerInterfaz.valorAtributoNuevo(dni,atributo,valor);
							lblMensaje.setText("Paciente actualizado correctamente");
							lblMensaje.setForeground(Color.GREEN);

						}else {
							lblMensaje.setText("La fecha debe tener este fomrato 00/00/0000");
							lblMensaje.setForeground(Color.RED);

						}
						break;
					case "Sexo":
						
						controllerInterfaz.valorAtributoNuevo(dni,atributo,valor);
						lblMensaje.setText("Paciente actualizado correctamente");
						lblMensaje.setForeground(Color.GREEN);

						break;
					case "Lugar_Nacimiento":
						
						controllerInterfaz.valorAtributoNuevo(dni,atributo,valor);
						lblMensaje.setText("Paciente actualizado correctamente");
						lblMensaje.setForeground(Color.GREEN);

						break;
					case "Altura":
						
						controllerInterfaz.valorAtributoNuevo(dni,atributo,valor);
						lblMensaje.setText("Paciente actualizado correctamente");
						lblMensaje.setForeground(Color.GREEN);

						break;
					case "Peso":
						
						controllerInterfaz.valorAtributoNuevo(dni,atributo,valor);
						lblMensaje.setText("Paciente actualizado correctamente");
						lblMensaje.setForeground(Color.GREEN);

						break;
					case "Grupo_Sanguineo":
						
						if(textFieldValorAtributo.getText().matches("^(?i)(0|[AB]|AB)[+-]$")) {
							controllerInterfaz.valorAtributoNuevo(dni,atributo,valor);
							lblMensaje.setText("Paciente actualizado correctamente");
							lblMensaje.setForeground(Color.GREEN);

						}else {
							lblMensaje.setText("El grupo sanguineo debe ser 0+/-, A+/-, B+/-, AB+/-");
							lblMensaje.setForeground(Color.RED);

						}
						break;
					case "Enfermedad":
						
						controllerInterfaz.valorAtributoNuevo(dni,atributo,valor);
						lblMensaje.setText("Paciente actualizado correctamente");
						lblMensaje.setForeground(Color.GREEN);

						break;
					case "Tipo":
					
						controllerInterfaz.valorAtributoNuevo(dni,atributo,valor);
						lblMensaje.setText("Paciente actualizado correctamente");
						lblMensaje.setForeground(Color.GREEN);

						break;
					case "Otro":
						String atributoOtro = textFieldNombre.getText();
						controllerInterfaz.valorAtributoNuevo(dni, atributoOtro, valor );
						lblMensaje.setText("Paciente actualizado correctamente");
						lblMensaje.setForeground(Color.GREEN);

						break;

						
					default:
						break;
					}
				}
			}
			
		});
		btnGuardar.setBounds(252, 295, 91, 30);
		contentPane.add(btnGuardar);
		
		textFieldValorAtributo = new JTextField();
		textFieldValorAtributo.setVisible(false);
		textFieldValorAtributo.setBounds(10, 238, 258, 19);
		contentPane.add(textFieldValorAtributo);
		textFieldValorAtributo.setColumns(10);
		
		lblNombre = new JLabel("Introduzca el nombre de atributo\r\n");
		lblNombre.setVisible(false);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombre.setBounds(10, 140, 258, 13);
		contentPane.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setVisible(false);
		textFieldNombre.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
				String atributoOtro = textFieldNombre.getText();

	            if(!atributoOtro.equals("")) {
	                lblValor.setVisible(true);
	                textFieldValorAtributo.setVisible(true);
	            } else {
	                lblValor.setVisible(false);
	                textFieldValorAtributo.setVisible(false);
	            }
	        }
	    });
		textFieldNombre.setBounds(10, 164, 235, 19);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		lblMensaje = new JLabel("");
		lblMensaje.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMensaje.setBounds(40, 350, 342, 30);
		contentPane.add(lblMensaje);
		
		scrollPaneMostrar = new JScrollPane();
		scrollPaneMostrar.setVisible(false);
		scrollPaneMostrar.setBounds(430, 83, 251, 302);
		contentPane.add(scrollPaneMostrar);
		
		textAreaMostrar = new JTextArea();
		textAreaMostrar.setVisible(false);
		scrollPaneMostrar.setViewportView(textAreaMostrar);
		
		
	}
}