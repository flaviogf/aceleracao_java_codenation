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
    private Team team;

    @Before
    public void setUp() {
        frank = new Player(PLAYER_ID, PLAYER_NAME, BIRTHDAY, OVERALL, BALANCE);
        team = new Team(TEAM_ID, TEAM_NAME, CREATION_DATE, MAIN_COLOR, SECONDARY_COLOR);
    }

    @Test
    public void add_player_should_add_the_player_to_the_team() {
        team.add(frank);

        assertEquals(1, team.getCountPlayers());

        assertTrue(team.getPlayer(frank.getId()).isPresent());
        assertEquals(frank, team.getPlayer(frank.getId()).get());

        assertTrue(frank.getTeam().isPresent());
        assertEquals(team, frank.getTeam().get());
    }

    @Test
    public void set_captain_should_set_the_current_captain_of_the_team() {
        team.setCaptain(frank);

        assertTrue(team.getCaptain().isPresent());
        assertEquals(frank, team.getCaptain().get());
    }
}
