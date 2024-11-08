package org.oz.adminapi.product;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.oz.adminapi.maker.domain.MakerEntity;
import org.oz.adminapi.maker.repository.MakerRepository;
import org.oz.adminapi.product.domain.CategoryEntity;
import org.oz.adminapi.product.domain.ProductCategoryEntity;
import org.oz.adminapi.product.domain.ProductEntity;
import org.oz.adminapi.product.dto.ProductReadDTO;
import org.oz.adminapi.product.repository.CategoryRepository;
import org.oz.adminapi.product.repository.ProductCategoryRepository;
import org.oz.adminapi.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootTest
@Log4j2
public class ProductTests {

    @Autowired
    private MakerRepository makerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    @Transactional
    @Commit
    // 제작자 더미데이터
    public void dummiesMaker() {
        for (int i = 1; i < 150; i++) {
            MakerEntity maker = MakerEntity.builder()
                    .makerBizNo(i + "123-45-67890")
                    .makerName("choi" + i)
                    .makerEmail("jiji" + i + "@gmail.com")
                    .makerPhone("010-2"+i +"345-6789")
                    .makerPostnum("45"+i+"67")
                    .makerAddr("addr" + i)
                    .makerAddrDetail("addr detail" + i)
                    .build();
            makerRepository.save(maker);
        }
    }

    @Test
    @Transactional
    @Commit
    // 상품 더미데이터
    public void dummiesProduct() {
        for (int i = 1; i <= 150; i++) {
            String makerBizNo = i + "123-45-67890";

            Optional<MakerEntity> maker = makerRepository.findById(makerBizNo);
            if (maker.isPresent()) {
                ProductEntity product = ProductEntity.builder()
                        .productNo((long) i)
                        .productName("Product " + i)
                        .productDescription("Description for Product " + i)
                        .maker(maker.get())
                        .build();
                productRepository.save(product);
            }
        }
    }
    @Test
    @Transactional
    @Commit
    // 상품 파일 더미데이터
    public void dummiesProductFiles() {
        String makerBizNo = "123-45-67890";
        Optional<MakerEntity> maker = makerRepository.findById(makerBizNo);

        List<String> attachFiles = new ArrayList<>();
        attachFiles.add("aaa.jpg");
        attachFiles.add("vvv.jpg");
        attachFiles.add("ccc.jpg");

        if (maker.isPresent()) {
            for (int i = 1; i <= 5; i++) {

                ProductEntity product = ProductEntity.builder()
                        .productNo((long) i)
                        .productName("Product " + i)
                        .productDescription("Description for Product " + i)
                        .maker(maker.get())
                        .build();
                product.updateAttachFiles(attachFiles);
                productRepository.save(product);
            }
        }
    }

    @Test
    @Transactional
    @Commit
    // 카테고리생성
    public void dummiesCategory() {
        for (int i = 1; i <= 20; i++) {
            CategoryEntity category = CategoryEntity.builder()
                    .categoryName("Category " + i)
                    .build();
            categoryRepository.save(category);
        }
    }

    @Test
    @Transactional
    @Commit
    // 상품 한 개 당 카테고리 여러 개
    public void dummiesProductCategory() {
        for (int i = 1; i <= 10; i++) {
            Optional<ProductEntity> product = productRepository.findById((long) i);
            Optional<CategoryEntity> category = categoryRepository.findById(3L);

            if (product.isPresent() && category.isPresent()) {
                ProductCategoryEntity productCategory = ProductCategoryEntity.builder()
                        .product(product.get())
                        .category(category.get())
                        .build();
                productCategoryRepository.save(productCategory);
                log.info("Saved ProductCategory: " + productCategory);
            }
        }
    }



    @Test
    @Transactional
    @Commit
    public void dummiesProductCategory2() {
        for (int i = 1; i <= 150; i++) {
            Optional<ProductEntity> product = productRepository.findById((long) i);

            // 각 상품마다 1~3개의 카테고리를 랜덤하게 연결
            int numberOfCategories = (int) (Math.random() * 3) + 1;

            if (product.isPresent()) {
                for (int j = 0; j < numberOfCategories; j++) {
                    long categoryId = (i + j) % 20 + 1;  // 카테고리 ID를 1에서 20 사이에서 순환
                    Optional<CategoryEntity> category = categoryRepository.findById(categoryId);

                    if (category.isPresent()) {
                        ProductCategoryEntity productCategory = ProductCategoryEntity.builder()
                                .product(product.get())
                                .category(category.get())
                                .build();
                        productCategoryRepository.save(productCategory);
                        log.info("Saved ProductCategory: " + productCategory);
                    }
                }
            }
        }
    }


    @Test
    public void testRead() {
        Long productNo = 3L;
        List<Object[]> result = productRepository.findWithFilesByProductNo(productNo);

        ProductEntity product = (ProductEntity) result.get(0)[0];

        List<CategoryEntity> categoryEntityList = result.stream().map(arr -> (CategoryEntity)arr[1]).collect(Collectors.toList());

        log.info("=====================================");
        log.info(product);
        log.info(categoryEntityList);
    }

    @Test
    @Transactional
    @Commit
    public void readProduct() {
//        Long productNo = 2L;
//        Optional<ProductEntity> result = productRepository.findWithFilesByProductNo(productNo);
//        ProductEntity entity = result.get();
//
//        List<String> attachFileNames = entity.getAttachFiles().stream()
//                .map(file -> file.getFileName())
//                .toList();
//
//        ProductReadDTO productReadDTO = ProductReadDTO.builder()
//                .productNo(productNo)
//                .productName(entity.getProductName())
//                .productDescription(entity.getProductDescription())
//                .productStatus(entity.getProductStatus())
//                .attachFileNames(attachFileNames)
//                .makerName(entity.getMaker().getMakerName())
//                .createDate(entity.getCreateDate())
//                .lastModifiedDate(entity.getLastModifiedDate())
//                .build();
//
//        log.info(productReadDTO.toString());
    }
}
