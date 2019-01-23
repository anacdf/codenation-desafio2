package challenge;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
	 private List<Jogador> jogadores = new ArrayList<>();
	 private Jogador jogador;

	public Main(){
		try (
				Reader reader = Files.newBufferedReader(Paths.get("C:\\Users\\ana.c.a.ferreira\\codenation\\java-3\\src\\main\\resources\\data.csv"), Charset.forName("UTF-8"));
				CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader().withDelimiter(','));
		) {
			for (CSVRecord csvRecord : csvParser) {
				String name = csvRecord.get(1);
				String full_name = csvRecord.get(2);
				String club = csvRecord.get(3);
				Integer age = Integer.valueOf(csvRecord.get(6));
				String birth_date = csvRecord.get(8);
				String nationality = csvRecord.get(14);
				Double eur_wage = Double.valueOf(csvRecord.get(17));
				String eur_clause_txt = csvRecord.get(18);
				Double eur_release_clause=0.0;
					if(eur_clause_txt.equals(null)||eur_clause_txt.equals("")){
						eur_release_clause = 0.0;
					} else eur_release_clause = Double.valueOf(csvRecord.get(18));

				jogador = new Jogador();

				jogador.setName(name);
				jogador.setFull_name(full_name);
				jogador.setClub(club);
				jogador.setAge(age);
				jogador.setBirth_date(birth_date);
				jogador.setNationality(nationality);
				jogador.setEur_wage(eur_wage);
				jogador.setEur_release_clause(eur_release_clause);

				jogadores.add(jogador);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Quantas nacionalidades (coluna `nationality`) diferentes existem no arquivo?
	public int q1() {
		Set<String> nacionalidades = jogadores.stream().map(jogador1 -> jogador1.getNationality()).collect(Collectors.toSet());

		return nacionalidades.size();
	}

	// Quantos clubes (coluna `club`) diferentes existem no arquivo?
	// Obs: Existem jogadores sem clube.
	public int q2() {
		Set<String> clubes = jogadores.stream().map(jogador1 -> jogador1.getClub()).collect(Collectors.toSet());
		clubes.removeIf(c -> c.isEmpty());
		return clubes.size();
	}

	// Liste o primeiro nome (coluna `full_name`) dos 20 primeiros jogadores.
	public List<String> q3() {
		List<String> primeiros = jogadores.stream().limit(20).map(jogador1 -> jogador1.getFull_name()).collect(Collectors.toList());

		return primeiros;
	}

	// Quem s�o os top 10 jogadores que possuem as maiores cl�usulas de rescis�o?
	// (utilize as colunas `full_name` e `eur_release_clause`)
	public List<String> q4() {
		List<String> clausulas = jogadores.stream().sorted(Comparator.comparing(Jogador::getEur_release_clause)
								.reversed()).limit(10).map(Jogador::getFull_name).collect(Collectors.toList());
		return clausulas;
	}

	// Quem s�o os 10 jogadores mais velhos (use como crit�rio de desempate o campo `eur_wage`)?
	// (utilize as colunas `full_name` e `birth_date`)
	public List<String> q5() {
		List<String> velhos = jogadores.stream().sorted(Comparator.comparing(Jogador::getBirth_date).thenComparing(Jogador::getEur_wage)).limit(10)
		.map(Jogador::getFull_name).collect(Collectors.toList());

		return velhos;
	}

	// Conte quantos jogadores existem por idade. Para isso, construa um mapa onde as chaves s�o as idades e os valores a contagem.
	// (utilize a coluna `age`)
	public Map<Integer, Integer> q6() {
		List<Integer> idades = new ArrayList<>();
		Map<Integer, Integer> quant = new LinkedHashMap<>();

		for(int i=0; i<jogadores.size(); i++){
			idades.add(jogadores.get(i).getAge());
		}
		Iterator<Integer> it = idades.iterator();
		while (it.hasNext()){
			Integer obj = it.next();
			Integer cont = Collections.frequency(idades, obj);
			quant.put(obj, cont);
		}
		return quant;
	}

}