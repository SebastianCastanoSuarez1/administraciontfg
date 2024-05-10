package view.paciente;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Optional;

import javax.swing.JButton;
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



public class VentanaAnadirAtributoNuevo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNombreAtributo,textFieldValorAtributo;
	private JLabel lblAtributoANadir, lblValor, lblDNI, lblMensaje;
	private JButton btnCancelar, btnAceptar, btnComprobar;
	private final Controller_Interfaz controllerInterfaz = new Controller_Interfaz();
	private JFormattedTextField formattedDni;
	private MaskFormatter mascara;
	private VentanaOpcionAnadirPaciente voa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAnadirAtributoNuevo frame = new VentanaAnadirAtributoNuevo();
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
	public VentanaAnadirAtributoNuevo() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 518, 377);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblAtributoANadir = new JLabel("Introduzca el nombre del nuevo atributo");
		lblAtributoANadir.setVisible(false);
		lblAtributoANadir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAtributoANadir.setBounds(10, 96, 279, 41);
		contentPane.add(lblAtributoANadir);
		
		textFieldNombreAtributo = new JTextField();
		textFieldNombreAtributo.setVisible(false);
		textFieldNombreAtributo.setColumns(10);
		textFieldNombreAtributo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String atributo = textFieldNombreAtributo.getText();
				if(!atributo.equals("")) {
					lblValor.setVisible(true);
					textFieldValorAtributo.setVisible(true);
				}else {
					lblValor.setVisible(false);
					textFieldValorAtributo.setVisible(false);
				}
			}
		});
		textFieldNombreAtributo.setBounds(271, 103, 206, 32);
		contentPane.add(textFieldNombreAtributo);
		
		lblValor = new JLabel("Introduzca el valor del nuevo atributo");
		lblValor.setVisible(false);
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblValor.setBounds(10, 163, 243, 32);
		contentPane.add(lblValor);
		
		textFieldValorAtributo = new JTextField();
		textFieldValorAtributo.setVisible(false);
		textFieldValorAtributo.setColumns(10);
		textFieldValorAtributo.setBounds(271, 166, 206, 32);
		contentPane.add(textFieldValorAtributo);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnCancelar == e.getSource()) {
					voa = new VentanaOpcionAnadirPaciente();
					voa.setVisible(true);
					dispose();
				}
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCancelar.setBounds(91, 247, 92, 27);
		contentPane.add(btnCancelar);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnAceptar == e.getSource()) {
					Optional<Document> pacientes = controllerInterfaz.findByDni(formattedDni.getText().toString()) ;
					if(pacientes.isPresent()) {
						Boolean anadido =controllerInterfaz.actualizarPaciente(pacientes,textFieldNombreAtributo.getText(), textFieldValorAtributo.getText());
						if(anadido == true) {
							lblMensaje.setText("El paciente ha sido actualizado con exito");
							lblMensaje.setForeground(Color.GREEN);
						}else {
							lblMensaje.setText("El paciente no ha sido actualizado con exito");
							lblMensaje.setForeground(Color.RED);
						}

					}
				}
			}
		});
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAceptar.setBounds(287, 247, 92, 27);
		contentPane.add(btnAceptar);
		
		lblDNI = new JLabel("DNI");
		lblDNI.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDNI.setBounds(73, 25, 55, 40);
		contentPane.add(lblDNI);
		
		btnComprobar = new JButton("Comprobar");
		btnComprobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnComprobar == e.getSource()) {
					Optional<Document> pacientes = controllerInterfaz.findByDni(formattedDni.getText());
					
					if(pacientes.isPresent()) {
						lblAtributoANadir.setVisible(true);
						textFieldNombreAtributo.setVisible(true);
					}else{
						String mensaje = "El paciente con DNI " + formattedDni.getText() + " no existe "; 
						JOptionPane.showMessageDialog(null, mensaje, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnComprobar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnComprobar.setBounds(330, 34, 98, 27);
		contentPane.add(btnComprobar);
		
		try {
            mascara = new MaskFormatter("########?");
            mascara.setValidCharacters("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            formattedDni = new JFormattedTextField(mascara);
    		formattedDni.setBounds(153, 36, 148, 26);
    		contentPane.add(formattedDni);
    		
    		lblMensaje = new JLabel("");
    		lblMensaje.setFont(new Font("Tahoma", Font.PLAIN, 12));
    		lblMensaje.setBounds(39, 298, 428, 32);
    		contentPane.add(lblMensaje);
    		
    		
        } catch (ParseException e) {
            e.printStackTrace();
        }
		
	}
}
