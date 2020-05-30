import processing.core.PApplet;

public class Sketch extends PApplet {
    ParticleSystem particleSystem;

    public void settings() {
        size(500,500);
    }

    public void setup() {
        particleSystem = new ParticleSystem(this);
    }

    public void draw() {
        background(255);
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
            particleSystem.addParticle(new Zombie(mouseX, mouseY, 0, 0, 1, this, particleSystem));
        }
    }

    public void keyPressed() {
        if (key == 'r') {
            particleSystem.deleteAllParticles();
            particleSystem = new ParticleSystem(this);
        }
        if (key == ' ') {
            particleSystem.deleteAllParticles();
        }
    }
}