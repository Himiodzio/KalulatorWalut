package projektNaZaliczenie;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.io.BufferedReader;

//Program "Kalkulator walut"      Grupa E10/I semestr/2017/Krzysiek_Gruszecki&Przemys�aw_Grzejdziak
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

	public static int menu() { // menu <wyb�r>

		System.out.println("Co chesz zrobi�?");
		System.out.println("1) Kupi� dan� walut�");
		System.out.println("2) Sprzeda� dan� walut�");
		System.out.println("3) Wymieni� z danej waluty na inn�");
		System.out.println("4) Pozosta�e");
		System.out.println("0) Wyj�cie");
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

			System.out.println("1) Tw�rcy");
			System.out.println("2) Co mog� kupi� za dan� kwot�");
			coKupie = pobierzInta();
			if (coKupie == 2) { // "Co kupi� za dan� kwot�"
				System.out.println("Ile chcesz wyda�?");
				Thread.sleep(1000);
				System.err.println("Kwota musi zosta� podana w Nilfgaardzkich florenach!!!!");
				Thread.sleep(3790);
				System.out.println("Nie no �artowa�em");
				Thread.sleep(2495);
				System.out.println("A mo�e jednak nie?");
				Thread.sleep(2890);
				System.out.println("Dobra, �artowa�em... Kwota musi zosta� podana w z�ot�wkach");
				kwota4 = pobierzInta();
				Thread.sleep(400);
				System.out.println("Za tak� kwot� kupisz: ");
				Thread.sleep(500);
				System.out.println(kwota4 / 3100 + " GTX 1080");
				Thread.sleep(550);
				System.out.println(kwota4 / 323 + " Warunk�w na wydziale");
				Thread.sleep(580);
				System.out.println(kwota4 / 40000 + " Fajnych samochod�w");
				Thread.sleep(800);
				System.out.println(kwota4 / 4 + " sok�w 100%");
				Thread.sleep(1000);
			} else if (coKupie == 1) { // Tw�rcy programu
				System.err.println("Tw�rcy programu:");
				Thread.sleep(800);
				System.out.print("Krzysiek ");
				Thread.sleep(400);
				System.out.println("Gruszecki");
				Thread.sleep(400);
				System.out.println("&");
				Thread.sleep(420);
				System.out.print("Przemys�aw ");
				Thread.sleep(450);
				System.out.println("Grzejdziak");
			}

		}

		catch (Exception e) {
			System.out.println("Z�e dane");
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
					System.out.println("Dzi�kujemy za skorzystanie z programu");
				} else if (wybor == 4) { // Kategoria pozosta�e
					pozostaleNieDotyczyProjektu();
				} else if (wybor == 9999) {
					System.out.println("Wprowadzono b��dne dane, program zako�czy� dzia�anie");
				}

				Thread.sleep(1900);
				System.out.println(" ");

			} while (wybor != 0 && wybor != 9999);
		}

		catch (Exception e) { // zle podane dane ko�cz� dzia�anie programu z
								// syso

			System.out.println("B��dne dane, program zako�czy� dzia�anie");

		}

	}

	private static void kupnoWaluty(String[][] wartosciWalut) {

		System.out.println("Co chcesz kupi�?");
		int w1 = wybierzWalute(wartosciWalut);
		System.out.println("Ile chcesz kupi� " + wartosciWalut[w1 - 1][0] + "?");
		System.out.println(" ");
		int kwota1 = pobierzInta();
		double kwota2 = kwota1 * Double.parseDouble(wartosciWalut[w1 - 1][2]);
		System.out.println("Ca�kowity koszt: " + dwiePoPrzecinku.format(kwota2) + "PLN");
	}

	private static void sprzedazWalut(String[][] wartosciWalut) {
		System.out.println("Jak� walut� chcesz sprzeda�?");
		int w1 = wybierzWalute(wartosciWalut);
		System.out.println("Ile chcesz sprzeda� " + wartosciWalut[w1 - 1][0] + "?");
		int kwota1 = pobierzInta();
		double kwota2 = kwota1 * Double.parseDouble(wartosciWalut[w1 - 1][1]);
		System.out.println("Kwota jak� dostaniesz: " + dwiePoPrzecinku.format(kwota2) + "PLN");
	}

	private static void wymianaWalut(String[][] wartosciWalut) {
		System.out.println("Ile chcesz wymieni�?");
		int kwota1 = pobierzInta();
		System.out.println("Jak� walut� chcesz wymieni�?");
		int w1 = wybierzWalute(wartosciWalut);
		double kwota2 = kwota1 * Double.parseDouble(wartosciWalut[w1 - 1][1]);
		System.out.println("Na jak�?");
		int ww = wybierzWalute(wartosciWalut);
		double kwota3 = kwota2 / Double.parseDouble(wartosciWalut[ww - 1][2]);
		System.out.println("Kwota jak� dostaniesz: " + dwiePoPrzecinku.format(kwota3) + " " + wartosciWalut[ww - 1][0]);
	}

	public static void main(String[] args) throws IOException {

		obliczanieWalut(args.length > 0 ? args[0] : "kursy_walut2.csv");

	}

}