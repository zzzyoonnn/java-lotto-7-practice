package lotto.domain;

import java.util.List;
import lotto.view.InputView;

public class WinningMachine {

  public WinningLotto run() {
    List<Integer> winningNumbers = InputView.getWinningNumbers();

    while (true) {
      try {
        int bonusNumber = InputView.getBonusNumber();
        return new WinningLotto(winningNumbers, bonusNumber);
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    }
  }
}
