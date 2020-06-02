import processing.core.PApplet;

public class Human extends Particle {

    Human(float x, float y, PApplet p, ParticleSystem particleSystem) {
        super(x, y, p.random(2, 10), p.color(255, 0, 255), p, particleSystem);
    }

    void calculate() {
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
        move();
    }
}