package br.com.flaviogf.gerenciadordetimes;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class SetCaptainTest {
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
    private final Player FRANK = new Player(PLAYER_ID, PLAYER_NAME, BIRTHDAY, OVERALL, BALANCE);

    private PlayerRepository playerRepository;

    private SetCaptain setCaptain;

    @Before
    public void setUp() {
        CORINTHIANS.add(FRANK);

        playerRepository = mock(PlayerRepository.class);

        setCaptain = new SetCaptain(playerRepository);
    }

    @Test
    public void execute_should_return_ok_result() {
        when(playerRepository.findOne(any())).thenReturn(Optional.of(FRANK));

        Result result = setCaptain.execute(PLAYER_ID);

        assertTrue(result.isSuccess());
    }

    @Test
    public void execute_should_set_the_new_captain_of_the_team() {
        when(playerRepository.findOne(any())).thenReturn(Optional.of(FRANK));

        Result result = setCaptain.execute(PLAYER_ID);

        assertTrue(CORINTHIANS.getCaptain().isPresent());
        assertEquals(CORINTHIANS.getCaptain().get(), FRANK);
    }

    @Test
    public void execute_when_the_player_was_not_found_should_return_fail_result() {
        Result result = setCaptain.execute(PLAYER_ID);

        assertTrue(result.isFailure());
    }
}
