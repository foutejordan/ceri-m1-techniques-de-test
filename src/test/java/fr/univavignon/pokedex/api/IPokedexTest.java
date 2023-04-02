package fr.univavignon.pokedex.api;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class IPokedexTest {

    @Mock
    private static IPokemonMetadataProvider metadataProviderMock;

    @Mock
    private static IPokemonFactory pokemonFactoryMock;

    private static PokemonMetadata metadata;
    private static Pokemon pokemon;
    private static IPokedex pokedex;

    @BeforeClass
    public static void setUp() throws PokedexException {
        metadata = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
        pokemon = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);

        metadataProviderMock = Mockito.mock(IPokemonMetadataProvider.class);
        pokemonFactoryMock = Mockito.mock(IPokemonFactory.class);
        pokedex = Mockito.mock(IPokedex.class);

        Mockito.when(metadataProviderMock.getPokemonMetadata(0)).thenReturn(metadata);
        Mockito.when(pokemonFactoryMock.createPokemon(0, 613, 64, 4000, 0)).thenReturn(pokemon);

        Mockito.when(pokedex.addPokemon(Mockito.any(Pokemon.class))).thenReturn(0);
        Mockito.when(pokedex.size()).thenReturn(1);
        Mockito.when(pokedex.getPokemon(0)).thenReturn(pokemon);
        Mockito.when(pokedex.getPokemons()).thenReturn(List.of(pokemon));

        pokedex.addPokemon(pokemon);
    }

    @Test
    public void testSize() {
        assertEquals(1, pokedex.size());
    }

    @Test
    public void testAddPokemon() throws PokedexException {
        Pokemon newPokemon = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100);
        Mockito.when(pokedex.addPokemon(newPokemon)).thenReturn(1);
        Mockito.when(pokedex.size()).thenReturn(2);
        Mockito.when(pokedex.getPokemon(1)).thenReturn(newPokemon);
        int index = pokedex.addPokemon(newPokemon);
        assertEquals(1, index);
        assertEquals(2, pokedex.size());
        assertEquals(newPokemon, pokedex.getPokemon(index));
    }

    @Test
    public void testGetPokemon() throws PokedexException {
        assertEquals(pokemon, pokedex.getPokemon(0));
    }

    @Test(expected = PokedexException.class)
    public void testGetPokemonWithInvalidIndex() throws PokedexException {
        pokedex.getPokemon(2);
    }

    @Test
    public void testGetPokemons() {
        List<Pokemon> pokemons = pokedex.getPokemons();
        assertEquals(1, pokemons.size());
        assertEquals(pokemon, pokemons.get(0));
    }

    @Test
    public void testGetPokemonsWithComparator() {
        Pokemon newPokemon = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100);

        pokedex.addPokemon(newPokemon);

        Comparator<Pokemon> comparator = Comparator.comparing(Pokemon::getIndex);
        List<Pokemon> pokemons = pokedex.getPokemons(comparator);
        assertEquals(2, pokemons.size());
        assertEquals(pokemon, pokemons.get(0));
        assertEquals(newPokemon, pokemons.get(133));
    }
}
