import mypackage.ClassForTesting;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class TestClass {


	@Test
	public void test() {

		Iterator iteratorMock = mock(Iterator.class);
		when(iteratorMock.next()).thenReturn("Hello").thenReturn("World");
		String s = iteratorMock.next() + " " + iteratorMock.next();
		assertEquals("Hello World", s);

	}

	@Test
	public void testWithArgs() {
		Comparable comparable = mock(Comparable.class);
		when(comparable.compareTo("Test")).thenReturn(1);
		assertEquals(1, comparable.compareTo("Test"));
	}

	@Test
	public void testWithAnyArgs() {
		Comparable comparable = mock(Comparable.class);
		when(comparable.compareTo(anyInt())).thenReturn(-1);
		assertEquals(-1, comparable.compareTo(2));
	}

	@Test
	public void testWithCustomReturn() {
		ArrayList list = new ArrayList();
		ArrayList spy = spy(list);
		doReturn(1).when(spy).get(0);
	}

	@Test(expected = IOException.class)
	public void testWithThrow() throws IOException {
		OutputStream os = mock(OutputStream.class);
		OutputStreamWriter writer = new OutputStreamWriter(os);
		doThrow(new IOException()).when(os).close();
		writer.close();
	}

	@Test(expected = ArithmeticException.class)
	public void testWithThrow2() {
		ClassForTesting mock = mock(ClassForTesting.class);
		doThrow(new ArithmeticException()).when(mock).doSomething();
		mock.doSomething();
	}

	@Test
	public void testMethodVerify() throws IOException {
		OutputStream os = mock(OutputStream.class);
		OutputStreamWriter writer = new OutputStreamWriter(os);
		writer.close();
		verify(os).close();
	}

	@Test
	public void testMyMethodVerify(){
		ClassForTesting mock = mock(ClassForTesting.class);
		mock.doSomething();
		mock.doSomething();
		verify(mock, times(2)).doSomething();
	}
}
