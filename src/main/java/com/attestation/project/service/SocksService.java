package com.attestation.project.service;

import com.attestation.project.entity.SocksEntity;
import com.attestation.project.exception.MissingOrIncorrectFormat;
import com.attestation.project.mapper.SocksMapper;
import com.attestation.project.recordDto.SocksRecord;
import com.attestation.project.repository.SocksRepository;
import org.springframework.stereotype.Service;

@Service
public class SocksService {
    private final SocksMapper socksMapper;
    public final ValidatorService validatorService;
    private final SocksRepository socksRepository;

    public SocksService(SocksMapper socksMapper, ValidatorService validatorService, SocksRepository socksRepository) {
        this.socksMapper = socksMapper;
        this.validatorService = validatorService;
        this.socksRepository = socksRepository;
    }


    public void income(SocksRecord socksRecord) {
        validatorService.checkSocksRecord(socksRecord);
        SocksEntity newSocks = socksMapper.socksDtoToEntity(socksRecord);
        SocksEntity oldSocks = socksRepository.findSocksEntitiesByColorAndCottonPart(newSocks.getColor(), newSocks.getCottonPart());
        if (oldSocks == null) {
            socksRepository.save(newSocks);
        } else {
            oldSocks.setQuantity(oldSocks.getQuantity() + newSocks.getQuantity());
            socksRepository.save(oldSocks);
        }
    }

    public void outcome(SocksRecord socksRecord) {
        validatorService.checkSocksRecord(socksRecord);
        SocksEntity newSocks = socksMapper.socksDtoToEntity(socksRecord);
        SocksEntity oldSocks = socksRepository.findSocksEntitiesByColorAndCottonPart(newSocks.getColor(), newSocks.getCottonPart());
        if (oldSocks == null || oldSocks.getQuantity() < newSocks.getQuantity()) {
            throw new MissingOrIncorrectFormat();
        } else {
            oldSocks.setQuantity(oldSocks.getQuantity() - newSocks.getQuantity());
            if (oldSocks.getQuantity() < 1) {
                socksRepository.delete(oldSocks);
            } else {
                socksRepository.save(oldSocks);
            }
        }
    }

    public String findCount(String color, String operation, int cottonPart) {
        switch (operation) {
            case "moreThan":
                return changeToString(socksRepository.findAllQuantityByColorAndCottonPartMoreThan(color, cottonPart));
            case "lessThan":
                return changeToString(socksRepository.findAllQuantityByColorAndCottonPartIsLessThan(color, cottonPart));
            case "equal":
                if (!(socksRepository.findSocksEntitiesByColorAndCottonPart(color, cottonPart) == null)) {
                    return changeToString(socksRepository.findSocksEntitiesByColorAndCottonPart(color, cottonPart).getQuantity());
                } else {
                    return changeToString(null);
                }
        }
        throw new MissingOrIncorrectFormat();
    }

    public String changeToString(Integer i) {
        if (i == null) {
            return String.valueOf(0);
        } else {
            return String.valueOf(i);
        }
    }
}
