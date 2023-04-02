package fr.univavignon.pokedex.api;
import static org.junit.Assert.assertEquals;
import static  org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class IPokemonMetadataProviderTest {

    @Mock
    private IPokemonMetadataProvider pokemonMetadataProviderMock;

    @Before
    public void setUp() throws PokedexException {
        MockitoAnnotations.initMocks(this);

        when(pokemonMetadataProviderMock.getPokemonMetadata(0))
                .thenReturn(new PokemonMetadata(0, "Bulbasaur", 126, 126, 90));
    }

    @Test
    public void testGetPokemonMetadata() throws PokedexException {
        PokemonMetadata pokemonMetadata = pokemonMetadataProviderMock.getPokemonMetadata(0);

        assertEquals(0, pokemonMetadata.getIndex());
        assertEquals("Bulbasaur", pokemonMetadata.getName());
        assertEquals(126, pokemonMetadata.getAttack());
        assertEquals(126, pokemonMetadata.getDefense());
        assertEquals(90, pokemonMetadata.getStamina());
    }
}
