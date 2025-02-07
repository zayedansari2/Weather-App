import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import org.json.simple.JSONObject;

public class WeatherAppGui {
    // Method to create and display the GUI
    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Weather App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 700);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);  // Use absolute positioning
        frame.add(panel);
        placeComponents(panel);
        
        frame.setVisible(true); // Set the frame visibility
    }
    
    private static void placeComponents(JPanel panel) {
        Font font = new Font("Arial", Font.BOLD, 20);
        
        JLabel userLabel = new JLabel("City:"); // Label for city name
        userLabel.setFont(font);
        userLabel.setBounds(20, 25, 60, 40);
        panel.add(userLabel);
        
        JTextField cityText = new JTextField(20); // Text field for entering city name
        cityText.setFont(font);
        cityText.setBounds(90, 20, 290, 50);
        panel.add(cityText);
        
        URL imgURL = WeatherAppGui.class.getResource("/assets/search.png");
        ImageIcon searchIcon = new ImageIcon(imgURL);
        
        JButton getWeatherButton = new JButton(searchIcon); // Button to fetch weather
        getWeatherButton.setBounds(390, 20, 50, 50);
        panel.add(getWeatherButton);
        
        JLabel temperatureLabel = new JLabel(); // Label to display the temperature
        temperatureLabel.setFont(new Font("Dialog", Font.BOLD, 48));
        temperatureLabel.setHorizontalAlignment(SwingConstants.CENTER);
        temperatureLabel.setBounds(20, 320, 460, 60);
        panel.add(temperatureLabel);
        
        JLabel condition = new JLabel(); // Label for weather condition
        condition.setBounds(205, 380, 460, 60);
        condition.setFont(new Font("Dialog", Font.PLAIN, 25));
        panel.add(condition);
        
        JLabel weatherImageLabel = new JLabel(); // Label to display the weather condition image
        weatherImageLabel.setBounds(120, 60, 300, 300);
        panel.add(weatherImageLabel);
        
        JLabel windSpeedLabel = new JLabel("<html><b>Windspeed: </b> 15mph</html>"); // Label for wind speed
        windSpeedLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
        windSpeedLabel.setBounds(150, 490, 200, 40);
        panel.add(windSpeedLabel);
        
        URL windSpeedImgURL = WeatherAppGui.class.getResource("/assets/windspeed.png");
        ImageIcon windSpeedIcon = new ImageIcon(windSpeedImgURL);
        JLabel windSpeedIconLabel = new JLabel(windSpeedIcon); // Icon for wind speed
        windSpeedIconLabel.setBounds(20, 480, 74, 66);
        panel.add(windSpeedIconLabel);
        
        JLabel humidityLabel = new JLabel("<html><b>Humidity: </b> 100%</html>"); // Label for humidity
        humidityLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
        humidityLabel.setBounds(150, 585, 140, 40);
        panel.add(humidityLabel);
        
        URL humidityImgURL = WeatherAppGui.class.getResource("/assets/humidity.png");
        ImageIcon humidityIcon = new ImageIcon(humidityImgURL);
        JLabel humidityIconLabel = new JLabel(humidityIcon); // Icon for humidity
        humidityIconLabel.setBounds(12, 560, 100, 100);
        panel.add(humidityIconLabel);
        
        // Action listener to fetch weather data
        ActionListener fetchWeatherAction = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cityName = cityText.getText();
                double temperature = 0.0;
                long humidity = 0;
                double windSpeed = 0.0;
                String weatherCondition = "";
                try {
                    JSONObject weatherData = WeatherApp.CityWeather(cityName);
                    temperature = WeatherApp.parseTemperature(weatherData);
                    humidity = WeatherApp.parseHumidity(weatherData);
                    windSpeed = WeatherApp.parseWindSpeed(weatherData);
                    weatherCondition = WeatherApp.parseWeatherCondition(weatherData);
                    
                    String formattedTemperature = String.format("%.1f", temperature) + "\u00B0" + "F";
                    String formattedWindSpeed = String.format("%.1f", windSpeed) + " mph";
                    temperatureLabel.setText(formattedTemperature);
                    humidityLabel.setText("Humidity: " + humidity + "%");
                    windSpeedLabel.setText("Windspeed: " + formattedWindSpeed);
                    condition.setText(weatherCondition);
                    
                    // Set weather condition image
                    switch (weatherCondition) {
                        case "Clear":
                            weatherImageLabel.setIcon(new ImageIcon(WeatherAppGui.class.getResource("/assets/clear.png")));
                            break;
                        case "Clouds":
                            weatherImageLabel.setIcon(new ImageIcon(WeatherAppGui.class.getResource("/assets/cloudy.png")));
                            break;
                        case "Rain":
                            weatherImageLabel.setIcon(new ImageIcon(WeatherAppGui.class.getResource("/assets/rain.png")));
                            break;
                        case "Snow":
                            weatherImageLabel.setIcon(new ImageIcon(WeatherAppGui.class.getResource("/assets/snow.png")));
                            break;
                        default:
                            weatherImageLabel.setIcon(null);
                    }
                } catch (Exception ex) {
                    temperatureLabel.setText("Error fetching weather data");
                    humidityLabel.setText("Error fetching weather data");
                    windSpeedLabel.setText("Error fetching weather data");
                    weatherImageLabel.setIcon(null);
                    ex.printStackTrace();
                }
            }
        };
        
        getWeatherButton.addActionListener(fetchWeatherAction);
        cityText.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    fetchWeatherAction.actionPerformed(null);
                }
            }
        });
    }
}