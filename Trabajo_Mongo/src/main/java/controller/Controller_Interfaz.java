package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.Document;

import com.mongodb.client.result.DeleteResult;

import model.Paciente;
import repositories.PacientesRepositories.PacienteRepositoryImpl;
import view.paciente.PacienteView;

public class Controller_Interfaz {
	private final PacienteRepositoryImpl pacienteRepositoryImpl = new PacienteRepositoryImpl();
	private final PacienteView pacienteView = new PacienteView();

	public Optional<Document> findByDni(String dni) {
		Optional<Document> paciente = pacienteRepositoryImpl.findById(dni);

		return paciente;
	}

	public String getAllPacientes() {
		List<Document> pacientes = pacienteRepositoryImpl.findAll();
		if (pacientes.isEmpty()) {
			return "No se encontraron pacientes.";
		} else {
			return pacienteView.mostrarPacientes(pacientes);
		}

	}
	
	public String[] alergenos(String dni) {
		String[] dniPacientes = pacienteRepositoryImpl.guardarAlergenos(dni);
		return dniPacientes;
	}
	
	public Boolean eliminarAlergenos(Optional<Document> medicos, String valor) {
		Boolean actualizado = pacienteRepositoryImpl.eliminarAlergenos(medicos, "Alergenos", valor);
		return actualizado;
	}
	
	public String[] medicamentos(String dni) {
		String[] dniPacientes = pacienteRepositoryImpl.guardarMedicamentos(dni);
		return dniPacientes;
	}
	
	public Boolean eliminarMedicamentos(Optional<Document> medicos, String valor) {
		Boolean actualizado = pacienteRepositoryImpl.eliminarAlergenos(medicos, "Medicamentos", valor);
		return actualizado;
	}
	public Boolean updateData(String dni, Document newData) {
    	Optional<Document> paciente = pacienteRepositoryImpl.findById(dni);
    	return paciente.map(p -> pacienteRepositoryImpl.replaceDocument(paciente, newData)).orElse(false);
    }


	public String findDniPorNombre(String nombre) {
		String medico = pacienteRepositoryImpl.findDniPorNombre(nombre);
		return medico;
	}

	public String findNombrePorNombre(String nombre) {
		String medico = pacienteRepositoryImpl.findNombrePorNombre(nombre);
		return medico;
	}

	public String findApellidosPorNombre(String nombre) {
		String medico = pacienteRepositoryImpl.findApellidosPorNombre(nombre);
		return medico;
	}

	public String findFechaNacimientoPorNombre(String nombre) {
		String medico = pacienteRepositoryImpl.findFechaNacimientoPorNombre(nombre);
		return medico;
	}

	public String findSexoPorNombre(String nombre) {
		String medico = pacienteRepositoryImpl.findSexoPorNombre(nombre);
		return medico;
	}

	public String findLugarNacimientoPorNombre(String nombre) {
		String medico = pacienteRepositoryImpl.findLugarNacimientoPorNombre(nombre);
		return medico;
	}

	public Integer findAlturaPorNombre(String nombre) {
		Integer medico = pacienteRepositoryImpl.findAlturaPorNombre(nombre);
		return medico;
	}

	public Integer findPesoPorNombre(String nombre) {
		Integer medico = pacienteRepositoryImpl.findPesoPorNombre(nombre);
		return medico;
	}

	public String findGrupoSanguineoPorNombre(String nombre) {
		String medico = pacienteRepositoryImpl.findGrupoSanguineoPorNombre(nombre);
		return medico;
	}
	public String findDniPorDni(String nombre) {
		String medico = pacienteRepositoryImpl.findDniPorDni(nombre);
		return medico;
	}

	public String findNombrePorDni(String nombre) {
		String medico = pacienteRepositoryImpl.findNombrePordni(nombre);
		return medico;
	}

	public String findApellidosPorDni(String nombre) {
		String medico = pacienteRepositoryImpl.findApellidosPordni(nombre);
		return medico;
	}

	public String findFechaNacimientoPorDni(String nombre) {
		String medico = pacienteRepositoryImpl.findFechaNacimientoPordni(nombre);
		return medico;
	}

	public String findSexoPorDni(String nombre) {
		String medico = pacienteRepositoryImpl.findSexoPordni(nombre);
		return medico;
	}

	public String findLugarNacimientoPorDni(String nombre) {
		String medico = pacienteRepositoryImpl.findLugarNacimientoPordni(nombre);
		return medico;
	}

	public Integer findAlturaPorDni(String nombre) {
		Integer medico = pacienteRepositoryImpl.findAlturaPordni(nombre);
		return medico;
	}

	public Integer findPesoPorDni(String nombre) {
		Integer medico = pacienteRepositoryImpl.findPesoPordni(nombre);
		return medico;
	}

	public String findGrupoSanguineoPorDni(String nombre) {
		String medico = pacienteRepositoryImpl.findGrupoSanguineoPordni(nombre);
		return medico;
	}
	public String findDniPorAtributo(String atributo, String valor) {
		String medico = pacienteRepositoryImpl.findDniPorAtributo(atributo,valor);
		return medico;
	}

	public String findNombrePorAtributo(String atributo, String valor) {
		String medico = pacienteRepositoryImpl.findNombrePorAtributo(atributo,valor);
		return medico;
	}

	public String findApellidosPorAtributo(String atributo, String valor) {
		String medico = pacienteRepositoryImpl.findApellidosPorAtributo(atributo, valor);
		return medico;
	}

	public String findFechaNacimientoPorAtributo(String atributo, String valor) {
		String medico = pacienteRepositoryImpl.findFechaNacimientoPorAtributo(atributo,valor);
		return medico;
	}

	public String findSexoPorAtributo(String atributo, String valor) {
		String medico = pacienteRepositoryImpl.findSexoPorAtributo(atributo,valor);
		return medico;
	}

	public String findLugarNacimientoPorAtributo(String atributo, String valor) {
		String medico = pacienteRepositoryImpl.findLugarNacimientoPorAtributo(atributo,valor);
		return medico;
	}

	public Integer findAlturaPorAtributo(String atributo, String valor) {
		Integer medico = pacienteRepositoryImpl.findAlturaPorAtributo(atributo,valor);
		return medico;
	}

	public Integer findPesoPorAtributo(String atributo, String valor) {
		Integer medico = pacienteRepositoryImpl.findPesoPorAtributo(atributo,valor);
		return medico;
	}

	public String findGrupoSanguineoPorAtributo(String atributo, String valor) {
		String medico = pacienteRepositoryImpl.findGrupoSanguineoPorAtributo(atributo,valor);
		return medico;
	}
	
	public Optional<Document> comprobarDni(String dni) {

		Optional<Document> pacientes = pacienteRepositoryImpl.findById(dni);
		return pacientes;
	}

	public String anadirDniMedico(String[] dniPacientes, String dni) {
		Boolean actualizado = false;
		for (int i = 0; i < dniPacientes.length; i++) {
			Optional<Document> paciente = pacienteRepositoryImpl.findById(dniPacientes[i]);
			if (paciente.isPresent()) {
				actualizado = pacienteRepositoryImpl.updateDniMedico(paciente, "Dni_Medico", dni);
				if (!actualizado) {
					return "El paciente con DNI " + dniPacientes[i] + " no se ha actualizado";
				}
			} else {
				return "El paciente con DNI " + dniPacientes[i] + " no se ha encontrado";
			}
		}
		return "Todos los pacientes han sido actualizados correctamente";
	}

	public String anadirNombreMedico(String[] dniPacientes, String dni) {
		Boolean actualizado = false;
		for (int i = 0; i < dniPacientes.length; i++) {
			Optional<Document> paciente = pacienteRepositoryImpl.findById(dniPacientes[i]);
			if (paciente.isPresent()) {
				actualizado = pacienteRepositoryImpl.updateNombreMedico(paciente, "Nombre_Medico", dni);
				if (!actualizado) {
					return "El paciente con DNI " + dniPacientes[i] + " no se ha actualizado";
				}
			} else {
				return "El paciente con DNI " + dniPacientes[i] + " no se ha encontrado";
			}
		}
		return "Todos los pacientes han sido actualizados correctamente";
	}
	public String anadirApellidosMedico(String[] dniPacientes, String dni) {
		Boolean actualizado = false;
		for (int i = 0; i < dniPacientes.length; i++) {
			Optional<Document> paciente = pacienteRepositoryImpl.findById(dniPacientes[i]);
			if (paciente.isPresent()) {
				actualizado = pacienteRepositoryImpl.updateApellidosMedico(paciente, "Apellidos_Medico", dni);
				if (!actualizado) {
					return "El paciente con DNI " + dniPacientes[i] + " no se ha actualizado";
				}
			} else {
				return "El paciente con DNI " + dniPacientes[i] + " no se ha encontrado";
			}
		}
		return "Todos los pacientes han sido actualizados correctamente";
	}
	public String anadirEspecialidadMedico(String[] dniPacientes, String dni) {
		Boolean actualizado = false;
		for (int i = 0; i < dniPacientes.length; i++) {
			Optional<Document> paciente = pacienteRepositoryImpl.findById(dniPacientes[i]);
			if (paciente.isPresent()) {
				actualizado = pacienteRepositoryImpl.updateEspecialidadMedico(paciente, "Especialidad_Medico", dni);
				if (!actualizado) {
					return "El paciente con DNI " + dniPacientes[i] + " no se ha actualizado";
				}
			} else {
				return "El paciente con DNI " + dniPacientes[i] + " no se ha encontrado";
			}
		}
		return "Todos los pacientes han sido actualizados correctamente";
	}
	public String valorAtributoNuevo(String dni, String atributo, String valor) {
		Optional<Document> pacientes;

		pacientes = pacienteRepositoryImpl.findById(dni);
		Boolean actualizado = pacienteRepositoryImpl.update(pacientes, atributo, valor);
		return actualizado ? "El paciente ha sido actualizado correctamente" : "El paciente no se ha actualizado";
	}

	public Document anadirPacientenuevo(String dni, String nombre, String apellidos, String fechaNacimiento,
			String sexo, String lugarNacimiento, Integer altura, Integer peso, String grupoSanguineo) {
		Document paciente;
		paciente = new Paciente().append("Dni", dni).append("Nombre", nombre).append("Apellidos", apellidos)
				.append("Fecha_Nacimiento", fechaNacimiento).append("Sexo", sexo)
				.append("Lugar_Nacimiento", lugarNacimiento).append("Altura", altura).append("Peso", peso)
				.append("Grupo_Sanguineo", grupoSanguineo);

		return paciente;
	}

	public Boolean salvarPaciente(Document paciente) {
		return pacienteRepositoryImpl.save(paciente);
	}


	public String mostrar(Optional<Document> pacientes) {
		String ensenar = pacienteView.mostrar(pacientes);
		return ensenar;
	}

	public String mostrar(String mensaje) {
		return mensaje;
	}

	public Boolean anadirVariables(Optional<Document> pacientes,
			Document enfermedades) {
		Document contenido = new Document();
		contenido.append("Enfermedades", enfermedades);
		return pacienteRepositoryImpl.updateHistorialMedico(pacientes, contenido);
	}

	public Boolean anadirAlergenos(Optional<Document> pacientes, String[] alergenos) {
		List<String> list = Arrays.asList(alergenos);
		Boolean anadido = pacienteRepositoryImpl.updateAlergenos(pacientes, "Alergenos", list);
		return anadido;
		
	}
	public Boolean anadirMedicamentos(Optional<Document> pacientes, String[] medicamentos) {
		List<String> list = Arrays.asList(medicamentos);
		Boolean anadido = pacienteRepositoryImpl.updateMedicamentos(pacientes, "Medicamentos", list);
		return anadido;
		
	}
	public Document crearDocumentoEnfermedades(Optional<Document> pacientes, String enfermedad, String fecha,
			String[] historialMedicoMedicamentos, String tratamiento, String informe) {
		Document enfermedades = new Document();
		Document detalles = new Document();

		enfermedades.append("Enfermedad", enfermedad);
		enfermedades.append("Fecha", fecha);

		List<String> medicamentosList = Arrays.asList(historialMedicoMedicamentos);

		detalles.append("Tratamiento", tratamiento);
		detalles.append("Medicamentos", medicamentosList);
		detalles.append("Informe", informe);
		enfermedades.append("Detalles", detalles);
		return enfermedades;
	}

	public Boolean actualizarPaciente(Optional<Document> pacientes, String nombreAtributo, String valorAtributo) {
		return pacienteRepositoryImpl.update(pacientes, nombreAtributo, valorAtributo);
	}

	public DeleteResult eliminarPaciente(String dni) {
		return pacienteRepositoryImpl.delete(dni);
	}

	public Boolean anadirLista(Optional<Document> pacientes, String atributo, String[] lista) {
		List<String> alergenosList = Arrays.asList(lista); // Convertir array en lista
		return pacienteRepositoryImpl.update(pacientes, atributo, alergenosList);
	}

	public Boolean anadirComponente(String dni, String atributoComponente, String[] atributo, String[] valores,
			String[] atributoLista, ArrayList<String[]> listas) {
		Optional<Document> paciente = pacienteRepositoryImpl.findById(dni);
		Document componente = new Document();
		anadirElementosComponente(atributo, valores, componente);
		anadirListaComponente(atributoLista, listas, componente);
		Boolean anadido = pacienteRepositoryImpl.update(paciente, atributoComponente, componente);
		return anadido;
	}

	public Boolean anadirComponente(String dni, String atributoComponente, String[] atributo, String[] valores) {
		Optional<Document> paciente = pacienteRepositoryImpl.findById(dni);
		Document componente = new Document();
		anadirElementosComponente(atributo, valores, componente);
		Boolean anadido = pacienteRepositoryImpl.update(paciente, atributoComponente, componente);
		return anadido;
	}

	public Boolean anadirComponente(String dni, String atributoComponente, String[] atributoLista,
			ArrayList<String[]> listas) {
		Optional<Document> paciente = pacienteRepositoryImpl.findById(dni);
		Document componente = new Document();
		anadirListaComponente(atributoLista, listas, componente);
		Boolean anadido = pacienteRepositoryImpl.update(paciente, atributoComponente, componente);
		return anadido;
	}

	private void anadirElementosComponente(String[] atributo, String[] valores, Document paciente) {
		for (int i = 0; i < atributo.length; i++) {
			paciente.append(atributo[i], valores[i]);
		}
	}

	private void anadirListaComponente(String[] atributoLista, ArrayList<String[]> listas, Document paciente) {
		for (int i = 0; i < listas.size(); i++) { // Corregir el límite del bucle
			String[] listaActual = listas.get(i); // Obtener la lista actual del índice i
			for (int j = 0; j < atributoLista.length && j < listaActual.length; j++) {
				paciente.append(atributoLista[j], Arrays.asList(listaActual));
			}
		}
	}

}
