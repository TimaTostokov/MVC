package org.example.view;

import org.example.controller.Controller;

import javax.swing.*;
import java.awt.*;

public class UserView extends JFrame {
    private JTextArea text;
    private JButton sendButton;
    private JLabel info;
    private Controller controller;

    public UserView(Controller controller) {
        this.controller = controller;
    }

    public void init() {
        setSize(400, 300);
        text = new JTextArea();
        sendButton = new JButton("Send");
        info = new JLabel("Info");

        add(text, BorderLayout.CENTER);
        add(sendButton, BorderLayout.SOUTH);
        add(info, BorderLayout.NORTH);

        sendButton.addActionListener(e -> {
            int pin;
            String textPing = this.text.getText();
            text.setText("");
            try {
                pin = Integer.parseInt(textPing);
                info.setText("Got it" + pin + " Waiting...");

                Thread thread = new Thread(() -> {
                    sendButton.setEnabled(false);
                    boolean result;
                    result = controller.checkPin(pin);

                    String resultMessage;
                    if (result) {
                        resultMessage = "Success";
                    } else {
                        resultMessage = "Wrong, try again";
                    }
                    info.setText(resultMessage);
                    sendButton.setEnabled(true);
                });
                thread.start();
            } catch (NumberFormatException e1) {
                info.setText("Only numbers allowed...");
            }
        });
    }
}