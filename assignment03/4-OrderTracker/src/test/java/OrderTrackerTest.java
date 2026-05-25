import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderTrackerTest {
    @Mock
    private DeliveryService deliveryService;

    @Mock
    private OrderDashboardService orderDashboardService;

    @Mock
    private AlertService alertService;

    private OrderTracker orderTracker;

    @BeforeEach
    void setup() {
        orderTracker = new OrderTracker(deliveryService, orderDashboardService, alertService);
    }

    // A. verify the updateOrderStatus method accurately updates the dashboard when new order data is received
    @ParameterizedTest
    @EnumSource(value = OrderStatus.class, names = "DELIVERED", mode = EnumSource.Mode.EXCLUDE)
    void updateOrderStatusUpdatesDashboardWithLatestStatus(OrderStatus orderStatus) {
        String orderId = "1";
        OrderUpdate latestUpdate = new OrderUpdate(
                orderId,
                orderStatus,
                "milestone"
        );

        when(deliveryService.getLatestUpdate(orderId)).thenReturn(latestUpdate);

        orderTracker.updateOrderStatus(orderId);

        verify(deliveryService).getLatestUpdate(orderId);
        verify(orderDashboardService).updateStatus(orderId, orderStatus);
        verifyNoInteractions(alertService);
    }

    // B. test if the system correctly triggers notifications when an order is delivered
    @Test
    void updateOrderStatusSendsCustomerAlertWhenOrderIsDelivered() {
        String orderId = "1";
        OrderUpdate latestUpdate = new OrderUpdate(
                orderId,
                OrderStatus.DELIVERED,
                "[location]"
        );

        when(deliveryService.getLatestUpdate(orderId)).thenReturn(latestUpdate);

        orderTracker.updateOrderStatus(orderId);

        ArgumentCaptor<String> orderIdCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);
        verify(alertService).sendCustomerAlert(orderIdCaptor.capture(), messageCaptor.capture());

        assertEquals(orderId, orderIdCaptor.getValue());
        assertEquals("Your order has been delivered: [location]", messageCaptor.getValue());
        verify(orderDashboardService).updateStatus(orderId, OrderStatus.DELIVERED);
    }

    // C. simulate the temporary unavailability of delivery tracking data to see how the system handles failures or interruptions in updates
    @Test
    void updateOrderStatusSendsCustomerAlertWhenTrackingIsUnavailable() {
        String orderId = "1";
        when(deliveryService.getLatestUpdate(orderId)).thenReturn(null);

        orderTracker.updateOrderStatus(orderId);

        ArgumentCaptor<String> orderIdCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);
        verify(alertService).sendCustomerAlert(orderIdCaptor.capture(), messageCaptor.capture());

        assertEquals(orderId, orderIdCaptor.getValue());
        assertEquals("Order tracking is temporarily unavailable. Please check again later.", messageCaptor.getValue());
        verify(deliveryService).getLatestUpdate(orderId);
        verifyNoInteractions(orderDashboardService);
    }

}
