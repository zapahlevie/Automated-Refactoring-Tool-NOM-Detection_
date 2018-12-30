package com.finalproject.automated.refactoring.tool.nom.detection.service;

import com.finalproject.automated.refactoring.tool.model.ClassModel;
import lombok.NonNull;

public interface NOMDetection {

    Long nomDetection(@NonNull ClassModel classModel);
}
