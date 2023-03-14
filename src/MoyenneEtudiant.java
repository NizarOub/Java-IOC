import Dao.EtudiantDao;
import Dao.IDao;
import Metier.EtudiantMetier;
import Metier.IEtudiantMetier;
import Model.Etudiant;
import Presentation.EtudiantControleur;
import Presentation.IEtudiantControleur;
import lombok.var;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.Scanner;

public class MoyenneEtudiant {
    static Scanner clavier = new Scanner(System.in);
    static IEtudiantControleur etudiantControleur;

    private static boolean estUnNombre(String input){
        try {
            Integer.parseInt(input);
            return true;
        }
        catch (Exception e ){
            return false;
        }
    }

    public static void test1(){
        var dao = new EtudiantDao();
        var metier = new EtudiantMetier();
        var controleur = new EtudiantControleur();

        metier.setEtudiantDao(dao);
        controleur.setEtudiantMetier(metier);

        String rep = "";
        do {
            System.out.println("=> [Test 1] calcule moyenne d'etudiant <= \n");
            try {
                String input = "";
                while (true){
                    System.out.println("=> Entrez l'id d'etudiant : ");
                    input = clavier.nextLine();
                    if (estUnNombre(input)) break;
                    System.err.println("Entrée non valide !!!");
                }
                long id = Long.parseLong(input);
                controleur.afficher_Moyenne(id);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            System.out.println("Voulez vous quittez (oui/non) ? ");
            rep = clavier.nextLine();
        }while (!rep.equalsIgnoreCase("oui"));
        System.out.println("Au revoir ^_^");
    }

    public static void test2() throws Exception {

        String daoClass;
        String serviceClass;
        String controllerClass;

        Properties properties = new Properties();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream("config.properties");

        if (propertiesFile == null) throw new Exception("fichier config introuvable");
        else {
            try {
                properties.load(propertiesFile);
                daoClass        = properties.getProperty("DAO");
                serviceClass    = properties.getProperty("SERVICE");
                controllerClass = properties.getProperty("CONTROLLER");
                propertiesFile.close();
            }
            catch (IOException e){
                throw new Exception("Problème de chargement des propriétés du fichier config");
            }
            finally {
                properties.clear();
            }
        }
        try {
            Class cDao          = Class.forName(daoClass);
            Class cMetier       = Class.forName(serviceClass);
            Class cController   = Class.forName(controllerClass);

            var dao = (IDao<Etudiant, Long>) cDao.getDeclaredConstructor().newInstance();
            var metier = (IEtudiantMetier) cMetier.getDeclaredConstructor().newInstance();
            etudiantControleur    = (IEtudiantControleur) cController.getDeclaredConstructor().newInstance();

            Method setDao       = cMetier.getMethod("setEtudiantDao", IDao.class);
            setDao.invoke(metier,dao);

            Method setMetier    = cController.getMethod("setEtudiantMetier", IEtudiantMetier.class);
            setMetier.invoke(etudiantControleur,metier);

            etudiantControleur.afficher_Moyenne(1L);
        }
        catch (Exception e ){
            e.printStackTrace();
        }
    }

    public static void test3() throws Exception{
        ApplicationContext context = new ClassPathXmlApplicationContext("/spring-ioc.xml");
        etudiantControleur = (IEtudiantControleur) context.getBean("controller");
        etudiantControleur.afficher_Moyenne(1L);
    }

    public static void test4() throws Exception{
        ApplicationContext context = new AnnotationConfigApplicationContext("dao","metier","presentation");
        etudiantControleur = (IEtudiantControleur) context.getBean(IEtudiantControleur.class);
        etudiantControleur.afficher_Moyenne(5L);
    }
    public static void main(String[] args) throws Exception{
    test4();
    }
}
