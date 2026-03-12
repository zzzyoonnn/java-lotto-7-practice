package lotto.domain;

import lotto.view.InputView;

public class LottoMachine {

  public void run() {
    int money = InputView.getMoney();
    LottoGenerator lottoGenerator = new LottoGenerator(money);
    lottoGenerator.run();
  }
}
