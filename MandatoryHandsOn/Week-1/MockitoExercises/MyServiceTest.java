import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MyServiceTest {

    @Mock
    ExternalApi mockApi;

    @InjectMocks
    MyService service;

    @Test
    void testExternalApi_MockingAndStubbing() {
        when(mockApi.getData()).thenReturn("Mock Data");
        String result = service.fetchData();
        assertEquals("Mock Data", result);
    }

    @Test
    void testVerifyInteraction() {
        when(mockApi.getData()).thenReturn("Some Data");
        service.fetchData();
        verify(mockApi, times(1)).getData();
    }

    @Test
    void testArgumentMatching() {
        when(mockApi.getDataWithParam(anyString())).thenReturn("Matched Data");
        when(mockApi.getDataWithParam("specific")).thenReturn("Specific Data");
        assertEquals("Specific Data", service.fetchByParam("specific"));
        verify(mockApi, atLeastOnce()).getDataWithParam(anyString());
    }

    @Test
    void testHandlingVoidMethod() {
        doNothing().when(mockApi).postData(anyString());
        service.sendData("Test Payload");
        verify(mockApi).postData("Test Payload");
    }

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

    @Test
    void testVoidMethodThrowsException() {
        doThrow(new RuntimeException("API Error")).when(mockApi).postData(anyString());
        assertThrows(RuntimeException.class, () -> service.sendData("bad data"));
        verify(mockApi).postData("bad data");
    }
}
