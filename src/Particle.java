import processing.core.PApplet;

abstract class Particle {
    PApplet p;
    ParticleSystem particleSystem;

    final float DENSITY_CONSTANT = (float) 1;
    final float DRAG_CONSTANT = (float) 0.01;

    float currentAngle;
    float targetX;
    float targetY;
    float targetAngle;
    float distanceFromTarget;
    float x;
    float y;
    float xVelocity;
    float yVelocity;
    float xAcceleration;
    float yAcceleration;
    int c;
    float size;
    float startingSize;

    Particle(float x, float y, int c, PApplet p, ParticleSystem particleSystem) {
        this.x = x;
        this.y = y;
        this.c = c;
        size = p.random(2, 10);
        startingSize = size;
        this.p = p;
        this.particleSystem=particleSystem;
    }

    Particle(float x, float y, float xVelocity, float yVelocity, float size, int c, PApplet p, ParticleSystem particleSystem) {
        this.x = x;
        this.y = y;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.size = size;
        startingSize = size;
        this.c = c;
        this.p = p;
        this.particleSystem=particleSystem;
    }

    void draw() {
        p.fill(c);
        p.ellipse(x, y, size, size);
    }

    abstract void move();
}