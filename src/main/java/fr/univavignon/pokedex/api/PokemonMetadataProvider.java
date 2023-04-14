package fr.univavignon.pokedex.api;

import java.util.HashMap;
import java.util.Map;

public class PokemonMetadataProvider implements IPokemonMetadataProvider{

    // Map pour stocker les métadonnées des pokemons par index
    private Map<Integer, PokemonMetadata> pokemonMetadataMap;
    public PokemonMetadataProvider() {
        pokemonMetadataMap = new HashMap<>();
        PokemonMetadata pokemonMetadata = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
        PokemonMetadata pokemonMetadata2 = new PokemonMetadata(133, "Aquali", 186, 168, 260);

        pokemonMetadataMap.put(pokemonMetadata.getIndex(), pokemonMetadata);
        pokemonMetadataMap.put(pokemonMetadata2.getIndex(), pokemonMetadata2);

    }

    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        if(pokemonMetadataMap.containsKey(index)){
            return pokemonMetadataMap.get(index);
        }
        else{
            throw new PokedexException("pokemon's index does not exist");
        }
    }
}
