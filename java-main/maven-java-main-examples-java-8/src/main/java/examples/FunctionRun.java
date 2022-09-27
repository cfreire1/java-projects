package examples;

import data.pojo.Students;

import java.util.Arrays;
import java.util.List;
import java.util.function.*;

public class FunctionRun {
    public static void main(String[] args) {
        System.out.println("[Function]");
        /**
         * Trabaja sobre un tipo y deuelve otro tipo
         * Function<T, R>
         * T – Tipo de la entrada a la función.
         * R – Tipo del resultado de la función.
         *
         * Function: Una interfaz de función es más genérica que toma un argumento
         * y produce un resultado. Tiene un método abstracto único (SAM) que acepta
         * un argumento de tipo T y produce un resultado de tipo R. Uno de los casos
         * de uso común de esta interfaz es el método Stream.map.
         */
        System.out.println("=>:Ejemplo 1 : suma de 2 numeros");
        Function<Integer,Integer> suma = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer valX) {
                return valX + valX;
            }
        };
        System.out.println(suma.apply(2));

        System.out.println("=>:Ejemplo 1 : lambda => retorna suma de 2 numeros");
        Function<Integer,Integer> suma2 = valX -> valX + valX;
        System.out.println(suma2.apply(2));

        System.out.println("=>:Ejemplo 2 : lambda => Retorna texto y concatena numero");
        Function<Integer,String > texto2 = valX -> "Hola mundo" + valX;
        System.out.println(texto2.apply(2));

        //Agregar ceros
        System.out.println("=>:Ejemplo 3 : Agregar ceros");
        Function<Integer, String> addCeros = inputNumber ->
                inputNumber <= 10 ? String.format("%02d",inputNumber) : String.valueOf(inputNumber);
        System.out.println(addCeros.apply(10));

        //=================================================================
        System.out.println("[Predicate]");//Testear si algo es valido
        /**
         * Testear si algo es valido
         * Predicate<T>
         * T – Tipo de la entrada al predicado.
         * Predicate: Una interfaz Predicate representa una función de valor booleano
         * de un argumento. Esto se usa principalmente para filtrar datos de un
         * flujo de Java. El método de filtro de una secuencia acepta un predicado
         * para filtrar los datos y devolver una nueva secuencia que satisfaga el
         * predicado. Un predicado tiene un método test() que acepta un argumento y
         * devuelve un valor booleano.
         */

        System.out.println("=>:Ejemplo 1 : Evalua si el numero es par");
        Predicate<Integer> isEven = new Predicate<Integer>() {
            @Override
            public boolean test(Integer valX) {
                return valX % 2 == 0 ;
            }
        };
        System.out.println(isEven.test(4));


        System.out.println("=>:Ejemplo 1 : lambda => Evalua si el numero es par");
        Predicate<Integer> isEven2 = valX -> valX % 2 == 0;
        System.out.println(isEven2.test(2));

        System.out.println("=>:Ejemplo 2 : lambda => Evalua la calificacion de estudiante");
        Predicate<Students> studentsPredicate = students -> students.getQualification() > 6.0;
        Students studentsObj = new Students(5.9);
        System.out.println(studentsPredicate.test(studentsObj));

        //=================================================================
        System.out.println("[Consumer y Supplier]");
        /**
         * Consumer:
         * Un Consumidor es una interfaz funcional que acepta una sola entrada y no devuelve ningún resultado.
         * Supplier:
         * Un proveedor es una interfaz simple que indica que esta implementación es un proveedor
         * de resultados. Sin embargo, esta interfaz no impone ninguna restricción que la
         * implementación del proveedor necesite para devolver un resultado diferente en cada invocación.
         */

        System.out.println("=>:Ejemplo 1 : consumer");
        List<String> cities = Arrays.asList("Sydney", "Dhaka", "New York", "London");

        Consumer<List<String>> upperCaseConsumer = list -> {
            for(int i=0; i< list.size(); i++){
                list.set(i, list.get(i).toUpperCase());
            }
        };
        upperCaseConsumer.accept(cities);
        System.out.println(cities);

        System.out.println("=>:Ejemplo 1 : Supplier");
        Supplier<Double> doubleSupplier1 = () -> Math.random();
        System.out.println(doubleSupplier1.get());

        System.out.println("=>:Ejemplo 2 : Supplier");
        DoubleSupplier doubleSupplier2 = Math::random;
        System.out.println(doubleSupplier2.getAsDouble());


        //=================================================================
        System.out.println("[UnaryOperator]");
        /**
         * UnaryOPerator: Solo se especifica un solo tipo de dato. Se entiende
         * que tendrá como resultado el mismo tipo.
         */
        System.out.println("=>:Ejemplo 1 : UnaryOperator");
        UnaryOperator<String> quote = text -> "\""+text+"\"";
        System.out.println(quote.apply("Hola"));

        //=================================================================
        System.out.println("[BinaryOperator]");
        /**
         * BinaryOperator: Solo se especifica un tipo de dato. Se entiende que
         * tendrá 2 parámetros de entrada y el uno de retorno del mismo tipo de dato.
         */
        System.out.println("=>:Ejemplo 1 : BinaryOperator");
        BinaryOperator<Integer> multipliBO = (x, y) -> x * y;
        System.out.println(multipliBO.apply(4,5));



        //=================================================================
        System.out.println("[Bifunction]");
        /**
         * Bifunction: 2 parámetros de entrada, se tiene que especificar el tipo de
         * dato. Puede tener diferentes tipos de entradas como también diferente tipo de salida.
         */

        System.out.println("=>:Ejemplo 1 : Bifunction");
        BiFunction<Integer,Integer,Integer> multipli = (x, y) -> x * y;
        System.out.println(multipli.apply(4,5));

        System.out.println("=>:Ejemplo 2 : Formatear con espacios a la izquierda");
        BiFunction<String, Integer,String> laftPad = (text, number) -> String.format("%" + number + "s",text);
        System.out.println(laftPad.apply("java",10));

    }
}
