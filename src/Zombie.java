import processing.core.PApplet;

public class Zombie extends Particle {
    private Particle target;

    private final float DECAY_SPEED = 1000000000;

    public Zombie(float x, float y, PApplet p, ParticleSystem particleSystem) {
        super(x, y, 10, p.color(0, 255, 0), p, particleSystem);
    }

    public Zombie(float x, float y, float xVelocity, float yVelocity, float size, PApplet p, ParticleSystem particleSystem) {
        super(x, y, xVelocity, yVelocity, size, p.color(0, 255, 0), p, particleSystem);
    }

    public void calculate() {
        target = this;
        distanceFromTarget = -1;
        for (Particle p : ParticleSystem.particles) {
            if (p instanceof Human) {
                if (distanceFromTarget == -1 || distanceFromTarget > Math.sqrt(Math.pow(x - p.x, 2) + Math.pow(y - p.y, 2))) {
                    target = p;
                    distanceFromTarget = (float) Math.sqrt(Math.pow(x - p.x, 2) + Math.pow(y - p.y, 2));
                }
            }
        }
        targetX = target.x;
        targetY = target.y;
        targetAngle = (float) Math.atan2(targetY - y, targetX - x);
        move();
        if (size - (System.nanoTime() - particleSystem.currentTime) / DECAY_SPEED > 1) {
            size -= (System.nanoTime() - particleSystem.currentTime) / DECAY_SPEED;
            c = p.color(0, 255 * ((size - 1) / (startingSize)), 0);
            if (Math.sqrt(Math.pow(x - targetX, 2) + Math.pow(y - targetY, 2)) <= (size + target.size) / 2 && target != this) {
                ParticleSystem.particles.remove(target);
                ParticleSystem.particles.add(new Zombie(targetX, targetY, target.xVelocity, target.yVelocity, target.size, p, particleSystem));
            }
        } else {
            ParticleSystem.particles.remove(this);
        }
    }
}