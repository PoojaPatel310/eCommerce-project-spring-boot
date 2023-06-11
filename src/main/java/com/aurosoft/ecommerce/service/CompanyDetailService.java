package com.aurosoft.ecommerce.service;

import com.aurosoft.ecommerce.entity.CompanyDetail;
import com.aurosoft.ecommerce.entity.CompanyDetail;

import java.util.List;

public interface CompanyDetailService {
    
    List<CompanyDetail> listAllCompanyDetail();
    CompanyDetail getCompanyDetailById(int id);
    CompanyDetail insertCompanyDetail(CompanyDetail companyDetail);
    CompanyDetail updateCompanyDetail(CompanyDetail companyDetail);
    int deleteCompanyDetail(int id);
    
}
