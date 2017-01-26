package projektNaZaliczenie;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.io.BufferedReader;

//Program "Kalkulator walut"      Grupa E10/I semestr/2017/Krzysiek_Gruszecki&Przemys³aw_Grzejdziak
public class KalkulatorWalut {

	private static DecimalFormat dwiePoPrzecinku = new DecimalFormat(".##");
	private static Scanner wczytana = new Scanner(System.in);

	public static String[][] tablica(String nazwaPliku) throws FileNotFoundException, IOException {

		FileReader fr = new FileReader(nazwaPliku); // plik czytany
		BufferedReader plik = new BufferedReader(fr);
		int i = 0;
		String linijka = plik.readLine();
		String[][] tab = new String[Integer.parseInt(linijka)][3];

		while ((linijka = plik.readLine()) != null) {
			String[] parts = linijka.split(";");
			tab[i][0] = parts[0];
			tab[i][1] = parts[1];
			tab[i][2] = parts[2];

			i++;
		}
		plik.close();

		return tab;
	}

	public static int menu() { // menu <wybór>

		System.out.println("Co chesz zrobiæ?");
		System.out.println("1) Kupiæ dan¹ walutê");
		System.out.println("2) Sprzedaæ dan¹ walutê");
		System.out.println("3) Wymieniæ z danej waluty na inn¹");
		System.out.println("4) Pozosta³e");
		System.out.println("0) Wyjœcie");
		return pobierzInta();
	}

	private static int pobierzInta() {
		int liczba;
		try {
			liczba = wczytana.nextInt();
			wczytana.nextLine();
		} catch (Exception e) {
			liczba = 9999;
		}
		return liczba;
	}

	private static String menuWalut(String[][] daneWalut, byte ekran, byte przesuniecie){
		for(int i = 0; i < przesuniecie; i++){
			System.out.println(i + 1 + przesuniecie * ekran  + ") " + daneWalut[i + przesuniecie*ekran][0]);
		}
		if (ekran != 0){
			System.out.println("    01) Cofnij");
		}
		if (ekran != (daneWalut.length - 1 )/przesuniecie){
			System.out.println("    00) Dalej");
		}
		return wczytana.nextLine();
	}

	private static int wybierzWalute(String[][] daneWalut) {
		String w1;
		byte ekran = 0;
		byte przesuniecie = 6;
		do {
			w1 = menuWalut(daneWalut, ekran, przesuniecie);
			if (w1.equals("00") && ekran != (daneWalut.length - 1)/przesuniecie){
				ekran++;
			}
			else if (w1.equals("01") && ekran != 0){
				ekran--;
			}
		} while (w1.equals("00") || w1.equals("01"));
		return Integer.parseInt(w1);
	}

	private static void pozostaleNieDotyczyProjektu() {
		int coKupie;
		int kwota4;
		try {

			System.out.println("1) Twórcy");
			System.out.println("2) Co mogê kupiæ za dan¹ kwotê");
			coKupie = pobierzInta();
			if (coKupie == 2) { // "Co kupiê za dan¹ kwotê"
				System.out.println("Ile chcesz wydaæ?");
				Thread.sleep(1000);
				System.err.println("Kwota musi zostaæ podana w Nilfgaardzkich florenach!!!!");
				Thread.sleep(3790);
				System.out.println("Nie no ¿artowa³em");
				Thread.sleep(2495);
				System.out.println("A mo¿e jednak nie?");
				Thread.sleep(2890);
				System.out.println("Dobra, ¿artowa³em... Kwota musi zostaæ podana w z³otówkach");
				kwota4 = pobierzInta();
				Thread.sleep(400);
				System.out.println("Za tak¹ kwotê kupisz: ");
				Thread.sleep(500);
				System.out.println(kwota4 / 3100 + " GTX 1080");
				Thread.sleep(550);
				System.out.println(kwota4 / 323 + " Warunków na wydziale");
				Thread.sleep(580);
				System.out.println(kwota4 / 40000 + " Fajnych samochodów");
				Thread.sleep(800);
				System.out.println(kwota4 / 4 + " soków 100%");
				Thread.sleep(1000);
			} else if (coKupie == 1) { // Twórcy programu
				System.err.println("Twórcy programu:");
				Thread.sleep(800);
				System.out.print("Krzysiek ");
				Thread.sleep(400);
				System.out.println("Gruszecki");
				Thread.sleep(400);
				System.out.println("&");
				Thread.sleep(420);
				System.out.print("Przemys³aw ");
				Thread.sleep(450);
				System.out.println("Grzejdziak");
			}

		}

		catch (Exception e) {
			System.out.println("Z³e dane");
		}

	}

	private static void obliczanieWalut(String plik) {

		try {

			String[][] wartosciWalut = tablica(plik);
			int wybor;

			do {
				wybor = menu();

				if (wybor == 1) {
					kupnoWaluty(wartosciWalut);

				} else if (wybor == 2) {
					sprzedazWalut(wartosciWalut);
				} else if (wybor == 3) {
					wymianaWalut(wartosciWalut);
				} else if (wybor == 0) { // koniec programu
					System.out.println("Dziêkujemy za skorzystanie z programu");
				} else if (wybor == 4) { // Kategoria pozosta³e
					pozostaleNieDotyczyProjektu();
				} else if (wybor == 9999) {
					System.out.println("Wprowadzono b³êdne dane, program zakoñczy³ dzia³anie");
				}

				Thread.sleep(1900);
				System.out.println(" ");

			} while (wybor != 0 && wybor != 9999);
		}

		catch (Exception e) { // zle podane dane koñcz¹ dzia³anie programu z
								// syso

			System.out.println("B³êdne dane, program zakoñczy³ dzia³anie");

		}

	}

	private static void kupnoWaluty(String[][] wartosciWalut) {

		System.out.println("Co chcesz kupiæ?");
		int w1 = wybierzWalute(wartosciWalut);
		System.out.println("Ile chcesz kupiæ " + wartosciWalut[w1 - 1][0] + "?");
		System.out.println(" ");
		int kwota1 = pobierzInta();
		double kwota2 = kwota1 * Double.parseDouble(wartosciWalut[w1 - 1][2]);
		System.out.println("Ca³kowity koszt: " + dwiePoPrzecinku.format(kwota2) + "PLN");
	}

	private static void sprzedazWalut(String[][] wartosciWalut) {
		System.out.println("Jak¹ walutê chcesz sprzedaæ?");
		int w1 = wybierzWalute(wartosciWalut);
		System.out.println("Ile chcesz sprzedaæ " + wartosciWalut[w1 - 1][0] + "?");
		int kwota1 = pobierzInta();
		double kwota2 = kwota1 * Double.parseDouble(wartosciWalut[w1 - 1][1]);
		System.out.println("Kwota jak¹ dostaniesz: " + dwiePoPrzecinku.format(kwota2) + "PLN");
	}

	private static void wymianaWalut(String[][] wartosciWalut) {
		System.out.println("Ile chcesz wymieniæ?");
		int kwota1 = pobierzInta();
		System.out.println("Jak¹ walutê chcesz wymieniæ?");
		int w1 = wybierzWalute(wartosciWalut);
		double kwota2 = kwota1 * Double.parseDouble(wartosciWalut[w1 - 1][1]);
		System.out.println("Na jak¹?");
		int ww = wybierzWalute(wartosciWalut);
		double kwota3 = kwota2 / Double.parseDouble(wartosciWalut[ww - 1][2]);
		System.out.println("Kwota jak¹ dostaniesz: " + dwiePoPrzecinku.format(kwota3) + " " + wartosciWalut[ww - 1][0]);
	}

	public static void main(String[] args) throws IOException {

		obliczanieWalut(args.length > 0 ? args[0] : "kursy_walut2.csv");

	}

}