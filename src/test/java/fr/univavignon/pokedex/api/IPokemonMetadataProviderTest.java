package fr.univavignon.pokedex.api;
import static org.junit.Assert.assertEquals;
import static  org.mockito.Mockito.*;
import org.junit.Test;

public class IPokemonMetadataProviderTest {

    @Test
    public  void testgetPokemonMetadata() throws PokedexException {

        IPokemonMetadataProvider metaDataMock = mock(IPokemonMetadataProvider.class);
        when(metaDataMock.getPokemonMetadata(0)).thenReturn(new PokemonMetadata(0, "Bulbizarre", 126, 126,90));

        Pokemon pk1 = new Pokemon(0, "Bulbizarre", 126, 126,90,613,64,4000,4,56);

        Pokemon pk2 = new Pokemon(133, "Aquali",186,168,260,2729,202,5000,4,100);

        PokemonMetadata result = metaDataMock.getPokemonMetadata(0);

        assertEquals(new PokemonMetadata(0, "Bulbizarre", 126, 126,90), result);
        verify(metaDataMock).getPokemonMetadata(0);

    }
}
