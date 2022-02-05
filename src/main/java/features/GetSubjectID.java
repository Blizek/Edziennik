package features;

public class GetSubjectID {
    public static int get(String subjectName) {
        int id = -1;
        switch (subjectName) {
            case "Język polski" -> id = 0;
            case "Język angielski" -> id = 1;
            case "Język niemiecki" -> id = 2;
            case "Język rosyjski" -> id = 3;
            case "Język hiszpański" -> id = 4;
            case "Język francuski" -> id = 5;
            case "Historia" -> id = 6;
            case "Wiedza o społeczeństwie" -> id = 7;
            case "Matematyka" -> id = 8;
            case "Fizyka" -> id = 9;
            case "Chemia" -> id = 10;
            case "Biologia" -> id = 11;
            case "Wychowanie do życia w rodzinie" -> id = 12;
            case "Geografia" -> id = 13;
            case "Podstawy przedsiębiorczości" -> id = 14;
            case "Informatyka" -> id = 15;
            case "Wychowanie fizyczne" -> id = 16;
            case "Edukacja dla bezpieczeństwa" -> id = 17;
            case "Religia" -> id = 18;
            case "Filozofia" -> id = 19;
        }

        return id;
    }
}
