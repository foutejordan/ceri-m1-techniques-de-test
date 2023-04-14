package fr.univavignon.pokedex.api;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Pokedex implements IPokedex{

    IPokemonMetadataProvider metadataProvider;
    IPokemonFactory pokemonFactory;

    private List<Pokemon> pokemons = new ArrayList<>();
    public Pokedex(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {

        this.metadataProvider = metadataProvider;
        this.pokemonFactory = pokemonFactory;
    }


    @Override
    public int size() {
        return pokemons.size();
    }
    @Override
    public int addPokemon(Pokemon pokemon) {
        pokemons.add(pokemon);
        return pokemons.size() - 1;
    }
    @Override
    public Pokemon getPokemon(int id) throws PokedexException {

            for (Pokemon pokemon: pokemons){
                if (pokemon.getIndex() == id){
                    return pokemon;
                }
            }
            throw new PokedexException("Invalid index: " + id);
    }
    @Override
    public List<Pokemon> getPokemons() {
        return new ArrayList<>(pokemons);
    }
    @Override
    public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
        List<Pokemon> sorted = new ArrayList<>(pokemons);
        sorted.sort(order);
        return sorted;
    }
    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        return metadataProvider.getPokemonMetadata(index);
    }
    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) throws PokedexException {
        return pokemonFactory.createPokemon(index, cp, hp, dust, candy);
    }
}
