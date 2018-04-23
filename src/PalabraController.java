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
    public void altaVerbo(){
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
            this.palabraController.altaVerbo(palabra);
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
                        this.palabraController.altaVerbo(palabra);
                    } else {
                        System.out.println("No existe ese verbo en ingles");
                    }
                } else {
                    System.out.println("No existe ese verbo en español");
                }
            } else {
                System.out.println("Ya existe ese verbo en nuestro vocabulario de verbos");
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
        }else if (palabraController.existeVerbo(palabra)){
            this.palabraController.bajaVerbo(palabra);
        }else{
            System.out.println("No existe esa palabra");
        }
    }
    public void traductor(){
        Scanner sc = new Scanner(System.in);
        String line;
        System.out.println("Dime una palabra o verbo: ");
        line = sc.nextLine();
        int opt = 0;
        if (palabraController.existePalabra(line)){
            opt = 1;
            System.out.println("******************************");
            System.out.println("* Español --> "+palabraController.traductor(line,opt).getEsp()+" *");
            System.out.println("* Ingles --> "+palabraController.traductor(line,opt).getIng()+" *");
            System.out.println("******************************");
        }else if (palabraController.existeVerbo(line)){
            opt = 2;
            System.out.println("******************************");
            System.out.println("* Español --> "+palabraController.traductor(line,opt).getEsp()+" *");
            System.out.println("* Ingles --> "+palabraController.traductor(line,opt).getIng()+" *");
            System.out.println("******************************");
        }else{
            System.out.println("Esa palabra no está en nuestro vocabulario");
        }
    }

    public void vocabulary(){
        Scanner sc = new Scanner(System.in);
        String resp;
        boolean checkFallos = true;
        ArrayList<Palabra> fallos = new ArrayList<>();
        int contFallos = 0;
        long time_start;
        long time_end = 0;
        long time;
        long voca;
        long verb;
        time_start = System.currentTimeMillis();
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
            time_end = System.currentTimeMillis();
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
                        }
                    }
                    if (contFallos != 0){
                        System.out.println("Sigue teniendo fallos, seguimos intentandolo?");
                        resp = sc.nextLine();
                        if (!resp.equalsIgnoreCase("si")){
                            System.out.println("Hasta otra!");
                            time_end = System.currentTimeMillis();
                            contFallos = 0;
                            checkFallos = false;
                        }
                    }
                }while (contFallos != 0);
               if (checkFallos){
                   System.out.println("Enhorabuena, hemos mitigado los fallos");
                   time_end = System.currentTimeMillis();
               }
            }else{
                System.out.println("Hasta otra!");
                time_end = System.currentTimeMillis();
            }
        }
        time = palabraController.tiempoEstudio() + (( time_end - time_start )/1000);
        voca = palabraController.tiempoEstVoca() + (( time_end - time_start )/1000);
        verb = palabraController.tiempoEstVerb();
        palabraController.insertarTiempo(time,voca,verb);
    }
    public void verbs(){
        Scanner sc = new Scanner(System.in);
        String resp;
        boolean checkFallos = true;
        ArrayList<Palabra> fallos = new ArrayList<>();
        int contFallos = 0;
        long time_start;
        long time_end = 0;
        long time;
        long voca;
        long verb;
        time_start = System.currentTimeMillis();
        for (Palabra palabra : this.palabraController.verbosRandom()) {
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
            System.out.println("ENHORABUENA, TODO ACERTADO A LA PRIMERA!!!! SIGUE ASÍ!!");
            time_end = System.currentTimeMillis();
        }else{
            System.out.println("Tiene "+contFallos+" fallos. Desea repetir sus fallos?");
            resp = sc.nextLine();
            if (resp.equalsIgnoreCase("si")){
                do {
                    contFallos = 0;
                    for (Palabra fallo : fallos) {
                        System.out.println("Español ---> "+fallo.getEsp());
                        System.out.print("English ---> ");
                        resp = sc.nextLine();
                        if (!resp.equalsIgnoreCase(fallo.getIng())){
                            System.out.println("¡¡¡ERROR!!! Ingles --> "+fallo.getIng());
                            contFallos++;
                        }
                    }
                    if (contFallos != 0){
                        System.out.println("Sigue teniendo fallos, seguimos intentandolo ?");
                        resp = sc.nextLine();
                        if (!resp.equalsIgnoreCase("si")){
                            System.out.println("Hasta otra!!");
                            time_end = System.currentTimeMillis();
                            contFallos = 0;
                            checkFallos = false;
                        }
                    }
                }while (contFallos != 0);
               if (checkFallos){
                   System.out.println("Enhorabuena, hemos mitigado los fallos");
                   time_end = System.currentTimeMillis();
               }
            }else{
                System.out.println("Hasta otra!");
                time_end = System.currentTimeMillis();
            }
        }
        time = palabraController.tiempoEstudio() + (( time_end - time_start )/1000);
        voca = palabraController.tiempoEstVoca();
        verb = palabraController.tiempoEstVerb() + (( time_end - time_start )/1000);
        palabraController.insertarTiempo(time,voca,verb);
    }

}
