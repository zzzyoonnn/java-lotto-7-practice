package lotto.domain;

import java.util.List;
import lotto.Lotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoMachine {


  public void run() {
    int money = InputView.getMoney();
    LottoGenerator lottoGenerator = new LottoGenerator(money);
    List<Lotto> lottos = lottoGenerator.run();

    // 출력문 실행
    OutputView.printLottoNumbers(lottos);
  }
}
