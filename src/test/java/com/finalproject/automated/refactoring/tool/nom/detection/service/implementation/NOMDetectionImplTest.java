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
                .name("EmailHelp")
                .parameters(Arrays.asList(
                        PropertyModel.builder()
                                .type("String")
                                .name("emailDestination")
                                .build(),
                        PropertyModel.builder()
                                .type("String")
                                .name("emailCc")
                                .build(),
                        PropertyModel.builder()
                                .type("String")
                                .name("emailBcc")
                                .build(),
                        PropertyModel.builder()
                                .type("String")
                                .name("emailSubject")
                                .build(),
                        PropertyModel.builder()
                                .type("String")
                                .name("emailContent")
                                .build()))
                .exceptions(Arrays.asList("Exception", "IOException"))
                .body("\n" +
                        "       mEmailSubject = emailDestination;\n" +
                        "       mEmailSubject = emailCc;\n" +
                        "       mEmailSubject = emailBcc;\n" +
                        "       mEmailSubject = emailSubject;\n" +
                        "       mEmailContent = emailContent;\n" +
                        "\n")
                .build());

        methodModels.add(MethodModel.builder()
                .keywords(Collections.singletonList("public"))
                .returnType("MyResponse<Integer>")
                .name("addGiftInfoCategory")
                .parameters(Collections.singletonList(
                        PropertyModel.builder()
                                .type("GiftInfoCategory")
                                .name("giftInfoCategory")
                                .build()))
                .body("\n" +
                        "        String message;\n" +
                        "        int response;\n" +
                        "\n" +
                        "        try {\n" +
                        "            giftInfoCategory = mGiftInfoCategoryService.addGiftInfoCategory(giftInfoCategory);\n" +
                        "\n" +
                        "            boolean isSuccess = giftInfoCategory != null;\n" +
                        "            message = isSuccess ? \"Gift info category add success\" : \"Gift info category add failed\";\n" +
                        "            response = isSuccess ? 1 : 0;\n" +
                        "        } catch (DataIntegrityViolationException e) {\n" +
                        "            message = \"Gift info category add failed - Gift info category already exists\";\n" +
                        "            response = 0;\n" +
                        "        } catch (Exception e) {\n" +
                        "            message = \"Gift info category add failed - Internal Server Error\";\n" +
                        "            response = 0;\n" +
                        "        }\n" +
                        "\n" +
                        "        return new MyResponse<>(message, response);\n" +
                        "\n")
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
                                .type("GiftInfoCategory")
                                .name("giftInfoCategory")
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
                                .type("GiftInfoCategory")
                                .name("giftInfoCategory")
                                .build()))
                .methodModels(null)
                .contentInner("")
                .loc(0L)
                .build();
    }
}
