package br.com.flaviogf.gerenciadordetimes;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GetPlayerNameTest {
    private static final Long PLAYER_ID = 1L;
    private static final String PLAYER_NAME = "FRANK";
    private static final LocalDate BIRTHDAY = LocalDate.of(1990, 1, 1);
    private static final Integer OVERALL = 99;
    private static final BigDecimal BALANCE = new BigDecimal(100_000);

    private Player frank;

    private PlayerRepository playerRepository;

    private GetPlayerName getPlayerName;

    @Before
    public void setUp() {
        frank = new Player(PLAYER_ID, PLAYER_NAME, BIRTHDAY, OVERALL, BALANCE);

        playerRepository = mock(PlayerRepository.class);

        getPlayerName = new GetPlayerName(playerRepository);
    }

    @Test
    public void execute_should_return_the_player_name() {
        when(playerRepository.findOne(any())).thenReturn(Optional.of(frank));

        Optional<String> playerName = getPlayerName.execute(PLAYER_ID);

        assertTrue(playerName.isPresent());
        assertEquals(PLAYER_NAME, playerName.get());
    }

    @Test
    public void execute_when_the_player_does_not_exist_should_return_an_empty_optional() {
        Optional<String> playerName = getPlayerName.execute(PLAYER_ID);

        assertFalse(playerName.isPresent());
    }
}
