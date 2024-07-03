import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class WeatherApp {

    // Weather method to parse and recieve weather information.
    public static JSONObject CityWeather(String cityName) throws Exception {
        JSONObject weatherData = null;
        try {
            String firstPartURL = "weathersourcelink";
            String secondPartURL = "apikeyforthesource"; //Enter your api key here
            String encodedCityName = URLEncoder.encode(cityName, "UTF-8");
            String theURL = firstPartURL + encodedCityName + secondPartURL;
            URL url = new URL(theURL);

            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            JSONParser jsonParser = new JSONParser();
            weatherData = (JSONObject) jsonParser.parse(br);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return weatherData;
    }

    //Read the temperature
    public static double parseTemperature(JSONObject weatherData) {
        double cityTemp = 0.0;
        if (weatherData != null) {
            cityTemp = (double) ((JSONObject) weatherData.get("main")).get("temp");
            cityTemp = ((cityTemp - 273.15) * 9) / 5 + 32; // Convert from Kelvin to Fahrenheit
        }
        return cityTemp;
    }

    //read the humidity
    public static long parseHumidity(JSONObject weatherData) {
        long humidity = 0;
        if (weatherData != null) {
            humidity = (long) ((JSONObject) weatherData.get("main")).get("humidity");
        }
        return humidity;
    }
    
    //read the windspeed
    public static double parseWindSpeed(JSONObject weatherData) {
        double windSpeed = 0.0;
        if (weatherData != null) {
            windSpeed = (double) ((JSONObject) weatherData.get("wind")).get("speed");
            windSpeed = windSpeed * 2.23694; // Convert from meters per second to miles per hour
        }
        return windSpeed;
    }
    
    //read the weather condition
    public static String parseWeatherCondition(JSONObject weatherData) {
        String weatherCondition = "";
        if (weatherData != null) {
            JSONArray weatherArray = (JSONArray) weatherData.get("weather");
            JSONObject weather = (JSONObject) weatherArray.get(0);
            weatherCondition = (String) weather.get("main");
        }
        return weatherCondition;
    }

    
}
