package com.attestation.project.service;

import com.attestation.project.exception.MissingOrIncorrectFormat;
import com.attestation.project.recordDto.SocksRecord;
import org.springframework.stereotype.Service;

@Service
public class ValidatorService {

    public void checkSocksRecord(SocksRecord socksRecord) {
        if(socksRecord.getCottonPart()<0 || socksRecord.getCottonPart()>100 || socksRecord.getQuantity()<=0){
            throw new MissingOrIncorrectFormat();
        }
    }
}
