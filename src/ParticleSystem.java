import processing.core.PApplet;

import java.util.ArrayList;

class ParticleSystem {
    static ArrayList<Particle> particles;

    private final int STARTING_NUMBER_OF_HUMANS = 100;
    private final int startingX;
    private final int startingY;
    PApplet p;
    float currentTime = System.nanoTime();

    public ParticleSystem(PApplet p) {
        this.p = p;
        particles = new ArrayList<Particle>();
        startingX = p.width / 2;
        startingY = p.height / 2;
        createParticles();
    }

    public void update() {
        for (int i = 0; i < ParticleSystem.particles.size(); i++)
            ParticleSystem.particles.get(i).calculate();
        currentTime = System.nanoTime();
    }

    public void draw() {
        for (Particle p : particles) p.draw();
    }

    public void addParticle(Particle p) {
        particles.add(p);
    }

    public void deleteAllParticles() {
        particles.clear();
    }

    public void createParticles() {
        particles.add(new Zombie(startingX, startingY, p, this));
        for (int i = 0; i < STARTING_NUMBER_OF_HUMANS; i++) {
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

    public int getHumans() {
        int numOfHumans = 0;
        for (Particle particle : particles) {
            if (particle instanceof Human)
                numOfHumans++;
        }
        return numOfHumans;
    }

    public int getZombies() {
        int numOfZombies = 0;
        for (Particle particle : particles) {
            if (particle instanceof Zombie)
                numOfZombies++;
        }
        return numOfZombies;
    }
}