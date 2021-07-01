package solar.demo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import solar.demo.model.Planet;
import solar.demo.model.SolarSystem;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SystemApplicationTests {

    private static long EARTH_TO_MOON = 384_400L;
    private static long EARTH_TO_SUN = 149_500_000L;
    private static long SATURN_TO_SUN = 400_000_000_000L;
    private static long URANUS_TO_SUN = 900_000_000_000L;

    private SolarSystem solarSystem;

    @BeforeAll
    void initSolarSystem() {
        solarSystem = new SolarSystem(new Planet("Sun")
                .moon(70_000_000L, 14.23333, new Planet("Mercury"))
                .moon(100_000_000L, 176.23123, new Planet("Venus"))
                .moon(EARTH_TO_SUN, -13.2313, new Planet("Earth")
                        .moon(EARTH_TO_MOON, -98.1233, new Planet("Moon"))
                )
                .moon(200_000_000L, 90, new Planet("Mars"))
                .moon(200_000_000_000L, 90, new Planet("Jupiter")
                        .moon(312_400L, 90.23, new Planet("Io"))
                        .moon(123_400L, 90.23, new Planet("Europa"))
                        .moon(902_400L, 90.23, new Planet("Ganymede"))
                        .moon(1_000_000L, 180, new Planet("Callisto"))
                )
                .moon(SATURN_TO_SUN, 90, new Planet("Saturn"))
                .moon(URANUS_TO_SUN, -90, new Planet("Uranus"))
        );
    }

    @Test
    void distanceFromPlanetToTheSun() {
        long distance = solarSystem.calculateDistance("Earth", "Sun");
        assertThat(distance).isEqualTo(EARTH_TO_SUN);
    }

    @Test
    void distanceFromPlanetToItsMoon() {
        long distance = solarSystem.calculateDistance("Earth", "Moon");
        assertThat(distance).isEqualTo(EARTH_TO_MOON);
    }

    @Test
    void distanceFromMoonToItsPlanet() {
        long distance = solarSystem.calculateDistance("Earth", "Moon");
        assertThat(distance).isEqualTo(EARTH_TO_MOON);
    }

    @Test
    void distanceFromPlanetToPlanet() {
        long distance = solarSystem.calculateDistance("Earth", "Venus");
        assertThat(distance).isEqualTo(248_683_352L);
    }

    @Test
    void distanceFromPlanetsInOppositeSidesOfTheSun() {
        long distance = solarSystem.calculateDistance("Saturn", "Uranus");
        assertThat(distance).isEqualTo(SATURN_TO_SUN + URANUS_TO_SUN);
    }

    @Test
    void distanceFromSateliteToSun() {
        long distance = solarSystem.calculateDistance("Sun", "Callisto");
        assertThat(distance).isEqualTo(200_000_000_002L);
    }

    @Test
    void distanceFromThePlanetToItselfIsZero() {
        long distance = solarSystem.calculateDistance("Earth", "Earth");
        assertThat(distance).isEqualTo(0L);
    }

    @Test
    void distanceFromSateliteToSatelite() {
        long distance = solarSystem.calculateDistance("Moon", "Europa");
        assertThat(distance).isEqualTo(200_034_774_804L);
    }

    @Test
    void unknownPlanetShouldRaiseAnError() {
        assertThatThrownBy(() -> solarSystem.calculateDistance("Earth", "OmicronPersei8"))
                .hasMessage("Planet not found: OmicronPersei8");
    }

    @Test
    void planetFromAnotherSolarSystemShouldRaiseAnError() {
        Planet earth = solarSystem.getPlanetByName("Earth");
        assertThatThrownBy(() -> solarSystem.calculateDistance(earth, new Planet("OmicronPersei8")))
                .hasMessage("Planet belongs to another solar system");
    }

}
