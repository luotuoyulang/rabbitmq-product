package cn.luotuoyulang.rabbitmqproduct.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FanoutConfig {

    // 邮件队列
    private String FANOUT_EMAIL_QUEUE = "fanout_eamil_queue";

    // 短信队列
    private String FANOUT_SMS_QUEUE = "fanout_sms_queue";
    // 短信队列
    private String EXCHANGE_NAME = "fanoutExchange";

    // 1.定义队列邮件
    @Bean
    public Queue fanOutEamilQueue() {
        return new Queue(FANOUT_EMAIL_QUEUE);
    }

    @Bean
    public Queue fanOutSmsQueue() {
        return new Queue(FANOUT_SMS_QUEUE);
    }

    // 2.定义交换机
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(EXCHANGE_NAME);
    }

    /**
     * 3.队列与交换机绑定邮件队列
     * 队列和交换机进行绑定 参数名称和自定义队列和交换机方法名一致
     * @param fanOutEamilQueue
     * @param fanoutExchange
     * @return
     */
    @Bean
    Binding bindingExchangeEamil(Queue fanOutEamilQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanOutEamilQueue).to(fanoutExchange);
    }

    // 4.队列与交换机绑定短信队列
    @Bean
    Binding bindingExchangeSms(Queue fanOutSmsQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanOutSmsQueue).to(fanoutExchange);
    }
}
