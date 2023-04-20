package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IPokedexFactoryTest {

    private static IPokedexFactory pokedexFactory;
    private static IPokemonFactory pokemonFactory;
    private static IPokemonMetadataProvider metadataProvider;
    private static IPokedex pokedex;

    @BeforeClass
    public static void setUp() {
        metadataProvider = new PokemonMetadataProvider();
        pokemonFactory = new PokemonFactory();
        pokedexFactory = new PokedexFactory();
    }

    @Test
    public void testCreatePokedex() throws PokedexException {
        /*IPokemonMetadataProvider metadataProvider = mock(IPokemonMetadataProvider.class);
        IPokemonFactory pokemonFactory = mock(IPokemonFactory.class);
        IPokedex pokedex = mock(IPokedex.class);

        when(pokedexFactory.createPokedex(metadataProvider, pokemonFactory)).thenReturn(pokedex);*/

        pokedex = new Pokedex(metadataProvider, pokemonFactory);


        int createdPokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory).size();

        assertNotNull(createdPokedex);
        assertEquals(createdPokedex, 0);
    }
}
