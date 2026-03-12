package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoGeneratorTest {

  @DisplayName("1000원당 로또 1장이 생성된다.")
  @Test
  void 금액에_따라_올바른_개수의_로또가_생성된다() {
    LottoGenerator generator = new LottoGenerator(5000);

    List<Lotto> lottos = generator.run();

    assertThat(lottos).hasSize(5);
  }

  @DisplayName("1000원으로 로또 1장이 생성된다.")
  @Test
  void 최소_금액으로_로또_1장이_생성된다() {
    LottoGenerator generator = new LottoGenerator(1000);

    List<Lotto> lottos = generator.run();

    assertThat(lottos).hasSize(1);
  }

  @DisplayName("0원이면 로또가 생성되지 않는다.")
  @Test
  void 금액이_0원이면_로또가_생성되지_않는다() {
    LottoGenerator generator = new LottoGenerator(0);

    List<Lotto> lottos = generator.run();

    assertThat(lottos).isEmpty();
  }

  @DisplayName("생성된 로또의 번호는 6개이다.")
  @Test
  void 생성된_로또의_번호는_6개이다() {
    LottoGenerator generator = new LottoGenerator(5000);

    List<Lotto> lottos = generator.run();

    lottos.forEach(lotto ->
            assertThat(lotto.getNumbers()).hasSize(6)
    );
  }

  @DisplayName("생성된 로또의 번호는 1~45 사이이다.")
  @Test
  void 생성된_로또의_번호는_1에서_45_사이이다() {
    LottoGenerator generator = new LottoGenerator(5000);

    List<Lotto> lottos = generator.run();

    lottos.forEach(lotto ->
            assertThat(lotto.getNumbers())
                    .allMatch(number -> number >= 1 && number <= 45)
    );
  }

  @DisplayName("생성된 로또의 번호는 중복되지 않는다.")
  @Test
  void 생성된_로또의_번호는_중복되지_않는다() {
    LottoGenerator generator = new LottoGenerator(5000);

    List<Lotto> lottos = generator.run();

    lottos.forEach(lotto -> {
      List<Integer> numbers = lotto.getNumbers();
      assertThat(numbers).doesNotHaveDuplicates();
    });
  }
}