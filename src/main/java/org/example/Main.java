package org.example;


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
    }
}

