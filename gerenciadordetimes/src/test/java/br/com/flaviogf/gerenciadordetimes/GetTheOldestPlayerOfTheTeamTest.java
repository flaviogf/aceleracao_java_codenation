package br.com.flaviogf.gerenciadordetimes;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetTheOldestPlayerOfTheTeamTest {
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

    private Team corinthians;
    private Player frank;

    private TeamRepository teamRepository;

    private GetTheOldestPlayerOfTheTeam getTheOldestPlayerOfTheTeam;

    @Before
    public void setUp() {
        corinthians = new Team(TEAM_ID, TEAM_NAME, CREATION_DATE, MAIN_COLOR, SECONDARY_COLOR);
        frank = new Player(PLAYER_ID, PLAYER_NAME, BIRTHDAY, OVERALL, SALARY);

        teamRepository = mock(TeamRepository.class);

        getTheOldestPlayerOfTheTeam = new GetTheOldestPlayerOfTheTeam(teamRepository);
    }

    @Test
    public void execute_should_return_ok_result() {
        when(teamRepository.findOne(any())).thenReturn(Optional.of(corinthians));

        corinthians.add(frank);

        Result<Optional<Long>> result = getTheOldestPlayerOfTheTeam.execute(TEAM_ID);

        assertTrue(result.isSuccess());
    }

    @Test
    public void execute_should_return_the_id_of_the_oldest_player_of_the_team() {
        when(teamRepository.findOne(any())).thenReturn(Optional.of(corinthians));

        corinthians.add(frank);

        Result<Optional<Long>> result = getTheOldestPlayerOfTheTeam.execute(TEAM_ID);

        assertTrue(result.getValue().isPresent());
        assertEquals(frank.getId(), result.getValue().get());
    }

    @Test
    public void execute_when_the_team_does_not_exist_should_return_fail_result() {
        Result<Optional<Long>> result = getTheOldestPlayerOfTheTeam.execute(TEAM_ID);

        assertTrue(result.isFailure());
    }

    @Test
    public void execute_when_the_team_does_not_have_a_player_should_return_an_empty_optional() {
        when(teamRepository.findOne(any())).thenReturn(Optional.of(corinthians));

        Result<Optional<Long>> result = getTheOldestPlayerOfTheTeam.execute(TEAM_ID);

        assertFalse(result.getValue().isPresent());
    }
}
