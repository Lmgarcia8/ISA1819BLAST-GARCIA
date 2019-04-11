package salud.isa.gsonMedDB;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

/**
 * Created by jmalvarez on 11/5/16.
 * http://developer.android.com/intl/es/training/basics/network-ops/xml.html
 */
public class DatabaseJSonReader {

	private CadenaMando sucesion;
	private JsonReader reader;
	public DatabaseJSonReader(JsonReader r){
		reader=r;
	}

	public void setCadenaMando(CadenaMando s) {
		sucesion=s;
	}

	public String parse() throws IOException {

		reader.beginObject();
		StringBuffer readData = new StringBuffer();

		while (reader.hasNext()) {
			String name = reader.nextName();
			readData.append(sucesion.estudio(name,reader));
		}

		reader.endObject();
		reader.close();
		reader.close();

		return readData.toString();
	}
}
