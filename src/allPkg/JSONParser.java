package allPkg;

import javax.json.Json;
import javax.json.stream.JsonParser;
import java.io.FileInputStream;


// Attempt at beginning to parse JSON

public class JSONParser {
    Policy policy = null;


    public void parser(String filename) {

        PolicyBuilder builder = new PolicyBuilder();

        try {

            JsonParser parser = Json.createParser(new FileInputStream(filename));

            while (parser.hasNext()) {
                JsonParser.Event event = parser.next();
                if (event == JsonParser.Event.KEY_NAME) {
                    if (parser.getString().equals("permissions")) {
                        Permission permission = new Permission();
                        builder.withPermission(permission);
                        getElements(parser,permission);
                    }
                    if (parser.getString().equals("prohibitions")) {
                        Prohibition prohibition = new Prohibition();
                        builder.withProhibition(prohibition);
                    }

                }
                if (event == JsonParser.Event.VALUE_STRING) {
                    System.out.print(event.toString() + " " + parser.getString() + " - ");
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void getElements(JsonParser parser, Rules element) {
        JsonParser.Event event = parser.next();
        if (event == JsonParser.Event.KEY_NAME) {
            if (event.toString().equals("target")) {

            }
        }
    }

    public static void main(String[] argv) {
        JSONParser parser = new JSONParser();
        parser.parser("/Users/Chapman/Documents/ODRLxmlTestFile/jsonTest.json");
    }


}
