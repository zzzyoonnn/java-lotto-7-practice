package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호가 6개 미만이면 예외가 발생한다.")
    @Test
    void 로또_번호의_개수가_6개_미만이면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("빈 리스트로 로또를 생성하면 예외가 발생한다.")
    @Test
    void 빈_리스트로_생성하면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호가 6개가 아니면 올바른 에러 메시지가 반환된다.")
    @Test
    void 로또_번호_개수_예외_메시지_검증() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("올바른 6개의 번호로 로또를 생성할 수 있다.")
    @Test
    void 정상적인_로또_생성() {
        assertThatNoException()
                .isThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6)));
    }

    @DisplayName("로또 번호는 오름차순으로 정렬되어 저장된다.")
    @Test
    void 로또_번호가_오름차순으로_정렬된다() {
        Lotto lotto = new Lotto(List.of(6, 3, 1, 4, 5, 2));

        assertThat(lotto.getNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @DisplayName("getNumbers()는 수정 불가능한 리스트를 반환한다.")
    @Test
    void 번호_리스트는_수정할_수_없다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        assertThatThrownBy(() -> lotto.getNumbers().add(7))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("로또 번호의 내용이 입력값과 동일하다.")
    @Test
    void 로또_번호_내용_검증() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        assertThat(lotto.getNumbers()).containsExactlyInAnyOrder(1, 2, 3, 4, 5, 6);
    }
}
