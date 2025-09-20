# Pokedex Application

## Prerequisites
- Install Java -> https://www.oracle.com/java/technologies/downloads/#jdk21-windows
- Install Maven -> https://maven.apache.org/install.html

## How to Run
1. Clone the Pokedex project.
2. Go to the project root directory.
3. Run ```mvn clean install```.
4. Run ```java -jar target/pokedex-0.0.1-SNAPSHOT.jar``` to start the application.

## Endpoints

### Get Pokemon Information
curl --location 'http://localhost:9000/pokemon/{pokemonName}'

Response:
```json
{
  "name": "pikachu",
  "description": "A yellow electric mouse Pokémon.",
  "habitat": "forest",
  "isLegendary": false
}
```

### Get translated Pokemon Description
curl --location 'http://localhost:9000/pokemon/translated/{pokemonName}'

Response:
```json
{
  "name": "pikachu",
  "description": "A yellow electric mouse Pokémon, yonder it zaps with lightning.",
  "habitat": "forest",
  "isLegendary": false
}
```

## Nice for Production
1. Endpoints input parameters validations.
2. Better exception handling.
3. OAuth authentication.
4. Increase unit tests cases.
