package solar.demo.model;

import solar.demo.exceptions.PlanetDataIsIncorrect;

import java.util.HashSet;
import java.util.Set;

/**
 * Any space body spinning around something. It may be a moon as well
 */
public class Planet {

    // In our case name will be identifier as well
    private final String name;

    // Planet which current space body is spinning around
    private Planet hostPlanet;

    // Distance, kilometers
    private Long distanceFromHostPlanet;

    // Angel from vertical base for all solar system
    private Double angleFromVertical;

    // Back reference all planet's moons
    private Set<Planet> moons;

    /**
     * Adds new Planet as a moons of current
     * @return current planet for additional chaining
     */
    public Planet moon(long distance, double angle, Planet moon) {
        this.moons.add(moon);
        moon.distanceFromHostPlanet = distance;
        moon.angleFromVertical = angle;
        moon.hostPlanet = this;
        return this;
    }

    public PlanetCoordinates getCoordinatesFromHost() {
        if (hostPlanet == null) {
            return PlanetCoordinates.CENTER;
        }
        if (distanceFromHostPlanet == null) {
            throw new PlanetDataIsIncorrect("distanceFromHostPlanet was not set for planet " + name);
        }
        if (angleFromVertical == null) {
            throw new PlanetDataIsIncorrect("angleFromVertical was not set for planet " + name);
        }
        long x = (long) (distanceFromHostPlanet * Math.sin(Math.toRadians(angleFromVertical)));
        long y = (long) (distanceFromHostPlanet * Math.cos(Math.toRadians(angleFromVertical)));
        return new PlanetCoordinates(x, y);
    }

    public Planet(String name) {
        this.name = name;
        this.moons = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public Planet getHostPlanet() {
        return hostPlanet;
    }

    public Long getDistanceFromHostPlanet() {
        return distanceFromHostPlanet;
    }

    public Double getAngleFromVertical() {
        return angleFromVertical;
    }

    public Set<Planet> getMoons() {
        return moons;
    }
}
