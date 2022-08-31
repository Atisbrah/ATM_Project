MachineInterface

A program indul�sakor CommandLineRunner-en kereszt�l p�ld�nyos�tom a MachineInterface oszt�lyt, ami p�ld�nyos�t�skor a k�vetkez�ket v�gzi el:

	- Lefuttatja a ReadFile oszt�ly adatb�zis felt�lt�s met�dus�t
	- be�ll�tja a g�p�llapotot rendszerind�t�sra
	- �dv�zl��zenetet ki�ratja

MachineInterface p�ld�nyos�t�sa ut�n while cilussal futtatjuk a programot, ami folyamatosan ellen�rzi, hogy mire van be�ll�tva a g�p�llapot. Addig futtatjuk, am�g a MachineInterface isWorking() met�dusa OFF �llapotban nem t�r vissza.

Ezut�n a MachineInterface run() met�dus�val elind�tjuk a kezd�k�perny�t.

A kezd�k�perny� 3 opci�t k�n�l fel:
	
	-Bejelentkez�s
	-Regisztr�ci�
	-Kil�p�s

	- Bejelentkez�skor �tl�p�nk a loggingInMenu()-re, a program bek�r egy felhaszn�l�n�v - jelsz� p�rost, amit m�r el�re meghat�rozva leellen�riz, hogy l�tezik-e ilyen felhaszn�l� a rendszerben. Ha igen, az adott login-hoz tartoz� felhaszn�l�t, �s a hozz� tartoz� els� bankk�rty�t oszt�lyv�ltoz�k�nt kimentj�k, �s tov�bbenged a felhaszn�l�i fel�letre, ha nem, figyelmeztet�st ad vissza a program, hogy valami nincs rendben.

	- Regisztr�ci�kor bel�p�nk a MachineActions() registration() men�j�be, egym�s ut�n l�trehozunk login-t, felhaszn�l�t �s bankk�rty�t. Adatokat bek�r�nk, el�re meghat�rozott met�dusok alapj�n ellen�rizz�k, hogy bizonyos krit�riumoknak megfelelnek-e a bevitt �rt�kek, ha igen, elmentj�k, ha nem, a program figyelmeztet, hogy mely mez�k nem voltak megfelel�en kit�ltve, �s visszat�r�nk a kezd�k�perny�re.

	- Kil�p�skor a g�p�llapotot OFF-ra �ll�tjuk, ezzel a k�ls� while ciklus meg�ll, elk�sz�n� �zenet, �s v�ge a program fut�s�nak.

Ha a bejelentkez�s sikeres, a k�vetkez� men� ami fogad, az a felhaszn�l�i men�. A felhaszn�l�i men� pontjai: 
	- Egyenleg lek�rdez�se
	- P�nzfelv�tel
	- P�nzlet�tel
	- PIN csere
	- Egy�b m�velet
	- Vissza

A felhaszn�l�i fel�let folyamatosan figyelmeztetni fog, ha az aktu�lis bankk�rtya, amit haszn�lunk aktiv�lva van-e, 

	- Egyenleg lek�rdez�se men�pontban currentBalanceMenu()-t megh�vjuk, ami az aktu�lisnak be�ll�tott bankk�rtya egyenleg�t Double primit�vbe lementi �s �zenet met�dus seg�ts�g�vel ki�ratjuk az aktu�lis egyenleget.

	- P�nzfelv�tel men�pontban a withdrawMenu()-t megh�vjuk, kilist�zzuk a lehet�s�geket, hogy mekkora p�nz�sszeget lehet levenni a k�rty�r�l, az els� 6 opci�ban el�re be�getett p�nz�sszeg �ll rendelkez�sre, a 7. opci�ban egy�nileg megadott p�nz�sszeg lev�tel�re van lehet�s�g. A megadott p�nz�sszegeket minden esetben megviszg�ljuk, hogy el�re meghat�rozott krit�riumoknak megfelel-e a bevitt p�nz�sszeg, ha igen, a program tov�bb fut, ha nem, ki�rjuk a hiba lehets�ges okait. Mindegyik men�pont ugyanazt a met�dust h�vja meg a MachineActions oszt�lyb�l.

	- P�nzlet�tel men�pontban bek�rj�k MachineActions oszt�ly met�dus�n kereszt�l a letenni k�v�nt �sszeget, ha 0, akkor kil�p�nk a met�dusb�l, egy�bk�nt ellen�rizz�k, hogy bizonyos krit�riumoknak megfelel-e, ha nem, a program figyelmeztet, hogy mik lehetnek a lehets�ges okok, ha igen, �gy felt�ltj�k az �sszeget a bankk�rtya egyenleg�re.

	* Mind a p�nzfelv�tel, mind a p�nzlet�tel opci�kn�l, ha a tranzakci� sikeres, arr�l oszt�ly/rekord k�sz�l, amit hozz�k�t�nk az aktu�lis bankk�rty�hoz, �s elmentj�k adatb�zisba.

	- PIN csere men�pontban a MachineActions changeUsersCardPin() met�dus�n kereszt�l elk�rj�k az aktu�lis PIN k�dot, ha megegyezik a t�nyleges PIN k�ddal, �gy bek�rj�k a be�ll�tani k�v�nt PIN k�dot, majd m�gegyszer meger�s�t�sk�nt. Ha nem egyezik az aktu�lis PIN k�d a t�nyleges PIN k�ddal, figyelmeztetj�k a felhaszn�l�t, �s megszak�tjuk a met�dust.

	- Egy�b m�veletn�l tov�bbi opci�kat k�n�lunk fel.

	- Vissza opci�n�l visszat�r�nk az el�z� men�re.

- Egy�b m�veletn�l tov�bbi opci�k:
		- K�rty�val kapcsolatos opci�k
		- Felhaszn�l�val kapcsolatos opci�k
		- Vissza

	- K�rty�val kapcsolatos opci�k men�pontban tov�bbi opci�kat k�n�lunk fel.

	- Felhaszn�l�val kapcsolatos men�pontban tov�bbi opci�kat k�n�lunk fel.

	- Vissza opci�n�l visszat�r�nk az el�z� men�re.

- K�rty�val kapcsolatos opci�k:
	- Regisztr�lt k�rty�k list�ja
	- �j k�rtya regisztr�ci�
	- K�rtyav�lt�s
	- K�rtya t�rl�se
	- K�rtya aktiv�l�s
	- Tranzakci� lista
	- Vissza

- Regisztr�lt k�rty�k list�ja men�pontban kilist�zzuk a felhaszn�l�hoz regisztr�lt �sszes k�rty�t, az aktu�lisan haszn�lt bankk�rty�t k�l�n megjel�lj�k.

- �j k�rtya regisztr�ci�kor a MachineActions met�dus�n kereszt�l bek�rj�k a felhaszn�l�t�l az adatokat, amelyek sz�ks�gesek a bankk�rtya regisztr�ci�j�hoz, ellen�rizz�k, hogy az el�re meghat�rozott krit�riumoknak megfelelnek-e a bevitt adatok, ha nem, a program figyelmeztet, hogy mely mez�k voltak helytelen�l kit�ltve, ha igen, hozz�regisztr�ljuk a bankk�rty�t az aktu�lis felhaszn�l�hoz. 

- K�rtyav�lt�s men�pontban a MachineActions met�dusain kereszt�l kilist�zzuk az aktu�lis felhaszn�l�hoz tartoz� �sszes bankk�rty�t, �s a bankk�rty�k el�tt felt�ntetett sorsz�mok alapj�n lehet kiv�lasztani, hogy melyik bankk�rty�t szeretn�nk a tov�bbiakban haszn�lni.

- K�rtya t�rl�se men�pontban a MachineActions met�dusain kereszt�l kilist�zzuk az aktu�lis felhaszn�l�hoz tartoz� �sszes bankk�rty�t, �s a bankk�rty�k el�tt felt�ntetett sorsz�mok alapj�n lehet kiv�lasztani, hogy melyik bankk�rty�t szeretn�nk t�r�lni. Ha azt a bankk�rty�t t�r�lj�k, amelyiket �ppen haszn�ljuk, a program a list�ban szerepl� legels� bankk�rty�ra v�ltja az aktu�lisan haszn�lt bankk�rty�t. Csak egy bankk�rtya eset�n ez a men�pont egy figyelmeztet�st ad, hogy egy bankk�rtya eset�n ez az opci� nem el�rhet�.

- K�rtya aktiv�l�s men�pontban a MachineActions met�dusain kereszt�l ellen�rizz�k, hogy az aktu�lisan haszn�lt bankk�rtya aktiv�lva van-e. Ha nem, �gy aktiv�ljuk, �s err�l �rtes�tj�k a felhaszn�l�t, ha igen, arr�l figyelmeztet�st adunk, hogy m�r aktiv�lva van.

- Tranzakci� lista men�pontban a MachineActions met�dusain kereszt�l az aktu�lis bankk�rty�hoz tartoz� �sszes tranzakci�t kilist�zzuk.

- Vissza opci�n�l visszat�r�nk az el�z� men�re.

- Felhaszn�l�val kapcsolatos opci�k:
	- Felhaszn�l�i adatok megtekint�se
	- Felhaszn�l�i adat m�dos�t�s
	- Jelsz� m�dos�t�s
	- Felhaszn�l� t�rl�se
	- Vissza

- Felhaszn�l�i adatok megtekint�se men�pontban a �zenet oszt�lyon kereszt�l ki�rjuk az aktu�lis felhaszn�l� adatait.

- Felhaszn�l�i adat m�dos�t�s men�pontban tov�bbi opci�kat k�n�lunk fel.

- Jelsz� m�dos�t�s men�pontban a MachineActions changeUsersLoginPassword() met�dus�n kereszt�l elk�rj�k az aktu�lis jelsz�t, ha megegyezik a t�nyleges jelsz�val, �gy bek�rj�k a be�ll�tani k�v�nt jelsz�t, majd m�gegyszer meger�s�t�sk�nt. Ha nem egyezik az aktu�lis jelsz� a t�nyleges jelsz�val, figyelmeztetj�k a felhaszn�l�t, �s megszak�tjuk a met�dust.

- Felhaszn�l� t�rl�se men�pontban elk�rj�k a felhaszn�l�hoz tartoz� jelsz�t, ha megegyezik a t�nyleges jelsz�val egy ism�telt k�rd�ssel meger�s�t�sk�nt meggy�z�dik a program a t�rl�s sz�nd�k�r�l, �s ha azt is meger�s�ti a felhaszn�l�, �gy a felhaszn�l� t�rl�sre ker�l, �s a men�pontok visszavezetnek a bejelentkez�si fel�letre, a felhaszn�l� t�rl�sre ker�l, �s a tov�bbiakban nem lesz el�rhet�.

- Felhaszn�l�i adat m�dos�t�s opci�k: 
	- Keresztn�v m�dos�t�s
	- Vezet�kn�v m�dos�t�s
	- C�m m�dos�t�s
	- Telefonsz�m m�dos�t�s
	- Email m�dos�t�s
	- Sz�let�si d�tum m�dos�t�s
	- Felhaszn�l�n�v m�dos�t�s
	- Vissza

- Adatok m�dos�t�sain�l minden esetben a MachineActions() oszt�lyban m�r el�re meg�rt met�dusain kereszt�l bek�rj�k a m�dos�tani k�v�nt felhaszn�l�i adathoz sz�ks�ges adatot a felhaszn�l�t�l, megviszg�ljuk, hogy el�re meghat�rozott krit�riumoknak megfelel-e a bevitt adat, ha igen, az adat m�dosul �s err�l �rtes�tj�k a felhaszn�l�t, ha nem figyelmeztetj�k a hib�r�l, �s visszat�r�nk az adatm�dos�t�s men�pontra.

- Vissza opci�n�l visszat�r�nk az el�z� men�re.


MachineActions

getInputBetweenMenuRanges(int, int) met�dus adott intervallumok k�z�tt v�r inputot, �s ha a DataOperations oszt�lyban m�r el�re meg�rt vizsg�latoknak megfelel, visszat�r a k�v�nt sz�mmal. *MachineInterface-ben, men�v�laszt�skor sz�ks�ges.

getCustomNumberInputForTransaciton() met�dus bek�r inputot, aminek a dataOperations oszt�lyban m�r el�re meg�rt met�dusoknak ha megfelel, visszat�r egy Double �rt�kkel.
*MachineActions-ben, tranzakci� l�trehoz�sakor sz�ks�ges

login() f�ggv�ny �sszegy�jti a DataOperations oszt�lyban m�r el�re meg�rt met�dusok seg�ts�g�vel a bejelentkez�si adatokat, �s leellen�rzi, hogy l�tezik-e ilyen �rt�kp�rral felhaszn�l�. Ha igen, a felhaszn�l� ID-j�val visszat�r, ha nem, 0-val.
	