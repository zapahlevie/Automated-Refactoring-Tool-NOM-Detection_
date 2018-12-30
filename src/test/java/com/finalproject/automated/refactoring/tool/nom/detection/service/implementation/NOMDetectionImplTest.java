package com.finalproject.automated.refactoring.tool.nom.detection.service.implementation;

import com.finalproject.automated.refactoring.tool.model.ClassModel;
import com.finalproject.automated.refactoring.tool.model.MethodModel;
import com.finalproject.automated.refactoring.tool.model.PropertyModel;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class NOMDetectionImplTest {

    private ClassModel classModel;
    private ClassModel classModelNoMethod;
    private NOMDetectionImpl nomDetection;

    @Before
    public void setUp(){
        nomDetection = new NOMDetectionImpl();
        classModel = createClassModel();
        classModelNoMethod = createClassModelNoMethod();
    }

    @Test
    public void nomDetection_success() {
        Long count = nomDetection.nomDetection(classModel);
        assertEquals(2, count.intValue());
    }

    @Test
    public void nomDetection_success_emptyMethodModel() {
        Long count = nomDetection.nomDetection(classModelNoMethod);
        assertEquals(0, count.intValue());
    }

    @Test(expected = NullPointerException.class)
    public void nomDetection_success_nullBody() {
        nomDetection.nomDetection(null);
    }

    private ClassModel createClassModel() {
        List<MethodModel> methodModels = new ArrayList<>();

        methodModels.add(MethodModel.builder()
                .keywords(Collections.singletonList("public"))
                .name("call")
                .parameters(null)
                .exceptions(Arrays.asList("Exception", "IOException"))
                .body("")
                .build());

        methodModels.add(MethodModel.builder()
                .keywords(Collections.singletonList("public"))
                .returnType("MyResponse<Integer>")
                .name("go")
                .parameters(Collections.singletonList(
                        PropertyModel.builder()
                                .type("Integer")
                                .name("time")
                                .build()))
                .body("")
                .build());

        return ClassModel.builder()
                .packageName("com.finalproject.automated.refactoring.tool")
                .imports(Arrays.asList("import java.util.ArrayList", "import java.util.Arrays;"))
                .contentOuter("")
                .keywords(Collections.singletonList("public"))
                .name("TestClassImpl")
                .extend("")
                .implement("TestClass")
                .attributes(Collections.singletonList(
                        PropertyModel.builder()
                                .type("Integer")
                                .name("number")
                                .build()))
                .methodModels(methodModels)
                .contentInner("")
                .loc(0L)
                .build();
    }

    private ClassModel createClassModelNoMethod() {
        return ClassModel.builder()
                .packageName("com.finalproject.automated.refactoring.tool")
                .imports(Arrays.asList("import java.util.ArrayList", "import java.util.Arrays;"))
                .contentOuter("")
                .keywords(Collections.singletonList("public"))
                .name("TestClassImpl")
                .extend("")
                .implement("TestClass")
                .attributes(Collections.singletonList(
                        PropertyModel.builder()
                                .type("Integer")
                                .name("number")
                                .build()))
                .methodModels(null)
                .contentInner("")
                .loc(0L)
                .build();
    }
}
