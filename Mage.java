/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battlearena;

import javafx.scene.paint.Color;

/**
 *
 * @author user
 */
public class Mage extends Player {

    public Mage(double x, double y, Color color) {
        super("Mage", x, y, color, "mage.png");
    }

    @Override
    public int getBaseDamage() {
        return 10;
    }
}
