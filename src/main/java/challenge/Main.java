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

//codenation submit -c java-3

public class Main {
	 private List<Jogador> jogadores = new ArrayList<>();
	 private Jogador jogador = new Jogador();;

	public Main(){
		try (
				Reader reader = Files.newBufferedReader(Paths.get("C:\\Users\\ana.c.a.ferreira\\codenation\\java-3\\src\\main\\resources\\data.csv"), Charset.forName("UTF-8"));
				CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader().withDelimiter(','));
		) {

			for (CSVRecord csvRecord : csvParser) {
				jogador = new Jogador();
				jogador.setName(csvRecord.get(1));
				jogador.setFull_name(csvRecord.get(2));
				jogador.setClub(csvRecord.get(3));
				jogador.setAge(Integer.valueOf(csvRecord.get(6)));
				jogador.setBirth_date(csvRecord.get(8));
				jogador.setNationality(csvRecord.get(14));
				jogador.setEur_wage(Double.valueOf(csvRecord.get(17)));
				String eur_release_clause = csvRecord.get(18);
					if(eur_release_clause.isEmpty()){
						jogador.setEur_release_clause(0.0);
					} else jogador.setEur_release_clause(Double.valueOf(eur_release_clause));

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

	// Quem são os top 10 jogadores que possuem as maiores cláusulas de rescisão?
	// (utilize as colunas `full_name` e `eur_release_clause`)
	public List<String> q4() {
		List<String> clausulas = jogadores.stream().sorted(Comparator.comparing(Jogador::getEur_release_clause)
								.reversed()).limit(10).map(Jogador::getFull_name).collect(Collectors.toList());
		return clausulas;
	}

	// Quem são os 10 jogadores mais velhos (use como critério de desempate o campo `eur_wage`)?
	// (utilize as colunas `full_name` e `birth_date`)
	public List<String> q5() {
		List<String> velhos = jogadores.stream().sorted(Comparator.comparing(Jogador::getBirth_date)
												.thenComparing(Jogador::getEur_wage)).limit(10)
												.map(Jogador::getFull_name).collect(Collectors.toList());
		return velhos;
	}

	// Conte quantos jogadores existem por idade. Para isso, construa um mapa onde as chaves são as idades e os valores a contagem.
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
