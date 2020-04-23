package br.com.flaviogf.gerenciadordetimes;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class CreatePlayerTest {
    private static final Long ID = 1L;
    private static final String PLAYER_NAME = "Philippe";
    private static final LocalDate BIRTHDAY = LocalDate.of(1994, 1, 1);
    private static final Integer OVERALL = 98;
    private static final BigDecimal BALANCE = new BigDecimal(100_000);

    private static final Long TEAM_ID = 1L;
    private static final String TEAM_NAME = "Corinthians";
    private static final LocalDate CREATION_DATE = LocalDate.of(1910, 1, 1);
    private static final String MAIN_UNIFORM_COLOR = "black";
    private static final String SECONDARY_UNIFORM_COLOR = "white";

    private static final Team CORINTHIANS = new Team(TEAM_ID, TEAM_NAME, CREATION_DATE, MAIN_UNIFORM_COLOR, SECONDARY_UNIFORM_COLOR);
    private static final Player PHILIPPE = new Player(ID, CORINTHIANS, PLAYER_NAME, BIRTHDAY, OVERALL, BALANCE);

    private TeamRepository teamRepository;
    private PlayerRepository playerRepository;
    private CreatePlayer createPlayer;

    @Before
    public void setUp() {
        teamRepository = mock(TeamRepository.class);
        playerRepository = mock(PlayerRepository.class);
        createPlayer = new CreatePlayer(teamRepository, playerRepository);
    }

    @Test
    public void execute_should_return_ok_result() {
        when(teamRepository.findOne(any())).thenReturn(Optional.of(CORINTHIANS));

        when(playerRepository.add(any())).thenReturn(Result.ok());

        Result result = createPlayer.execute(ID, TEAM_ID, PLAYER_NAME, BIRTHDAY, OVERALL, BALANCE);

        assertTrue(result.isSuccess());
    }

    @Test
    public void execute_should_add_a_new_player() {
        when(teamRepository.findOne(any())).thenReturn(Optional.of(CORINTHIANS));

        when(playerRepository.add(any())).thenReturn(Result.ok());

        createPlayer.execute(ID, TEAM_ID, PLAYER_NAME, BIRTHDAY, OVERALL, BALANCE);

        verify(playerRepository).add(PHILIPPE);
    }

    @Test
    public void execute_when_trying_add_a_player_with_a_existing_id_should_return_fail_result() {
        when(playerRepository.findOne(any())).thenReturn(Optional.of(PHILIPPE));

        Result result = createPlayer.execute(ID, TEAM_ID, PLAYER_NAME, BIRTHDAY, OVERALL, BALANCE);

        assertEquals("This player already exists.", result.getError());
        assertTrue(result.isFailure());
    }

    @Test
    public void execute_when_trying_add_a_player_with_a_non_existing_team_should_return_fail_result() {
        when(teamRepository.findOne(any())).thenReturn(Optional.empty());

        Result result = createPlayer.execute(ID, TEAM_ID, PLAYER_NAME, BIRTHDAY, OVERALL, BALANCE);

        assertEquals("This team not exists.", result.getError());
        assertTrue(result.isFailure());
    }
}
