package Dao;

import Model.Etudiant;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
@Data @AllArgsConstructor
@Component("dao")
public class EtudiantDao implements IDao<Etudiant,Long>{
    @Override
    public Etudiant trouverParID(Long id) {
        System.out.println("[DAO] Trouver l'etudiant n° : " + id);
        return BDEtudiant()
                .stream()
                .filter(etudiant -> etudiant.getId() == id)
                .findFirst()
                .get();
    }
    public static Set<Etudiant> BDEtudiant(){
        return new HashSet<Etudiant>(
                Arrays.asList(
                        new Etudiant(1L,"Nizar","Oubbali","4IIR9",1L,17.00,18.00,15.50,0.00),
                        new Etudiant(2L,"Walid","Moddar Alaoui","4IIR9",1L,15.0,18.0,16.5,0.0),
                        new Etudiant(3L,"Mehdi","Kerroumi","4IIR9",2L,19.0,14.5,17.0,0.0),
                        new Etudiant(4L,"Mahdi","Bouhli","4IIR9",2L,14.5,13.5,17.0,0.0),
                        new Etudiant(5L,"Chwiqlan","Test","4IIR9",2L,9.5,10.5,7.0,0.0)

                )
        );
    }

}
