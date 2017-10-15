create table `Chambre`
(
	Numero int not null,
	Categorie int not null,
	Description varchar(255),
	Nettoyee boolean not null,
	Occupee boolean not null,

	primary key (Numero)
) ;