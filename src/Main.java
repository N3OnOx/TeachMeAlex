import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        int opt;
        PalabraController palabraController = new PalabraController();
        do {
            mostrarMenu();
            opt = sn.nextByte();
            switch (opt){
                case 1:
                    menuAltas();
                    opt = sn.nextByte();
                    switch (opt){
                        case 1:
                            palabraController.altaPalabra();
                            break;
                        case 2:
                            palabraController.altaVerbo();
                            break;
                        default:
                            System.out.println("Opcion invalida");
                            break;
                    }
                    break;
                case 2:
                    palabraController.bajaPalabra();
                    break;
                case 3:
                    menuEstudiar();
                    opt = sn.nextByte();
                    switch (opt){
                        case 1:
                            palabraController.vocabulary();
                            break;
                        case 2:
                            palabraController.verbs();
                            break;
                            default:
                                System.out.println("Opcion invalida");
                                break;
                    }
                    break;
                case 4:
                    System.out.println("Has elegido salir");
                    break;
                    default:
                        System.out.println("Opción inválida");
                        break;
            }
        }while (opt != 4);
    }
    static void mostrarMenu(){
        System.out.println("1. Altas");
        System.out.println("2. Bajas");
        System.out.println("3. Estudiar");
        System.out.println("4. Salir");
    }
    static void menuEstudiar(){
        System.out.println("1. Estudiar vocabulario");
        System.out.println("2. Estudiar verbos regulares");
        System.out.println("3. Estudiar verbos irregulares");
    }
    static void menuAltas(){
        System.out.println("1. Alta palabra");
        System.out.println("2. Alta verbo");
    }
}
