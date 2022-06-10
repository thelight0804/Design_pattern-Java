package conf.processor;

import com.google.auto.service.AutoService;
import conf.annotation.Singleton;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

/**
 * This class is used to generate the Singleton class.
 * creates getInstance() method in the @Singleton annotated class.
 * @see Singleton
 * @version 1.0
 * @author Mina_
 */
@SupportedAnnotationTypes("conf.annotation.Singleton")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class SingletonProcessor extends AbstractProcessor {
    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        messager = processingEnv.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(Singleton.class)) {
            messager.printMessage(Diagnostic.Kind.NOTE, "Processing " + element.getSimpleName());
            try {
                String className = element.getSimpleName().toString();
                String packageName = processingEnv.getElementUtils().getPackageOf(element).getQualifiedName().toString();
                String classFullName = packageName + "." + className;
                String sourceCode = "package " + packageName + ";\n" +
                        "\n" +
                        "public class " + className + " {\n" +
                        "    private volatile static " + classFullName + " instance = null;\n" +
                        "\n" +
                        "    private " + classFullName + "() {}\n" +
                        "\n" +
                        "    public static " + classFullName + " getInstance() {\n" +
                        "       if (instance == null) {\n" +
                        "           synchronized (" + classFullName + ".class) {\n" +
                        "               if (instance == null) {" +
                        "                   instance = new " + classFullName + "();\n" +
                        "               }\n" +
                        "           }\n" +
                        "        }\n" +
                        "        return instance;\n" +
                        "    }\n" +
                        "}\n";
                messager.printMessage(Diagnostic.Kind.NOTE, "Source code generated: " + sourceCode);
                processingEnv.getFiler().createSourceFile(classFullName, element).openWriter().append(sourceCode).close();
            } catch (Exception e) {
                messager.printMessage(Diagnostic.Kind.ERROR, "Error: " + e.getMessage());
            }
        }
        return true;
    }
}

