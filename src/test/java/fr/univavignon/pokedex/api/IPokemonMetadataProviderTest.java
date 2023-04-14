package fr.univavignon.pokedex.api;
import static org.junit.Assert.assertEquals;
import static  org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class IPokemonMetadataProviderTest {

    /*@Mock
    private IPokemonMetadataProvider pokemonMetadataProviderMock;*/
    private static IPokemonMetadataProvider pokemonMetadataProvider;

    private static PokemonMetadata metadata1;
    private static PokemonMetadata metadata2;

    @Before
    public void setUp() throws PokedexException {
        /*pokemonMetadataProviderMock = mock(IPokemonMetadataProvider.class);
        when(pokemonMetadataProviderMock.getPokemonMetadata(0))
                .thenReturn(new PokemonMetadata(0, "Bulbizarre", 126, 126, 90));*/

        pokemonMetadataProvider = new PokemonMetadataProvider();
        metadata1 = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
        metadata2 = new PokemonMetadata(133, "Aquali", 186, 168, 260);
    }

    @Test
    public void testGetPokemonMetadata() throws PokedexException {
        //PokemonMetadata pokemonMetadata = pokemonMetadataProviderMock.getPokemonMetadata(0);

        assertEquals(pokemonMetadataProvider.getPokemonMetadata(0).getIndex(), metadata1.getIndex());
        assertEquals(pokemonMetadataProvider.getPokemonMetadata(0).getName(), metadata1.getName());
        assertEquals(pokemonMetadataProvider.getPokemonMetadata(0).getAttack(), metadata1.getAttack());
        assertEquals(pokemonMetadataProvider.getPokemonMetadata(0).getDefense(), metadata1.getDefense());
        assertEquals(pokemonMetadataProvider.getPokemonMetadata(0).getStamina(), metadata1.getStamina());
    }
}
