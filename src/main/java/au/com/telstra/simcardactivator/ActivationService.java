package au.com.telstra.simcardactivator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivationService {

    @Autowired
    private ActivationRecordRepository activationRecordRepository;

    public void saveActivationRecord(String iccid, String customerEmail, boolean active) {
        ActivationRecord activationRecord = new ActivationRecord(iccid, customerEmail, active);
        activationRecordRepository.save(activationRecord);
    }

    public ActivationRecord getActivationRecord(long simCardId) {
        return activationRecordRepository.findById(simCardId).orElse(null);
    }
}

