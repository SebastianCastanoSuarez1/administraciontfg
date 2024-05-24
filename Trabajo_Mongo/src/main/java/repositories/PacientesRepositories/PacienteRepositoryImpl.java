package repositories.PacientesRepositories;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;

import db.MongoDB;

public class PacienteRepositoryImpl implements PacienteRepository {

	MongoClient mongoClient = MongoDB.getClient();
	MongoDatabase database = mongoClient.getDatabase("TrabajoMongo");
	MongoCollection<Document> collection = database.getCollection("Pacientes");
	String dni = "Dni", nombre = "Nombre", apellidos = "Apellidos", fechaNacimiento = "Fecha_Nacimiento", sexo = "Sexo",
			lugarNacimiento = "Lugar_Nacimiento", altura = "Altura", peso = "Peso", grupo_Sanguineo = "Grupo_Sanguineo",
			enfermedad = "Enfermedad", tipo = "Tipo";

	@Override
	public List<Document> findAll() {
		Bson projectionFields = Projections.fields(Projections.include(dni, nombre, enfermedad, tipo),
				Projections.excludeId());

		MongoCursor<Document> cursor = collection.find().projection(projectionFields).iterator();

		List<Document> documentList = new ArrayList<>();
		try {
			while (cursor.hasNext()) {
				Document document = cursor.next();
				documentList.add(document);
			}
		} finally {
			cursor.close();
		}

		return documentList;
	}

	@SuppressWarnings("unchecked")
	public String[] guardarAlergenos(String paciente) {
		Bson filter = eq(dni, paciente);
		Document document = collection.find(filter).first();
		List<String> medicamentos = (List<String>) document.get("Alergenos");
		return medicamentos.toArray(new String[0]);
	}

	public Boolean eliminarAlergenos(Optional<Document> paciente, String atributo, String valor) {
		try {
			if (paciente.isPresent()) {
				Document filter = paciente.get();
				collection.updateOne(eq("Dni", filter.getString("Dni")), Updates.pull(atributo, valor));
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public String[] guardarMedicamentos(String paciente) {
		Bson filter = eq(dni, paciente);
		Document document = collection.find(filter).first();
		List<String> medicamentos = (List<String>) document.get("Medicamentos");
		return medicamentos.toArray(new String[0]);
	}

	public Boolean eliminarMedicamentos(Optional<Document> paciente, String atributo, String valor) {
		try {
			if (paciente.isPresent()) {
				Document filter = paciente.get();
				collection.updateOne(eq("Dni", filter.getString("Dni")), Updates.pull(atributo, valor));
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Boolean eliminarDniMedico(String dniPaciente, String atributo) {
		try {
			Document filter = new Document("Dni", dniPaciente);

			collection.updateOne(filter, Updates.unset(atributo));

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public Boolean eliminarNombreMedico(String dniPaciente, String atributo) {
		try {
			Document filter = new Document("Dni", dniPaciente);

			collection.updateOne(filter, Updates.unset(atributo));

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public Boolean eliminarApellidosMedico(String dniPaciente, String atributo) {
		try {
			Document filter = new Document("Dni", dniPaciente);

			collection.updateOne(filter, Updates.unset(atributo));

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public Boolean eliminarEspecialidadMedico(String dniPaciente, String atributo) {
		try {
			Document filter = new Document("Dni", dniPaciente);

			collection.updateOne(filter, Updates.unset(atributo));

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public Boolean replaceDocument(Optional<Document> optionalOldDocument, Document newDocument) {
		try {
			if (optionalOldDocument.isPresent()) {
				Document oldDocument = optionalOldDocument.get();

				for (String key : newDocument.keySet()) {
					Object newValue = newDocument.get(key);
					if (oldDocument.containsKey(key)) {
						oldDocument.put(key, newValue);
					}
				}
				collection.replaceOne(eq("Dni", oldDocument.getString("Dni")), oldDocument);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean save(Document entity) {
		try {
			collection.insertOne(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public String findDniPorNombre(String paciente) {
		Bson filter = eq(nombre, paciente);
		Document result = collection.find(filter).first();
		Object dniList = result.get(dni);
		return (String) dniList;

	}

	public String findNombrePorNombre(String paciente) {
		Bson filter = eq(nombre, paciente);
		Document result = collection.find(filter).first();
		Object dniList = result.get(nombre);
		return (String) dniList;

	}

	public String findApellidosPorNombre(String paciente) {
		Bson filter = eq(nombre, paciente);
		Document result = collection.find(filter).first();
		Object dniList = result.get(apellidos);
		return (String) dniList;

	}

	public String findFechaNacimientoPorNombre(String paciente) {
		Bson filter = eq(nombre, paciente);
		Document result = collection.find(filter).first();
		Object dniList = result.get(fechaNacimiento);
		return (String) dniList;

	}

	public String findSexoPorNombre(String paciente) {
		Bson filter = eq(nombre, paciente);
		Document result = collection.find(filter).first();
		Object dniList = result.get(sexo);
		return (String) dniList;

	}

	public String findLugarNacimientoPorNombre(String paciente) {
		Bson filter = eq(nombre, paciente);
		Document result = collection.find(filter).first();
		Object dniList = result.get(lugarNacimiento);
		return (String) dniList;

	}

	public Integer findAlturaPorNombre(String paciente) {
		Bson filter = eq(nombre, paciente);
		Document result = collection.find(filter).first();
		Object dniList = result.get(altura);
		return (Integer) dniList;

	}

	public Integer findPesoPorNombre(String paciente) {
		Bson filter = eq(nombre, paciente);
		Document result = collection.find(filter).first();
		Object dniList = result.get(peso);
		return (Integer) dniList;

	}

	public String findGrupoSanguineoPorNombre(String paciente) {
		Bson filter = eq(nombre, paciente);
		Document result = collection.find(filter).first();
		Object dniList = result.get(grupo_Sanguineo);
		return (String) dniList;

	}

	public String findDniPorDni(String paciente) {
		Bson filter = eq(dni, paciente);
		Document result = collection.find(filter).first();
		Object dniList = result.get(dni);
		return (String) dniList;

	}

	public String findNombrePordni(String paciente) {
		Bson filter = eq(dni, paciente);
		Document result = collection.find(filter).first();
		Object dniList = result.get(nombre);
		return (String) dniList;

	}

	public String findApellidosPordni(String paciente) {
		Bson filter = eq(dni, paciente);
		Document result = collection.find(filter).first();
		Object dniList = result.get(apellidos);
		return (String) dniList;

	}

	public String findFechaNacimientoPordni(String paciente) {
		Bson filter = eq(dni, paciente);
		Document result = collection.find(filter).first();
		Object dniList = result.get(fechaNacimiento);
		return (String) dniList;

	}

	public String findSexoPordni(String paciente) {
		Bson filter = eq(dni, paciente);
		Document result = collection.find(filter).first();
		Object dniList = result.get(sexo);
		return (String) dniList;

	}

	public String findLugarNacimientoPordni(String paciente) {
		Bson filter = eq(dni, paciente);
		Document result = collection.find(filter).first();
		Object dniList = result.get(lugarNacimiento);
		return (String) dniList;

	}

	public Integer findAlturaPordni(String paciente) {
		Bson filter = eq(dni, paciente);
		Document result = collection.find(filter).first();
		Object dniList = result.get(altura);
		return (Integer) dniList;

	}

	public Integer findPesoPordni(String paciente) {
		Bson filter = eq(dni, paciente);
		Document result = collection.find(filter).first();
		Object dniList = result.get(peso);
		return (Integer) dniList;

	}

	public String findGrupoSanguineoPordni(String paciente) {
		Bson filter = eq(dni, paciente);
		Document result = collection.find(filter).first();
		Object dniList = result.get(grupo_Sanguineo);
		return (String) dniList;

	}

	public String findDniPorAtributo(String atributo, String valor) {
		Bson filter = eq(atributo, valor);
		Document result = collection.find(filter).first();
		Object dniList = result.get(dni);
		return (String) dniList;

	}

	public String findNombrePorAtributo(String atributo, String valor) {
		Bson filter = eq(atributo, valor);
		Document result = collection.find(filter).first();
		Object dniList = result.get(nombre);
		return (String) dniList;

	}

	public String findApellidosPorAtributo(String atributo, String valor) {
		Bson filter = eq(atributo, valor);
		Document result = collection.find(filter).first();
		Object dniList = result.get(apellidos);
		return (String) dniList;

	}

	public String findFechaNacimientoPorAtributo(String atributo, String valor) {
		Bson filter = eq(atributo, valor);

		Document result = collection.find(filter).first();
		Object dniList = result.get(fechaNacimiento);
		return (String) dniList;

	}

	public String findSexoPorAtributo(String atributo, String valor) {
		Bson filter = eq(atributo, valor);

		Document result = collection.find(filter).first();
		Object dniList = result.get(sexo);
		return (String) dniList;

	}

	public String findLugarNacimientoPorAtributo(String atributo, String valor) {
		Bson filter = eq(atributo, valor);

		Document result = collection.find(filter).first();
		Object dniList = result.get(lugarNacimiento);
		return (String) dniList;

	}

	public Integer findAlturaPorAtributo(String atributo, String valor) {
		Bson filter = eq(atributo, valor);

		Document result = collection.find(filter).first();
		Object dniList = result.get(altura);
		return (Integer) dniList;

	}

	public Integer findPesoPorAtributo(String atributo, String valor) {
		Bson filter = eq(atributo, valor);

		Document result = collection.find(filter).first();
		Object dniList = result.get(peso);
		return (Integer) dniList;

	}

	public String findGrupoSanguineoPorAtributo(String atributo, String valor) {
		Bson filter = eq(atributo, valor);

		Document result = collection.find(filter).first();
		Object dniList = result.get(grupo_Sanguineo);
		return (String) dniList;

	}

	@Override
	public Optional<Document> findById(String id) {
		Bson filter = eq(dni, id);
		Bson projectionFields = Projections.excludeId();
		Document result = collection.find(filter).projection(projectionFields).first();
		return Optional.ofNullable(result);
	}

	@Override
	public DeleteResult delete(String dni) {
		DeleteResult resultado = null;
		try {
			Bson filter = eq("Dni", dni);
			resultado = collection.deleteOne(filter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public Boolean updateHistorialMedico(Optional<Document> paciente, Document historial) {
		try {
			if (paciente.isPresent()) {
				Document filter = paciente.get(); // filtro para seleccionar el documento a actualizar
				Document update = new Document("$push", new Document(historial));
				collection.updateOne(filter, update);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Boolean updateAlergenos(Optional<Document> paciente, String atributo, List<String> alergenos) {
		try {
			if (paciente.isPresent()) {
				Document filter = paciente.get();
				collection.updateOne(eq(dni, filter.getString("Dni")), Updates.addEachToSet(atributo, alergenos));
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Boolean updateMedicamentos(Optional<Document> paciente, String atributo, List<String> medicamentos) {
		try {
			if (paciente.isPresent()) {
				Document filter = paciente.get();
				collection.updateOne(eq(dni, filter.getString("Dni")), Updates.addEachToSet(atributo, medicamentos));
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Boolean update(Optional<Document> paciente, String atributo, List<String> valores) {
		try {
			if (paciente.isPresent()) {
				Document filter = paciente.get();
				Document update = new Document("$set", new Document(atributo, valores));
				collection.updateOne(filter, update);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Boolean updateDniMedico(Optional<Document> paciente, String atributo, String valor) {
		try {

			if (paciente.isPresent()) {
				Document filter = paciente.get(); // filtro para seleccionar el documento a actualizar
				Document update = new Document("$set", new Document(atributo, valor));
				collection.updateOne(filter, update);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Boolean updateNombreMedico(Optional<Document> paciente, String atributo, String valor) {
		try {

			if (paciente.isPresent()) {
				Document filter = paciente.get(); // filtro para seleccionar el documento a actualizar
				Document update = new Document("$set", new Document(atributo, valor));
				collection.updateOne(filter, update);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Boolean updateApellidosMedico(Optional<Document> paciente, String atributo, String valor) {
		try {

			if (paciente.isPresent()) {
				Document filter = paciente.get(); // filtro para seleccionar el documento a actualizar
				Document update = new Document("$set", new Document(atributo, valor));
				collection.updateOne(filter, update);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Boolean updateEspecialidadMedico(Optional<Document> paciente, String atributo, String valor) {
		try {

			if (paciente.isPresent()) {
				Document filter = paciente.get(); // filtro para seleccionar el documento a actualizar
				Document update = new Document("$set", new Document(atributo, valor));
				collection.updateOne(filter, update);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Boolean update(Optional<Document> paciente, String atributo, String valor) {
		try {

			if (paciente.isPresent()) {
				Document filter = paciente.get(); // filtro para seleccionar el documento a actualizar
				Document update = new Document("$set", new Document(atributo, valor));
				collection.updateOne(filter, update);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Boolean update(Optional<Document> paciente, String atributo, Document valores) {
		try {
			if (paciente.isPresent()) {
				Document filter = paciente.get();
				Document update = new Document("$set", new Document(atributo, valores));
				collection.updateOne(filter, update);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
