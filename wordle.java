
import java.util.Random;
import java.util.Scanner;

public class wordle {
	public static void main(String[] args) {
		String[] listaDePalabras = new String[] { "PERRO", "GATOS", "MANGO", "JUEGO", "MARIO", "PELIS", "BARCO",
				"RUEDA" };
		Random random = new Random();
		int palabra = random.nextInt(listaDePalabras.length);
		String wordle = listaDePalabras[palabra];

		Scanner teclado = new Scanner(System.in);

		String palabraSinProcesar = "";
		String palabraDelUsuario = "";

		System.out.println("-Si una letra está recogida entre paréntesis '(A)' esa letra estará escrita erróneamente.");
		System.out.println(
				"-Si una letra está recogida entre corchetes '{A}' esa letra estará bien escrita pero en el lugar erróneo.");
		System.out.println(
				"-Si una letra está recogida entre corchetes '[A]' esa letra estará bien escrita en el lugar correcto.");

		int numeroDeVidas = 5;
		String[] filasImprimir = new String[numeroDeVidas];
		for (int i = 0; i < filasImprimir.length; i++) {
			filasImprimir[i] = "";
		}

		boolean victoria = false;
		int intento = 0;
		for (intento = 0; intento < numeroDeVidas && !victoria; intento++) {
			if (intento > 0) {
				for (int i = 0; i < intento; i++) {
					System.out.println(filasImprimir[i]);
				}
			}
			for (int i = 0; i < numeroDeVidas - intento; i++) {
				System.out.println("| || || || || |");
			}

			boolean seguir = true;
			boolean seguir2 = false;

			do {
				System.out.println("Introduzca una palabra de 5 letras sin tilde: ");
				palabraSinProcesar = teclado.next();
				palabraDelUsuario = "";
				if (palabraSinProcesar.length() == 5) {
					seguir = false;
					for (int i = 0; i < 5; i++) {

						if (palabraSinProcesar.charAt(i) > 96 && palabraSinProcesar.charAt(i) < 123) {
							palabraDelUsuario = palabraDelUsuario + (char) (palabraSinProcesar.charAt(i) - 32);
						} else if (palabraSinProcesar.charAt(i) > 64 && palabraSinProcesar.charAt(i) < 91) {
							palabraDelUsuario = palabraDelUsuario + palabraSinProcesar.charAt(i);
						} else {
							seguir2 = true;
						}
					}

					if (seguir2) {
						seguir = true;
						System.out.println("Solo puedes introducir letras sin tilde.");
						seguir2 = false;
					}

				} else if (palabraSinProcesar.length() > 5) {
					System.out.println("La palabra introducida tiene más de 5 caracteres.");
				} else {
					System.out.println("La palabra introducida tiene menos de 5 caracteres.");
				}

			} while (seguir);

			int contador = 0;
			for (int i = 0; i < 5; i++) {
				if (wordle.charAt(i) == palabraDelUsuario.charAt(i)) {
					filasImprimir[intento] = filasImprimir[intento] + "[" + palabraDelUsuario.charAt(i) + "]";
					contador++;
				} else {
					boolean pertenece = false;
					for (int j = 0; j < 5; j++) {
						if (wordle.charAt(j) == palabraDelUsuario.charAt(i)) {
							pertenece = true;
						}
					}
					if (pertenece) {
						filasImprimir[intento] = filasImprimir[intento] + "{" + palabraDelUsuario.charAt(i) + "}";
					} else {
						filasImprimir[intento] = filasImprimir[intento] + "(" + palabraDelUsuario.charAt(i) + ")";
					}
				}
			}
			if (contador == 5) {
				victoria = true;
			}
		}
		if (victoria) {
			if (intento == 1) {
				System.out.println("¡Has adivinado la palabra en 1 intento!");
			} else {
				System.out.println("¡Has adivinado la palabra en " + intento + " intentos!");
			}
		} else {
			System.out.println("¡No has adivinado la palabra!");
		}
	}
}
