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
        do {
            System.out.print("Español --> ");
            line = sc.nextLine().toLowerCase();
            if (line.startsWith("*")) {
                line = line.substring(1, line.length());
                palabra.setEsp(line);
                System.out.print("English --> ");
                line = sc.nextLine().toLowerCase();
                palabra.setIng(line);
                this.palabraController.altaPalabra(palabra);
            } else {
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
        }while (!line.equalsIgnoreCase("-1"));
    }
    public void altaVerbo(){
        Scanner sc = new Scanner(System.in);
        Verbo verbo = new Verbo();
        String line;
        String diccionario;
        do {
            System.out.print("Español --> ");
            line = sc.nextLine().toLowerCase();
            if (line.startsWith("*")) {
                line = line.substring(1, line.length());
                verbo.setVerb(line);
                System.out.print("Present --> ");
                line = sc.nextLine().toLowerCase();
                verbo.setPresent(line);
                System.out.print("Past -- >");
                line = sc.nextLine().toLowerCase();
                verbo.setPast(line);
                this.palabraController.altaVerbo(verbo);
            } else {
                if (!palabraController.existeVerbo(line)) {
                    diccionario = "desp";
                    if (palabraController.existePDiccionario(diccionario, line)) {
                        verbo.setVerb(line);
                        System.out.print("Present --> ");
                        line = sc.nextLine().toLowerCase();
                        diccionario = "ding";
                        if (palabraController.existePDiccionario(diccionario, line)) {
                            verbo.setPresent(line);
                            System.out.print("Past --> ");
                            line = sc.nextLine().toLowerCase();
                            if (palabraController.existePDiccionario(diccionario, line)){
                                verbo.setPast(line);
                                this.palabraController.altaVerbo(verbo);
                            }else{
                                System.out.println("No existe ese verbo en ingles");
                            }
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
        }while (!line.equalsIgnoreCase("-1"));
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
    public void traductor(){
        Scanner sc = new Scanner(System.in);
        String line;
        System.out.println("Dime una palabra o verbo: ");
        line = sc.nextLine();
        if (palabraController.existePalabra(line)){
            System.out.println("******************************");
            System.out.println("* Español --> "+palabraController.traductorPalabra(line).getEsp()+" *");
            System.out.println("* Ingles --> "+palabraController.traductorPalabra(line).getIng()+" *");
            System.out.println("******************************");
            System.out.println("******************************");
            System.out.println("* Verbo --> "+palabraController.traductorVerbo(line).getVerb()+" *");
            System.out.println("* Presente --> "+palabraController.traductorVerbo(line).getPresent()+" *");
            System.out.println("* Pasado --> "+palabraController.traductorVerbo(line).getPast()+" *");
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
        boolean check = true;
        time_start = System.currentTimeMillis();
        for (Palabra palabra : this.palabraController.palabrasRandom()) {
            System.out.println("Español --> "+palabra.getEsp());
            System.out.print("English --> ");
            resp = sc.nextLine();
            if (!resp.equalsIgnoreCase("-1")) {
                if (!resp.equalsIgnoreCase(palabra.getIng())) {
                    System.out.println("¡¡¡ERROR!!! Ingles --> " + palabra.getIng());
                    contFallos++;
                    fallos.add(palabra);
                }
            }else{
                System.out.println("Hasta luego");
                time_end = System.currentTimeMillis();
                check = false;
                break;
            }
        }
        if (check) {
            if (contFallos == 0) {
                System.out.println("ENHORABUENA, TODO ACERTADO A LA PRIMERA!!! SIGUE ASÍ!!");
                time_end = System.currentTimeMillis();
            } else {
                System.out.println("Tiene " + contFallos + " fallos. Desea repetir sus fallos?");
                resp = sc.nextLine();
                if (resp.equalsIgnoreCase("si")) {
                    do {
                        contFallos = 0;
                        for (Palabra fallo : fallos) {
                            System.out.println("Español --> " + fallo.getEsp());
                            System.out.print("English --> ");
                            resp = sc.nextLine();
                            if (!resp.equalsIgnoreCase(fallo.getIng())) {
                                System.out.println("¡¡¡ERROR!!! Ingles --> " + fallo.getIng());
                                contFallos++;
                            }
                        }
                        if (contFallos != 0) {
                            System.out.println("Sigue teniendo fallos, seguimos intentandolo?");
                            resp = sc.nextLine();
                            if (!resp.equalsIgnoreCase("si")) {
                                System.out.println("Hasta otra!");
                                time_end = System.currentTimeMillis();
                                contFallos = 0;
                                checkFallos = false;
                            }
                        }
                    } while (contFallos != 0);
                    if (checkFallos) {
                        System.out.println("Enhorabuena, hemos mitigado los fallos");
                        time_end = System.currentTimeMillis();
                    }
                } else {
                    System.out.println("Hasta otra!");
                    time_end = System.currentTimeMillis();
                }
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
        ArrayList<Verbo> fallos = new ArrayList<>();
        int contFallos = 0;
        long time_start;
        long time_end = 0;
        long time;
        long voca;
        long verb;
        boolean check = true;
        time_start = System.currentTimeMillis();
        for (Verbo verbo : this.palabraController.verbosRandom()) {
            System.out.println("Verbo --> "+verbo.getVerb());
            System.out.print("Present --> ");
            resp = sc.nextLine();
            if (!resp.equalsIgnoreCase("-1")) {
                if (!resp.equalsIgnoreCase(verbo.getPresent())) {
                    System.out.println("¡¡¡ERROR!!! Present --> " + verbo.getPresent());
                    contFallos++;
                    fallos.add(verbo);
                } else {
                    System.out.println("Past --> ");
                    resp = sc.nextLine();
                    if (!resp.equalsIgnoreCase(verbo.getPast())) {
                        System.out.println("¡¡¡ERROR!!! Past --> " + verbo.getPast());
                        contFallos++;
                        fallos.add(verbo);
                    }
                }
            }else{
                System.out.println("Hasta luego!");
                time_end = System.currentTimeMillis();
                check = false;
                break;
            }
        }
        if (check) {
            if (contFallos == 0) {
                System.out.println("ENHORABUENA, TODO ACERTADO A LA PRIMERA!!!! SIGUE ASÍ!!");
                time_end = System.currentTimeMillis();
            } else {
                System.out.println("Tiene " + contFallos + " fallos. Desea repetir sus fallos?");
                resp = sc.nextLine();
                if (resp.equalsIgnoreCase("si")) {
                    do {
                        contFallos = 0;
                        for (Verbo fallo : fallos) {
                            System.out.println("Verbo ---> " + fallo.getVerb());
                            System.out.print("Present ---> ");
                            resp = sc.nextLine();
                            if (!resp.equalsIgnoreCase(fallo.getPresent())) {
                                System.out.println("¡¡¡ERROR!!! Present --> " + fallo.getPresent());
                                contFallos++;
                            } else {
                                System.out.print("Past ---> ");
                                resp = sc.nextLine();
                                if (!resp.equalsIgnoreCase(fallo.getPast())) {
                                    System.out.println("¡¡¡ERROR!!! Past --> " + fallo.getPast());
                                    contFallos++;
                                }
                            }
                        }
                        if (contFallos != 0) {
                            System.out.println("Sigue teniendo fallos, seguimos intentandolo ?");
                            resp = sc.nextLine();
                            if (!resp.equalsIgnoreCase("si")) {
                                System.out.println("Hasta otra!!");
                                time_end = System.currentTimeMillis();
                                contFallos = 0;
                                checkFallos = false;
                            }
                        }
                    } while (contFallos != 0);
                    if (checkFallos) {
                        System.out.println("Enhorabuena, hemos mitigado los fallos");
                        time_end = System.currentTimeMillis();
                    }
                } else {
                    System.out.println("Hasta otra!");
                    time_end = System.currentTimeMillis();
                }
            }
        }
        time = palabraController.tiempoEstudio() + (( time_end - time_start )/1000);
        voca = palabraController.tiempoEstVoca();
        verb = palabraController.tiempoEstVerb() + (( time_end - time_start )/1000);
        palabraController.insertarTiempo(time,voca,verb);
    }

}
