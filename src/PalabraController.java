import java.util.ArrayList;
import java.util.Scanner;

public class PalabraController {
    private BDController palabraController;

    public PalabraController() {
        this.palabraController = new BDController();
    }

    public void altaPalabra(){
        Scanner sc = new Scanner(System.in);
        Palabra palabra = new Palabra();
        String line;
        String diccionario;
        System.out.print("Español --> ");
        line = sc.nextLine().toLowerCase();
        if (line.startsWith("*")){
            line = line.substring(1,line.length());
            palabra.setEsp(line);
            System.out.print("English --> ");
            line = sc.nextLine().toLowerCase();
            palabra.setIng(line);
            this.palabraController.altaPalabra(palabra);
        }else {
            if (!palabraController.existePesp(line)) {
                diccionario = "desp";
                if (palabraController.existePDiccionario(diccionario, line)) {
                    palabra.setEsp(line);
                    System.out.print("English --> ");
                    line = sc.nextLine().toLowerCase();
                    diccionario = "ding";
                    if (palabraController.existePDiccionario(diccionario, line)) {
                        palabra.setIng(line);
                        this.palabraController.altaPalabra(palabra);
                    } else {
                        System.out.println("No existe esa palabra en ingles");
                    }
                } else {
                    System.out.println("No existe esa palabra en español");
                }
            } else {
                System.out.println("Ya existe esa palabra en nuestro vocabulario");
            }
        }
    }
    public void bajaPalabra(){
        Scanner sc = new Scanner(System.in);
        String palabra;
        System.out.print("Que palabra desea borrar: ");
        palabra = sc.nextLine();
        if (palabraController.existePalabra(palabra)){
            this.palabraController.bajaPalabra(palabra);
        }else{
            System.out.println("No existe esa palabra");
        }
    }
    public void randomStudy(){
        Scanner sc = new Scanner(System.in);
        String resp;
        boolean checkFallos = true;
        ArrayList<Palabra> fallos = new ArrayList<>();
        int contFallos = 0;
        for (Palabra palabra : this.palabraController.palabrasRandom()) {
            System.out.println("Español --> "+palabra.getEsp());
            System.out.print("English --> ");
            resp = sc.nextLine();
            if (!resp.equalsIgnoreCase(palabra.getIng())){
                System.out.println("¡¡¡ERROR!!! Ingles --> "+palabra.getIng());
                contFallos++;
                fallos.add(palabra);
            }
        }
        if (contFallos == 0){
            System.out.println("ENHORABUENA, TODO ACERTADO A LA PRIMERA!!! SIGUE ASÍ!!");
        }else{
            System.out.println("Tiene "+contFallos+" fallos. Desea repetir sus fallos?");
            resp = sc.nextLine();
            if (resp.equalsIgnoreCase("si")){
                do {
                    contFallos = 0;
                    for (Palabra fallo : fallos) {
                        System.out.println("Español --> "+fallo.getEsp());
                        System.out.print("English --> ");
                        resp = sc.nextLine();
                        if (!resp.equalsIgnoreCase(fallo.getIng())){
                            System.out.println("¡¡¡ERROR!!! Ingles --> "+fallo.getIng());
                            contFallos++;
                        }else{
                            fallos.remove(fallo);
                        }
                    }
                    if (contFallos != 0){
                        System.out.println("Sigue teniendo fallos, seguimos intentandolo?");
                        resp = sc.nextLine();
                        if (!resp.equalsIgnoreCase("si")){
                            System.out.println("Hasta otra!");
                            contFallos = 0;
                            checkFallos = false;
                        }
                    }
                }while (contFallos != 0);
               if (checkFallos){
                   System.out.println("Enhorabuena, hemos mitigado los fallos");
               }
            }else{
                System.out.println("Hasta otra!");
            }
        }
    }
    public void traductor(){
        Scanner sc = new Scanner(System.in);
        String line;
        System.out.println("Dime una palabra: ");
        line = sc.nextLine();
        if (palabraController.existePalabra(line)){
            System.out.println("******************************");
            System.out.println("* Español --> "+palabraController.traductor(line).getEsp()+" *");
            System.out.println("* Ingles --> "+palabraController.traductor(line).getIng()+" *");
            System.out.println("******************************");
        }else{
            System.out.println("Esa palabra no está en nuestro vocabulario");
        }
    }
}
