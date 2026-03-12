package lotto.domain;

import lotto.Lotto;

public class LottoMatcher {

  public Rank match(Lotto lotto, WinningLotto winningLotto) {
    int matchCount = countMatch(lotto, winningLotto);
    boolean bonusMatch = checkBonusMatch(lotto, winningLotto);
    return Rank.valueOf(matchCount, bonusMatch);
  }

  private int countMatch(Lotto lotto, WinningLotto winningLotto) {
    return (int) lotto.getNumbers().stream()
            .filter(winningLotto.getWinningNumbers()::contains)
            .count();
  }

  private boolean checkBonusMatch(Lotto lotto, WinningLotto winningLotto) {
    return lotto.getNumbers().contains(winningLotto.getBonusNumber());
  }
}
