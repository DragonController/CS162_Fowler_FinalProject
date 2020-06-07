import processing.core.PApplet;

public class Human extends Particle {
    public Human(float x, float y, PApplet p, ParticleSystem particleSystem) {
        super(x, y, p.random(2, 10), p.color(255, 0, 255), p, particleSystem);
    }

    public void calculate() {
        setTargetX(this.getX());
        setTargetY(this.getY());
        for (Particle p : ParticleSystem.particles) {
            if (p instanceof Zombie) {
                setDistanceFromTarget((float) (Math.pow(getX() - p.getX(), 2) + Math.pow(getY() - p.getY(), 2)));
                if (getDistanceFromTarget() != 0) {
                    setTargetX(getTargetX() + (p.getX() - getX()) / getDistanceFromTarget());
                    setTargetY(getTargetY() + (p.getY() - getY()) / getDistanceFromTarget());
                }
            }
        }
        setTargetAngle((float) (Math.atan2(getTargetY() - getY(), getTargetX() - getX()) + Math.PI));
        move();
    }
}