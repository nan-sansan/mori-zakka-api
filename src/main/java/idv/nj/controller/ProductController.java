package idv.nj.controller;

import idv.nj.dto.Response;
import idv.nj.entity.Category;
import idv.nj.entity.Product;
import idv.nj.repo.CategoryRepo;
import idv.nj.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Product")
public class ProductController {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @GetMapping("/all")
    public Response<List<Product>> list() {
        List<Product> res = productRepo.findAll();
        return Response.ok(res);
    }

    @GetMapping("/category")
    public Response<List<Category>> listByCategory() {
        List<Category> res = categoryRepo.findAll();
        return Response.ok(res);
    }

}
