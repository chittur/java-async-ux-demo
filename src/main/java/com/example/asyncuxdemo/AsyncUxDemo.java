/******************************************************************************
 * Filename    = AsyncUxDemo.java
 *
 * Author      = Ramaswamy Krishnan-Chittur
 *
 * Project     = java-async-ux-demo
 * 
 * Description = A simple JavaFX demo of asynchronous programming using 
 *               CompletableFuture with FXML-based UI updates.
 *****************************************************************************/

package com.example.asyncuxdemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AsyncUxDemo extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("async-ux-demo.fxml"));
        Parent root = loader.load();
        
        // Scene and Stage
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("JavaFX CompletableFuture Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
