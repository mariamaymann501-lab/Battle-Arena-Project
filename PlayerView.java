/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battlearena;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author user
 */
public class PlayerView {

    private Player player;
    private Image playerImage;

    public PlayerView(Player player, String imagePath) {
        this.player = player;
        this.playerImage = new Image("file:src/assets/" + imagePath);
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(playerImage, player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight());
    }

}
