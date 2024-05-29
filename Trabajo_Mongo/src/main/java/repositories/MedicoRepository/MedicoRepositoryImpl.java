package repositories.MedicoRepository;

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

public class MedicoRepositoryImpl implements MedicoRepository {

	MongoClient mongoClient = MongoDB.getClient();
	MongoDatabase database = mongoClient.getDatabase("TrabajoMongo");
	MongoCollection<Document> collection = database.getCollection("Medicos");
	String dni = "Dni", nombre = "Nombre", apellidos = "Apellidos", especialidad = "Especialidad",
			fecha_incorporacion = "Fecha_Incorporacion";

	@Override
	public List<Document> findAll() {
		Bson projectionFields = Projections.excludeId();

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
	public String[] guardarPacientesCargo(String paciente) {
		Bson filter = eq(dni, paciente);
		Document document = collection.find(filter).first();
		List<String> medicamentos = (List<String>) document.get("Pacientes_Cargo");
		return medicamentos.toArray(new String[0]);
	}

	public Boolean eliminarPacienteCargo(Optional<Document> paciente, String atributo, String valor) {
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
	
	public String findDniPorNombre(String medico) {
		Bson filter = eq(nombre, medico);
		Document result = collection.find(filter).first();
		Object dniList = result.get(dni);
		return (String) dniList;

	}

	public String findNombrePorNombre(String medico) {
		Bson filter = eq(nombre, medico);
		Document result = collection.find(filter).first();
		Object dniList = result.get(nombre);
		return (String) dniList;

	}

	public String findApellidosPorNombre(String medico) {
		Bson filter = eq(nombre, medico);
		Document result = collection.find(filter).first();
		Object dniList = result.get(apellidos);
		return (String) dniList;

	}

	public String findEspecialidadPorNombre(String medico) {
		Bson filter = eq(nombre, medico);
		Document result = collection.find(filter).first();
		Object dniList = result.get(especialidad);
		return (String) dniList;

	}

	public String findFechaIncorporacionPorNombre(String medico) {
		Bson filter = eq(nombre, medico);
		Document result = collection.find(filter).first();
		Object dniList = result.get(fecha_incorporacion);
		return (String) dniList;

	}

	public String findDniPorDni(String medico) {
		Bson filter = eq(dni, medico);
		Document result = collection.find(filter).first();
		Object dniList = result.get(dni);
		return (String) dniList;

	}

	public String findNombrePordni(String medico) {
		Bson filter = eq(dni, medico);
		Document result = collection.find(filter).first();
		Object dniList = result.get(nombre);
		return (String) dniList;

	}

	public String findApellidosPordni(String medico) {
		Bson filter = eq(dni, medico);
		Document result = collection.find(filter).first();
		Object dniList = result.get(apellidos);
		return (String) dniList;

	}

	public String findEspecialidadPordni(String medico) {
		Bson filter = eq(dni, medico);
		Document result = collection.find(filter).first();
		Object dniList = result.get(especialidad);
		return (String) dniList;

	}

	public String findFechaIncorporacionPordni(String medico) {
		Bson filter = eq(dni, medico);
		Document result = collection.find(filter).first();
		Object dniList = result.get(fecha_incorporacion);
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

	public String findEspecialidadPorAtributo(String atributo, String valor) {
		Bson filter = eq(atributo, valor);

		Document result = collection.find(filter).first();
		Object dniList = result.get(especialidad);
		return (String) dniList;

	}

	public String findFechaIncorporacionPorAtributo(String atributo, String valor) {
		Bson filter = eq(atributo, valor);

		Document result = collection.find(filter).first();
		Object dniList = result.get(fecha_incorporacion);
		return (String) dniList;

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

	public Boolean update(Optional<Document> medico, String atributo, List<String> valores) {
		try {
			if (medico.isPresent()) {
				Document filter = medico.get();
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

	public Boolean update(Optional<Document> medico, String atributo, String valor) {
		try {

			if (medico.isPresent()) {
				Document filter = medico.get(); // filtro para seleccionar el documento a actualizar
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

	public Boolean update(Optional<Document> medico, String atributo, Document valores) {
		try {
			if (medico.isPresent()) {
				Document filter = medico.get();
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

	public Boolean updatePacientesCargo(Optional<Document> medico, String atributo, List<String> valor) {
		try {

			if (medico.isPresent()) {
				Document filter = medico.get();
				collection.updateOne(eq("Dni", filter.getString("Dni")), Updates.addEachToSet(atributo, valor));
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
