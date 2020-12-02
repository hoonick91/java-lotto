package lotto.domain;

import lotto.constant.ErrorMessage;

import java.util.List;
import java.util.stream.Collectors;

public class LottoNumbers {

    public static final int NUMBER_RANGE = 6;

    private final List<LottoNumber> value;

    public LottoNumbers(List<LottoNumber> value) {
        valid(value);
        this.value = value;
    }

    public List<LottoNumber> getValue() {
        return value;
    }

    private void valid(List<LottoNumber> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(ErrorMessage.WINNING_NUMBER_ERROR);
        }

        if (isExistsDuplicateNumber(numbers)) {
            throw new IllegalArgumentException(ErrorMessage.NOT_ALLOW_DUPLICATED);
        }
    }

    private boolean isExistsDuplicateNumber(List<LottoNumber> numbers) {
        return numbers.stream()
                .map(LottoNumber::getValue)
                .distinct()
                .count() != NUMBER_RANGE;
    }

    public boolean hasLottoNumber(LottoNumber lottoNumber) {
        return value.stream()
                .map(LottoNumber::getValue)
                .collect(Collectors.toList())
                .contains(lottoNumber.getValue());
    }
}
