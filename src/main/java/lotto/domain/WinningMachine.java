package lotto.domain;

import java.util.List;
import lotto.view.InputView;

public class WinningMachine {

  public WinningLotto run() {
    List<Integer> winningNumbers = InputView.getWinningNumbers();
    int bonusNumber = InputView.getBonusNumber();

    return new WinningLotto(winningNumbers, bonusNumber);
  }
}
