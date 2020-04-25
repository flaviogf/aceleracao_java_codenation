package br.com.flaviogf.gerenciadordetimes;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetPlayersOfTheTeamTest {
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

    private Team corinthians;
    private Player frank;

    private TeamRepository teamRepository;

    private GetPlayersOfTheTeam getPlayersOfTheTeam;

    @Before
    public void setUp() {
        corinthians = new Team(TEAM_ID, TEAM_NAME, CREATION_DATE, MAIN_COLOR, SECONDARY_COLOR);
        frank = new Player(PLAYER_ID, PLAYER_NAME, BIRTHDAY, OVERALL, BALANCE);

        teamRepository = mock(TeamRepository.class);

        getPlayersOfTheTeam = new GetPlayersOfTheTeam(teamRepository);
    }

    @Test
    public void execute_should_return_ok_result() {
        when(teamRepository.findOne(any())).thenReturn(Optional.of(corinthians));

        corinthians.add(frank);

        Result<List<Long>> result = getPlayersOfTheTeam.execute(TEAM_ID);

        assertTrue(result.isSuccess());
    }

    @Test
    public void execute_should_return_a_list_with_the_players_of_the_team() {
        when(teamRepository.findOne(any())).thenReturn(Optional.of(corinthians));

        corinthians.add(frank);

        Result<List<Long>> result = getPlayersOfTheTeam.execute(TEAM_ID);

        assertTrue(result.getValue().isPresent());
        assertEquals(1, result.getValue().get().size());
        assertEquals(PLAYER_ID, result.getValue().get().get(0));
    }

    @Test
    public void execute_when_the_team_does_not_exist_should_return_fail_result() {
        Result<List<Long>> result = getPlayersOfTheTeam.execute(TEAM_ID);

        assertTrue(result.isFailure());
    }
}
