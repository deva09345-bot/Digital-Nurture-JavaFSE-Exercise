import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MyServiceTest {

    @Mock
    ExternalApi mockApi;

    @InjectMocks
    MyService service;

    // Exercise 1: Mocking and Stubbing
    @Test
    void testExternalApi_mockingAndStubbing() {
        when(mockApi.getData()).thenReturn("Mock Data");

        String result = service.fetchData();

        assertEquals("Mock Data", result);
    }

    // Exercise 2: Verifying Interactions
    @Test
    void testVerifyInteraction() {
        when(mockApi.getData()).thenReturn("Some Data");

        service.fetchData();

        verify(mockApi, times(1)).getData();
    }

    // Exercise 3: Argument Matching
    @Test
    void testArgumentMatching() {
        when(mockApi.getDataWithParam(anyString())).thenReturn("Matched Data");
        when(mockApi.getDataWithParam("specific")).thenReturn("Specific Data");

        assertEquals("Matched Data",  service.fetchByParam("anything"));
        assertEquals("Specific Data", service.fetchByParam("specific"));

        verify(mockApi).getDataWithParam(anyString());
    }

    // Exercise 4: Handling Void Methods
    @Test
    void testHandlingVoidMethod() {
        doNothing().when(mockApi).postData(anyString());

        service.sendData("Test Payload");

        verify(mockApi).postData("Test Payload");
    }

    // Exercise 5: Multiple Return Values
    @Test
    void testMultipleReturnValues() {
        when(mockApi.getData())
            .thenReturn("First Call")
            .thenReturn("Second Call")
            .thenReturn("Third Call");

        assertEquals("First Call",  service.fetchData());
        assertEquals("Second Call", service.fetchData());
        assertEquals("Third Call",  service.fetchData());
    }

    // Exercise 6: Verifying Interaction Order
    @Test
    void testInteractionOrder() {
        when(mockApi.getData()).thenReturn("Data");
        when(mockApi.getDataWithParam(anyString())).thenReturn("Param Data");

        service.fetchData();
        service.fetchByParam("param");

        InOrder inOrder = inOrder(mockApi);
        inOrder.verify(mockApi).getData();
        inOrder.verify(mockApi).getDataWithParam("param");
    }

    // Exercise 7: Void Method Throws Exception
    @Test
    void testVoidMethodThrowsException() {
        doThrow(new RuntimeException("API Error"))
            .when(mockApi).postData(anyString());

        assertThrows(RuntimeException.class, () -> service.sendData("bad data"));
        verify(mockApi).postData("bad data");
    }
}
