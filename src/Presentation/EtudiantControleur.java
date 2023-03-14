package Presentation;

import Metier.IEtudiantMetier;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Data @AllArgsConstructor @NoArgsConstructor
@Controller
public class EtudiantControleur implements IEtudiantControleur{
    @Autowired
    @Qualifier("metier")
    IEtudiantMetier etudiantMetier;
    @Override
    public void afficher_Moyenne(Long id) throws Exception {
        var etudiantAvecMoyenne = etudiantMetier.calculer_Moyenne(id);
        System.out.println(etudiantAvecMoyenne);
    }
}
