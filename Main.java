/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package battlearena;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.util.Optional;
import javafx.animation.FadeTransition;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.application.Platform;

/**
 *
 * @author user
 */
public class Main extends Application {

    private Stage mainStage;

    @Override
    public void start(Stage stage) {
        this.mainStage = stage;

        CharacterSelectionView selection = new CharacterSelectionView();
        Scene FirstScene = selection.buildScene(this);

        mainStage.setScene(FirstScene);
        mainStage.setTitle("Battle Arena");
        mainStage.show();
    }

    public Stage getStage() {
        return mainStage;
    }

    public void startGameScene(String player1Char, String player1Weapon, String player2Char, String player2Weapon) {

        GameController controller = new GameController(player1Char, player1Weapon, player2Char, player2Weapon, this);

        Scene gameScene = controller.buildScene();
        showStartScreen(this, gameScene);
    }

    public void showEndGamePopup(int winnerPlayer) {
        Alert endGamePopup = new Alert(Alert.AlertType.NONE);
        endGamePopup.setTitle("Game Over");
        endGamePopup.setHeaderText("Player" + winnerPlayer + " WON!");
        endGamePopup.setContentText("Press Restart to play again or Exit to quit");

        ButtonType restartButton = new ButtonType("Restart");
        ButtonType exitButton = new ButtonType("Exit");

        endGamePopup.getButtonTypes().setAll(restartButton, exitButton);
        endGamePopup.showAndWait().ifPresent(response -> {
            if (response == restartButton) {

                CharacterSelectionView selection = new CharacterSelectionView();
                getStage().setScene(new CharacterSelectionView().buildScene(this));
            } else if (response == exitButton) {
                Platform.exit();
            }
        });
    }

    public void showStartScreen(Main mainApp, Scene gameScene) {

        StackPane startRoot = new StackPane();
        Image bgImage = new Image("file:src/assets/start2_background.png");
        ImageView bgView = new ImageView(bgImage);
        bgView.setFitWidth(900);
        bgView.setFitHeight(550);

        Text startText = new Text("READY!");
        startText.setFont(new Font("Arial Black", 80));
        startText.setFill(Color.LIGHTBLUE);

        startRoot.getChildren().addAll(bgView, startText);

        Scene startScene = new Scene(startRoot, 900, 550);
        mainStage.setScene(startScene);

        FadeTransition fade = new FadeTransition(Duration.seconds(1), startText);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.setCycleCount(2);
        fade.setAutoReverse(true);
        fade.setOnFinished(e -> mainApp.getStage().setScene(gameScene));
        fade.play();
    }

    public void displayAlertforselection() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Selection Required");
        alert.setHeaderText(null);
        alert.setContentText("Please select characters and weapons for both players !");
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
