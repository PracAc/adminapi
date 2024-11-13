package org.oz.adminapi.area.repository.search;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AreaCodeSearchImpl implements AreaCodeSearch {

    private final EntityManager entityManager;

}