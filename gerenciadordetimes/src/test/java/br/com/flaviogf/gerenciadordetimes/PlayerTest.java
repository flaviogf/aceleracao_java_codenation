package br.com.flaviogf.gerenciadordetimes;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PlayerTest {
    private static final Long TEAM_ID = 1L;
    private static final String TEAM_NAME = "Corinthians";
    private static final LocalDate CREATION_DATE = LocalDate.of(1910, 1, 1);
    private static final String MAIN_COLOR = "black";
    private static final String SECONDARY_COLOR = "white";

    private static final Long PLAYER_ID = 1L;
    private static final String PLAYER_NAME = "FRANK";
    private static final LocalDate BIRTHDAY = LocalDate.of(1990, 1, 1);
    private static final Integer OVERALL = 99;
    private static final BigDecimal BALANCE = new BigDecimal(100_000);

    private static final Team CORINTHIANS = new Team(TEAM_ID, TEAM_NAME, CREATION_DATE, MAIN_COLOR, SECONDARY_COLOR);

    private Player player;

    @Before
    public void setUp() {
        player = new Player(PLAYER_ID, PLAYER_NAME, BIRTHDAY, OVERALL, BALANCE);
    }

    @Test
    public void join_should_join_the_player_to_the_team() {
        player.join(CORINTHIANS);

        assertTrue(player.getTeam().isPresent());
        assertEquals(CORINTHIANS, player.getTeam().get());
    }
}
