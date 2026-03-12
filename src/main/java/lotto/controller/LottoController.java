package lotto.controller;

import java.util.List;
import lotto.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoResult;
import lotto.domain.WinningLotto;
import lotto.domain.WinningMachine;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

  public void run() {
    int money = InputView.getMoney();
    List<Lotto> lottos = new LottoMachine(money).run();
    OutputView.printLottoNumbers(lottos);

    WinningLotto winningLotto = new WinningMachine().run();
    LottoResult lottoResult = new LottoResult(lottos, winningLotto);
    OutputView.printResult(lottoResult, money);
  }
}
