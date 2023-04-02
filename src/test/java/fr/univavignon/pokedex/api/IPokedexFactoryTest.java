package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class IPokedexFactoryTest {

    private static IPokemonMetadataProvider metadataProvider;
    private static IPokemonFactory pokemonFactory;
    private static IPokedexFactory pokedexFactory;

    @Before
    public void setUp() throws PokedexException {
        metadataProvider = mock(IPokemonMetadataProvider.class);
        pokemonFactory = mock(IPokemonFactory.class);
//        pokedexFactory = new PokedexFactory();
    }

    @Test
    public void testCreatePokedex() throws PokedexException {
        IPokedex pokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);
        Pokemon pokemon = mock(Pokemon.class);
        assertEquals(0, pokedex.size());
        pokedex.addPokemon(pokemon);
        assertEquals(1, pokedex.size());
    }

}
