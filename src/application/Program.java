package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import entities.Sale;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);

		Scanner sc = new Scanner(System.in);

		System.out.print("Entre o caminho do arquivo: ");
		String path = sc.nextLine();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			Set<Sale> set = new HashSet<>();
			String line = br.readLine();
			while (line != null) {

				String[] fields = line.split(",");

				Integer month = Integer.parseInt(fields[0]);
				Integer year = Integer.parseInt(fields[1]);
				String seller = fields[2];
				Integer items = Integer.parseInt(fields[3]);
				Double total = Double.parseDouble(fields[4]);

				set.add(new Sale(month, year, seller, items, total));

				line = br.readLine();

			}

			double sum = set.stream().map(s -> s.getTotal()).reduce(0.0, (x, y) -> x + y);

			System.out.println("Total de vendas por vendedor: ");

			for (Sale sale : set) {
				System.out.println(sale);
			}

		}

		catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
		}

		sc.close();

	}
}
