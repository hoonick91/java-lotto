package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Lottoes {

    private final List<LottoNumbers> value;

    public Lottoes(List<LottoNumbers> value) {
        this.value = value;
    }

    public List<LottoNumbers> getValue() {
        return value;
    }

    public LottoGameResult getResult(WinningNumbers winningNumbers) {
        List<Rank> ranks = value.stream()
                .map(winningNumbers::getRank)
                .collect(Collectors.toList());

        return new LottoGameResult(ranks, new PurchaseAmount(ranks));

    }
}
