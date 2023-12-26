package com.health.medfinder.writer;

import com.health.medfinder.elastic.Clinic;
import com.health.medfinder.repository.ClinicRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClinicItemWriter implements ItemWriter<Clinic> {

    @Autowired
    private ClinicRepository clinicRepository;

    @Override
    public void write(List<? extends Clinic> clinics) throws Exception {
        for (Clinic clinic : clinics) {
            // Convert the latitude and longitude to a valid geo-point format.
            String latitude = String.valueOf(clinic.getLatitude());
            String longitude = String.valueOf(clinic.getLongitude());
            clinic.setLocation(latitude + "," + longitude);

            // Save the clinic to Elasticsearch.
            clinicRepository.save(clinic);
        }
    }
}
