package com.rahhali.sqs;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import io.awspring.cloud.sqs.listener.ContainerOptions;
import io.awspring.cloud.sqs.listener.MessageListenerContainerRegistry;
import io.awspring.cloud.sqs.listener.SqsMessageListenerContainer;
import java.time.Duration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "spring.cloud.aws.sqs.listener.poll-timeout=15")
class SqsApplicationTests {

    @Autowired
    private MessageListenerContainerRegistry messageListenerContainerRegistry;

    private ContainerOptions containerOptions;

    @BeforeEach
    void setup() {
        var container = (SqsMessageListenerContainer) messageListenerContainerRegistry.getListenerContainers().stream().findFirst().get();
        this.containerOptions = container.getContainerOptions();
    }

    @Test
    void pollTimeoutIs15Milliseconds() {
        assertThat(containerOptions.getPollTimeout()).isEqualTo(Duration.ofMillis(15));
    }

    @Test
    void listenerShutdownTimeoutIs20s() {
        assertThat(containerOptions.getListenerShutdownTimeout()).isEqualTo(Duration.ofSeconds(20));
    }

    @Test
    void acknowledgeShutdownTimeoutIs20s() {
        assertThat(containerOptions.getAcknowledgementShutdownTimeout()).isEqualTo(Duration.ofSeconds(20));
    }
}
