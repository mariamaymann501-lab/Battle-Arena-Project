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
public class Fireball extends Weapon {

    public Fireball() {
        super("Fireball", 20, 8, 700);
    }

    @Override
    protected Projectile createProjectile(Player owner,double angle) {
        return new Projectile(
                owner,
                getDamage(),
                getSpeed(),
                8,
                Color.ORANGE,
                angle
        );
    }
}
