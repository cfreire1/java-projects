package examples;

import java.time.LocalDate;
import java.time.Period;
import java.util.function.Function;

public class FunctionalInterfacesRun {
    public static void main(String[] args) {

        System.out.println("[SAM: Single Abstrab Method]");
        /**
         * SAM: Single Abstrab Method, Es una interfaz que tiene un solo
         * método sin definir. Para ello decoramos con @FuctionalInterface.
         * Nos permite crear nuestras propias funciones.
         */

        System.out.println("=>:Ejemplo 1:Crear formato de fecha");

        //Agregar ceros
        Function<Integer, String> addCeros = inputNumber ->
                inputNumber <= 10 ? String.format("%02d",inputNumber) : String.valueOf(inputNumber);

        System.out.println(addCeros.apply(10));

        // Crear formato de fecha
        TriFunction<Integer, Integer, Integer, LocalDate> parseDate = (day, month, year) ->
                LocalDate.parse(year + "-" + addCeros.apply(month) + "-" + addCeros.apply(day));

        System.out.println(parseDate.apply(10, 9, 1992));

        System.out.println("=>:Ejemplo 2:calcular edad");
        TriFunction<Integer, Integer, Integer, Integer> calculateAge = (day, month, year) ->
                Period.between(parseDate.apply(day, month, year), LocalDate.now()).getYears();

        System.out.println(calculateAge.apply(27, 1, 1995));



    }
    @FunctionalInterface
    interface TriFunction<T,U,V,R>{
        R apply(T t,U u,V v);
    }

}
