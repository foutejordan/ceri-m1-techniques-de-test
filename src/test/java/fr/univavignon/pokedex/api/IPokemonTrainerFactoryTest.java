package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class IPokemonTrainerFactoryTest {

    private IPokemonTrainerFactory pokemonTrainerFactory;

    private IPokedexFactory pokedexFactory;

    private static IPokedex pokedex;
    private static PokemonTrainer pokemonTrainer;
    final String name = "Ash Ketchum";
    final Team team = Team.VALOR;
    @Before
    public void setUp() {
        /*pokemonTrainerFactory = mock(IPokemonTrainerFactory.class);
        pokedexFactory = mock(IPokedexFactory.class);*/
        pokemonTrainerFactory = new PokemonTrainerFactory();
        pokedexFactory = new PokedexFactory();

        pokedex = new Pokedex(new PokemonMetadataProvider(), new PokemonFactory());
        pokemonTrainer = new PokemonTrainer(name,team, pokedex);

    }

    @Test
    public void testCreateTrainer() {

        //final PokemonTrainer pokemonTrainer = mock(PokemonTrainer.class);

        //when(pokemonTrainerFactory.createTrainer(name, team, pokedexFactory)).thenReturn(pokemonTrainer);

        final PokemonTrainer createdTrainer = pokemonTrainerFactory.createTrainer(name, team, pokedexFactory);

        assertEquals(pokemonTrainer.getName(), createdTrainer.getName());
        assertEquals(pokemonTrainer.getTeam(), createdTrainer.getTeam());
        assertEquals(pokemonTrainer.getPokedex().size(), createdTrainer.getPokedex().size());

        //verify(pokemonTrainerFactory).createTrainer(name, team, pokedexFactory);
    }

}
