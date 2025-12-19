/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battlearena;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author user
 */
public class CharacterSelectionView {

    public Scene buildScene(Main mainApp) {
        StackPane root = new StackPane();
        Image bgImage = new Image("file:src/assets/start_background.png");
        ImageView bgView = new ImageView(bgImage);
        bgView.setFitWidth(900);
        bgView.setFitHeight(550);
        root.getChildren().add(bgView);

        VBox mainBox = new VBox(30);
        mainBox.setAlignment(Pos.CENTER);

        Text Title = new Text("BATTLE ARENA");
        Title.setFont(new Font("Arial Black", 60));
        Title.setFill(Color.LIGHTGREY);

        VBox player1Box = new VBox(10);
        player1Box.setAlignment(Pos.CENTER);
        Text p1Text = new Text("Player 1");
        p1Text.setFont(new Font("Arial BLACK", 30));
        p1Text.setFill(Color.PURPLE);

        ComboBox<String> player1CharacterBox = new ComboBox<>();
        player1CharacterBox.getItems().addAll("Gunner1", "Mage1", "Archer1");
        player1CharacterBox.setPromptText("Select Character");
        player1CharacterBox.setStyle("-fs-font-family:'Arial';-fx-font-size:18px;-fx-font-weight:bold;");

        ComboBox<String> player1WeaponBox = new ComboBox<>();
        player1WeaponBox.getItems().addAll("Spear", "Bow", "Fireball");
        player1WeaponBox.setPromptText("Select Weapon");
        player1WeaponBox.setStyle("-fs-font-family:'Arial';-fx-font-size:18px;-fx-font-weight:bold;");

        Canvas p1Preview = new Canvas(60, 60);
        GraphicsContext p1Gcontext = p1Preview.getGraphicsContext2D();

        player1Box.getChildren().addAll(p1Text, player1CharacterBox, player1WeaponBox, p1Preview);
        VBox player2Box = new VBox(10);
        player2Box.setAlignment(Pos.CENTER);
        Text p2Text = new Text("Player 2");
        p2Text.setFont(new Font("Arial Black", 30));
        p2Text.setFill(Color.PURPLE);

        ComboBox<String> player2CharacterBox = new ComboBox<>();
        player2CharacterBox.getItems().addAll("Gunner2", "Mage2", "Archer2");
        player2CharacterBox.setPromptText("Select Character");
        player2CharacterBox.setStyle("-fs-font-family:'Arial';-fx-font-size:18px;-fx-font-weight:bold;");

        ComboBox<String> player2WeaponBox = new ComboBox<>();
        player2WeaponBox.getItems().addAll("Spear", "Bow", "Fireball");
        player2WeaponBox.setPromptText("Select Weapon");
        player2WeaponBox.setStyle("-fs-font-family:'Arial';-fx-font-size:18px;-fx-font-weight:bold;");

        Canvas p2Preview = new Canvas(60, 60);
        GraphicsContext p2Gcontext = p2Preview.getGraphicsContext2D();

        player2Box.getChildren().addAll(p2Text, player2CharacterBox, player2WeaponBox, p2Preview);

        HBox selectionBox = new HBox(50, player1Box, player2Box);
        selectionBox.setAlignment(Pos.CENTER);
        Button startButton = new Button("Start Game");
        startButton.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        startButton.setOnAction(e -> {
            String p1Character = player1CharacterBox.getValue();
            String p1Weapon = player1WeaponBox.getValue();
            String p2Character = player2CharacterBox.getValue();
            String p2Weapon = player2WeaponBox.getValue();
            if (p1Character == null || p1Weapon == null || p2Character == null || p2Weapon == null) {
                mainApp.displayAlertforselection();
            } else {
                mainApp.startGameScene(p1Character, p1Weapon, p2Character, p2Weapon);
            }
        });

        mainBox.getChildren().addAll(Title, selectionBox, startButton);
        root.getChildren().add(mainBox);
        return new Scene(root, 900, 550);
    }
}
