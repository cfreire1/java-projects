package domain.controller;

import domain.entity.Product;
import domain.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {

    public static final String VAR_PRODUCTOS = "productos";
    public static final String VAR_MODEL = "model";
    public static final String PAG_LIST_PRODUCT = "listProduct";
    public static final String PAG_EDIT_PRODUCT = "editProduct";

    @Autowired
    private ProductServices productServices;

    @GetMapping("/")
    public String initHome(Model model) {
        return "home";
    }

    /**
     * Listar productos
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String listProduct(Model model) {
        Map<String, Object> myModel = new HashMap<>();
        myModel.put(VAR_PRODUCTOS, this.productServices.allProduct());//BD
        model.addAttribute(VAR_MODEL,myModel);
        return PAG_LIST_PRODUCT;
    }

    /**
     * Busqueda de 1 producto
     * @param model
     * @param searchid
     * @return
     */
    @GetMapping("/search")
    public String searchProduct(Model model,
                              @RequestParam( value = "searchid") String searchid) {
        Map<String, Object> myModel = new HashMap<>();
        if (searchid.equalsIgnoreCase("")){
            myModel.put(VAR_PRODUCTOS, this.productServices.allProduct());//BD
        }else {
            myModel.put(VAR_PRODUCTOS, this.productServices.findProduct(Integer.parseInt(searchid)));//BD
        }
        model.addAttribute(VAR_MODEL,myModel);
        return PAG_LIST_PRODUCT;
    }

    /**
     * Agregar producto
     * @param product
     * @return
     */
    @GetMapping("/go-add-product")
    public String goAddProduct(@ModelAttribute("producto") Product product) {
        return "addProduct";
    }
    @PostMapping("/add-product")
    public String addProduct(@ModelAttribute("producto") Product product) {
        this.productServices.addProduct(product);
        return "redirect:/product/list";
    }

    /**
     * Eliminar producto
     * @param model
     * @param deleteid
     * @return
     */
    @GetMapping("/delete-product")
    public String deleteProduct(Model model,
                                @RequestParam( value = "deleteid") String deleteid) {
        this.productServices.deleteProduct(Integer.parseInt(deleteid));//BD

        Map<String, Object> myModel = new HashMap<>();
        myModel.put(VAR_PRODUCTOS, this.productServices.allProduct());//BD
        model.addAttribute(VAR_MODEL,myModel);

        return PAG_LIST_PRODUCT;
    }

    /**
     * - buscar y pasar datos a otra pagina
     * - Redirecciona a view editarogin?
     * @param model
     * @param product
     * @param editid
     * @return
     */
    @GetMapping("/go-edit-product")
    public String goEditProduct(Model model,
                                @ModelAttribute("producto") Product product,
                                @RequestParam( value = "editid") String editid) {

        Map<String, Object> myModel = new HashMap<>();
        myModel.put(VAR_PRODUCTOS, this.productServices.findProduct(Integer.parseInt(editid)));//BD
        model.addAttribute(VAR_MODEL,myModel);

        return PAG_EDIT_PRODUCT;
    }

    /**
     * Editar producto selecionado
     * @param product
     * @return
     */
    @PostMapping("/edit-product")
    public String editProduct(@ModelAttribute("producto") Product product) {
        this.productServices.editProduct(product);//BD
        return "redirect:/product/list";
    }

}
