package com.accenture.flowershop.back.controller;

import com.accenture.flowershop.back.model.FlowerModel;
import org.springframework.stereotype.Controller;
        import org.springframework.ui.ModelMap;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "catalog")
public class CatalogController {
    @RequestMapping(method = RequestMethod.GET)
    public String catalog(ModelMap modelMap) {
        FlowerModel productModel = new FlowerModel();
        modelMap.put("products", productModel.findAll());
        return "catalog";
    }
}