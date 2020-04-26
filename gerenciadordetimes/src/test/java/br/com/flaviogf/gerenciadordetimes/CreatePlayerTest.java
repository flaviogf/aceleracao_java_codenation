package br.com.flaviogf.gerenciadordetimes;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class CreatePlayerTest {
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

    private PlayerRepository playerRepository;
    private TeamRepository teamRepository;

    private CreatePlayer createPlayer;

    @Before
    public void setUp() {
        corinthians = new Team(TEAM_ID, TEAM_NAME, CREATION_DATE, MAIN_COLOR, SECONDARY_COLOR);
        frank = new Player(PLAYER_ID, PLAYER_NAME, BIRTHDAY, OVERALL, SALARY);

        playerRepository = mock(PlayerRepository.class);
        teamRepository = mock(TeamRepository.class);

        createPlayer = new CreatePlayer(playerRepository, teamRepository);
    }

    @Test
    public void execute_should_return_ok_result() {
        when(playerRepository.add(any())).thenReturn(Result.ok());
        when(teamRepository.findOne(any())).thenReturn(Optional.of(corinthians));

        Result<Void> result = createPlayer.execute(PLAYER_ID, TEAM_ID, PLAYER_NAME, BIRTHDAY, OVERALL, SALARY);

        assertTrue(result.isSuccess());
    }

    @Test
    public void execute_should_add_player_to_the_team() {
        when(playerRepository.add(any())).thenReturn(Result.ok());
        when(teamRepository.findOne(any())).thenReturn(Optional.of(corinthians));

        createPlayer.execute(PLAYER_ID, TEAM_ID, PLAYER_NAME, BIRTHDAY, OVERALL, SALARY);

        assertEquals(1, corinthians.getCountPlayers());
    }

    @Test
    public void execute_when_id_is_already_in_use_should_return_fail_result() {
        when(playerRepository.findOne(any())).thenReturn(Optional.of(frank));

        Result<Void> result = createPlayer.execute(PLAYER_ID, TEAM_ID, PLAYER_NAME, BIRTHDAY, OVERALL, SALARY);

        assertTrue(result.isFailure());
    }

    @Test
    public void execute_when_team_does_not_exist_should_return_fail_result() {
        Result<Void> result = createPlayer.execute(PLAYER_ID, TEAM_ID, PLAYER_NAME, BIRTHDAY, OVERALL, SALARY);

        assertTrue(result.isFailure());
    }
}
