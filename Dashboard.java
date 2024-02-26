import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Dashboard extends Application {

    private Stage primaryStage;
    private BorderPane layout;
    private Scene loginScene;
    private Scene dashboardScene;
    private Scene orderScene;
    private Scene menuScene;
    private Scene reservationScene;
    private Scene userScene;
    private TextField usernameField;
    private PasswordField passwordField;
    private Label errorLabel;

    @Override
    public void start(Stage primaryStage) {//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        try {
            Image icon = new Image(new FileInputStream("Images/G.png"));
            primaryStage.getIcons().add(icon);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Gus's Fine Eating");

        createScenes();
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    private void createScenes() {//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        createLoginScene();
        createDashboardScene();
        createOrderScene();
        createMenuScene();
        createReservationScene();
        createUserScene();

    }



    private void createLoginScene() {//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //greeting------------------------------------------------------------------------------------------------------------------------------------------------------
        Text greeting = new Text("Gus's Fine Eating &\nSoftware Development Company LLC");
        greeting.setStyle("-fx-font-size: 22px;");
        greeting.styleProperty().bind(Bindings.concat("-fx-font-size: ", primaryStage.widthProperty().add(primaryStage.heightProperty()).divide(40), "px;"));

        usernameField = new TextField();
        passwordField = new PasswordField();
        usernameField.setPromptText("Username");
        passwordField.setPromptText("Password");

        usernameField.styleProperty().bind(Bindings.concat("-fx-font-size: ", primaryStage.widthProperty().add(primaryStage.heightProperty()).divide(80).add(5), "px;"));
        passwordField.styleProperty().bind(Bindings.concat("-fx-font-size: ", primaryStage.widthProperty().add(primaryStage.heightProperty()).divide(80).add(5), "px;"));

        errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");

        //button----------------------------------------------------------------------------------------------------------------------------------------------------------------
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> login());

        //layout--------------------------------------------------------------------------------------------------------------
        VBox loginLayout = new VBox(10);
        loginLayout.setAlignment(Pos.CENTER);
        loginLayout.setPadding(new Insets(20));
        loginLayout.getChildren().addAll(usernameField, passwordField, loginButton, errorLabel);
        loginLayout.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        layout = new BorderPane();
        layout.setTop(greeting);
        BorderPane.setAlignment(greeting, Pos.CENTER);
        BorderPane.setMargin(greeting, new Insets(20));
        BorderPane.setMargin(loginLayout, new Insets(100, 0, 0, 0));
        layout.setCenter(loginLayout);
        layout.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));

        loginScene = new Scene(layout, 800, 800);
    }

    private void login() {//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.equals("admin") && password.equals("password")) {
            primaryStage.setScene(dashboardScene);
            usernameField.clear();
            passwordField.clear();
            errorLabel.setText("");
        } else {
            errorLabel.setText("Invalid username or password, Please try again!");
        }
    }



    private void createDashboardScene() {//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //greeting------------------------------------------------------------------------------------------------------------------------------------------------------
        Text greeting = new Text("Welcome to Gus's Fine Eating &\nSoftware Development Company LLC");
        greeting.setStyle("-fx-font-size: 22px;");
        greeting.styleProperty().bind(Bindings.concat("-fx-font-size: ", primaryStage.widthProperty().add(primaryStage.heightProperty()).divide(40), "px;"));

        //buttons----------------------------------------------------------------------------------------------------------------------------------------------------------------
        Button placeOrderBtn = new Button("Place Order");
        Button resManBtn = new Button("Make or Check a Reservation");
        Button userManBtn = new Button("User Management");
        Button logOutBtn = new Button("Log Out");
        Button settingsBtn = new Button("Settings");

        placeOrderBtn.styleProperty().bind(Bindings.concat("-fx-font-size: ", primaryStage.widthProperty().add(primaryStage.heightProperty()).divide(80).add(5), "px;"));
        resManBtn.styleProperty().bind(Bindings.concat("-fx-font-size: ", primaryStage.widthProperty().add(primaryStage.heightProperty()).divide(80).add(5), "px;"));
        userManBtn.styleProperty().bind(Bindings.concat("-fx-font-size: ", primaryStage.widthProperty().add(primaryStage.heightProperty()).divide(80).add(5), "px;"));
        logOutBtn.styleProperty().bind(Bindings.concat("-fx-font-size: ", primaryStage.widthProperty().add(primaryStage.heightProperty()).divide(80).add(5), "px;"));
        settingsBtn.styleProperty().bind(Bindings.concat("-fx-font-size: ", primaryStage.widthProperty().add(primaryStage.heightProperty()).divide(80).add(5), "px;"));

        placeOrderBtn.setOnAction(e -> primaryStage.setScene(orderScene));
        resManBtn.setOnAction(e -> primaryStage.setScene(reservationScene));
        userManBtn.setOnAction(e -> primaryStage.setScene(userScene));
        logOutBtn.setOnAction(e -> primaryStage.setScene(loginScene));
        settingsBtn.setOnAction(e -> {});

        VBox buttonBox = new VBox(10);
        buttonBox.getChildren().addAll(placeOrderBtn, resManBtn, userManBtn, settingsBtn, logOutBtn);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setAlignment(Pos.CENTER);

        //layout----------------------------------------------------------------------------------------------------------------
        layout = new BorderPane();
        layout.setCenter(buttonBox);
        layout.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        BorderPane.setAlignment(greeting, Pos.CENTER);
        layout.setTop(greeting);

        dashboardScene = new Scene(layout, 800, 800);
    }


    private void createOrderScene() {//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //greeting------------------------------------------------------------------------------------------------------------------------------------------------------
        Text greeting = new Text("Please Select From the Following:");
        greeting.setStyle("-fx-font-size: 22px;");
        greeting.styleProperty().bind(Bindings.concat("-fx-font-size: ", primaryStage.widthProperty().add(primaryStage.heightProperty()).divide(40), "px;"));

        //buttons----------------------------------------------------------------------------------------------------------------------------------------------------------------
        Button viewMenuBtn = new Button("View Menu and Order");
        Button checkOrderBtn = new Button("Check or Update Order");
        Button backBtn = new Button("<-- Back to Dashboard");

        viewMenuBtn.styleProperty().bind(Bindings.concat("-fx-font-size: ", primaryStage.widthProperty().add(primaryStage.heightProperty()).divide(80).add(5), "px;"));
        checkOrderBtn.styleProperty().bind(Bindings.concat("-fx-font-size: ", primaryStage.widthProperty().add(primaryStage.heightProperty()).divide(80).add(5), "px;"));
        backBtn.styleProperty().bind(Bindings.concat("-fx-font-size: ", primaryStage.widthProperty().add(primaryStage.heightProperty()).divide(80).add(5), "px;"));

        viewMenuBtn.setOnAction(e -> primaryStage.setScene(menuScene));
        checkOrderBtn.setOnAction(e -> {});
        backBtn.setOnAction(e -> primaryStage.setScene(dashboardScene));

        VBox buttonBox = new VBox(10);
        buttonBox.getChildren().addAll(viewMenuBtn, checkOrderBtn, backBtn);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setAlignment(Pos.CENTER);

        //layout----------------------------------------------------------------------------------------------------------------
        layout = new BorderPane();
        layout.setCenter(buttonBox);
        layout.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        BorderPane.setAlignment(greeting, Pos.CENTER);
        layout.setTop(greeting);

        orderScene = new Scene(layout, 800, 800);
    }


    
    private void createMenuScene() {//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //greeting------------------------------------------------------------------------------------------------------------------------------------------------------
        Text greeting = new Text("Menu Items");
        greeting.setStyle("-fx-font-size: 22px;");
        greeting.styleProperty().bind(Bindings.concat("-fx-font-size: ", primaryStage.widthProperty().add(primaryStage.heightProperty()).divide(40), "px;"));

        // Label to display menu items----
        Label menuLabel = new Label();
    
            try {
                //URL for menu items----------------------------------------------
                URL url_menu = new URL("http://localhost:8080/menu-items/all");
    
                //open connection--------------------------------------------------
                HttpURLConnection con = (HttpURLConnection) url_menu.openConnection();
                con.setRequestMethod("GET");
    
                //show menu-----------------------------------------------------------------------------------------------------------------------------------------
                StringBuilder response = new StringBuilder();
                try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        // Parse the response and extract the required fields
                        if (inputLine.contains("item_name") && inputLine.contains("item_price") && inputLine.contains("item_description")) {
                            response.append("\n");
                            String[] parts = inputLine.split(",");
                            for (String part : parts) {
                                if (part.contains("item_name")) {
                                    String itemName = part.split(":")[1].replace("\"", "");
                                    response.append("Name: ").append(itemName).append("\n");
                                } else if (part.contains("item_price")) {
                                    String itemPrice = part.split(":")[1].replace("\"", "").replaceAll("[^\\d.]", "");
                                    response.append("Price: ").append(itemPrice).append("\n");
                                } else if (part.contains("item_description")) {
                                    String itemDescription = part.split(":")[1].replace("\"", "");
                                    response.append("Description: ").append(itemDescription).append("\n");
                                }
                            }
                        }
                    }
                }
                
    
                //response text----------------------------------------------------
                menuLabel.setText(response.toString());
            } 
            catch (IOException ex) {
                ex.printStackTrace();
                menuLabel.setText("Error: " + ex.getMessage());
            }

            //back button-------------------------------------------------------
            Button backBtn = new Button("<-- Back to Order Screen");
            backBtn.setOnAction(e -> primaryStage.setScene(orderScene));

    
        // Layout----------------------------------------------------------------------------------------------------------------------------------------------
        VBox menuLayout = new VBox(10);
        menuLayout.getChildren().addAll(greeting, menuLabel, backBtn);
        menuLayout.setPadding(new Insets(20));
        menuLayout.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        menuLayout.styleProperty().bind(Bindings.concat("-fx-font-size: ", primaryStage.widthProperty().add(primaryStage.heightProperty()).divide(80), "px;"));
        menuLayout.setAlignment(Pos.CENTER);
    
        menuScene = new Scene(menuLayout,800, 800);
    }



    private void createReservationScene() {//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        Text greeting = new Text("Please Select From the Following:");
        greeting.setStyle("-fx-font-size: 22px;");
        greeting.styleProperty().bind(Bindings.concat("-fx-font-size: ", primaryStage.widthProperty().add(primaryStage.heightProperty()).divide(40), "px;"));

        //buttons----------------------------------------------------------------------------------------------------------------------------------------------------------------
        Button addReservationBtn = new Button("Add Reservation");
        Button viewReservationBtn = new Button("View Reservation");
        Button backBtn = new Button("<-- Back to Dashboard");

        addReservationBtn.styleProperty().bind(Bindings.concat("-fx-font-size: ", primaryStage.widthProperty().add(primaryStage.heightProperty()).divide(80).add(5), "px;"));
        viewReservationBtn.styleProperty().bind(Bindings.concat("-fx-font-size: ", primaryStage.widthProperty().add(primaryStage.heightProperty()).divide(80).add(5), "px;"));
        backBtn.styleProperty().bind(Bindings.concat("-fx-font-size: ", primaryStage.widthProperty().add(primaryStage.heightProperty()).divide(80).add(5), "px;"));

        addReservationBtn.setOnAction(e -> {});
        viewReservationBtn.setOnAction(e -> {});
        backBtn.setOnAction(e -> primaryStage.setScene(dashboardScene));

        VBox buttonBox = new VBox(10);
        buttonBox.getChildren().addAll(addReservationBtn, viewReservationBtn, backBtn);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setAlignment(Pos.CENTER);

        //layout----------------------------------------------------------------------------------------------------------------
        layout = new BorderPane();
        layout.setCenter(buttonBox);
        layout.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        BorderPane.setAlignment(greeting, Pos.CENTER);
        layout.setTop(greeting);

        reservationScene = new Scene(layout, 800, 800);
    }

    private void createUserScene() {//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        Text greeting = new Text("Please Select From the Following:");
        greeting.setStyle("-fx-font-size: 22px;");
        greeting.styleProperty().bind(Bindings.concat("-fx-font-size: ", primaryStage.widthProperty().add(primaryStage.heightProperty()).divide(40), "px;"));

        //buttons----------------------------------------------------------------------------------------------------------------------------------------------------------------
        Button editUserProfileBtn = new Button("Edit User Profile");
        Button backBtn = new Button("<-- Back to Dashboard");

        editUserProfileBtn.styleProperty().bind(Bindings.concat("-fx-font-size: ", primaryStage.widthProperty().add(primaryStage.heightProperty()).divide(80).add(5), "px;"));
        backBtn.styleProperty().bind(Bindings.concat("-fx-font-size: ", primaryStage.widthProperty().add(primaryStage.heightProperty()).divide(80).add(5), "px;"));

        editUserProfileBtn.setOnAction(e -> {});
        backBtn.setOnAction(e -> primaryStage.setScene(dashboardScene));

        VBox buttonBox = new VBox(10);
        buttonBox.getChildren().addAll(editUserProfileBtn, backBtn);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setAlignment(Pos.CENTER);

        //layout----------------------------------------------------------------------------------------------------------------
        layout = new BorderPane();
        layout.setCenter(buttonBox);
        layout.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        BorderPane.setAlignment(greeting, Pos.CENTER);
        layout.setTop(greeting);

        userScene = new Scene(layout, 800, 800);
    }

    public static void main(String[] args) {
        launch(args);
    }
}