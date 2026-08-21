package com.solona.service.impl;

import com.solona.response.ProductResponse;
import com.solona.exception.ProductException;
import com.solona.mapper.ProductMapper;
import com.solona.modal.Brand;
import com.solona.modal.Category;
import com.solona.modal.Product;
import com.solona.modal.Seller;
import com.solona.repository.BrandRepository;
import com.solona.repository.CategoryRepository;
import com.solona.repository.ProductRepository;
import com.solona.request.CreateProductRequest;
import com.solona.service.ProductService;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.data.jpa.domain.Specification;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final BrandRepository brandRepository;

    private final ProductMapper productMapper;


    @Override
    public ProductResponse createProduct(
            CreateProductRequest req,
            Seller seller
    ) {

        // -------------------------
        // CATEGORY
        // -------------------------

        Category category = categoryRepository
                .findById(req.getCategoryId())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Category not found with id "
                                        + req.getCategoryId()
                        )
                );


        // -------------------------
        // BRAND
        // -------------------------

        Brand brand = null;

        if (req.getBrandId() != null) {

            brand = brandRepository
                    .findById(req.getBrandId())
                    .orElseThrow(() ->
                            new IllegalArgumentException(
                                    "Brand not found with id "
                                            + req.getBrandId()
                            )
                    );
        }


        // -------------------------
        // DISCOUNT
        // -------------------------

        int discountPercentage =
                calculateDiscountPercentage(
                        req.getMrpPrice(),
                        req.getSellingPrice()
                );


        // -------------------------
        // PRODUCT
        // -------------------------

        Product product = new Product();

        product.setSeller(seller);

        product.setCategory(category);

        product.setBrand(brand);

        product.setTitle(req.getTitle());

        product.setDescription(req.getDescription());

        product.setMrpPrice(req.getMrpPrice());

        product.setSellingPrice(req.getSellingPrice());

        product.setQuantity(req.getQuantity());

        product.setColor(req.getColor());

        product.setImages(req.getImages());

        product.setSizes(req.getSizes());

        product.setDiscountPercent(discountPercentage);

        product.setCreatedAt(LocalDateTime.now());


        Product savedProduct =
                productRepository.save(product);


        return productMapper.toResponse(savedProduct);
    }


    private int calculateDiscountPercentage(
            int mrpPrice,
            int sellingPrice
    ) {

        if (mrpPrice <= 0) {

            throw new IllegalArgumentException(
                    "MRP price must be greater than 0"
            );
        }

        if (sellingPrice < 0) {

            throw new IllegalArgumentException(
                    "Selling price cannot be negative"
            );
        }

        if (sellingPrice > mrpPrice) {

            throw new IllegalArgumentException(
                    "Selling price cannot be greater than MRP price"
            );
        }

        double discount =
                ((double) (mrpPrice - sellingPrice)
                        / mrpPrice) * 100;

        return (int) discount;
    }


    @Override
    public void deleteProduct(Long productId)
            throws ProductException {

        Product product =
                findProductEntityById(productId);

        productRepository.delete(product);
    }


    @Override
    public ProductResponse updateProduct(
            Long productId,
            CreateProductRequest req
    ) throws ProductException {

        Product product =
                findProductEntityById(productId);


        Category category =
                categoryRepository
                        .findById(req.getCategoryId())
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Category not found with id "
                                                + req.getCategoryId()
                                )
                        );


        Brand brand = null;

        if (req.getBrandId() != null) {

            brand = brandRepository
                    .findById(req.getBrandId())
                    .orElseThrow(() ->
                            new IllegalArgumentException(
                                    "Brand not found with id "
                                            + req.getBrandId()
                            )
                    );
        }


        product.setTitle(req.getTitle());

        product.setDescription(req.getDescription());

        product.setMrpPrice(req.getMrpPrice());

        product.setSellingPrice(req.getSellingPrice());

        product.setQuantity(req.getQuantity());

        product.setColor(req.getColor());

        product.setImages(req.getImages());

        product.setSizes(req.getSizes());

        product.setCategory(category);

        product.setBrand(brand);

        product.setDiscountPercent(
                calculateDiscountPercentage(
                        req.getMrpPrice(),
                        req.getSellingPrice()
                )
        );


        Product updatedProduct =
                productRepository.save(product);


        return productMapper.toResponse(updatedProduct);
    }


    @Override
    public ProductResponse findProductById(
            Long productId
    ) throws ProductException {

        Product product =
                findProductEntityById(productId);

        return productMapper.toResponse(product);
    }


    public Product findProductEntityById(
            Long productId
    ) throws ProductException {

        return productRepository
                .findById(productId)
                .orElseThrow(() ->
                        new ProductException(
                                "Product not found with id "
                                        + productId
                        )
                );
    }


    @Override
    public List<ProductResponse> searchProducts(
            String query
    ) {

        return productRepository
                .searchProduct(query)
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }


    @Override
    public Page<ProductResponse> getAllProducts(
            String category,
            String brand,
            String color,
            String sizes,
            Integer minPrice,
            Integer maxPrice,
            Integer minDiscount,
            String sort,
            String stock,
            Integer pageNumber
    ) {

        Specification<Product> spec =
                (root, query, criteriaBuilder) -> {

                    List<Predicate> predicates =
                            new ArrayList<>();


                    // CATEGORY

                    if (category != null &&
                            !category.isEmpty()) {

                        Join<Product, Category> categoryJoin =
                                root.join("category");

                        predicates.add(
                                criteriaBuilder.equal(
                                        categoryJoin.get("id"),
                                        Long.valueOf(category)
                                )
                        );
                    }


                    // BRAND

                    if (brand != null &&
                            !brand.isEmpty()) {

                        Join<Product, Brand> brandJoin =
                                root.join("brand");

                        predicates.add(
                                criteriaBuilder.equal(
                                        brandJoin.get("id"),
                                        Long.valueOf(brand)
                                )
                        );
                    }


                    // COLOR

                    if (color != null &&
                            !color.isEmpty()) {

                        predicates.add(
                                criteriaBuilder.equal(
                                        root.get("color"),
                                        color
                                )
                        );
                    }


                    // SIZE

                    if (sizes != null &&
                            !sizes.isEmpty()) {

                        predicates.add(
                                criteriaBuilder.equal(
                                        root.get("sizes"),
                                        sizes
                                )
                        );
                    }


                    // MIN PRICE

                    if (minPrice != null) {

                        predicates.add(
                                criteriaBuilder.greaterThanOrEqualTo(
                                        root.get("sellingPrice"),
                                        minPrice
                                )
                        );
                    }


                    // MAX PRICE

                    if (maxPrice != null) {

                        predicates.add(
                                criteriaBuilder.lessThanOrEqualTo(
                                        root.get("sellingPrice"),
                                        maxPrice
                                )
                        );
                    }


                    // MIN DISCOUNT

                    if (minDiscount != null) {

                        predicates.add(
                                criteriaBuilder.greaterThanOrEqualTo(
                                        root.get("discountPercent"),
                                        minDiscount
                                )
                        );
                    }


                    return criteriaBuilder.and(
                            predicates.toArray(
                                    new Predicate[0]
                            )
                    );
                };


        int page =
                pageNumber != null
                        ? pageNumber
                        : 0;


        Pageable pageable;


        if (sort != null &&
                !sort.isEmpty()) {

            switch (sort) {

                case "price_low":

                    pageable = PageRequest.of(
                            page,
                            10,
                            Sort.by(
                                    "sellingPrice"
                            ).ascending()
                    );

                    break;


                case "price_high":

                    pageable = PageRequest.of(
                            page,
                            10,
                            Sort.by(
                                    "sellingPrice"
                            ).descending()
                    );

                    break;


                default:

                    pageable = PageRequest.of(
                            page,
                            10
                    );
            }

        } else {

            pageable = PageRequest.of(
                    page,
                    10
            );
        }


        return productRepository
                .findAll(spec, pageable)
                .map(productMapper::toResponse);
    }


    @Override
    public List<ProductResponse> getProductBySellerId(
            Long sellerId
    ) {

        return productRepository
                .findBySellerId(sellerId)
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }
}