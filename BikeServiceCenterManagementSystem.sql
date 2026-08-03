CREATE TABLE customer_bike (
    customer_id SERIAL PRIMARY KEY,
    customer_name VARCHAR(100) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    bike_number VARCHAR(20) NOT NULL,
    brand VARCHAR(50) NOT NULL,
    model VARCHAR(50) NOT NULL,
	current_km INT NOT NULL,
    address TEXT NOT NULL,
    notes TEXT NOT NULL
)

SELECT * FROM customer_bike

ALTER TABLE customer_bike
ADD COLUMN created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

CREATE TABLE mechanic (
    mechanic_id SERIAL PRIMARY KEY,
    mechanic_name VARCHAR(100) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    email VARCHAR(100),
    id_proof VARCHAR(30),
    specialization VARCHAR(50),
    experience INT,
    address TEXT,
    status VARCHAR(20) DEFAULT 'Available'
)

SELECT * FROM mechanic

CREATE TABLE spare_parts (

    part_id SERIAL PRIMARY KEY,

    part_number VARCHAR(50) UNIQUE NOT NULL,

    part_name VARCHAR(100) NOT NULL,

    category VARCHAR(50) NOT NULL,

    brand VARCHAR(50),

    compatible_vehicle VARCHAR(100),

    cost_price NUMERIC(10,2) NOT NULL,

    selling_price NUMERIC(10,2) NOT NULL,

    opening_quantity INT NOT NULL,

    current_stock INT NOT NULL,

    min_stock INT NOT NULL,

    rack_location VARCHAR(50),

    notes TEXT

)

SELECT * FROM spare_parts

CREATE TABLE job_card (

    job_card_id SERIAL PRIMARY KEY,

    customer_id INT NOT NULL,

    mechanic_id INT,

    service_type VARCHAR(100) NOT NULL,

    fuel_level VARCHAR(30),

    current_km INT,

    complaint TEXT,

    approximate_cost DECIMAL(10,2),

    delivery_date DATE,

    status VARCHAR(20) DEFAULT 'Pending',

    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_jobcard_customer
        FOREIGN KEY (customer_id)
        REFERENCES customer_bike(customer_id),

    CONSTRAINT fk_jobcard_mechanic
        FOREIGN KEY (mechanic_id)
        REFERENCES mechanic(mechanic_id)

)

SELECT * FROM job_card

CREATE TABLE job_card_spare_parts (

    job_card_spare_id SERIAL PRIMARY KEY,

    job_card_id INT NOT NULL,

    part_id INT NOT NULL,

    quantity INT NOT NULL,

    unit_price NUMERIC(10,2) NOT NULL,

    total_price NUMERIC(10,2) NOT NULL,

    CONSTRAINT fk_jobcard
        FOREIGN KEY(job_card_id)
        REFERENCES job_card(job_card_id),

    CONSTRAINT fk_sparepart
        FOREIGN KEY(part_id)
        REFERENCES spare_parts(part_id)

)

SELECT * FROM job_card_spare_parts

CREATE TABLE invoice (

    invoice_id SERIAL PRIMARY KEY,

    invoice_no VARCHAR(20) UNIQUE NOT NULL,

    job_card_id INT NOT NULL,

    customer_id INT NOT NULL,

    bill_date DATE NOT NULL,

    spare_total NUMERIC(10,2) DEFAULT 0,

    labour_charge NUMERIC(10,2) DEFAULT 0,

    grand_total NUMERIC(10,2),

    payment_mode VARCHAR(30),

    payment_status VARCHAR(20),

    CONSTRAINT fk_invoice_jobcard
        FOREIGN KEY (job_card_id)
        REFERENCES job_card(job_card_id),

    CONSTRAINT fk_invoice_customer
        FOREIGN KEY (customer_id)
        REFERENCES customer_bike(customer_id)

)

SELECT invoice_no, payment_status
FROM invoice;

UPDATE invoice
SET payment_status = 'Paid'
WHERE payment_status IS NULL;

DELETE FROM invoice WHERE invoice_id = 2

ALTER TABLE invoice ADD CONSTRAINT unique_jobcard_invoice UNIQUE(job_card_id);

SELECT * FROM invoice

SELECT part_name, current_stock, min_stock
FROM spare_parts;

SELECT
TO_CHAR(created_date,'Mon') AS month,
COUNT(*) AS total
FROM job_card
GROUP BY TO_CHAR(created_date,'Mon'),
EXTRACT(MONTH FROM created_date)
ORDER BY EXTRACT(MONTH FROM created_date);

SELECT job_card_id, created_date
FROM job_card
ORDER BY created_date;