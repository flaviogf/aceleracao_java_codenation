package br.com.flaviogf.gerenciadordetimes;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetColorOfTheOutsideTemTest {
    private static final Long CORINTHIANS_ID = 1L;
    private static final String CORINTHIANS_NAME = "Corinthians";
    private static final LocalDate CORINTHIANS_CREATION_DATE = LocalDate.of(1910, 1, 1);
    private static final String CORINTHIANS_MAIN_COLOR = "black";
    private static final String CORINTHIANS_SECONDARY_COLOR = "white";

    private static final Long OTHER_ID = 2L;
    private static final String OTHER_NAME = "OTHER";
    private static final LocalDate OTHER_CREATION_DATE = LocalDate.of(1910, 1, 1);
    private static final String OTHER_MAIN_COLOR = "black";
    private static final String OTHER_SECONDARY_COLOR = "white";

    private Team corinthians;
    private Team other;

    private TeamRepository teamRepository;

    private GetColorOfTheOutsideTeam getColorOfTheOutsideTeam;

    @Before
    public void setUp() {
        corinthians = new Team(CORINTHIANS_ID, CORINTHIANS_NAME, CORINTHIANS_CREATION_DATE, CORINTHIANS_MAIN_COLOR, CORINTHIANS_SECONDARY_COLOR);
        other = new Team(OTHER_ID, OTHER_NAME, OTHER_CREATION_DATE, OTHER_MAIN_COLOR, OTHER_SECONDARY_COLOR);

        teamRepository = mock(TeamRepository.class);

        getColorOfTheOutsideTeam = new GetColorOfTheOutsideTeam(teamRepository);
    }

    @Test
    public void execute_should_return_ok_result() {
        when(teamRepository.findOne(corinthians.getId())).thenReturn(Optional.of(corinthians));
        when(teamRepository.findOne(other.getId())).thenReturn(Optional.of(other));

        Result<String> result = getColorOfTheOutsideTeam.execute(corinthians.getId(), other.getId());

        assertTrue(result.isSuccess());
    }

    @Test
    public void execute_should_return_the_outside_color_of_the_outside_team() {
        when(teamRepository.findOne(corinthians.getId())).thenReturn(Optional.of(corinthians));
        when(teamRepository.findOne(other.getId())).thenReturn(Optional.of(other));

        Result<String> result = getColorOfTheOutsideTeam.execute(corinthians.getId(), other.getId());

        assertEquals(other.getOutsideColor(corinthians), result.getValue());
    }

    @Test
    public void execute_when_the_outside_team_does_not_exist_should_return_fail_result() {
        when(teamRepository.findOne(corinthians.getId())).thenReturn(Optional.of(corinthians));

        Result<String> result = getColorOfTheOutsideTeam.execute(corinthians.getId(), other.getId());

        assertTrue(result.isFailure());
    }

    @Test
    public void execute_when_the_home_team_does_not_exist_should_return_fail_result() {
        when(teamRepository.findOne(other.getId())).thenReturn(Optional.of(other));

        Result<String> result = getColorOfTheOutsideTeam.execute(corinthians.getId(), other.getId());

        assertTrue(result.isFailure());
    }
}
