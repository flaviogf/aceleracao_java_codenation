package br.com.flaviogf.gerenciadordetimes;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetTeamNameTest {
    private static final Long TEAM_ID = 1L;
    private static final String TEAM_NAME = "Corinthians";
    private static final LocalDate CREATION_DATE = LocalDate.of(1910, 1, 1);
    private static final String MAIN_COLOR = "black";
    private static final String SECONDARY_COLOR = "white";

    private Team corinthians;

    private TeamRepository teamRepository;

    private GetTeamName getTeamName;

    @Before
    public void setUp() {
        corinthians = new Team(TEAM_ID, TEAM_NAME, CREATION_DATE, MAIN_COLOR, SECONDARY_COLOR);

        teamRepository = mock(TeamRepository.class);

        getTeamName = new GetTeamName(teamRepository);
    }

    @Test
    public void execute_should_return_the_team_name() {
        when(teamRepository.findOne(any())).thenReturn(Optional.of(corinthians));

        Optional<String> teamName = getTeamName.execute(TEAM_ID);

        assertTrue(teamName.isPresent());
        assertEquals(TEAM_NAME, teamName.get());
    }

    @Test
    public void execute_when_the_team_does_not_exist_should_return_an_empty_optional() {
        Optional<String> teamName = getTeamName.execute(TEAM_ID);

        assertFalse(teamName.isPresent());
    }
}
