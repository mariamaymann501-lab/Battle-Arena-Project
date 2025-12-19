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
public class Archer extends Player {

    public Archer(double x, double y, Color color) {
        super("Archer", x, y, color, "archer.png");
    }

    @Override
    public int getBaseDamage() {
        return 12;
    }
}
