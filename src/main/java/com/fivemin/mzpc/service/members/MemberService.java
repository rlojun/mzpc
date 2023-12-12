package com.fivemin.mzpc.service.members;

import com.fivemin.mzpc.data.entity.Members;
import com.fivemin.mzpc.data.entity.history.MemberLoginHistory;
import com.fivemin.mzpc.data.repository.MemberRepository;
import com.fivemin.mzpc.data.repository.history.MemberLoginHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberLoginHistoryRepository memberLoginHistoryRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository,
                         MemberLoginHistoryRepository memberLoginHistoryRepository){
        this.memberRepository= memberRepository;
        this.memberLoginHistoryRepository = memberLoginHistoryRepository;
    }

    public void loginMember(String memberId){
        Members members = memberRepository.findById(memberId);
        members.setStartTime(LocalDateTime.now());
        members.setRemainingTime(members.getLoginRemainingTime());
        memberRepository.save(members);

        MemberLoginHistory memberLoginHistory = new MemberLoginHistory();
        memberLoginHistory.setMembers(members);
        memberLoginHistory.setStatus(true);
        memberLoginHistoryRepository.save(memberLoginHistory);
    }

    public void logoutMember(String memberId){
        Members members = memberRepository.findById(memberId);

        // remainingTime >> LoginRemainingTime 에 저장
        members.setLoginRemainingTime(members.getRemainingTime());

        MemberLoginHistory memberLoginHistory = new MemberLoginHistory();
        memberLoginHistory.setMembers(members);
        memberLoginHistory.setStatus(false);
        memberLoginHistoryRepository.save(memberLoginHistory);
    }

    

    public List<Members> getAllMembers() {
        return memberRepository.findAll();
    }

    public Members getMemberById(String memberId) {
        return memberRepository.findById(memberId); //.orElse(null);
    }


}
