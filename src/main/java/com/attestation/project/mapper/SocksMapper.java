package com.attestation.project.mapper;

import com.attestation.project.entity.SocksEntity;
import com.attestation.project.recordDto.SocksRecord;
import org.springframework.stereotype.Component;

@Component
public class SocksMapper {
    public SocksEntity socksDtoToEntity(SocksRecord socksRecord) {
        SocksEntity socksEntity = new SocksEntity();
        socksEntity.setColor(socksRecord.getColor());
        socksEntity.setCottonPart(socksRecord.getCottonPart());
        socksEntity.setQuantity(socksRecord.getQuantity());
        return  socksEntity;
    }
}
