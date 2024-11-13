package org.oz.adminapi.localmaneger.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.oz.adminapi.common.dto.PageRequestDTO;
import org.oz.adminapi.common.dto.PageResponseDTO;
import org.oz.adminapi.localmaneger.dto.LocalManagerDTO;
import org.oz.adminapi.localmaneger.repository.LocalManagerRepository;
import org.springframework.stereotype.Service;


@Service
@Log4j2
@RequiredArgsConstructor
public class LocalManagerService {

    private final LocalManagerRepository localManagerRepository;

    public PageResponseDTO<LocalManagerDTO> getList(PageRequestDTO pageRequestDTO){
        return localManagerRepository.list(pageRequestDTO);
    }
}
