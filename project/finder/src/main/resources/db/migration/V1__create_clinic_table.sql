CREATE TABLE contacts (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          email_id VARCHAR(255) NOT NULL,
                          first_name VARCHAR(255),
                          last_name VARCHAR(255),
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE clinics (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         latitude DECIMAL(10, 6) NOT NULL,
                         longitude DECIMAL(10, 6) NOT NULL,
                         hours VARCHAR(255),
                         telephone VARCHAR(20),
                         mobile VARCHAR(20),
                         web_address VARCHAR(255),
                         has_icu BOOLEAN,
                         has_ambulance BOOLEAN,
                         has_wheelchair_support BOOLEAN,
                         primary_contact_id INT,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         FOREIGN KEY (primary_contact_id) REFERENCES contacts (id)
);




CREATE TABLE medications (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             medication_name VARCHAR(255) NOT NULL,
                             medication_code VARCHAR(20),
                             age INT,
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                             updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE inventory (
                           clinic_id INT NOT NULL,
                           medication_id INT NOT NULL,
                           quantity INT,
                           updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           PRIMARY KEY (clinic_id, medication_id),
                           FOREIGN KEY (clinic_id) REFERENCES clinics (id),
                           FOREIGN KEY (medication_id) REFERENCES medications (id)
);
