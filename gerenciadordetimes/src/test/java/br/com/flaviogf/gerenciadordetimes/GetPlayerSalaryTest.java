package br.com.flaviogf.gerenciadordetimes;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetPlayerSalaryTest {
    private static final Long PLAYER_ID = 1L;
    private static final String PLAYER_NAME = "FRANK";
    private static final LocalDate BIRTHDAY = LocalDate.of(1990, 1, 1);
    private static final Integer OVERALL = 99;
    private static final BigDecimal SALARY = new BigDecimal(100_000);

    private Player frank;

    private PlayerRepository playerRepository;

    private GetPlayerSalary getPlayerSalary;

    @Before
    public void setUp() {
        frank = new Player(PLAYER_ID, PLAYER_NAME, BIRTHDAY, OVERALL, SALARY);

        playerRepository = mock(PlayerRepository.class);

        getPlayerSalary = new GetPlayerSalary(playerRepository);
    }

    @Test
    public void execute_should_return_the_player_salary() {
        when(playerRepository.findOne(any())).thenReturn(Optional.of(frank));

        Optional<BigDecimal> salary = getPlayerSalary.execute(PLAYER_ID);

        assertTrue(salary.isPresent());
        assertEquals(SALARY, salary.get());
    }

    @Test
    public void execute_when_the_player_does_not_exist_should_return_an_empty_optional() {
        Optional<BigDecimal> salary = getPlayerSalary.execute(PLAYER_ID);

        assertFalse(salary.isPresent());
    }
}
