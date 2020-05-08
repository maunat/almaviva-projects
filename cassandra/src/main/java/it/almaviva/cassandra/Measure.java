package it.almaviva.cassandra;

import it.almaviva.etsi_messages.CoopIts;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
public class Measure extends CoopIts.Cam {

    @PrimaryKey
    private String id;

}
