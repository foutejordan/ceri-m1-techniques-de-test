package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IPokedexTest {
    private static IPokemonMetadataProvider metadataProvider = mock(IPokemonMetadataProvider.class);
    private static IPokemonFactory pokemonFactory = mock(IPokemonFactory.class);
    private static Pokemon pokemon1, pokemon2;
    private static IPokedex pokedex;

    @Before
    public void setUp() throws PokedexException {
        PokemonMetadata metadata1 = new PokemonMetadata(0, "Bulbasaur", 126, 126, 90);
        PokemonMetadata metadata2 = new PokemonMetadata(1, "Ivysaur", 156, 158, 120);
        when(metadataProvider.getPokemonMetadata(0)).thenReturn(metadata1);
        when(metadataProvider.getPokemonMetadata(1)).thenReturn(metadata2);

        pokemon1 = new Pokemon(0, "Bulbasaur", 126, 126, 90, 613, 64, 4000, 4, 4);
        pokemon2 = new Pokemon(1, "Ivysaur", 156, 158, 120, 950, 100, 4000, 4, 4);
        when(pokemonFactory.createPokemon(0, 613, 64, 4000, 4)).thenReturn(pokemon1);
        when(pokemonFactory.createPokemon(1, 950, 100, 4000, 4)).thenReturn(pokemon2);

        pokedex = new IPokedex() {
            private List<Pokemon> pokemons = new ArrayList<>();
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
                if (id >= 0 && id < pokemons.size()) {
                    return pokemons.get(id);
                } else {
                    throw new PokedexException("Invalid index: " + id);
                }
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
            public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
                return pokemonFactory.createPokemon(index, cp, hp, dust, candy);
            }
        };
    }

    @Test
    public void testSize() {
        IPokedexFactory factory = mock(IPokedexFactory.class);
        IPokedex pokedex = mock(IPokedex.class);

        when(factory.createPokedex(any(), any())).thenReturn(pokedex);
        when(pokedex.size()).thenReturn(3);

        assertEquals(3, pokedex.size());
    }

    @Test
    public void testAddPokemon() throws PokedexException {
        int id1 = pokedex.addPokemon(pokemon1);
        int id2 = pokedex.addPokemon(pokemon2);

        assertEquals(2, pokedex.size());
        assertEquals(pokemon1, pokedex.getPokemon(id1));
        assertEquals(pokemon2, pokedex.getPokemon(id2));
    }

    @Test(expected = PokedexException.class)
    public void testGetInvalidPokemon() throws PokedexException {
        pokedex.getPokemon(10);
    }

    @Test
    public void testGetPokemons() throws PokedexException {
        pokedex.addPokemon(pokemon1);
        pokedex.addPokemon(pokemon2);

        List<Pokemon> pokemons = pokedex.getPokemons();
        assertEquals(2, pokemons.size());
        assertTrue(pokemons.contains(pokemon1));
        assertTrue(pokemons.contains(pokemon2));
    }

    @Test
    public void testGetPokemonsSorted() throws PokedexException {
        Pokemon pokemon3 = pokemonFactory.createPokemon(1, 10, 20, 100, 10);
        pokedex.addPokemon(pokemon1);
        pokedex.addPokemon(pokemon2);
        pokedex.addPokemon(pokemon3);

        Comparator<Pokemon> cpComparator = new Comparator<Pokemon>() {
            public int compare(Pokemon p1, Pokemon p2) {
                return Integer.compare(p1.getCp(), p2.getCp());
            }
        };

        List<Pokemon> pokemons = pokedex.getPokemons(cpComparator);
        assertEquals(3, pokemons.size());
        assertEquals(pokemon2, pokemons.get(0));
        assertEquals(pokemon1, pokemons.get(1));
        assertEquals(pokemon3, pokemons.get(2));
    }
}
