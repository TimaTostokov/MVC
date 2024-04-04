package org.example;

import org.example.controller.Controller;
import org.example.model.Model;
import org.example.view.UserView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        Controller controller = new Controller(model);
        UserView userView = new UserView(controller);
        SwingUtilities.invokeLater(userView::init);
    }

}