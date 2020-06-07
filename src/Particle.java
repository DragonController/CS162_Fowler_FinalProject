import processing.core.PApplet;

abstract class Particle {
    PApplet p;
    ParticleSystem particleSystem;

    private final float DENSITY_CONSTANT = 1;
    private final float DRAG_CONSTANT = (float) 0.01;

    private float currentAngle;
    private float targetX;
    private float targetY;
    private float targetAngle;
    private float distanceFromTarget;
    private float x;
    private float y;
    private float xVelocity;
    private float yVelocity;
    private float xAcceleration;
    private float yAcceleration;
    private int c;
    private float size;
    private final float startingSize;

    public Particle(float x, float y, float size, int c, PApplet p, ParticleSystem particleSystem) {
        this.x = x;
        this.y = y;
        this.c = c;
        this.size = size;
        startingSize = size;
        this.p = p;
        this.particleSystem=particleSystem;
    }

    public Particle(float x, float y, float xVelocity, float yVelocity, float size, int c, PApplet p, ParticleSystem particleSystem) {
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

    public void draw() {
        p.fill(c);
        p.ellipse(x, y, size, size);
    }

    public abstract void calculate();

    public void move() {
        currentAngle = (float) Math.atan2(yVelocity, xVelocity);
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
    }

    public float getTargetX() {
        return targetX;
    }

    public void setTargetX(float targetX) {
        this.targetX = targetX;
    }

    public float getTargetY() {
        return targetY;
    }

    public void setTargetY(float targetY) {
        this.targetY = targetY;
    }

    public void setTargetAngle(float targetAngle) {
        this.targetAngle = targetAngle;
    }

    public float getDistanceFromTarget() {
        return distanceFromTarget;
    }

    public void setDistanceFromTarget(float distanceFromTarget) {
        this.distanceFromTarget = distanceFromTarget;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getXVelocity() {
        return xVelocity;
    }

    public float getYVelocity() {
        return yVelocity;
    }

    public void setC(int c) {
        this.c = c;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public float getStartingSize() {
        return startingSize;
    }
}