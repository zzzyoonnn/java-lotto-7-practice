package lotto.domain;

import java.util.List;
import lotto.Lotto;

public class LottoMachine {

  private final int money;

  public LottoMachine(int money) {
    this.money = money;
  }

  public List<Lotto> run() {
    LottoGenerator lottoGenerator = new LottoGenerator(money);

    return lottoGenerator.run();
  }
}
