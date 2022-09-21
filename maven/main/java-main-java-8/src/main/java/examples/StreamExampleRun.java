package examples;


import data.ProductDataRepository;
import data.ProductDataRepositoryImpl;
import data.pojo.Product;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class StreamExampleRun {
    public static void main(String[] args) throws IOException {
        //instances
        ProductDataRepository dataDemoRepository = new ProductDataRepositoryImpl();

        //=============================================================================================================
        //data
        List<Product> productList = dataDemoRepository.getAllProducts();
        System.out.println("=>Lista Original");
        productList.forEach(System.out::println);//productList.forEach(product -> System.out.println(product));

        //=============================================================================================================
        //Examples
        System.out.println("[Filter]");
        System.out.println("=>Filtrar lista por solo productos inferiores a 500 pesos");
        List<Product> list1 = productList.stream()
                .filter(product -> product.getPrice()<500)
                .collect(Collectors.toList());//recopilamos los resultados en una lista

        list1.forEach(System.out::println);

        System.out.println("=>Filtrar lista por solo futas");
        List<Product> list2  = productList.stream()
                .filter(product -> product.getType().equalsIgnoreCase("Fruta"))
                .collect(Collectors.toList());

        list2.forEach(System.out::println);

        //=============================================================================================================
        System.out.println("[Sort]");//clasificar, ordenar
        System.out.println("=>Ordenar lista de registros por precio (menor a mayor)(ASC)");
        List<Product> list3 = productList.stream()
                .sorted(Comparator.comparing(Product::getPrice))
                .collect(Collectors.toList());

        list3.forEach(System.out::println);

        System.out.println("=>Ordenar lista de registros por nombre alfabeticamente (DESC)");
        List<Product> list4 = productList.stream()
                .sorted(Comparator.comparing(Product::getName))
                .collect(Collectors.toList());

        list4.forEach(System.out::println);

        System.out.println("=>1:Ordenar lista de registros por precio (menor a mayor)(ASC)");
        System.out.println("=>2:Ordenar lista de registros por nombre alfabeticamente (DESC)");
        List<Product> list5 = productList.stream()
                .sorted(Comparator.comparing(Product::getPrice).thenComparing(Product::getName))
                .collect(Collectors.toList());

        list5.forEach(System.out::println);

        //=============================================================================================================
        System.out.println("[All Match]");//Todos los match
        //devuelve si todos los elementos de esta secuencia coinciden con el predicado proporcionado
        System.out.println("=>Todos los productos tienen precio superior a 100 en la lista");
        boolean allMatch = productList.stream()
                .allMatch(product -> product.getPrice() >= 100);

        System.out.println("Existe alguno?="+allMatch);

        //=============================================================================================================
        System.out.println("[Any Match]");//cualquer match
        //devuelve si algún elemento de esta secuencia coincide con el predicado proporcionado.
        System.out.println("=>Existe algun producto con 0 de stock en la lista");
        boolean anyMatch = productList.stream()
                .anyMatch(product -> product.getStock() == 0);

        System.out.println("Existe alguno?="+anyMatch);

        //=============================================================================================================
        System.out.println("[None Match]");//Ningun match
        //devuelve si ningún elemento de esta secuencia coincide con el predicado proporcionado.
        System.out.println("=>Valida si no existe este producto en la lista");
        boolean noneMatch = productList.stream()
                .noneMatch(product -> product.getName().equalsIgnoreCase("Neumatico"));
        System.out.println("Existe?="+noneMatch);

        //=============================================================================================================
        System.out.println("[Max]");// devuelve el valor maximo de una lista

        //devuelve un opcional porque es posible que no encuentre el valor máximo
        System.out.println("=>:Devuelve el Producto que tiene el precio mas alto");
        Optional<Product> list6 = productList.stream()
                .max(Comparator.comparing(Product::getPrice));

        list6.ifPresent(System.out::println);

        //=============================================================================================================
        System.out.println("[Min]");// devuelve el valor minimo de una lista

        //devuelve un opcional porque es posible que no encuentre el valor máximo
        System.out.println("=>:Devuelve el Producto que tiene el precio mas bajo");
        Optional<Product> list7 = productList.stream()
                .min(Comparator.comparing(Product::getPrice));

        list7.ifPresent(System.out::println);

        //=============================================================================================================
        System.out.println("[Group]");//Creacion de map

        System.out.println("=>:Crear map por tipos de productos");
        Map<String,List<Product>> stringListMap = productList.stream()
                .collect(Collectors.groupingBy(Product::getType));//Se posiciona en primer valor String, y se filtra por tipo

        stringListMap.forEach((s, products) -> {
            System.out.println("tipo="+s);
            products.forEach(System.out::println);
        });

        //=============================================================================================================
        System.out.println("[Map]");

        System.out.println("=>:Obtener solo el registro platano");
        Optional<List<Product>> list8 = Optional.of(productList)
                .map(products2 -> products2.stream().filter(product -> product.getName().equalsIgnoreCase("Platano")))
                .map(productStream -> productStream.collect(Collectors.toList()));

        System.out.println(list8);

        System.out.println("=>:Obtener solo el registro platano para el Obj 'Product'");
        Optional<Product> objProduct = Optional.of(productList)
                .map(products2 -> products2.stream().filter(product -> product.getName().equalsIgnoreCase("Platano")))
                .map(productStream -> productStream.collect(Collectors.toList()))
                .map(products -> products.get(0));

        System.out.println(objProduct);

        System.out.println("=>:Obtener la cantidad de registros");
        Integer cant = Optional.of(productList)
                .map(List::size)
                .orElse(0);
        System.out.println(cant);

    }
}
