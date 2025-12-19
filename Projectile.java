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
public class Projectile {

    private double x;
    private double y;
    private int damage;
    private double speed;
    private Player owner;
    private double radius;
    private Color color;
    private double angle;

    public Projectile(Player owner, int damage, double speed, double radius, Color color, double angle) {
        this.owner = owner;
        this.damage = damage;
        this.speed = speed;
        this.radius = radius;
        this.color = color;
        
        this.x = owner.getPosX() + owner.getWidth() / 2;
        this.y = owner.getPosY() + owner.getHeight() / 2;
       this.angle = angle;
    }

    public int getDamage() {
        return damage;
    }

    public double getSpeed() {
        return speed;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRadius() {
        return radius;
    }

    public Color getColor() {
        return color;
    }

    public Player getOwner() {
        return owner;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }
    

    public void move() {
        x += speed * Math.cos(angle);
        y+=speed* Math.sin(angle);
    }
}
