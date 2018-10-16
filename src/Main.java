import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage main) {
        BorderPane bp = new BorderPane();
        bp.setTop(setMenuBar());

        main.setScene(new Scene(bp));
        main.setResizable(true);
        main.setTitle("Laboratoire 6");
        main.setMaximized(true);
        main.show();
    }

    private MenuBar setMenuBar() {
        Menu fichiers = new Menu("Fichiers");
        Menu actions = new Menu("Actions");
        Menu images = new Menu("Charger une image");

        CheckMenuItem image1 = new CheckMenuItem("Image #1");
        image1.setOnAction(event -> {});

        CheckMenuItem image2 = new CheckMenuItem("Image #2");
        image2.setOnAction(event -> {});

        CheckMenuItem image3 = new CheckMenuItem("Image #3");
        image3.setOnAction(event -> {});

        CheckMenuItem reinitialiser = new CheckMenuItem("Réinitialiser");
        reinitialiser.setOnAction(event -> {});

        fichiers.getItems().add(images);
        images.getItems().addAll(image1, image2, image3);
        actions.getItems().add(reinitialiser);

        return new MenuBar(fichiers, actions);
    }
}
