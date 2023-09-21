-- Create the Client table
CREATE TABLE Client (
                        nom VARCHAR(255),
                        prenom VARCHAR(255),
                        dateDeNaissance DATE,
                        telephone VARCHAR(255),
                        code VARCHAR(255) PRIMARY KEY,
                        adresse VARCHAR(255)
);

-- Create the Compte table with a foreign key reference to Client
CREATE TABLE Compte (
                        numero SERIAL PRIMARY KEY,
                        solde INT,
                        date DATE,
                        etat CompteEtat,
                        client VARCHAR(225),
                        FOREIGN KEY (client) REFERENCES Client(code)
);

-- Create the Employe table
CREATE TABLE Employe (
                         nom VARCHAR(255),
                         prenom VARCHAR(255),
                         dateDeNaissance DATE,
                         telephone VARCHAR(255),
                         matricule VARCHAR(255) PRIMARY KEY,
                         dateDeRecrutement DATE,
                         email VARCHAR(255)
);

-- Create the Mission table
CREATE TABLE Mission (
                         id SERIAL PRIMARY KEY,
                         code VARCHAR(255),
                         nom VARCHAR(255),
                         description TEXT
);

-- Create the MissionOfEmploye table
CREATE TABLE MissionOfEmploye (
                                  id SERIAL PRIMARY KEY,
                                  mission_id INT,
                                  employe_id INT,
                                  dateStart DATE,
                                  dateEnd DATE,
                                  FOREIGN KEY (mission_id) REFERENCES Mission(id),
                                  FOREIGN KEY (employe_id) REFERENCES Employe(id)
);

-- Create the Operation table
CREATE TABLE Operation (
                           id SERIAL PRIMARY KEY,
                           numero VARCHAR(255),
                           dateDeCreation DATE,
                           montant REAL
);
