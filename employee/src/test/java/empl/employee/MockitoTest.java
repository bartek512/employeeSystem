package empl.employee;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

public class MockitoTest {

    @Test
    public void returnValue() {
        // given
        final IntSupplier intSupplier = Mockito.mock(IntSupplier.class);
        when(intSupplier.getInt()).thenReturn(1);
        // when
        final int value = intSupplier.getInt();
        // then
        assertEquals(1, value);
    }

    @Test
    public void returnValue_fewSameCalls() {
        // given
        final IntSupplier intSupplier = Mockito.mock(IntSupplier.class);
        when(intSupplier.getInt()).thenReturn(1);
        // then
        assertEquals(1, intSupplier.getInt());
        assertEquals(1, intSupplier.getInt());
        assertEquals(1, intSupplier.getInt());
    }

    @Test
    public void returnValue_fewDifferentCalls() {
        // given
        final IntSupplier intSupplier = Mockito.mock(IntSupplier.class);
        when(intSupplier.getInt()).thenReturn(1)
                .thenReturn(2);
        // then
        assertEquals(1, intSupplier.getInt());
        assertEquals(2, intSupplier.getInt());
        assertEquals(2, intSupplier.getInt());
    }

    @Test
    public void reset_onMock() {
        // given
        final IntSupplier intSupplier = Mockito.mock(IntSupplier.class);
        when(intSupplier.getInt()).thenReturn(1)
                .thenReturn(2);
        // then
        assertEquals(1, intSupplier.getInt());
        assertEquals(2, intSupplier.getInt());
        Mockito.reset(intSupplier);
        assertEquals(0, intSupplier.getInt());
    }

    /**
     * when().thenReturn() - dla mockow jest OK
     */
    @Test
    public void spy_incorrectWay() {
        // given
        final List<String> spy = Mockito.spy(new LinkedList<>());
        // then
        final IndexOutOfBoundsException ex = assertThrows(IndexOutOfBoundsException.class, () -> {
            when(spy.get(0)).thenReturn("foo");
        });
        assertEquals("Index: 0, Size: 0", ex.getMessage());
    }

    @Test
    public void spy_correctWay() {
        // given
        final List<String> spy = Mockito.spy(new LinkedList<>());
        doReturn("foo").when(spy)
                .get(0);
        // then
        assertEquals("foo", spy.get(0));
        assertEquals("foo", spy.get(0));
        assertEquals("foo", spy.get(0));
    }

    static class IntSupplier {

        public int getInt() {
            return -1;
        }

    }

}
