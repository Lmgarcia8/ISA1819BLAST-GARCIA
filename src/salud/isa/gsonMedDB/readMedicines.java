package salud.isa.gsonMedDB;

import java.io.IOException;
import com.google.gson.stream.JsonReader;

public class readMedicines extends CadenaMando{

	private static final String MEDICINES_TAGNAME = "medicines";
	private static final String NAME_FIELD_TAGNAME = "name";

	public readMedicines(CadenaMando d) throws IOException {
		// TODO Auto-generated constructor stub
		setDescendiente(d);

	}
	private StringBuffer readMedicine(JsonReader reader) throws IOException {
		StringBuffer medicineData = new StringBuffer();
		reader.beginArray();
		while (reader.hasNext()) {
			reader.beginObject();
			medicineData.append(readMedicineEntry(reader)).append("\n");
			reader.endObject();
		}
		medicineData.append("\n");
		reader.endArray();
		return medicineData;
	}

	private String readMedicineEntry(JsonReader reader) throws IOException {
		//	        reader.require(XmlPullParser.START_TAG, Ins, SINGLE_ELEMENT_TAGNAME);
		String medName = null;
		while(reader.hasNext()){
			String name = reader.nextName();
			switch (name) {
			case NAME_FIELD_TAGNAME:
				medName = reader.nextString();
				break;
			default:
				reader.skipValue();
			}
		}

		return medName;
	}
	@Override
	public String estudio (String nombre, JsonReader reader) throws IOException {
		// TODO Auto-generated method stub
		StringBuffer readData = new StringBuffer();
		if (nombre.equals(MEDICINES_TAGNAME)) {

			readData.append(readMedicine(reader)).append("\n");
		}else if (descendiente!=null){
			readData.append(descendiente.estudio(nombre, reader));
		}else {
			reader.skipValue();
			readData.append("Category " + nombre + " not processed.").append("\n");
		}

		return readData.toString();
	}


}
