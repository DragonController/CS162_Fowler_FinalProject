import processing.core.PApplet;

public class Sketch extends PApplet {
    ParticleSystem particleSystem;

    boolean middleMouseButton = false;

    public void settings() {
        size(500,500);
    }

    public void setup() {
        particleSystem = new ParticleSystem(this);
    }

    public void draw() {
        background(255);
        if (middleMouseButton)
            particleSystem.addParticle(new Zombie(mouseX, mouseY, 0, 0, 0, this, particleSystem));
        particleSystem.draw();
        particleSystem.update();
    }

    public void mousePressed() {
        if (mouseButton == LEFT) {
            particleSystem.addParticle(new Zombie(mouseX, mouseY,this, particleSystem));
        }
        if (mouseButton == RIGHT) {
            particleSystem.addParticle(new Human(mouseX, mouseY,this, particleSystem));
        }
        if (mouseButton == CENTER) {
            middleMouseButton = true;
        }
    }

    public void mouseReleased() {
        if (mouseButton == CENTER) {
            middleMouseButton = false;
        }
    }

    public void keyPressed() {
        if (key == 'r') {
            particleSystem.resetParticles();
        }
        if (key == ' ') {
            particleSystem.deleteAllParticles();
        }
    }
}