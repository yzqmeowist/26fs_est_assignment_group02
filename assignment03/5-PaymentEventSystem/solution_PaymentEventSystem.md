# PaymentEventSystem Solution

We use JUnit 5 and Mockito to test the behavior of `PaymentListener.onPaymentSuccess`.

## A. Number of invocations

We use a spy of `PaymentEventPublisher` and mock listeners for the email service and the invoice service.

The test subscribes both listeners and publishes one `PaymentEvent` and checks that `publishPaymentSuccess` is called once and that each listener receives exactly one `onPaymentSuccess` call.

## B. Content of invocations - `ArgumentCaptor`

We use mock listeners for the email service and the invoice service, and `ArgumentCaptor` to capture `PaymentEvent` that is passed to each listener.

The test publishes a `PaymentEvent` through the `PaymentEventPublisher` and checks that the captured events contain the correct payment ID, customer email, and amount.

## C. Content of invocations - Increasing observability

We use the real `EmailService`, which stores the last generated email message in `lastSentMessage` that can be read with `getLastSentMessage`, instead of a mock.

The test publishes a `PaymentEvent` through `PaymentEventPublisher` and checks that the stored email message contains the correct customer email, payment ID, and amount.

## D. Advantages

Using `ArgumentCaptor` checks that the `PaymentEventPublisher` forwards the correct `PaymentEvent` to both subscribed services. It verifies the exact argument that was passed to a mock object, which keeps the test focused on the publisher's responsibility and does not require the real `EmailService` or `InvoiceService` implementations to run. 

Increasing the observability of `EmailService` allows the test to verify the real behavior of the service after it receives the event. By exposing the last generated email message through `getLastSentMessage`, the test can check the exact final message content, which means the test is closer to the visible result of the service and can catch not only mistakes in how the event was passed, but mistakes in how the email message is constructed as well.
