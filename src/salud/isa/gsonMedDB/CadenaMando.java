package salud.isa.gsonMedDB;

import java.io.IOException;
import com.google.gson.stream.JsonReader;

public abstract class CadenaMando {
	protected CadenaMando descendiente;
	protected String respuesta;

	public void setDescendiente(CadenaMando d) {
		descendiente=d;
	}

	abstract public String estudio (String nombre, JsonReader reader) throws IOException;

}
