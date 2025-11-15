/******************************************************************************
 * Filename    = AsyncUxController.java
 *
 * Author      = Ramaswamy Krishnan-Chittur
 *
 * Project     = java-async-ux-demo
 * 
 * Description = Controller class for the JavaFX FXML-based CompletableFuture demo.
 *****************************************************************************/

package com.example.asyncuxdemo;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.util.concurrent.CompletableFuture;

public class AsyncUxController {
    
    @FXML
    private Label statusLabel;
    
    @FXML
    private Label progressLabel;
    
    @FXML
    private ProgressBar progressBar;
    
    @FXML
    private Button startButton;
    
    // Async API: Process iterations asynchronously
    private CompletableFuture<Void> processIterationsAsync() {
        return CompletableFuture.runAsync(() -> {
            long threadId = Thread.currentThread().threadId();
            System.out.println("[Thread " + threadId + "] Starting async task...");
            
            for (int i = 1; i <= 10; i++) {
                final int iteration = i;
                
                // Simulate work with 2 second delay
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }

                // Set progress bar color to go from red to green as progress increases
                // Calculate color in worker thread (outside Platform.runLater)
                double progress = iteration / 10.0;
                String color = String.format("#%02x%02x%02x", 
                    (int)((1 - progress) * 255), 
                    (int)(progress * 255), 
                    0);

                // Then capture it in the lambda
                final String calculatedColor = color;
                
                // Update UI on JavaFX Application Thread
                Platform.runLater(() -> {
                    // Set progress bar color based on iteration
                    progressBar.setStyle("-fx-accent: " + calculatedColor + ";");

                    // Update progress label and progress bar
                    progressLabel.setText("Progress: " + iteration + "/10");
                    progressBar.setProgress(iteration / 10.0);
                    statusLabel.setText("Processing iteration " + iteration);
                });
                
                System.out.println("[Thread " + threadId + "] Completed iteration " + iteration);
            }
            
            System.out.println("[Thread " + threadId + "] All iterations completed!");
        });
    }
    
    // Event handler for Start Task button
    @FXML
    private void handleStartTask() {
        // Disable button during processing
        startButton.setDisable(true);
        statusLabel.setText("Task started...");
        progressLabel.setText("Progress: 0/10");
        progressBar.setProgress(0);
        
        long mainThreadId = Thread.currentThread().threadId();
        System.out.println("[Thread " + mainThreadId + "] Main UI thread starting async task");
        
        // Call async API and handle completion
        processIterationsAsync().thenRun(() -> {
            Platform.runLater(() -> {
                statusLabel.setText("Task completed!");
                startButton.setDisable(false);
            });
            
            long threadId = Thread.currentThread().threadId();
            System.out.println("[Thread " + threadId + "] Task completion handler executed");
        });
        
        System.out.println("[Thread " + mainThreadId + "] Main UI thread continues (non-blocking)");
    }
}
