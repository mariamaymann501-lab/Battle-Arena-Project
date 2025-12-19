/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java 
 */
package battlearena;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import static javafx.scene.input.KeyCode.R;
import static javafx.scene.input.KeyCode.U;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author user
 */
public class GameController {

    private Main mainApp;
    private Player player1;
    private Player player2;
    private List<Projectile> activeProjectiles = new ArrayList<>();
    private String p1Character, p1Weapon;
    private String p2Character, p2Weapon;
    private boolean gameOver = false;

    private Image backgroundImage;

    private ComboBox<String> p1WeaponBox;
    private ComboBox<String> p2WeaponBox;

    public GameController(String p1Character, String p1Weapon, String p2Character, String p2Weapon, Main mainApp) {
        this.mainApp = mainApp;
        this.p1Character = p1Character;
        this.p1Weapon = p1Weapon;
        this.p2Character = p2Character;
        this.p2Weapon = p2Weapon;

        player1 = createPlayer(p1Character, 100.0, 255.0, Color.BLUE);
        player2 = createPlayer(p2Character, 760.0, 255.0, Color.RED);

        player1.setWeapon(createWeapon(p1Weapon));
        player2.setWeapon(createWeapon(p2Weapon));

        player1.setBounds(0, 860, 0, 510);
        player2.setBounds(0, 860, 0, 510);

        try {
            this.backgroundImage = new Image("file:src/resources/arena.jpeg");
        } catch (Exception e) {
            System.out.println("Could not find arena.png in resources folder");
        }
    }

    public Scene buildScene() {

        BorderPane arenaLayout = new BorderPane();
        Canvas arenaCanvas = new Canvas(900, 550);

        GraphicsContext painter = arenaCanvas.getGraphicsContext2D();
        arenaLayout.setCenter(arenaCanvas);

        HBox weaponBox = new HBox(50);
        weaponBox.setAlignment(Pos.TOP_CENTER);

        p1WeaponBox = new ComboBox<>();
        p1WeaponBox.getItems().addAll("Spear", "Bow", "Fireball");
        p1WeaponBox.setPromptText("Select new Weapon");
        p1WeaponBox.setStyle("-fs-font-family:'Arial';-fx-font-size:18px;-fx-font-weight:bold;");

        p1WeaponBox.setVisible(false);
        p1WeaponBox.setOnAction(e -> {
            player1.switchWeapon(createWeapon(p1WeaponBox.getValue()));
            p1WeaponBox.setVisible(false);
        });

        p2WeaponBox = new ComboBox<>();
        p2WeaponBox.getItems().addAll("Spear", "Bow", "Fireball");
        p2WeaponBox.setPromptText("Select new Weapon");
        p2WeaponBox.setStyle("-fs-font-family:'Arial';-fx-font-size:18px;-fx-font-weight:bold;");

        p2WeaponBox.setVisible(false);
        p2WeaponBox.setOnAction(e -> {
            player2.switchWeapon(createWeapon(p2WeaponBox.getValue()));
            p2WeaponBox.setVisible(false);
        });

        weaponBox.getChildren().addAll(p1WeaponBox, p2WeaponBox);
        arenaLayout.setTop(weaponBox);

        startGameLoop(painter);
        Scene scene = new Scene(arenaLayout, 900, 550);

        scene.setOnKeyPressed(e -> {
            KeyCode code = e.getCode();
            switch (code) {
                case W ->
                    player1.up = true;
                case S ->
                    player1.down = true;
                case A -> {
                    player1.left = true;
                    player1.setFacingDirection(-1);
                }
                case D -> {
                    player1.right = true;
                    player1.setFacingDirection(1);
                }
                case UP ->
                    player2.up = true;
                case DOWN ->
                    player2.down = true;
                case LEFT -> {
                    player2.left = true;
                    player2.setFacingDirection(-1);
                }
                case RIGHT -> {
                    player2.right = true;
                    player1.setFacingDirection(1);
                }
                case F -> {
                     double angle = 0;
                    if(player1.up)angle = -Math.PI/2;
                    else if(player1.down)angle = Math.PI/2;
                    else if(player1.left)angle = Math.PI;  
                    else if(player1.right)angle = 0;
                     
                     Projectile p = player1.getWeapon().fire(player1, angle);
                            if (p!= null){
                                 activeProjectiles.add(p);
                            }
                    
                }
                     case L ->  {
                     double angle = 0;
                    if(player2.up)angle = -Math.PI/2;
                    else if(player2.down)angle = Math.PI/2;
                    else if(player2.left)angle = Math.PI;  
                    else if(player2.right)angle = 0;
                     
                     Projectile p = player2.getWeapon().fire(player2, angle);
                            if (p!= null){
                                 activeProjectiles.add(p);
                     }
                    
                }
                case U ->
                    p2WeaponBox.setVisible(true);
                case R ->
                    p1WeaponBox.setVisible(true);
            }
        });

        scene.setOnKeyReleased(e -> {
            KeyCode code = e.getCode();
            switch (code) {
                case W ->
                    player1.up = false;
                case S ->
                    player1.down = false;
                case A ->
                    player1.left = false;
                case D ->
                    player1.right = false;

                case UP ->
                    player2.up = false;
                case DOWN ->
                    player2.down = false;
                case LEFT ->
                    player2.left = false;
                case RIGHT ->
                    player2.right = false;

            }
        });
        return scene;
    }

    private Player createPlayer(String character, double x, double y, Color color) {
        Player p = switch (character) {
            case "Gunner" ->
                new Gunner(x, y, color);
            case "Mage" ->
                new Mage(x, y, color);
            case "Archer" ->
                new Archer(x, y, color);
            default ->
                new Gunner(x, y, color);
        };
        switch (character) {
            case "Gunner" ->
                p.setImagePath("gunner.png");
            case "Mage" ->
                p.setImagePath("mage.png");
            case "Archer" ->
                p.setImagePath("Archer.png");
        }
        return p;
    }

    private Weapon createWeapon(String weapon) {
        return switch (weapon) {
            case "Spear" ->
                new Spear();
            case "Bow" ->
                new Bow();
            case "Fireball" ->
                new Fireball();
            default ->
                new Spear();
        };
    }

    private void drawArena(GraphicsContext painter) {

        if (backgroundImage != null && !backgroundImage.isError()) {
            painter.drawImage(backgroundImage, 0, 0, 900, 550);
        } else {
            painter.setFill(Color.LIGHTGRAY);
            painter.fillRect(0, 0, 900, 550);
        }
        painter.setStroke(Color.PURPLE);
        painter.setLineWidth(3.5);
        painter.strokeLine(450, 0, 450, 550);
    }

    private void startGameLoop(GraphicsContext gc) {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!gameOver) {
                    updatePlayers();
                    updateProjectiles();
                    renderPlayers(gc);
                }
            }
        }.start();
    }

    private void updatePlayers() {
        player1.update();
        player2.update();
    }

    private void updateProjectiles() {
        List<Projectile> toRemove = new ArrayList<>();

        for (Projectile p : activeProjectiles) {
            p.move();

            if (p.getOwner() != player1 && intersects(p, player1)) {
                player1.takeDamage(p.getDamage());
                toRemove.add(p);
            }
            if (p.getOwner() != player2 && intersects(p, player2)) {
                player2.takeDamage(p.getDamage());
                toRemove.add(p);
            }

            if (p.getX() < 0 || p.getX() > 900) {
                toRemove.add(p);
            }
        }
        activeProjectiles.removeAll(toRemove);
        if (player1.getCurrentHealth() <= 0) {
            gameOver = true;
            activeProjectiles.clear();
            Platform.runLater(() -> mainApp.showEndGamePopup(2));
        } else if (player2.getCurrentHealth() <= 0) {
            gameOver = true;
            activeProjectiles.clear();
            Platform.runLater(() -> mainApp.showEndGamePopup(1));
        }
    }

    private boolean intersects(Projectile p, Player p1) {
        double px = p1.getPosX();
        double py = p1.getPosY();
        double pw = p1.getWidth();
        double ph = p1.getHeight();
        double cx = p.getX();
        double cy = p.getY();
        double r = p.getRadius();
        return (cx + r > px && cx - r < px + pw)
                && (cy + r > py && cy - r < py + ph);
    }

    private void renderPlayers(GraphicsContext gc) {
        gc.clearRect(0, 0, 900, 500);
        drawArena(gc);

        PlayerView pv1 = new PlayerView(player1, player1.getImagePath());
        PlayerView pv2 = new PlayerView(player2, player2.getImagePath());
        pv1.render(gc);
        pv2.render(gc);

        player1.renderHealthBar(gc, 30, 25, 220, 20);
        player2.renderHealthBar(gc, 650, 25, 220, 20);

        for (Projectile p : activeProjectiles) {
            gc.setFill(p.getColor());
            gc.fillOval(
                    p.getX() - p.getRadius(),
                    p.getY() - p.getRadius(),
                    p.getRadius() * 2,
                    p.getRadius() * 2);
        }
    }
}
