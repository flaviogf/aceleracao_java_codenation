package br.com.flaviogf.gerenciadordetimes;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetTopPlayersTest {
    private static final Long FRANK_ID = 1L;
    private static final String FRANK_NAME = "FRANK";
    private static final LocalDate FRANK_BIRTHDAY = LocalDate.of(1990, 1, 1);
    private static final Integer FRANK_OVERALL = 99;
    private static final BigDecimal FRANK_SALARY = new BigDecimal(100_000);

    private static final Long REX_ID = 2L;
    private static final String REX_NAME = "REX";
    private static final LocalDate REX_BIRTHDAY = LocalDate.of(1990, 1, 1);
    private static final Integer REX_OVERALL = 100;
    private static final BigDecimal REX_SALARY = new BigDecimal(100_000);

    private static final Long TANK_ID = 3L;
    private static final String TANK_NAME = "TANK";
    private static final LocalDate TANK_BIRTHDAY = LocalDate.of(1990, 1, 1);
    private static final Integer TANK_OVERALL = 98;
    private static final BigDecimal TANK_SALARY = new BigDecimal(100_000);

    private Player frank;
    private Player rex;
    private Player tank;

    private PlayerRepository playerRepository;

    private GetTopPlayers getTopPlayers;

    @Before
    public void setUp() {
        frank = new Player(FRANK_ID, FRANK_NAME, FRANK_BIRTHDAY, FRANK_OVERALL, FRANK_SALARY);
        rex = new Player(REX_ID, REX_NAME, REX_BIRTHDAY, REX_OVERALL, REX_SALARY);
        tank = new Player(TANK_ID, TANK_NAME, TANK_BIRTHDAY, TANK_OVERALL, TANK_SALARY);

        playerRepository = mock(PlayerRepository.class);

        getTopPlayers = new GetTopPlayers(playerRepository);
    }

    @Test
    public void execute_should_return_the_top_players() {
        when(playerRepository.findAll()).thenReturn(Arrays.asList(frank, rex, tank));

        List<Long> topPlayers = getTopPlayers.execute(3);

        assertEquals(3, topPlayers.size());
        assertEquals(rex.getId(), topPlayers.get(0));
        assertEquals(frank.getId(), topPlayers.get(1));
        assertEquals(tank.getId(), topPlayers.get(2));
    }

    @Test
    public void execute_when_the_limit_is_two_should_return_a_list_with_size_two() {
        when(playerRepository.findAll()).thenReturn(Arrays.asList(frank, rex, tank));

        List<Long> topPlayers = getTopPlayers.execute(2);

        assertEquals(2, topPlayers.size());
        assertEquals(rex.getId(), topPlayers.get(0));
        assertEquals(frank.getId(), topPlayers.get(1));
    }

    @Test
    public void execute_when_do_not_have_a_player_should_return_a_empty_list() {
        List<Long> topPlayers = getTopPlayers.execute(10);

        assertEquals(0, topPlayers.size());
    }
}
