package br.com.flaviogf.gerenciadordetimes;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class CreateTeamTest {
    private static final Long TEAM_ID = 1L;
    private static final String TEAM_NAME = "Corinthians";
    private static final LocalDate CREATION_DATE = LocalDate.of(1910, 1, 1);
    private static final String MAIN_COLOR = "black";
    private static final String SECONDARY_COLOR = "white";

    private static final Team CORINTHIANS = new Team(TEAM_ID, TEAM_NAME, CREATION_DATE, MAIN_COLOR, SECONDARY_COLOR);

    private TeamRepository teamRepository;

    private CreateTeam createTeam;

    @Before
    public void setUp() {
        teamRepository = mock(TeamRepository.class);

        createTeam = new CreateTeam(teamRepository);
    }

    @Test
    public void execute_should_return_ok_result() {
        when(teamRepository.add(any())).thenReturn(Result.ok());

        Result result = createTeam.execute(TEAM_ID, TEAM_NAME, CREATION_DATE, MAIN_COLOR, SECONDARY_COLOR);

        assertTrue(result.isSuccess());
    }

    @Test
    public void execute_when_id_is_already_in_use_should_return_fail_result() {
        when(teamRepository.findOne(any())).thenReturn(Optional.of(CORINTHIANS));

        Result result = createTeam.execute(TEAM_ID, TEAM_NAME, CREATION_DATE, MAIN_COLOR, SECONDARY_COLOR);

        assertTrue(result.isFailure());
    }
}
