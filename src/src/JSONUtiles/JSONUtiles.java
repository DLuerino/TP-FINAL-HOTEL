package JSONUtiles;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JSONUtiles {

    public static void grabar(JSONArray o, String archivo){

        try {
            FileWriter file = new FileWriter(archivo);
            file.write(o.toString(4));
            file.close();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public static JSONTokener leer(String archivo){

        JSONTokener t = null;

        try {
            t = new JSONTokener(new FileReader(archivo));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return t;
    }

}
