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

  public void run() {
    System.out.println(money + "원을 입력 받았습니다.");
    int count = money / LOTTO_PRICE;
    System.out.println(count + "장의 로또를 구매합니다.");

    List<Lotto> lottos = generateLottos(count);
    System.out.println(lottos.size() + "개를 구매했습니다.");
    for (Lotto lotto : lottos) {
      System.out.println(lotto.getNumbers());
    }
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
