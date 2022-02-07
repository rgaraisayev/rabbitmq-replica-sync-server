package com.termitpos.replicasyncserver.listener;

import com.termitpos.replicasyncserver.configuration.RabbitMQConfig;
import com.termitpos.replicasyncserver.model.SyncActionEvent;
import com.termitpos.replicasyncserver.service.ReplicaServerService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ReplicaServerListener {

    private final ReplicaServerService replicaServerService;

    public ReplicaServerListener(ReplicaServerService replicaServerService) {
        this.replicaServerService = replicaServerService;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_Q1, concurrency = "13")
    public SyncActionEvent handleSyncEvent(SyncActionEvent event) {
        return replicaServerService.handleSyncEvent(event);
    }

}
