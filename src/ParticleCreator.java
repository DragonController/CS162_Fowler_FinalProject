import processing.core.PApplet;

public class ParticleCreator extends PApplet {
    ParticleSystem particleSystem;

    public void settings() {
        size(500,500);
    }

    public void setup() {
        particleSystem = new ParticleSystem(this);
    }

    public void draw() {
        particleSystem.draw();
    }
}