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

    OutputView.printLottoNumbers(lottos);

    List<Integer> winningNumbers = InputView.getWinningNumbers();
    int bonusNumber = InputView.getBonusNumber();
    WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
    LottoResult lottoResult = new LottoResult(lottos, winningLotto);

    OutputView.printResult(lottoResult, money);
  }
}
