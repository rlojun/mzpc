package com.fivemin.mzpc.service.admin;

import com.fivemin.mzpc.data.dto.TimeDto;
import com.fivemin.mzpc.data.entity.Store;
import com.fivemin.mzpc.data.entity.Times;
import com.fivemin.mzpc.data.repository.StoreRepository;
import com.fivemin.mzpc.data.repository.TimesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TimeService {


    private final TimesRepository timesRepository;
    private final StoreRepository storeRepository;

    @Autowired
    public TimeService(TimesRepository timesRepository, StoreRepository storeRepository){
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

    private TimeDto convertToDto(Times times) {
        return TimeDto.builder()
                .code(times.getCode())
                .name(times.getName())
                .price(times.getPrice())
                .addTime(times.getAddTimeAsDuration())
                .save(times.isSave())
                .build();
    }

    // 상품 디테일 (상품 수정 폼)
    public Times detailTime(String timeCode){
        return timesRepository.findByCode(timeCode);
    }

    // 상품 수정
    public TimeDto updateTime(String timeCode, TimeDto timeDto){
        Times updateTime = timesRepository.findByCode(timeCode);

        updateTime.setName(timeDto.getName());
        updateTime.setAddTime(timeDto.getAddTimeAsLong());
        updateTime.setPrice(timeDto.getPrice());
        updateTime.setSave(timeDto.isSave());

        Times updatedTime = timesRepository.save(updateTime);
        return convertToDto(updatedTime);
    }
}
