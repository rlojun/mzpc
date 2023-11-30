package com.fivemin.mzpc.service.admin;

import com.fivemin.mzpc.data.dto.TimeDto;
import com.fivemin.mzpc.data.entity.Store;
import com.fivemin.mzpc.data.entity.Times;
import com.fivemin.mzpc.data.repository.StoreRepository;
import com.fivemin.mzpc.data.repository.TimesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AdminTimeService {


    private final TimesRepository timesRepository;
    private final StoreRepository storeRepository;

    @Autowired
    public AdminTimeService(TimesRepository timesRepository, StoreRepository storeRepository){
        this.timesRepository = timesRepository;
        this.storeRepository = storeRepository;
    }

    // 상품 리스트
    public List<TimeDto> listTime(String storeCode) {
        Store store = storeRepository.findByCode(storeCode);
        List<Times> timesList = timesRepository.findByStore(store);

        if (timesList.isEmpty()) {
            return Collections.emptyList();
        }

        List<TimeDto> timeDtoList = timesList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return timeDtoList;
    }

    // 상품 신규 생성
    public TimeDto createTime(TimeDto timeDto, String storeCode){
        Times newTime = new Times();

        Store store = storeRepository.findByCode(storeCode);
        newTime.setStore(store);
        newTime.setName(timeDto.getName());
        newTime.setAddTime(timeDto.getAddTime());
        newTime.setPrice(timeDto.getPrice());
        newTime.setSave(timeDto.isSave());

        Times saveTime = timesRepository.save(newTime);
        return convertToDto(saveTime);
    }

    // 상품 디테일 (상품 수정 폼)
    public Times detailTime(String timeCode){
        return timesRepository.findByCode(timeCode);
    }

    // 상품 수정
    public TimeDto updateTime(String timeCode, TimeDto timeDto){
        Times updateTime = timesRepository.findByCode(timeCode);

        updateTime.setName(timeDto.getName());
        updateTime.setAddTime(timeDto.getAddTime());
        updateTime.setPrice(timeDto.getPrice());
        updateTime.setSave(timeDto.isSave());

        Times updatedTime = timesRepository.save(updateTime);
        return convertToDto(updatedTime);
    }

    private TimeDto convertToDto(Times times) {
        return TimeDto.builder()
                .code(times.getCode())
                .name(times.getName())
                .price(times.getPrice())
                .addTime(times.getAddTime())
                .save(times.isSave())
                .build();
    }

    @Transactional
    public void deleteTime(String timeCode){
        timesRepository.deleteByCode(timeCode);
    }
}
