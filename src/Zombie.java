import processing.core.PApplet;

public class Zombie extends Particle {
    private Particle target;

    private final float DECAY_SPEED = 1000000000;

    public Zombie(float x, float y, PApplet p, ParticleSystem particleSystem) {
        super(x, y, p.color(0, 255, 0), p, particleSystem);
    }

    public Zombie(float x, float y, float xVelocity, float yVelocity, float size, PApplet p, ParticleSystem particleSystem) {
        super(x, y, xVelocity, yVelocity, size, p.color(0, 255, 0), p, particleSystem);
    }

    public void calculate() {
        target = this;
        setDistanceFromTarget(-1);
        for (Particle p : ParticleSystem.particles) {
            if (p instanceof Human) {
                if (getDistanceFromTarget() == -1 || getDistanceFromTarget() > Math.sqrt(Math.pow(getX() - p.getX(), 2) + Math.pow(getY() - p.getY(), 2))) {
                    target = p;
                    setDistanceFromTarget((float) Math.sqrt(Math.pow(getX() - p.getX(), 2) + Math.pow(getY() - p.getY(), 2)));
                }
            }
        }
        setTargetX(target.getX());
        setTargetY(target.getY());
        setTargetAngle((float) Math.atan2(getTargetY() - getY(), getTargetX() - getX()));
        move();
        if (getSize() - (System.nanoTime() - getParticleSystem().getCurrentTime()) / DECAY_SPEED > 1) {
            setSize(getSize() - (System.nanoTime() - getParticleSystem().getCurrentTime()) / DECAY_SPEED);
            setC(getP().color(0, 255 * ((getSize() - 1) / (getStartingSize())), 0));
            if (Math.sqrt(Math.pow(getX() - getTargetX(), 2) + Math.pow(getY() - getTargetY(), 2)) <= (getSize() + target.getSize()) / 2 && target != this) {
                ParticleSystem.particles.remove(target);
                ParticleSystem.particles.add(new Zombie(getTargetX(), getTargetY(), target.getXVelocity(), target.getYVelocity(), target.getSize(), getP(), getParticleSystem()));
            }
        } else {
            ParticleSystem.particles.remove(this);
        }
    }
}