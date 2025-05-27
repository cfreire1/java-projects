package examples.chaining;


import examples.chaining.pojo.ChainingExample;

import java.util.List;

/**
 * El Chaining o encadenamiento consiste en hacer llamadas consecutivas
 * a diferentes metodos de un mismo objeto.
 *
 * Un ejemplo claro es al utilizar la clase StringBuilder, esta permite
 * encadenar llamadas al objeto creado inicialmente para manipular la creación de un String.
 */
public class ChainingRun {
    public static void main(String[] args) {
        ChainingExample chainingExample = new ChainingExample();

        System.out.println("=>:retorno alo que se agrego al objeto");
        System.out.println(chainingExample
                        .addText("hola-mundo")
                        .addSpace()
                        .addText("hola-mundo-2")
                        .addSpace()
                        .addText("texto finalizado")
                        .toUpperCase()
                        .toString());

        System.out.println("=>:Retorna array");
        List list = chainingExample
                    .addText("hola-mundo")
                    .addSpace()
                    .addText("hola-mundo-2")
                    .addSpace()
                    .addText("texto finalizado")
                    .toUpperCase()
                    .toArray();
        System.out.println(list);


    }
}
