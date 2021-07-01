package solar.demo.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Solar system model with it's calculations
 */
public class SolarSystem {

    // Host of the system
    private Planet sun;

    // Needed to get planet by name fast. Will be calculated in a lazy manner
    private Map<String, Planet> planetVocabulary;

    public SolarSystem(Planet sun) {
        this.sun = sun;
    }

    /**
     * Fetching planet object from current solar system my plant's name
     * @param planetName
     * @return
     */
    public Planet getPlanetByName(final String planetName) {
        // Let's assume that we may not need this structure at all, so let's build it in lazy manner
        if (planetVocabulary == null) {
            planetVocabulary = new HashMap<>();
            this.calculatePlanetsCache(sun);
        }
        if (!planetVocabulary.containsKey(planetName)) {
            throw new RuntimeException("Planet not found: " + planetName);
        }
        return planetVocabulary.get(planetName);
    }


    /**
     * Calculating distance fom one planet to another if we only know their names
     *
     * @param planetAName
     * @param planetBName
     * @return
     */
    public long calculateDistance(String planetAName, String planetBName) {
        Planet planetA = getPlanetByName(planetAName);
        Planet planetB = getPlanetByName(planetBName);
        return calculateDistance(planetA, planetB);
    }


    /**
     * Calculating distance fom one planet to another if we have them as objects
     *
     * @param planetA
     * @param planetB
     * @return
     */
    public long calculateDistance(Planet planetA, Planet planetB) {
        if (planetA == planetB || planetA.getName().equals(planetB.getName())) {
            return 0L;
        }
        if (planetA.getHostPlanet() == planetB) {
            return planetA.getDistanceFromHostPlanet();
        }
        if (planetB.getHostPlanet() == planetA) {
            return planetB.getDistanceFromHostPlanet();
        }
        PlanetCoordinates planetACoordinates = getCoordinatesFromSun(planetA);
        PlanetCoordinates planetBCoordinates = getCoordinatesFromSun(planetB);
        return planetACoordinates.distanceTo(planetBCoordinates);
    }

    /**
     * Inner method that is needed to calculate
     * @param planet
     * @return
     */
    private PlanetCoordinates getCoordinatesFromSun(Planet planet) {
        PlanetCoordinates result = PlanetCoordinates.CENTER;
        Planet host = planet;
        while (host.getHostPlanet() != null) {
            result = result.plus(host.getCoordinatesFromHost());
            host = host.getHostPlanet();
        }
        if (host != sun) {
            throw new RuntimeException("Planet belongs to another solar system");
        }
        return result;
    }

    /**
     * Calculates planet vocabulary from planet given as a parameter and all it's moons
     * @param planet
     */
    private void calculatePlanetsCache(Planet planet) {
        planetVocabulary.put(planet.getName(), planet);
        planet.getMoons().forEach(this::calculatePlanetsCache);
    }
}
