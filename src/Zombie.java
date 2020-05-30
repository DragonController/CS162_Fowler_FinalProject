import processing.core.PApplet;

public class Zombie extends Particle {

    Particle target;

    float time = 0;

    Zombie(float x, float y, PApplet p, ParticleSystem particleSystem) {
        super(x, y, p.color(0, 255, 0), p, particleSystem);
    }

    Zombie(float x, float y, float xVelocity, float yVelocity, float size, PApplet p, ParticleSystem particleSystem) {
        super(x, y, xVelocity, yVelocity, size, p.color(0, 255, 0), p, particleSystem);
    }

    void move() {
        target = this;
        distanceFromTarget = -1;
        for (Particle particle : ParticleSystem.particles) {
            if (particle instanceof Human) {
                if (distanceFromTarget == -1 || distanceFromTarget > Math.sqrt(Math.pow(x - particle.x, 2) + Math.pow(y - particle.y, 2))) {
                    target = (Human) particle;
                    distanceFromTarget = (float) Math.sqrt(Math.pow(x - particle.x, 2) + Math.pow(y - particle.y, 2));
                }
            }
        }
        currentAngle = (float) Math.atan2(yVelocity, xVelocity);
        targetX = target.x;
        targetY = target.y;
        targetAngle = (float) Math.atan2(targetY - y, targetX - x);
        if (targetX != x || targetY != y) {
            xAcceleration = (float) (DENSITY_CONSTANT * Math.cos(targetAngle) / Math.pow(size, 2) - xVelocity * DRAG_CONSTANT);
            yAcceleration = (float) (DENSITY_CONSTANT * Math.sin(targetAngle) / Math.pow(size, 2) - yVelocity  * DRAG_CONSTANT);
        } else {
            xAcceleration = 0;
            yAcceleration = 0;
        }
        xVelocity += xAcceleration / 2;
        if (xVelocity != 0)
            xVelocity = (float) ((Math.abs(xVelocity) - DRAG_CONSTANT * Math.pow(xVelocity, 2) / size) * Math.abs(xVelocity) / xVelocity);
        yVelocity += yAcceleration / 2;
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
        time += p.millis();
        if (time >= 1000000) {
            if (size - time / 1000000 > 1) {
                size -= time / 1000000;
            } else {
                ParticleSystem.particles.remove(this);
            }
            time = 0;
        }
        if (Math.sqrt(Math.pow(x - targetX, 2) + Math.pow(y - targetY, 2)) < (size + target.size) / 2 && target != this) {
            ParticleSystem.particles.remove(target);
            ParticleSystem.particles.add(new Zombie(targetX, targetY, target.xVelocity, target.yVelocity, target.size, p, particleSystem));
        }
    }
}