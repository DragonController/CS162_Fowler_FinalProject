import processing.core.PApplet;

import java.util.ArrayList;

class ParticleSystem {
    static ArrayList<Particle> particles;
    static int i;
    final int NUMBER_OF_HUMANS = 100;
    int startingX;
    int startingY;

    ParticleSystem(PApplet p) {
        particles = new ArrayList<Particle>();
        startingX = p.width / 2;
        startingY = p.height / 2;
        particles.add(new Zombie(startingX, startingY, p, this));
        for (int i = 0; i < NUMBER_OF_HUMANS; ++i) {
            int humanX = (int) p.random(0, p.width);
            int humanY = (int) p.random(0, p.height);
            double distance = Math.min(p.width, p.height) / 4.0;
            while (Math.sqrt(Math.pow(humanX - startingX, 2) + Math.pow(humanY - startingY, 2)) < distance) {
                humanX = (int) p.random(0, p.width);
                humanY = (int) p.random(0, p.height);
            }
            particles.add(new Human(humanX, humanY, p, this));
        }
    }

    ParticleSystem(int startingX, int startingY, PApplet p) {
        particles = new ArrayList<Particle>();
        this.startingX = Math.min(Math.max(startingX, 0), p.width);
        this.startingY = Math.min(Math.max(startingY, 0), p.height);
        particles.add(new Zombie(startingX, startingY, p, this));
        for (int i = 0; i < NUMBER_OF_HUMANS; ++i) {
            int humanX = (int) p.random(0, p.width);
            int humanY = (int) p.random(0, p.height);
            double distance = Math.min(p.width, p.height) / 4.0;
            while (Math.sqrt(Math.pow(humanX - startingX, 2) + Math.pow(humanY - startingY, 2)) < distance) {
                humanX = (int) p.random(0, p.width);
                humanY = (int) p.random(0, p.height);
            }
            particles.add(new Human(humanX, humanY, p, this));
        }
    }

    void update() {
        for (i = 0; i < ParticleSystem.particles.size(); i++)
            ParticleSystem.particles.get(i).move();
    }

    void draw() {
        for (Particle p : particles) p.draw();
    }
}