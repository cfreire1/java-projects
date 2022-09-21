package examples;


import java.util.Arrays;
import java.util.List;

public class ReferenceMethodRun {
    public static void main(String[] args) {

        System.out.println("[Operator::<...>]");
        /**
         * Operator::
         * - Es una referencia a metodos y contructores mediante la
         * implementacion de una interfaz funcional y java infiere la
         * informacion del contexto
         */


        List<String> personas = Arrays.asList("Belisario","Eduardo","Juan","","");

        System.out.println("=>:Imprimir registros");
        System.out.println("=>=>:Tradicional");
            for (String ps: personas) {
                System.out.println(ps);
            }

        System.out.println("=>=>:lambda");
            personas.forEach(p -> System.out.println(p));

        System.out.println("=>=>:Referencia de metodo");
            personas.forEach(System.out::println);

        //=================================================================
        System.out.println("=>:Busqueda de persona");
            String busqueda = "Juan";

        System.out.println("=>=>:Tradicional");
            for (String ps: personas) {
                if (busqueda.equalsIgnoreCase(ps)){
                    System.out.println(ps);
                }
            }
        System.out.println("=>=>:lambda");
            personas.stream().filter(p -> busqueda.equalsIgnoreCase(p)).forEach(p -> System.out.println(p));

        System.out.println("=>=>:Referencia de metodo");
            personas.stream().filter(busqueda::equalsIgnoreCase).forEach(System.out::println);

        //=================================================================
        System.out.println("=>:Cuantos valores esta vacios");
        System.out.println("=>=>:Tradicional");
            long count = 0;
            for (String ps: personas) {
                if (ps.equalsIgnoreCase("")){
                    count++;
                }
            }
            System.out.println(count);

        System.out.println("=>=>:lambda");
            System.out.println(personas.stream().filter(p -> p.isEmpty()).count());

        System.out.println("=>=>:Referencia de metodo");
            System.out.println(personas.stream().filter(String::isEmpty).count());

    }
}
