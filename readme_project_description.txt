MachineInterface

A program indulásakor CommandLineRunner-en keresztül példányosítom a MachineInterface osztályt, ami példányosításkor a következõket végzi el:

	- Lefuttatja a ReadFile osztály adatbázis feltöltés metódusát
	- beállítja a gépállapotot rendszerindításra
	- üdvözlõüzenetet kiíratja

MachineInterface példányosítása után while cilussal futtatjuk a programot, ami folyamatosan ellenõrzi, hogy mire van beállítva a gépállapot. Addig futtatjuk, amíg a MachineInterface isWorking() metódusa OFF állapotban nem tér vissza.

Ezután a MachineInterface run() metódusával elindítjuk a kezdõképernyõt.

A kezdõképernyõ 3 opciót kínál fel:
	
	-Bejelentkezés
	-Regisztráció
	-Kilépés

	- Bejelentkezéskor átlépünk a loggingInMenu()-re, a program bekér egy felhasználónév - jelszó párost, amit már elõre meghatározva leellenõriz, hogy létezik-e ilyen felhasználó a rendszerben. Ha igen, az adott login-hoz tartozó felhasználót, és a hozzá tartozó elsõ bankkártyát osztályváltozóként kimentjük, és továbbenged a felhasználói felületre, ha nem, figyelmeztetést ad vissza a program, hogy valami nincs rendben.

	- Regisztrációkor belépünk a MachineActions() registration() menüjébe, egymás után létrehozunk login-t, felhasználót és bankkártyát. Adatokat bekérünk, elõre meghatározott metódusok alapján ellenõrizzük, hogy bizonyos kritériumoknak megfelelnek-e a bevitt értékek, ha igen, elmentjük, ha nem, a program figyelmeztet, hogy mely mezõk nem voltak megfelelõen kitöltve, és visszatérünk a kezdõképernyõre.

	- Kilépéskor a gépállapotot OFF-ra állítjuk, ezzel a külsõ while ciklus megáll, elköszönõ üzenet, és vége a program futásának.

Ha a bejelentkezés sikeres, a következõ menü ami fogad, az a felhasználói menü. A felhasználói menü pontjai: 
	- Egyenleg lekérdezése
	- Pénzfelvétel
	- Pénzletétel
	- PIN csere
	- Egyéb mûvelet
	- Vissza

A felhasználói felület folyamatosan figyelmeztetni fog, ha az aktuális bankkártya, amit használunk aktiválva van-e, 

	- Egyenleg lekérdezése menüpontban currentBalanceMenu()-t meghívjuk, ami az aktuálisnak beállított bankkártya egyenlegét Double primitívbe lementi és üzenet metódus segítségével kiíratjuk az aktuális egyenleget.

	- Pénzfelvétel menüpontban a withdrawMenu()-t meghívjuk, kilistázzuk a lehetõségeket, hogy mekkora pénzösszeget lehet levenni a kártyáról, az elsõ 6 opcióban elõre beégetett pénzösszeg áll rendelkezésre, a 7. opcióban egyénileg megadott pénzösszeg levételére van lehetõség. A megadott pénzösszegeket minden esetben megviszgáljuk, hogy elõre meghatározott kritériumoknak megfelel-e a bevitt pénzösszeg, ha igen, a program tovább fut, ha nem, kiírjuk a hiba lehetséges okait. Mindegyik menüpont ugyanazt a metódust hívja meg a MachineActions osztályból.

	- Pénzletétel menüpontban bekérjük MachineActions osztály metódusán keresztül a letenni kívánt összeget, ha 0, akkor kilépünk a metódusból, egyébként ellenõrizzük, hogy bizonyos kritériumoknak megfelel-e, ha nem, a program figyelmeztet, hogy mik lehetnek a lehetséges okok, ha igen, úgy feltöltjük az összeget a bankkártya egyenlegére.

	* Mind a pénzfelvétel, mind a pénzletétel opcióknál, ha a tranzakció sikeres, arról osztály/rekord készül, amit hozzákötünk az aktuális bankkártyához, és elmentjük adatbázisba.

	- PIN csere menüpontban a MachineActions changeUsersCardPin() metódusán keresztül elkérjük az aktuális PIN kódot, ha megegyezik a tényleges PIN kóddal, úgy bekérjük a beállítani kívánt PIN kódot, majd mégegyszer megerõsítésként. Ha nem egyezik az aktuális PIN kód a tényleges PIN kóddal, figyelmeztetjük a felhasználót, és megszakítjuk a metódust.

	- Egyéb mûveletnél további opciókat kínálunk fel.

	- Vissza opciónál visszatérünk az elõzõ menüre.

- Egyéb mûveletnél további opciók:
		- Kártyával kapcsolatos opciók
		- Felhasználóval kapcsolatos opciók
		- Vissza

	- Kártyával kapcsolatos opciók menüpontban további opciókat kínálunk fel.

	- Felhasználóval kapcsolatos menüpontban további opciókat kínálunk fel.

	- Vissza opciónál visszatérünk az elõzõ menüre.

- Kártyával kapcsolatos opciók:
	- Regisztrált kártyák listája
	- Új kártya regisztráció
	- Kártyaváltás
	- Kártya törlése
	- Kártya aktiválás
	- Tranzakció lista
	- Vissza

- Regisztrált kártyák listája menüpontban kilistázzuk a felhasználóhoz regisztrált összes kártyát, az aktuálisan használt bankkártyát külön megjelöljük.

- Új kártya regisztrációkor a MachineActions metódusán keresztül bekérjük a felhasználótól az adatokat, amelyek szükségesek a bankkártya regisztrációjához, ellenõrizzük, hogy az elõre meghatározott kritériumoknak megfelelnek-e a bevitt adatok, ha nem, a program figyelmeztet, hogy mely mezõk voltak helytelenül kitöltve, ha igen, hozzáregisztráljuk a bankkártyát az aktuális felhasználóhoz. 

- Kártyaváltás menüpontban a MachineActions metódusain keresztül kilistázzuk az aktuális felhasználóhoz tartozó összes bankkártyát, és a bankkártyák elõtt feltüntetett sorszámok alapján lehet kiválasztani, hogy melyik bankkártyát szeretnénk a továbbiakban használni.

- Kártya törlése menüpontban a MachineActions metódusain keresztül kilistázzuk az aktuális felhasználóhoz tartozó összes bankkártyát, és a bankkártyák elõtt feltüntetett sorszámok alapján lehet kiválasztani, hogy melyik bankkártyát szeretnénk törölni. Ha azt a bankkártyát töröljük, amelyiket éppen használjuk, a program a listában szereplõ legelsõ bankkártyára váltja az aktuálisan használt bankkártyát. Csak egy bankkártya esetén ez a menüpont egy figyelmeztetést ad, hogy egy bankkártya esetén ez az opció nem elérhetõ.

- Kártya aktiválás menüpontban a MachineActions metódusain keresztül ellenõrizzük, hogy az aktuálisan használt bankkártya aktiválva van-e. Ha nem, úgy aktiváljuk, és errõl értesítjük a felhasználót, ha igen, arról figyelmeztetést adunk, hogy már aktiválva van.

- Tranzakció lista menüpontban a MachineActions metódusain keresztül az aktuális bankkártyához tartozó összes tranzakciót kilistázzuk.

- Vissza opciónál visszatérünk az elõzõ menüre.

- Felhasználóval kapcsolatos opciók:
	- Felhasználói adatok megtekintése
	- Felhasználói adat módosítás
	- Jelszó módosítás
	- Felhasználó törlése
	- Vissza

- Felhasználói adatok megtekintése menüpontban a Üzenet osztályon keresztül kiírjuk az aktuális felhasználó adatait.

- Felhasználói adat módosítás menüpontban további opciókat kínálunk fel.

- Jelszó módosítás menüpontban a MachineActions changeUsersLoginPassword() metódusán keresztül elkérjük az aktuális jelszót, ha megegyezik a tényleges jelszóval, úgy bekérjük a beállítani kívánt jelszót, majd mégegyszer megerõsítésként. Ha nem egyezik az aktuális jelszó a tényleges jelszóval, figyelmeztetjük a felhasználót, és megszakítjuk a metódust.

- Felhasználó törlése menüpontban elkérjük a felhasználóhoz tartozó jelszót, ha megegyezik a tényleges jelszóval egy ismételt kérdéssel megerõsítésként meggyõzõdik a program a törlés szándékáról, és ha azt is megerõsíti a felhasználó, úgy a felhasználó törlésre kerül, és a menüpontok visszavezetnek a bejelentkezési felületre, a felhasználó törlésre kerül, és a továbbiakban nem lesz elérhetõ.

- Felhasználói adat módosítás opciók: 
	- Keresztnév módosítás
	- Vezetéknév módosítás
	- Cím módosítás
	- Telefonszám módosítás
	- Email módosítás
	- Születési dátum módosítás
	- Felhasználónév módosítás
	- Vissza

- Adatok módosításainál minden esetben a MachineActions() osztályban már elõre megírt metódusain keresztül bekérjük a módosítani kívánt felhasználói adathoz szükséges adatot a felhasználótól, megviszgáljuk, hogy elõre meghatározott kritériumoknak megfelel-e a bevitt adat, ha igen, az adat módosul és errõl értesítjük a felhasználót, ha nem figyelmeztetjük a hibáról, és visszatérünk az adatmódosítás menüpontra.

- Vissza opciónál visszatérünk az elõzõ menüre.


MachineActions

getInputBetweenMenuRanges(int, int) metódus adott intervallumok között vár inputot, és ha a DataOperations osztályban már elõre megírt vizsgálatoknak megfelel, visszatér a kívánt számmal. *MachineInterface-ben, menüválasztáskor szükséges.

getCustomNumberInputForTransaciton() metódus bekér inputot, aminek a dataOperations osztályban már elõre megírt metódusoknak ha megfelel, visszatér egy Double értékkel.
*MachineActions-ben, tranzakció létrehozásakor szükséges

login() függvény összegyûjti a DataOperations osztályban már elõre megírt metódusok segítségével a bejelentkezési adatokat, és leellenõrzi, hogy létezik-e ilyen értékpárral felhasználó. Ha igen, a felhasználó ID-jával visszatér, ha nem, 0-val.
	