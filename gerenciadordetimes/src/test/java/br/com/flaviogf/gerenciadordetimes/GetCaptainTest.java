package br.com.flaviogf.gerenciadordetimes;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GetCaptainTest {
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

    private GetCaptain getCaptain;

    @Before
    public void setUp() {
        corinthians = new Team(TEAM_ID, TEAM_NAME, CREATION_DATE, MAIN_COLOR, SECONDARY_COLOR);
        frank = new Player(PLAYER_ID, PLAYER_NAME, BIRTHDAY, OVERALL, BALANCE);

        teamRepository = mock(TeamRepository.class);

        getCaptain = new GetCaptain(teamRepository);
    }

    @Test
    public void execute_should_return_ok_result() {
        when(teamRepository.findOne(any())).thenReturn(Optional.of(corinthians));

        corinthians.add(frank);

        corinthians.setCaptain(frank);

        Result<Optional<Long>> result = getCaptain.execute(corinthians.getId());

        assertTrue(result.isSuccess());
    }

    @Test
    public void execute_should_return_the_id_of_captain_of_the_team() {
        when(teamRepository.findOne(any())).thenReturn(Optional.of(corinthians));

        corinthians.add(frank);

        corinthians.setCaptain(frank);

        Result<Optional<Long>> result = getCaptain.execute(corinthians.getId());

        assertTrue(result.getValue().isPresent());
        assertTrue(result.getValue().get().isPresent());
        assertEquals(frank.getId(), result.getValue().get().get());
    }

    @Test
    public void execute_when_team_was_not_found_should_return_the_fail_result() {
        Result<Optional<Long>> result = getCaptain.execute(corinthians.getId());

        assertTrue(result.isFailure());
    }

    @Test
    public void execute_when_team_does_not_have_a_captain_should_return_the_ok_result() {
        when(teamRepository.findOne(any())).thenReturn(Optional.of(corinthians));

        Result<Optional<Long>> result = getCaptain.execute(corinthians.getId());

        assertTrue(result.isSuccess());
    }

    @Test
    public void execute_when_team_does_not_have_a_captain_should_return_an_empty_optional() {
        when(teamRepository.findOne(any())).thenReturn(Optional.of(corinthians));

        Result<Optional<Long>> result = getCaptain.execute(corinthians.getId());

        assertTrue(result.getValue().isPresent());
        assertFalse(result.getValue().get().isPresent());
    }
}
