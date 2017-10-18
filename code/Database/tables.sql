create table `Chambre`
(
	Numero int not null,
	Categorie int not null,
	Description varchar(255),
	Nettoyee boolean not null,
	Occupee boolean not null,

	primary key (Numero)
) ;

create table `Historique`
(
	jour date not null,
	taux_occupation float,
	taux_non_presentation float,

	primary key (jour)
) ;

create table `Presentations`
(
	Jour date not null,
	Reservation varchar(20),
	

	primary key (jour)
) ;

--Ne fonctionne pas
create event save_history
ON SCHEDULE EVERY 1 DAY STARTS '2017-10-20 00:00:00'
do
	begin
		declare pourcentage float,
		declare nbr_chambre float,
		declare occupe float,

		--Calcul de l occupation
		set nbr_chambre = select COUNT(*) from `Chambre`,
		set occupe = select COUNT(*) from `Chambre` where Occupee=1,
		set pourcentage = occupe/nbr_chambre,

		INSERT into `Historique` values(curedate(), pourcentage, 0),
	end



--passe MAIS curdate existe pas --> erreur
create event save_history
ON SCHEDULE EVERY 1 DAY STARTS '2017-10-20 00:00:00'
do
	INSERT into `Historique` values(select curedate(), select COUNT(*) from `Chambre` where Occupee=1, 0),