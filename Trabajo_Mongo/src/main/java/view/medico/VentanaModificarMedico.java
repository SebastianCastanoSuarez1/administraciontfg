package view.medico;

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
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import org.bson.Document;

import controller.MedicoController_Interfaz;

public class VentanaModificarMedico extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JFormattedTextField formattedDni;
    private JFormattedTextField formattedFechaIncorporacion;
    private JButton btnComprobar;
    private final MedicoController_Interfaz controllerMedico = new MedicoController_Interfaz();
    private MaskFormatter mascara;
    private JLabel lblDNI;
    private VentanaPrincipalMedico vm;
    private JTextField textFieldNombre;
    private JTextField textFieldApellidos;
    private JTextField textFieldEspecialida;
    private JButton saveButton;
    private JButton btnCancelar;
    private JLabel lblModificarMedico;


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

    public VentanaModificarMedico() {
   	 setResizable(false);
   	 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   	 setBounds(100, 100, 631, 424);
   	 contentPane = new JPanel();
   	 contentPane.setBackground(new Color(230, 230, 250));
   	 contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

   	 setContentPane(contentPane);
   	 contentPane.setLayout(null);

   	 lblDNI = new JLabel("DNI");
   	 lblDNI.setFont(new Font("Tahoma", Font.BOLD, 17));
   	 lblDNI.setBounds(145, 82, 55, 40);
   	 contentPane.add(lblDNI);

   	 try {
   		 mascara = new MaskFormatter("########?");
   		 mascara.setValidCharacters("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
   		 formattedDni = new JFormattedTextField(mascara);
   		 formattedDni.setBounds(210, 93, 148, 26);
   		 contentPane.add(formattedDni);
   	 } catch (ParseException e) {
   		 e.printStackTrace();
   	 }

   	 btnComprobar = new JButton("Comprobar");
   	 btnComprobar.addActionListener(new ActionListener() {
   		 public void actionPerformed(ActionEvent e) {
   			 if (btnComprobar == e.getSource()) {
   				 String dni = formattedDni.getText();
   				 Optional<Document> pacientes = controllerMedico.comprobarDni(dni);

   				 if (pacientes.isPresent()) {
   					 formattedDni.setEnabled(false);
   					 habilitarCampos(true);
   					 cargarDatosDocumento(dni);
   					 saveButton.setEnabled(true);
   				 } else {
   					 String mensaje = "El paciente con DNI " + formattedDni.getText() + " no existe ";
   					 JOptionPane.showMessageDialog(null, mensaje, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
   				 }
   			 }
   		 }
   	 });
   	 btnComprobar.setFont(new Font("Tahoma", Font.PLAIN, 13));
   	 btnComprobar.setBounds(380, 91, 98, 27);
   	 contentPane.add(btnComprobar);

   	 textFieldNombre = new JTextField();
   	 textFieldNombre.setEnabled(false);
   	 textFieldNombre.setBounds(142, 169, 150, 20);
   	 contentPane.add(textFieldNombre);

   	 JLabel lblNombre = new JLabel("Nombre:");
   	 lblNombre.setBounds(10, 169, 70, 20);
   	 contentPane.add(lblNombre);

   	 textFieldApellidos = new JTextField();
   	 textFieldApellidos.setEnabled(false);
   	 textFieldApellidos.setBounds(446, 170, 150, 20);
   	 contentPane.add(textFieldApellidos);

   	 JLabel lblApellidos = new JLabel("Apellidos:");
   	 lblApellidos.setBounds(336, 169, 70, 20);
   	 contentPane.add(lblApellidos);

   	 try {
   		 MaskFormatter dobMask = new MaskFormatter("##/##/####");
   		 dobMask.setValidCharacters("0123456789");
   		 formattedFechaIncorporacion = new JFormattedTextField(dobMask);
   		 formattedFechaIncorporacion.setBounds(142, 223, 150, 20);
   		 formattedFechaIncorporacion.setEnabled(false);
   		 contentPane.add(formattedFechaIncorporacion);
   	 } catch (ParseException e) {
   		 e.printStackTrace();
   	 }

   	 JLabel lblFechaNacimiento = new JLabel("Fecha Incorporacion:");
   	 lblFechaNacimiento.setBounds(10, 223, 129, 20);
   	 contentPane.add(lblFechaNacimiento);

   	 textFieldEspecialida = new JTextField();
   	 textFieldEspecialida.setEnabled(false);
   	 textFieldEspecialida.setColumns(10);
   	 textFieldEspecialida.setBounds(446, 223, 150, 20);
   	 contentPane.add(textFieldEspecialida);

   	 JLabel alturaLbl = new JLabel("Especialidad:");
   	 alturaLbl.setBounds(336, 224, 83, 16);
   	 contentPane.add(alturaLbl);

   	 saveButton = new JButton("Aceptar");
   	 saveButton.setEnabled(false);
   	 saveButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
   	 saveButton.setBorderPainted(false);
   	 saveButton.setBackground(new Color(240, 240, 240));
   	 saveButton.setBounds(334, 307, 122, 36);
   	 contentPane.add(saveButton);
   	 
   	 btnCancelar = new JButton("Cancelar");
   	 btnCancelar.addActionListener(new ActionListener() {
   	 	public void actionPerformed(ActionEvent e) {
   	 		vm = new VentanaPrincipalMedico();
   	 		vm.setVisible(true);
   	 		dispose();
   	 	}
   	 });
   	 btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
   	 btnCancelar.setBorderPainted(false);
   	 btnCancelar.setBackground(UIManager.getColor("Button.background"));
   	 btnCancelar.setBounds(145, 308, 122, 35);
   	 contentPane.add(btnCancelar);
   	 
   	 lblModificarMedico = new JLabel("Modificar medico");
   	 lblModificarMedico.setFont(new Font("Tahoma", Font.BOLD, 15));
   	 lblModificarMedico.setBounds(225, 26, 168, 21);
   	 contentPane.add(lblModificarMedico);

   	 saveButton.addActionListener(new ActionListener() {
   		 public void actionPerformed(ActionEvent e) {
   			 if (e.getSource() == saveButton) {
   				 actualizarPaciente();
   			 }
   		 }
   	 });
    }

    private void habilitarCampos(boolean habilitar) {
   	 textFieldNombre.setEnabled(habilitar);
   	 textFieldApellidos.setEnabled(habilitar);
   	 formattedFechaIncorporacion.setEnabled(habilitar);
   	 textFieldEspecialida.setEnabled(habilitar);
   
   	 JLabel[] labels = { new JLabel("Nombre:"), new JLabel("Apellidos:"), new JLabel("Fecha Incorporacion:"),
   			 new JLabel("Especialidad:")};

   	 for (JLabel label : labels) {
   		 label.setEnabled(habilitar);
   	 }
    }

    private void cargarDatosDocumento(String dni) {
   	 Optional<Document> documento = controllerMedico.findByDni(dni);
   	 documento.ifPresent(usuarioDatos -> {
   		 textFieldNombre.setText(usuarioDatos.getString("Nombre"));
   		 textFieldApellidos.setText(usuarioDatos.getString("Apellidos"));
   		 formattedFechaIncorporacion.setText(usuarioDatos.getString("Fecha_Incorporacion"));
   		 textFieldEspecialida.setText(usuarioDatos.getString("Especialidad"));
   	 });
    }

    private void actualizarPaciente() {
   	 String dni = formattedDni.getText();
   	 String nombre = textFieldNombre.getText();
   	 String apellidos = textFieldApellidos.getText();
   	 String fechaIncorporacion = formattedFechaIncorporacion.getText();
   	 String especialidad = textFieldEspecialida.getText();

  
   	 Document paciente = new Document("Dni", dni).append("Nombre", nombre).append("Apellidos", apellidos)
   			 .append("Fecha_Incorporacion", fechaIncorporacion).append("Especialidad", especialidad);

   	 boolean actualizado = controllerMedico.updateData(dni, paciente);

   	 if (actualizado) {
   		 JOptionPane.showMessageDialog(this, "Datos del paciente actualizados correctamente.",
   				 "Actualización exitosa", JOptionPane.INFORMATION_MESSAGE);

   	 } else {
   		 JOptionPane.showMessageDialog(this, "Hubo un problema al actualizar los datos del paciente.",
   				 "Error de actualización", JOptionPane.ERROR_MESSAGE);
   	 }
    }
}
