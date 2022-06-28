package tech.uept.fuel.client.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

public class Producer {

	private DefaultMQProducer producer;

	public Producer(DefaultMQProducer producer) {
		this.producer = producer;
	}

	public void send(Message message)
			throws MQClientException, RemotingException, MQBrokerException, InterruptedException {
		producer.send(message);
	}
}
