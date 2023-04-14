package fr.univavignon.pokedex.api;

import java.util.Random;

public class PokemonFactory implements IPokemonFactory{

    IPokemonMetadataProvider pokemonMetadataProvider = new PokemonMetadataProvider();
    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) throws PokedexException {
        PokemonMetadata pokemonMetadata = pokemonMetadataProvider.getPokemonMetadata(index);

        Random rand = new Random();
        int iv = rand.nextInt(101);
        // Création d'une instance de Pokemon avec les IVs calculés
        return new Pokemon(index,pokemonMetadata.getName(),pokemonMetadata.getAttack(), pokemonMetadata.getDefense(), pokemonMetadata.getStamina(), cp,hp, dust, candy,iv);
    }

}
