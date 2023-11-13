package com.fivemin.mzpc.service;

import com.fivemin.mzpc.data.entity.Admin;
import com.fivemin.mzpc.data.entity.Members;
import com.fivemin.mzpc.data.repository.AdminRepository;
import com.fivemin.mzpc.data.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private MemberRepository memberRepository;

    public Admin findByAdminId(String id){
        return adminRepository.findById(id);
    }

    public Members findByMemberId(String id){
        return memberRepository.findById(id);
    }
}
