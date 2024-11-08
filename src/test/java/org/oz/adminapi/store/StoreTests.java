package org.oz.adminapi.store;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.oz.adminapi.area.repository.AreaCodeRepository;
import org.oz.adminapi.localmaneger.repository.LocalManagerRepository;
import org.oz.adminapi.product.domain.CategoryEntity;
import org.oz.adminapi.product.domain.ProductCategoryEntity;
import org.oz.adminapi.product.domain.ProductEntity;
import org.oz.adminapi.store.domain.Store;
import org.oz.adminapi.store.repository.StoreRepository;
import org.oz.adminapi.store.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Log4j2
public class StoreTests {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private AreaCodeRepository areaCodeRepository;
    @Autowired
    private LocalManagerRepository localManagerRepository;

    @Test
    @Transactional
    @Commit
    public void storeDummies(){

        for (int i = 1; i <= 10; i++) {

            Store store = Store.builder()
                    .areaCode(areaCodeRepository.getReferenceById("Area1"))
                    .localManager(localManagerRepository.getReferenceById(1L))
                    .storeName("Store"+i)
                    .storeContact("Contact"+i)
                    .storeLatitude("Latitude"+i)
                    .storeLongitude("Longitude"+i)
                    .isRentAvailable(true)
                    .issale(true)
                    .build();

            storeRepository.save(store);
        }



    }

}
