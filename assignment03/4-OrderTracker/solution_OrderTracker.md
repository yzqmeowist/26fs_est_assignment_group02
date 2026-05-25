# OrderTracker Solution

We use JUnit 5 and Mockito to test the behavior of `OrderTracker.updateOrderStatus`. The external collaborators are replaced with
test doubles:

- `DeliveryService` is mocked to control the latest order update returned to the tracker.
- `OrderDashboardService` is mocked to verify whether the customer dashboard is updated with the expected status.
- `AlertService` is mocked to verify whether customer notifications are sent with the correct details.

A fresh `OrderTracker` is created before each test.

## A. Accuracy of Status Updates

The test `updateOrderStatusUpdatesDashboardWithLatestStatus` uses a parameterized test to verify that new order data is accurately reflected on the dashboard.

It covers every `OrderStatus` except `DELIVERED`: `PLACED`, `PREPARING`, `PICKED_UP` and `ON_THE_WAY`.

For each status, the mocked `DeliveryService` returns an `OrderUpdate` with the status. After calling `updateOrderStatus`, the test verifies that:

- `deliveryService.getLatestUpdate(orderId)` is called with the expected `orderId`.
- `orderDashboardService.updateStatus(orderId, orderStatus)` is called with the same status returned by the delivery service.
- `alertService` is not called as these statuses are not delivered milestones.

## B. Notification of Key Events

The test `updateOrderStatusSendsCustomerAlertWhenOrderIsDelivered` verifies that key events trigger notifications when an order is delivered.

The mocked `DeliveryService` returns an `OrderUpdate` with:

- order id `"1"`
- status `DELIVERED`
- milestone name `"[location]"`

`ArgumentCaptor<String>` is used to capture the arguments passed to `alertService.sendCustomerAlert`.

The captured values are then used to verify the details of the arguments passed to `AlertService`:

- The captured order id must be `"1"`.
- The captured message must be `"Your order has been delivered: [location]"`.

## C. Response to Tracking Failures

The test `updateOrderStatusSendsCustomerAlertWhenTrackingIsUnavailable` tests how the system handles temporary tracking failures simulating missing tracking data by making `deliveryService.getLatestUpdate(orderId)` return `null`.

`ArgumentCaptor<String>` is used to capture the arguments passed to `alertService.sendCustomerAlert`.

The captured values are then used to verify the details of the arguments passed to `AlertService`:

- The alert must be sent for the order id `"1"`.
- The message must be `"Order tracking is temporarily unavailable. Please check again later."`
- `orderDashboardService` has no interactions as there is no valid status to display.
