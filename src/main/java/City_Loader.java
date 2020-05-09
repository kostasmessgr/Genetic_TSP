import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.json.simple.parser.ParseException;

public class City_Loader {

    private ArrayList<City> cities;
    private int city_number;
    private String filename= "src\\main\\resources\\1000-largest-us-cities-by-population-with-geographic-coordinates.json";

    public City_Loader(int number){
        this.city_number=number;
        load_cities();
    }

    private void load_cities(){
        ArrayList<City> result = new ArrayList<City>();

       JSONParser parser = new JSONParser();

       try{
           JSONArray array = (JSONArray) parser.parse(new FileReader(filename));
           cities =  new ArrayList<City>();
           Random r = new Random();
           for(int i=0;i<city_number;i++){
               int num = r.nextInt(array.size());
               JSONObject curr_object = (JSONObject) array.get(num);
               JSONObject fields = (JSONObject)curr_object.get("fields");
               JSONArray coordinates = (JSONArray)fields.get("coordinates");
               City curr_city = new City((String)fields.get("city"),i,(double)coordinates.get(1),(double)coordinates.get(0));
               cities.add(curr_city);
           }

       }catch(FileNotFoundException e) {
           e.printStackTrace();
       }catch (ParseException e) {
           e.printStackTrace();
       }catch (IOException e) {
           e.printStackTrace();
       }

    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public void printCities(){
        for(City c:cities){
            System.out.println(c.cityStr());
        }
    }


}
