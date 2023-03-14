package Model;


import lombok.*;

@Data @AllArgsConstructor @NoArgsConstructor
public class Etudiant {
    private Long id;
    private String first_Name;
    private String last_Name;
    private String classe;
    private Long groupe;
    private Double note1;
    private Double note2;
    private Double note3;
    private Double moyenne;

    public String nom_Complet(){
        return getLast_Name() + " " + getFirst_Name();
    }
    @Override
    public String toString(){
        var studentStr = "==================================================================== \n";
        studentStr += "=> Etudiant n°                   : " +getId()+ "                        \n";
        studentStr += "=> Nom complet d'etudiant        : " +nom_Complet()+ "                  \n";
        studentStr += "=> Claase                        : " +getClasse()+ " G" + getGroupe()+ "\n";
        studentStr += "=> Note n° 1                     : " +getNote1()+ "                     \n";
        studentStr += "=> Note n° 2                     : " +getNote2()+ "                     \n";
        studentStr += "=> Note n° 3                     : " +getNote3()+ "                     \n";
        studentStr += "=> La moyenne                    : "
                + (getMoyenne() == 0.0 ? "NON-CALCULE":getMoyenne())+"                         \n";
        if (getMoyenne() > 10){
        studentStr += "=> Validation                    : Validé                               \n";
        }
        else {
        studentStr += "=> Validation                    : Non-Validé                           \n";
        }

        return studentStr;
    }

}
