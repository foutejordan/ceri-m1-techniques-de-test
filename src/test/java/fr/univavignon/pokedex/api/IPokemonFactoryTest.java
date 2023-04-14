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

    /*@Mock
    private static IPokemonMetadataProvider metadataProvider;*/

    private static Pokemon pokemon1, pokemon2;

    private static IPokemonFactory pokemonFactory;



    @BeforeClass
    public static void setUp() throws PokedexException {
        /*metadataProvider = mock(IPokemonMetadataProvider.class);
        pokemonFactory = mock(IPokemonFactory.class);
        Mockito.when(metadataProvider.getPokemonMetadata(0)).thenReturn(bulbasaurMetadata);
        Mockito.when(pokemonFactory.createPokemon(0, 613, 64, 4000, 4)).thenReturn(new Pokemon(0, "Bulbizarre", 126, 126, 90,613, 64, 4000, 4,56));*/

        pokemonFactory = new PokemonFactory();
        pokemon1 = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);
        pokemon2 = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100);
    }

    @Test
    public void testCreatePokemon() throws PokedexException {
        Pokemon newPokemon = pokemonFactory.createPokemon(0, 613, 64, 4000, 4);
        Assert.assertEquals(newPokemon.getCp(), pokemon1.getCp());
        Assert.assertEquals(newPokemon.getHp(), pokemon1.getHp());
        Assert.assertEquals(newPokemon.getDust(), pokemon1.getDust());
        Assert.assertEquals(newPokemon.getCandy(), pokemon1.getCandy());
    }
}
