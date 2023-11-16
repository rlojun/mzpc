package com.fivemin.mzpc.service;

import com.fivemin.mzpc.data.dto.AuthDTO;
import com.fivemin.mzpc.data.entity.Admin;
import com.fivemin.mzpc.data.entity.Members;
import com.fivemin.mzpc.data.entity.Store;
import com.fivemin.mzpc.data.repository.AdminRepository;
import com.fivemin.mzpc.data.repository.MemberRepository;
import com.fivemin.mzpc.data.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private StoreRepository storeRepository;

    public Admin findByAdminId(String id){
        return adminRepository.findById(id);
    }

    public Members findByMemberId(String id){
        return memberRepository.findById(id);
    }

    // 회원가입
    public void auth(AuthDTO authDTO){
        Members members = new Members();
        members.setId(authDTO.getId());
        members.setPw(authDTO.getPw());
        members.setName(authDTO.getName());
        members.setSsn(authDTO.getSsn());
        members.setNumber(authDTO.getNumber());
        members.setEmail(authDTO.getEmail());
        members.setAddress(authDTO.getAddress());

        // 가게 이름 확인
        String storeName = authDTO.getStoreName();
        Store store = storeRepository.findByName(storeName);

        // 가게가 존재하지 않으면 예외 발생
        if(store == null){
            throw new IllegalArgumentException("가게 이름이 유효하지 않습니다.");
        }
        // 가게가 존재하면 회원 엔티티에 추가
        members.setStore(store);

        memberRepository.save(members);
    }

    // 아이디 찾기
    public Members findId(String name, String ssn){
        return memberRepository.findByNameAndSsn(name, ssn);
    }
}
