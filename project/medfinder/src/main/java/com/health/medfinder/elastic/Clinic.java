package com.health.medfinder.elastic;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.GeoPointField;

@Data
@Document(indexName = "clinics")
public class Clinic {
    @Id
    private String id;
    private String name;
    private double latitude;
    private double longitude;
    private String hours;
    private String telephone;
    private String mobile;
    private String webAddress;
    private Boolean hasIcu;
    private Boolean hasAmbulance;
    private Boolean hasWheelchairSupport;
    private String governmentHealthCode;

    @GeoPointField // Annotation to store latitude and longitude as a geo-point
    private String location; // Store the location as a geo-point in the format "latitude,longitude"

    // Constructors, getters, setters, etc.
}

