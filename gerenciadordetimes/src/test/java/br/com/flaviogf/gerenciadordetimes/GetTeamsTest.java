package br.com.flaviogf.gerenciadordetimes;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetTeamsTest {
    private static final Long TEAM_ID = 1L;
    private static final String TEAM_NAME = "Corinthians";
    private static final LocalDate CREATION_DATE = LocalDate.of(1910, 1, 1);
    private static final String MAIN_COLOR = "black";
    private static final String SECONDARY_COLOR = "white";

    private Team corinthians;

    private TeamRepository teamRepository;

    private GetTeams getTeams;

    @Before
    public void setUp() {
        corinthians = new Team(TEAM_ID, TEAM_NAME, CREATION_DATE, MAIN_COLOR, SECONDARY_COLOR);

        teamRepository = mock(TeamRepository.class);

        getTeams = new GetTeams(teamRepository);
    }

    @Test
    public void execute_should_return_a_list_of_player_ids() {
        when(teamRepository.findAll()).thenReturn(Collections.singletonList(corinthians));

        List<Long> teams = getTeams.execute();

        assertEquals(1, teams.size());
        assertEquals(corinthians.getId(), teams.get(0));
    }

    @Test
    public void execute_when_do_not_have_any_players_should_return_an_empty_list() {
        List<Long> teams = getTeams.execute();

        assertEquals(0, teams.size());
    }
}
