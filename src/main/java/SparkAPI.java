import static spark.Spark.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

public class SparkAPI {
    public static void main(String[] args) {
        port(System.getenv("PORT") != null ? Integer.parseInt(System.getenv("PORT")) : 9090); // Spark will run on port 9090, as opposed to 4567 which it will default to if not specified

        get("/hello", (req, res) -> "Hello World");

        get("/method", (req, res) -> methodRoute());

        get("/method/:param", (req, res) -> methodRoute(req.params(":param")));

        // you can test this route using the following curl
        // curl -X POST -d "hello world" http://localhost:9090/postit
        post("/postit", (req, res) -> postIt(req.body()));

        // curl -X POST -d '{"data" : [{"line" : "This is a line"}, {"line" : "This is also a line"}]}' http://localhost:9090/postJSON
        post("/postJSON", (req, res) -> parseJSON(req.body()));
    }

    private static String methodRoute(){
        return "The method route was hit";
    }

    private static String methodRoute(String param){
        return "Hello, you sent the method route the word " + param;
    }

    private static String postIt(String bodyText){
        return bodyText;
    }

    private static String parseJSON(String bodyText){

        try{
            JSONParser parser = new JSONParser();

            // get the array that is the value for the key "data"
            JSONObject obj=(JSONObject)parser.parse(bodyText);
            Object fieldValue = obj.get("data");

            // cast this to a JSON array to enable iteration
            JSONArray array=(JSONArray)fieldValue;

            for(int i = 0; i < array.size(); i++){
                //cast each line to a JSON object
                JSONObject lineDetails = (JSONObject)array.get(i);
                String lineValue = (String)lineDetails.get("line");

                System.out.println("====== element " + i + " ======");
                System.out.println(lineDetails);
                System.out.println("====== line value  ======");
                System.out.println(lineValue);
                System.out.println();
            }

        }catch(ParseException pe){

            System.out.println("position: " + pe.getPosition());
            System.out.println(pe);
        }

        return "done";
    }
}