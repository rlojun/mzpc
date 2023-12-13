package com.fivemin.mzpc.service.member;

import com.fivemin.mzpc.data.dto.TimeDto;
import com.fivemin.mzpc.data.entity.Members;
import com.fivemin.mzpc.data.entity.Store;
import com.fivemin.mzpc.data.entity.MileageInfo;
import com.fivemin.mzpc.data.entity.Times;
import com.fivemin.mzpc.data.repository.MemberRepository;
import com.fivemin.mzpc.data.repository.StoreRepository;
import com.fivemin.mzpc.data.repository.MileageInfoRepository;
import com.fivemin.mzpc.data.repository.TimesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
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
    private final MileageInfoRepository mileageInfoRepository;

    @Autowired
    public MemberTimeService(TimesRepository timesRepository,
                             StoreRepository storeRepository,
                             MemberRepository memberRepository,
                             MileageInfoRepository mileageInfoRepository){
        this.timesRepository = timesRepository;
        this.storeRepository = storeRepository;
        this.memberRepository = memberRepository;
        this.mileageInfoRepository = mileageInfoRepository;
    }

    // 상품 리스트
    public List<TimeDto> listTime(String storeName) {
        Store store = storeRepository.findByName(storeName);
        List<Times> timesList = timesRepository.findByStore(store);

        if (timesList.isEmpty()) {
            return Collections.emptyList();
        }

        List<TimeDto> timeDtoList = timesList.stream()
                .filter(times -> !times.isCheckDelete())
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

        Members member = memberRepository.findById(memberId);

        processAdditionalTime(member, additionalTime);
        member.setMileage(member.getMileage() - usedMileage);

        int earnedMileage = (int) (timePrice * 0.02);
        member.setMileage(member.getMileage() + earnedMileage);

        memberRepository.save(member);

        Times times = timesRepository.findByCode(timeCode);

        MileageInfo mileageInfo = createMileageInfo(member, usedMileage, earnedMileage, times);
        mileageInfoRepository.save(mileageInfo);
    }

    private void processAdditionalTime(Members member, LocalTime additionalTime) {
        member.setLoginRemainingTime(member.getLoginRemainingTime().plusHours(additionalTime.getHour())
                .plusMinutes(additionalTime.getMinute())
                .plusSeconds(additionalTime.getSecond()));
    }

    private MileageInfo createMileageInfo(Members member, int usedMileage, int earnedMileage, Times times) {
        MileageInfo mileageInfo = new MileageInfo();
        mileageInfo.setMembers(member);
        mileageInfo.setUse(usedMileage);
        mileageInfo.setSave(earnedMileage);
        mileageInfo.setTimes(times);
        return mileageInfo;
    }

    public Members realRemainingTime(String memberId){
        Members members = memberRepository.findById(memberId);

        // 현재 시간 - 로그인 시간
        Duration betweenTime = Duration.between(members.getStartTime(), LocalDateTime.now().plusSeconds(1));
        LocalTime betweenLocalTime = LocalTime.ofSecondOfDay(betweenTime.getSeconds());

        // 실시간 남은 시간
        Duration durationRemainingTime = Duration.between(betweenLocalTime, members.getLoginRemainingTime());
        LocalTime remainingTime = LocalTime.ofSecondOfDay(durationRemainingTime.getSeconds());

        // remainingTime 최신화
        members.setRemainingTime(remainingTime);
        return memberRepository.save(members);
    }

    // 시간구매, 마일리지 사용 내역
    public List<MileageInfo> listMileageInfo(String memberId){
        Members members = memberRepository.findById(memberId);
        return mileageInfoRepository.findByMembers(members);
    }

    private TimeDto convertToDto(Times times) {
        return TimeDto.builder()
                .code(times.getCode())
                .name(times.getName())
                .price(times.getPrice())
                .addTime(times.getAddTime())
                .build();
    }
}
