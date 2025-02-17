package fr.univavignon.pokedex.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IPokedexTest {
    //private static IPokemonMetadataProvider metadataProvider = mock(IPokemonMetadataProvider.class);
    //private static IPokemonFactory pokemonFactory = mock(IPokemonFactory.class);
    private static Pokemon pokemon1, pokemon2;
    private static IPokedex pokedex;
    private static PokemonMetadata metadata1;
    private static PokemonMetadata metadata2;

    @Before
    public void setUp() throws PokedexException {
        metadata1 = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
        metadata2 = new PokemonMetadata(133, "Aquali", 186, 168, 260);
        //when(metadataProvider.getPokemonMetadata(0)).thenReturn(metadata1);
        //when(metadataProvider.getPokemonMetadata(133)).thenReturn(metadata2);

        pokemon1 = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);
        pokemon2 = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100);
        //when(pokemonFactory.createPokemon(0, 613, 64, 4000, 4)).thenReturn(pokemon1);
        //when(pokemonFactory.createPokemon(133, 2729, 202, 5000, 4)).thenReturn(pokemon2);

        pokedex = new Pokedex(new PokemonMetadataProvider(), new PokemonFactory());
    }

    @Test
    public void testSize() {
        /*IPokedexFactory factory = mock(IPokedexFactory.class);
        IPokedex pokedex = mock(IPokedex.class);

        when(factory.createPokedex(any(), any())).thenReturn(pokedex);
        when(pokedex.size()).thenReturn(3);*/

        assertEquals(0, pokedex.size());
    }

    @Test
    public void testAddPokemon() throws PokedexException {
        int id1 = pokedex.addPokemon(pokemon1);
        assertEquals(id1,0);
        assertEquals(pokedex.size(),1);


        int id2 = pokedex.addPokemon(pokemon2);

        assertEquals(pokedex.size(),2);
        assertEquals(id2,1);
    }

    @Test
    public void testGetInvalidPokemon() throws PokedexException {
        assertThrows(PokedexException.class, () -> pokedex.getPokemon(10));
    }

    @Test
    public void testGetPokemons() throws PokedexException {
        pokedex.addPokemon(pokemon1);
        pokedex.addPokemon(pokemon2);

        List<Pokemon> pokemons = pokedex.getPokemons();
        assertEquals(2, pokemons.size());
        assertTrue(pokemons.contains(pokemon1));
        assertTrue(pokemons.contains(pokemon2));
    }

    @Test
    public void getPokemonID() throws PokedexException{
        pokedex.addPokemon(pokemon1);
        pokedex.addPokemon(pokemon2);
        assertEquals(pokedex.getPokemon(0).getIndex(),0);
        assertEquals(pokedex.getPokemon(0).getName(), "Bulbizarre");
        //when(pokedex.getPokemon(133)).thenReturn(aquali);
        assertEquals(pokedex.getPokemon(133).getIndex(),133);
        assertEquals(pokedex.getPokemon(133).getName(), "Aquali");
    }

    @Test
    public void getPokemonsSortedByIndexTest(){
        List<Pokemon> pokemonsSorted = new ArrayList<>(Arrays.asList(pokemon1, pokemon2));
        List<Pokemon> expectedPokemonsSorted = Collections.unmodifiableList(pokemonsSorted);

        pokedex.addPokemon(pokemon2);
        pokedex.addPokemon(pokemon1);

        Assert.assertEquals(pokedex.getPokemons(PokemonComparators.INDEX), expectedPokemonsSorted);
    }

    @Test
    public void getPokemonMetadataTest() throws PokedexException {
        PokemonMetadata pokemonMetadata = pokedex.getPokemonMetadata(133);
        assertEquals(pokemonMetadata.getIndex(), metadata2.getIndex());
        assertEquals(pokemonMetadata.getName(), metadata2.getName());
        assertEquals(pokemonMetadata.getAttack(), metadata2.getAttack());
        assertEquals(pokemonMetadata.getDefense(), metadata2.getDefense());
        assertEquals(pokemonMetadata.getStamina(), metadata2.getStamina());
    }

    @Test
    public void createPokemonTest() throws PokedexException {

        Pokemon pokemon = pokedex.createPokemon(0,613,64, 4000,4 );
        assertEquals(pokemon.getCp(), pokemon1.getCp());
        assertEquals(pokemon.getCandy(), pokemon1.getCandy());
        assertEquals(pokemon.getDust(), pokemon1.getDust());
        assertEquals(pokemon.getHp(), pokemon1.getHp());
        assertEquals(pokemon.getIndex(), pokemon1.getIndex());
        assertEquals(pokemon.getAttack(), pokemon1.getAttack());
        assertTrue(pokemon.getIv() >=0 && pokemon.getIv() <=100);

    }
}
