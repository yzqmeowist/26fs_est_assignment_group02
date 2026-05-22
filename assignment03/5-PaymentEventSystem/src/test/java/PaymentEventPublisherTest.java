import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class PaymentEventPublisherTest {

    // A. create a spy of PaymentEventPublisher to verify interactions with it during the test
    @Test
    void publishPaymentSuccessInvokesEachSubscribedListenerOnce() {
        PaymentEventPublisher publisher = spy(new PaymentEventPublisher());
        PaymentListener emailService = mock(PaymentListener.class);
        PaymentListener invoiceService = mock(PaymentListener.class);
        PaymentEvent event = new PaymentEvent("test", "test@example.com", 123.45);

        publisher.subscribe(emailService);
        publisher.subscribe(invoiceService);
        publisher.publishPaymentSuccess(event);

        verify(publisher, times(1)).publishPaymentSuccess(event);
        verify(emailService, times(1)).onPaymentSuccess(event);
        verify(invoiceService, times(1)).onPaymentSuccess(event);
        verifyNoMoreInteractions(emailService, invoiceService);
    }

    // B. test if the details of the payment event are correctly passed to the services
    @Test
    void publishPaymentSuccessPassesPaymentEventDetailsToServices() {
        PaymentEventPublisher publisher = new PaymentEventPublisher();
        PaymentListener emailService = mock(PaymentListener.class);
        PaymentListener invoiceService = mock(PaymentListener.class);
        PaymentEvent event = new PaymentEvent("test", "test@example.com", 234.56);
        ArgumentCaptor<PaymentEvent> emailEventCaptor = ArgumentCaptor.forClass(PaymentEvent.class);
        ArgumentCaptor<PaymentEvent> invoiceEventCaptor = ArgumentCaptor.forClass(PaymentEvent.class);

        publisher.subscribe(emailService);
        publisher.subscribe(invoiceService);
        publisher.publishPaymentSuccess(event);

        verify(emailService).onPaymentSuccess(emailEventCaptor.capture());
        verify(invoiceService).onPaymentSuccess(invoiceEventCaptor.capture());

        assertEquals(event.getPaymentId(), emailEventCaptor.getValue().getPaymentId());
        assertEquals(event.getCustomerEmail(), emailEventCaptor.getValue().getCustomerEmail());
        assertEquals(event.getAmount(), emailEventCaptor.getValue().getAmount());
        assertEquals(event.getPaymentId(), invoiceEventCaptor.getValue().getPaymentId());
        assertEquals(event.getCustomerEmail(), invoiceEventCaptor.getValue().getCustomerEmail());
        assertEquals(event.getAmount(), invoiceEventCaptor.getValue().getAmount());
    }

    // C. write additional tests without ArgumentCaptor
    @Test
    void publishPaymentCreatesCorrectEmailMessage() {
        PaymentEventPublisher publisher = new PaymentEventPublisher();
        EmailService emailService = new EmailService();
        PaymentEvent event = new PaymentEvent("test", "test@example.com", 345.67);

        publisher.subscribe(emailService);
        publisher.publishPaymentSuccess(event);

        assertEquals(
                "Payment confirmation sent to test@example.com for payment ID: test Amount: 345.67",
                emailService.getLastSentMessage()
        );
    }

}
