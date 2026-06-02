-- Réinitialisation du schéma
DROP SCHEMA IF EXISTS assurance CASCADE;
CREATE SCHEMA assurance;
SET search_path TO assurance;

-- Extension UUID
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

------------------------------------------------------------
-- 1) VEHICULE & HABITATION
------------------------------------------------------------

CREATE TABLE vehicule (
                          id_vehicule UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                          immatriculation VARCHAR(20) UNIQUE NOT NULL,
                          marque VARCHAR(50),
                          modele VARCHAR(50)
);

CREATE TABLE habitation (
                            id_habitation UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                            nombre_piece INT,
                            type_habitation VARCHAR(50)
);

------------------------------------------------------------
-- 2) RISQUE (XOR : véhicule OU habitation)
------------------------------------------------------------

CREATE TABLE risque (
                        id_risque UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                        id_vehicule UUID REFERENCES vehicule(id_vehicule),
                        id_habitation UUID REFERENCES habitation(id_habitation),
                        type_risque VARCHAR(50),
                        code_etat VARCHAR(20),
                        date_effet DATE,
                        date_fin DATE,

                        CONSTRAINT chk_risque_xor CHECK (
                            (id_vehicule IS NOT NULL AND id_habitation IS NULL)
                                OR
                            (id_vehicule IS NULL AND id_habitation IS NOT NULL)
                            )
);

------------------------------------------------------------
-- 3) PERSONNE
------------------------------------------------------------

CREATE TABLE personne (
                          id_personne UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                          nom VARCHAR(100) NOT NULL,
                          prenom VARCHAR(100) NOT NULL
);

------------------------------------------------------------
-- 4) CONTRAT (lié à une personne, optionnellement à un risque)
------------------------------------------------------------

CREATE TABLE contrat (
                         id_contrat UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                         id_personne UUID NOT NULL REFERENCES personne(id_personne),
                         id_risque UUID REFERENCES risque(id_risque),
                         type_contrat VARCHAR(50) NOT NULL,
                         tarif_annuel NUMERIC(10,2) NOT NULL
);
