package br.com.flaviogf.gerenciadordetimes;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Optional;

public class CreateTeamTest {
    private static final Long ID = 1L;
    private static final String NAME = "Corinthians";
    private static final LocalDate CREATION_DATE = LocalDate.of(1910, 1, 1);
    private static final String MAIN_UNIFORM_COLOR = "black";
    private static final String SECONDARY_UNIFORM_COLOR = "white";
    private static final Team CORINTHIANS = new Team(ID, NAME, CREATION_DATE, MAIN_UNIFORM_COLOR, SECONDARY_UNIFORM_COLOR);

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

        Result result = createTeam.execute(ID, NAME, CREATION_DATE, MAIN_UNIFORM_COLOR, SECONDARY_UNIFORM_COLOR);

        assertTrue(result.isSuccess());
    }

    @Test
    public void execute_should_add_a_new_team() {
        when(teamRepository.add(any())).thenReturn(Result.ok());

        createTeam.execute(ID, NAME, CREATION_DATE, MAIN_UNIFORM_COLOR, SECONDARY_UNIFORM_COLOR);

        verify(teamRepository).add(CORINTHIANS);
    }

    @Test
    public void execute_when_trying_add_a_team_with_a_existing_id_should_return_fail_result() {
        when(teamRepository.findOne(any())).thenReturn(Optional.of(CORINTHIANS));

        Result result = createTeam.execute(ID, NAME, CREATION_DATE, MAIN_UNIFORM_COLOR, SECONDARY_UNIFORM_COLOR);

        assertEquals("This team already exists.", result.getError());
        assertTrue(result.isFailure());
    }
}
