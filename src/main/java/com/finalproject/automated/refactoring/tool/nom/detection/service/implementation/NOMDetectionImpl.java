package com.finalproject.automated.refactoring.tool.nom.detection.service.implementation;

import com.finalproject.automated.refactoring.tool.model.ClassModel;
import com.finalproject.automated.refactoring.tool.nom.detection.service.NOMDetection;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class NOMDetectionImpl implements NOMDetection {

    @Override
    public Long nomDetection(ClassModel classModel) {
        if(classModel.getMethodModels() == null){
            return 0L;
        }
        return (Long) (long) classModel.getMethodModels().size();
    }
}
