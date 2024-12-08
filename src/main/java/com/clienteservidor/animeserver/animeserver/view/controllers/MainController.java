package com.clienteservidor.animeserver.animeserver.view.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.clienteservidor.animeserver.animeserver.models.EmployeeModel;
import com.clienteservidor.animeserver.animeserver.services.EmployeeService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/fxml/LoginView.fxml") // Associa o controlador ao arquivo FXML
public class MainController {

    @FXML
    private TextField emailText;
    @FXML
    private TextField passwordText;
    @FXML
    private Button loginButton;
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private FxWeaver fxWeaver;

    private Stage stage;

    // Método para configurar o Stage
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    @SuppressWarnings("unused")
    private void handleLoginButtonAction(ActionEvent event) {
        String email = emailText.getText();
        String password = passwordText.getText();

        // Validação básica
        if (email.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Campos obrigatórios");
            alert.setContentText("Email e senha são obrigatórios.");
            alert.showAndWait();
            return;
        }

        try {
            EmployeeModel employee = employeeService.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Funcionário não encontrado."));

            if (!employee.getPassword().equals(password)) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Senha incorreta");
                alert.setContentText("A senha informada está incorreta.");
                alert.showAndWait();
                return;
            }

            System.out.println("Login realizado com sucesso!");

            Parent root = fxWeaver.loadView(GerenciamentoViewController.class);
            Scene scene = new Scene(root);

            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.show();

            if (stage != null) {
                stage.close();
            }

        } catch (RuntimeException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro ao realizar login");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
