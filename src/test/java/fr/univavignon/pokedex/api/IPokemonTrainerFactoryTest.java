package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class IPokemonTrainerFactoryTest {

    private IPokemonTrainerFactory pokemonTrainerFactory;

    private IPokedexFactory pokedexFactory;

    @Before
    public void setUp() {
        pokemonTrainerFactory = mock(IPokemonTrainerFactory.class);
        pokedexFactory = mock(IPokedexFactory.class);
    }

    @Test
    public void testCreateTrainer() {
        final String name = "Ash Ketchum";
        final Team team = Team.VALOR;
        final PokemonTrainer pokemonTrainer = mock(PokemonTrainer.class);

        when(pokemonTrainerFactory.createTrainer(name, team, pokedexFactory)).thenReturn(pokemonTrainer);

        final PokemonTrainer createdTrainer = pokemonTrainerFactory.createTrainer(name, team, pokedexFactory);

        assertEquals(pokemonTrainer, createdTrainer);

        verify(pokemonTrainerFactory).createTrainer(name, team, pokedexFactory);
    }

}
