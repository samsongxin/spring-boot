package com.samsong.heldout;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple Java file parser that extracts classes, methods, fields,
 * and other structural elements from Java source files.
 */
public class JavaFileParser {
    
    private String filePath;
    private String fileContent;
    private String packageName;
    private List<String> imports;
    private List<ClassInfo> classes;
    
    /**
     * Constructor that initializes the parser with a file path
     * @param filePath path to the Java file
     */
    public JavaFileParser(String filePath) {
        this.filePath = filePath;
        this.imports = new ArrayList<>();
        this.classes = new ArrayList<>();
    }
    
    /**
     * Parses the Java file and extracts its components
     * @return true if parsing was successful, false otherwise
     */
    public boolean parse() {
        try {
            // Read the entire file content
            readFileContent();
            
            // Extract package declaration
            extractPackage();
            
            // Extract import statements
            extractImports();
            
            // Extract classes, interfaces, and enums
            extractClasses();
            
            return true;
        } catch (IOException e) {
            System.err.println("Error parsing file: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Reads the entire content of the file into memory
     * @throws IOException if the file cannot be read
     */
    private void readFileContent() throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        this.fileContent = content.toString();
    }
    
    /**
     * Extracts the package declaration from the file content
     */
    private void extractPackage() {
        Pattern pattern = Pattern.compile("package\\s+([\\w.]+);");
        Matcher matcher = pattern.matcher(fileContent);
        if (matcher.find()) {
            this.packageName = matcher.group(1);
        }
    }
    
    /**
     * Extracts all import statements from the file content
     */
    private void extractImports() {
        Pattern pattern = Pattern.compile("import\\s+([\\w.*]+);");
        Matcher matcher = pattern.matcher(fileContent);
        while (matcher.find()) {
            imports.add(matcher.group(1));
        }
    }
    
    /**
     * Extracts all class, interface, and enum declarations from the file content
     */
    private void extractClasses() {
        // Pattern to match class/interface/enum declarations
        Pattern classPattern = Pattern.compile(
            "(?:public|private|protected)?\\s*" +
            "(?:static)?\\s*" +
            "(?:final)?\\s*" +
            "(class|interface|enum)\\s+" +
            "([\\w<>]+)\\s*" +
            "(?:extends\\s+([\\w<>.]+))?\\s*" +
            "(?:implements\\s+([\\w<>.,\\s]+))?" +
            "\\s*\\{", Pattern.DOTALL);
        
        Matcher matcher = classPattern.matcher(fileContent);
        while (matcher.find()) {
            String type = matcher.group(1);
            String name = matcher.group(2);
            String extendsClass = matcher.group(3);
            String implementsInterfaces = matcher.group(4);
            
            ClassInfo classInfo = new ClassInfo(type, name, extendsClass, implementsInterfaces);
            
            // Extract the class body to find methods and fields
            int openBraces = 1;
            int startPos = matcher.end();
            int endPos = startPos;
            
            while (openBraces > 0 && endPos < fileContent.length()) {
                char c = fileContent.charAt(endPos);
                if (c == '{') openBraces++;
                else if (c == '}') openBraces--;
                endPos++;
            }
            
            if (openBraces == 0) {
                String classBody = fileContent.substring(startPos, endPos - 1);
                extractMethods(classInfo, classBody);
                extractFields(classInfo, classBody);
            }
            
            classes.add(classInfo);
        }
    }
    
    /**
     * Extracts method declarations from a class body
     * @param classInfo the ClassInfo object to update
     * @param classBody the body of the class
     */
    private void extractMethods(ClassInfo classInfo, String classBody) {
        // Pattern to match method declarations
        Pattern methodPattern = Pattern.compile(
            "(?:public|private|protected)?\\s*" +
            "(?:static)?\\s*" +
            "(?:final)?\\s*" +
            "(?:<[\\w,\\s<>]+>)?\\s*" +
            "([\\w<>\\[\\]]+)\\s+" +  // Return type
            "([\\w]+)\\s*" +          // Method name
            "\\(([^)]*)\\)\\s*" +     // Parameters
            "(?:throws\\s+([\\w,\\s.]+))?\\s*" +
            "\\{", Pattern.DOTALL);
            
        Matcher matcher = methodPattern.matcher(classBody);
        while (matcher.find()) {
            String returnType = matcher.group(1);
            String name = matcher.group(2);
            String parameters = matcher.group(3);
            String exceptions = matcher.group(4);
            
            MethodInfo methodInfo = new MethodInfo(returnType, name, parameters, exceptions);
            classInfo.methods.add(methodInfo);
        }
    }
    
    /**
     * Extracts field declarations from a class body
     * @param classInfo the ClassInfo object to update
     * @param classBody the body of the class
     */
    private void extractFields(ClassInfo classInfo, String classBody) {
        // Pattern to match field declarations
        Pattern fieldPattern = Pattern.compile(
            "(?:public|private|protected)?\\s*" +
            "(?:static)?\\s*" +
            "(?:final)?\\s*" +
            "([\\w<>\\[\\].]+)\\s+" +  // Field type
            "([\\w]+)" +               // Field name
            "(?:\\s*=\\s*[^;]+)?;",    // Optional initialization
            Pattern.DOTALL);
            
        Matcher matcher = fieldPattern.matcher(classBody);
        while (matcher.find()) {
            String type = matcher.group(1);
            String name = matcher.group(2);
            
            FieldInfo fieldInfo = new FieldInfo(type, name);
            classInfo.fields.add(fieldInfo);
        }
    }
    
    /**
     * Class to hold information about a class/interface/enum
     */
    public static class ClassInfo {
        private String type;      // "class", "interface", or "enum"
        private String name;
        private String extendsClass;
        private String implementsInterfaces;
        private List<MethodInfo> methods;
        private List<FieldInfo> fields;
        
        public ClassInfo(String type, String name, String extendsClass, String implementsInterfaces) {
            this.type = type;
            this.name = name;
            this.extendsClass = extendsClass;
            this.implementsInterfaces = implementsInterfaces;
            this.methods = new ArrayList<>();
            this.fields = new ArrayList<>();
        }
        
        public String getType() { return type; }
        public String getName() { return name; }
        public String getExtendsClass() { return extendsClass; }
        public String getImplementsInterfaces() { return implementsInterfaces; }
        public List<MethodInfo> getMethods() { return methods; }
        public List<FieldInfo> getFields() { return fields; }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(type).append(" ").append(name);
            
            if (extendsClass != null) {
                sb.append(" extends ").append(extendsClass);
            }
            
            if (implementsInterfaces != null) {
                sb.append(" implements ").append(implementsInterfaces);
            }
            
            sb.append("\nMethods (").append(methods.size()).append("):");
            for (MethodInfo method : methods) {
                sb.append("\n  ").append(method);
            }
            
            sb.append("\nFields (").append(fields.size()).append("):");
            for (FieldInfo field : fields) {
                sb.append("\n  ").append(field);
            }
            
            return sb.toString();
        }
    }
    
    /**
     * Class to hold information about a method
     */
    public static class MethodInfo {
        private String returnType;
        private String name;
        private String parameters;
        private String exceptions;
        
        public MethodInfo(String returnType, String name, String parameters, String exceptions) {
            this.returnType = returnType;
            this.name = name;
            this.parameters = parameters;
            this.exceptions = exceptions;
        }
        
        public String getReturnType() { return returnType; }
        public String getName() { return name; }
        public String getParameters() { return parameters; }
        public String getExceptions() { return exceptions; }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(returnType).append(" ").append(name).append("(").append(parameters).append(")");
            
            if (exceptions != null) {
                sb.append(" throws ").append(exceptions);
            }
            
            return sb.toString();
        }
    }
    
    /**
     * Class to hold information about a field
     */
    public static class FieldInfo {
        private String type;
        private String name;
        
        public FieldInfo(String type, String name) {
            this.type = type;
            this.name = name;
        }
        
        public String getType() { return type; }
        public String getName() { return name; }
        
        @Override
        public String toString() {
            return type + " " + name;
        }
    }
    
    /**
     * Get the package name of the parsed file
     * @return the package name
     */
    public String getPackageName() {
        return packageName;
    }
    
    /**
     * Get the list of imports from the parsed file
     * @return list of imports
     */
    public List<String> getImports() {
        return imports;
    }
    
    /**
     * Get the list of classes from the parsed file
     * @return list of ClassInfo objects
     */
    public List<ClassInfo> getClasses() {
        return classes;
    }
    
    /**
     * Entry point for testing the parser
     */
    public static void main(String[] args) {
        // String filePath = "src/test/java/com/samsong/heldout/JavaFileParser.java";
		String filePath = "src/main/java/com/samsong/reward/processor/LandingPageVisitProcessor.java";
        JavaFileParser parser = new JavaFileParser(filePath);
        
        if (parser.parse()) {
            System.out.println("File parsed successfully!");
            System.out.println("Package: " + parser.getPackageName());
            
            System.out.println("\nImports (" + parser.getImports().size() + "):");
            for (String imp : parser.getImports()) {
                System.out.println("  " + imp);
            }
            
            System.out.println("\nClasses (" + parser.getClasses().size() + "):");
            for (ClassInfo classInfo : parser.getClasses()) {
                System.out.println("\n" + classInfo);
            }
        } else {
            System.out.println("Failed to parse file: " + filePath);
        }
    }
}