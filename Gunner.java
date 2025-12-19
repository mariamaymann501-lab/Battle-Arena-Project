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
public class Gunner extends Player {

    public Gunner(double x, double y, Color color) {
        super("Warrior", x, y, color, "gunner.png");
    }

    @Override
    public int getBaseDamage() {
        return 15;
    }
}
