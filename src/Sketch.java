import processing.core.PApplet;

public class Sketch extends PApplet {
    private ParticleSystem particleSystem;

    private boolean middleMouseButton = false;

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
        textSize(12);
        fill(0);
        text("Humans: " + particleSystem.getHumans(), width / 2 - textWidth("Humans: " + particleSystem.getHumans()) / 2, 20);
        text("Zombies: " + particleSystem.getZombies(), width / 2 - textWidth("Zombies: " + particleSystem.getZombies()) / 2, height - 10);
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
            particleSystem.deleteAllParticles();
            particleSystem.createParticles();
        }
        if (key == ' ') {
            particleSystem.deleteAllParticles();
        }
    }
}