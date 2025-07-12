package idv.nj.controller;

import idv.nj.dto.Response;
import idv.nj.dto.request.ProductQuery;
import idv.nj.dto.response.ProductLite;
import idv.nj.entity.Category;
import idv.nj.repo.CategoryRepo;
import idv.nj.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @GetMapping()
    public Response<List<ProductLite>> query(ProductQuery req) {
        Page<ProductLite> res = productRepo.findByCategoryId(req.getCategory(), req.toPageable());
        return Response.ok(res.getContent(), res.getTotalElements());
    }

    @GetMapping("/category")
    public Response<List<Category>> listByCategory() {
        List<Category> res = categoryRepo.findAll();
        return Response.ok(res);
    }
}
