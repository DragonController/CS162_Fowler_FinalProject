import processing.core.PApplet;

public class Human extends Particle {

    Human(int x, int y, PApplet p) {
        super(x, y, p);
        c = p.color(255, 0, 255);
    }

    void move() {
        currentAngle = (float) Math.atan2(yVelocity, xVelocity);
        targetX = p.mouseX;
        targetY = p.mouseY;
        targetAngle = (float) (Math.atan2(targetY - y, targetX - x) + Math.PI);
        xAcceleration = (float) (DENSITY_CONSTANT * Math.cos(targetAngle) / Math.pow(size, 2));
        yAcceleration = (float) (DENSITY_CONSTANT * Math.sin(targetAngle) / Math.pow(size, 2));
        xVelocity += xAcceleration;
        if (xVelocity != 0)
            xVelocity = (float) ((Math.abs(xVelocity) - DRAG_CONSTANT * Math.pow(xVelocity, 2) / size) * Math.abs(xVelocity) / xVelocity);
        yVelocity += yAcceleration;
        if (yVelocity != 0)
            yVelocity = (float) ((Math.abs(yVelocity) - DRAG_CONSTANT * Math.pow(yVelocity, 2) / size) * Math.abs(yVelocity) / yVelocity);
        x += xVelocity;
        y += yVelocity;
    }
}