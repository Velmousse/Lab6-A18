import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {
    private Label information;
    private Label lLuminosite, lContraste, lTeinte, lSaturation;

    private HBox retour;

    private ColorAdjust colorAdjust = new ColorAdjust();

    private Slider sLuminosite, sContraste, sTeinte, sSaturation;

    private Image image = new Image("file:default.jpg"),
            image1 = new Image("file:image1.jpg"),
            image2 = new Image("file:image2.jpg"),
            image3 = new Image("file:image3.jpg");

    private ImageView imageView;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage main) {
        imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(500);

        BorderPane bp = new BorderPane();

        Rectangle rect = new Rectangle(0, 0, Screen.getPrimary().getBounds().getWidth(), 30); //À faire fonctionner
        rect.setFill(Color.LIGHTGRAY);

        information = new Label("Bienvenue dans le modificateur d'images!");

        StackPane sp = new StackPane(rect, information);
        sp.setPadding(new Insets(5));
        sp.setAlignment(Pos.CENTER_LEFT);

        bp.setTop(setMenuBar());
        bp.setCenter(setCenter());
        bp.setBottom(sp);

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
        image1.setOnAction(event -> setImage(1));

        CheckMenuItem image2 = new CheckMenuItem("Image #2");
        image2.setOnAction(event -> setImage(2));

        CheckMenuItem image3 = new CheckMenuItem("Image #3");
        image3.setOnAction(event -> setImage(3));

        CheckMenuItem reinitialiser = new CheckMenuItem("Réinitialiser");
        reinitialiser.setOnAction(event -> reset());

        fichiers.getItems().add(images);
        images.getItems().addAll(image1, image2, image3);
        actions.getItems().add(reinitialiser);

        return new MenuBar(fichiers, actions);
    }

    private void setImage(int value) {
        switch (value) {
            case 1:
                imageView.setImage(image1);
                information.setText("Image #1 chargée");
                break;

            case 2:
                imageView.setImage(image2);
                information.setText("Image #2 chargée");
                break;

            case 3:
                imageView.setImage(image3);
                information.setText("Image #3 chargée");
        }

        retour.setPadding(new Insets(imageView.getFitHeight() + 175, imageView.getFitHeight() + 60 , imageView.getFitHeight() + 175, imageView.getFitHeight() + 60));
    }

    private HBox setCenter() {
        lLuminosite = new Label("Luminosité");
        lContraste = new Label("Contraste");
        lTeinte = new Label("Teinte");
        lSaturation = new Label("Saturation");

        Tooltip tLuminosite = new Tooltip("Rend l'image plus claire ou plus sombre"),
                tContraste = new Tooltip("Diminue ou augmente la différence entre les couleurs"),
                tTeinte = new Tooltip("Change la teinte (couleur) de l'image"),
                tSaturation = new Tooltip("Diminue ou augmente l'intensité des couleurs");

        sLuminosite = new Slider(-1, 1, 0);
        sLuminosite.setOnMouseDragged(event -> colorAdjust.setBrightness(sLuminosite.getValue()));
        sLuminosite.setTooltip(tLuminosite);

        sContraste = new Slider(-1, 1, 0);
        sContraste.setOnMouseDragged(event -> colorAdjust.setContrast(sContraste.getValue()));
        sContraste.setTooltip(tContraste);

        sTeinte = new Slider(-1, 1, 0);
        sTeinte.setOnMouseDragged(event -> colorAdjust.setHue(sTeinte.getValue()));
        sTeinte.setTooltip(tTeinte);

        sSaturation = new Slider(-1, 1, 0);
        sSaturation.setOnMouseDragged(event -> colorAdjust.setSaturation(sSaturation.getValue()));
        sSaturation.setTooltip(tSaturation);

        imageView.setEffect(colorAdjust);

        VBox sliders = new VBox(lLuminosite, sLuminosite, lContraste, sContraste, lTeinte, sTeinte, lSaturation, sSaturation);
        sliders.setSpacing(10);
        sliders.setAlignment(Pos.CENTER);

        retour = new HBox(imageView, sliders);
        retour.setPadding(new Insets(imageView.getFitHeight() + 175, imageView.getFitHeight() + 60 , imageView.getFitHeight() + 175, imageView.getFitHeight() + 60));
        retour.setSpacing(10);
        retour.setAlignment(Pos.CENTER);

        return retour;
    }

    private void reset() {
        information.setText("Réinitialisation des ajustements de couleurs");

        colorAdjust.setBrightness(0);
        colorAdjust.setContrast(0);
        colorAdjust.setHue(0);
        colorAdjust.setSaturation(0);

        sLuminosite.setValue(0);
        sContraste.setValue(0);
        sTeinte.setValue(0);
        sSaturation.setValue(0);
    }
}
