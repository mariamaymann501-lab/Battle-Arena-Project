/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battlearena;

/**
 *
 * @author user
 */
import javafx.scene.paint.Color;

public class Spear extends Weapon {

    public Spear() {
        super("Spear", 18, 15.0, 800);
    }

    @Override
    protected Projectile createProjectile(Player owner,double angle) {
                return new Projectile(
                owner,
                getDamage(),
                getSpeed(),
                7,
                Color.PURPLE,
               angle
        );
    }
}
