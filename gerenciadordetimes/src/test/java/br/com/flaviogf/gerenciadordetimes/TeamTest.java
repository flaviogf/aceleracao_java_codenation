package br.com.flaviogf.gerenciadordetimes;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TeamTest {
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

    private Player frank;
    private Team corinthians;

    @Before
    public void setUp() {
        frank = new Player(PLAYER_ID, PLAYER_NAME, BIRTHDAY, OVERALL, BALANCE);
        corinthians = new Team(TEAM_ID, TEAM_NAME, CREATION_DATE, MAIN_COLOR, SECONDARY_COLOR);
    }

    @Test
    public void add_player_should_add_the_player_to_the_team() {
        corinthians.add(frank);

        assertEquals(1, corinthians.getCountPlayers());

        assertTrue(corinthians.getPlayer(frank.getId()).isPresent());
        assertEquals(frank, corinthians.getPlayer(frank.getId()).get());

        assertTrue(frank.getTeam().isPresent());
        assertEquals(corinthians, frank.getTeam().get());
    }

    @Test
    public void set_captain_should_set_the_current_captain_of_the_team() {
        corinthians.setCaptain(frank);

        assertTrue(corinthians.getCaptain().isPresent());
        assertEquals(frank, corinthians.getCaptain().get());
    }
}
