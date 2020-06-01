import processing.core.PApplet;

public class Human extends Particle {

    Human(float x, float y, PApplet p, ParticleSystem particleSystem) {
        super(x, y, p.random(2, 10), p.color(255, 0, 255), p, particleSystem);
    }

    void move(int millis) {
        currentAngle = (float) Math.atan2(yVelocity, xVelocity);
        targetX = this.x;
        targetY = this.y;
        for (Particle p : ParticleSystem.particles) {
            if (p instanceof Zombie) {
                distanceFromTarget = (float) (Math.pow(x - p.x, 2) + Math.pow(y - p.y, 2));
                if (distanceFromTarget != 0) {
                    targetX += (p.x - x) / distanceFromTarget;
                    targetY += (p.y - y) / distanceFromTarget;
                }
            }
        }
        targetAngle = (float) (Math.atan2(targetY - y, targetX - x) + Math.PI);
        if (targetX != x || targetY != y) {
            xAcceleration = (float) (DENSITY_CONSTANT * Math.cos(targetAngle) / Math.pow(size, 2));
            yAcceleration = (float) (DENSITY_CONSTANT * Math.sin(targetAngle) / Math.pow(size, 2));
        } else {
            xAcceleration = 0;
            yAcceleration = 0;
        }
        xVelocity += xAcceleration;
        if (xVelocity != 0)
            xVelocity = (float) ((Math.abs(xVelocity) - DRAG_CONSTANT * Math.pow(xVelocity, 2) / size) * Math.abs(xVelocity) / xVelocity);
        yVelocity += yAcceleration;
        if (yVelocity != 0)
            yVelocity = (float) ((Math.abs(yVelocity) - DRAG_CONSTANT * Math.pow(yVelocity, 2) / size) * Math.abs(yVelocity) / yVelocity);
        x += xVelocity;
        y += yVelocity;
        if (x < 0) {
            x = 0;
            xVelocity *= -1;
        }
        if (x > p.width) {
            x = p.width;
            xVelocity *= -1;
        }
        if (y < 0) {
            y = 0;
            yVelocity *= -1;
        }
        if (y > p.height) {
            y = p.height;
            yVelocity *= -1;
        }
    }
}