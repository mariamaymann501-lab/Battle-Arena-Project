package battlearena;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/*
 */
public abstract class Player {

    private String name;
    private String imagePath;
    private int currentHealth;
    private final int maxHealth = 100;

    private double posX;
    private double posY;

    private final double width = 40;
    private final double height = 40;

    public boolean up, down, left, right;
    private double speed = 5.0;

    private double minX, maxX, minY, maxY;

    private Color playerColor;

    private Weapon weapon;
    private int facingDirection = 1;

    public Player(String name, double x, double y, Color color, String imagePath) {
        this.name = name;
        this.posX = x;
        this.posY = y;
        this.playerColor = color;
        this.imagePath = imagePath;
        this.currentHealth = maxHealth;
    }

    public abstract int getBaseDamage();

    public void update() {
        double newX = posX;
        double newY = posY;

        if (up) {
            newY -= speed;
        }
        if (down) {
            newY += speed;
        }
        if (left) {
            newX -= speed;
            facingDirection = -1;
        }
        if (right) {
            newX += speed;
            facingDirection = 1;
        }

        if (newX < minX) {
            newX = minX;
        }
        if (newX > maxX) {
            newX = maxX;
        }
        if (newY < minY) {
            newY = minY;
        }
        if (newY > maxY) {
            newY = maxY;
        }

        posX = newX;
        posY = newY;
    }

    public void render(GraphicsContext gc) {
        gc.setFill(playerColor);
        gc.fillRect(posX, posY, width, height);
    }

    public void renderHealthBar(GraphicsContext gc, double x, double y, double w, double h) {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeRect(x, y, w, h);
        double healthPercent = Math.max(0, (double) currentHealth / maxHealth);
        //change color based on health
        if (healthPercent > 0.6) {
            gc.setFill(Color.GREEN);
        } else if (healthPercent > 0.3) {
            gc.setFill(Color.GOLD);
        } else {
            gc.setFill(Color.RED);
        }
        gc.fillRect(x, y, w * healthPercent, h);
        gc.setFill(Color.BLACK);
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        String healthText = currentHealth + "/" + maxHealth;
        double textX = x + w / 2 - 25;
        double textY = y + h / 2 + 5;
        gc.fillText(healthText, textX, textY);
    }

    public void takeDamage(int damage) {
        currentHealth -= damage;
        if (currentHealth < 0) {
            currentHealth = 0;
        }
    }

    public void setBounds(double minX, double maxX, double minY, double maxY) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void switchWeapon(Weapon newWeapon) {
        this.weapon = newWeapon;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public String getName() {
        return name;
    }

    public int getFacingDirection() {
        return facingDirection;
    }

    public void setFacingDirection(int direction) {
        this.facingDirection = direction;
    }

    public void setImagePath(String Path) {
        this.imagePath = Path;
    }

    public String getImagePath() {
        return imagePath;
    }
}
