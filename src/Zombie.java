import processing.core.PApplet;

public class Zombie extends Particle {

    float time = 0;

    Zombie(int x, int y, PApplet p) {
        super(x, y, p);
        c = p.color(0, 255, 0);
    }

    void move() {
        currentAngle = (float) Math.atan2(yVelocity, xVelocity);
        targetX = p.mouseX;
        targetY = p.mouseY;
        targetAngle = (float) Math.atan2(targetY - y, targetX - x);
        xAcceleration = (float) (DENSITY_CONSTANT * Math.cos(targetAngle) / Math.pow(size, 2) - xVelocity * DRAG_CONSTANT);
        yAcceleration = (float) (DENSITY_CONSTANT * Math.sin(targetAngle) / Math.pow(size, 2) - yVelocity  * DRAG_CONSTANT);
        xVelocity += xAcceleration;
        if (xVelocity != 0)
            xVelocity = (float) ((Math.abs(xVelocity) - DRAG_CONSTANT * Math.pow(xVelocity, 2) / size) * Math.abs(xVelocity) / xVelocity);
        yVelocity += yAcceleration;
        if (yVelocity != 0)
            yVelocity = (float) ((Math.abs(yVelocity) - DRAG_CONSTANT * Math.pow(yVelocity, 2) / size) * Math.abs(yVelocity) / yVelocity);
        x += xVelocity;
        y += yVelocity;
        time += p.millis();
        if (time >= 1000000) {
            time = 0;
            size--;
        }
    }
}