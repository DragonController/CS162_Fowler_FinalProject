import processing.core.PApplet;

import java.util.ArrayList;

class ParticleSystem {
    final int NUMBER_OF_HUMANS = 100;
    ArrayList<Particle> particles;
    int startingX;
    int startingY;

    ParticleSystem(PApplet p) {
        particles = new ArrayList<Particle>();
        startingX = p.width / 2;
        startingY = p.height / 2;
        particles.add(new Zombie(startingX, startingY, p));
        for (int i = 0; i < NUMBER_OF_HUMANS; ++i) {
            int humanX = (int) p.random(0, p.width);
            int humanY = (int) p.random(0, p.height);
            double distance = Math.min(p.width, p.height) / 4.0;
            while (Math.sqrt(Math.pow(humanX - startingX, 2) + Math.pow(humanY - startingY, 2)) < distance) {
                humanX = (int) p.random(0, p.width);
                humanY = (int) p.random(0, p.height);
            }
            particles.add(new Human(humanX, humanY, p));
        }
    }

    ParticleSystem(int startingX, int startingY, PApplet p) {
        this.startingX = Math.min(Math.max(startingX, 0), p.width);
        this.startingY = Math.min(Math.max(startingY, 0), p.height);
        particles = new ArrayList<Particle>();
        particles.add(new Zombie(startingX, startingY, p));
        for (int i = 0; i < NUMBER_OF_HUMANS; ++i) {
            int humanX = (int) p.random(0, p.width);
            int humanY = (int) p.random(0, p.height);
            double distance = Math.min(p.width, p.height) / 4.0;
            while (Math.sqrt(Math.pow(humanX - startingX, 2) + Math.pow(humanY - startingY, 2)) < distance) {
                humanX = (int) p.random(0, p.width);
                humanY = (int) p.random(0, p.height);
            }
            System.out.println(distance);
            particles.add(new Human(humanX, humanY, p));
        }
    }

    void update() {
        for (Particle p : particles) {
            p.move();
        }
    }

    void draw() {
        for (Particle p : particles) p.draw();
    }
}