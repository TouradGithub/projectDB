public class Users {
    private  int id;
    private String nom;
    private String prenom;
    private  int age;


    public Users(int id, String nom, String prenom, int age) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getAge() {
        return age;
    }
    public void setNom(String Nom)
    {
        this.nom = Nom;
    }
    public void setPrenom(String Prenom)
    {
        this.prenom = Prenom;
    }
    public void setAge(int Age)
    {
        this.age = Age;
    }


}
