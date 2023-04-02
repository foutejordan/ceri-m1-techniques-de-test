package fr.univavignon.pokedex.api;
import static org.junit.Assert.assertEquals;
import static  org.mockito.Mockito.*;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


public class IPokemonFactoryTest {

    @Mock
    private static IPokemonMetadataProvider metadataProvider;

    private static IPokemonFactory pokemonFactory;

    private static PokemonMetadata bulbasaurMetadata = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);

    @BeforeClass
    public static void setUp() throws PokedexException {
        metadataProvider = mock(IPokemonMetadataProvider.class);
        pokemonFactory = mock(IPokemonFactory.class);
        Mockito.when(metadataProvider.getPokemonMetadata(0)).thenReturn(bulbasaurMetadata);
        Mockito.when(pokemonFactory.createPokemon(0, 613, 64, 4000, 4)).thenReturn(new Pokemon(0, "Bulbizarre", 126, 126, 90,613, 64, 4000, 4,56));

    }

    @Test
    public void testCreatePokemon() {
        Pokemon Bulbizarre = pokemonFactory.createPokemon(0, 613, 64, 4000, 4);
        Assert.assertEquals(613, Bulbizarre.getCp());
        Assert.assertEquals(64, Bulbizarre.getHp());
        Assert.assertEquals(4000, Bulbizarre.getDust());
        Assert.assertEquals(4, Bulbizarre.getCandy());
    }
}
