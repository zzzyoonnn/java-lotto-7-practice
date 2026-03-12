package lotto.domain;


public class LottoGenerator {

  private static final int LOTTO_PRICE = 1_000;
  private final int money;

  public LottoGenerator(int money) {
    this.money = money;
  }

  public void run() {
    System.out.println(money + "원을 입력 받았습니다.");
    System.out.println(money / LOTTO_PRICE + "장의 로또를 구매합니다.");
    // 로또 번호 생성 로직
  }

}
