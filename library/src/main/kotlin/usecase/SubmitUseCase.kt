package usecase

import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory

class SubmitUseCase(private val requestBody: String) {

    private val rabbitMQHost = "localhost"
    private val rabbitMQPort = 32779
    private val rabbitMQQueueName = "contracts"

    fun run() {
        withConnection { rabbitMQ ->
            rabbitMQ.basicPublish("", rabbitMQQueueName, null, requestBody.toByteArray())
        }
    }

    private fun withConnection(function: (Channel) -> Unit) {
        var connection: Connection? = null
        var channel: Channel? = null
        try {
            connection = ConnectionFactory().apply {
                host = rabbitMQHost
                port = rabbitMQPort
            }.newConnection()
            channel = connection.createChannel()
            function(channel)
        } finally {
            channel?.close()
            connection?.close()
        }
    }
}
