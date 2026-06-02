SET search_path TO assurance;

insert into vehicule (immatriculation, marque, modele) values ('BB-270-XA', 'Ford', 'C-MAX');

insert into habitation (nombre_piece, type_habitation) values (4, 'Appartement');

insert into risque (id_vehicule, type_risque, code_etat, date_effet, date_fin) values (
                                                                                          (select id_vehicule from vehicule where immatriculation = 'BB-270-XA'),
                                                                                          'Risque lié au véhicule',
                                                                                          'Actif',
                                                                                          '2024-01-01',
                                                                                          '2025-01-01'
                                                                                      );

insert into personne (id_personne, nom, prenom) values ('15b17eb1-1a32-4639-b420-99f70b02dba8', 'DUPONT', 'Jean');
insert into personne (nom, prenom) values ('MOULIN', 'Jean');
insert into personne (nom, prenom) values ('DUPONT', 'Pierre');
insert into personne (nom, prenom) values ('DOE', 'John');
insert into personne (nom, prenom) values ('DOE', 'Jane');

insert into contrat (id_personne, id_risque, type_contrat, tarif_annuel) values (
                                                                                    (select id_personne from personne where nom = 'DUPONT' and prenom = 'Jean'),
                                                                                    (select id_risque from risque where code_etat = 'Actif'),
                                                                                    'Contrat de responsabilité civile',
                                                                                    1200
                                                                                );