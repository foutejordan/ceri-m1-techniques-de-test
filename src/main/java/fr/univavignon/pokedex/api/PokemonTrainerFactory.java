package fr.univavignon.pokedex.api;

public class PokemonTrainerFactory implements IPokemonTrainerFactory{
    @Override
    public PokemonTrainer createTrainer(String name, Team team, IPokedexFactory pokedexFactory) {
        IPokedex pokedex = pokedexFactory.createPokedex(new PokemonMetadataProvider(), new PokemonFactory());
        // Création d'un nouvel objet PokemonTrainer en utilisant les paramètres d'entrée

        return new PokemonTrainer(name, team, pokedex);
    }
}
