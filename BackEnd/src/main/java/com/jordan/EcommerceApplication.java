package com.jordan;

import com.jordan.common.Receiver;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

@ServletComponentScan
@SpringBootApplication
public class EcommerceApplication {

	static final String topicExchangeName = "inventory-exchange";
	static final String queueName = "inventory";

	@Bean
	Queue queue() {

		return new Queue(queueName, false);
	}

	@Bean
	TopicExchange exchange() {

		return new TopicExchange(topicExchangeName);
	}

	//    binds queues and exchanges
//    defines the behavior that occurs when RabbitTemplate publishes to an exchange
	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder
				.bind(queue).to(exchange)
				.with("foo.bar.#");
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
											 MessageListenerAdapter listenerAdapter) {

		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();

		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(listenerAdapter);
		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}


	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

}
