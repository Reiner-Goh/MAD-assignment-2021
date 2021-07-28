package sg.edu.np.madapplcation;

public class Concept {

    private String Concept;
    private String Description;



    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }



    public String getConcept() {
        return Concept;
    }

    public void setConcept(String concept) {
        Concept = concept;
    }

    public Concept(String concept, String description) {
        Concept = concept;
        Description = description;
    }
}
