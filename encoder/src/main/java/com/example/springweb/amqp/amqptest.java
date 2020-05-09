package com.example.springweb.amqp;



import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class amqptest{
    private static final boolean ND = false;
    private static final String MY_QUEUE_NAME = "fileQueue";

    public static RabbitTemplate template;
//    @Bean
//    public Queue myQueue()
//    {
//        return new Queue(MY_QUEUE_NAME, ND);
//    }
//
    @Bean
    public ApplicationRunner runner(RabbitTemplate newtemplate)
    {
        amqptest.template = newtemplate;
        return args -> {
            template.convertAndSend(MY_QUEUE_NAME, "start");
        };
    }
}
