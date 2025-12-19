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
public class Bow extends Weapon {
    public Bow() {
    super("Bow", 10, 10.0, 400);
    }

    @Override
    protected Projectile createProjectile(Player owner,double angle) {
        return new Projectile(
                owner,
                getDamage(),
                getSpeed(),
                5,
                Color.LIGHTBLUE,
                angle
        );
    }
}
