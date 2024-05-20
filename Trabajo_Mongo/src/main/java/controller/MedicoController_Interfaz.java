package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.bson.Document;
import com.mongodb.client.result.DeleteResult;
import model.Paciente;
import repositories.MedicoRepository.MedicoRepositoryImpl;

public class MedicoController_Interfaz {
	private final MedicoRepositoryImpl medicoRepositoryImpl = new MedicoRepositoryImpl();

	public Optional<Document> findByDni(String dni) {
		Optional<Document> medico = medicoRepositoryImpl.findById(dni);

		return medico;

	}

	public Boolean updateData(String dni, Document newData) {
    	Optional<Document> paciente = medicoRepositoryImpl.findById(dni);
    	return paciente.map(p -> medicoRepositoryImpl.replaceDocument(paciente, newData)).orElse(false);
    }

	public String findDniPorNombre(String nombre) {
		String medico = medicoRepositoryImpl.findDniPorNombre(nombre);
		return medico;
	}

	public String findNombrePorNombre(String nombre) {
		String medico = medicoRepositoryImpl.findNombrePorNombre(nombre);
		return medico;
	}

	public String findApellidosPorNombre(String nombre) {
		String medico = medicoRepositoryImpl.findApellidosPorNombre(nombre);
		return medico;
	}

	public String findEspecialidadPorNombre(String nombre) {
		String medico = medicoRepositoryImpl.findEspecialidadPorNombre(nombre);
		return medico;
	}

	public String findFechaIncorporacionPorNombre(String nombre) {
		String medico = medicoRepositoryImpl.findFechaIncorporacionPorNombre(nombre);
		return medico;
	}

	public String findDniPorDni(String nombre) {
		String medico = medicoRepositoryImpl.findDniPorDni(nombre);
		return medico;
	}

	public String findNombrePorDni(String nombre) {
		String medico = medicoRepositoryImpl.findNombrePordni(nombre);
		return medico;
	}

	public String findApellidosPorDni(String nombre) {
		String medico = medicoRepositoryImpl.findApellidosPordni(nombre);
		return medico;
	}

	public String findEspecialidadPorDni(String nombre) {
		String medico = medicoRepositoryImpl.findEspecialidadPordni(nombre);
		return medico;
	}

	public String findFechaIncoporacionPorDni(String nombre) {
		String medico = medicoRepositoryImpl.findFechaIncorporacionPordni(nombre);
		return medico;
	}

	public String findDniPorAtributo(String atributo, String valor) {
		String medico = medicoRepositoryImpl.findDniPorAtributo(atributo, valor);
		return medico;
	}

	public String findNombrePorAtributo(String atributo, String valor) {
		String medico = medicoRepositoryImpl.findNombrePorAtributo(atributo, valor);
		return medico;
	}

	public String findApellidosPorAtributo(String atributo, String valor) {
		String medico = medicoRepositoryImpl.findApellidosPorAtributo(atributo, valor);
		return medico;
	}

	public String findEspcialidadPorAtributo(String atributo, String valor) {
		String medico = medicoRepositoryImpl.findEspecialidadPorAtributo(atributo, valor);
		return medico;
	}

	public String findFechaIncorporacionPorAtributo(String atributo, String valor) {
		String medico = medicoRepositoryImpl.findFechaIncorporacionPorAtributo(atributo, valor);
		return medico;
	}

	public String getAllMedicos() {
		List<Document> medicos = medicoRepositoryImpl.findAll();
		if (medicos.isEmpty()) {
			return "No se encontraron pacientes.";
		} else {
			return medicoRepositoryImpl.mostrarMedicos(medicos);
		}
	}

	public Optional<Document> comprobarDni(String dni) {
		Optional<Document> medicos = medicoRepositoryImpl.findById(dni);
		return medicos;

	}

	public String valorAtributoNuevo(String dni, String atributo, String valor) {
		Optional<Document> medicos;

		medicos = medicoRepositoryImpl.findById(dni);
		Boolean actualizado = medicoRepositoryImpl.update(medicos, atributo, valor);

		return actualizado ? "El paciente ha sido actualizado correctamente" : "El paciente no se ha actualizado";
	}

	public Document anadirMedicoNuevo(String dni, String nombre, String apellidos, String especialidad,
			String fecha_incorporacion) {
		Document paciente;
		paciente = new Paciente().append("Dni", dni).append("Nombre", nombre).append("Apellidos", apellidos)
				.append("Especialidad", especialidad).append("Fecha_Incorporacion", fecha_incorporacion);

		return paciente;

	}

	public Boolean salvarMedico(Document medico) {
		return medicoRepositoryImpl.save(medico);
	}

	public String mostrar(List<Document> medicos) {
		String ensenar = medicoRepositoryImpl.mostrarMedicos(medicos);
		return ensenar;
	}

	public String mostrar(Optional<Document> medicos) {
		String ensenar = medicoRepositoryImpl.mostrar(medicos);
		return ensenar;
	}

	public String mostrar(String mensaje) {
		return mensaje;
	}

	public Boolean crearPacientesCargo(Optional<Document> medicos, String[] dni_Paciente) {

		List<String> pacientes_List = Arrays.asList(dni_Paciente);

		Boolean actualizado = medicoRepositoryImpl.updatePacientesCargo(medicos, "Pacientes_Cargo", pacientes_List);
		return actualizado;
	}

	public Boolean actualizarMedico(Optional<Document> medicos, String nombreAtributo, String valorAtributo) {
		return medicoRepositoryImpl.update(medicos, nombreAtributo, valorAtributo);
	}

	public DeleteResult eliminarMedico(String dni) {
		return medicoRepositoryImpl.delete(dni);
	}

	public Boolean anadirLista(Optional<Document> pacientes, String atributo, String[] lista) {

		List<String> list = Arrays.asList(lista); // Convertir array en lista
		return medicoRepositoryImpl.update(pacientes, atributo, list);
	}

	public Boolean anadirComponente(String dni, String atributoComponente, String[] atributo, String[] valores,
			String[] atributoLista, ArrayList<String[]> listas) {
		Optional<Document> paciente = medicoRepositoryImpl.findById(dni);
		Document componente = new Document();
		anadirElementosComponente(atributo, valores, componente);
		anadirListaComponente(atributoLista, listas, componente);
		Boolean anadido = medicoRepositoryImpl.update(paciente, atributoComponente, componente);
		return anadido;
	}

	public Boolean anadirComponente(String dni, String atributoComponente, String[] atributo, String[] valores) {
		Optional<Document> paciente = medicoRepositoryImpl.findById(dni);
		Document componente = new Document();
		anadirElementosComponente(atributo, valores, componente);
		Boolean anadido = medicoRepositoryImpl.update(paciente, atributoComponente, componente);
		return anadido;
	}

	public Boolean anadirComponente(String dni, String atributoComponente, String[] atributoLista,
			ArrayList<String[]> listas) {
		Optional<Document> paciente = medicoRepositoryImpl.findById(dni);
		Document componente = new Document();
		anadirListaComponente(atributoLista, listas, componente);
		Boolean anadido = medicoRepositoryImpl.update(paciente, atributoComponente, componente);
		return anadido;
	}

	private void anadirElementosComponente(String[] atributo, String[] valores, Document paciente) {
		for (int i = 0; i < atributo.length; i++) {
			paciente.append(atributo[i], valores[i]);
		}
	}

	private void anadirListaComponente(String[] atributoLista, ArrayList<String[]> listas, Document paciente) {
		for (int i = 0; i < listas.size(); i++) {
			String[] listaActual = listas.get(i); // Obtener la lista actual del índice i
			for (int j = 0; j < listaActual.length; j++) {
				paciente.append(atributoLista[j], listaActual[j]); // Agregar el atributo y su valor correspondiente al
																	// documento
			}
		}
	}

}
