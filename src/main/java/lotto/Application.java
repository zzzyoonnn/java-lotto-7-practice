package lotto;

import java.util.List;
import lotto.domain.LottoMachine;
import lotto.domain.LottoResult;
import lotto.domain.WinningLotto;
import lotto.domain.WinningMachine;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {

  public static void main(String[] args) {
    int money = InputView.getMoney();
    LottoMachine lottoMachine = new LottoMachine(money);
    List<Lotto> lottos = lottoMachine.run();

    OutputView.printLottoNumbers(lottos);

    WinningLotto winningLotto = new WinningMachine().run();
    LottoResult lottoResult = new LottoResult(lottos, winningLotto);
    OutputView.printResult(lottoResult, money);
  }
}
