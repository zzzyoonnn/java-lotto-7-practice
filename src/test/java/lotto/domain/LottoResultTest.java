package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.Lotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoResultTest {

  private WinningLotto winningLotto;

  @BeforeEach
  void setUp() {
    winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
  }

  @DisplayName("결과 맵은 모든 Rank를 키로 가진다.")
  @Test
  void 결과_맵은_모든_Rank를_포함한다() {
    LottoResult result = new LottoResult(List.of(), winningLotto);

    assertThat(result.getResults().keySet())
            .containsExactlyInAnyOrderElementsOf(List.of(Rank.values()));
  }

  @DisplayName("getResults()는 수정 불가능한 맵을 반환한다.")
  @Test
  void 결과_맵은_수정할_수_없다() {
    LottoResult result = new LottoResult(List.of(), winningLotto);

    assertThatThrownBy(() -> result.getResults().put(Rank.FIRST, 1))
            .isInstanceOf(UnsupportedOperationException.class);
  }

  @DisplayName("숫자_3개 일치하면 5등이다.")
  @Test
  void 숫자_3개_일치하면_5등이다() {
    List<Lotto> lottos = List.of(new Lotto(List.of(1, 2, 3, 10, 11, 12)));

    LottoResult result = new LottoResult(lottos, winningLotto);

    assertThat(result.getResults().get(Rank.FIFTH)).isEqualTo(1);
  }

  @DisplayName("숫자_4개 일치하면 4등이다.")
  @Test
  void 숫자_4개_일치하면_4등이다() {
    List<Lotto> lottos = List.of(new Lotto(List.of(1, 2, 3, 4, 10, 11)));

    LottoResult result = new LottoResult(lottos, winningLotto);

    assertThat(result.getResults().get(Rank.FOURTH)).isEqualTo(1);
  }

  @DisplayName("숫자_5개 일치하고 보너스 불일치하면 3등이다.")
  @Test
  void 숫자_5개_일치하고_보너스_불일치하면_3등이다() {
    List<Lotto> lottos = List.of(new Lotto(List.of(1, 2, 3, 4, 5, 10)));

    LottoResult result = new LottoResult(lottos, winningLotto);

    assertThat(result.getResults().get(Rank.THIRD)).isEqualTo(1);
  }

  @DisplayName("숫자_5개 일치하고 보너스 일치하면 2등이다.")
  @Test
  void 숫자_5개_일치하고_보너스_일치하면_2등이다() {
    List<Lotto> lottos = List.of(new Lotto(List.of(1, 2, 3, 4, 5, 7)));

    LottoResult result = new LottoResult(lottos, winningLotto);

    assertThat(result.getResults().get(Rank.SECOND)).isEqualTo(1);
  }

  @DisplayName("숫자_6개 일치하면 1등이다.")
  @Test
  void 숫자_6개_일치하면_1등이다() {
    List<Lotto> lottos = List.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)));

    LottoResult result = new LottoResult(lottos, winningLotto);

    assertThat(result.getResults().get(Rank.FIRST)).isEqualTo(1);
  }

  @DisplayName("숫자_2개 이하 일치하면 미당첨이다.")
  @Test
  void 숫자_2개_이하_일치하면_미당첨이다() {
    List<Lotto> lottos = List.of(new Lotto(List.of(1, 2, 10, 11, 12, 13)));

    LottoResult result = new LottoResult(lottos, winningLotto);

    assertThat(result.getResults().get(Rank.MISS)).isEqualTo(1);
  }

  @DisplayName("여러 장의 로또 결과를 한번에 집계한다.")
  @Test
  void 여러_장의_로또를_집계한다() {
    List<Lotto> lottos = List.of(
            new Lotto(List.of(1, 2, 3, 10, 11, 12)),  // 5등
            new Lotto(List.of(1, 2, 3, 10, 11, 12)),  // 5등
            new Lotto(List.of(10, 11, 12, 13, 14, 15)) // 미당첨
    );

    LottoResult result = new LottoResult(lottos, winningLotto);

    assertThat(result.getResults().get(Rank.FIFTH)).isEqualTo(2);
    assertThat(result.getResults().get(Rank.MISS)).isEqualTo(1);
  }

  @DisplayName("당첨이 없으면 수익률은 0.0이다.")
  @Test
  void 당첨이_없으면_수익률은_0이다() {
    List<Lotto> lottos = List.of(new Lotto(List.of(10, 11, 12, 13, 14, 15)));

    LottoResult result = new LottoResult(lottos, winningLotto);

    assertThat(result.calculateProfitRate(1000)).isEqualTo(0.0);
  }

  @DisplayName("숫자_5등 1개 당첨 시 수익률은 500.0%이다.")
  @Test
  void 숫자_5등_1개_당첨_시_수익률을_계산한다() {
    List<Lotto> lottos = List.of(new Lotto(List.of(1, 2, 3, 10, 11, 12)));

    LottoResult result = new LottoResult(lottos, winningLotto);

    assertThat(result.calculateProfitRate(1000)).isEqualTo(500.0);
  }

  @DisplayName("수익률은 소수점 첫째 자리까지 반올림된다.")
  @Test
  void 수익률은_소수점_첫째_자리까지_반올림된다() {
    List<Lotto> lottos = List.of(
            new Lotto(List.of(1, 2, 3, 10, 11, 12)),  // 5등 5000원
            new Lotto(List.of(10, 11, 12, 13, 14, 15)),
            new Lotto(List.of(10, 11, 12, 13, 14, 15))
    );

    LottoResult result = new LottoResult(lottos, winningLotto);

    assertThat(result.calculateProfitRate(3000)).isEqualTo(166.7);
  }
}
