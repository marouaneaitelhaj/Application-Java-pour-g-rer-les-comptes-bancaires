-- DROP DATABASE myBank;
-- CREATE DATABASE IF NOT EXISTS myBank;






CREATE TABLE IF NOT EXISTS  Client (
                        nom VARCHAR(255),
                        prenom VARCHAR(255),
                        dateDeNaissance VARCHAR(255),
                        telephone VARCHAR(255),
                        code VARCHAR(255) PRIMARY KEY,
                        adresse VARCHAR(255)
);
CREATE TABLE IF NOT EXISTS  Employe (
                         nom VARCHAR(255),
                         prenom VARCHAR(255),
                         dateDeNaissance VARCHAR(255),
                         telephone VARCHAR(255),
                         matricule VARCHAR(255) PRIMARY KEY,
                         dateDeRecrutement VARCHAR(255),
                         email VARCHAR(255)
);
CREATE TABLE IF NOT EXISTS  Compte (
                        numero VARCHAR(255) PRIMARY KEY,
                        solde INT,
                        date VARCHAR(255),
                        etat VARCHAR(255),
                        client VARCHAR(225),
                        FOREIGN KEY (client) REFERENCES Client(code)
);

CREATE TABLE IF NOT EXISTS  CompteEpargne (
    compte VARCHAR(255) PRIMARY KEY,
    tauxDinteret double precision,
    FOREIGN KEY (compte) REFERENCES compte(numero)
);


CREATE TABLE IF NOT EXISTS  CompteCourant (
    compte VARCHAR(255)  PRIMARY KEY,
    decouvert double precision,
    FOREIGN KEY (compte) REFERENCES compte(numero)
);



CREATE TABLE IF NOT EXISTS  Mission (
                         code VARCHAR(255) PRIMARY KEY,
                         nom VARCHAR(255),
                         description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS  MissionOfEmploye (
                                  mission VARCHAR(255),
                                  employe VARCHAR(255),
                                  dateStart VARCHAR(255),
                                  dateEnd VARCHAR(255),
                                  FOREIGN KEY (mission) REFERENCES Mission(code),
                                  FOREIGN KEY (employe) REFERENCES Employe(matricule)
);

CREATE TABLE IF NOT EXISTS  Operation (
                           numero VARCHAR(255),
                           dateDeCreation VARCHAR(255),
                           montant double precision,
                           employe VARCHAR(255),
                           compte VARCHAR(255),
                           FOREIGN KEY (compte) REFERENCES Compte(numero),
                           FOREIGN KEY (employe) REFERENCES Employe(matricule)
);
