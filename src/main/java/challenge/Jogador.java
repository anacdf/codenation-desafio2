package challenge;

public class Jogador {

    String name; //1
    String full_name; //2
    String club; //3
    Integer age; //6
    String birth_date; //8
    String nationality; //14
    Double eur_wage; //17
    Double eur_release_clause; //18

    public Jogador() {}

    public Jogador(String name, String full_name, String club, Integer age,
                   String birth_date, String nationality, Double eur_wage, Double eur_release_clause) {
        this.name = name;
        this.full_name = full_name;
        this.club = club;
        this.age = age;
        this.birth_date = birth_date;
        this.nationality = nationality;
        this.eur_wage = eur_wage;
        this.eur_release_clause = eur_release_clause;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Double getEur_wage() {
        return eur_wage;
    }

    public void setEur_wage(Double eur_wage) {
        this.eur_wage = eur_wage;
    }

    public Double getEur_release_clause() {
        return eur_release_clause;
    }

    public void setEur_release_clause(Double eur_release_clause) {
        this.eur_release_clause = eur_release_clause;
    }
}
