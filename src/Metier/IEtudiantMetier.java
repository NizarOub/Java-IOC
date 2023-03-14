package Metier;

import Model.Etudiant;

public interface IEtudiantMetier {
    Etudiant calculer_Moyenne(Long id) throws Exception;
}
