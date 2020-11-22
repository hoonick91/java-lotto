package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class WinningNumberTest {

    public static final WinningNumber winningNumber = new WinningNumber("1, 2, 3, 4, 5, 6");

    @ParameterizedTest
    @DisplayName("입력받은 번호와 당첨번호의 일치하는 Rank를 반환한다.")
    @CsvSource(value = {
            "1, 7, 8, 9, 10, 11:1",
            "1, 2, 8, 9, 10, 11:2",
            "1, 2, 3, 9, 10, 11:3",
            "1, 2, 3, 4, 10, 11:4",
            "1, 2, 3, 4, 5, 11:5",
            "1, 2, 3, 4, 5, 6:6"
    }, delimiter = ':')
    void should_return_rank(String inputNumbers, int expectedCount) {
        //Given
        List<String> lottoNumbers = StringUtils.splitString(inputNumbers);
        List<Integer> numbers = IntegerUtils.parsePositiveInt(lottoNumbers);

        Rank expectedRank = Rank.value(expectedCount);

        //When & Then
        assertThat(winningNumber.getRank(numbers)).isEqualTo(expectedRank);

    }

    @Test
    @DisplayName("입력한 당첨번호에 따른 WinningNumber를 반환한다.")
    void should_return_winning_number() {
        //Given
        String inputNumbers = "1, 2, 3, 4, 5, 6";

        //When
        WinningNumber winningNumber = new WinningNumber(inputNumbers);

        //Then
        assertThat(winningNumber.getValue()).containsAll(Arrays.asList(1, 2, 3, 4, 5, 6));

    }

    @ParameterizedTest
    @DisplayName("입력한 당첨번호가 1~45 범위 밖의 수, 숫자가 6개가 아닐 때 IllegalArgumentException을 throw 한다.")
    @ValueSource(strings = {"1, 2, 4, 244, 12, 1", "1, 2, 3"})
    void should_throw_illegal_argument_exception(String numbers) {

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new WinningNumber(numbers))
                .withMessage(ErrorMessage.WINNING_NUMBER_ERROR);
    }
}
