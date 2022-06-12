module com.example.sae_robots_mineur {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.testng;


    opens com.example.sae_robots_mineur to javafx.fxml;
    exports com.example.sae_robots_mineur;
    exports com.example.sae_robots_mineur_JFX;
    opens com.example.sae_robots_mineur_JFX to javafx.fxml;
}