package org.example;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.swing.*;
import java.awt.*;


import static spark.Spark.*;

public class Main {
    private static int totalRequis;

    private static String nomeRequis;


    public static void main(String[] args) {

        JFrame frame = new JFrame("Servidor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,2));

        JTextField inputField1 = new JTextField();
        JTextField inputField2 = new JTextField();

        inputField1.setFont((new Font("Arial", Font.PLAIN, 30)));
        inputField2.setFont((new Font("Arial", Font.PLAIN, 30)));


        JLabel label1 = new JLabel("Nome:");
        label1.setFont((new Font("Arial", Font.BOLD, 30)));
        JLabel label2 = new JLabel("Acesso:");
        label2.setFont((new Font("Arial", Font.BOLD, 30)));


        panel.add(label1);
        panel.add(inputField1);
        panel.add(label2);
        panel.add(inputField2);


        frame.add(panel);
        frame.setVisible(true);
        port(8080);
        get("/api/:nome", (req, res)->{
            String nome = req.params(":nome");
            nomeRequis = nome;
            totalRequis++;
            inputField1.setText(nomeRequis);
            inputField2.setText(Integer.toString(totalRequis));
            return String.valueOf(totalRequis);
        });
        post("/api", ((request, response) -> {
            String nomeEmail = request.body();
            System.out.println("Corpo JSON: " + nomeEmail);

            JsonElement jsonElement = JsonParser.parseString(nomeEmail);
            JsonObject jsonObject =jsonElement.getAsJsonObject();
            inputField1.setText(jsonObject.get("nome").getAsString());
            inputField2.setText(jsonObject.get("email").getAsString());

            if (inputField1.getText()==inputField2.getText())
                return "{\"ack\":\"1\"}";
            else
                return "{\"ack\":\"0\"}";

        }));

        post("/imc", ((request, response) -> {
            String imc = request.body();
            JsonElement jsonElement = JsonParser.parseString(imc);
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            Double massa = jsonObject.get("massa").getAsDouble();
            Double altura = jsonObject.get("altura").getAsDouble();
            System.out.println("Corpo JSON: " + imc );
            Double IMC = massa / Math.pow(altura, 2);
                return String.valueOf(IMC);

        }));
    }
}
