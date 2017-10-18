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
	taux_occupation decimal(4,2),
	taux_non_presentation decimal(4,2),

	primary key (jour)
) ;

--Ne fonctionne pas
create event save_history
ON SCHEDULE EVERY 1 DAY STARTS '2006-06-12 04:00:00'
do
	begin
		declare pourcentage decimal(5,2),
		declare nbr_chambre int,
		declare occupe int,

		--Calcul de l occupation
		set nbr_chambre = 100,
		set occupe = select COUNT(*) from `Chambre` where Occupee=1,
		set pourcentage = occupe/nbr_chambre,

		INSERT into `Historique` values(curedate(), pourcentage, 0),
	end

--passe MAIS curdate existe pas --> erreur
create event save_history
on schedule every 01 day_hour
do
	INSERT into `Historique` values(curedate(), (select COUNT(*) from `Chambre` where Occupee=1), 0) ;
