ALTER TABLE contacts
    ADD CONSTRAINT unique_email UNIQUE (email_id);

ALTER TABLE inventory
    ADD CONSTRAINT unique_clinic_medication UNIQUE (clinic_id, medication_id);

ALTER TABLE medications
    ADD CONSTRAINT unique_medication_name UNIQUE (medication_name);

ALTER TABLE clinics
    ADD COLUMN government_health_code VARCHAR(255),
    ADD CONSTRAINT unique_government_health_code UNIQUE (government_health_code);
