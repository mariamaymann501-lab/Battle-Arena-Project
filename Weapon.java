/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battlearena;

/**
 *
 * @author user
 */
public abstract class Weapon {

    private String name;
    private int damage;
    private double speed;
    private long cooldown;
    private long lastFireTime = 0;

    public Weapon(String name, int damage, double speed, long cooldown) {
        this.name = name;
        this.damage = damage;
        this.speed = speed;
        this.cooldown = cooldown;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public double getSpeed() {
        return speed;
    }

    public long getCooldown() {
        return cooldown;
    }
    

    public Projectile fire(Player owner, double angle) {
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastFireTime < cooldown) {
            return null;
        }
        lastFireTime = currentTime;
        return createProjectile(owner,angle);
    }

    protected abstract Projectile createProjectile(Player owner,double angle);
}
