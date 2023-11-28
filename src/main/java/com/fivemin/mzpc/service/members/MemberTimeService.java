package com.fivemin.mzpc.service.members;

import com.fivemin.mzpc.data.dto.TimeDto;
import com.fivemin.mzpc.data.entity.Members;
import com.fivemin.mzpc.data.entity.Store;
import com.fivemin.mzpc.data.entity.TimePurchase;
import com.fivemin.mzpc.data.entity.Times;
import com.fivemin.mzpc.data.repository.MemberRepository;
import com.fivemin.mzpc.data.repository.StoreRepository;
import com.fivemin.mzpc.data.repository.TimePurchaseRepository;
import com.fivemin.mzpc.data.repository.TimesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MemberTimeService {

    private final TimesRepository timesRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final TimePurchaseRepository timePurchaseRepository;

    @Autowired
    public MemberTimeService(TimesRepository timesRepository,
                             StoreRepository storeRepository,
                             MemberRepository memberRepository,
                             TimePurchaseRepository timePurchaseRepository){
        this.timesRepository = timesRepository;
        this.storeRepository = storeRepository;
        this.memberRepository = memberRepository;
        this.timePurchaseRepository = timePurchaseRepository;
    }

    // 상품 리스트
    public List<TimeDto> listTime(String storeName) {
        Store store = storeRepository.findByName(storeName);
        List<Times> timesList = timesRepository.findByStore(store);

        if (timesList.isEmpty()) {
            return Collections.emptyList();
        }

        List<TimeDto> timeDtoList = timesList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return timeDtoList;
    }

    // 상품 상세보기
    public Times detailTime(String timeCode){
        return timesRepository.findByCode(timeCode);
    }

    // 상품 구매                                                  이거 상품의 본 가격,  추가되는 시간
    public void purchaseTime(String memberId, int usedMileage, int timePrice, LocalTime additionalTime, String timeCode) {
        log.info("memberId : {} : ", memberId);
        // 사용자 찾기
        Members member = memberRepository.findById(memberId);

        // 추가 시간 추가
        member.setRemainingTime(member.getRemainingTime().plusHours(additionalTime.getHour())
                .plusMinutes(additionalTime.getMinute())
                .plusSeconds(additionalTime.getSecond()));

        // 사용한 마일리지 차감
        member.setMileage(member.getMileage() - usedMileage);

        // 상품 가격의 2%를 마일리지로 적립
        int earnedMileage = (int) (timePrice * 0.02);
        member.setMileage(member.getMileage() + earnedMileage);

        // 엔티티 저장
        memberRepository.save(member);

        // 시간 정보
        Times times = timesRepository.findByCode(timeCode);

        // 구매 기록 저장
        TimePurchase timePurchase = new TimePurchase();
        timePurchase.setMembers(member);
        timePurchase.setUse(usedMileage);
        timePurchase.setTimes(times);
        timePurchaseRepository.save(timePurchase);
    }

    // 시간구매, 마일리지 사용 내역
    public List<TimePurchase> listTimePurchase(String memberId){
        Members members = memberRepository.findById(memberId);
        return timePurchaseRepository.findByMembers(members);
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
}
