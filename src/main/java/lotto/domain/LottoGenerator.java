package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import lotto.Lotto;

public class LottoGenerator {

  private static final int LOTTO_PRICE = 1_000;
  private final int money;

  public LottoGenerator(int money) {
    this.money = money;
  }

  public List<Lotto> run() {
    int count = money / LOTTO_PRICE;

    return generateLottos(count);
  }

  private List<Lotto> generateLottos(int count) {
    List<Lotto> lottos = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      lottos.add(generateLotto());
    }
    return lottos;
  }

  private Lotto generateLotto() {
    List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
    return new Lotto(numbers);
  }

}
