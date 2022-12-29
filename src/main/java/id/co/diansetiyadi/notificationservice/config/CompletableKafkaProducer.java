package id.co.diansetiyadi.notificationservice.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class CompletableKafkaProducer<K, V> extends KafkaProducer<K, V> {
    public CompletableKafkaProducer(Properties properties) {
        super(properties);
    }

    public CompletableFuture<RecordMetadata> sendAsync(ProducerRecord<K, V> record) {
        final CompletableFuture<RecordMetadata> promise = new CompletableFuture<>();


        final Callback callback = new Callback() {

            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if (exception == null) {
                    log.debug("submit into " + metadata.topic() + "  (" + metadata.offset() + "@" + metadata.partition() + ")");
                    promise.complete(metadata);
                } else {
                    log.debug(exception.getMessage() + " error occured by writing data");
                    promise.completeExceptionally(exception);
                }
            }
        };

        super.send(record, callback);


        return promise;
    }
}
