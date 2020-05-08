package it.almaviva.socket;

import it.almaviva.etsi_messages.CoopIts;
import it.almaviva.etsi_messages.Etsi;
import net.gcdc.asn1.uper.UperEncoder;

import javax.inject.Singleton;

@Singleton
public class EtsiConverter {

    public Etsi convert(byte[] bytes) {

        try {

            return UperEncoder.decode(bytes, CoopIts.Cam.class);

        } catch (Exception e) {

            try {

                return UperEncoder.decode(bytes, CoopIts.Denm.class);

            } catch (Exception e1) {
                return null;
            }

        }

    }

}
