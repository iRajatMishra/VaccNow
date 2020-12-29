create TABLE slot(
  id bigint AUTO_INCREMENT  PRIMARY KEY,
  slot DATE NULL,
  isBooked BOOLEAN DEFAULT NULL,
  branch_id bigint NULL
);

create TABLE vaccines(
  id bigint AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  quantity bigint NULL,
  branch_id bigint NULL
);

create TABLE customer(
  id bigint AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  email VARCHAR(250) NOT NULL,
  paymentMethod VARCHAR(250) NOT NULL,
  applied BOOLEAN DEFAULT NULL,
  vaccine bigint NULL,
  slot bigint NULL,
  branch_id bigint NULL,
  constraint FK_vaccine FOREIGN KEY (vaccine) REFERENCES vaccines(id),
  constraint FK_slot FOREIGN KEY (slot) REFERENCES slot(id)
);

create TABLE branches(
  id bigint AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  vaccines bigint NULL,
  slots bigint NULL,
  customers bigint NULL,
  constraint FK_vaccines FOREIGN KEY (vaccines) REFERENCES vaccines(id),
  constraint FK_slots FOREIGN KEY (slots) REFERENCES slot(id),
  constraint FK_customers FOREIGN KEY (customers) REFERENCES customers(id)
);