import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestAsinDataProvider {
    static String getASINs(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        } catch (IOException e) {
             throw new RuntimeException(e);
        }
    }
}
