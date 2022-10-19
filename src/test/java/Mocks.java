import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class Mocks {
    @Test
    void testWithAMock2() {
        // creating a fake object that simulates to be our real ArrayList
        ArrayList<String> arrayListMock = mock(ArrayList.class);
        // if we call a method on our mock, it will return with default values, in this case with 0
        assertEquals(0, arrayListMock.size());
        // we can call methods that our real object implements on our mock
        // if we call them on our mock nothing will happen, except of the mock keepuing track of methods calls
        arrayListMock.add("test");
        // if we now call get(0) to get the first element in our arraylist
        // the mock responds with the default value and not with the real value
        // because the mock just simulates or fakes the real object but does not behave like the real object
        assertEquals(null, arrayListMock.get(0));
        // if we now call the size() method again on our mock it returns again with the default value 0
        // because the mock does not really add an element in the list
        assertEquals(0, arrayListMock.size());
        // we can stub certain methods on our mock object
        // here we stub the size() method
        // we tell the mock "when size()" gets called on the mock "then return with 3".
        when(arrayListMock.size()).thenReturn(3);
        // now we add a new element (nothing happens again)
        // usually we would not call a stubbed method in our test, this is just for understanding purposes
        // the stub would be a dependency that would be called in our unit that we test
        arrayListMock.add("test 2");
        // now if we check the length of the mock with size() we get a stubbed value back
        // in our case 3
        assertEquals(3, arrayListMock.size());
        // as mocks keep track of how often a method is called
        // we can verify that a method was called
        // here we verify that a add("test") method was only called once...
        verify(arrayListMock, times(1)).add("test");
    }

    @Test
    void testWithASpy() {
        // A spy is essentially a partial mock
        // if you create a spy you can use all of the original objects functionality
        // it is essentially like calling new ArrayList() and add some
        // useful functionality of a mock to it, like keeping track of method calls, etc...
        // but you can also stub some methods of this mock

        // so here we create a spy similar to a mock
        ArrayList<String> arrayListSpy = spy(ArrayList.class);
        // if we call size() on the spy the real method will be called
        assertEquals(0, arrayListSpy.size());
        // if we call add() the real method will be invoked
        // so we added an element to our arraylist
        arrayListSpy.add("test");
        // if we now call get(0) the first element in the list we will get back the real value
        assertEquals("test", arrayListSpy.get(0));
        // and for size() the real value
        assertEquals(1, arrayListSpy.size());
        // we can stub functionallity on a spy as needed
        when(arrayListSpy.size()).thenReturn(5);
        // still adding real elements to our list
        arrayListSpy.add("test 2");
        // still getting real values out of the spy
        assertEquals("test 2", arrayListSpy.get(1));
        // but getting the stubbed value as needed, here we call size() and as we stubbed this method it is not returning 3 as expected
        // it rather return the stub value 5
        assertEquals(5, arrayListSpy.size());
        // we can keep track of method calls on spys as well...
        verify(arrayListSpy, times(1)).add("test");
    }
}
