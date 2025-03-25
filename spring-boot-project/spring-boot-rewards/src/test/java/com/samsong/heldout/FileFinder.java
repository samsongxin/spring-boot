package com.samsong.heldout;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utility class to find files with specific extensions in a directory
 */
public class FileFinder {

	public static boolean doesFileExistInProject( String fileName) {
		List<File> result;
		final String directoryPath = ".";

		// Use Java NIO for faster recursive search
		try (Stream<Path> paths = Files.walk(Paths.get(directoryPath))) {
			result = paths
					.filter(Files::isRegularFile)
					.filter(p -> p.toString().toLowerCase().endsWith(fileName.toLowerCase()))
					.map(Path::toFile)
					.collect(Collectors.toList());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		// System.out.println("all files=" + result);
		return !result.isEmpty();
	}

	public static File findFile( String fileName) {
		List<File> result;
		final String directoryPath = ".";

		// Use Java NIO for faster recursive search
		try (Stream<Path> paths = Files.walk(Paths.get(directoryPath))) {
			result = paths
					.filter(Files::isRegularFile)
					.filter(p -> p.toString().toLowerCase().endsWith(fileName.toLowerCase()))
					.map(Path::toFile)
					.collect(Collectors.toList());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		if(result.size() != 1){
			throw new RuntimeException("No file or more than one file found: " + result);
		}
		return result.get(0);
	}

	public static List<File> findProjectFilesWithExtension(final String extension){
		try {
			return findFilesWithExtension(".", extension);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String readFileContent(String filePath) {
		StringBuilder content = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				content.append(line).append("\n");
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return content.toString();
	}
    
    /**
     * Finds all files with the specified extension in the given directory and its subdirectories
     * @param directoryPath Path to the directory to search in
     * @param extension File extension to search for (without the dot)
     * @return List of File objects matching the criteria
     * @throws IOException If an I/O error occurs
     */
    public static List<File> findFilesWithExtension(String directoryPath, String extension) throws IOException {
        List<File> result = new ArrayList<>();
        File directory = new File(directoryPath);
        
        // Check if the directory exists
        if (!directory.exists()) {
            throw new IllegalArgumentException("Directory does not exist: " + directoryPath);
        }
        
        // Check if it's actually a directory
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("Not a directory: " + directoryPath);
        }

        // Normalize the extension by removing the dot prefix if present
        String normalizedExtension = extension.startsWith(".") ? extension.substring(1) : extension;
        
        // Use Java NIO for faster recursive search
        try (Stream<Path> paths = Files.walk(Paths.get(directoryPath))) {
            result = paths
                .filter(Files::isRegularFile)
                .filter(p -> p.toString().endsWith("." + normalizedExtension))
                .map(Path::toFile)
                .collect(Collectors.toList());
        }
        
        return result;
    }

    /**
     * Alternative method using traditional File approach (non-streaming)
     * @param directoryPath Path to the directory to search in
     * @param extension File extension to search for (without the dot)
     * @return List of File objects matching the criteria
     */
    public static List<File> findFilesWithExtensionTraditional(String directoryPath, String extension) {
        List<File> result = new ArrayList<>();
        File directory = new File(directoryPath);
        
        // Check if the directory exists and is a directory
        if (!directory.exists() || !directory.isDirectory()) {
            throw new IllegalArgumentException("Invalid directory: " + directoryPath);
        }
        
        // Normalize the extension
        String normalizedExtension = extension.startsWith(".") ? extension.substring(1) : extension;
        
        // Call recursive helper method
        findFilesRecursively(directory, normalizedExtension, result);
        
        return result;
    }
    
    /**
     * Helper method to recursively search for files
     * @param directory The current directory to search in
     * @param extension The file extension to filter by
     * @param result The list to add matching files to
     */
    private static void findFilesRecursively(File directory, String extension, List<File> result) {
        File[] files = directory.listFiles();
        
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // Recursive call for subdirectories
                    findFilesRecursively(file, extension, result);
                } else {
                    // Check if the file has the desired extension
                    if (file.getName().endsWith("." + extension)) {
                        result.add(file);
                    }
                }
            }
        }
    }

    /**
     * Demo program to test the file finder
     */
    public static void main(String[] args) {

      //  String directoryPath = "src/main/java";
		String directoryPath = ".";
        String extension = "xml";
        
        try {
            System.out.println("Searching for *." + extension + " files in " + directoryPath);
            
            // Use the stream-based method
            List<File> files = findFilesWithExtension(directoryPath, extension);
            
            System.out.println("Found " + files.size() + " files:");
            for (File file : files) {
                System.out.println(file.getAbsolutePath());
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}