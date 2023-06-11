package com.aurosoft.ecommerce.serviceimpl;

import com.aurosoft.ecommerce.entity.CompanyDetail;
import com.aurosoft.ecommerce.repository.CompanyDetailRepository;
import com.aurosoft.ecommerce.service.CompanyDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyDetailServiceImpl implements CompanyDetailService {
    @Autowired
    CompanyDetailRepository repository;

    @Override
    public List<CompanyDetail> listAllCompanyDetail() {
        return repository.findAll();
    }

    @Override
    public CompanyDetail getCompanyDetailById(int id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public CompanyDetail insertCompanyDetail(CompanyDetail companyDetail) {
        return repository.save(companyDetail);
    }

    @Override
    public CompanyDetail updateCompanyDetail(CompanyDetail companyDetail) {
        return repository.save(companyDetail);
    }

    @Override
    public int deleteCompanyDetail(int id) {
        repository.deleteById(id);
        return id;
    }
}
