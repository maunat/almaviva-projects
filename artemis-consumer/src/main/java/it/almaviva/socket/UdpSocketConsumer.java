package it.almaviva.socket;

import io.netty.channel.ChannelHandler;
import it.almaviva.etsi_messages.CoopIts;
import it.almaviva.etsi_messages.Etsi;
import org.apache.camel.BindToRegistry;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.netty.DefaultChannelHandlerFactory;
import org.apache.camel.component.netty.codec.DatagramPacketByteArrayDecoder;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UdpSocketConsumer extends RouteBuilder {


    /**
     * Send packet towards AMQP
     * Defined in the application.properties
     */
    @Inject
    @Channel("generated-measure")
    Emitter<CoopIts.Cam> measureEmitter;

    /**
     * Send packet towards MQTT
     * Defined in the application.properties
     */
    @Inject
    @Channel("generated-event")
    Emitter<CoopIts.Denm> eventEmitter;

    @Inject
    EtsiConverter converter;

    /**
     * if sync is true a response must be provided
     *
     * @throws Exception
     */
    @Override
    public void configure() {

        from("netty:udp://0.0.0.0:4400?sync=false&decoders=#decoder")
                .process(exchange -> {

                    byte[] in = (byte[]) exchange.getIn().getBody();

                    Etsi etsi = converter.convert(in);

                    if (etsi == null) throw new RuntimeException("Unknown format");

                    if (etsi instanceof CoopIts.Cam) measureEmitter.send((CoopIts.Cam) etsi);
                    else if (etsi instanceof CoopIts.Denm) eventEmitter.send((CoopIts.Denm) etsi);
                    else throw new RuntimeException("Etsi message is neither Cam nor Denm");

                });

    }

    @BindToRegistry("decoder")
    public ChannelHandler getDecoder() {
        return new DefaultChannelHandlerFactory() {
            @Override
            public ChannelHandler newChannelHandler() {
                return new DatagramPacketByteArrayDecoder();
            }
        };
    }

}
