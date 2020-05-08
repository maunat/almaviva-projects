package it.almaviva.cassandra;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MeasureConsumer {

    private ObjectMapper mapper = new ObjectMapper();

    @JmsListener(destination = "measures")
    public void receiveCam(String msg) throws Exception {
        // Cam cam = mapper.readValue(msg, Cam.class);
        System.out.println("Cam received");
    }

    @JmsListener(destination = "events")
    public void receiveDenm(String msg) throws Exception {
        // Denm denm = mapper.readValue(msg, Denm.class);
        System.out.println("Denm received");
    }


}
