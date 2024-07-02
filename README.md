# WeatherApp
<img width="361" alt="image" src="https://github.com/zayedansari2/Weather-App---Java/assets/174281840/8c1fa95e-c782-418e-b16f-2bbbf9d32387">




# Overview
The Weather App is a Java application that provides a graphical user interface (GUI) for retrieving and displaying current weather information for any city. It uses the OpenWeatherMap API to fetch weather data and displays temperature, humidity, wind speed, and weather conditions with corresponding icons. Used Object Oriented programming principles.

**Resources Used**
- java
- json simple - used to parse and read through json data

**Files**
- `WeatherAppGui.java`: This file contains the `WeatherAppGui` class, which sets up the GUI using Java Swing components and handles user

 interactions.
- `WeatherApp.java`: This file contains the `WeatherApp` class, which includes methods to fetch and parse weather data from the OpenWeatherMap API.
- `AppLauncher.java`: This file contains the `AppLauncher` class with the `main` method to launch the application.

**Usage**
To use the Weather App, follow these steps:
1. Compile the Java files:
   
   javac WeatherAppGui.java WeatherApp.java AppLauncher.java
   
2. Run the compiled Java program:
   
   java AppLauncher
   

**Class Details**

1. **WeatherAppGui.java**
   This class sets up the GUI and handles user interactions.

   - **Attributes**
     - `JFrame frame`: The main window frame.
     - `JPanel panel`: The panel containing GUI components.
     - `JLabel userLabel`: Label for the city input field.
     - `JTextField cityText`: Text field for entering the city name.
     - `JButton getWeatherButton`: Button to fetch weather data.
     - `JLabel temperatureLabel`: Label to display the temperature.
     - `JLabel condition`: Label to display the weather condition.
     - `JLabel weatherImageLabel`: Label to display the weather condition image.
     - `JLabel windSpeedLabel`: Label to display the wind speed.
     - `JLabel windSpeedIconLabel`: Label to display the wind speed icon.
     - `JLabel humidityLabel`: Label to display the humidity.
     - `JLabel humidityIconLabel`: Label to display the humidity icon.
     
   - **Methods**
     - `createAndShowGUI()`: Creates and shows the main GUI frame.
     - `placeComponents(JPanel panel)`: Sets up and places components on the panel.
     
2. **WeatherApp.java**
   This class handles fetching and parsing weather data from the OpenWeatherMap API.

   - **Methods**
     - `CityWeather(String cityName)`: Fetches weather data for the given city name.
     - `parseTemperature(JSONObject weatherData)`: Parses the temperature from the weather data.
     - `parseHumidity(JSONObject weatherData)`: Parses the humidity from the weather data.
     - `parseWindSpeed(JSONObject weatherData)`: Parses the wind speed from the weather data.
     - `parseWeatherCondition(JSONObject weatherData)`: Parses the weather condition from the weather data.

3. **AppLauncher.java**
   This class contains the `main` method to launch the application.

   - **Methods**
     - `main(String[] args)`: The entry point of the application, calling `createAndShowGUI()` to launch the GUI.

