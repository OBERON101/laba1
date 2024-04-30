package library.entity;

public class EducationalLiterature extends Literature{
    private LiteratureLevel level;
    private String param;
    private String discipline;

    public EducationalLiterature(String title, String author, Lang lang, LiteratureLevel level, String param, String discipline) {
        super(title, author, lang);
        this.level = level;
        this.param = param;
        this.discipline = discipline;
    }

    public LiteratureLevel getLevel() {
        return level;
    }

    public String getParam() {
        return param;
    }

    public String getDiscipline() {
        return discipline;
    }
}
