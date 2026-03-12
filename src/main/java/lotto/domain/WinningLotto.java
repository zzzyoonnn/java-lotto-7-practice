package lotto.domain;

import java.util.HashSet;
import java.util.List;

public class WinningLotto {

  private final List<Integer> winningNumbers;
  private final int bonusNumber;

  public WinningLotto(List<Integer> winningNumbers, int bonusNumber) {
    validate(winningNumbers, bonusNumber);
    this.winningNumbers = winningNumbers;
    this.bonusNumber = bonusNumber;
  }

  private void validate(List<Integer> winningNumbers, int bonusNumber) {
    if (winningNumbers.size() != 6) {
      throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 합니다.");
    }
    if (new HashSet<>(winningNumbers).size() != winningNumbers.size()) {
      throw new IllegalArgumentException("[ERROR] 당첨 번호는 중복될 수 없습니다.");
    }
    if (winningNumbers.contains(bonusNumber)) {
      throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }
  }
}
