import processing.core.PApplet;

abstract class Particle {
    PApplet p;

    final float DENSITY_CONSTANT = (float) 1;
    final float DRAG_CONSTANT = (float) 0.01;

    float currentAngle;
    float targetX;
    float targetY;
    float targetAngle;
    float x;
    float y;
    float xVelocity;
    float yVelocity;
    float xAcceleration;
    float yAcceleration;
    int c;
    float size;

    Particle(PApplet p) {
        x = p.mouseX;
        y = p.mouseY;
        this.p=p;
        size = p.random(2, 10);
    }

    Particle(int x, int y, PApplet p) {
        this.x = x;
        this.y = y;
        this.p=p;
        size = p.random(2, 10);
    }

    void draw() {
        p.stroke(150);
        p.fill(c);
        p.ellipse(x, y, size, size);
    }

    abstract void move();
}