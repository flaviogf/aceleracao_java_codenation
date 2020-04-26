package br.com.flaviogf.gerenciadordetimes;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;

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
    private static final BigDecimal SALARY = new BigDecimal(100_000);

    private Player frank;
    private Team corinthians;

    @Before
    public void setUp() {
        frank = new Player(PLAYER_ID, PLAYER_NAME, BIRTHDAY, OVERALL, SALARY);
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

    @Test
    public void get_the_best_player_should_return_the_best_player() {
        corinthians.add(new Player(2L, PLAYER_NAME, BIRTHDAY, 98, SALARY));
        corinthians.add(frank);
        corinthians.add(new Player(3L, PLAYER_NAME, BIRTHDAY, 96, SALARY));

        Optional<Player> theBestPlayer = corinthians.getTheBestPlayer();

        assertTrue(theBestPlayer.isPresent());
        assertEquals(frank, theBestPlayer.get());
    }

    @Test
    public void get_the_best_player_when_the_team_does_not_have_a_player_should_return_an_empty_optional() {
        Optional<Player> theBestPlayer = corinthians.getTheBestPlayer();

        assertFalse(theBestPlayer.isPresent());
    }

    @Test
    public void get_the_oldest_player_should_return_the_oldest_player() {
        corinthians.add(new Player(2L, PLAYER_NAME, LocalDate.of(1989, 1, 1), OVERALL, SALARY));
        corinthians.add(frank);
        corinthians.add(new Player(3L, PLAYER_NAME, LocalDate.of(1988, 1, 1), OVERALL, SALARY));

        Optional<Player> theOldestPlayer = corinthians.getTheOldestPlayer();

        assertTrue(theOldestPlayer.isPresent());
        assertEquals(frank, theOldestPlayer.get());
    }

    @Test
    public void get_the_oldest_player_when_the_team_does_not_have_a_player_should_return_an_empty_optional() {
        Optional<Player> theOldestPlayer = corinthians.getTheOldestPlayer();

        assertFalse(theOldestPlayer.isPresent());
    }

    @Test
    public void get_player_with_the_biggest_salary_should_return_the_player_with_the_biggest_salary() {
        corinthians.add(new Player(2L, PLAYER_NAME, BIRTHDAY, OVERALL, new BigDecimal(90_000)));
        corinthians.add(frank);
        corinthians.add(new Player(3L, PLAYER_NAME, BIRTHDAY, OVERALL, new BigDecimal(110_00)));

        Optional<Player> player = corinthians.getPlayerWithTheBiggestSalary();

        assertTrue(player.isPresent());
        assertEquals(frank, player.get());
    }

    @Test
    public void get_player_with_the_biggest_salary_when_the_team_does_not_have_a_player_should_return_an_empty_optional() {
        Optional<Player> player = corinthians.getPlayerWithTheBiggestSalary();

        assertFalse(player.isPresent());
    }
}
