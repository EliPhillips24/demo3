package com.example.demo3;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Popup;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.lang.Math; // Needed to use Math.round()

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
public class HelloController {
    @FXML
    private Label welcomeText;

    public TextField currentTextField;
    public TextField introcurrentTextField;

public ListView myListView;
    private static final String API_ENDPOINT = "https://currency-converter18.p.rapidapi.com/api/v1/convert";
    private static final String FROM_CURRENCY = "USD";
    private static String TO_CURRENCY = "KWD";
    private static double AMOUNT_TO_CONVERT = 0;
    private static final String API_KEY = "f256297849mshef77e781aa0d584p100210jsn4863ea880edc";
    private static final String API_HOST = "currency-converter18.p.rapidapi.com";


    @FXML
    protected void onHelloButtonClick() throws Exception {
        AMOUNT_TO_CONVERT = Double.parseDouble(introcurrentTextField.getText().toString());
        String apiUri = String.format("%s?from=%s&to=%s&amount=%s", API_ENDPOINT, FROM_CURRENCY, TO_CURRENCY, AMOUNT_TO_CONVERT);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUri))
                .header("X-RapidAPI-Key", API_KEY)
                .header("X-RapidAPI-Host", API_HOST)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        String jsonData = response.body(); // Replace [...] with your JSON data
        ObjectMapper objectMapper = new ObjectMapper();

        // Parse JSON string to JsonNode
        JsonNode jsonNode = objectMapper.readTree(jsonData);

        // Extract values from the JsonNode
        boolean success = jsonNode.get("success").asBoolean();
        JsonNode resultNode = jsonNode.get("result");

        String fromCurrency = resultNode.get("from").asText();
        String toCurrency = resultNode.get("to").asText();

    double amountToConvert = resultNode.get("amountToConvert").asDouble();
    double convertedAmount = resultNode.get("convertedAmount").asDouble();


    // Print the extracted values
    System.out.println("Success: " + success);
    System.out.println("From Currency: " + fromCurrency);
    System.out.println("To Currency: " + toCurrency);
    System.out.println("Amount to Convert: " + amountToConvert);
    System.out.println("Converted Amount: " + convertedAmount);

    double num1 = convertedAmount;

    DecimalFormat df = new DecimalFormat("#.##");


    currentTextField.setText(String.valueOf(df.format(num1)) + " " + toCurrency);
}



    public void initialize() throws Exception {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://currency-converter18.p.rapidapi.com/api/v1/supportedCurrencies"))
                .header("X-RapidAPI-Key", "f256297849mshef77e781aa0d584p100210jsn4863ea880edc")
                .header("X-RapidAPI-Host", "currency-converter18.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        String jsonArray = response.body(); // Assign the JSON response to jsonArray
        System.out.println(jsonArray);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = response.body(); // Replace [...] with your JSON data


            // Parse JSON array to JsonNode
            JsonNode jsonArray2 = objectMapper.readTree(json);

            // Iterate through array elements
            for (JsonNode element : jsonArray2) {
                String symbol = element.get("symbol").asText();
                String name = element.get("name").asText();
                System.out.println("Symbol: " + symbol + ", Name: " + name);
                myListView.getItems().add(symbol + " " + name);
                System.out.println();
            }
    }

    public void convert() throws Exception {
        try {
            ObservableList selectedIndices = myListView.getSelectionModel().getSelectedIndices();

            String selectedItem = (String) myListView.getSelectionModel().getSelectedItem();

            System.out.println(selectedItem + "selectedItem");

            String input = selectedItem;        //input string
            String firstFourChars = "";        //substring containing first 4 characters

            if (input.length() > 3) {
                firstFourChars = input.substring(0, 3);
            } else {
                firstFourChars = input;
            }

            System.out.println(firstFourChars);

            TO_CURRENCY = firstFourChars;
            this.onHelloButtonClick();
        }catch (Exception e){
            this.dilo();
        }
    }


    public void dilo(){
        //Creating a dialog
        Dialog<String> dialog = new Dialog<String>();
        //Setting the title
        dialog.setTitle("Error");
        ButtonType type = new ButtonType("Return", ButtonBar.ButtonData.OK_DONE);
        //Setting the content of the dialog
        dialog.setContentText("Please Input Numbers!");
        //Adding buttons to the dialog pane
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();

    }
}

