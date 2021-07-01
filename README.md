# Static Solar System Data Model

Spring boot is not necessary for this model

## Initialization
```
SolarSystem solarSystem = new SolarSystem(new Planet("Sun")
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
```
## Usage
To calculate destination between one planet and another use following syntax
```
long distance = solarSystem.calculateDistance("Earth", "Sun");
```
