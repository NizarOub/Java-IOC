package Metier;

import Dao.IDao;
import Model.Etudiant;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Data @AllArgsConstructor @NoArgsConstructor
@Service("metier")
public class EtudiantMetier implements IEtudiantMetier{
    @Autowired
    @Qualifier("dao")
    IDao<Etudiant, Long> etudiantDao;
    @Override
    public Etudiant calculer_Moyenne(Long id) throws Exception {
        var etudiant = etudiantDao.trouverParID(id);
        if (etudiant == null){
            throw new Exception("L'id d'etudiant est incorrecte :: [Credit non trouve]");
        }
        else {
            Double note1 = etudiant.getNote1();
            Double note2 = etudiant.getNote2();
            Double note3 = etudiant.getNote3();
            double moyenne = (note1+note2+note3)/3;
                   moyenne = Math.round(moyenne*100)/100;
            etudiant.setMoyenne(moyenne);
            return etudiant;
        }
    }
}
