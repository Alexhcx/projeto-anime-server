package com.clienteservidor.animeserver.animeserver.view;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.clienteservidor.animeserver.animeserver.AnimeserverApplication;
import com.clienteservidor.animeserver.animeserver.view.controllers.MainController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFxApplication extends Application {

    private ConfigurableApplicationContext springContext;

    @Override
    public void init() {
        springContext = SpringApplication.run(AnimeserverApplication.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoginView.fxml"));
        loader.setControllerFactory(springContext::getBean);

        Parent root = loader.load();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        
        MainController  loginController = loader.getController();
        loginController.setStage(stage);
    }

    @Override
    public void stop() throws Exception {
        if (springContext != null) {
            springContext.close();
        }
    }
}
