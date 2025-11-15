# JavaFX CompletableFuture Demo

A simple JavaFX demonstration of asynchronous programming using CompletableFuture with FXML-based UI updates.

## What This Demonstrates

- An async API that processes iterations in a background thread
- How to update JavaFX UI elements from a background thread using `Platform.runLater()`
- Thread IDs showing which thread executes each part of the code
- Non-blocking UI execution (UI remains responsive while async work happens)
- Progress tracking with a progress bar and status labels
- FXML-based UI design with MVC pattern

## Prerequisites

- Java 21 or higher
- Maven 3.6 or higher

## Building

```bash
mvn clean compile
```

## Running

```bash
mvn javafx:run
```

## Project Structure

- **AsyncUxDemo.java**: Main application class that loads the FXML file
- **AsyncUxController.java**: Controller class with business logic and event handlers
- **async-ux-demo.fxml**: FXML file defining the UI layout

## How It Works

1. User clicks "Start Async Task" button (defined in FXML)
2. Button click triggers `handleStartTask()` in the controller
3. Controller calls the async API: `processIterationsAsync()`
4. API returns immediately and processes 10 iterations in a background thread
5. Each iteration:
   - Delays for 2 seconds (simulating work)
   - Updates UI elements (progress bar, labels) using `Platform.runLater()`
6. UI thread remains responsive throughout the process
7. After all iterations complete, the button is re-enabled

## UI Elements

- **Title**: Displays the application name
- **Status Label**: Shows current task status
- **Progress Label**: Shows iteration count (e.g., "Progress: 3/10")
- **Progress Bar**: Visual representation of completion percentage
- **Start Button**: Initiates the async task (disabled during processing)

## Console Output

The application prints thread IDs to the console to demonstrate which threads are executing different parts of the code:

```
[Thread 24] Main UI thread starting async task
[Thread 24] Main UI thread continues (non-blocking)
[Thread 37] Starting async task...
[Thread 37] Completed iteration 1
[Thread 37] Completed iteration 2
...
[Thread 37] Completed iteration 10
[Thread 37] All iterations completed!
[Thread 37] Task completion handler executed
```

## Key Concepts

- **FXML**: Separates UI definition from business logic
- **MVC Pattern**: Model-View-Controller architecture with FXML (View) and Controller
- **CompletableFuture**: Enables asynchronous, non-blocking operations
- **Platform.runLater()**: Required for updating JavaFX UI from non-UI threads
- **Thread Safety**: Demonstrates proper thread synchronization for UI updates
- **@FXML Annotation**: Injects UI components from FXML into controller

## IntelliJ IDEA

Open the project directory in IntelliJ IDEA and it will automatically recognize the Maven project. Run the application using the Maven goal `javafx:run`.
